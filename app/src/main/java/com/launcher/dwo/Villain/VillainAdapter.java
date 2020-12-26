package com.launcher.dwo.Villain;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.launcher.dwo.Create.CreateDialog;
import com.launcher.dwo.Hero.Hero;
import com.launcher.dwo.R;
import com.launcher.dwo.Hero.HeroShowDialog;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class VillainAdapter extends RecyclerView.Adapter<VillainAdapter.My2ViewHolder>{
    private ArrayList<Hero> mDataset;
    LayoutInflater inflater;
    private int RoomID;
    private DialogFragment dialogFragment;
    private boolean isEvil;

    public static class My2ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TextView inventory;
        public TextView specifications;
        public CircleImageView circleImageView;
        public My2ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_item);
            circleImageView = itemView.findViewById(R.id.circle_image);
            inventory = itemView.findViewById(R.id.text_item3);
            specifications = itemView.findViewById(R.id.text_item2);
        }
    }

    public VillainAdapter(Context context, ArrayList<Hero> myDataset, int RoomID, boolean isEvil) {
        this.mDataset = myDataset;
        this.inflater = LayoutInflater.from(context);
        this.RoomID = RoomID;
        this.isEvil = isEvil;
    }

    @Override
    public VillainAdapter.My2ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = inflater.inflate(R.layout.create_rooms_item, parent, false);
        My2ViewHolder vh = new My2ViewHolder(view);
        return vh;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final My2ViewHolder holder, final int position) {
        final Hero hero = mDataset.get(position);
        if(!mDataset.get(position).getRole().equals("Add hero")) {
            if(!mDataset.get(position).isEvil()) {
                holder.textView.setText(mDataset.get(position).getName() + " [" + mDataset.get(position).getRole() + "]");
            } else{
                holder.textView.setText(mDataset.get(position).getName() + " [Evil]");
            }
            Log.d("DebugLogs", "VillainAdapter: inventory: " + mDataset.get(position).getInventory());
            holder.inventory.setText(mDataset.get(position).getInventory() + "... Money: " + mDataset.get(position).getMoney() + " ...");
            holder.specifications.setText(
                    "Health: " + mDataset.get(position).getSpecifications().getHealth() + ", " +
                            "Strength: " + mDataset.get(position).getSpecifications().getStrength() + ", " +
                            "Charisma: " + mDataset.get(position).getSpecifications().getCharisma() + ", " +
                            "Intelligence" + mDataset.get(position).getSpecifications().getIntelligence() + ", " +
                            "Agility: " + mDataset.get(position).getSpecifications().getAgility() + ", " +
                            "Stamina: " + mDataset.get(position).getSpecifications().getStamina() + " ..."
            );
        } else{
            holder.textView.setText(mDataset.get(position).getName() + " [" + mDataset.get(position).getRole() + "]");
            holder.specifications.setText("...");
            holder.inventory.setText("");
            if (isEvil){
                holder.textView.setText(" [" + "Add villain" + "]");
            }
        }
        if(!mDataset.get(position).isDownloaded()){
            Glide.with(holder.itemView.getContext()).load(mDataset.get(position).getResID()).into(holder.circleImageView);
        } else{
            Glide.with(holder.itemView.getContext()).load(mDataset.get(position).getUriResID()).into(holder.circleImageView);
        }

        holder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDataset.get(position).getRole().equals("Add hero")) {
                    Log.d("DebugLogs", "VillainAdapter: DialogFragment is created");
                    dialogFragment = CreateDialog.newInstance(holder.itemView.getContext(), RoomID, isEvil);
                    FragmentManager fragmentManager = ((AppCompatActivity) holder.itemView.getContext()).getSupportFragmentManager();
                    dialogFragment.show(fragmentManager, "dlg");
                } else{
                    Log.d("DebugLogs", "VillainAdapter: hero: " + mDataset.get(position).toString() );
                    dialogFragment = HeroShowDialog.newInstance((Application) holder.itemView.getContext().getApplicationContext(), mDataset.get(position), position);
                    FragmentManager fragmentManager = ((AppCompatActivity) holder.itemView.getContext()).getSupportFragmentManager();
                    dialogFragment.show(fragmentManager, "dlg");
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(isEvil) {
                    new VillainSharedPreferences(holder.itemView.getContext(), RoomID).deleteOneVillain(position);
                    notifyDataSetChanged();
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public DialogFragment getDialogFragment() {
        return dialogFragment;
    }
}
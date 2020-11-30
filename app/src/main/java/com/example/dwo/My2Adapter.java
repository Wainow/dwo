package com.example.dwo;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class My2Adapter extends RecyclerView.Adapter<My2Adapter.My2ViewHolder>{
    private ArrayList<Hero> mDataset;
    LayoutInflater inflater;
    private int RoomID;
    private DialogFragment dialogFragment;
    private boolean isEvil;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class My2ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
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

    // Provide a suitable constructor (depends on the kind of dataset)
    public My2Adapter(Context context, ArrayList<Hero> myDataset, int RoomID, boolean isEvil) {
        this.mDataset = myDataset;
        this.inflater = LayoutInflater.from(context);
        this.RoomID = RoomID;
        this.isEvil = isEvil;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public My2Adapter.My2ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View view = inflater.inflate(R.layout.create_rooms_item, parent, false);
        My2ViewHolder vh = new My2ViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final My2ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Hero hero = mDataset.get(position);
        if(!mDataset.get(position).getRole().equals("Add hero")) {
            if(!mDataset.get(position).isEvil()) {
                holder.textView.setText(mDataset.get(position).getName() + " [" + mDataset.get(position).getRole() + "]");
            } else{
                holder.textView.setText(mDataset.get(position).getName() + " [Evil]");
            }
            Log.d("DebugLogs", "My2Adapter: inventory: " + mDataset.get(position).getInventory());
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
                    Log.d("DebugLogs", "My2Adapter: DialogFragment is created");
                    dialogFragment = new CreateDialog(holder.itemView.getContext(), RoomID, isEvil);
                    FragmentManager fragmentManager = ((AppCompatActivity) holder.itemView.getContext()).getSupportFragmentManager();
                    dialogFragment.show(fragmentManager, "dlg");
                } else{
                    Log.d("DebugLogs", "My2Adapter: hero: " + mDataset.get(position).toString() );
                    dialogFragment = new ShowHeroDialog(holder.itemView.getContext(), mDataset.get(position), position);
                    FragmentManager fragmentManager = ((AppCompatActivity) holder.itemView.getContext()).getSupportFragmentManager();
                    dialogFragment.show(fragmentManager, "dlg");
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(isEvil) {
                    new SharedPreferencesHelper(holder.itemView.getContext(), RoomID).deleteOneVillain(position);
                    notifyDataSetChanged();
                }
                return true;
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public DialogFragment getDialogFragment() {
        return dialogFragment;
    }
}
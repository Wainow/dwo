package com.example.dwo.General;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dwo.Custom.FileWorker;
import com.example.dwo.R;
import com.example.dwo.Room;
import com.example.dwo.RoomActivity;
import com.example.dwo.Villain.VillainSharedPreferences;
import com.google.gson.Gson;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.dwo.General.GeneralRoomListFragment.getRandomInt0_10;
import static com.example.dwo.General.GeneralRoomListFragment.myDataset;

public class GeneralRoomListAdapter extends RecyclerView.Adapter<GeneralRoomListAdapter.MyViewHolder> {
    private ArrayList<Room> mDataset;
    LayoutInflater inflater;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView circle;
        public TextView name_of_room;
        public TextView number_of_players;
        public TextView starting;
        public MyViewHolder(View itemView) {
            super(itemView);
            circle = itemView.findViewById(R.id.room_circle_image);
            name_of_room = itemView.findViewById(R.id.room_text_item);
            number_of_players = itemView.findViewById(R.id.room_text_item2);
            starting = itemView.findViewById(R.id.room_text_item3);
        }
    }

    public GeneralRoomListAdapter(Context context, ArrayList<Room> myDataset) {
        mDataset = myDataset;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public GeneralRoomListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
        View view = inflater.inflate(R.layout.rooms_item, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        String imageAddress = "android.resource://"  + holder.itemView.getContext().getPackageName() + "/" + mDataset.get(position).getRoom_image_src();
        Glide.with(holder.itemView.getContext()).load(imageAddress).into(holder.circle);
        holder.name_of_room.setText(mDataset.get(position).getRoom_name());
        holder.starting.setText("Starting from " + getRandomInt0_10() + " min...");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), RoomActivity.class);
                intent.putExtra("position", position);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("DebugLogs", "Some Room has been removed");
                new VillainSharedPreferences(holder.itemView.getContext(), mDataset.get(position).getRoomID()).deleteVillains();
                myDataset.remove(position);
                String json = new Gson().toJson(myDataset);
                FileWorker fileWorker = new FileWorker(holder.itemView.getContext());
                fileWorker.writeFile(json);
                notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public String list_of_heroes_names(int position){
        String players = "";
        for(int i = 0; i < mDataset.get(position).getNumber_of_players(); i++){
            players += mDataset.get(position).getHeroes().get(i).getName() + ", ";
        }
        return players;
    }
}

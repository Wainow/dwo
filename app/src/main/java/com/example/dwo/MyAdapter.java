package com.example.dwo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.dwo.First2Fragment.getRandomInt0_10;
import static com.example.dwo.First2Fragment.myDataset;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Room> mDataset;
    LayoutInflater inflater;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
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

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context context, ArrayList<Room> myDataset) {
        mDataset = myDataset;
        this.inflater = LayoutInflater.from(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View view = inflater.inflate(R.layout.rooms_item, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        String imageAddress = "android.resource://"  + holder.itemView.getContext().getPackageName() + "/" + mDataset.get(position).getRoom_image_src();
        Glide.with(holder.itemView.getContext()).load(imageAddress).into(holder.circle);
        holder.name_of_room.setText(mDataset.get(position).getRoom_name());
        holder.number_of_players.setText("Players : " + String.valueOf(mDataset.get(position).getNumber_of_players()) + " - " + list_of_heroes_names(position));
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
                myDataset.remove(position);
                String json = new Gson().toJson(myDataset);
                FileWorker fileWorker = new FileWorker(holder.itemView.getContext());
                fileWorker.writeFile(json);
                notifyDataSetChanged();
                return false;
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
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

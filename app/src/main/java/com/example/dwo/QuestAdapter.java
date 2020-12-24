package com.example.dwo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestAdapter extends RecyclerView.Adapter<QuestAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Quest> data;
    private FragmentManager manager;
    private Boolean isOpen = false;
    private int RoomID;

    public QuestAdapter(Context context, ArrayList<Quest> myDataset, int RoomID){
        this.context = context;
        this.data = myDataset;
        this.RoomID = RoomID;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quest_item, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Log.d("DebugLogs", "QuestAdapter: onBindViewHolder: data.get(position).getName(): " + data.get(position).getName());
        holder.name_of_quest.setText("«" + data.get(position).getName() + "»");
        holder.history.setText(data.get(position).getHistory() + " ...");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DebugLogs", "QuestAdapter: onBindViewHolder: OnClick");
                if(!isOpen) {
                    ((AppCompatActivity)holder.itemView.getContext()).getSupportFragmentManager().getFragments().get(1).getChildFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.fragment_container, QuestRoom.newInstance(
                                    data.get(position).getName(),
                                    data.get(position).getHistory(),
                                    data.get(position).getDescription(),
                                    RoomID,
                                    position
                            ))
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new SharedPreferencesQuest(holder.itemView.getContext(), RoomID).deleteOneQuest(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name_of_quest;
        public TextView history;
        public MyViewHolder(View itemView) {
            super(itemView);
            name_of_quest = itemView.findViewById(R.id.quest_text_item);
            history = itemView.findViewById(R.id.quest_text_item2);
        }
    }
}

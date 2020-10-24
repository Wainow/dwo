package com.example.dwo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class My2Adapter extends RecyclerView.Adapter<My2Adapter.My2ViewHolder> {
    private ArrayList<Hero> mDataset;
    LayoutInflater inflater;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class My2ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public CircleImageView circleImageView;
        public My2ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_item);
            circleImageView = itemView.findViewById(R.id.circle_image);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public My2Adapter(Context context, ArrayList<Hero> myDataset) {
        mDataset = myDataset;
        this.inflater = LayoutInflater.from(context);
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
        holder.textView.setText(mDataset.get(position).getName());
        switch (mDataset.get(position).getRole()){
            case "Knight":
                holder.circleImageView.setImageResource(R.drawable.mini_knight);
                break;
            case "Mag":
                holder.circleImageView.setImageResource(R.drawable.mini_mag);
                break;
            case "Rower":
                holder.circleImageView.setImageResource(R.drawable.mini_row);
                break;
            case "Thief":
                holder.circleImageView.setImageResource(R.drawable.mini_thief);
                break;
            default:
                holder.circleImageView.setImageResource(R.drawable.mini_q);
                break;
        };

        holder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Congratulations", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
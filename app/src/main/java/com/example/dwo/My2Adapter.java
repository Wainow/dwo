package com.example.dwo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class My2Adapter extends RecyclerView.Adapter<My2Adapter.My2ViewHolder> {
    private int[] mDataset;
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
    public My2Adapter(Context context, int[] myDataset) {
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
    public void onBindViewHolder(My2ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        switch(mDataset[position]){
            default: holder.textView.setText("Add hero");
                holder.circleImageView.setImageResource(R.drawable.mini_q);
            case 1: holder.textView.setText("Knight");
                holder.circleImageView.setImageResource(R.drawable.veryknight);
                break;
            case 2: holder.textView.setText("Mag");
                holder.circleImageView.setImageResource(R.drawable.veryminimag);
                break;
            case 3: holder.textView.setText("Rower");
                holder.circleImageView.setImageResource(R.drawable.veryrow);
                break;
            case 4: holder.textView.setText("Thief");
                holder.circleImageView.setImageResource(R.drawable.mini_thief);
                break;
            case 5: holder.textView.setText("Add hero");
                holder.circleImageView.setImageResource(R.drawable.mini_q);
                break;
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
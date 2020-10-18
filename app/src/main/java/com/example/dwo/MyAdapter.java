package com.example.dwo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private int[] mDataset;
    LayoutInflater inflater;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public CircleImageView circleImageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_item);
            circleImageView = itemView.findViewById(R.id.circle_image);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context context, int[] myDataset) {
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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        switch(mDataset[position]){
            default: holder.textView.setText("Knight's room");
                    holder.circleImageView.setImageResource(R.drawable.veryknight);
            case 1: holder.textView.setText("Knight's room");
                    holder.circleImageView.setImageResource(R.drawable.veryknight);
                break;
            case 2: holder.textView.setText("Mag's room");
                    holder.circleImageView.setImageResource(R.drawable.veryminimag);
                break;
            case 3: holder.textView.setText("Row's room");
                    holder.circleImageView.setImageResource(R.drawable.veryrow);
                break;
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}

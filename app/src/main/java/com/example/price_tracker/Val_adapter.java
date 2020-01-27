package com.example.price_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Val_adapter extends RecyclerView.Adapter<Val_adapter.Viewholder> {
    private Context context;
    private ArrayList<Item_list> arrayList;
    public Val_adapter(Context a_context, ArrayList<Item_list> a_arrayList) {
        context = a_context;
        arrayList = a_arrayList;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Item_list current=arrayList.get(position);
        String get_item = current.getval();

        holder.textView.setText(get_item);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        public  TextView textView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            textView=( TextView) itemView.findViewById(R.id.tvholder);
        }
    }

}



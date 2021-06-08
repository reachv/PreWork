package com.example.myapplication;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.Viewholder> {

    public interface OnClickListener{
        void onItemClicked(int position);
    }
    public interface OnLongClickListener{
        void onItemLongClick(int position);
    }
    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener ClickListener;
    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener ClickListener){
        this.items = items;
        this.longClickListener = longClickListener;
        this.ClickListener = ClickListener;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        return new Viewholder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ItemsAdapter.Viewholder holder, int position) {
        String item = items.get(position);

        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public Viewholder(@NonNull View itemView){
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    ClickListener.onItemClicked(getAdapterPosition());
                }
            });
            tvItem.setOnLongClickListener(new View.OnLongClickListener(){
               public boolean onLongClick(View v){
                   longClickListener.onItemLongClick(getAdapterPosition());
                   return true;
               }
            });

        }
    }
}

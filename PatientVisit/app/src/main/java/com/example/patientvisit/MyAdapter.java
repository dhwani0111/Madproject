package com.example.patientvisit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<product> listItems;
    Context context;

    public MyAdapter(List<product> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        product p = listItems.get(position);
        holder.textViewname.setText(p.getName());
        holder.textViewcharge.setText(p.getCost());
        holder.textViewage.setText(p.getAge());
        holder.textViewphone.setText(p.getPhone_no());
        holder.textViewdate.setText(p.getStarting_date());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        public TextView textViewname,textViewage,textViewdate,textViewphone,textViewcharge;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewname = itemView.findViewById(R.id.textview_name);
            textViewage = itemView.findViewById(R.id.textview_brand);
            textViewdate = itemView.findViewById(R.id.textview_desc);
            textViewphone = itemView.findViewById(R.id.textview_price);
            textViewcharge = itemView.findViewById(R.id.textview_quantity);
        }
    }

}

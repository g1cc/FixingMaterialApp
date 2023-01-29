package com.example.fixingmaterial.Adaptor;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixingmaterial.Domain.MyOrdersDomain;
import com.example.fixingmaterial.R;

import java.util.ArrayList;

public class myOrdersAdaptor extends RecyclerView.Adapter<myOrdersAdaptor.ViewHolder>{
    ArrayList<MyOrdersDomain> myOrdersDomains;

    public myOrdersAdaptor(ArrayList<MyOrdersDomain> myOrdersDomains)
    {
        this.myOrdersDomains = myOrdersDomains;
    }


    @NonNull
    @Override
    public myOrdersAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_myorders, parent, false);
        return new ViewHolder(inflate);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull myOrdersAdaptor.ViewHolder holder, int position) {
        holder.title.setText("Заказ №" + myOrdersDomains.get(position).getTitle());
        holder.totalPrice.setText("На сумму " + myOrdersDomains.get(position).getTotalPrice() + " ₽");
    }

    @Override
    public int getItemCount() {
        return myOrdersDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, totalPrice;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.myOrdTitle);
            totalPrice = itemView.findViewById(R.id.myOrdTotalPrice);
        }
    }
}

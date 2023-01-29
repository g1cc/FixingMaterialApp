package com.example.fixingmaterial.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixingmaterial.Domain.StockDomain;
import com.example.fixingmaterial.Helper.ManagmentCart;
import com.example.fixingmaterial.R;
import com.example.fixingmaterial.iface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class cartAdaptor extends RecyclerView.Adapter<cartAdaptor.ViewHolder> {
    ArrayList<StockDomain> pizzaDomains;

    private ManagmentCart managmentCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public int qtyPos = 1;

    public cartAdaptor(ArrayList<StockDomain> pizzaDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener)
    {

        this.pizzaDomains = pizzaDomains;
        this.managmentCart = new ManagmentCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public cartAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(pizzaDomains.get(position).getTitle());
        holder.price.setText(String.valueOf(pizzaDomains.get(position).getPrice()));
        holder.qtyText.setText(String.valueOf(pizzaDomains.get(position).getCartPosition()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(pizzaDomains.get(position).getImage(), "drawable", holder.itemView.getContext().getPackageName());

        holder.pizzaImage.setImageResource(drawableResourceId);

        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    managmentCart.plusNumberFood(pizzaDomains, position, new ChangeNumberItemsListener() {
                        @Override
                        public void changed() {
                            notifyDataSetChanged();
                            changeNumberItemsListener.changed();
                        }
                    });
                }
        });

        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                managmentCart.minusNumberFood(pizzaDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return pizzaDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, price, plusButton, minusButton, qtyText;
        ImageView pizzaImage;
        ConstraintLayout constraintLayout;
        int qty = 1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.vhcTitle);
            price = itemView.findViewById(R.id.vhcPrice);
            pizzaImage = itemView.findViewById(R.id.vhcImage);
            constraintLayout = itemView.findViewById(R.id.cartConstLay);
            plusButton = itemView.findViewById(R.id.plusButton);
            minusButton = itemView.findViewById(R.id.minusButton);
            qtyText = itemView.findViewById(R.id.qtyText);
        }
    }
}
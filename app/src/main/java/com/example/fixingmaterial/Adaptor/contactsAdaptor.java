package com.example.fixingmaterial.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixingmaterial.Domain.AddressDomain;
import com.example.fixingmaterial.R;

import java.util.ArrayList;

public class contactsAdaptor extends RecyclerView.Adapter<contactsAdaptor.ViewHolder> {
    ArrayList<AddressDomain> addressDomains;

    public contactsAdaptor(ArrayList<AddressDomain> addressDomains)
    {
        this.addressDomains = addressDomains;
    }

    @NonNull
    @Override
    public contactsAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_addresses, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull contactsAdaptor.ViewHolder holder, int position) {

        holder.adTitle.setText(addressDomains.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return addressDomains.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView adTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            adTitle = itemView.findViewById(R.id.adTitle);
        }
    }
}

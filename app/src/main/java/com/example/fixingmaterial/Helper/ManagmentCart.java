package com.example.fixingmaterial.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.fixingmaterial.Domain.StockDomain;
import com.example.fixingmaterial.iface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagmentCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagmentCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(StockDomain item) {
        ArrayList<StockDomain> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if (listFood.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            listFood.get(n).setCartPosition(item.getCartPosition());
        } else {
            listFood.add(item);
        }
        tinyDB.putListObject("fragment_Cart", listFood);
        Toast.makeText(context, "Добавлено в корзину", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<StockDomain> getListCart() {
        return tinyDB.getListObject("fragment_Cart");
    }

    public void plusNumberFood(ArrayList<StockDomain> listFood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listFood.get(position).setCartPosition(listFood.get(position).getCartPosition() + 1);
        tinyDB.putListObject("fragment_Cart", listFood);
        changeNumberItemsListener.changed();
    }

    public void minusNumberFood(ArrayList<StockDomain> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (listfood.get(position).getCartPosition() == 1) {
            listfood.remove(position);
        } else {
            listfood.get(position).setCartPosition(listfood.get(position).getCartPosition() - 1);
        }
        tinyDB.putListObject("fragment_Cart", listfood);
        changeNumberItemsListener.changed();
    }

    public int getTotalFee() {
        ArrayList<StockDomain> listfood = getListCart();
        int price = 0;
        for (int i = 0; i < listfood.size(); i++) {
            price = price + (listfood.get(i).getPrice() * listfood.get(i).getCartPosition());
        }
        return price;
    }
}
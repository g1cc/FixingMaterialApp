package com.example.fixingmaterial;

import androidx.lifecycle.ViewModelProvider;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fixingmaterial.Adaptor.myOrdersAdaptor;
import com.example.fixingmaterial.Domain.MyOrdersDomain;
import com.example.fixingmaterial.databinding.MyOrdersFragmentBinding;

import java.io.IOException;
import java.util.ArrayList;

public class myOrders extends Fragment {

    private MyOrdersViewModel mViewModel;
    MyOrdersFragmentBinding binding;
    RecyclerView myOrdRecView;
    RecyclerView.Adapter adapter;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor;
    String query;

    int titleID, dateID, totalPriceID, deliveryPriceID, orderPriceID;
    String title, date, totalPrice, deliveryPrice, orderPrice;

    public static myOrders newInstance() {
        return new myOrders();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MyOrdersFragmentBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();

        databaseHelper = new DatabaseHelper(getContext());
        try {
            databaseHelper.updateDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        db = databaseHelper.open();

        setMyOrdRecView();


        return view;
    }

    private void setMyOrdRecView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        myOrdRecView = binding.myOrdRecView;
        myOrdRecView.setLayoutManager(linearLayoutManager);

        query = "select id as 'Код', sumcost as 'Итого', deliv_costt as 'Стоимость доставки', order_cost as 'Стоимость заказа'\n" +
                "from orders\n" +
                "where id_clients = " + SignActivity.id;

        cursor = db.rawQuery(query, null);

        ArrayList<MyOrdersDomain> myOrdersDomains = new ArrayList<>();

        while (cursor.moveToNext())
        {
            titleID = cursor.getColumnIndex("Код");
            totalPriceID = cursor.getColumnIndex("Итого");
            deliveryPriceID = cursor.getColumnIndex("Стоимость доставки");
            orderPriceID = cursor.getColumnIndex("Стоимость заказа");
//            dateID = cursor.getColumnIndex("Дата заказа");

            title = cursor.getString(titleID);
            totalPrice = cursor.getString(totalPriceID);
            deliveryPrice = cursor.getString(deliveryPriceID);
            orderPrice = cursor.getString(orderPriceID);
//            date = cursor.getString(dateID);

            myOrdersDomains.add(new MyOrdersDomain(title, "date", Integer.parseInt(totalPrice), Integer.parseInt(deliveryPrice), Integer.parseInt(orderPrice)));
        }
        adapter = new myOrdersAdaptor(myOrdersDomains);
        myOrdRecView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MyOrdersViewModel.class);
        // TODO: Use the ViewModel
    }

}
package com.example.fixingmaterial;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.fixingmaterial.Adaptor.cartAdaptor;
import com.example.fixingmaterial.Domain.StockDomain;
import com.example.fixingmaterial.Helper.ManagmentCart;

import com.example.fixingmaterial.databinding.FragmentCartFragmentBinding;
import com.example.fixingmaterial.iface.ChangeNumberItemsListener;

import java.io.IOException;
import java.util.ArrayList;

public class fragment_Cart extends Fragment {

    private FragmentCartViewModel mViewModel;

    @SuppressLint("StaticFieldLeak")
    public static FragmentCartFragmentBinding binding;

    private RecyclerView.Adapter adapter;
    private RecyclerView cartRecyclerView;

    ScrollView scrollView;
    TextView summ, delivery, total, emptyCart, summTitle, deliveryTitle, totalTitle, cartTitle;
    Button cartButton;
    ManagmentCart managmentCart;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    int summPrice = 0;

    AboutfoodActivity aboutfoodActivity;

    public static int qtyItems = 0;

    String dialogGetText;

    public static fragment_Cart newInstance() {
        return new fragment_Cart();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentCartFragmentBinding.inflate(getLayoutInflater());

        managmentCart = new ManagmentCart(getContext());

        View view = binding.getRoot();

        aboutfoodActivity = new AboutfoodActivity();

        scrollView = binding.scrollView2;

        summ = binding.summText;
        delivery = binding.deliveryText;
        total = binding.totalText;
        cartButton = binding.cartOrderButton;
        emptyCart = binding.emptyCartText;
        cartRecyclerView = binding.cartRecView;
        summTitle = binding.summTitle;
        deliveryTitle = binding.deliveryTitle;
        totalTitle = binding.totalTitle;

        databaseHelper = new DatabaseHelper(getContext());
        try {
            databaseHelper.updateDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        db = databaseHelper.open();

        int deliveryPrice = 0;

        for (int i = 0; i < aboutfoodActivity.abFoodDomain.size(); i++)
        {
            summPrice = summPrice + aboutfoodActivity.abFoodDomain.get(i).getPrice();
        }

        delivery.setText(deliveryPrice + "₽");

        initList();
        calculateCart();

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                ArrayList<StockDomain> stockDomains = managmentCart.getListCart();

                for (int i = 0; i < managmentCart.getListCart().size(); i++)
                {
                    stockDomains.add(new StockDomain(managmentCart.getListCart().get(i).getTitle(), managmentCart.getListCart().get(i).getImage(), managmentCart.getListCart().get(i).getDescription(), managmentCart.getListCart().get(i).getBrand(), managmentCart.getListCart().get(i).getPrice()));
                }

                showForgotDialog(getContext());

                String cvSumOrder = String.valueOf(managmentCart.getTotalFee());
                String cvDelivery = "150";
                String cvTotal = String.valueOf(Math.round(managmentCart.getTotalFee() + 0));
                String cvStatus = "Готовится";
                String cvDateOrder = "2020-12-12";
                String cvDateComplete = "2020-12-12";
                String cvClientID = SignActivity.id;
                String cvStreet = "Брусницына";
                String cvHouse = "30";
                String cvFlat = "NULL";

                cv.put("id_clients", SignActivity.id);
                cv.put("pay_type", "Наличный");
                cv.put("sumcost", managmentCart.getTotalFee() + 0);
                cv.put("deliv_costt", 150);
                cv.put("order_cost", managmentCart.getTotalFee());
                cv.put("qty", 1);
                cv.put("status", "Успешно");

                db.insert("orders", null, cv);
            }
        });

        return view;
    }

    private void initList()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        cartRecyclerView.setLayoutManager(linearLayoutManager);

        adapter = new cartAdaptor(managmentCart.getListCart(), getContext(), new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCart();
            }
        });

        cartRecyclerView.setAdapter(adapter);

        if(managmentCart.getListCart().isEmpty())
        {
            emptyCart.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.INVISIBLE);
        }

        else
        {
            emptyCart.setVisibility(View.INVISIBLE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void showForgotDialog(Context c) {
        final EditText taskEditText = new EditText(c);
        final EditText editText2 = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Заказ оформлен успешно!")
                .setMessage("На данный момент доступен только самовывоз заказа. Ознакомьтесь со списком наших магазинов во вкладке 'Контакты'. Назовите номер заказа в магазине для того, чтобы получить заказ. Заказ оплачивается на месте.")
                .setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogGetText = String.valueOf(taskEditText.getText());
                    }
                })
                .create();
        dialog.show();
    }

    private void calculateCart()
    {
        int delivery = 0;
        int totalSum = Math.round(managmentCart.getTotalFee() + delivery);
        int itemTotalSum = Math.round(managmentCart.getTotalFee());

        total.setText(totalSum + "₽");
        summ.setText(managmentCart.getTotalFee() + "₽");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentCartViewModel.class);
        // TODO: Use the ViewModel
    }

}
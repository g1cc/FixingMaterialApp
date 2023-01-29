package com.example.fixingmaterial;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.fixingmaterial.Adaptor.StockAdaptor;
import com.example.fixingmaterial.Domain.StockDomain;
import com.example.fixingmaterial.databinding.FragmentHomeFragmentBinding;

import java.io.IOException;
import java.util.ArrayList;

public class fragment_Home extends Fragment
{
    private FragmentHomeViewModel mViewModel;
    private FragmentHomeFragmentBinding binding;
    private RecyclerView.Adapter adapter;
    private  RecyclerView nutsRecView, plasticNutsRecView, boltsRecView, screwsRecView, stScrewsRecView, intScrewRecView;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor;
    String query;

    String productName;
    String productPrice;
    String productImage;
    String productDescription;
    String productBrand;

    int productNameID;
    int productPriceID;
    int productImageID;
    int productDescriptionID;
    int productBrandID;

    public static fragment_Home newInstance() {
        return new fragment_Home();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = FragmentHomeFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        databaseHelper = new DatabaseHelper(getContext());
        try {
            databaseHelper.updateDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        db = databaseHelper.open();

        setNutsRecView();
        setPlasticNutsRecView();
        setBoltsRecView();
        setScrewsRecView();
        setSTScrewsRecView();
        setIntScrewRecView();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentHomeViewModel.class);
        // TODO: Use the ViewModel
    }

    private void setNutsRecView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        nutsRecView = binding.nutsRecView;
        nutsRecView.setLayoutManager(linearLayoutManager);

        query = "SELECT product.name as 'Название', product.brend as 'Бренд', product.description as 'Описание', product.price as 'Цена', product.image as 'Картинка'\n" +
                "from product inner join category on product.id_category = category.id\n" +
                "where category.name = 'Автомобильный крепеж'";

        cursor = db.rawQuery(query, null);

        ArrayList<StockDomain> stockDomains = new ArrayList<>();

        while (cursor.moveToNext())
        {
            listFill();

            stockDomains.add(new StockDomain(productName, productImage, productDescription, productBrand, Integer.parseInt(productPrice)));
        }

        adapter = new StockAdaptor(stockDomains);
        nutsRecView.setAdapter(adapter);
    }

    private void setPlasticNutsRecView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        plasticNutsRecView = binding.plasticNutsRecView;
        plasticNutsRecView.setLayoutManager(linearLayoutManager);

        query = "SELECT product.name as 'Название', product.brend as 'Бренд', product.description as 'Описание', product.price as 'Цена', product.image as 'Картинка'\n" +
                "from product inner join category on product.id_category = category.id\n" +
                "where category.name = 'Пластиковый крепеж'";

        cursor = db.rawQuery(query, null);

        ArrayList<StockDomain> stockDomains = new ArrayList<>();

        while (cursor.moveToNext())
        {
            listFill();

            stockDomains.add(new StockDomain(productName, productImage, productDescription, productBrand, Integer.parseInt(productPrice)));
        }

        adapter = new StockAdaptor(stockDomains);
        plasticNutsRecView.setAdapter(adapter);
    }

    private void setBoltsRecView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        boltsRecView = binding.boltsRecView;
        boltsRecView.setLayoutManager(linearLayoutManager);

        query = "SELECT product.name as 'Название', product.brend as 'Бренд', product.description as 'Описание', product.price as 'Цена', product.image as 'Картинка'\n" +
                "from product inner join category on product.id_category = category.id\n" +
                "where category.name = 'Болты'";

        cursor = db.rawQuery(query, null);

        ArrayList<StockDomain> stockDomains = new ArrayList<>();

        while (cursor.moveToNext())
        {
            listFill();

            stockDomains.add(new StockDomain(productName, productImage, productDescription, productBrand, Integer.parseInt(productPrice)));
        }

        adapter = new StockAdaptor(stockDomains);
        boltsRecView.setAdapter(adapter);
    }

    private void setScrewsRecView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        screwsRecView = binding.screwsRecView;
        screwsRecView.setLayoutManager(linearLayoutManager);

        query = "SELECT product.name as 'Название', product.brend as 'Бренд', product.description as 'Описание', product.price as 'Цена', product.image as 'Картинка'\n" +
                "from product inner join category on product.id_category = category.id\n" +
                "where category.name = 'Винты'";

        cursor = db.rawQuery(query, null);

        ArrayList<StockDomain> stockDomains = new ArrayList<>();

        while (cursor.moveToNext())
        {
            listFill();

            stockDomains.add(new StockDomain(productName, productImage, productDescription, productBrand, Integer.parseInt(productPrice)));
        }

        adapter = new StockAdaptor(stockDomains);
        screwsRecView.setAdapter(adapter);
    }

    private void setSTScrewsRecView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        stScrewsRecView = binding.stScrewsRecView;
        stScrewsRecView.setLayoutManager(linearLayoutManager);

        query = "SELECT product.name as 'Название', product.brend as 'Бренд', product.description as 'Описание', product.price as 'Цена', product.image as 'Картинка'\n" +
                "from product inner join category on product.id_category = category.id\n" +
                "where category.name = 'Саморезы, шурупы'";

        cursor = db.rawQuery(query, null);

        ArrayList<StockDomain> stockDomains = new ArrayList<>();

        while (cursor.moveToNext())
        {
            listFill();

            stockDomains.add(new StockDomain(productName, productImage, productDescription, productBrand, Integer.parseInt(productPrice)));
        }

        adapter = new StockAdaptor(stockDomains);
        stScrewsRecView.setAdapter(adapter);
    }

    private void setIntScrewRecView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        intScrewRecView = binding.intScrewRecView;
        intScrewRecView.setLayoutManager(linearLayoutManager);

        query = "SELECT product.name as 'Название', product.brend as 'Бренд', product.description as 'Описание', product.price as 'Цена', product.image as 'Картинка'\n" +
                "from product inner join category on product.id_category = category.id\n" +
                "where category.name = 'Гайки'";

        cursor = db.rawQuery(query, null);

        ArrayList<StockDomain> stockDomains = new ArrayList<>();

        while (cursor.moveToNext())
        {
            listFill();
            stockDomains.add(new StockDomain(productName, productImage, productDescription, productBrand, Integer.parseInt(productPrice)));
        }

        adapter = new StockAdaptor(stockDomains);
        intScrewRecView.setAdapter(adapter);
    }


    public class NoScrollRecyclerView extends RecyclerView
    {
        public  NoScrollRecyclerView(Context context)
        {
            super(context);
        }

        public NoScrollRecyclerView(Context context, AttributeSet attributes)
        {
            super(context, attributes);
        }

        public  NoScrollRecyclerView(Context context, AttributeSet attributes, int style)
        {
            super(context, attributes, style);
        }

        public boolean dispatchTouchEvent(MotionEvent ev)
        {
            if(ev.getAction() == MotionEvent.ACTION_MOVE)
                return true;
            return  super.dispatchTouchEvent(ev);
        }
    }

    private void listFill()
    {
        productNameID = cursor.getColumnIndex("Название");
        productPriceID = cursor.getColumnIndex("Цена");
        productImageID = cursor.getColumnIndex("Картинка");
        productDescriptionID = cursor.getColumnIndex("Описание");
        productBrandID = cursor.getColumnIndex("Бренд");

        productName = cursor.getString(productNameID);
        productPrice = cursor.getString(productPriceID);
        productImage = cursor.getString(productImageID);
        productDescription = cursor.getString(productDescriptionID);
        productBrand = cursor.getString(productBrandID);
    }

}
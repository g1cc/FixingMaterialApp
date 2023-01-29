package com.example.fixingmaterial;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.net.Uri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.fixingmaterial.Adaptor.contactsAdaptor;
import com.example.fixingmaterial.Domain.AddressDomain;
import com.example.fixingmaterial.databinding.FragmentContactsFragmentBinding;

import java.io.IOException;
import java.util.ArrayList;

public class fragment_Contacts extends Fragment {

    private FragmentContactsViewModel mViewModel;

    FragmentContactsFragmentBinding binding;

    ImageButton ytButton, vkButton;
    RecyclerView contRecView;
    RecyclerView.Adapter adapter;
    Cursor cursor;
    SQLiteDatabase db;
    DatabaseHelper databaseHelper;

    String query, address;
    int addressID;

    public static fragment_Contacts newInstance() {
        return new fragment_Contacts();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentContactsFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();


        ytButton = binding.ytButton;
        vkButton = binding.vkButton;

        databaseHelper = new DatabaseHelper(getContext());
        try {
            databaseHelper.updateDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        db = databaseHelper.open();


        ytButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("https://www.youtube.com");
            }
        });

        vkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("https://vk.com");
            }
        });

        setContRecView();

        return view;

    }

    private void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    private void setContRecView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        contRecView = binding.contRecView;
        contRecView.setLayoutManager(linearLayoutManager);

        query = "select (\"ул. \" || street || \", д. \" || house) as 'Адрес'\n" +
                "from address";

        cursor = db.rawQuery(query, null);

        ArrayList<AddressDomain> addressDomains = new ArrayList<>();

        while (cursor.moveToNext())
        {
            addressID = cursor.getColumnIndex("Адрес");
            address = cursor.getString(addressID);
            addressDomains.add(new AddressDomain(address));
        }

        adapter = new contactsAdaptor(addressDomains);
        contRecView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentContactsViewModel.class);
        // TODO: Use the ViewModel
    }

}
package com.example.fixingmaterial;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fixingmaterial.databinding.FragmentRegistrationBinding;

import java.io.IOException;

public class fragment_registration extends Fragment {

    private FragmentRegistrationBinding binding;
    Button regButton;
    EditText edPhoneReg, edPasswordReg, edMailReg, edNameReg;
        DatabaseHelper databaseHelper;
        SQLiteDatabase db;
        String phone, password, name, mail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        regButton = binding.regButtonReg;
        edMailReg = binding.edMailReg;
        edPasswordReg = binding.edPasswordReg;
        edNameReg = binding.edNameReg;
        edPhoneReg = binding.edPhoneReg;

        edPhoneReg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {
                    if (edPhoneReg.length() == 0)
                        edPhoneReg.setText("+7");
                }
            }
        });

        databaseHelper = new DatabaseHelper(getContext());
        try {
            databaseHelper.updateDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        db = databaseHelper.open();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phone = edPhoneReg.getText().toString();
                password = edPasswordReg.getText().toString();
                name = edNameReg.getText().toString();
                mail = edMailReg.getText().toString();

                if (phone.length() == 12 && password.length() >= 6)
                {

                    ContentValues cv = new ContentValues();

                    cv.put("Имя", name);
                    cv.put("[Электронный адрес]", mail);
                    cv.put("Пароль", password);
                    cv.put("[Номер телефона]", phone);
                    cv.put("Фамилия", "NULL");
                    cv.put("[Дата рождения]", "NULL");

                    db.insert("Клиенты", null, cv);
                    db.close();

                    Intent intent = new Intent(getContext(), SignActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getContext(), "Минимальная длина пароля - 6 символов, номера телефона - 12 символов", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;

    }
}
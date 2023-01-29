package com.example.fixingmaterial;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.fixingmaterial.databinding.MyProfileFragmentBinding;

import java.io.IOException;

public class myProfile extends Fragment {

    private MyProfileViewModel mViewModel;
    MyProfileFragmentBinding binding;
    TextView myProfileName, myProfileMail, myProfilePhone, myProfilePassword;
    ImageButton nameButton, mailButton, phoneButton, passwordButton;
    String dialogTitle, dialogMessage, dialogGetText;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor;
    String query;

    public static myProfile newInstance() {
        return new myProfile();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MyProfileFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        init();
        setText();

        databaseHelper = new DatabaseHelper(getContext());
        try {
            databaseHelper.updateDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        db = databaseHelper.open();

        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTitle = "Смена имени";
                dialogMessage = "Введите новое имя";
                showForgotDialog(getContext());
            }
        });

        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTitle = "Смена электронного адреса";
                dialogMessage = "Введите новый электронный адрес";
                showForgotDialog(getContext());
            }
        });

        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTitle = "Смена номера телефона";
                dialogMessage = "Введите новый номер телефона";
                showForgotDialog(getContext());
            }
        });

        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTitle = "Смена пароля";
                dialogMessage = "Введите новый пароль";
                showForgotDialog(getContext());
            }
        });


        return view;
    }

    private void showForgotDialog(Context c) {
        final EditText taskEditText = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle(dialogTitle)
                .setMessage(dialogMessage)
                .setView(taskEditText)
                .setPositiveButton("Применить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogGetText = String.valueOf(taskEditText.getText());
                        ContentValues contentValues = new ContentValues();
                        switch (dialogTitle)
                        {
                            case "Смена имени":
                                SignActivity.name = dialogGetText;
                                contentValues.put("name", dialogGetText);
                                db.update("clients", contentValues, "id" + "=" + SignActivity.id, null);
                                break;
                            case "Смена электронного адреса":
                                SignActivity.mail = dialogGetText;
                                contentValues.put("e-mail", dialogGetText);
                                db.update("clients", contentValues, "id" + "=" + SignActivity.id, null);
                                break;
                            case "Смена номера телефона":
                                SignActivity.phone = dialogGetText;
                                contentValues.put("phone", dialogGetText);
                                db.update("clients", contentValues, "id" + "=" + SignActivity.id, null);
                                break;
                            case "Смена пароля":
                                SignActivity.passwordStr = dialogGetText;
                                contentValues.put("password", dialogGetText);
                                db.update("clients", contentValues, "id" + "=" + SignActivity.id, null);
                                break;
                        }
                        setText();
                    }
                })
                .setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        dialog.show();
    }

    private void init()
    {
        myProfileMail = binding.myProfileMail;
        myProfileName = binding.myProfileName;
        myProfilePhone = binding.myProfilePhone;
        myProfilePassword = binding.myProfilePassword;

        nameButton = binding.nameButton;
        mailButton = binding.mailButton;
        phoneButton = binding.phoneButton;
        passwordButton = binding.passwordButton;
    }

    private void setText()
    {
        myProfilePassword.setText(SignActivity.passwordStr);
        myProfileMail.setText(SignActivity.mail);
        myProfilePhone.setText(SignActivity.phone);
        myProfileName.setText(SignActivity.name);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MyProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}
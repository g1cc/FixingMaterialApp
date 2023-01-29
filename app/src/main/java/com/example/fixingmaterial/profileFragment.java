package com.example.fixingmaterial;

import static androidx.core.app.ActivityCompat.finishAffinity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fixingmaterial.databinding.ProfileFragmentBinding;

public class profileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    SignActivity signActivity;

    ProfileFragmentBinding binding;
    TextView profileTitle;
    Button myProfile, myOrders, myExit;

    public static int chooseButton;

    public static profileFragment newInstance() {
        return new profileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ProfileFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();


        signActivity = new SignActivity();

        profileTitle = binding.profileTitle;
        myProfile = binding.myProfileButton;
        myOrders = binding.myOrdersButton;
        myExit = binding.myExitButton;

        profileTitle.setText("Пользователь: " + SignActivity.name);

        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseButton = 1;
                Intent intent = new Intent(getContext(), profileActivity.class);
                startActivity(intent);
            }
        });

        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseButton = 2;
                Intent intent = new Intent(getContext(), profileActivity.class);
                startActivity(intent);
            }
        });

        myExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseButton = 3;
                Intent intent = new Intent(getContext(), SignActivity.class);
                startActivity(intent);
                finishAffinity(getActivity());
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}
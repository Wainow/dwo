package com.launcher.dwo.Create;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.launcher.dwo.R;

public class CreateAdditionalInfoFragment extends Fragment {

    public CreateAdditionalInfoFragment() {
    }
    public static CreateAdditionalInfoFragment newInstance() {
        CreateAdditionalInfoFragment fragment = new CreateAdditionalInfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info_additional_create, container, false);
    }
}
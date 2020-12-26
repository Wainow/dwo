package com.launcher.dwo.Create;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.launcher.dwo.R;

public class CreateClassFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Log.d("DebugLogs", "CreateClassFragment: OnCreateView");
        return inflater.inflate(R.layout.fragment_class_create, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void startActivityForResult(Intent data, int requestCode) {
        super.startActivityForResult(data, requestCode);
    }
}
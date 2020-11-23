package com.example.dwo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import static com.example.dwo.CreateDialog.photoUri;

public class First3Fragment extends Fragment {
    private GridView g;
    private DataAdapter mAdapter;
    private TextView t;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        Log.d("DebugLogs", "First3Fragment: OnCreateView");
        return inflater.inflate(R.layout.fragment_first3, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void startActivityForResult(Intent data, int requestCode) {
        if(requestCode == DataAdapter.REQUEST_CODE_GET_PHOTOS && data != null){
            photoUri = data.getData();
            Log.d("DebugLogs", "First3Fragment: photoUri:" + photoUri.toString());
        }
        super.startActivityForResult(data, requestCode);
    }
}
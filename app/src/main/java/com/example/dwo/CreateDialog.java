package com.example.dwo;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import org.w3c.dom.Text;

public class CreateDialog extends DialogFragment {
    private GridView g;
    private ProgressBar progressBar;
    private DataAdapter mAdapter;
    private Context context;
    private TextView textView;

    public CreateDialog(Context context){
        this.context = context;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.create_dialog, null);
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context, R.style.Dialog_PurpleAppTheme_NoActionBar);
        mDialogBuilder.setView(promptsView);

        textView = promptsView.findViewById(R.id.text_select);
        progressBar = promptsView.findViewById(R.id.progress_creating);
        progressBar.setProgress(25);
        ViewPager pager = promptsView.findViewById(R.id.pager_dialog);
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(this.context, pager);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(0);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    progressBar.setProgress(25);
                    textView.setText("Select class of hero");
                } else {
                    progressBar.setProgress(75);
                    textView.setText("Generate specifications");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        AlertDialog alertDialog = mDialogBuilder.create();
        alertDialog.show();
        alertDialog.getWindow().setLayout(1000, 1100);

        return alertDialog;
    }

    public void setProgress(int x){
        this.progressBar.setProgress(x);
    }
}

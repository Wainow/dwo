package com.example.dwo;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
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
    private int RoomID;
    private boolean isEvil;
    private MyFragmentPagerAdapter pagerAdapter;
    public static Uri photoUri;

    public CreateDialog(Context context, int RoomID, boolean isEvil){
        this.context = context;
        this.RoomID = RoomID;
        this.isEvil = isEvil;
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
        pagerAdapter = new MyFragmentPagerAdapter(this.context, pager, RoomID, isEvil);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(0);
        if(!isEvil) {
            pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 0) {
                        progressBar.setProgress(25);
                        textView.setText("Select class");
                    } else if (position == 1) {
                        progressBar.setProgress(50);
                        textView.setText("Generate specifications");
                    } else {
                        progressBar.setProgress(75);
                        textView.setText("Tell about new Hero");
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else{

        }
        AlertDialog alertDialog = mDialogBuilder.create();
        alertDialog.show();
        alertDialog.getWindow().clearFlags( WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        alertDialog.getWindow().setLayout(1000, 1100);

        return alertDialog;
    }

    public void setProgress(int x){
        this.progressBar.setProgress(x);
    }

    @Override
    public void startActivityForResult(Intent data, int requestCode) {
        if(requestCode == DataAdapter.REQUEST_CODE_GET_PHOTOS && data != null){
            photoUri = data.getData();
            Log.d("DebugLogs", "CreateDialog: photoUri:" + photoUri.toString());
            pagerAdapter.setRoleImage();
        }
        super.startActivityForResult(data, requestCode);
    }
}

package com.example.eplanettask.FireBaseNotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;

import com.example.eplanettask.FireBaseNotification.Fragments.ReciveNotificationFrag;
import com.example.eplanettask.FireBaseNotification.Fragments.SendNotificationFrag;
import com.example.eplanettask.R;
import com.example.eplanettask.databinding.ActivitySendNotifiBinding;
import com.google.firebase.messaging.FirebaseMessaging;

public class SendNotifiActi extends AppCompatActivity {

    ActivitySendNotifiBinding binding;
    Context mContex;
    private Fragment fragment = new SendNotificationFrag();
    int id =0;
    private boolean onFrom ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_send_notifi);
        mContex = SendNotifiActi.this;
        FirebaseMessaging.getInstance().subscribeToTopic("all");
        if(getIntent() != null){
            onFrom = getIntent().getBooleanExtra("onNotifcation",false);
            if(onFrom){
                fragment = new ReciveNotificationFrag();

            }

        }
        loadHomeFragment(true, true);

    }

    private void loadHomeFragment(boolean addToBack, boolean clearBackStack) {
        if (fragment != null) {

            replaceFrg(this, fragment, addToBack, clearBackStack, id);
        }
    }


    private void replaceFrg(FragmentActivity ctx, Fragment frag, boolean addToBackStack, boolean isClearBackByOne,
                                  int resId) {
        FragmentManager fm = ctx.getSupportFragmentManager();
        int addedFrgCount = fm.getBackStackEntryCount();

        if (isClearBackByOne && addedFrgCount > 0){
            for (int i=1; i <= addedFrgCount; i++)
                fm.popBackStack();
        }

        FragmentTransaction ft = fm
                .beginTransaction();
        if (resId == id) {
            resId = R.id.fmContainer;
        }
        ft.replace(resId, frag, frag.getClass().getName());
        if (addToBackStack)
            ft.addToBackStack(frag.getClass().getName());
        ft.commitAllowingStateLoss();
    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() ==1) {
            finish();
        }else{
            super.onBackPressed();
        }
    }

}
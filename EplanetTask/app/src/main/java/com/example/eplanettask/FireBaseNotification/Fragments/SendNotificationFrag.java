package com.example.eplanettask.FireBaseNotification.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eplanettask.FireBaseNotification.FcmNotificationsSender;
import com.example.eplanettask.R;
import com.example.eplanettask.databinding.FragmentSendNotificationBinding;

public class SendNotificationFrag extends Fragment {
    private Context mContext;

    private View view;
    private FragmentSendNotificationBinding binding;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_send_notification, container, false);
        view = binding.getRoot();

binding.btnSendNotfication.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        onSendNotificaion();
    }
});
        return view;
    }

    public void onSendNotificaion() {

        FcmNotificationsSender fcmNotificationsSender = new FcmNotificationsSender("/topics/all", binding.title.getText().toString(), binding.etDetails.getText().toString(),mContext,getActivity());
        fcmNotificationsSender.SendNotifications();
    }

}
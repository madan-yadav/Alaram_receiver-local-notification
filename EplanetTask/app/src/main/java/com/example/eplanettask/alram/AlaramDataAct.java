package com.example.eplanettask.alram;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eplanettask.databinding.ActivityAlaramDataBinding;
import com.example.eplanettask.R;

import java.util.Calendar;
import java.util.List;

public class AlaramDataAct extends AppCompatActivity {
    ActivityAlaramDataBinding binding;
    SaveAlaramAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_alaram_data);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.floatingAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlaramDataAct.this, AddAlaramActivity.class);
                startActivity(intent);
            }
        });
        getTasks();
    }

    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<AlaramBean>> {
            @Override
            protected List<AlaramBean> doInBackground(Void... voids) {
                List<AlaramBean> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<AlaramBean> tasks) {
                super.onPostExecute(tasks);
                 adapter = new SaveAlaramAdapter(AlaramDataAct.this, tasks);
                binding.recyclerview.setAdapter(adapter);
            }
        }
        GetTasks gt = new GetTasks();
        gt.execute();
    }

    public void startAlarm(long mills) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mills);
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
}
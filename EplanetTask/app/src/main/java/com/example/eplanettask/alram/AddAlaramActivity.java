 package com.example.eplanettask.alram;

 import android.app.AlarmManager;
 import android.app.PendingIntent;
 import android.app.TimePickerDialog;
 import android.content.Context;
 import android.content.Intent;
 import android.os.AsyncTask;
 import android.os.Bundle;
 import android.util.Log;
 import android.view.View;
 import android.widget.TimePicker;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.databinding.DataBindingUtil;
 import androidx.fragment.app.DialogFragment;

 import com.example.eplanettask.R;
 import com.example.eplanettask.databinding.ActivityAddAlaramBinding;

 import java.text.DateFormat;
 import java.util.Calendar;

 public class AddAlaramActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
     Calendar c = Calendar.getInstance();
     ActivityAddAlaramBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_alaram);

        binding.buttonTimepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }

    private void saveTask() {

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                AlaramBean task = new AlaramBean();
                task.setDesc(binding.editTextDesc.getText().toString());
                task.setTimeString(binding.textAlaramText.getText().toString());
                task.setTimeMills(c.getTimeInMillis());
                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .insert(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), AlaramDataAct.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        updateTimeText(c);
//        startAlarm(c);
        Log.d("@@start", c+"oo");
    }
    private void updateTimeText(Calendar c) {
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        binding.textAlaramText.setText(timeText);
    }
//    private void startAlarm(Calendar c) {
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, AlertReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
//        if (c.before(Calendar.getInstance())) {
//            c.add(Calendar.DATE, 1);
//        }
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
//    }
    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        alarmManager.cancel(pendingIntent);
        binding.textAlaramText.setText("Alarm canceled");
    }
}
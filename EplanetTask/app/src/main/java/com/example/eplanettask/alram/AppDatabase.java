package com.example.eplanettask.alram;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {AlaramBean.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}

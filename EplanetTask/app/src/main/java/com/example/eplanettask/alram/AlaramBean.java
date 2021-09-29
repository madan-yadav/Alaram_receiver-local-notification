package com.example.eplanettask.alram;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class AlaramBean implements Serializable {

        @PrimaryKey(autoGenerate = true)
        private int id;

        @ColumnInfo(name = "desc")
        private String desc;

        @ColumnInfo(name = "time_mills")
        private long timeMills;

        @ColumnInfo(name = "timeString")
        private String timeString;

    public long getTimeMills() {
        return timeMills;
    }

    public void setTimeMills(long timeMills) {
        this.timeMills = timeMills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }
}

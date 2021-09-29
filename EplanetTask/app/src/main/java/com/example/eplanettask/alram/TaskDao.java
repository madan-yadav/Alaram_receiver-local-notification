package com.example.eplanettask.alram;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {


    @Query("SELECT * FROM AlaramBean")
    List<AlaramBean> getAll();

    @Insert
    void insert(AlaramBean task);

    @Delete
    void delete(AlaramBean task);

    @Update
    void update(AlaramBean task);

}

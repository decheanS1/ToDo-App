package com.example.todo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// how to handle the data - crud operations are defined here
@Dao
public interface Note_Dao {

  @Query("SELECT * FROM my_notes ORDER BY priority")
    public LiveData<List<Note>> getAllData();

    @Insert
    public void insert(Note note);

    @Update
    public void update(Note note);

    @Delete
    public void delete(Note note);






}

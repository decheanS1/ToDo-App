package com.example.todo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class Note_ViewModel  extends AndroidViewModel {
    private Note_Repo note_repo;
    private LiveData<List<Note>> notelist;


    public Note_ViewModel(@NonNull Application application) {
        super(application);
        note_repo = new Note_Repo(application);
        notelist = note_repo.getAllData();
    }

    // call all the methods
    public void insert(Note note)
    {
        note_repo.insertData(note);
    }
    public void delete(Note note)
    {
        note_repo.deleteData(note);
    }
    public void update(Note note)
    {
        note_repo.updateData(note);
    }

    public LiveData<List<Note>> getAllNotes(){
        return notelist;
    }


}

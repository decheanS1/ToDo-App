package com.example.todo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import java.util.List;

public class Note_Repo {

    private Note_Dao note_dao;
    private LiveData<List<Note>> note_list;

    public Note_Repo(Application application)
    {
        Note_Database note_database = Note_Database.getInstance(application);
        // binding the data
        note_dao = note_database.note_dao();
        note_list = note_dao.getAllData();
    }

    public void insertData(Note note)
    {
        new InsertTask(note_dao).execute(note);
    }

    public void updateData(Note note)
    {
        new UpdateTask(note_dao).execute(note);
    }

    public void deleteData(Note note)
    {
        new DeleteTask(note_dao).execute(note);
    }

    public LiveData<List<Note>> getAllData()
    {
        return note_list;
    }


    // for inserting task
    private static class InsertTask extends AsyncTask<Note, Void, Void>{

        private Note_Dao note_dao;

        public InsertTask(Note_Dao note_dao) {
            this.note_dao = note_dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            note_dao.insert(notes[0]);
            return null;
        }
    }

    // for update task
    private static class UpdateTask extends AsyncTask<Note, Void, Void>{

        private Note_Dao note_dao;

        public UpdateTask(Note_Dao note_dao) {
            this.note_dao = note_dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            note_dao.update(notes[0]);
            return null;
        }
    }
    // for delete task
    private static class DeleteTask extends AsyncTask<Note, Void, Void>{

        private Note_Dao note_dao;

        public DeleteTask(Note_Dao note_dao) {
            this.note_dao = note_dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            note_dao.delete(notes[0]);
            return null;
        }
    }


}

package com.example.todo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class, version = 1)
public abstract class Note_Database extends RoomDatabase {

    private static Note_Database note_instance;
    public static synchronized Note_Database getInstance(Context context)
    {
        if (note_instance == null)
        {
            note_instance = Room.databaseBuilder(context.getApplicationContext(),
                    Note_Database.class, "note_db").fallbackToDestructiveMigration().build();
        }
        return note_instance;

    }

}

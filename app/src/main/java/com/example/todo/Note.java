package com.example.todo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_notes")
public class Note {

    private String title;
    private String description;
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

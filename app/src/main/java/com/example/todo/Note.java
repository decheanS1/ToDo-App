package com.example.todo;

public class Note {

    private String title;
    private String description;
    @PrimaryKey(autoGenerate = true)
    private int id;
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

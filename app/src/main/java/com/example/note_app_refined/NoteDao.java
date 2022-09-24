package com.example.note_app_refined;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("Select * from notesTable")
    List<Note> getAll();

    @Insert
    void addNote(Note note);

    @Delete
    void delNote(Note note);

}

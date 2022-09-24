package com.example.note_app_refined;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class,exportSchema = false,version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static final String DB_NAME = "notesTable";
    private static NoteDatabase instance;

    public static synchronized NoteDatabase getInstance(Context context){

        if(instance==null){
            instance = Room.databaseBuilder(context,NoteDatabase.class,DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract NoteDao noteDao();

}

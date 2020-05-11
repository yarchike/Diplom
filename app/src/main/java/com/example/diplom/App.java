package com.example.diplom;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

public class App extends Application {
    private static NoteRepository notesRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
        notesRepository = new DbNotesRepository(writableDatabase);
    }

    public static NoteRepository getNotesRepository() {
        return notesRepository;
    }
}
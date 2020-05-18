package com.example.diplom;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class DbNotesRepository implements NoteRepository {
    private static final String LOG_TAG = DbNotesRepository.class.getName();
    private static final String NOTES_TABLE_NAME = "mytable";
    private SQLiteDatabase database;

    public DbNotesRepository(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public List<Note> getNotes() {
        try (Cursor c = database.query(NOTES_TABLE_NAME, null, null, null, null, null, null)) {
            if (c.moveToFirst()) {
                List<Note> result = new ArrayList<>();
                int id = c.getColumnIndex("id");
                int headingColIndex = c.getColumnIndex("heading");
                int bodyColIndex = c.getColumnIndex("body");
                int dateColIndex = c.getColumnIndex("date");
                int idColIndex = c.getColumnIndex("idList");
                do {
                    String heading = c.getString(headingColIndex);
                    String body = c.getString(bodyColIndex);
                    Date date = DateUtil.StringToDate(c.getString(dateColIndex));
                    int idList = c.getInt(idColIndex);
                    result.add(new Note(heading, body, date, idList));
                } while (c.moveToNext());
                Log.d("list", result.toString());
                Collections.sort(result);
                Log.d("list", result.toString());
                return result;
            } else {
                Log.d(LOG_TAG, "0 rows");
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void setNotes(Note note) {
        ContentValues cv = new ContentValues();
        cv.put("heading", note.getHeading());
        cv.put("body", note.getBody());
        cv.put("date", DateUtil.DateToString(note.getDate()));
        cv.put("idList", ListActivity.simpleAdapterContent.size());
        long rowID = database.insert(NOTES_TABLE_NAME, null, cv);
        Log.d(LOG_TAG, "row inserted, ID = " + rowID);
    }

    @Override
    public void removeNotes(int position) {
        int delCount = database.delete(NOTES_TABLE_NAME, "idList = " + App.getNotesRepository().getNotes().get(position).getId(), null);
    }

}
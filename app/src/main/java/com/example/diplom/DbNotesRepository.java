package com.example.diplom;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.diplom.DBHelper.LOG_TAG;

public class DbNotesRepository implements NoteRepository {
    private static final String LOG_TAG = DbNotesRepository.class.getName();
    private static final String NOTES_TABLE_NAME = "mytable";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy  HH:mm", Locale.US);
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
                Log.d(LOG_TAG, "id= " + id);
                int headingColIndex = c.getColumnIndex("heading");
                Log.d(LOG_TAG, "id2= " + headingColIndex);
                int bodyColIndex = c.getColumnIndex("body");
                Log.d(LOG_TAG, "id3= " + bodyColIndex);
                int dateColIndex = c.getColumnIndex("date");
                do {
                    String heading = c.getString(headingColIndex);
                    String body = c.getString(bodyColIndex);
                    Date date;
                    try {
                        date = dateFormat.parse(c.getString(dateColIndex));
                    } catch (ParseException e) {
                        date = null;
                    }
                    result.add(new Note(heading, body, date));
                } while (c.moveToNext());
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
        String dateFormatted = null;
        if (note.getDate() != null) {
            dateFormatted = dateFormat.format(note.getDate());
        }
        cv.put("date", dateFormatted);
        cv.put("idList", ListActivity.simpleAdapterContent.size());
        long rowID = database.insert("mytable", null, cv);
        Log.d(LOG_TAG, "row inserted, ID = " + rowID);
    }
}
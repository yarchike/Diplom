package com.example.diplom;

import java.util.List;

public interface NoteRepository {
    List<Note> getNotes();
    void setNotes(Note note);
}

package com.example.diplom;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.Objects;

public class Note implements Comparable<Note> {
    private String heading;
    private String body;
    private Date date;
    private int id;

    public Note(String heading, String body, Date date, int id) {
        this.heading = heading;
        this.body = body;
        this.date = date;
        this.id = id;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public int getId() {
        return id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeading() {

        return heading;
    }

    public String getBody() {
        return body;
    }

    public Date getDate() {

        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        if (!Objects.equals(heading, note.heading)) return false;
        if (!Objects.equals(body, note.body)) return false;
        return Objects.equals(date, note.date);
    }

    @Override
    public int hashCode() {
        int result = heading != null ? heading.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }


    @Override
    public int compareTo(Note o) {
        if (this.getDate() == null && o.getDate() == null) {
            return -1;
        } else if (this.getDate() != null && o.getDate() == null) {
            return -1;
        } else if (this.getDate() == null && o.getDate() != null) {
            return 1;
        }
        return this.getDate().compareTo(o.getDate());
    }

    @NonNull
    @Override
    public String toString() {
        return "Дата " + DateUtil.DateToString(date);
    }
}
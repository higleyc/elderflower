package com.higley.elderflower.models;

public class Note {
    private static final int TITLE_MAX_LEN = 50;

    public String title;
    public String text;

    public Note() { }

    public Note(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public static Note createNoteFromSimpleEntry(String input) {
        Note note = new Note();
        note.text = input;

        // For now, title is just truncated text
        if (input.length() > TITLE_MAX_LEN) {
            note.title = input.substring(0, TITLE_MAX_LEN - 3) + "...";
        } else {
            note.title = input;
        }

        return note;
    }
}

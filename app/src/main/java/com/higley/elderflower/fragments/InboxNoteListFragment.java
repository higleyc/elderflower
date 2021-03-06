package com.higley.elderflower.fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.higley.elderflower.utils.Authentication;

public class InboxNoteListFragment extends NoteListFragment {
    public InboxNoteListFragment() { }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        return databaseReference.child("notes")
                .child(Authentication.getCurrentUid())
                .child("inbox");
    }
}

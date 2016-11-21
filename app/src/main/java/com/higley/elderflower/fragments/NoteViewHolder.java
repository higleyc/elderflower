package com.higley.elderflower.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.higley.elderflower.R;
import com.higley.elderflower.models.Note;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    public TextView titlView;

    public NoteViewHolder(View view) {
        super(view);

        titlView = (TextView)view.findViewById(R.id.note_item_title);
    }

    //TODO will also want to pass in an onclicklistener
    public void bindToNote(Note note) {
        titlView.setText(note.title);
    }
}

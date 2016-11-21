package com.higley.elderflower.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.higley.elderflower.R;
import com.higley.elderflower.models.Note;

public abstract class NoteListFragment extends Fragment {

    private DatabaseReference database;
    private LinearLayoutManager layoutManager;
    private FirebaseRecyclerAdapter<Note, NoteViewHolder> adapter;

    private RecyclerView noteList;

    public NoteListFragment() { }

    public abstract Query getQuery(DatabaseReference databaseReference);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_note_list, container, false);

        database = FirebaseDatabase.getInstance().getReference();

        noteList = (RecyclerView)rootView.findViewById(R.id.note_list);
        noteList.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstance) {
        super.onActivityCreated(savedInstance);

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        noteList.setLayoutManager(layoutManager);

        Query query = getQuery(database);
        adapter = new FirebaseRecyclerAdapter<Note, NoteViewHolder>(
                Note.class, R.layout.note_item, NoteViewHolder.class, query) {
            @Override
            protected void populateViewHolder(NoteViewHolder viewHolder, Note model, int position) {
                //TODO handle clicks
                viewHolder.bindToNote(model);
            }
        };
        noteList.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.cleanup();
        }
    }
}

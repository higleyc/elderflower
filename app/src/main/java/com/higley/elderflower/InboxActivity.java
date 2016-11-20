package com.higley.elderflower;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.higley.elderflower.models.Note;
import com.higley.elderflower.utils.Authentication;

import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;

public class InboxActivity extends AppCompatActivity {
    private EditText entry;

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        database = FirebaseDatabase.getInstance().getReference();

        //TODO should this check also be done each time saving?
        Authentication.ensureAuthenticated(this);

        //Views
        entry = (EditText)findViewById(R.id.entryEditText);

        // Listeners
        entry.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    addNote();
                    return true;
                }
                return false;
            }
        });
    }

    private void addNote() {
        // Make the note
        String text = entry.getText().toString();
        Note note = Note.createNoteFromSimpleEntry(text);

        // Store the note and make toast
        String uid = Authentication.getCurrentUid();
        String noteKey = database.child("notes").child(uid).push().getKey();
        database.child("notes").child(uid).child(noteKey).setValue(note);
        Toast.makeText(getApplicationContext(), "Note added", Toast.LENGTH_SHORT).show();

        // Reset UI
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        entry.setText("");
    }
}

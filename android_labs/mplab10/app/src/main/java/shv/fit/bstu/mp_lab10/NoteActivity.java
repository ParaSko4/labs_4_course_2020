package shv.fit.bstu.mp_lab10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import shv.fit.bstu.mp_lab10.models.Note;

public class NoteActivity extends AppCompatActivity {
    private static final String extra_note = "note";

    private Note note;
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        editText = findViewById(R.id.editTextNote);

        if (getIntent().hasExtra(extra_note)) {
            note = getIntent().getParcelableExtra(extra_note);
            editText.setText(note.text);
        } else {
            note = new Note();
        }
    }

    public static void start(Activity caller, Note note){
        Intent intent = new Intent(caller, NoteActivity.class);
        if (note != null){
            intent.putExtra(extra_note, note);
        }
        caller.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                break;
            case R.id.save:
                if (editText.getText().length() > 0) {
                    note.text = editText.getText().toString();
                    note.done = false;

                    if (getIntent().hasExtra(extra_note)) {
                        App.getInstance().getNoteDao().update(note);
                    } else {
                        App.getInstance().getNoteDao().insert(note);
                    }
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

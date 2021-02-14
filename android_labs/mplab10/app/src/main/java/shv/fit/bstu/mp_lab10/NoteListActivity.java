package shv.fit.bstu.mp_lab10;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.List;

import shv.fit.bstu.mp_lab10.adapters.NoteListAdapter;
import shv.fit.bstu.mp_lab10.models.Note;

public class NoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView noteListView = findViewById(R.id.noteList);

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        noteListView.setLayoutManager(manager);
        noteListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        NoteListAdapter adapter = new NoteListAdapter();
        noteListView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoteActivity.start(NoteListActivity.this, null);
            }
        });

        NoteListViewModel noteListViewModel = ViewModelProviders.of(this).get(NoteListViewModel.class);
        noteListViewModel.getNoteLiveData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setItems(notes);
            }
        });
    }
}
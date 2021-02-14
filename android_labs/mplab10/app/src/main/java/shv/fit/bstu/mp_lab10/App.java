package shv.fit.bstu.mp_lab10;

import android.app.Application;

import androidx.room.Room;

import shv.fit.bstu.mp_lab10.data.NoteDao;
import shv.fit.bstu.mp_lab10.data.RoomDatabase;

public class App extends Application {
    private static App instance;

    private RoomDatabase db;
    private NoteDao noteDao;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        db = Room.databaseBuilder(getApplicationContext(), RoomDatabase.class, "todo-db")
                .allowMainThreadQueries()
                .build();

        noteDao = db.noteDao();
    }

    public NoteDao getNoteDao() {
        return noteDao;
    }

    public static App getInstance() {
        return instance;
    }
}
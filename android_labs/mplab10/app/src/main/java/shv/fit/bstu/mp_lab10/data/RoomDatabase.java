package shv.fit.bstu.mp_lab10.data;

import androidx.room.Database;
import androidx.room.Room;

import shv.fit.bstu.mp_lab10.models.Note;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {
    public abstract NoteDao noteDao();
}
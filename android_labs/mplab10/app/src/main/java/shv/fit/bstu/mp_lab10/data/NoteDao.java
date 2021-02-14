package shv.fit.bstu.mp_lab10.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import shv.fit.bstu.mp_lab10.models.Note;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM Note")
    LiveData<List<Note>> getAllLiveData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);
}
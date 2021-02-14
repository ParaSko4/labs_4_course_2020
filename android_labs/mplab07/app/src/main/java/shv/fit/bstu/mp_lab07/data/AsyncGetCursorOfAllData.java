package shv.fit.bstu.mp_lab07.data;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

public class AsyncGetCursorOfAllData extends AsyncTask<Context, Void, Cursor> {
    @Override
    protected Cursor doInBackground(Context... contexts) {
        DatabaseHelper databaseHelper = new DatabaseHelper(contexts[0]);
        return databaseHelper.getCursorOfAllData();
    }
}

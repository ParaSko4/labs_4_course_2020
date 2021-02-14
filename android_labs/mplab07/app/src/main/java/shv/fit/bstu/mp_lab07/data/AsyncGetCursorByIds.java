package shv.fit.bstu.mp_lab07.data;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import java.util.ArrayList;

public class AsyncGetCursorByIds extends AsyncTask<Context, Void, Cursor> {
    private DatabaseHelper databaseHelper;
    private ArrayList<Integer> ids;

    public AsyncGetCursorByIds(Context context, ArrayList<Integer> ids){
        this.ids = ids;
        databaseHelper = new DatabaseHelper(context);
    }

    @Override
    protected Cursor doInBackground(Context... contexts) {
        return databaseHelper.getCursorByUsersId(ids);
    }
}

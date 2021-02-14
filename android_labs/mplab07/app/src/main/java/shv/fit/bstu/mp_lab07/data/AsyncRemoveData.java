package shv.fit.bstu.mp_lab07.data;

import android.content.Context;
import android.os.AsyncTask;

public class AsyncRemoveData extends AsyncTask<Void, Void, Void> {
    private DatabaseHelper databaseHelper;
    private int id;

    public AsyncRemoveData(Context context, int id){
        databaseHelper = new DatabaseHelper(context);
        this.id = id;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        databaseHelper.removeData(id);

        return null;
    }
}

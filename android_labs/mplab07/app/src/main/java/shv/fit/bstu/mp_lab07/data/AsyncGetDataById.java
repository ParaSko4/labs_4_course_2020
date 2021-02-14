package shv.fit.bstu.mp_lab07.data;

import android.content.Context;
import android.os.AsyncTask;

import shv.fit.bstu.mp_lab07.models.ContactModel;

public class AsyncGetDataById extends AsyncTask<Void, Void, ContactModel> {
    private DatabaseHelper databaseHelper;
    private int id;

    public AsyncGetDataById(Context context, int id){
        databaseHelper = new DatabaseHelper(context);
        this.id = id;
    }

    @Override
    protected ContactModel doInBackground(Void... contexts) {
        return databaseHelper.findById(id);
    }
}
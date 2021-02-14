package shv.fit.bstu.mp_lab07.data;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import shv.fit.bstu.mp_lab07.models.ContactModel;

public class AsyncGetAllData extends AsyncTask<Context, Void, ArrayList<ContactModel>> {

    @Override
    protected ArrayList<ContactModel> doInBackground(Context... contexts) {
        DatabaseHelper databaseHelper = new DatabaseHelper(contexts[0]);
        return databaseHelper.getAllData();
    }
}

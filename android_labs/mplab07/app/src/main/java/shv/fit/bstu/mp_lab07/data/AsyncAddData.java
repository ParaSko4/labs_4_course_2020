package shv.fit.bstu.mp_lab07.data;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import shv.fit.bstu.mp_lab07.models.ContactModel;

public class AsyncAddData extends AsyncTask<Void, Void, Void> {
    private DatabaseHelper databaseHelper;
    private ContactModel contact;

    public AsyncAddData(Context context, ContactModel contact){
        databaseHelper = new DatabaseHelper(context);
        this.contact = contact;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        databaseHelper.addData(contact);
        return null;
    }
}

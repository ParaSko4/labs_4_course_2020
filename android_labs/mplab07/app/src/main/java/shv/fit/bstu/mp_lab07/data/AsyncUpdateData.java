package shv.fit.bstu.mp_lab07.data;

import android.content.Context;
import android.os.AsyncTask;

import shv.fit.bstu.mp_lab07.models.ContactModel;

public class AsyncUpdateData extends AsyncTask<Void, Void, Void> {
    private DatabaseHelper databaseHelper;
    private ContactModel contact;

    public AsyncUpdateData(Context context, ContactModel contact){
        databaseHelper = new DatabaseHelper(context);
        this.contact = contact;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        databaseHelper.updateData(contact);
        return null;
    }
}

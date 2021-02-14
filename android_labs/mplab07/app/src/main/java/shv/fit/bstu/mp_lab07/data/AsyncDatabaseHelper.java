package shv.fit.bstu.mp_lab07.data;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import shv.fit.bstu.mp_lab07.models.ContactModel;

public class AsyncDatabaseHelper {
    private Context context;

    private AsyncGetAllData allData;
    private AsyncGetCursorOfAllData cursorOfAllData;
    private AsyncGetDataById dataById;
    private AsyncGetCursorByIds cursorByIds;
    private AsyncAddData addData;
    private AsyncRemoveData removeData;
    private AsyncUpdateData updateData;

    public AsyncDatabaseHelper(Context context){
        this.context = context;
    }

    public ArrayList<ContactModel> getAllData() throws ExecutionException, InterruptedException {
        allData = new AsyncGetAllData();
        allData.execute(context);

        return allData.get();
    }

    public Cursor getCursorOfAllData() throws ExecutionException, InterruptedException {
        cursorOfAllData = new AsyncGetCursorOfAllData();
        cursorOfAllData.execute(context);

        return cursorOfAllData.get();
    }

    public Cursor getCursorByIds(ArrayList<Integer> ids) throws ExecutionException, InterruptedException {
        cursorByIds = new AsyncGetCursorByIds(context, ids);
        cursorByIds.execute();

        return cursorByIds.get();
    }

    public ContactModel findById(int id) throws ExecutionException, InterruptedException {
        dataById = new AsyncGetDataById(context, id);
        dataById.execute();
        return dataById.get();
    }

    public void addData(ContactModel model){
        addData = new AsyncAddData(context, model);
        addData.execute();
    }

    public void updateData(ContactModel model){
        updateData = new AsyncUpdateData(context, model);
        updateData.execute();
    }

    public void removeData(int id){
        removeData = new AsyncRemoveData(context, id);
        removeData.execute();
    }
}

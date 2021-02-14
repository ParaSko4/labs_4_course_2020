package shv.fit.bstu.mp_lab04.models;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH;
    private static String DB_NAME = "bdlab4.db";
    public static final String TABLE = "UserInfo";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_URL = "url";

    private Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = context.getFilesDir().getPath() + DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void create_db(){
        InputStream myInput = null;
        OutputStream myOutput = null;

        try {
            File file = new File(DB_PATH);

            if (!file.exists()) {
                this.getReadableDatabase();

                myInput = myContext.getAssets().open(DB_NAME);

                String outFileName = DB_PATH;

                myOutput = new FileOutputStream(outFileName);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        }
        catch(IOException ex){
            Log.d("DatabaseHelper", "hi 1! " + DB_PATH);
        }
    }

    public SQLiteDatabase Open()throws SQLException {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public ArrayList<String> GetAllUsersNames(){
        SQLiteDatabase db = Open();
        ArrayList<String> userNameList = new ArrayList<>();

        String query = "SELECT name FROM "+ TABLE + ";";
        try{
            Cursor cursor = db.rawQuery(query, null);

            while (cursor.moveToNext()){
                userNameList.add(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            }

            return  userNameList;
        }catch (Exception err){
            Log.i("err", err.getMessage());
        }

        return  null;
    }

    public ContactModel findContactById(int id){
        SQLiteDatabase db = Open();
        String where = "Select * from " + TABLE + " where " + COLUMN_ID + " = ?";
        Cursor cursor = db.rawQuery(where, new String[]{ String.valueOf(id) });
        cursor.moveToFirst();

        ContactModel contact = new ContactModel();
        contact.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
        contact.location = cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION));
        contact.email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
        contact.phone =  cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
        contact.url = cursor.getString(cursor.getColumnIndex(COLUMN_URL));
        contact.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));

        return contact;
    }

    public ArrayList<ContactModel> GetAllUsers(){
        SQLiteDatabase db = Open();
        ArrayList<ContactModel> contacts = new ArrayList<>();
        ContactModel contact;

        String query = "SELECT * FROM "+ TABLE + ";";
        try{
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()){
                contact = new ContactModel();
                contact.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                contact.location = cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION));
                contact.email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                contact.phone =  cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
                contact.url = cursor.getString(cursor.getColumnIndex(COLUMN_URL));
                contact.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));

                contacts.add(contact);
            }

            return  contacts;
        }catch (Exception err){
            Log.i("err", err.getMessage());
        }

        return  null;
    }

    public void AddNewData(ContactModel newData){
        SQLiteDatabase db = Open();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, newData.name);
        values.put(COLUMN_PHONE, newData.phone);
        values.put(COLUMN_EMAIL, newData.email);
        values.put(COLUMN_LOCATION, newData.location);
        values.put(COLUMN_URL, newData.url);

        db.insert(TABLE, null, values);
    }

    public ContactModel FindContactById(int id){
        SQLiteDatabase db = Open();
        String where = "Select * from " + TABLE + " where " + COLUMN_ID + " = ?";
        Cursor cursor = db.rawQuery(where, new String[]{ String.valueOf(id) });
        cursor.moveToFirst();

        ContactModel contact = new ContactModel();
        contact.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
        contact.location = cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION));
        contact.email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
        contact.phone =  cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
        contact.url = cursor.getString(cursor.getColumnIndex(COLUMN_URL));
        contact.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));

        return contact;
    }

    public void UpdateData(ContactModel data){
        SQLiteDatabase db = Open();
        ContentValues cvalues = new ContentValues();
        cvalues.put(COLUMN_NAME, data.name);
        cvalues.put(COLUMN_PHONE, data.phone);
        cvalues.put(COLUMN_LOCATION, data.location);
        cvalues.put(COLUMN_EMAIL, data.email);
        cvalues.put(COLUMN_URL, data.url);

        db.update(TABLE, cvalues, "id = ?", new String[]{ String.valueOf(data.id) });
    }

    public void RemoveData(int id){
        SQLiteDatabase db = Open();
        db.delete(TABLE, "id = " + String.valueOf(id), null);
    }
}
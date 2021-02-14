package shv.fit.bstu.mp_lab07.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.text.PrecomputedText;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.transform.Result;

import shv.fit.bstu.mp_lab07.models.ContactModel;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH;
    private static String DB_NAME = "database7.db";
    public static final String TABLE = "UserInfo";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_IMG = "image";

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
                myOutput = new FileOutputStream(DB_PATH);

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

    public SQLiteDatabase open()throws SQLException {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public ArrayList<ContactModel> getAllData(){
        SQLiteDatabase db = open();
        ArrayList<ContactModel> contacts = new ArrayList<>();
        ContactModel contact;

        String query = "SELECT * FROM "+ TABLE + " ORDER BY " + COLUMN_NAME + " ;";
        try{
            Cursor cursor = db.rawQuery(query, null);

            while (cursor.moveToNext()) {
                contact = new ContactModel();
                contact.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                contact.location = cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION));
                contact.email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                contact.phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
                contact.url = cursor.getString(cursor.getColumnIndex(COLUMN_URL));
                contact.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                contact.cimg = cursor.getString(cursor.getColumnIndex(COLUMN_IMG));

                contacts.add(contact);
            }

            return  contacts;
        }catch (Exception err){
            Log.i("err", err.getMessage());
        }

        return  null;
    }

    public Cursor getCursorOfAllData(){
        SQLiteDatabase db = open();
        String query = "SELECT * FROM "+ TABLE + " ORDER BY " + COLUMN_NAME + " ;";

        return db.rawQuery(query, null);
    }

    public Cursor getCursorByUsersId(ArrayList<Integer> ids){
        SQLiteDatabase db = open();
        String query = "SELECT * FROM "+ TABLE + " where ";

        for (int i = 0; i < ids.size(); i++){
            query += COLUMN_ID + " = " + ids.get(i);
            if (i + 1 != ids.size()){
                query += " or ";
            }
        }

        query += " ORDER BY " + COLUMN_NAME + " ;";

        return db.rawQuery(query, null);
    }

    public void addData(ContactModel newData){
        SQLiteDatabase db = open();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, newData.name);
        values.put(COLUMN_PHONE, newData.phone);
        values.put(COLUMN_EMAIL, newData.email);
        values.put(COLUMN_LOCATION, newData.location);
        values.put(COLUMN_URL, newData.url);
        values.put(COLUMN_IMG, newData.cimg);

        db.insert(TABLE, null, values);
    }

    public ContactModel findById(int id){
        SQLiteDatabase db = open();
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
        contact.cimg = cursor.getString(cursor.getColumnIndex(COLUMN_IMG));

        return contact;
    }

    public void updateData(ContactModel data){
        SQLiteDatabase db = open();
        ContentValues cvalues = new ContentValues();
        cvalues.put(COLUMN_NAME, data.name);
        cvalues.put(COLUMN_PHONE, data.phone);
        cvalues.put(COLUMN_LOCATION, data.location);
        cvalues.put(COLUMN_EMAIL, data.email);
        cvalues.put(COLUMN_IMG, data.cimg);

        db.update(TABLE, cvalues, COLUMN_ID + " = ?", new String[]{ String.valueOf(data.id) });
    }

    public void removeData(int id){
        SQLiteDatabase db = open();
        db.delete(TABLE, COLUMN_ID + " = " + String.valueOf(id), null);
    }
}
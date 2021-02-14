package shv.fit.bstu.mp_lab07.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import shv.fit.bstu.mp_lab07.R;
import shv.fit.bstu.mp_lab07.data.DatabaseHelper;
import shv.fit.bstu.mp_lab07.models.ContactModel;

public class ContactListCursorAdapter extends CursorAdapter {
    private DatabaseHelper db;

    private List<ContactModel> contacts;
    private ArrayList<Integer> selectedIndex;

    public ContactListCursorAdapter(Context context, Cursor c, List<ContactModel> contacts, ArrayList<Integer> selectedIndex){
        super(context, c, 0);
        db = new DatabaseHelper(context);

        this.contacts = contacts;
        this.selectedIndex = selectedIndex;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_row, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(db.COLUMN_ID));
        if (selectedIndex != null){
            for (ContactModel model: contacts){
                if (model.id == id){
                    if (selectedIndex.contains(contacts.indexOf(model))) {
                        view.findViewById(R.id.line).setBackgroundColor(Color.parseColor("#668cff"));
                    }
                }
            }
        }

        TextView nameView = view.findViewById(R.id.nameView);
        TextView numberView = view.findViewById(R.id.numberView);
        ImageView imageView = view.findViewById(R.id.imageView);

        nameView.setText(cursor.getString(cursor.getColumnIndex(db.COLUMN_NAME)));
        numberView.setText(cursor.getString(cursor.getColumnIndex(db.COLUMN_PHONE)));
        String img = cursor.getString(cursor.getColumnIndex(db.COLUMN_IMG));
        if (img == null || img.equals(" ")){
            imageView.setImageResource(R.drawable.baseline_account_box_white_18dp);
        }
        else{
            try {
                File directory = context.getDir("imageDir", Context.MODE_PRIVATE);
                File f = new File(directory, img);
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                imageView.setImageBitmap(b);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
}

package shv.fit.bstu.mp_lab08.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import shv.fit.bstu.mp_lab08.R;
import shv.fit.bstu.mp_lab08.models.ContactModel;

public class ContactListAdapter extends BaseAdapter {
    private Context context;
    private List<ContactModel> contacts;
    private ArrayList<Integer> selectedIndex;

    private static LayoutInflater inflater = null;

    public ContactListAdapter(Context context, List<ContactModel> contacts, ArrayList<Integer> selectedIndex){
        this.context = context;
        this.contacts = contacts;
        this.selectedIndex = selectedIndex;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = inflater.inflate(R.layout.item_row, viewGroup, false);

        if (selectedIndex != null){
            if (selectedIndex.contains(i)) {
                view.findViewById(R.id.line).setBackgroundColor(Color.parseColor("#668cff"));
            }
        }

        TextView nameView = view.findViewById(R.id.nameView);
        TextView numberView = view.findViewById(R.id.numberView);
        ImageView imageView = view.findViewById(R.id.imageView);

        ContactModel contact = contacts.get(i);

        nameView.setText(contact.name);
        numberView.setText(contact.phone);
        if (contact.cimg == null || contact.cimg.equals(" ")){
            imageView.setImageResource(R.drawable.baseline_account_box_white_18dp);
        }
        else{
            try {
                File directory = context.getDir("imageDir", Context.MODE_PRIVATE);
                File f = new File(directory, contact.cimg);
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                imageView.setImageBitmap(b);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }

        return view;
    }
}

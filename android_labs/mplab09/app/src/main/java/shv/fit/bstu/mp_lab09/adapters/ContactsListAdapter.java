package shv.fit.bstu.mp_lab09.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import shv.fit.bstu.mp_lab09.R;
import shv.fit.bstu.mp_lab09.models.ContactModal;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ViewHolder> {
    public interface OnContactClickListener{
        void onContactClick(ContactModal contact, int position);
    }

    private final OnContactClickListener onClickListener;

    private final LayoutInflater inflater;
    private ArrayList<ContactModal> contacts;
    private Context context;

    public ContactsListAdapter(Context context, ArrayList<ContactModal> contacts, OnContactClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public ContactsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsListAdapter.ViewHolder holder, int position) {
        ContactModal contact = contacts.get(position);

        if (contact.cimg == null || contact.cimg.equals(" ")){
            holder.imageView.setImageResource(R.drawable.baseline_account_box_white_18dp);
        }
        else{
            try {
                File directory = context.getDir("imageDir", Context.MODE_PRIVATE);
                File f = new File(directory, contact.cimg);
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                holder.imageView.setImageBitmap(b);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }

        holder.nameView.setText(contact.name);
        holder.numberView.setText(contact.phone);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onContactClick(contact, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameView, numberView;

        ViewHolder(View view){
            super(view);
            imageView = (ImageView)view.findViewById(R.id.imageView);
            nameView = (TextView) view.findViewById(R.id.nameView);
            numberView = (TextView) view.findViewById(R.id.numberView);
        }
    }
}

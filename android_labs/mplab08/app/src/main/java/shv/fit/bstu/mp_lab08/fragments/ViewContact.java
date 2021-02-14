package shv.fit.bstu.mp_lab08.fragments;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;

import shv.fit.bstu.mp_lab08.ContactEditorActivity;
import shv.fit.bstu.mp_lab08.R;
import shv.fit.bstu.mp_lab08.data.DatabaseHelper;
import shv.fit.bstu.mp_lab08.fragments.ViewContactsList;
import shv.fit.bstu.mp_lab08.models.ContactModel;

public class ViewContact extends Fragment {
    private DatabaseHelper databaseHelper;

    private TextView nameText;
    private Button phoneButton;
    private Button emailButton;
    private Button locationButton;
    private Button urlButton;
    private ImageView imageView;

    private ContactModel contact;

    private Menu menu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_contact, container, false);

        nameText = view.findViewById(R.id.name);
        phoneButton = view.findViewById(R.id.phone);
        emailButton = view.findViewById(R.id.email);
        locationButton = view.findViewById(R.id.location);
        urlButton = view.findViewById(R.id.url);
        imageView = view.findViewById(R.id.imageContactView);

        databaseHelper = new DatabaseHelper(this.getContext());

        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toDial = "tel:" + phoneButton.getText().toString();
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toDial)));
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailButton.getText().toString()});

                startActivity(emailIntent);
            }
        });

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String geoUriString = "geo:0,0?q=" + locationButton.getText().toString();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(geoUriString)));
            }
        });

        urlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlButton.getText().toString())));
            }
        });
        setContactDataToUI();

        return view;
    }

    public void setSelectedData(ContactModel contact) {
        this.contact = contact;
    }

    private void setContactDataToUI() {
        if (contact == null){
            return;
        }
        nameText.setText(contact.name);

        if (contact.location.equals(" ")) {
            locationButton.setEnabled(false);
        }
        if (contact.email.equals(" ")) {
            emailButton.setEnabled(false);
        }
        if (contact.url.equals(" ")) {
            urlButton.setEnabled(false);
        }

        locationButton.setText(contact.location);
        emailButton.setText(contact.email);
        urlButton.setText(contact.url);
        phoneButton.setText(contact.phone);

        if (contact.cimg != null) {
            loadImageFromStorage();
        } else {
            imageView.setImageResource(R.drawable.baseline_account_box_white_18dp);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setContactDataToUI();
    }

    private void loadImageFromStorage() {
        try {
            File directory = this.getContext().getDir("imageDir", Context.MODE_PRIVATE);
            File f = new File(directory, contact.cimg);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            imageView.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("contact", contact);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@NonNull Bundle resored) {
        if (resored != null) {
            contact = (ContactModel) resored.getSerializable("contact");
        }
        super.onViewStateRestored(resored);
    }
}
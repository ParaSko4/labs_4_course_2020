package shv.fit.bstu.mp_lab06;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import shv.fit.bstu.mp_lab06.models.ContactModel;
import shv.fit.bstu.mp_lab06.models.DatabaseHelper;

public class ContactInfoActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    private Button phoneButton;
    private Button emailButton;
    private Button locationButton;
    private Button urlButton;
    private ImageView imageView;

    private ContactModel contact;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info_layout);

        if (handleIntent(getIntent())){
            return;
        }

        phoneButton = findViewById(R.id.phone);
        emailButton = findViewById(R.id.email);
        locationButton = findViewById(R.id.location);
        urlButton = findViewById(R.id.url);
        imageView = findViewById(R.id.imageContactView);

        userEmail = (String) getIntent().getSerializableExtra("FirebaseUser");
        databaseHelper = new DatabaseHelper(userEmail);

        contact = (ContactModel) getIntent().getSerializableExtra("Contact");

        setContactDataToUI();

        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toDial="tel:"+phoneButton.getText().toString();
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
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
        super.onNewIntent(intent);
    }

    private boolean handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.i("query", query);

            return true;
        }

        return false;
    }

    private void setContactDataToUI(){
        ((TextView)findViewById(R.id.name)).setText(contact.name);

        if (contact.location.equals(" ")){
            locationButton.setEnabled(false);
        }
        if (contact.email.equals(" ")){
            emailButton.setEnabled(false);
        }
        if (contact.url.equals(" ")){
            urlButton.setEnabled(false);
        }

        locationButton.setText(contact.location);
        emailButton.setText(contact.email);
        urlButton.setText(contact.url);
        phoneButton.setText(contact.phone);
        if (contact.cimg != null){
            loadImageFromStorage();
        }else{
            imageView.setImageResource(R.drawable.baseline_account_box_white_18dp);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contact_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Edit:
                Intent intent = new Intent(shv.fit.bstu.mp_lab06.ContactInfoActivity.this, ContactEditorActivity.class);
                intent.putExtra("NewValue", false);
                intent.putExtra("Contact", contact);
                intent.putExtra("FirebaseUser", userEmail);

                startActivityForResult(intent, 1);

                return true;
            case R.id.Delete:
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                databaseHelper.removeData(contact.phone);
                                finish();

                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(shv.fit.bstu.mp_lab06.ContactInfoActivity.this);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener)
                        .show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        databaseHelper.setListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean check = false;

                for (DataSnapshot contactSnapshot: snapshot.getChildren()) {
                    if (contactSnapshot.getValue(ContactModel.class).phone == contact.phone){
                        check = true;
                    }
                }

                if (!check){
                    finish();
                }
                if (data != null){
                    contact = (ContactModel) data.getSerializableExtra("Result");
                }
                setContactDataToUI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void loadImageFromStorage()
    {
        try {
            File directory = this.getDir("imageDir", Context.MODE_PRIVATE);
            File f = new File(directory, contact.cimg);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            imageView.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
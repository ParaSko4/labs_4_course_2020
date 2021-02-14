package shv.fit.bstu.mp_lab07;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;

import shv.fit.bstu.mp_lab07.data.AsyncDatabaseHelper;
import shv.fit.bstu.mp_lab07.data.AsyncRemoveData;
import shv.fit.bstu.mp_lab07.models.ContactModel;

public class ContactInfoActivity extends AppCompatActivity {
    private AsyncDatabaseHelper asyncDatabaseHelper;

    private Button phoneButton;
    private Button emailButton;
    private Button locationButton;
    private Button urlButton;
    private ImageView imageView;

    private ContactModel contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info_layout);

        if (handleIntent(getIntent())){
            return;
        }

        asyncDatabaseHelper = new AsyncDatabaseHelper(getApplicationContext());

        phoneButton = findViewById(R.id.phone);
        emailButton = findViewById(R.id.email);
        locationButton = findViewById(R.id.location);
        urlButton = findViewById(R.id.url);
        imageView = findViewById(R.id.imageContactView);

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
                Intent intent = new Intent(ContactInfoActivity.this, ContactEditorActivity.class);
                intent.putExtra("NewValue", false);
                intent.putExtra("Contact", contact);

                startActivityForResult(intent, 1);

                return true;
            case R.id.Delete:
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                asyncDatabaseHelper.removeData(contact.id);
                                finish();

                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(ContactInfoActivity.this);
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
        try {
            if (asyncDatabaseHelper.findById(contact.id) == null){
                finish();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (data != null){
            contact = (ContactModel) data.getSerializableExtra("Result");
        }
        setContactDataToUI();

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
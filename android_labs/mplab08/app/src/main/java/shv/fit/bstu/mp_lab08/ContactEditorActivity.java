package shv.fit.bstu.mp_lab08;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import shv.fit.bstu.mp_lab08.data.DatabaseHelper;
import shv.fit.bstu.mp_lab08.models.ContactModel;

public class ContactEditorActivity extends AppCompatActivity {
    private final String keyName = "KEY_NAME";
    private final String keyPhone = "KEY_PHONE";
    private final String keyEmail = "KEY_EMAIL";
    private final String keyLocation = "KEY_LOCATION";
    private final String keyUrl= "KEY_URL";

    private DatabaseHelper databaseHelper;

    private Button saveButton;

    private TextView name;
    private TextView phone;
    private TextView email;
    private TextView location;
    private TextView url;
    private ImageView imageView;

    private boolean addNewUserInfo = false;
    public ContactModel contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        location = findViewById(R.id.location);
        url = findViewById(R.id.url);
        imageView = findViewById(R.id.imageContactView);


        databaseHelper = new DatabaseHelper(getApplicationContext());

        addNewUserInfo = (boolean)getIntent().getSerializableExtra("NewValue");

        if (addNewUserInfo){
            contact = new ContactModel();
            imageView.setImageResource(R.drawable.baseline_account_box_white_18dp);
        }else{
            contact = (ContactModel) getIntent().getSerializableExtra("Contact");

            name.setText(contact.name);
            phone.setText(contact.phone);
            email.setText(contact.email);
            location.setText(contact.location);
            url.setText(contact.url);
            if (contact.cimg == null || contact.cimg.equals(" ")){
                imageView.setImageResource(R.drawable.baseline_account_box_white_18dp);
            }else{
                loadImageFromStorage();
            }
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contact_editor_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Save:
                contact.name = ((TextView)findViewById(R.id.name)).getText().toString();
                if (contact.name.length() == 0){
                    Toast.makeText(this, "Enter Name field.", Toast.LENGTH_SHORT).show();
                    return true;
                }
                contact.phone = ((TextView)findViewById(R.id.phone)).getText().toString();
                if (contact.phone.length() == 0){
                    Toast.makeText(this, "Enter Phone field.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                contact.url = ((TextView)findViewById(R.id.url)).getText().toString();
                contact.url = contact.url.length() == 0 ? " " : contact.url;

                contact.email = ((TextView)findViewById(R.id.email)).getText().toString();
                contact.email = contact.email.length() == 0 ? " " : contact.email;

                contact.location = ((TextView)findViewById(R.id.location)).getText().toString();
                contact.location = contact.location.length() == 0 ? " " : contact.location;

                if (addNewUserInfo){
                    databaseHelper.addNewData(contact);
                    finish();
                }else{
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    databaseHelper.updateData(contact);

                                    Intent intent = new Intent();
                                    intent.putExtra("Result", contact);
                                    setResult(RESULT_OK, intent);
                                    finish();

                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener)
                            .show();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(keyName, name.getText().toString());
        outState.putString(keyPhone, phone.getText().toString());
        outState.putString(keyEmail, email.getText().toString());
        outState.putString(keyLocation, location.getText().toString());
        outState.putString(keyUrl, url.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        name.setText(savedInstanceState.getString(keyName));
        phone.setText(savedInstanceState.getString(keyPhone));
        email.setText(savedInstanceState.getString(keyEmail));
        location.setText(savedInstanceState.getString(keyLocation));
        url.setText(savedInstanceState.getString(keyUrl));

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageView.setImageBitmap(selectedImage);

                saveToInternalStorage(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        File directory = this.getDir("imageDir", Context.MODE_PRIVATE);
        String imgName = contact.id + ".jpg";
        File mypath = new File(directory, imgName);

        contact.cimg = imgName;

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
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
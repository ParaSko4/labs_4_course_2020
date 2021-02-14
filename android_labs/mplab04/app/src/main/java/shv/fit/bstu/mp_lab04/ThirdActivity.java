package shv.fit.bstu.mp_lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import shv.fit.bstu.mp_lab04.models.ContactModel;
import shv.fit.bstu.mp_lab04.models.DatabaseHelper;

public class ThirdActivity extends AppCompatActivity {
    private final String keyName = "KEY_NAME";
    private final String keyPhone = "KEY_PHONE";
    private final String keyEmail = "KEY_EMAIL";
    private final String keyLocation = "KEY_LOCATION";
    private final String keyUrl= "KEY_URL";

    private DatabaseHelper databaseHelper;

    private Button saveButton;
    private Button removeButton;

    private TextView name;
    private TextView phone;
    private TextView email;
    private TextView location;
    private TextView url;

    private boolean addNewUserInfo = false;
    public ContactModel contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        saveButton = (Button)findViewById(R.id.saveButton);
        removeButton = (Button)findViewById(R.id.removeButton);

        name = (TextView)findViewById(R.id.name);
        phone = (TextView)findViewById(R.id.phone);
        email = (TextView)findViewById(R.id.email);
        location = (TextView)findViewById(R.id.location);
        url = (TextView)findViewById(R.id.url);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        addNewUserInfo = (boolean)getIntent().getSerializableExtra("NewValue");

        if (addNewUserInfo){
            saveButton.setText("Add");
            removeButton.setText("Cansel");

            contact = new ContactModel();
        }else{
            contact = (ContactModel) getIntent().getSerializableExtra("Contact");

            name.setText(contact.name);
            phone.setText(contact.phone);
            email.setText(contact.email);
            location.setText(contact.location);
            url.setText(contact.url);
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact.name = ((TextView)findViewById(R.id.name)).getText().toString();
                if (contact.name.length() == 0){
                    Toast.makeText(ThirdActivity.this, "Enter Name field.", Toast.LENGTH_SHORT).show();
                    return;
                }
                contact.phone = ((TextView)findViewById(R.id.phone)).getText().toString();
                if (contact.phone.length() == 0){
                    Toast.makeText(ThirdActivity.this, "Enter Phone field.", Toast.LENGTH_SHORT).show();
                    return;
                }

                contact.url = ((TextView)findViewById(R.id.url)).getText().toString();
                contact.url = contact.url.length() == 0 ? " " : contact.url;

                contact.email = ((TextView)findViewById(R.id.email)).getText().toString();
                contact.email = contact.email.length() == 0 ? " " : contact.email;

                contact.location = ((TextView)findViewById(R.id.location)).getText().toString();
                contact.location = contact.location.length() == 0 ? " " : contact.location;

                if (addNewUserInfo){
                    databaseHelper.AddNewData(contact);
                    finish();
                }

                databaseHelper.UpdateData(contact);
                FinishActivity(contact);
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!addNewUserInfo){
                    databaseHelper.RemoveData(contact.id);
                    FinishActivity(null);
                }
                finish();
            }
        });
    }

    private void FinishActivity(ContactModel result){
        Intent intent = new Intent();
        intent.putExtra("Result", result);
        setResult(RESULT_OK, intent);
        finish();
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
        super.onRestoreInstanceState(savedInstanceState);

        name.setText(savedInstanceState.getString(keyName));
        phone.setText(savedInstanceState.getString(keyPhone));
        email.setText(savedInstanceState.getString(keyEmail));
        location.setText(savedInstanceState.getString(keyLocation));
        url.setText(savedInstanceState.getString(keyUrl));
    }
}
package shv.fit.bstu.mp_lab04;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import shv.fit.bstu.mp_lab04.models.ContactModel;
import shv.fit.bstu.mp_lab04.models.DatabaseHelper;

public class SecondActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    private Button changeButton;
    private Button phoneButton;
    private Button emailButton;
    private Button locationButton;
    private Button urlButton;

    public ContactModel contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        contact = (ContactModel) getIntent().getSerializableExtra("Contact");

        SetContactDataToUI();

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("NewValue", false);
                intent.putExtra("Contact", contact);

                startActivityForResult(intent, 1);
            }
        });

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

    private void SetContactDataToUI(){
        ((TextView)findViewById(R.id.name)).setText(contact.name);

        changeButton = findViewById(R.id.saveButton);
        phoneButton = findViewById(R.id.phone);
        emailButton = findViewById(R.id.email);
        locationButton = findViewById(R.id.location);
        urlButton = findViewById(R.id.url);

        setContactDataToUI();
    }

    private void setContactDataToUI(){
        ((TextView)findViewById(R.id.name)).setText(contact.name);

        locationButton.setText(contact.location);
        emailButton.setText(contact.email);
        urlButton.setText(contact.url);
        phoneButton.setText(contact.phone);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (databaseHelper.findContactById(contact.id) == null){
            finish();
        }
        if (data != null){
            contact = (ContactModel) data.getSerializableExtra("Result");
        }
        setContactDataToUI();

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        databaseHelper = new DatabaseHelper(getApplicationContext());
        contact = databaseHelper.FindContactById(contact.id);

        super.onPause();
    }
}
package shv.fit.bstu.mp_lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.AdapterView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import shv.fit.bstu.mp_lab04.models.ContactModel;
import shv.fit.bstu.mp_lab04.models.DatabaseHelper;

public class ViewContacts extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    public ArrayList<ContactModel> contacts;
    private ArrayList<String> contactNames;

    private ListView contactView;
    public ContactModel contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactView = (ListView)findViewById(R.id.ContactView);

        contactNames = new ArrayList<>();

        contactView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                contact = contacts.get(i);

                Intent intent = new Intent(ViewContacts.this, SecondActivity.class);
                intent.putExtra("Contact", contact);
                startActivity(intent);
            }
        });

        contactView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                Toast.makeText(getApplicationContext(), "long clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        ((Button)findViewById(R.id.addButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewContacts.this, ThirdActivity.class);
                intent.putExtra("NewValue", true);
                startActivity(intent);
            }
        });

        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();

        contacts = databaseHelper.GetAllUsers();
        contactNames = databaseHelper.GetAllUsersNames();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactNames);
        contactView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        contacts = databaseHelper.GetAllUsers();
        contactNames = databaseHelper.GetAllUsersNames();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactNames);
        contactView.setAdapter(adapter);
    }
}
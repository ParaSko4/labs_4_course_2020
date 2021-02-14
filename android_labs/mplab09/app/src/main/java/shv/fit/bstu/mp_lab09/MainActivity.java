package shv.fit.bstu.mp_lab09;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import shv.fit.bstu.mp_lab09.adapters.ContactsListAdapter;
import shv.fit.bstu.mp_lab09.data.DatabaseHelper;
import shv.fit.bstu.mp_lab09.models.ContactModal;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton button;
    private CardView card;
    private RecyclerView recyclerView;
    private ContactsListAdapter contactsListAdapter;
    private ContactsListAdapter.OnContactClickListener contactClickListener;

    private DatabaseHelper databaseHelper;

    public ArrayList<ContactModal> contacts;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.create_db();

        contacts = databaseHelper.getAllUsers();

        button = findViewById(R.id.floatingActionButton);
        card = findViewById(R.id.cardView);
        recyclerView = findViewById(R.id.recyclerView);

        contactClickListener = new ContactsListAdapter.OnContactClickListener() {
            @Override
            public void onContactClick(ContactModal contact, int position) {
                TextView name = card.findViewById(R.id.nameViewCard);
                TextView number = card.findViewById(R.id.numberViewCard);

                name.setText(contact.name);
                number.setText(contact.phone);
            }
        };

        contacts = databaseHelper.getAllUsers();
        contactsListAdapter = new ContactsListAdapter(this, contacts, contactClickListener);
        recyclerView.setAdapter(contactsListAdapter);

        findViewById(R.id.toggleButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean on = ((ToggleButton) view).isChecked();

                if (on) {
                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                    recyclerView.setAdapter(contactsListAdapter);
                } else {
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(contactsListAdapter);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactEditorActivity.class);
                intent.putExtra("NewValue", true);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    protected void onResume() {
        contacts = databaseHelper.getAllUsers();
        contactsListAdapter = new ContactsListAdapter(this, contacts, contactClickListener);
        recyclerView.setAdapter(contactsListAdapter);

        super.onResume();
    }
}
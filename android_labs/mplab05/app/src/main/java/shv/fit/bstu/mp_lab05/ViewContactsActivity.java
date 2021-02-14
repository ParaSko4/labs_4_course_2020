package shv.fit.bstu.mp_lab05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Parcelable;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Comparator;

import shv.fit.bstu.mp_lab05.adapters.ContactListAdapter;
import shv.fit.bstu.mp_lab05.data.DatabaseHelper;
import shv.fit.bstu.mp_lab05.models.ContactModel;

public class ViewContactsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private DatabaseHelper databaseHelper;

    public ArrayList<ContactModel> contacts;
    public ArrayList<ContactModel> dbContacts;
    public ArrayList<ContactModel> selectedContacts;

    private ArrayList<Integer> selectedIndex;

    private ListView contactView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();

        selectedContacts = new ArrayList<>();
        selectedIndex = new ArrayList<>();


        dbContacts = databaseHelper.getAllUsers();
        contacts = new ArrayList<>(dbContacts);

        contactView = findViewById(R.id.ContactView);
        contactView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ViewContactsActivity.this, ContactInfoActivity.class);
                intent.putExtra("Contact", contacts.get(i));
                startActivity(intent);
            }
        });
        contactView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        contactView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.context, menu);
                return true;
            }

            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getTitle().toString()){
                    case "View":
                        Intent intent = new Intent(ViewContactsActivity.this, ViewSelectedContactActivity.class);
                        intent.putExtra("ViewContacts", selectedContacts);
                        startActivity(intent);
                        break;
                    case "Delete":
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        ContactModel contact;

                                        for (int i = 0; i < selectedContacts.size(); i++){
                                            contact = selectedContacts.get(i);
                                            contacts.remove(contact);
                                            databaseHelper.removeData(contact.id);
                                        }

                                        setAdapter();

                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(ViewContactsActivity.this);
                        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener)
                                .show();
                        break;
                }

                mode.finish();
                return false;
            }

            public void onDestroyActionMode(ActionMode mode) {
                if (contactView.getChildCount() != 0){
                    for (int i = 0; i < contactView.getCount(); i++){
                        contactView.getChildAt(i).findViewById(R.id.line).setBackgroundColor(Color.parseColor("#2B2A2A"));
                    }
                }

                selectedIndex.clear();
                selectedContacts.clear();
            }

            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                if (checked){
                    if (contactView.getChildCount() != 0){
                        contactView.getChildAt(position).findViewById(R.id.line).setBackgroundColor(Color.parseColor("#668cff"));
                        selectedIndex.add(position);
                    }

                    selectedContacts.add(contacts.get(position));
                }else{
                    contactView.getChildAt(position).findViewById(R.id.line).setBackgroundColor(Color.parseColor("#2B2A2A"));

                    selectedContacts.remove(contacts.get(position));
                    selectedIndex.remove((Integer) position);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapter();
    }

    private void setAdapter(){
        dbContacts = databaseHelper.getAllUsers();
        contacts = new ArrayList<>(dbContacts);

        contacts.sort(new Comparator<ContactModel>() {
            @Override
            public int compare(ContactModel o1, ContactModel o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        contactView.setAdapter(new ContactListAdapter(this, contacts, selectedIndex));

        if (selectedIndex.size() != 0){
            for (int i = 0; i < selectedIndex.size(); i++){
                if(contacts.size() <= selectedIndex.get(i)) {
                    contactView.setItemChecked(selectedIndex.get(i), true);
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putIntegerArrayList("selectedIndex", selectedIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        selectedIndex = savedInstanceState.getIntegerArrayList("selectedIndex");
        super.onRestoreInstanceState(savedInstanceState);
    }





    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contacts_menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.New:
                Intent intent = new Intent(ViewContactsActivity.this, ContactEditorActivity.class);
                intent.putExtra("NewValue", true);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (s.length() == 0){
            contacts = new ArrayList<>(dbContacts);
        }else{
            String regex = String.format("^%s.*$", s);
            contacts = new ArrayList<>();

            for (int i = 0; i < dbContacts.size(); i++){
                if (dbContacts.get(i).name.matches(regex)){
                    contacts.add(dbContacts.get(i));
                }
            }
        }

        setAdapter();

        return false;
    }
}
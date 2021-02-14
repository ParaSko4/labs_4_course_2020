package shv.fit.bstu.mp_lab07;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import shv.fit.bstu.mp_lab07.adapters.ContactListCursorAdapter;
import shv.fit.bstu.mp_lab07.data.AsyncDatabaseHelper;
import shv.fit.bstu.mp_lab07.data.DatabaseHelper;
import shv.fit.bstu.mp_lab07.models.ContactModel;

public class ViewContactsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private AsyncDatabaseHelper asyncDatabaseHelper;

    public ArrayList<ContactModel> contacts;
    public ArrayList<ContactModel> dbContacts;
    public ArrayList<ContactModel> selectedContacts;

    private ArrayList<Integer> selectedIndex;

    private ListView contactView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();

        asyncDatabaseHelper = new AsyncDatabaseHelper(getApplicationContext());

        selectedContacts = new ArrayList<>();
        selectedIndex = new ArrayList<>();

        contactView = findViewById(R.id.ContactView);
        contactView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("jopa", String.valueOf(i));
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
                                        for (ContactModel model: selectedContacts){
                                            contacts.remove(model);
                                            asyncDatabaseHelper.removeData(model.id);
                                        }

                                        try {
                                            setAdapter();
                                        } catch (ExecutionException e) {
                                            e.printStackTrace();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                        mode.finish();
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
        try {
            setAdapter();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public View getViewByPosition(int pos) {
        final int firstListItemPosition = contactView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + contactView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return contactView.getAdapter().getView(pos, null, contactView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return contactView.getChildAt(childIndex);
        }
    }

    private void setAdapter() throws ExecutionException, InterruptedException {
        dbContacts = asyncDatabaseHelper.getAllData();
        contacts = new ArrayList<>(dbContacts);

        contactView.setAdapter(new ContactListCursorAdapter(this, asyncDatabaseHelper.getCursorOfAllData(), contacts, selectedIndex));
        if (selectedIndex.size() != 0){
            for (int i = 0; i < selectedIndex.size(); i++){
                contactView.setItemChecked(selectedIndex.get(i), true);
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

        try {
            setAdapter();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }
}
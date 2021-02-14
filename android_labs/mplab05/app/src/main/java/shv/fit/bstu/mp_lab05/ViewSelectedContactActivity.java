package shv.fit.bstu.mp_lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import shv.fit.bstu.mp_lab05.adapters.ContactListAdapter;
import shv.fit.bstu.mp_lab05.models.ContactModel;
import shv.fit.bstu.mp_lab05.data.DatabaseHelper;

public class ViewSelectedContactActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    private ListView contactView;
    private List<ContactModel> contacts;
    private ContactModel contact;

    private ClipboardManager clipboard;

    private DialogInterface.OnClickListener dialogClickListener;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_contact);

        contactView = findViewById(R.id.ContactView);

        contacts = (List<ContactModel>)getIntent().getSerializableExtra("ViewContacts");
        contactView.setAdapter(new ContactListAdapter(this, contacts, null));

        databaseHelper = new DatabaseHelper(getApplicationContext());

        clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        contacts.remove(contact);
                        databaseHelper.removeData(contact.id);

                        if (contacts.size() == 0){
                            finish();
                        }

                        break;
                }
            }
        };

        builder = new AlertDialog.Builder(this);

        registerForContextMenu(contactView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, v.getId(), 0, "Copy");
        menu.add(0, v.getId(), 0, "Delete");
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        contact = contacts.get(info.position);

        switch(item.getTitle().toString()){
            case "Copy":
                String message = String.format("name: %s ; phone: %s ; email: %s ; url: %s ; location: %s ;", contact.name, contact.phone, contact.email, contact.url, contact.location);
                clipboard.setPrimaryClip(ClipData.newPlainText("label", message));

                break;
            case "Delete":
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                                                   .setNegativeButton("No", dialogClickListener)
                                                   .show();
                break;
        }
        return true;
    }
}
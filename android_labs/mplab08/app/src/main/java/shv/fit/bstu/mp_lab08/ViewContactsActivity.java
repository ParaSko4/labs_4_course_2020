package shv.fit.bstu.mp_lab08;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import shv.fit.bstu.mp_lab08.fragments.ViewContact;
import shv.fit.bstu.mp_lab08.fragments.ViewContactsList;
import shv.fit.bstu.mp_lab08.models.ContactModel;

public class ViewContactsActivity extends AppCompatActivity implements ViewContactsList.OnFragmentSendDataListener {
    private ViewContact viewContact;
    private ViewContactsList viewContactsList;

    private ContactModel contact;

    private ScrollView scroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scroll = findViewById(R.id.scrollList);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (viewContactsList != null){
            fragmentManager.beginTransaction().remove(viewContactsList).commit();
        }
        if (viewContact != null){
            fragmentManager.beginTransaction().remove(viewContact).commit();
        }

        viewContactsList = new ViewContactsList();
        viewContact = new ViewContact();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            if (contact != null){
                viewContact.setSelectedData(contact);
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_contacts_list_container, viewContactsList, "ViewContactsList")
                        .add(R.id.fragment_contact_container, viewContact, "ViewContact")
                        .commit();
            }else{
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_contacts_list_container, viewContactsList, "ViewContactsList")
                        .commit();
            }
        }else{
//            if (contact != null){
//                viewContact.setSelectedData(contact);
//                fragmentManager.beginTransaction()
//                        .add(R.id.fragment_container_view, viewContactsList, "ViewContactsList")
//                        .commit();
//                fragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container_view, viewContact, "ViewContact")
//                        .addToBackStack("ViewContactsList")
//                        .commit();
//            }else{
//                fragmentManager.beginTransaction()
//                        .add(R.id.fragment_container_view, viewContactsList, "ViewContactsList")
//                        .commit();
//            }

            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container_view, viewContactsList, "ViewContactsList")
                    .commit();
        }
    }

    @Override
    public void onSendData(ContactModel data) {
        contact = data;
        viewContact = new ViewContact();
        viewContact.setSelectedData(data);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_contact_container, viewContact)
                    .commit();
        }else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_view, viewContact)
                    .addToBackStack("ViewContactsList")
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        this.getMenuInflater().inflate(R.menu.contacts_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.New:
                Intent intent = new Intent(this, ContactEditorActivity.class);
                intent.putExtra("NewValue", true);
                startActivity(intent);
                return true;
            case R.id.Up:
                scroll.fullScroll(ScrollView.FOCUS_UP);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        contact = (ContactModel) savedInstanceState.getSerializable("contact");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("contact", contact);
        super.onSaveInstanceState(outState);
    }
}
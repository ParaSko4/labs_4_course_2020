package shv.fit.bstu.mp_lab08.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;

import shv.fit.bstu.mp_lab08.ContactEditorActivity;
import shv.fit.bstu.mp_lab08.R;
import shv.fit.bstu.mp_lab08.ViewSelectedContactActivity;
import shv.fit.bstu.mp_lab08.adapters.ContactListAdapter;
import shv.fit.bstu.mp_lab08.data.DatabaseHelper;
import shv.fit.bstu.mp_lab08.models.ContactModel;

public class ViewContactsList extends Fragment {
    public interface OnFragmentSendDataListener {
        void onSendData(ContactModel data);
    }
    private OnFragmentSendDataListener fragmentSendDataListener;

    private DatabaseHelper databaseHelper;

    public ArrayList<ContactModel> contacts;
    public ArrayList<ContactModel> dbContacts;
    public ArrayList<ContactModel> selectedContacts;

    private ArrayList<Integer> selectedIndex;
    private Menu menu;

    private ListView contactView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_contacts_list, container, false);

        databaseHelper = new DatabaseHelper(view.getContext());
        databaseHelper.create_db();

        selectedContacts = new ArrayList<>();
        selectedIndex = new ArrayList<>();

        dbContacts = databaseHelper.getAllUsers();
        contacts = new ArrayList<>(dbContacts);

        contactView = view.findViewById(R.id.ListContacts);
        contactView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                fragmentSendDataListener.onSendData(contacts.get(i));
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
                        Intent intent = new Intent(view.getContext(), ViewSelectedContactActivity.class);
                        intent.putExtra("ViewContacts", selectedContacts);
                        startActivity(intent);

                        selectedContacts = new ArrayList<>();
                        mode.finish();

                        break;
                    case "Delete":
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        for (ContactModel model: selectedContacts){
                                            contacts.remove(model);
                                            databaseHelper.removeData(model.id);
                                        }

                                        selectedContacts = new ArrayList<>();

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

                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
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

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            setAdapter();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setAdapter() throws ExecutionException, InterruptedException {
        dbContacts = databaseHelper.getAllUsers();
        contacts = new ArrayList<>(dbContacts);

        contacts.sort(new Comparator<ContactModel>() {
            @Override
            public int compare(ContactModel o1, ContactModel o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        contactView.setAdapter(new ContactListAdapter(this.getContext(), contacts, selectedIndex));

        if (selectedIndex.size() != 0){
            for (int i = 0; i < selectedIndex.size(); i++){
                if(contacts.size() <= selectedIndex.get(i)) {
                    contactView.setItemChecked(selectedIndex.get(i), true);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putIntegerArrayList("selectedIndex", selectedIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@NonNull Bundle fragment_container_view) {
        if (fragment_container_view != null){
            selectedIndex = fragment_container_view.getIntegerArrayList("selectedIndex");
        }
        super.onViewStateRestored(fragment_container_view);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        this.menu = menu;
//        this.getActivity().getMenuInflater().inflate(R.menu.contacts_menu, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.New:
//                Intent intent = new Intent(this.getContext(), ContactEditorActivity.class);
//                intent.putExtra("NewValue", true);
//                startActivity(intent);
//                return true;
//            case R.id.Up:
//                Log.i("todo", "Up");
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        menu.clear();
//        super.onDestroy();
//    }
}
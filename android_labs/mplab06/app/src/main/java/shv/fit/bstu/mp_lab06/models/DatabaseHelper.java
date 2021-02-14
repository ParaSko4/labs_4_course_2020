package shv.fit.bstu.mp_lab06.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatabaseHelper {
    private DatabaseReference contacts;

    public DatabaseHelper(String key) {
        contacts = FirebaseDatabase.getInstance().getReference().child("users").child(key.split("@")[0]).child("contacts");
    }

    public void setListener(ValueEventListener listener){
        contacts.addValueEventListener(listener);
    }

    public void addOrUpdateData(ContactModel data){
        contacts.child(data.phone).setValue(data);
    }

    public void removeData(String phone){
        contacts.child(phone).removeValue();
    }
}
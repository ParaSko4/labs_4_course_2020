package shv.fit.bstu.mp_lab04.models;

import java.io.Serializable;

public class ContactModel implements Serializable {
    public int id;
    public String name;
    public String email;
    public String location;
    public String phone;
    public String url;

    public ContactModel(){

    }

    public ContactModel(int id, String name, String email, String location, String phone, String url){
        this.id = id;
        this.name = name;
        this.email = email;
        this.location = location;
        this.phone = phone;
        this.url = url;
    }
}

package shv.fit.bstu.mp_lab06.models;

import java.io.Serializable;

public class ContactModel implements Serializable {
    public String name;
    public String email;
    public String location;
    public String phone;
    public String url;
    public String cimg;

    public ContactModel(){

    }

    public ContactModel(String name, String email, String location, String phone, String url, String cimg){
        this.name = name;
        this.email = email;
        this.location = location;
        this.phone = phone;
        this.url = url;
        this.cimg = cimg;
    }
}

package shv.fit.bstu.mp_lab09.models;

import java.io.Serializable;

public class ContactModal implements Serializable {
    public int id;
    public String name;
    public String email;
    public String location;
    public String phone;
    public String url;
    public String cimg;

    public ContactModal(){

    }

    public ContactModal(int id, String name, String email, String location, String phone, String url, String cimg){
        this.id = id;
        this.name = name;
        this.email = email;
        this.location = location;
        this.phone = phone;
        this.url = url;
        this.cimg = cimg;
    }
}

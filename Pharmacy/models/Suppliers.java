
package models;

public class Suppliers {
    private int id;
    private String name;
    private String description;
    private String address;
    private String telephone;
    private String email;
    private String city;
    private String created;
    private String update;

    public Suppliers() {
    }

    public Suppliers(int id, String name, String description, String address, String telephone, String email, String city, String created, String update) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.city = city;
        this.created = created;
        this.update = update;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }
}

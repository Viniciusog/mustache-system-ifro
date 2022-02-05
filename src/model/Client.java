
package model;

public class Client {

    private Person person;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return getPerson().getName(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

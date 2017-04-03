package it.polito.cowapplication;

/**
 * Created by Utente on 03/04/2017.
 */

public class ExpenseItem {

    private String name;
    private String value; //double

    public ExpenseItem(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}

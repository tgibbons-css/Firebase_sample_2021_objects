package css.firebase_sample_2021.data_model;

import java.sql.Date;


public class Item {
    String itemDescription;
    String itemUser;

    // no-argument constructor required by Firebase
    public Item() {
        this.itemDescription = "Default Description";
        this.itemUser = "Default User";
    }

    public Item(String itemDescription) {

        this.itemDescription = itemDescription;
        this.itemUser = "Default User";
    }

    public Item(String itemDescription, String itemUser) {
        this.itemDescription = itemDescription;
        this.itemUser = itemUser;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemUser() {
        return itemUser;
    }

    public void setItemUser(String itemUser) {
        this.itemUser = itemUser;
    }


}

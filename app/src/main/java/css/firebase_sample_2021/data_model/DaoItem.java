package css.firebase_sample_2021.data_model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import css.firebase_sample_2021.ViewModelItem;
import css.firebase_sample_2021.data_model.Item;

public class DaoItem {

    // variables for the Firebase database

    private DatabaseReference myDbRef;
    ViewModelItem viewModelItem;

    public DaoItem(ViewModelItem viewModelItem) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myDbRef = database.getReference("ITEM_OBJECTS");
        this.viewModelItem = viewModelItem;
        setupFirebaseUpdates();
    }

    public void add(Item newItem) {
        Log.d("CIS 3334", "DaoItem add(item) ");
        myDbRef.push().setValue(newItem);
    }

    private void setupFirebaseUpdates() {
        // Read from the database
        myDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("CIS 3334", "onDataChange ");
                viewModelItem.clearItems();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                // Loop through the items, each is contained in its own DataSnapshot
                for(DataSnapshot itemSnapshot : dataSnapshot.getChildren()){
                    Item item = itemSnapshot.getValue(Item.class);
                    Log.d("CIS 3334", "Item is: " + item.getItemDescription());
                    viewModelItem.addItemToUI(item);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d("CIS 3334", "Failed to read value.", error.toException());
            }
        });
    }
}

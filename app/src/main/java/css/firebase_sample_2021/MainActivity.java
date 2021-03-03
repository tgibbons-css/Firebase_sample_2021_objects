package css.firebase_sample_2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editTextItem;
    Button buttonPost;
    RecyclerView recyclerViewItems;
    ItemRecycleViewAdapter iItemRecycleViewAdapter;

    ItemViewModel itemViewModel;

    // variables for the Firebase database
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup ViewModel
        // May need to add the following to the Module build.gradle file's dependencies section
        // implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        // setup firebase variables
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("ITEM_OBJECTS");

        editTextItem = findViewById(R.id.editTextItem);
        setupButtonPost();
        setupFirebaseUpdates();
        setupItemRecycleView();

    }

    private void setupFirebaseUpdates() {
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("CIS 3334", "onDataChange ");
                itemViewModel.clearItems();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                // Loop through the items, each is contained in its own DataSnapshot
                for(DataSnapshot itemSnapshot : dataSnapshot.getChildren()){
                    Item item = itemSnapshot.getValue(Item.class);
                    Log.d("CIS 3334", "Item is: " + item.getItemDescription());
                    itemViewModel.addItem(item);
                }

                iItemRecycleViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d("CIS 3334", "Failed to read value.", error.toException());
            }
        });
    }

    private void setupButtonPost() {
        buttonPost = findViewById(R.id.buttonPost);
        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
                Log.d("CIS 3334", "Updating the firebase data");
                String itemString = editTextItem.getText().toString();
                //myRef.setValue("Hello, World!");
                //myRef.setValue(item);
                //myRef.child("CIS3334_ITEMS").push().setValue(item);
                Item newItem = new Item(itemString);
                myRef.push().setValue(newItem);

            }
        });
    }

    private void setupItemRecycleView() {
        recyclerViewItems = (RecyclerView) findViewById(R.id.recycleViewItems);
        iItemRecycleViewAdapter = new ItemRecycleViewAdapter(itemViewModel);
        recyclerViewItems.setAdapter(iItemRecycleViewAdapter);
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
    }


}
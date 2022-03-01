package css.firebase_sample_2021;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import css.firebase_sample_2021.data_model.Item;
import css.firebase_sample_2021.ui_views.RecycleViewAdapter;

public class MainActivity extends AppCompatActivity {

    EditText editTextItem;
    Button buttonPost;
    RecyclerView recyclerViewItems;
    RecycleViewAdapter iItemRecycleViewAdapter;
    ViewModelItem viewModelItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup ViewModel
        // May need to add the following to the Module build.gradle file's dependencies section
        // implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
        viewModelItem = new ViewModelProvider(this).get(ViewModelItem.class);

        editTextItem = findViewById(R.id.editTextItem);
        setupButtonPost();
        setupItemRecycleView();
        setupViewModelObserver();
    }

    private void setupViewModelObserver() {
        // Create the observer which updates the UI.
        final Observer<String> statusObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newStatus) {
                // Update the UI, in this case, a TextView.
                iItemRecycleViewAdapter.notifyDataSetChanged();
            }
        };
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModelItem.getNewItems().observe(this, statusObserver);
    }


    private void setupButtonPost() {
        buttonPost = findViewById(R.id.buttonPost);
        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
                Log.d("CIS 3334", "Updating the firebase data");
                String itemString = editTextItem.getText().toString();
                Item newItem = new Item(itemString);
                viewModelItem.addItemToFirebase(newItem);
                //myRef.push().setValue(newItem);
            }
        });
    }

    private void setupItemRecycleView() {
        recyclerViewItems = (RecyclerView) findViewById(R.id.recycleViewItems);
        iItemRecycleViewAdapter = new RecycleViewAdapter(viewModelItem);
        recyclerViewItems.setAdapter(iItemRecycleViewAdapter);
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
    }


}
package css.firebase_sample_2021;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import css.firebase_sample_2021.data_model.DaoItem;
import css.firebase_sample_2021.data_model.Item;

public class ViewModelItem extends AndroidViewModel {

    ArrayList<Item> itemList;
    DaoItem daoItem;

    // Update this to notify the MainActivity that new data has arrived
    private MutableLiveData<String> newItems;
    // create singleton for newItems
    public MutableLiveData<String> getNewItems() {
        if (newItems == null) {
            newItems = new MutableLiveData<String>();
        }
        return newItems;
    }

    public ViewModelItem(@NonNull Application application) {
        super(application);
        itemList = new ArrayList<>();
        daoItem = new DaoItem(this);
    }

    public void clearItems() {

        itemList.clear();

        // notify the main activity that new data has arrived
        newItems.postValue("Data has been cleared");
    }

    public void addItemToUI(Item item) {
        // add item to local list
        itemList.add(item);
        // notify the main activity that new data has arrived
        newItems.postValue("New data has arrived");

    }

    public void addItemToFirebase(Item item) {
        // add item to Firebase database
        daoItem.add(item);
        addItemToUI(item);
    }

    public Item getItem(Integer position) {
        return itemList.get(position);
    }

    public Integer getNumberOfItems() {
        return itemList.size();
    }
}

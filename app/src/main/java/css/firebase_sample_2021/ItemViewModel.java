package css.firebase_sample_2021;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;

public class ItemViewModel extends AndroidViewModel {

    ArrayList<Item> itemList;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        itemList = new ArrayList<>();
    }

    public void clearItems() {
        itemList.clear();
    }
    public void addItem(Item item) {
        itemList.add(item);
    }

    public Item getItem(Integer position) {
        return itemList.get(position);
    }

    public Integer getNumberOfItems() {
        return itemList.size();
    }
}

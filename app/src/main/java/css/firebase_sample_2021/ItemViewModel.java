package css.firebase_sample_2021;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;

public class ItemViewModel extends AndroidViewModel {

    ArrayList<String> itemList;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        itemList = new ArrayList<>();
    }

    public void clearItems() {
        itemList.clear();
    }
    public void addItem(String item) {
        itemList.add(item);
    }

    public String getItem(Integer position) {
        return itemList.get(position);
    }

    public Integer getNumberOfItems() {
        return itemList.size();
    }
}

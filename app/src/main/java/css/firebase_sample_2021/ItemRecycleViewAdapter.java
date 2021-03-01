package css.firebase_sample_2021;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemRecycleViewAdapter extends RecyclerView.Adapter<ItemViewHolder>  {

    ItemViewModel itemViewHolder;

    ItemRecycleViewAdapter (ItemViewModel itemViewHolder) {
        //this.application = application;
        this.itemViewHolder = itemViewHolder;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String item = itemViewHolder.getItem(position);
        holder.textViewRowItem.setText(item);
    }

    @Override
    public int getItemCount() {
        return itemViewHolder.getNumberOfItems();
    }
}

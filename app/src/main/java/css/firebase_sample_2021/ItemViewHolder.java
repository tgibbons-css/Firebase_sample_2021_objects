package css.firebase_sample_2021;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder{

    TextView textViewRowItem;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewRowItem = itemView.findViewById(R.id.textViewRowItem);
    }
}

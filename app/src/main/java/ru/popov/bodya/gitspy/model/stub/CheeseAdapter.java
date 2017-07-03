package ru.popov.bodya.gitspy.model.stub;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.popov.bodya.gitspy.R;

public class CheeseAdapter extends RecyclerView.Adapter<CheeseAdapter.Holder> {

    private List<String> filteredCheeses;
    private List<String> defaultCheeses;

    public CheeseAdapter() {
        defaultCheeses = Arrays.asList(Cheeses.cheeseStrings);
        filteredCheeses = defaultCheeses;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.cheeseNameTextView.setText(filteredCheeses.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredCheeses.size();
    }

    public void filter(String query) {
        filteredCheeses = new ArrayList<>();
        for (String cheese : defaultCheeses) {
            if (cheese.toLowerCase().contains(query.toLowerCase())) {
                filteredCheeses.add(cheese);
            }
        }
        notifyDataSetChanged();

    }

    class Holder extends RecyclerView.ViewHolder {

        private TextView cheeseNameTextView;

        Holder(View itemView) {
            super(itemView);
            cheeseNameTextView = (TextView) itemView.findViewById(R.id.cheese_name);
        }
    }
}
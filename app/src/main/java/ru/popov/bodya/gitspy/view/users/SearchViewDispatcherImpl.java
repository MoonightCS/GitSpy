package ru.popov.bodya.gitspy.view.users;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.popov.bodya.gitspy.R;

public class SearchViewDispatcherImpl implements SearchViewDispatcher {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TextInputEditText searchEditText;


    @Nullable
    @Override
    public View createView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_user_activity_layout, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        recyclerView = view.findViewById(R.id.recycler_view);
        searchEditText = view.findViewById(R.id.search_edit_text);
        return view;
    }

    @Override
    public void destroyView() {
        toolbar = null;
        searchEditText = null;
        recyclerView = null;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }
}

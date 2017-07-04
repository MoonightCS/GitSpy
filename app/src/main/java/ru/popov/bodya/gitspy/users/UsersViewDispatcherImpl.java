package ru.popov.bodya.gitspy.users;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

import ru.popov.bodya.gitspy.R;
import ru.popov.bodya.gitspy.entity.User;
import ru.popov.bodya.gitspy.model.stub.CheeseAdapter;

public class UsersViewDispatcherImpl implements UsersViewDispatcher, SwipeRefreshLayout.OnRefreshListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextInputEditText textInputEditText;

    private WeakReference<DispatcherEventListener> dispatcherEventListenerWeakRef;
    private WeakReference<Context> contextWeakRef;
    private CheeseAdapter cheeseAdapter;

    public UsersViewDispatcherImpl(DispatcherEventListener dispatcherEventListener, Context context) {
        this.dispatcherEventListenerWeakRef = new WeakReference<>(dispatcherEventListener);
        this.contextWeakRef = new WeakReference<>(context);
    }


    @Override
    public void setUsers(List<User> rates) {

    }

    public void showRefresh(boolean show) {
        swipeRefreshLayout.setRefreshing(show);
    }

    @Override
    public void filterAdapter(String query) {
        cheeseAdapter.filter(query);
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    @Override
    public NavigationView getNavigationView() {
        return navigationView;
    }

    @Nullable
    @Override
    public View createView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.users_activity_layout, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.nav_view);
        recyclerView = view.findViewById(R.id.recycler_view);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        textInputEditText = view.findViewById(R.id.filter_edit_text);

        configRecyclerView();
        addListenerToSearchEditText();
        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }


    @Override
    public void destroyView() {
        drawerLayout = null;
        navigationView = null;
        recyclerView = null;
        swipeRefreshLayout = null;
        textInputEditText = null;
    }

    @Override
    public void onRefresh() {
        Context context = contextWeakRef.get();
        if (context != null) {
            Toast.makeText(context, "Refresh data", Toast.LENGTH_LONG).show();
        }
        DispatcherEventListener dispatcherEventListener = dispatcherEventListenerWeakRef.get();
        if (dispatcherEventListener != null) {
            dispatcherEventListener.onRefresh(this);
        }
    }

    private void addListenerToSearchEditText() {
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                DispatcherEventListener dispatcherEventListener = dispatcherEventListenerWeakRef.get();
                if (dispatcherEventListener != null) {
                    dispatcherEventListener.onFilterModified(UsersViewDispatcherImpl.this, s.toString());
                }
            }
        });
    }


    private void configRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        // fill stub data for testing
        cheeseAdapter = new CheeseAdapter();
        recyclerView.setAdapter(cheeseAdapter);
    }

}

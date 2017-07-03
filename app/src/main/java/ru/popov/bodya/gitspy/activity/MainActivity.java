package ru.popov.bodya.gitspy.activity;

import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import ru.popov.bodya.gitspy.R;
import ru.popov.bodya.gitspy.model.stub.CheeseAdapter;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.filter_edit_text)
    TextInputEditText textInputEditText;

    private CheeseAdapter cheeseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setUpNavViewAndToggle();

        swipeRefreshLayout.setOnRefreshListener(this);
        configRecyclerVIew();
        addListenerToSearchEditText();

    }

    private void configRecyclerVIew() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        cheeseAdapter = new CheeseAdapter();
        recyclerView.setAdapter(cheeseAdapter);
    }

    private void setUpNavViewAndToggle() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        setupDrawerContent(navigationView);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    menuItem.setChecked(true);
                    drawerLayout.closeDrawers();
                    return true;
                });
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
                cheeseAdapter.filter(s.toString());
            }
        });
    }


    @Override
    public void onRefresh() {
        Log.e(TAG, "onRefresh");
        Toast.makeText(this, "Refresh data", Toast.LENGTH_LONG).show();
        new Handler().postDelayed(() -> swipeRefreshLayout.setRefreshing(false), 3000);
    }
}

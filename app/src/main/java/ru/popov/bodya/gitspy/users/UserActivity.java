package ru.popov.bodya.gitspy.users;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;

import ru.popov.bodya.gitspy.R;

public class UserActivity extends AppCompatActivity implements UsersViewDispatcher.DispatcherEventListener {

    private static final String TAG = UserActivity.class.getSimpleName();

    private UsersViewDispatcher usersViewDispatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usersViewDispatcher = new UsersViewDispatcherImpl(this, this);
        setContentView(usersViewDispatcher.createView(LayoutInflater.from(this), null, savedInstanceState));
        setSupportActionBar(usersViewDispatcher.getToolbar());
        setUpNavViewAndToggle();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        usersViewDispatcher.destroyView();
    }

    @Override
    public void onRefresh(UsersViewDispatcher sender) {
        Log.e(TAG, "onRefresh");
        new Handler().postDelayed(() -> sender.showRefresh(false), 3000);
    }

    @Override
    public void onFilterModified(UsersViewDispatcher sender, String modifiedText) {
        sender.filterAdapter(modifiedText);
    }


    private void setUpNavViewAndToggle() {
        DrawerLayout drawerLayout = usersViewDispatcher.getDrawerLayout();
        Toolbar toolbar = usersViewDispatcher.getToolbar();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        setupDrawerContent(drawerLayout);
    }

    private void setupDrawerContent(DrawerLayout drawerLayout) {
        NavigationView navigationView = usersViewDispatcher.getNavigationView();
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    menuItem.setChecked(true);
                    drawerLayout.closeDrawers();
                    return true;
                });
    }

}

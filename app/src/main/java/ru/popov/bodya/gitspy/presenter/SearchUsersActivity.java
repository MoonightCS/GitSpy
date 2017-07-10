package ru.popov.bodya.gitspy.presenter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import ru.popov.bodya.gitspy.view.users.SearchViewDispatcher;
import ru.popov.bodya.gitspy.view.users.SearchViewDispatcherImpl;

public class SearchUsersActivity extends AppCompatActivity {

    private static final String TAG = SearchUsersActivity.class.getSimpleName();

    private SearchViewDispatcher searchViewDispatcher;

    public static Intent newIntent(Context context) {
        return new Intent(context, SearchUsersActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchViewDispatcher = new SearchViewDispatcherImpl();
        setContentView(searchViewDispatcher.createView(LayoutInflater.from(this), null, savedInstanceState));
        setSupportActionBar(searchViewDispatcher.getToolbar());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchViewDispatcher.destroyView();
    }
}

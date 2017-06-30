package ru.popov.bodya.gitspy.activity;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.popov.bodya.gitspy.R;

public class BaseActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    protected void bindViews() {
        ButterKnife.bind(this);
        bindToolbar();
    }

    protected void bindToolbar() {
        setSupportActionBar(toolbar);
    }
}

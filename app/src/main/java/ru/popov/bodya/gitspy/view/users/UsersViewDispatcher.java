package ru.popov.bodya.gitspy.view.users;


import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import java.util.List;

import ru.popov.bodya.gitspy.view.core.BaseViewDispatcher;
import ru.popov.bodya.gitspy.model.users.entity.User;

public interface UsersViewDispatcher extends BaseViewDispatcher {

    void setUsers(List<User> rates);

    void showRefresh(boolean refresh);

    void filterAdapter(String query);

    Toolbar getToolbar();

    DrawerLayout getDrawerLayout();

    NavigationView getNavigationView();

    interface DispatcherEventListener {
        void onRefresh(UsersViewDispatcher sender);
        void onFilterModified(UsersViewDispatcher sender, String modifiedText);
    }

}

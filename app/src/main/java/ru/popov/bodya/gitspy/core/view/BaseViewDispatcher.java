package ru.popov.bodya.gitspy.core.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface BaseViewDispatcher {

    @Nullable
    View createView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState);

    void destroyView();

}

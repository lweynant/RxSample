package com.lweynant.rxsample;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */

public class BaseFragment extends Fragment {


    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onDestroy() {
        Timber.d("onDestroy");
        super.onDestroy();
        RefWatcher refWatcher = MyApp.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}

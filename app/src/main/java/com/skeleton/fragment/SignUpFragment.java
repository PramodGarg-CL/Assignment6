package com.skeleton.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skeleton.R;

/**
 * Registered User -  Sign In
 */

public class SignInFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        init(view);
        return view;
    }

    /**
     * Initializes the views and variables
     *
     * @param view : rootView of the fragment
     */
    private void init(final View view) {

    }
}

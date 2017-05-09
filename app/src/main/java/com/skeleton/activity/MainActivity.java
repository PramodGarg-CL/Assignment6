package com.skeleton.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.skeleton.R;
import com.skeleton.fragment.ViewPagerFragment;

/**
 * MainActivity contains {@link ViewPagerFragment}
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * Intializes variables and views
     */
    private void init() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_fl, ViewPagerFragment.getInstance());
        transaction.commit();
    }
}

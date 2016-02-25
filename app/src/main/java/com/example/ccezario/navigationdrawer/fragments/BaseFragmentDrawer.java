package com.example.ccezario.navigationdrawer.fragments;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ccezario.navigationdrawer.MainActivity;
import com.example.ccezario.navigationdrawer.R;

import butterknife.ButterKnife;

public abstract class BaseFragmentDrawer extends Fragment {
    Toolbar mToolbar;

    @LayoutRes
    protected abstract int getLayout();

    public MainActivity getDrawerActivity() {
        return (MainActivity) super.getActivity();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view);
    }

    protected void setToolbar(View view) {
        if (hasCustomToolbar()) {
            this.mToolbar = ButterKnife.findById(view, getToolbarId());
            this.mToolbar.setTitle(getTitle());
            this.mToolbar.setNavigationIcon(R.mipmap.ic_ab_drawer);
        }
    }

    @IdRes
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    public boolean hasCustomToolbar() {
        return false;
    }

    @StringRes
    protected int getTitle() {
        return R.string.app_name;
    }
}

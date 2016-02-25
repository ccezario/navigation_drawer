package com.example.ccezario.navigationdrawer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ccezario.navigationdrawer.fragments.MenuFragment;

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog waitingDialog;
    private Toast toast;
    private DrawerLayout dlDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    public TextView tvTitleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.toast = Toast.makeText(getActivity(), "", Toast.LENGTH_LONG);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on
        // position
        Fragment fragment = null;

        Class fragmentClass;
        /*switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                fragmentClass = FirstFragment.class;
                break;
            case R.id.nav_second_fragment:
                fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_third_fragment:
                fragmentClass = ThirdFragment.class;
                break;
            default:
                fragmentClass = FirstFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item, update the title, and close the drawer
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        dlDrawer.closeDrawers();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, dlDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    public void showToast(String text, int duration) {
        runOnUiThread(new ShowToast(text, duration));
    }

    public void showToast(String text) {
        runOnUiThread(new ShowFixedToast(text));
    }

    public void showToast(int textRid) {
        try {
            showToast(getString(textRid));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTitleText(String text) {
        try {
            if (this.tvTitleText == null) {
               // this.tvTitleText = (TextView) findViewById(R.id.tvTitleText);
            }
            this.tvTitleText.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initMenu(boolean mode) {
        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find our drawer view
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Tie DrawerLayout events to the ActionBarToggle
        dlDrawer.setDrawerListener(drawerToggle);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public Context getContext() {
        return this;
    }

    public BaseActivity getActivity() {
        return this;
    }

    public void showDialog() {
        showDialog("", false);
    }

    public void showDialog(String msg) {
        showDialog( msg, true );
    }

    public void showDialog(String msg, boolean isLight) {

        if ( waitingDialog == null ){
            waitingDialog = new ProgressDialog( this );
            waitingDialog.setIndeterminate( true );
            waitingDialog.setCancelable( false );
        }

        if ( !waitingDialog.isShowing() ){
            waitingDialog.setMessage( msg );
            waitingDialog.show();
        }
    }

    public void setMessage(String msg) {
        if ( waitingDialog != null ){
            waitingDialog.setMessage(msg);
        }
    }

    public void dismissDialog() {

        if ( waitingDialog != null ){
            waitingDialog.dismiss();
        }
    }

    class ShowToast implements Runnable {
        final int duration;
        final String text;

        ShowToast(String str, int i) {
            this.text = str;
            this.duration = i;
        }

        public void run() {
            BaseActivity.this.toast.setText(this.text);
            BaseActivity.this.toast.setDuration(this.duration);
            BaseActivity.this.toast.show();
        }
    }

    class ShowFixedToast implements Runnable {
        final String text;

        ShowFixedToast(String str) {
            this.text = str;
        }

        public void run() {
            BaseActivity.this.toast.setText(this.text);
            BaseActivity.this
                    .toast.setDuration(this.text.length() > 40 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
            BaseActivity.this.toast.show();
        }
    }
}

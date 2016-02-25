package com.example.ccezario.navigationdrawer.fragments;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ccezario.navigationdrawer.BaseActivity;
import com.example.ccezario.navigationdrawer.MainActivity;
import com.example.ccezario.navigationdrawer.R;
import com.example.ccezario.navigationdrawer.adapters.MenuAdapter;

import java.util.ArrayList;

public class MenuFragment extends BaseFragmentDrawer {

    MenuAdapter adapter;
    TextView drawerUserName;
    ImageView imgMenuLogout;
    ImageView imgMenuSetting;
    ListView lvMenu;
    AdapterView.OnItemClickListener mItemClickListener;

    public static final String NOTIFICATION_CNT_UPDATED = "notification_cnt_updated";
    public static final String PROFILE_UPDATED = "profile_updated";

    public MenuFragment() {
        this.mItemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int option, long arg3) {
                Intent intent;
                switch (option) {
                    case 0:
                        hideMenu(true);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        hideMenu(true);
                        break;
                    case 5:
                        hideMenu(true);
                        break;
                }
            }
        };
    }

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NOTIFICATION_CNT_UPDATED);
        intentFilter.addAction(PROFILE_UPDATED);
        super.onCreate(savedInstanceState);
    }

    protected int getLayout() {
        return R.menu.drawer_view;
    }

    private ArrayList<MenuItem> getMenuItem() {
        ArrayList<MenuItem> menuList = new ArrayList();
        menuList.add(new MenuItem(getString(R.string.menu_home), R.mipmap.menu_home));
        menuList.add(new MenuItem(getString(R.string.menu_cal), R.mipmap.menu_home));
        menuList.add(new MenuItem(getString(R.string.menu_notify), R.mipmap.menu_home, true));
        menuList.add(new MenuItem(getString(R.string.menu_payments), R.mipmap.menu_home));
        menuList.add(new MenuItem(getString(R.string.menu_courses), R.mipmap.menu_home));
        menuList.add(new MenuItem(getString(R.string.menu_clients), R.mipmap.menu_home));
        return menuList;
    }

    private void hideMenu(boolean animate) {
        try {
            // TODO ((BaseActivity) getActivity()).getSlidingMenu().toggle(animate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void finishActivity() {
        if (!(getActivity() instanceof MainActivity)) {
            getActivity().finish();
        }
    }

    public class MenuItem {

        public String menuTitle;
        public int  menuIcon;
        public boolean displayCnt;

        public MenuItem (String s, int i) {
            menuIcon = i;
            menuTitle = s;
        }

        public MenuItem (String s, int i, boolean b) {
            menuIcon = i;
            menuTitle = s;
            displayCnt = b;
        }
    }
}

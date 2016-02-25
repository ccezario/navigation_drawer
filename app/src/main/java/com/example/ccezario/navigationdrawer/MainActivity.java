package com.example.ccezario.navigationdrawer;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    public TextView tvTitleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tvTitleText = (TextView) findViewById(R.id.tvTitle);
        this.tvTitleText.setText("home");
        initMenu(true);
    }

}

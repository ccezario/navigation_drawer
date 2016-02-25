package com.example.ccezario.navigationdrawer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ccezario.navigationdrawer.R;
import com.example.ccezario.navigationdrawer.fragments.MenuFragment;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {

    public class ViewHolder{

        ImageView imgMenu;
        TextView tvMenuTitle;
        TextView tvMenuNotifyCnt;
    }

    private ArrayList<MenuFragment.MenuItem> data;
    private LayoutInflater inflater;
    private Context mContext;

    public void addAll( ArrayList b ){

        try {
            this.data.clear();
            this.data.addAll(b);
        } catch (Exception e){
        }

        notifyDataSetChanged();
    }

    public int getCount(){
        return this.data.size();
    }

    @Override
    public Object getItem(int position) {
        return this.data.get( position );
    }

    @Override
    public long getItemId(int position) {
        // TODO método não descompilado pois é a versão free do descompilador
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder v2;
        if ( convertView == null ){

            convertView = inflater.inflate(R.layout.drawer_item, null);
            v2 = new ViewHolder();
            v2.tvMenuNotifyCnt = (TextView) convertView.findViewById(R.id.tvMenuNotifyCnt);
            v2.tvMenuTitle = (TextView) convertView.findViewById(R.id.tvMenuTitle);
            v2.imgMenu = (ImageView)convertView.findViewById(R.id.imgMenu);
            convertView.setTag( v2 );
        } else {
            v2 = (ViewHolder)convertView.getTag();
        }

        try{
            v2.tvMenuTitle.setText(this.data.get(position).menuTitle);
            v2.imgMenu.setImageResource( this.data.get( position ).menuIcon );

            int v0 = 0;
            if (this.data.get( position ).displayCnt && v0 > 0 ){
                v2.tvMenuNotifyCnt.setText( "" + v0 );
                v2.tvMenuNotifyCnt.setVisibility( View.VISIBLE );
            } else {
                v2.tvMenuNotifyCnt.setVisibility( View.GONE );
            }
        }catch (Exception e){

        }

        return convertView;
    }

}

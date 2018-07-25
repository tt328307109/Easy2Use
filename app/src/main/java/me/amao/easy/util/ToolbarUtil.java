package me.amao.easy.util;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import me.amao.easy.R;


/**
 * Created by zhang on 2018/3/15.
 * Supported By 甜瓜移动.
 * Official Website: www.melonmobile.cn.
 *
 * @author zhang
 */
public class ToolbarUtil {

    private ToolbarUtil() {
    }

    public static void setToolbar(AppCompatActivity activity, String title, int toolbarColor , boolean backVisiable) {
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        if (toolbarColor!=0) {
            toolbar.setBackgroundColor(toolbarColor);
        }
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar!=null){
            if(backVisiable){
                actionBar.setDisplayHomeAsUpEnabled(true);
            }else {
                actionBar.setDisplayHomeAsUpEnabled(false);
            }
//
            actionBar.setDisplayShowTitleEnabled(false);
        }
        TextView t = toolbar.findViewById(R.id.title);
        t.setText(title);
    }
}

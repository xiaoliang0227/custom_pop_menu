package com.zyl.popmenu;

import android.app.Activity;
import android.os.Bundle;


/**
 * @author Jason Zhao
 * @email xiaoliang0227@163.com
 */
public class MainActivity extends Activity {

  private static final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ((CustomPopMenu) findViewById(R.id.custom_pop_menu)).setDismissClickOutofSide(true);
  }
}

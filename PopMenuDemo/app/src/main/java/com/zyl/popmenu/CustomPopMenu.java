package com.zyl.popmenu;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by JasonZhao on 7/18/14.
 */
public class CustomPopMenu extends LinearLayout {

  private static final String TAG = "CustomPopMenu";

  private static final int ANIMATION_DURATION = 300;

  private Context context;

  private GridView actionsGrid;

  private ListView menuItems;

  private View view;

  private int currentPage = 0;

  private GridActionsAdapter adapter;

  public CustomPopMenu(Context context) {
    super(context);
  }

  public CustomPopMenu(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
    init();
  }

  private void init() {
    initLayout();
    initField();
    initActionsGrid();
  }

  private void initActionsGrid() {
    List<String> tmp = new ArrayList<String>();
    Set<String> keySet = CommonConsts.MATRIX_STATE_ACTIONS.keySet();
    for (String key : keySet) {
      tmp.add(key);
    }
    adapter = new GridActionsAdapter(context);
    adapter.setActionsSet(tmp);
    actionsGrid.setAdapter(adapter);
    actionsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
        Log.d(TAG, "currentPage:" + currentPage + ",i:" + i + ",visable:" + (menuItems.getVisibility() == View.GONE));
        if (menuItems.getVisibility() == View.GONE) {
          showMenuItems(i);
        } else {
          if (currentPage == i) {
            hideMenuItems(-1);
          } else {
            hideMenuItems(i);
          }
        }
      }
    });
  }

  private void hideMenuItems(final int position) {
    AlphaAnimation aa = new AlphaAnimation(1.0f, 0.0f);
    TranslateAnimation ta = new TranslateAnimation(0, 0, 0, actionsGrid.getBottom());
    AnimationSet as = new AnimationSet(true);
    as.addAnimation(aa);
    as.addAnimation(ta);
    as.setDuration(ANIMATION_DURATION);
    as.setInterpolator(context, android.R.anim.linear_interpolator);
    as.setAnimationListener(new Animation.AnimationListener() {
      @Override
      public void onAnimationStart(Animation animation) {

      }

      @Override
      public void onAnimationEnd(Animation animation) {
        menuItems.setVisibility(GONE);
        if (position != -1) {
          showMenuItems(position);
        }
      }

      @Override
      public void onAnimationRepeat(Animation animation) {

      }
    });
    menuItems.startAnimation(as);
  }

  private void showMenuItems(int position) {
    currentPage = position;
    menuItems.setVisibility(VISIBLE);
    final ActionMenusAdapter adapter1 = new ActionMenusAdapter(context);
    adapter1.setActionsSet(CommonConsts.MATRIX_STATE_ACTIONS.get(adapter.getItem(position)));
    menuItems.setAdapter(adapter1);
    menuItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d(TAG, adapter1.getItem(i).toString());
      }
    });
    TranslateAnimation ta = new TranslateAnimation(0, 0, actionsGrid.getBottom(), 0);
    AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
    AnimationSet as = new AnimationSet(true);
    as.addAnimation(ta);
    as.addAnimation(aa);
    as.setDuration(ANIMATION_DURATION);
    as.setInterpolator(context, android.R.anim.linear_interpolator);
    menuItems.startAnimation(as);
  }

  private void initField() {
    actionsGrid = (GridView) view.findViewById(R.id.actions_grid);
  }

  private void initLayout() {
    view = LayoutInflater.from(context).inflate(R.layout.widget_custom_pop_menu, null);
    addView(view);
    actionsGrid = (GridView) view.findViewById(R.id.actions_grid);
    menuItems = (ListView) view.findViewById(R.id.menu_items);
  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB)
  public CustomPopMenu(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }
}

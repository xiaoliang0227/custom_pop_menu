package com.zyl.popmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by JasonZhao on 7/18/14.
 */
public class GridActionsAdapter extends BaseAdapter {

  private Context context;

  private List<String> actionsList;

  public void setActionsSet(List<String> actionsList) {
    this.actionsList = actionsList;
  }

  public GridActionsAdapter(Context context) {
    this.context = context;
  }

  @Override
  public int getCount() {
    return actionsList.size();
  }

  @Override
  public Object getItem(int i) {
    return actionsList.get(i);
  }

  @Override
  public long getItemId(int i) {
    return 0;
  }

  @Override
  public View getView(int i, View view, ViewGroup viewGroup) {
    ViewHolder holder = null;
    if (null == view) {
      holder = new ViewHolder();
      view = LayoutInflater.from(context).inflate(R.layout.action_grid_item, null);
      holder.item = (TextView) view.findViewById(R.id.item);
      view.setTag(holder);
    } else {
      holder = (ViewHolder) view.getTag();
    }
    holder.item.setText(getItem(i).toString());
    return view;
  }

  class ViewHolder {
    TextView item;
  }
}

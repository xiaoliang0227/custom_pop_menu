package com.zyl.popmenu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author JasonZhao
 */
public class CommonConsts {

  public static LinkedHashMap<String, List<String>> MATRIX_STATE_ACTIONS = new LinkedHashMap<String, List<String>>();

  static {
    MATRIX_STATE_ACTIONS.clear();
    List<String> tmpList = new ArrayList<String>();
    tmpList.add("Menu1-Item1");
    tmpList.add("Menu1-Item2");
    MATRIX_STATE_ACTIONS.put("Menu1", tmpList);
    tmpList = new ArrayList<String>();
    tmpList.add("Menu2-Item1");
    tmpList.add("Menu2-Item2");
    tmpList.add("Menu2-Item3");
    MATRIX_STATE_ACTIONS.put("Menu2", tmpList);
    tmpList = new ArrayList<String>();
    tmpList.add("Menu3-Item1");
    tmpList.add("Menu3-Item2");
    tmpList.add("Menu3-Item3");
    MATRIX_STATE_ACTIONS.put("Menu3", tmpList);
  }

}

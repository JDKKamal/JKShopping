package com.jdkgroup.jkshopping.utils;

import android.support.v4.app.Fragment;

import java.util.List;
import java.util.Stack;

public class GlobalClass {
    public static GlobalClass instance;

    public List<Integer> fragmentStack = new Stack<>();

    public Stack<Fragment> stack = new Stack<>();

    public static GlobalClass getInstance() {
        if (instance == null) {
            instance = new GlobalClass();
        }
        return instance;
    }



}

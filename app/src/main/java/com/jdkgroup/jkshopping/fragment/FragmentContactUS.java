package com.jdkgroup.jkshopping.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import  com.jdkgroup.jkshopping.R;
import  com.jdkgroup.jkshopping.base.BaseFragment;
import  com.jdkgroup.jkshopping.general.General;
import  com.jdkgroup.jkshopping.general.GeneralImplement;
import  com.jdkgroup.jkshopping.utils.SaveSharedPrefernces;

public class FragmentContactUS extends BaseFragment {
    private Activity activity;
    private Context context;
    private View view;

    private General general;
    private SaveSharedPrefernces ssp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contact_us, null);

        activity = getActivity();
        context = getActivity();

        general = new GeneralImplement();
        ssp = new SaveSharedPrefernces();

        return view;
    }
}
package com.jdkgroup.jkshopping;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jdkgroup.jkshopping.fragment.FragmentAboutUS;
import com.jdkgroup.jkshopping.fragment.FragmentAddToCart;
import com.jdkgroup.jkshopping.fragment.FragmentChangePassword;
import com.jdkgroup.jkshopping.fragment.FragmentCompany;
import com.jdkgroup.jkshopping.fragment.FragmentFashionProduct;
import com.jdkgroup.jkshopping.fragment.FragmentShoppingItemList;
import com.jdkgroup.jkshopping.general.General;
import com.jdkgroup.jkshopping.general.GeneralImplement;
import com.jdkgroup.jkshopping.utils.AppKeyword;
import com.jdkgroup.jkshopping.utils.GlobalClass;
import com.jdkgroup.jkshopping.utils.SaveSharedPrefernces;
import com.jdkgroup.jkshopping.widget.CustomTypefaceSpan;
import com.jdkgroup.jkshopping.widget.FontTypeface;

import java.util.List;

public class Drawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener {

    private Activity activity;
    public static Toolbar toolBar;

    private General general;
    private GlobalClass globalClass;
    private SaveSharedPrefernces saveSharedPrefernces;

    public NavigationView navigationView;
    private AppCompatTextView apptvUsername, apptvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        activity = this;

        general = new GeneralImplement();
        globalClass = GlobalClass.getInstance();
        saveSharedPrefernces = new SaveSharedPrefernces();

        toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        for (int i = 0; i < toolBar.getChildCount(); i++) {
            View view = toolBar.getChildAt(i);
            if (view instanceof TextView) {
                TextView tv = (TextView) view;
                Typeface titleFont = Typeface.
                        createFromAsset(getApplicationContext().getAssets(), "fonts/sourcesanspro_regular.ttf");
                if (tv.getText().equals(toolBar.getTitle())) {
                    tv.setTypeface(titleFont);
                    break;
                }
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        View navigationViewHeaderView = navigationView.getHeaderView(0);
        apptvUsername = (AppCompatTextView) navigationViewHeaderView.findViewById(R.id.apptvUsername);
        apptvAddress = (AppCompatTextView) navigationViewHeaderView.findViewById(R.id.apptvAddress);

        general.FontAppCompatTextView(apptvUsername, AppKeyword.sourcesanspro_regular);
        general.FontAppCompatTextView(apptvAddress, AppKeyword.sourcesanspro_regular);

        apptvUsername.setText(saveSharedPrefernces.getUsername(activity));

        if (saveSharedPrefernces.getAddress(activity) == null) {
            apptvAddress.setVisibility(View.GONE);
        } else {
            apptvAddress.setText(saveSharedPrefernces.getAddress(activity));
        }

        navigationView.setNavigationItemSelectedListener(this);
        changeTypeface(navigationView);

        general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_Home_3);
    }

    private void changeTypeface(NavigationView navigationView) {
        FontTypeface fontTypeface = new FontTypeface(this);
        Typeface typeface = fontTypeface.getTypefaceAndroid();

        MenuItem item;

        item = navigationView.getMenu().findItem(R.id.nav_home);
        applyFontToItem(item, typeface);

        item = navigationView.getMenu().findItem(R.id.nav_add_to_cart);
        applyFontToItem(item, typeface);

        item = navigationView.getMenu().findItem(R.id.nav_shopping_item_list);
        applyFontToItem(item, typeface);

        item = navigationView.getMenu().findItem(R.id.menu_profile);
        applyFontToItem(item, typeface);

        item = navigationView.getMenu().findItem(R.id.menu_change_password);
        applyFontToItem(item, typeface);

        item = navigationView.getMenu().findItem(R.id.nav_about_us);
        applyFontToItem(item, typeface);

        item = navigationView.getMenu().findItem(R.id.nav_contact_us);
        applyFontToItem(item, typeface);

        item = navigationView.getMenu().findItem(R.id.nav_help);
        applyFontToItem(item, typeface);

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {

            @Override
            public void onBackStackChanged() {
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.containerView);
                if (f != null) {
                    updateTitleAndDrawer(f);
                }
            }
        });
    }

    private void applyFontToItem(MenuItem item, Typeface font) {
        SpannableString mNewTitle = new SpannableString(item.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font, 16), 0,
                mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        item.setTitle(mNewTitle);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // AppKeyword.FLag_FRAGMENT_STACK = true;

           /* if (GeneralImplement.manager.getBackStackEntryCount() <= 1) {
                general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_LOGOUT_0_0);
                return;
            }
            super.onBackPressed();*/

            List<Integer> fragmentStack = globalClass.fragmentStack;
            Gson gson = new Gson();

            if (fragmentStack.isEmpty()) {
                general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_LOGOUT_0);
            }
            if (fragmentStack.size() == 1) {
                general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_LOGOUT_0);
            } else {
                // AppKeyword.FLag_FRAGMENT_STACK = true;

                fragmentStack.remove(fragmentStack.size() - 1);
                int redirect = fragmentStack.get(fragmentStack.size() - 1);

                //TODO BACK REFRESH DATA TO CALL and COMMENT SUPER.onBackPressed();
                general.SwitchPassdataChangeFragment(activity, redirect);

                System.out.println("Tag" + gson.toJson(fragmentStack) + "Get");
                //super.onBackPressed();
            }
        }
    }


    private void updateTitleAndDrawer(Fragment fragment) {
        String fragClassName = fragment.getClass().getName();

        if (fragClassName.equals(FragmentCompany.class.getName())) {
            setTitle("JK Shopping");
            navigationView.getMenu().getItem(0).setChecked(true);
        } else if (fragClassName.equals(FragmentAddToCart.class.getName())) {
            setTitle("Add To Cart");
            navigationView.getMenu().getItem(1).setChecked(true);
        } else if (fragClassName.equals(FragmentShoppingItemList.class.getName())) {
            setTitle("Shopping Item List");
            navigationView.getMenu().getItem(2).setChecked(true);
        } else if (fragClassName.equals(FragmentChangePassword.class.getName())) {
            navigationView.getMenu().getItem(3).setChecked(true);
        } else if (fragClassName.equals(FragmentChangePassword.class.getName())) {
            setTitle("Change Password");
            navigationView.getMenu().getItem(4).setChecked(true);
        } else if (fragClassName.equals(FragmentAboutUS.class.getName())) {
            setTitle("About US");
            navigationView.getMenu().getItem(5).setChecked(true);
        } else if (fragClassName.equals(FragmentFashionProduct.class.getName())) {
            setTitle("JK Fashion");
            navigationView.getMenu().getItem(0).setChecked(true);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        AppKeyword.FLag_FRAGMENT_STACK = false;

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            setTitle("JK Shopping");
            general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_Home_3);
        } else if (id == R.id.nav_add_to_cart) {
            setTitle("Add To Cart");
            general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_ADD_TO_CART_4);
        } else if (id == R.id.nav_shopping_item_list) {
            setTitle("Shopping Item List");
            general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_SHOPPING_ITEM_LIST_5);
        } else if (id == R.id.menu_profile) {
            general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_PROFILE_9);
        } else if (id == R.id.menu_change_password) {
            setTitle("Change Password");
            general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_CHANGE_PASSWORD_6);
        } else if (id == R.id.nav_about_us) {
            setTitle("About US");
            general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_ABOUT_US_7);
        } else if (id == R.id.nav_contact_us) {
            general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_CONTACT_US_8);
        } else if (id == R.id.nav_help) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackStackChanged() {

    }
}

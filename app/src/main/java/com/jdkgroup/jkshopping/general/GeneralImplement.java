package com.jdkgroup.jkshopping.general;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.CharMatcher;
import com.google.common.io.BaseEncoding;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jdkgroup.jkshopping.Drawer;
import com.jdkgroup.jkshopping.R;
import com.jdkgroup.jkshopping.activity.ActivityForgotPassword;
import com.jdkgroup.jkshopping.activity.ActivityLogin;
import com.jdkgroup.jkshopping.activity.ActivityProfile;
import com.jdkgroup.jkshopping.activity.ActivitySignUp;
import com.jdkgroup.jkshopping.dialog.DialogLogout;
import com.jdkgroup.jkshopping.fragment.FragmentAboutUS;
import com.jdkgroup.jkshopping.fragment.FragmentAddToCart;
import com.jdkgroup.jkshopping.fragment.FragmentChangePassword;
import com.jdkgroup.jkshopping.fragment.FragmentContactUS;
import com.jdkgroup.jkshopping.fragment.FragmentCompany;
import com.jdkgroup.jkshopping.fragment.FragmentFashionProduct;
import com.jdkgroup.jkshopping.fragment.FragmentShoppingItemList;
import com.jdkgroup.jkshopping.utils.AppKeyword;
import com.jdkgroup.jkshopping.utils.GlobalClass;
import com.jdkgroup.jkshopping.utils.SaveSharedPrefernces;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.json.JSONObject;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GeneralImplement implements General {

    private Date date;
    private SimpleDateFormat simpleDateFormat;
    private CharSequence charsequence;
    private JSONObject jsonobject;
    private Iterator iterator;

    private Typeface typeface;
    private LinearLayoutManager linearLayoutManager;
    private View view;
    private TextView tv;

    //TODO Calendar
    private Calendar calendar = Calendar.getInstance();
    private int mYear = calendar.get(Calendar.YEAR);
    private int mMonth = calendar.get(Calendar.MONTH);
    private int mDay = calendar.get(Calendar.DAY_OF_MONTH);

    private Snackbar snackbar;
    private Gson gson;

    private SaveSharedPrefernces ssp = new SaveSharedPrefernces();

    private GlobalClass globalClass = GlobalClass.getInstance();

    @Override
    public String LongDateCovert(Long val) {
        date = new Date(Long.valueOf(val) * 1000);
        simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy");
        return simpleDateFormat.format(date);
    }

    @Override
    public String DayName(String date) {
        return null;
    }

    @Override
    public String DateZeroAdd(int number) {
        if (String.valueOf(number).length() < 2) {
            return String.valueOf(0 + "" + number);
        }
        return String.valueOf(number);
    }

    @Override
    public List<String> alSplit(final String splipt) {
        String arraySplit[] = splipt.split("\\s+");
        List<String> alSplit = new ArrayList<>();

        for (int i = 0; i < arraySplit.length; i++) {
            alSplit.add(arraySplit[i]);
        }
        return alSplit;
    }

    @Override
    public String LastCharacter(final String String) {
        char lastcharacter = String.charAt(String.length() - 1);
        return String.valueOf(lastcharacter);
    }

    @Override
    public CharSequence CurrentDateTime(final String dataformat) {
        simpleDateFormat = new SimpleDateFormat(dataformat);
        charsequence = simpleDateFormat.format(calendar.getTime());
        return charsequence;
    }

    @Override
    public CharSequence Uuid() {
        charsequence = java.util.UUID.randomUUID().toString();
        return charsequence;
    }

    @Override
    public JSONObject ConvertMapToJsonObject(final Map map) {
        jsonobject = new JSONObject();
        try {
            iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry) iterator.next();

                jsonobject.put(String.valueOf(pair.getKey()), String.valueOf(pair.getValue()));
            }
        } catch (Exception ex) {
        }

        return jsonobject;
    }

    @Override
    public String ConvertintToString(int value) {
        return String.valueOf(value);
    }

    @Override
    public boolean CheckInternetConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivityManager.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        } else {
            if (connectivityManager != null) {
                NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
                if (networkInfo != null)
                    for (int i = 0; i < networkInfo.length; i++)
                        if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
            }
        }

        return false;
    }

    @Override
    public void DisplayToast(final Activity activity, final String message, final int time, final boolean status) {
        if (status == true) {
            SwitchDisplayToast(activity, message, time);
        }
    }

    private void SwitchDisplayToast(final Activity activity, final String message, final int time) {
        switch (time) {
            case 1:
                Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                break;

            case 2:
                Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void DisplaySnackbar(final CoordinatorLayout coordinatorLayout, final String message, final String retry, final String retry_message, final int time, final int condition, final boolean status) {

        if (status == true) {
            SwitchDisplaySnackbar(coordinatorLayout, message, retry, retry_message, time, condition);
        }
    }

    private void SwitchDisplaySnackbar(final CoordinatorLayout coordinatorLayout, final String message, final String retry, final String retry_message, final int time, final int condition) {
        switch (condition) {
            case 1:
                snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT);
                view = snackbar.getView();
                snackbar.getView().setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorSnackBarBG));
                tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.WHITE);
                tv.setTextSize(17);
                FontTextView(tv, AppKeyword.sourcesanspro_regular);
                snackbar.show();
                break;

            case 2:
                snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
                view = snackbar.getView();
                snackbar.getView().setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorSnackBarBG));
                tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.WHITE);
                tv.setTextSize(17);
                FontTextView(tv, AppKeyword.sourcesanspro_regular);
                snackbar.show();
                break;

            case 3:
                snackbar = Snackbar
                        .make(coordinatorLayout, message, Snackbar.LENGTH_SHORT)
                        .setAction(retry, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbar = Snackbar.make(coordinatorLayout, retry_message, Snackbar.LENGTH_SHORT);
                                view = snackbar.getView();
                                snackbar.getView().setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorSnackBarBG));
                                tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                                tv.setTextColor(Color.WHITE);
                                tv.setTextSize(17);
                                FontTextView(tv, AppKeyword.sourcesanspro_regular);
                                snackbar.show();
                            }
                        });
                //snackbar.show();
                break;

            case 4:
                snackbar = Snackbar
                        .make(coordinatorLayout, message, Snackbar.LENGTH_LONG)
                        .setAction(retry, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbar = Snackbar.make(coordinatorLayout, retry_message, Snackbar.LENGTH_SHORT);
                                view = snackbar.getView();
                                snackbar.getView().setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorSnackBarBG));
                                tv.setTextSize(17);
                                FontTextView(tv, AppKeyword.sourcesanspro_regular);
                                snackbar.show();
                            }
                        });
                //snackbar.show();
                break;


        }
    }

    @Override
    public void DisplayNotification(Activity act, String eventtext) {

    }

    @Override
    public void DatePicketSet(final Activity act, final TextView textView) {
        DatePickerDialog dpd = new DatePickerDialog(act,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        if (String.valueOf(dayOfMonth).length() != 2 || String.valueOf(monthOfYear).length() != 2) {
                            textView.setText(DateZeroAdd(dayOfMonth) + "/" + DateZeroAdd(monthOfYear + 1) + "/" + year);
                            textView.setTextColor(Color.parseColor("#000000"));
                        } else {
                            textView.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            textView.setTextColor(Color.parseColor("#000000"));
                        }
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    @Override
    public String DatePicketFormat(final String dateformat) {
        switch (dateformat) {
            case "dd/mm/yyyy/zeroadd": {
                String dd_mm_yyyy_zeroadd = DateZeroAdd(mDay) + "/" + DateZeroAdd(mMonth + 1) + "/" + mYear;
                return dd_mm_yyyy_zeroadd;
            }
        }

        return null;
    }

    @Override
    public void HideKeyboard(final Activity activity, final String keybordtype) {
        SwitchHideKeyboard(activity, keybordtype);
    }

    @Override
    public void ConsoleLog(final String ActivityName, final String consoleprint, final String logstatus, final boolean status) {
        if (status == true) {
            SwitchConsoleLog(ActivityName, consoleprint, logstatus);
        }
    }

    private void SwitchConsoleLog(final String ActivityName, final String consoleprint, final String logstatus) {
        switch (logstatus) {
            case "debug":
                //The Log.d() method is used to log debug messages.
                Log.d(ActivityName, "=" + consoleprint + "=" + CurrentDateTime("DD/MM/YYYY"));
                break;

            case "info":
                //The Log.i() method is used to log informational messages.
                Log.i(ActivityName, "=" + consoleprint + "=" + CurrentDateTime("DD/MM/YYYY"));
                break;

            case "warn":
                //The Log.w() method is used to log warnings.
                Log.w(ActivityName, "=" + consoleprint + "=" + CurrentDateTime("DD/MM/YYYY"));
                break;

            case "error":
                //The Log.e() method is used to log errors.
                Log.e(ActivityName, "=" + consoleprint + "=" + CurrentDateTime("DD/MM/YYYY"));
                break;

            case "verbose":
                //The Log.v() method is used to log verbose messages.
                Log.v(ActivityName, "=" + consoleprint + "=" + CurrentDateTime("DD/MM/YYYY"));
                break;

            case "wtf":
                //The Log.wtf() method is used to log terrible failures that should never happen. ("WTF" stands for "What a Terrible Failure!" of course.)
                Log.wtf(ActivityName, "=" + consoleprint + "=" + CurrentDateTime("DD/MM/YYYY"));
                break;
        }
    }

    @Override
    public void ConsoleSystem(String ActivityName, String consoleprint, boolean status) {
        if (status == true) {
            SwitchConsoleSystem(ActivityName, consoleprint);
        }
    }

    private void SwitchConsoleSystem(final String ActivityName, final String consoleprint) {
        System.out.println("Tag " + AppKeyword.APPNAME + " :: " + "(" + ActivityName + ") " + consoleprint);
    }

    private void SwitchHideKeyboard(final Activity activity, final String keybordtype) {
        View view;
        switch (keybordtype) {
            case "hide":
                view = activity.getCurrentFocus();
                if (view != null) {
                    InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                break;
        }

    }

    @Override
    public List<String[]> csv(final Activity activity, final String filename) {
        /*List<String[]> list = new ArrayList<>();
        String next[] = {};

        try {
            InputStreamReader csvStreamReader = new InputStreamReader(activity.getAssets().open(filename + ".csv"));
            CSVReader reader = new CSVReader(csvStreamReader);
            for (; ; ) {
                next = reader.readNext();
                if (next != null) {
                    list.add(next);
                } else {
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return list;*/

        return null;
    }

    @Override
    public void FontAppCompatButton(final AppCompatButton appCompatButton, final String fonttype) {
        typeface = Typeface.createFromAsset(appCompatButton.getContext().getAssets(), fonttype);
        appCompatButton.setTypeface(typeface);
    }

    @Override
    public void FontAppCompatTextView(final AppCompatTextView appCompatTextView, final String fonttype) {
        typeface = Typeface.createFromAsset(appCompatTextView.getContext().getAssets(), fonttype);
        appCompatTextView.setTypeface(typeface);
    }

    public void FontTextView(final TextView textView, final String fonttype) {
        typeface = Typeface.createFromAsset(textView.getContext().getAssets(), fonttype);
        textView.setTypeface(typeface);
    }

    @Override
    public void FontAppCompatEditText(final AppCompatEditText appCompatEditText, final String fonttype) {
        typeface = Typeface.createFromAsset(appCompatEditText.getContext().getAssets(), fonttype);
        appCompatEditText.setTypeface(typeface);
    }

    @Override
    public void FacebookHashKey(Activity activity, String packageName) {
        PackageInfo info;
        try {
            info = activity.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String facebookkeyBase64 = new String(Base64.encode(md.digest(), 0));
                //String facebookkeyBase64new = new String(Base64.encodeBytes(md.digest()));

                ConsoleSystem("GeneralImplement", facebookkeyBase64, AppKeyword.STATUS);
                ConsoleLog("GeneralImplement", facebookkeyBase64, AppKeyword.LOGTYPE_DEBUG, AppKeyword.STATUS);
            }
        } catch (PackageManager.NameNotFoundException e1) {

        } catch (NoSuchAlgorithmException e) {

        } catch (Exception e) {

        }
    }

    @Override
    public void ImageViewSetBG(final Activity activity, final ImageView iv, final String imagename, final String foldername, final String colorbg, final String tintcolor, final int status) {
        SwitchImageViewSetBG(activity, iv, imagename, foldername, colorbg, tintcolor, status);
    }


    private void SwitchImageViewSetBG(final Activity activity, final ImageView iv, final String imagename, final String foldername, final String colorbg, final String tintcolor, final int status) {
        switch (status) {
            case 1:
                int resID = activity.getResources().getIdentifier(imagename, foldername, activity.getPackageName());
                iv.setImageResource(resID);
                break;

            case 2:
                //Tint color && Background Image
                break;

            case 3:
                //Background Image
                break;
        }
    }

    @Override
    public void RecyclerViewListGrid(final Activity activity, final RecyclerView recyclerView, final int numbergrid, final int status) {
        SwitchRecyclerViewListGrid(activity, recyclerView, numbergrid, status);
    }

    @Override
    public boolean ValidationEmail(final String email) {
        EmailValidator validator = EmailValidator.getInstance();
        if (validator.isValid(email)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean IsValidLength(final String value, final int min, final int max) {
        boolean condition = GenericValidator.minLength(value, min, max);
        return condition;
    }

    @Override
    public boolean ValidationBlank(final String strBlank) {
        return StringUtils.isBlank(strBlank);
    }

    @Override
    public boolean ValidationisNotNullEmptyWhiteSpaceOnly(final String strAny) {
        return strAny != null && !strAny.isEmpty() && !strAny.trim().isEmpty();
    }

    @Override
    public boolean ValidationExpression(final String expression, final String password) {
        RegexValidator sensitive = new RegexValidator(expression);

        boolean check = sensitive.isValid(password);
        return check == true;
    }

    @Override
    public boolean ValidationLength(final String strLength, final int min, final int max) {
        return GenericValidator.minLength(strLength, min, max);
    }

    @Override
    public boolean ValidationOnlyNumericAllow(final String strNumericAllow) {
        return StringUtils.isNumeric(strNumericAllow);
    }

    @Override
    public boolean ValidationWhitespace(final String strWhitespace) {
        return CharMatcher.WHITESPACE.matchesAnyOf(strWhitespace);
    }

    @Override
    public boolean ValidationContainNumeric(final String strNumeric) {
        return StringUtils.containsOnly(strNumeric, "0123456789") || CharMatcher.JAVA_DIGIT.matchesAllOf(strNumeric);
    }

    @Override
    public boolean Validation2StringEqual(final String strEquala, final String strEqualb) {
        return StringUtils.equals(strEquala, strEqualb);
    }

    @Override
    public boolean ValidationEmail(final CharSequence email) {
        if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    @Override
    public int SpaceCount(final String strvalue) {
        int i, space = 0;
        char chararray[] = strvalue.toCharArray();
        for (i = 0; i < chararray.length; i++) {
            if (chararray[i] == ' ') {
                space = space + 1;
            }
        }
        return space;
    }

    @Override
    //First letter capital
    public String FirstCharacterUpperCase(final String strValue) {
        return Character.toUpperCase(strValue.charAt(0)) + strValue.substring(1);
    }

    @Override
    //Uppercase letter
    public List<String> AllUpperCase(final List<String> alString) {
        //List<String> transformeduppercase = Lists.transform(alString, (String input) -> input.toUpperCase());
        //return transformeduppercase;

        return null;
    }

    @Override
    //Lowercase letter
    public String tolowercase(final String line) {
        String result = "";
        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);
            char currentCharToLowerCase = Character.toLowerCase(currentChar);
            result = result + currentCharToLowerCase;
        }
        return result;
    }

    @Override
    //cell no specific format
    public String MobileNoFormat(final String mobileno) {
        ArrayList alcn = new ArrayList();
        alcn.add(mobileno.substring(0, 2));
        alcn.add(mobileno.substring(2, 4));
        alcn.add(mobileno.substring(4, 10));
        return alcn.get(0) + " - " + alcn.get(1) + " - " + alcn.get(2);
    }

    @Override
    public String DMYCount(final Object datemonthyearcount) throws Exception {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        Date fdate = null;
        Date cdate = null;
        fdate = formatter.parse(datemonthyearcount.toString());
        cdate = formatter.parse(formatter.format(date));

        DateTime dt1 = new DateTime(fdate);
        DateTime dt2 = new DateTime(cdate);

        if (Days.daysBetween(dt1, dt2).getDays() < 7) {
            if (Days.daysBetween(dt1, dt2).getDays() == 0) {
                return "Today";
            } else if (Days.daysBetween(dt1, dt2).getDays() > 1 && Days.daysBetween(dt1, dt2).getDays() < 7) {
                return Days.daysBetween(dt1, dt2).getDays() + " Days";
            }
        }
        return df_yyyy_MM_dd(datemonthyearcount.toString());
    }

    private String df_yyyy_MM_dd(String date) {
        String dates[] = date.split("/");
        String datey = dates[2];
        String dated = dates[0];
        String datem = dates[1];

        String detechar = SwitchDayMonthShortFull(1, Integer.parseInt(datem) - 1);
        return dated + "/" + detechar + "/" + datey;
    }

    @Override
    public String SwitchDayMonthShortFull(final int isdaymonthshortfull, final int whichdaymonth) {
        switch (isdaymonthshortfull) {
            case 1: //MONTH SHORT NAME
                String[] shortmonthnames =
                        {
                                "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT",
                                "NOV", "DEC"
                        };

                return shortmonthnames[whichdaymonth];


            case 2: //MONTH FULL NAME
                String[] fullmonthnames =
                        {
                                "January", "February", "March", "April", "May", "June", "July", "August", "September",
                                "October", "November", "December"
                        };
                return fullmonthnames[whichdaymonth];

            case 3: //SHORT DAY
                String[] shortdays =
                        {
                                "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"
                        };
                return shortdays[whichdaymonth];

            case 4: //FULL DAY
                String[] fulldays =
                        {
                                "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"
                        };
                return fulldays[whichdaymonth];
        }

        return null;
    }


    @Override
    public String MobileInRange(String no) {
        String telephonenumber = CharMatcher.inRange('0', '9').retainFrom(no);
        return telephonenumber;
    }

    @Override
    public void ClearMemory() {
        Runtime.getRuntime().freeMemory();
        Runtime.getRuntime().runFinalization();
        System.gc();
    }

    private void SwitchRecyclerViewListGrid(final Activity activity, final RecyclerView recyclerView, final int numbergrid, final int status) {
        switch (status) {
            case 1: //List Item
                linearLayoutManager = new LinearLayoutManager(activity);

                break;

            case 2: //Grid Item
                linearLayoutManager = new GridLayoutManager(activity, numbergrid);
                break;
        }

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public String ConvertString(final String convertString, final int switchcase) throws Exception {
        return SwitchStringConvert(convertString, switchcase);
    }

    private String SwitchStringConvert(final String stringValue, final int switchcase) throws Exception {
        byte[] byteArray;
        switch (switchcase) {
            case 1: //Base64Decode

            case 2:

            case 3: //Base64Decode
                byteArray = Base64.decode(stringValue, Base64.DEFAULT);
                return new String(byteArray, "UTF-8");
            case 4:
                byteArray = stringValue.getBytes("UTF-16");
                return new String(Base64.decode(Base64.encode(byteArray, Base64.DEFAULT), Base64.DEFAULT));
            default:
                break;
        }

        return null;
    }

    @Override
    public String ReadFile(final Activity activity, final String path, final String extension) throws Exception {
        String json = null;
        try {
            InputStream is = activity.getAssets().open(path + "." + extension);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    @Override
    public int dpToPx(final float dp, final Resources resources) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }

    @Override
    public void AnimationAppCompactActivity(final Activity activity, final Intent intent, boolean reverseAnimate, final boolean animate, final int requestCode, final int animationtype) {
        SwitchAnimationAppCompactActivity(activity, intent, reverseAnimate, animate, requestCode, animationtype);
    }

    private void SwitchAnimationAppCompactActivity(final Activity activity, final Intent intent, boolean reverseAnimate, final boolean animate, final int requestCode, final int animationtype) {
        switch (animationtype) {
            case 1: //startActivity
                activity.startActivity(intent);
                if (animate)
                    activity.overridePendingTransition(R.anim.animation_slide_from_right, R.anim.animation_slide_to_left);
                break;

            case 2: //startActivityForResult
                activity.startActivityForResult(intent, requestCode);
                if (animate)
                    activity.overridePendingTransition(R.anim.animation_slide_from_right, R.anim.animation_slide_to_left);
                break;

            case 3: //reverse animation
                activity.finish();
                if (animate)
                    activity.overridePendingTransition(R.anim.animation_slide_from_left, R.anim.animation_slide_to_right);
                break;

            case 4: //finish
                activity.finish();
                if ((animate == true) && (reverseAnimate == true))
                    activity.overridePendingTransition(R.anim.animation_slide_from_left, R.anim.animation_slide_to_right);
                else if ((animate == true) && (reverseAnimate == false))
                    activity.overridePendingTransition(R.anim.animation_slide_from_left, R.anim.animation_slide_to_right);
                break;

            case 5: //startActivity
                activity.startActivity(intent);
                if ((animate == true) && (reverseAnimate == true))
                    activity.overridePendingTransition(R.anim.animation_slide_from_left, R.anim.animation_slide_to_right);
                else if ((animate == true) && (reverseAnimate == false))
                    activity.overridePendingTransition(R.anim.animation_slide_from_right, R.anim.animation_slide_to_left);
                break;
        }
    }

    @Override
    public void NavigationBar(final Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().setNavigationBarColor(activity.getResources().getColor(R.color.colorBlack));
        }
    }

    private void ChangeActivity(final Activity activity, final Class classname) {
        Intent i = new Intent(activity, classname);
        activity.startActivity(i);
    }

    private void pushFragments(Activity activity, Fragment fragment, int redirect) {

        FragmentManager manager = ((Drawer) activity).getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.containerView, fragment);

        if (globalClass.fragmentStack.contains(redirect)) {
            globalClass.fragmentStack.remove(new Integer(redirect));
            manager.popBackStack();
        }
        ft.addToBackStack(null);
        globalClass.fragmentStack.add(redirect);

        //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        ft.commit();
    }

    private void pushFragmentsFirst(Activity activity, Fragment fragment, int redirect) {

        FragmentManager manager = ((Drawer) activity).getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        //ft.setCustomAnimations(R.anim.fragment_slide_left, R.anim.fragment_slide_right);
        ft.replace(R.id.containerView, fragment);

        if (globalClass.fragmentStack.isEmpty()) {
            globalClass.fragmentStack.add(redirect);
        } else {
            if (globalClass.fragmentStack.contains(redirect)) {
                globalClass.fragmentStack = new ArrayList<>();
                globalClass.fragmentStack.add(redirect);
                int count = manager.getBackStackEntryCount();
                for (int i = 0; i < count; ++i) {
                    manager.popBackStackImmediate();
                }
            }
        }

        //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void SwitchPassdataChangeFragment(final Activity activity, final int redirect) {
        Fragment fragment;

        switch (redirect) {
            case 1: //REDIRECT_DRAWER_1
                ChangeActivity(activity, Drawer.class);
                break;

            case 2: //REDIRECT_LOGIN_2
                ChangeActivity(activity, ActivityLogin.class);
                break;

            case 21: //REDIRECT_SIGN_UP_21
                ChangeActivity(activity, ActivitySignUp.class);
                break;

            case 22: //REDIRECT_FORGOT_PASSWORD_22
                ChangeActivity(activity, ActivityForgotPassword.class);
                break;

            case 3: //REDIRECT_Home_3
                fragment = new FragmentCompany();
                pushFragmentsFirst(activity, fragment, redirect);
                break;

            case 31: //REDIRECT_HOME_FASHION_PRODuct_31
                Drawer.toolBar.setTitle("JK Fashion");
                fragment = new FragmentFashionProduct();
                pushFragments(activity, fragment, redirect);
                break;

            case 4: //REDIRECT_ADD_TO_CART_4
                fragment = new FragmentAddToCart();
                pushFragments(activity, fragment, redirect);
                break;

            case 5: //REDIRECT_SHOPPING_ITEM_LIST_5
                fragment = new FragmentShoppingItemList();
                pushFragments(activity, fragment, redirect);
                break;

            case 7: //REDIRECT_ABOUT_US_7
                fragment = new FragmentAboutUS();
                pushFragments(activity, fragment, redirect);
                break;

            case 6: //REDIRECT_CHANGE_PASSWORD_6
                fragment = new FragmentChangePassword();
                pushFragments(activity, fragment, redirect);
                break;

            case 8: //REDIRECT_CONTACT_US_8s
                fragment = new FragmentContactUS();
                pushFragments(activity, fragment, redirect);
                break;

            case 9: //REDIRECT_PROFILE_9
                ChangeActivity(activity, ActivityProfile.class);
                break;


            case 0:
                if (AppKeyword.APPMODE == AppKeyword.APPMODE_LOGIN || AppKeyword.APPMODE == AppKeyword.APPMODE_WITHOUTLOGIN) {
                    //Drawer.mDrawerLayout.closeDrawers();
                }
                DialogLogout dialogLogout = new DialogLogout(activity);
                dialogLogout.show();
                break;

            default:
                System.out.println("No available drawer menu");

        }
    }

    private Gson SwitchGson(int param) {
        switch (param) {
            case 1:
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                gson = gsonBuilder.create();
                return gson;

            case 2: //FIRST CHARACTER UPPER CAMEL
                gson = new GsonBuilder().
                        disableHtmlEscaping().
                        setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).
                        setPrettyPrinting().serializeNulls().
                        create();
                return gson;

            default:
                break;
        }

        return null;
    }

    public String EncodingGuava(String encodeText) {
        return BaseEncoding.base64().encode(encodeText.getBytes());
    }

    public String DecodingGuava(String decodeText) {
        byte[] decoded = BaseEncoding.base64().decode(decodeText);
        return new String(decoded);
    }
}

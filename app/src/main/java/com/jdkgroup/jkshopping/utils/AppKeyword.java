package com.jdkgroup.jkshopping.utils;

/**
 * Created by kamlesh on 12/18/2016.
 */

public class AppKeyword {
    public static String APPNAME = "JK Shopping";
    private static String FONT_FOLDER = "fonts/";

    public static boolean FLag_FRAGMENT_STACK = false;

    public static String HOSTNAME = "192.168.43.174";
    public static int PORT = 3000;

    public static int APPMODE; //WELCOME, LOGIN, WITHOUTLOGIN
    public static int APPMODE_WELCOME = 0, APPMODE_LOGIN = 1, APPMODE_WITHOUTLOGIN = 2;

    public static boolean
            STATUS = true;

    public static int REQUEST_PICKUP_IMAGE_GALLERY = 1;
    public static int REQUEST_PICKUP_IMAGE_CAMERA = 0;

    public static int PROGRESSBAR_SHOW = 1,
            PROGRESSBAR_CANCEL = 2;

    public static int RECYCLERVIEW_LIST = 1,
            RECYCLERVIEW_GRID = 2;

    public static String
            FOLDER_DRAWABLE = "drawable",
            FOLDER_MIPMAP = "mipmap";

    public static String
            LOGTYPE_DEBUG = "debug",
            LOGTYPE_INFO = "info",
            LOGTYPE_WARN = "warn",
            LOGTYPE_ERROR = "error",
            LOGTYPE_VERBOSE = "verbose",
            LOGTYPE_WTF = "wtf";

    public static String keyboardtypehide = "hide",
            keyboardtypeshow = "show";

    public static String
            fontsourcesanspro_light = FONT_FOLDER + "sourcesanspro_light.ttf",
            sourcesanspro_regular = FONT_FOLDER + "sourcesanspro_regular.ttf",
            sourcesanspro_bold = FONT_FOLDER + "sourcesanspro_bold.ttf";

    public static int
            SNACKBAR_SHORT = 1,
            SNACKBARLONG = 2,
            SNACKBAR_RETRY_SHORT = 3,
            SNACKBAR_RETRY_LONG = 4;

    public static String
            //[A-Z]{3,10}$
            EXPRESSION_PASSWORD = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).{6,20})",
            EXPRESSION_MOBILE = "^[0-9]{10,10}$",
            EXPRESSION_USERNAME = "[a-zA-Z]{3,20}$",
            EXPRESSION_BIRTHDATE = "\\d{1,2}-\\d{1,2}-\\d{4}";

    public static int GSON_NEWGSON = 1;

    //TODO MENU
    public static final int REDIRECT_DRAWER_1 = 1;

    public static final int REDIRECT_LOGIN_2 = 2;
    public static final int REDIRECT_SIGN_UP_21 = 21;
    public static final int REDIRECT_FORGOT_PASSWORD_22 = 22;

    public static final int REDIRECT_Home_3 = 3;
    public static final int REDIRECT_HOME_FASHION_PRODuct_31 = 31;

    public static final int REDIRECT_ADD_TO_CART_4 = 4;

    public static final int REDIRECT_SHOPPING_ITEM_LIST_5 = 5;


    public static final int REDIRECT_CHANGE_PASSWORD_6 = 6;

    public static final int REDIRECT_ABOUT_US_7 = 7;

    public static final int REDIRECT_CONTACT_US_8 = 8;

    public static final int REDIRECT_PROFILE_9 = 9;

    public static final int REDIRECT_LOGOUT_0 = 0;

    //TODO API CALL
    public static int WS_CODE_1 = 1, WS_CODE_0 = 0;

    public static int CODE_SIGNUP_INSERT = 100;
    public static int CODE_SIGNUP_AUTHENTICATION = 103;

    public static int CODE_COMPANY_DISPLAY_ALL = 201;

    //TODO REGULAR EXPRESSION
    public static String REGULAR_EXPRESSION_CELLNO = "^[0-9]{4,10}$";
    //public static String REGULAR_EXPRESSION_PASSWORD = "^(?=.*[A-Za-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    public static String REGULAR_EXPRESSION_PASSWORD = "((?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).{6,20})";
    public static String REGULAR_EXPRESSION_PASSWORD_1_UPPERCASE = "^(.*?[A-Z]){1,}.*$";
    public static String REGULAR_EXPRESSION_PASSWORD_1_NUMERIC = "^(.*?[0-9]){1,}.*$";
    public static String REGULAR_EXPRESSION_USERNAME = "[a-zA-Z]{3,20}$";

}

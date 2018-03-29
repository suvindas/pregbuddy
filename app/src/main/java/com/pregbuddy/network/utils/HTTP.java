package com.pregbuddy.network.utils;

import android.os.Build;

import com.pregbuddy.BuildConfig;
import com.pregbuddy.utils.Constants;
import com.pregbuddy.utils.Utils;


public final class HTTP {
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
    public static final String MULTI_PART_FORM_DATA = "multipart/form-data;";
    public static final String APPLICATION_JSON = "application/json";
    public static final String USER_AGENT = "User-Agent";
    public static final String ENCODE_UTF_8 = "UTF-8";
    public static final String TEXT_PLAIN = "text/plain";


    // Used in HTTP Request User Agent
    public static final String USER_AGENT_VALUE = Constants.APPLICATION_NAME + "/" + BuildConfig.VERSION_NAME + " (Android "
            + Utils.getDeviceName() + "; OS " + Build.VERSION.RELEASE
            + "; SDK Level :" + Build.VERSION.SDK_INT + ")";

    HTTP() {
        throw new RuntimeException("Stub!");
    }

    public static boolean isWhitespace(char ch) {
        throw new RuntimeException("Stub!");
    }
}

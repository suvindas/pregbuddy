package com.pregbuddy.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class PreferenceData {
    private SharedPreferences sharedPreferences;
    private static final String PREFERENCE_NAME = "BC_PREFERENCE";
    private static final String PREFERENCE_USER_ID = "USER_ID";
    private static final String PREFERENCE_USER_TYPE = "USER_TYPE";
    private static final String PREFERENCE_USER_ACCESS_TOKEN = "USER_ACCESS_TOKEN";
    private static final String PREF_USER_DATA = "USER_DATA";
    private static final String PREF_USER_BUSINESS_DATA = "USER_BUSINESS_DATA";
    private static final String PREF_ADVERTISEMENT_DATA = "ADVERTISEMENT_DATA";

    private static final String PREF_BUSINESS_TYPE = "BUSINESS_TYPE";
    private static final String PREF_CONSUMER_TYPE = "CONSUMER_TYPE";
    private static final String PREF_PROFESSIONAL_TYPE = "PROFESSIONAL_TYPE";
    private static final String PREF_REGISTRATION_TOKEN = "REGISTRATION_TOKEN";

    private static final String PREF_NOTIFICATION_COUNT = "NOTIFICATION_COUNT";
    private static final String PREF_PRIVATE_MESSAGE_COUNT = "PRIVATE_MESSAGE_COUNT";
    private static final String PREF_IS_FIRST = "IS_FIRST";


    public PreferenceData(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void setFirstTimeFalse() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PREF_IS_FIRST, false);
        editor.commit();
    }

    public boolean isFirstTime() {
        return sharedPreferences.getBoolean(PREF_IS_FIRST, true);
    }


    public void setPrivateMessageCount(int count) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_PRIVATE_MESSAGE_COUNT, count);
        editor.commit();
    }

    public int getPrivateMessageCount() {
        return sharedPreferences.getInt(PREF_PRIVATE_MESSAGE_COUNT, 0);
    }

    public void setNotificationCount(int count) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_NOTIFICATION_COUNT, count);
        editor.commit();
    }

    public int getNotificationCount() {
        return sharedPreferences.getInt(PREF_NOTIFICATION_COUNT, 0);
    }

    public void setRegistrationToken(String registrationToken) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_REGISTRATION_TOKEN, registrationToken);
        editor.commit();

    }

    public String getRegistrationToken() {
        return sharedPreferences.getString(PREF_REGISTRATION_TOKEN, null);

    }

    public void setUserId(int userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREFERENCE_USER_ID, userId);
        editor.commit();

    }

    public int getUserID() {
        return sharedPreferences.getInt(PREFERENCE_USER_ID, -1);

    }


}

package com.pregbuddy.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static com.pregbuddy.utils.FontTypeIntDef.REGULAR;


public class Device {
    /**
     * Check the device is connected to network.
     *
     * @param context Current application context.
     * @return true/false according to the check.
     */
    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null)
                for (NetworkInfo aNetworkInfo : networkInfo)
                    if (aNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    /**
     * Show the Toast message to the user
     *
     * @param context Current application context.
     * @param message Message to show the device user.
     */
    public static void showToastMessage(Context context, String message, int length) {
        if (context != null && !TextUtils.isEmpty(message)) {
            Toast toast = Toast.makeText(context, message, length);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public static void showSnackBarErrorWithAction(Context context, String message, int length, String actionText, View.OnClickListener listener) {
        try {
            final Snackbar snackbar = Snackbar.make(((Activity) context).findViewById(android.R.id.content),
                    message, length);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(Color.parseColor("#d8453c"));
            TextView textView = sbView
                    .findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setBackgroundColor(Color.parseColor("#d8453c"));
            textView.setTypeface(FontUtils.getCustomTypeFace(context, REGULAR));
            snackbar.setActionTextColor(ContextCompat.getColor(context, android.R.color.white));
            snackbar.setAction(actionText, listener);
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSnackBarError(Context context, String message, int length) {
        try {
            Snackbar snackbar = Snackbar.make(((Activity) context).findViewById(android.R.id.content),
                    message, length);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(Color.parseColor("#d8453c"));
            TextView textView = sbView
                    .findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setBackgroundColor(Color.parseColor("#d8453c"));
            textView.setTypeface(FontUtils.getCustomTypeFace(context, REGULAR));
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSnackBarSuccess(Context context, String message, int length) {
        try {
            Snackbar snackbar = Snackbar.make(((Activity) context).findViewById(android.R.id.content),
                    message, length);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(Color.parseColor("#4caf50"));
            TextView textView = sbView
                    .findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setTypeface(FontUtils.getCustomTypeFace(context, REGULAR));
            textView.setBackgroundColor(Color.parseColor("#4caf50"));
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.pregbuddy.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Android : BizClips on 8/21/2017.
 */
public class DisplayUtils {
    private int _screenWidth;
    private int _screenHeight;

    public DisplayUtils(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        setScreenWidth(displaymetrics.widthPixels);
        setScreenHeight(displaymetrics.heightPixels);
    }


    private void setScreenWidth(int screenWidth) {
        this._screenWidth = screenWidth;
    }

    private void setScreenHeight(int screenHeight) {
        this._screenHeight = screenHeight;
    }

    public int getScreenWidth() {
        return _screenWidth;
    }

    public int getScreenHeight() {
        return _screenHeight;
    }


    public int getHeightPercentage(int percentage) {
        return (getScreenHeight() * percentage) / 100;
    }

    public int getWidthPercentage(int percentage) {
        return (getScreenWidth() * percentage) / 100;
    }
}

package com.pregbuddy.utils;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FontTypeIntDef {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            REGULAR,
            MEDIUM,
            LIGHT}
    )
    public @interface FontType {
    }

    public static final int REGULAR = 1;
    public static final int MEDIUM = 2;
    public static final int LIGHT = 3;


}
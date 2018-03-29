package com.pregbuddy.utils;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class GlobalTypeIntDef {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            VALIDATION_ERROR,
            EMPTY_ERROR,
            PASSWORD_MISSMATCH}
    )
    public @interface GlobalType {
    }

    public static final int VALIDATION_ERROR = 1;
    public static final int EMPTY_ERROR = 2;
    public static final int PASSWORD_MISSMATCH = 3;


}
package com.pregbuddy.utils;

import android.content.Context;
import android.graphics.Typeface;


public class FontUtils {

    private static final String FONT_LIGHT = "font/roboto_light.ttf";
    private static final String FONT_MEDIUM = "font/roboto_medium.ttf";
    private static final String FONT_REGULAR = "font/roboto_regular.ttf";

    public static Typeface getCustomTypeFace(Context context, int fontType) {
        Typeface typeface;
        if (fontType == FontTypeIntDef.REGULAR) {
            typeface = Typeface.createFromAsset(context.getAssets(),
                    FONT_REGULAR);
        } else if (fontType == FontTypeIntDef.LIGHT) {
            typeface = Typeface.createFromAsset(context.getAssets(),
                    FONT_LIGHT);
        } else {
            typeface = Typeface.createFromAsset(context.getAssets(),
                    FONT_MEDIUM);
        }
        return typeface;
    }


}

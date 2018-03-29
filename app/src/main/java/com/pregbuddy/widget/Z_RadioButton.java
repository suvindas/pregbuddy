package com.pregbuddy.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.pregbuddy.R;
import com.pregbuddy.utils.FontTypeIntDef;

import static com.pregbuddy.utils.FontUtils.getCustomTypeFace;

public class Z_RadioButton extends android.support.v7.widget.AppCompatRadioButton {

    public Z_RadioButton(Context context) {
        super(context);
        if (isInEditMode()) return;
        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.Z_RadioButton);
        int fontType = typedArray.getInt(R.styleable.Z_RadioButton_typeface, FontTypeIntDef.REGULAR);
        setTypeface( getCustomTypeFace(getContext(), fontType));
        invalidate();
    }

    public Z_RadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode()) return;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Z_RadioButton);
        int fontType = typedArray.getInt(R.styleable.Z_RadioButton_typeface, FontTypeIntDef.REGULAR);
        setTypeface( getCustomTypeFace(getContext(), fontType));
        invalidate();
    }

    public Z_RadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (isInEditMode()) return;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Z_RadioButton);
        int fontType = typedArray.getInt(R.styleable.Z_RadioButton_typeface, FontTypeIntDef.REGULAR);
        setTypeface( getCustomTypeFace(getContext(), fontType));
        invalidate();
    }



}

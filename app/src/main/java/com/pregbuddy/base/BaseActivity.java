package com.pregbuddy.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;


import com.pregbuddy.data.PreferenceData;
import com.pregbuddy.utils.DisplayUtils;
import com.pregbuddy.utils.ProgressUtils;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    protected ProgressUtils _progressUtils;
    protected PreferenceData _preferenceData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        _progressUtils = new ProgressUtils(this);
        _preferenceData = new PreferenceData(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        onCreateViews(new DisplayUtils(this));
    }

    protected void onCreateViews(DisplayUtils displayUtils) {
        onViewCreated();
    }

    protected void onViewCreated() {

    }


    protected BaseActivity getThis() {
        return this;
    }
}

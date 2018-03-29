package com.pregbuddy.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import com.pregbuddy.data.PreferenceData;
import com.pregbuddy.utils.ProgressUtils;


public class BaseFragment extends Fragment {
    protected ProgressUtils _progressUtils;
    protected PreferenceData _preferenceData;
    protected final String TAG = this.getClass().getSimpleName();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        _progressUtils = new ProgressUtils(getActivity());
        _preferenceData = new PreferenceData(getActivity());
    }

}

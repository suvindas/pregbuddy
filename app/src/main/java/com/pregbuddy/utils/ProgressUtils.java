package com.pregbuddy.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Android : BizClips on 8/21/2017.
 */
public class ProgressUtils {

    private ProgressDialog _progressDialog;

    public ProgressUtils(Context context) {
        _progressDialog = new ProgressDialog(context);
    }

    public void showProgress(String message) {
        if (_progressDialog != null && !_progressDialog.isShowing()) {
            _progressDialog.setMessage(message);
            _progressDialog.setCancelable(false);
            _progressDialog.show();
        }
    }

    public void dismissProgress() {
        if (_progressDialog != null && _progressDialog.isShowing()) {
            _progressDialog.dismiss();
        }
    }
}

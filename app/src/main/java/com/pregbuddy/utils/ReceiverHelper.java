package com.pregbuddy.utils;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class ReceiverHelper {

    public static IntentFilter getFilterForReceiver(String... actions) {
        IntentFilter intentFilter = new IntentFilter();
        for (String action : actions) {
            intentFilter.addAction(action);
        }
        return intentFilter;
    }

    public static Intent getIntentForReceiver(String action, Bundle bundle) {
        Intent intent = new Intent(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        return intent;
    }
}
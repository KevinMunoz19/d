package com.digifactbeta;

import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import javax.annotation.Nonnull;

public class KpModule extends ReactContextBaseJavaModule {

    ReactApplicationContext context = getReactApplicationContext();

    public KpModule(@Nonnull ReactApplicationContext reactContext){
        super(reactContext);

    }

    @Nonnull
    @Override
    public String getName() {
        return "KpModule";
    }

    @ReactMethod
    public void NavigatetoNative() {
        Activity activity = getCurrentActivity();
        //Intent intent = new Intent(context.Main3Activity.class);

        //if (activity != null) {
            Intent intent = new Intent(activity, Main3Activity.class);
            activity.startActivity(intent);
        //}

        //if (intent.resolveActivity(context.getPackageManager()) !=null) {
        //    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //    context.startActivity(intent);
        //}
    }


}

package com.digifactbeta;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class SwchManager extends SimpleViewManager<Swch> {
    @Override
    public String getName() {
        return "Swch";
    }
    @Override
    protected Swch createViewInstance(ThemedReactContext reactContext) {
        return new Swch(reactContext);
    }


    @ReactProp(name="isTurnedOn")
    public void setSwchStatus(Swch swchView, Boolean isTurnedOn) {
        swchView.setIsTurnedOn(isTurnedOn);
    }
}
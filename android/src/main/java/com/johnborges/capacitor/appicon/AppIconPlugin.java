package com.johnborges.capacitor.appicon;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "AppIcon")
public class AppIconPlugin extends Plugin {

    private AppIcon implementation = new AppIcon();

    @PluginMethod
    public void isSupported(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("value", implementation.isSupported());
        call.resolve(ret);
    }

    @PluginMethod
    public void getName(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("value", implementation.getName(getContext()));
        call.resolve(ret);
    }

    @PluginMethod
    public void reset(PluginCall call) {
        implementation.reset(getContext());
        call.resolve();
    }

    @PluginMethod
    public void change(PluginCall call) {
        String name = call.getString("name");

        if (name == null || name.trim().length() == 0) {
            call.reject("Must provide an icon name.");
            return;
        }

        implementation.change(getContext(), name);
        call.resolve();
    }
}

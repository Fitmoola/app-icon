package com.johnborges.capacitor.appicon;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.getcapacitor.PluginConfig;

public class AppIcon {

    private PluginConfig pluginConfig;

    public void setPluginConfig(PluginConfig pluginConfig) {
        this.pluginConfig = pluginConfig;
    }

    public static String getPreferencesName(Context context) {
        String prefId = context.getPackageName() + "." + "CAPACITOR_PLUGIN_APP_ICON_PREFERENCE";
        return prefId;
    }

    public Boolean isSupported() {
        return true;
    }

    public String getName(Context context) {
        String name = getCurrentIconName(context);
        return (name.length() > 0 && !name.equals("MainActivityDefault")) ? name : null;
    }

    public void change(Context context, String iconName) {
        setIcon(context, iconName);
    }

    public void reset(Context context) {
        setIcon(context, "");
    }

    private String getCurrentIconName(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(AppIcon.getPreferencesName(context), Context.MODE_PRIVATE);
        String currentIconName = sharedPref.getString("currentAppIcon","MainActivityDefault");
        return currentIconName;
    }

    private void saveCurrentIconName(Context context, String name) {
        SharedPreferences sharedPref = context.getSharedPreferences(AppIcon.getPreferencesName(context), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("currentAppIcon", name.trim());
        editor.apply();
    }

    private void setIcon(Context context, String enableIconName) {
        PackageManager manager = context.getPackageManager();
        String packagePrefix = pluginConfig.getString("packagePrefix", context.getPackageName());

        // Read current app icon if any
        String disableIconName = getCurrentIconName(context);

        if (disableIconName.trim().length() == 0) {
            disableIconName = "MainActivityDefault";
        }

        if (enableIconName.trim().length() == 0) {
            enableIconName = "MainActivityDefault";
        }

        if (!disableIconName.equals(enableIconName)) {
            if (enableIconName.trim().length() != 0) {
                // Enable app icon
                String enabledIconAlias = packagePrefix + "." + enableIconName;
                manager.setComponentEnabledSetting(new ComponentName(context.getPackageName(), enabledIconAlias)
                        ,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP);
            }

            if (disableIconName.trim().length() != 0) {
                // Disable custom icon selected
                String disabledIconAlias = packagePrefix + "." + disableIconName;
                manager.setComponentEnabledSetting(new ComponentName(context.getPackageName(), disabledIconAlias)
                        ,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
            }

            // Save new icon name that should be enabled to disable it later if needed
            saveCurrentIconName(context, enableIconName);
        }
    }
}

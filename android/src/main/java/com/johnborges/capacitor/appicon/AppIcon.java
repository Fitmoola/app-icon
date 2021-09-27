package com.johnborges.capacitor.appicon;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class AppIcon {

    public static String getPreferencesName(Context context) {
        String prefId = context.getPackageName() + "." + "CAPACITOR_PLUGIN_APP_ICON_PREFERENCE";
        return prefId;
    }

    public String echo(String value) {
        return value;
    }

    public Boolean isSupported() {
        return true;
    }

    public String getName(Context context) {
        String name = getCurrentIconName(context);
        return name.length() > 0 ? name : null;
    }

    public void change(Context context, String iconName) {
        setIcon(context, iconName);
    }

    public void reset(Context context) {
        setIcon(context, "");
    }

    private String getCurrentIconName(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(AppIcon.getPreferencesName(context), Context.MODE_PRIVATE);
        String currentIconName = sharedPref.getString("currentAppIcon","");
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
        String packageName = context.getPackageName();

        // Read current app icon if any
        String disableIconName = getCurrentIconName(context);

        if (disableIconName != enableIconName) {
            // Save new icon name that should be enabled to disable it later if needed


            if (enableIconName.trim().length() != 0) {
                // Enable app icon
                String enabledIconAlias = packageName + "." + enableIconName;
                manager.setComponentEnabledSetting(new ComponentName(packageName, enabledIconAlias)
                        ,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP);
            }

            if (disableIconName.trim().length() != 0) {
                // Disable custom icon selected
                String disabledIconAlias = packageName + "." + disableIconName;
                manager.setComponentEnabledSetting(new ComponentName(packageName, disabledIconAlias)
                        ,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
            }
        }
    }
}

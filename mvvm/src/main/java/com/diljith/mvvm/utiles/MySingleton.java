package com.diljith.mvvm.utiles;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Oneteam-Harish .
 */

public class MySingleton {
    private static MySingleton ourInstance = new MySingleton();
    private String companyName;
    private Integer companyId;
    public static MySingleton getInstance() {
        if (ourInstance == null) ourInstance = new MySingleton();
        return ourInstance;
    }

    private MySingleton() {
    }

    public void clear() {
        ourInstance = null;
    }

    //***************************** SharedPreferences Start*********************************
    //***************************** SharedPreferences Methods Start*********************************
    public SharedPreferences get(Context context) {
        return context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
    }

    public void clearPreference(Context context) {
        SharedPreferences settings = MySingleton.getInstance().get(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public void addKeyString(Context context, String key, String val) {
        SharedPreferences settings = MySingleton.getInstance().get(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, val);
        editor.commit();
    }

    public void addKeyInt(Context context, String key, int val) {
        SharedPreferences settings = MySingleton.getInstance().get(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, val);
        editor.commit();
    }

    public void addKeyBoolean(Context context, String key, boolean val) {
        SharedPreferences settings = MySingleton.getInstance().get(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, val);
        editor.commit();
    }

    public String getKeyString(Context context, String key) {
        SharedPreferences prefs = MySingleton.getInstance().get(context);
        return prefs.getString(key, "0");
    }

    public int getKeyInt(Context context, String key) {
        SharedPreferences prefs = MySingleton.getInstance().get(context);
        return prefs.getInt(key, 0);
    }

    public boolean getKeyBoolean(Context context, String key) {
        SharedPreferences prefs = MySingleton.getInstance().get(context);
        return prefs.getBoolean(key, false);
    }


    public void removeKey(Context context, String key) {
        SharedPreferences settings = MySingleton.getInstance().get(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.commit();
    }
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

}

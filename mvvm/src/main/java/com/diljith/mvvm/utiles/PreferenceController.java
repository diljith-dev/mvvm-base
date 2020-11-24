package com.diljith.mvvm.utiles;

import android.content.Context;
import android.content.SharedPreferences;




public class PreferenceController {
    private static PreferenceController ourInstance = new PreferenceController();



    private  int crmOverViewcurrentTabPosition =0;



    private  int listActivityTabPosition;

    public static PreferenceController getInstance() {
        if (ourInstance == null) ourInstance = new PreferenceController();
        return ourInstance;
    }

    private PreferenceController() {
    }

    public void clear() {
        ourInstance = null;
    }

    //***************************** SharedPreferences Start*********************************
    //***************************** SharedPreferences Methods Start*********************************
    public SharedPreferences get(Context context) {
        return context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
    }

    public static void clearPreference(Context context) {
        SharedPreferences settings = PreferenceController.getInstance().get(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }
    public void addKeyString(Context context, String key, String val) {
        SharedPreferences settings = PreferenceController.getInstance().get(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, val);
        editor.apply();
    }


    public void addKeyInt(Context context, String key, int val) {
        SharedPreferences settings = PreferenceController.getInstance().get(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, val);
        editor.apply();
    }

    public void addKeyILong(Context context, String key, long val) {
        SharedPreferences settings = PreferenceController.getInstance().get(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, val);
        editor.apply();
    }

    public void addKeyBoolean(Context context, String key, boolean val) {
        SharedPreferences settings = PreferenceController.getInstance().get(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, val);
        editor.apply();
    }

    public String getKeyString(Context context, String key) {
        SharedPreferences prefs = PreferenceController.getInstance().get(context);
        return prefs.getString(key, "");
    }

    public int getKeyInt(Context context, String key) {
        SharedPreferences prefs = PreferenceController.getInstance().get(context);
        return prefs.getInt(key, 0);
    }

    public long getKeyLong(Context context, String key) {
        SharedPreferences prefs = PreferenceController.getInstance().get(context);
        return prefs.getLong(key, 0);
    }

    public boolean getKeyBoolean(Context context, String key) {
        SharedPreferences prefs = PreferenceController.getInstance().get(context);
        return prefs.getBoolean(key, false);
    }


    public void removeKey(Context context, String key) {
        SharedPreferences settings = PreferenceController.getInstance().get(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.apply();
    }

    public int getCrmOverViewcurrentTabPosition() {
        return crmOverViewcurrentTabPosition;
    }

    public void setCrmOverViewcurrentTabPosition(int crmOverViewcurrentTabPosition) {
        this.crmOverViewcurrentTabPosition = crmOverViewcurrentTabPosition;
    }

    public int getListActivityTabPosition() {
        return listActivityTabPosition;
    }

    public void setListActivityTabPosition(int listActivityTabPosition) {
        this.listActivityTabPosition = listActivityTabPosition;
    }
}

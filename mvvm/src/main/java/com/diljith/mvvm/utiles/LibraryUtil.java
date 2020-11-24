package com.diljith.mvvm.utiles;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.graphics.Bitmap;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;



import com.diljith.mvvm.R;
import com.diljith.mvvm.livedatas.Response;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class LibraryUtil {



    public static android.app.ProgressDialog sProgressDialog;

    public static void dismissProgressDialog() {

        if (sProgressDialog != null && sProgressDialog.isShowing()) {
            sProgressDialog.dismiss();
        }
    }

    public static void showProgressDialog(Context context) {
        try {
            dismissProgressDialog();

            sProgressDialog = new android.app.ProgressDialog(context, R.style.ProgressDialogTheme);
            sProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Large);
            sProgressDialog.setCancelable(false);
            try {
                sProgressDialog.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

     /*hiding key board*/
    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }



public static boolean isInternetOn(Context ctx) {
    // get Connectivity Manager object to check connection

    ConnectivityManager connec = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
    // Check for network connections
    if (connec.getNetworkInfo(0) != null) {
        Log.d("obj", connec + "");
        Log.d("obj1", connec.getNetworkInfo(0).getState() + "");
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED || connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING || connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING || connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            return true;
        } else if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED || connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            return false;
        }
    } else {
        if (connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING || connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            return true;
        } else if (connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            return false;
        }
    }


    return false;
}


    public static void alertDialogue(Context context, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.app_name);
        builder.setMessage(message);

        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void showAlert(Context context, String message, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.app_name);
        builder.setMessage(message);
        builder.setPositiveButton(android.R.string.ok, listener);
        builder.setNegativeButton(android.R.string.cancel, listener);
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
    }

    public static void showSingleAlert(Context context, String message, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.app_name);
        builder.setMessage(message);
        builder.setPositiveButton(android.R.string.ok, listener);

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
    }

    public static void ShowSnackBarMessage(View view, String button_txt, String message, View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
        snackbar.setAction(button_txt, listener);
    }

    public static void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showMessage(Context context, int message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    public static String changeDateFormat(long date) {
        SimpleDateFormat oldFormater = new SimpleDateFormat("dd MMM,(EEE)", Locale.US);
        Date oldDate = new Date();
        oldDate.setTime(date);
        return oldFormater.format(oldDate);
    }

    public static String changeDateFormatForCalander(long date) {
        SimpleDateFormat oldFormater = new SimpleDateFormat("dd MMM (EEE)", Locale.US);
        Date oldDate = new Date();
        oldDate.setTime(date);
        return oldFormater.format(oldDate);
    }
    public static long changeDateToLong(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        return calendar.getTimeInMillis();
    }

    public static void showToastAtCenter(Context ctx, String message) {
        Toast toast = Toast.makeText(ctx, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /*  Converting Date from  milliseconds to  Required format Format
        Example - 27 Feb, Wed
        */
    public static long getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis();
    }

    public static String getCurrentDate()
    {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c);
        return formattedDate;
    }


































    public static boolean inputValidation(String leave_desc) {

        return !leave_desc.trim().equals("");
    }




    @SuppressWarnings("deprecation")
    public static void clearCookies(Context context)

    {

        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.removeAllCookies(null);
        }
        else {
            cookieManager.removeAllCookie();
        }
    }
    public static void permissionAlertDialogue(final Context context, String Message)
    {
        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setTitle(R.string.app_name);
        builder.setMessage(Message);

        // add a button
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
                ((Activity)context).finish();

            }
        });

        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void showCrmStageAlert(Context context, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.app_name);
        builder.setMessage(message);
        builder.setTitle(R.string.app_name);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public static CharSequence trimTrailingWhitespace(CharSequence source) {

        if(source == null)
            return "";

        int i = source.length();

        // loop back to the first non-whitespace character
        while(--i >= 0 && Character.isWhitespace(source.charAt(i))) {
        }

        return source.subSequence(0, i+1);
    }



    public static String clearSpaceInMobileNumber(String phone) {
        phone=phone.replaceAll(" ","");
        return phone;

    }

    public static boolean isValidMobile(String phone) {
        phone=phone.replaceAll(" ","");
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() >= 10 && phone.length() <= 13;
        } else {
            return false;
        }
    }
    public static boolean isValidPhoneNumber(String phone) {
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() >= 8 && phone.length() <= 12;
        }
        return false;
    }
    public static boolean isEmailValid(String email) {
        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
    public static String convertToBase64(Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Bitmap.createScaledBitmap(bitmap, 800, 800, false);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 30, outputStream);
            return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
        }
        return null;
    }















    public static void deleteCache(Context context)
    { try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }


    public static String getTimeInFormatForScheduleView(String dateInServerFormat)
    {
        dateInServerFormat=   dateInServerFormat.replace(".",":");
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy (EEE) hh:mm aa",Locale.ENGLISH);

        Date newDate = null;
        try {
            newDate = format.parse(dateInServerFormat);
        } catch (ParseException e) {
            e.printStackTrace();
          //  return dateInServerFormat;
        }
        format = new SimpleDateFormat("hh:mm aa",Locale.ENGLISH);
        //  format = new SimpleDateFormat("dd MMM (EEE)",Locale.ENGLISH);
        String date = format.format(newDate);
        return date;
    }
    public static String getDateInFormatForScheduleView(String dateInServerFormat)
    {
        dateInServerFormat=   dateInServerFormat.replace(".",":");
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy (EEE) hh:mm aa",Locale.ENGLISH);

        Date newDate = null;
        try {
            newDate = format.parse(dateInServerFormat);
        } catch (ParseException e) {
            e.printStackTrace();
           // return dateInServerFormat;
        }
        format = new SimpleDateFormat("dd MMM yyyy (EEE)",Locale.ENGLISH);
        //  format = new SimpleDateFormat("dd MMM (EEE)",Locale.ENGLISH);
        String date = format.format(newDate);
        return date;
    }


    public static void showActionAlertWithCustomUpdate(Context context,
                                                          String message,
                                                          String alertTitle,
                                                          String firstButtonName,
                                                          String secondButtonName,
                                                          String neutralButtonName,
                                                          DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(alertTitle);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(firstButtonName, listener);
        builder.setNegativeButton(secondButtonName,listener);
        builder.setNeutralButton(neutralButtonName,listener);
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
    }

    public static void showActionAlertWithCustomUpdate(Context context,
                                                       String message,
                                                       String alertTitle,
                                                       String firstButtonName,
                                                       String secondButtonName,

                                                       DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(alertTitle);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(firstButtonName, listener);
        builder.setNegativeButton(secondButtonName,listener);
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
    }




    public static void showActionAlert(Context context,
                                         int message,

                                         DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.app_name);

        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", listener);
        builder.setNegativeButton("Cancel",listener);
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
    }

    public static void showSingleButtonActionAlert(Context context,
                                       String message,

                                       DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.app_name);

        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", listener);

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
    }
    public static Boolean isLocationEnabled(Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // This is new method provided in API 28
            LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            return lm.isLocationEnabled();
        } else {
            // This is Deprecated in API 28
            int mode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE,
                    Settings.Secure.LOCATION_MODE_OFF);
            return  (mode != Settings.Secure.LOCATION_MODE_OFF);

        }
    }



    public static String ApiResponseParser(Response response) {
        String SERVER_NOT_REACHABLE = "Server not reachable, please try again later.";
        String error_404 = "Server not reachable. May be invalid entries, try again.";



        if(response==null)
        {
            return SERVER_NOT_REACHABLE;
        } else if (validateString(response.message )) {
            return response.message;
        } else if(validateString(response.OuterParamErrorMessage))
        {
            return response.OuterParamErrorMessage;
        }

        else if (validateString(response.rowMessage )) {
            return  response.rowMessage;
        } else if (response.status == 404) {
            return  error_404;
        } else {
            return SERVER_NOT_REACHABLE;
        }




    }




    public static boolean validateString(String string)
    {
        if(string==null)
        {
            return false;
        }
        else return !string.isEmpty();

    }
    
    

























}

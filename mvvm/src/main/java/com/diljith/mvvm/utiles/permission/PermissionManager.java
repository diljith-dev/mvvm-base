package com.diljith.mvvm.utiles.permission;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.diljith.mvvm.R;


public class PermissionManager {

    public static final int REQUEST_FOR_PERMISSION = 1001;

    /*
     * Add the required permission in this array and manifest
     */
    private static String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static boolean checkPermissions(Context context) {
        for (final String permission : permissions) {
            final boolean status = checkPermission(context, permission);
            if (!status) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkPermissionRationale(AppCompatActivity context) {
        for (final String permission : permissions) {
            final boolean status = isShouldShowRequestPermissionRationale(context, permission);
            if (!status) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkPermissionGranted(int[] results) {
        for (final int result : results) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void requestPermissions(AppCompatActivity context) {
        ActivityCompat.requestPermissions(context, permissions, REQUEST_FOR_PERMISSION);
    }
    public static void requestPermissionsForAttachmentActivity(Activity context, String[] permissions) {
        ActivityCompat.requestPermissions(context, permissions, REQUEST_FOR_PERMISSION);
    }



    public static void requestPermission(AppCompatActivity activity, String permission) {
        ActivityCompat.requestPermissions(activity, new String[]{permission}, REQUEST_FOR_PERMISSION);
    }


    public static boolean checkPermission(Context context, String permission) {
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkCameraPermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkExternalStoragePermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkCallPhonePermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkReadExtenalStoragePermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }


    public static boolean checkLocationPermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean checkFingerprintPermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isShouldShowRequestPermissionRationale(AppCompatActivity context, String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(context, permission);
    }

    public static void showAlertForPermission(final Context context, int resource, final String app_id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
        builder.setMessage(resource);
        builder.setNeutralButton(R.string.settings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", app_id, null);
                intent.setData(uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

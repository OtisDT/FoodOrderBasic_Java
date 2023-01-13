package com.example.foodorder.constant;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.example.foodorder.R;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class GlobalFuntion {

    public static void startActivity(Context context, Class<?> clz) {
        Intent intent = new Intent(context, clz);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }
    public static void startLogout(Context context, Class<?> T) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.app_name)
                .setMessage(R.string.msg_logout)
                .setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, T);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        ((Activity) context).finish();
                    }

                })
                .setNegativeButton(R.string.action_cancel, null)
                .show();

    }



    public static void startActivity(Context context, Class<?> T, Bundle bundle) {
        Intent intent = new Intent(context, T);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.
                    getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public static void showMessageError(Activity activity) {
        Toast.makeText(activity, Constant.GENERIC_ERROR, Toast.LENGTH_SHORT).show();
    }


    public static void onClickOpenZalo(Context context) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.ZALO_LINK)));
    }
    public static void onClickOpenZalo1(Context context) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.ZALO_LINK1)));
    }
    public static void onClickOpenZalo2(Context context) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.ZALO_LINK2)));
    }

    public static void callPhoneNumber(Activity activity) {
        try {
            if (Build.VERSION.SDK_INT > 22) {
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 101);
                    return;
                }

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + Constant.PHONE_NUMBER));
                activity.startActivity(callIntent);

            } else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + Constant.PHONE_NUMBER));
                activity.startActivity(callIntent);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void showToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String getTextSearch(String input) {
        String nfdNormalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}
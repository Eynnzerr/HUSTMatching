package com.example.hustmatching.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import com.example.hustmatching.R;

public class AlertDialogUtil {
    public static void createErrorDialog(Activity activity, String msg) {
        View alertView = activity.getLayoutInflater().inflate(R.layout.error_dialog, null, false);
        TextView textView = alertView.findViewById(R.id.dialog_msg);
        textView.setText(msg);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(alertView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

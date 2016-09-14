package com.crs.demo.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.crs.demo.R;

/**
 * Created on 2016/8/26.
 * Author:crs
 * Description:对话框工具类
 */
public class DialogUtils {

    public static Dialog createDialog(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        ImageView iv_loading = (ImageView) view.findViewById(R.id.iv_loading);
        return null;
    }


    public static Dialog createLoadingDialog(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        LinearLayout ll_loading_dialog = (LinearLayout) view.findViewById(R.id.ll_loading_dialog);
        ImageView iv_loading = (ImageView) view.findViewById(R.id.iv_loading);
        iv_loading.setImageResource(R.drawable.loading);
        final AnimationDrawable animationDrawable = (AnimationDrawable) iv_loading.getDrawable();
        animationDrawable.start();

        Dialog dialog = new Dialog(context, R.style.loading_dialog);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.setContentView(ll_loading_dialog, params);
        //设置按下返回键能否取消
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (animationDrawable.isRunning()) {
                    animationDrawable.stop();
                }
            }
        });

        return dialog;

    }
}

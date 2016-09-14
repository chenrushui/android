package com.crs.demo.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created on 2016/9/13.
 * Author:crs
 * Description:软件盘工具类
 * 调用时机：在控件的点击事件的时候弹出
 */
public class KeyBoardUtils {
    /**
     * 打开软键盘
     *
     * @param et
     * @param context
     */
    public static void openKeyBoard(EditText et, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     *
     * @param et
     * @param context
     */
    public static void closeKeyBoard(EditText et, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);

    }
}

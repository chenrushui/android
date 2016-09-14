package com.crs.demo.ui;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.utils.LogUtils;

import java.io.File;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created on 2016/9/8.
 * Author:crs
 * Description:LuBan图片压缩
 */
public class LunBanImageActivity extends BaseActivity {

    public static final String TAG = "LunBanImage";
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lu_ban);

        final File file = new File("/storage/sdcard1" + File.separator + "test.png");
        final long start = file.length();

        findView(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Luban.get(LunBanImageActivity.this)
                        .load(file)
                        .putGear(Luban.FIRST_GEAR)//设置压缩级别
                        .setCompressListener(new OnCompressListener() {
                    //压缩开始前调用，可以在方法内启动loading UI
                    @Override
                    public void onStart() {
                        LogUtils.i(TAG, "开始压缩");
                    }
                    //压缩成功后调用，返回压缩后的图片文件
                    @Override
                    public void onSuccess(File file) {
                        //获取压缩后的文件大小
                        long end = file.length();
                        long reduce = start - end; //448434 压缩的特别小
                        LogUtils.i(TAG, reduce + "");
                    }

                    //当压缩出现问题时调用
                    @Override
                    public void onError(Throwable e) {

                    }
                    //启动
                }).launch();
            }
        });
    }
}

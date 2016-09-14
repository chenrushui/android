package com.crs.demo.ui;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.utils.AssetUtils;

/**
 * Created on 2016/9/13.
 * Author:crs
 * Description:资产文件操作
 */
public class AssetActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset);
        final AssetManager assetsManager = AssetActivity.this.getAssets();

        findView(R.id.btn_asset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strFromAsset = AssetUtils.getStrFromAsset("area.data", assetsManager);
            }
        });


    }
}

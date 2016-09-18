package com.crs.demo.ui.rx;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.constant.UrlConstant;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 2016/9/18.
 * Author:crs
 * Description:测试RxAndroid
 * RxAndroid应用场景总结:
 * 1)使用RxAndroid代替AsyTask完成下载操作
 * 2)结合okHttp完成常规的网络操作
 * 3)结合ListView/GridView完成图文混排操作
 */
public class TestRxAndroidActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_rx_create;
    private Button btn_rx_download_image;
    private ImageView iv_rx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rx);

        initViews();
        initListener();
    }


    private void initViews() {
        btn_rx_create = findView(R.id.btn_rx_create);
        btn_rx_download_image = findView(R.id.btn_rx_download_image);
        iv_rx = findView(R.id.iv_rx);
    }

    private void initListener() {
        btn_rx_create.setOnClickListener(this);
        btn_rx_download_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rx_create: {
                //创建被观察者
                RxUtils.createObserable();
            }
            break;
            case R.id.btn_rx_download_image: {
                //下载图片
                DownLoadUtils utils = new DownLoadUtils();
                //主要用于更新UI的操作！
                utils.downloadImage(UrlConstant.IMAGE_URL)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<byte[]>() {
                    @Override
                    public void onCompleted() {
                        //一般情况下，应用于对话框消失

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(byte[] bytes) {
                        //从服务器端获取字节数组，然后通过BitmapFactory解析成Bitmap对象;
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        iv_rx.setImageBitmap(bitmap);
                    }
                });
            }
            break;
        }
    }
}

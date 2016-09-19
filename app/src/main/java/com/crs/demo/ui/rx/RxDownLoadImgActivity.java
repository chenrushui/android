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
import com.crs.demo.utils.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 2016/9/19.
 * Author:crs
 * Description:使用RxAndroid+OKHttp进行图片的下载
 */
public class RxDownLoadImgActivity extends BaseActivity implements View.OnClickListener {

    private static String TAG = RxDownLoadImgActivity.class.getSimpleName();
    private Button btn_rx_download_img;
    private ImageView iv_rx_download_img;
    private DownloadUtils mDownloadUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_download_img);

        initViews();
        initListener();

        //持有网络请求类的引用
        mDownloadUtils = new DownloadUtils();
    }


    private void initViews() {
        btn_rx_download_img = findView(R.id.btn_rx_download_img);
        iv_rx_download_img = findView(R.id.iv_rx_download_img);
    }

    private void initListener() {
        btn_rx_download_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rx_download_img: {
                //观察者和被观察者通过subscribe()方法实现绑定
                mDownloadUtils.downloadImage(UrlConstant.IMAGE_URL)
                        .subscribeOn(Schedulers.io())//IO操作
                        .observeOn(AndroidSchedulers.mainThread())//切换到主线程里面
                        .subscribe(new Subscriber<byte[]>() {
                            @Override
                            public void onCompleted() {
                                //主要用于处理加载对话框的显示与隐藏
                            }

                            @Override
                            public void onError(Throwable e) {
                                LogUtils.e(TAG, e.getMessage());

                            }

                            @Override
                            public void onNext(byte[] bytes) {
                                //从服务器端获取的字节数组对象，通过BitmapFactory把字节数组转化成Bitmap对象
                                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                iv_rx_download_img.setImageBitmap(bitmap);
                            }
                        });
            }
            break;
        }
    }

}

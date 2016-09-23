package com.crs.demo.ui.widget;

;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.crs.demo.R;
import com.crs.demo.base.BaseActivity;
import com.crs.demo.bean.StudentEntity;

/**
 * Created on 2016/8/23.
 * Author:crs
 * Description:Activity的生命周期方法
 * 音乐的播放与暂停
 */
public class LifeActivity extends BaseActivity {

    private MediaPlayer player;
    //用于记录播放器播放到的位置
    private int position;

    /**
     * 参数表示保存的状态信息
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            //就可以获取到数据。
        }

        Intent intent = getIntent();
        if (intent != null) {
            //获取intent对象后，判断是否为非空
        }

        //播放器对象
        player = MediaPlayer.create(this, R.raw.music);
        //开始播放
        player.start();

        Bundle bundle = new Bundle();
        bundle.putInt("sum", 100);
        //使用Bundle传递引用类型
        bundle.putSerializable("学生对象", new StudentEntity("陈如水", 22));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        bundle.putParcelable("图片", bitmap);
        bundle.getParcelable("");

        int taskId = getTaskId();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 当界面显示的时候调用这个方法。
        // 1）后退栈中重新显示，获取购物车中商品的数量。
        // 2）回到当前页面，让音乐继续播放。
        if (position != 0) {
            //让播放器对象跳转到上次播放的位置
            player.seekTo(position);
            //让播放器继续播放
            player.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //如果此时要跳转到另一个页面，音乐应该处于暂停状态
        if (player.isPlaying()) {
            //让播放器处于暂停状态
            player.pause();
            //获取当前播放到的位置
            position = player.getCurrentPosition();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    //Activity的收尾工作，用于解决内存泄露的问题。 比如销毁广播、销毁EventBus
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            //释放播放器所消耗的资源
            player.release();
            player = null;
        }
    }

    //横竖屏切换时，调用此方法保存Activity的状态信息。
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //可以把Bundle看成map集合
    }
}

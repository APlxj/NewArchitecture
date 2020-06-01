package io.vov.vitamio.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import io.vov.vitamio.R;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

public class VideoPlayerActivity extends Activity {

    private RelativeLayout mRootView;
    private RelativeLayout mHeadView;
    private RelativeLayout mFootView;

    private VideoView mVideoView;
    private TextView mName;
    private CheckBox mPlatAction;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (!Vitamio.initialize(this)) {
            Toast.makeText(this, "初始化失败", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplayer);
        initView();
        initVitamio();
    }

    private void initView() {
        mRootView = findViewById(R.id.root_view);
        mRootView.setOnClickListener(v -> {
            //显示与影藏HeadView和FootView
            if (mHeadView.getVisibility() == View.VISIBLE) {
                mHeadView.setVisibility(View.GONE);
                mFootView.setVisibility(View.GONE);
            } else {
                mHeadView.setVisibility(View.VISIBLE);
                mFootView.setVisibility(View.VISIBLE);
            }
        });
        mHeadView = findViewById(R.id.head_view);
        mFootView = findViewById(R.id.foot_view);

        mPlatAction = findViewById(R.id.play_action);
        mPlatAction.setChecked(false);
        mPlatAction.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                //当前状态为播放中，转换为暂停

            } else {
                //当前状态为暂停，转换为播放中

            }
        });
        mVideoView = findViewById(R.id.video_view);
    }

    private void initVitamio() {

    }

}

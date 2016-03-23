package com.zhangzhenzhong1.tools.context;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhangzhenzhong1.tools.R;
import com.zhangzhenzhong1.tools.widget.DragProgressView;


/**
 *
 * 自定义进度条
 * Created by zhangzhenzhong1 on 2016/3/17.
 */
public class ProgressBarToolsActivity extends Activity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);
        initView();
    }

    private void initView(){
        //xml颜色值定义型
        ProgressBar  mPbJindu = (ProgressBar) findViewById(R.id.pb_jindu);
        mPbJindu.setProgress(50);
        mPbJindu.setSecondaryProgress(80);

        //自定义继承View型
        final TextView tv_progressbar=(TextView)findViewById(R.id.tv_progressbar);
        DragProgressView mCanDragProgressView = (DragProgressView) findViewById(R.id.m_CanDragProgressView);
        int Jine=1000;
        mCanDragProgressView.setmSecondValue(Jine);// 默认初始为1万
        mCanDragProgressView.setmProgressHeight(100);
        mCanDragProgressView.setmMaxValue(100000);
        mCanDragProgressView.setmHeight(170);
        mCanDragProgressView.setmAverageScaleNumber(100);
        mCanDragProgressView.setmFirstValue(mContext,10000);// 默认初始为1万
        // 选中的是哪个点
        mCanDragProgressView.setmDragListener(new DragProgressView.DragListener() {

            @Override
            public void onDrag(DragProgressView view, float value) {
                tv_progressbar.setText("继承View型"+value);
            }
        });
    }

}

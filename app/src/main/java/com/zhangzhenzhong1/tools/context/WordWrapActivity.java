package com.zhangzhenzhong1.tools.context;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import com.zhangzhenzhong1.tools.R;
import com.zhangzhenzhong1.tools.utils.TextTypeface;
import com.zhangzhenzhong1.tools.widget.WordWrapView;


/**
 * Created by zhangzhenzhong1 on 2016/3/17.
 */
public class WordWrapActivity extends Activity{
    private Context mContext;
    /**
     * 标签自动换行测试数据
     */
    private String[] biaoQianStrs = new String[]{"哲学系", "新疆维吾尔族自治区", "新闻学", "心理学",
            "犯罪心理学", "明明白白", "西方文学史", "计算机", "掌声", "心太软", "生命",
            "程序开发"};
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        mContext=this;
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_wordwrapview);
        intiView();
    }
    private void intiView(){
        WordWrapView wwv_auto=(WordWrapView)findViewById(R.id.wwv_auto);
        for (int i = 0; i <biaoQianStrs.length; i++) {
            TextView textview = new TextView(mContext);
            textview.setText(biaoQianStrs[i]);
            textview.setTextColor(Color.parseColor("#780000"));
            textview.setTextSize(18);
            TextTypeface.configRobotoThin(mContext, textview);
            wwv_auto.addView(textview);
        }
    }
}

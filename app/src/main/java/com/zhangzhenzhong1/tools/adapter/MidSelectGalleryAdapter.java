package com.zhangzhenzhong1.tools.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.zhangzhenzhong1.tools.R;
import com.zhangzhenzhong1.tools.utils.DisplayUtil;

import java.util.List;

/**
 *可滑动中间为选择状态项的Gallery适配
 *
 * @author zhangzhenzhong
 */
public class MidSelectGalleryAdapter extends BaseAdapter {
    private int w1;
    private int h1;
    private Context mContext;
    private int selectItem;


    private List<String> mList;

    public MidSelectGalleryAdapter(Context mContext, int w1, int h1, List<String> list) {
        this.mContext = mContext;
        this.w1 = w1;
        this.h1 = h1;
        this.mList = list;
    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setSelectItem(int selectItem) {

        if (this.selectItem != selectItem) {
            this.selectItem = selectItem;
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout mRl = new RelativeLayout(mContext);
        RelativeLayout.LayoutParams mRlTopParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams mRlMidParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams mRlBottomParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        // mRl.setLayoutParams(mRlParams);
        TextView mTopView = new TextView(mContext);
        TextView mMidView = new TextView(mContext);
        TextView mBottomView = new TextView(mContext);

        mTopView.setHeight(DisplayUtil.dipToPx(mContext, 18));
        mTopView.setId(R.id.id_adview_101);
        mTopView.setLayoutParams(mRlTopParams);

        mMidView.setHeight(DisplayUtil.dipToPx(mContext, 37));
        mMidView.setId(R.id.id_adview_102);
        mMidView.setLayoutParams(mRlMidParams);
        mRlMidParams.addRule(RelativeLayout.BELOW, R.id.id_adview_101);

        mBottomView.setHeight(DisplayUtil.dipToPx(mContext, 16));
        mBottomView.setId(R.id.id_adview_103);
        mBottomView.setLayoutParams(mRlBottomParams);

        mRlTopParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mRlTopParams.topMargin = DisplayUtil.dipToPx(mContext, 22);

        mRlMidParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mRlMidParams.topMargin = DisplayUtil.dipToPx(mContext, 2);

        mRlBottomParams.addRule(RelativeLayout.BELOW, R.id.id_adview_102);
        mRlBottomParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mRlBottomParams.topMargin = DisplayUtil.dipToPx(mContext, 8);

        mTopView.setTextSize(DisplayUtil.px2sp(mContext,
                DisplayUtil.dipToPx(mContext, 12)));
        mMidView.setTextSize(DisplayUtil.px2sp(mContext,
                DisplayUtil.dipToPx(mContext, 30)));
        TextPaint tp = mMidView.getPaint();
        tp.setFakeBoldText(true);
        mBottomView.setTextSize(DisplayUtil.px2sp(mContext,
                DisplayUtil.dipToPx(mContext, 12)));

        mTopView.setText("七日年化收益率");
        if (position == 0) {
            mMidView.setText(mList.get(0) + "%");
            mBottomView.setText("( 我的收益)");
        } else {
            mMidView.setText(mList.get(position) + "%");
            mBottomView.setText("( 我的收益)");
        }

        mRl.addView(mTopView);
        mRl.addView(mMidView);
        mRl.addView(mBottomView);

        if (selectItem == position) {
            // Animation animation = AnimationUtils.loadAnimation(mContext,
            // R.anim.hyperspace_out);
            /* 关键之处，决定子项显示的块大小，进而显示的块位置也不一样 */
            mRl.setLayoutParams(new Gallery.LayoutParams(w1 / 2,
                    Gallery.LayoutParams.WRAP_CONTENT));
            // mRl.startAnimation(animation);
            // imageView.setImageResource(R.drawable.center);

            mTopView.setTextColor(Color.parseColor("#999999"));
            mMidView.setTextColor(Color.parseColor("#FF801A"));
            mBottomView.setTextColor(Color.parseColor("#999999"));

        } else {
            mTopView.setTextColor(Color.parseColor("#dddddd"));
            mMidView.setTextColor(Color.parseColor("#dddddd"));
            mBottomView.setTextColor(Color.parseColor("#dddddd"));
			/* 代码需要合并去掉判断，if判断是以后留用来做特效的，暂时不去掉 */
            // if (selectItem < position) {
            //
            // } else {
            //
            // }
			/* 关键之处，决定子项显示的块大小，进而显示的块位置也不一样 */
            mRl.setLayoutParams(new Gallery.LayoutParams(w1 / 3,
                    Gallery.LayoutParams.WRAP_CONTENT));
        }
        return mRl;
    }

}

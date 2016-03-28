package com.zhangzhenzhong1.tools.context;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhangzhenzhong1.tools.R;
import com.zhangzhenzhong1.tools.fragment.AutoScaleTextFragment;


public class MainActivity extends AppCompatActivity implements AutoScaleTextFragment.OnFragmentInteractionListener{

    private Context mContext;
    private LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        TextView mTvProgressbar=(TextView)findViewById(R.id.tv_progressbar);
        TextView mTvWordwrapview=(TextView)findViewById(R.id.tv_wordwrapview);
        TextView mTvAutoscale=(TextView)findViewById(R.id.tv_autoscale);
        container=(LinearLayout)findViewById(R.id.ll_fragment);
        mTvProgressbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                container.setVisibility(View.GONE);
                Intent i=new Intent(mContext,ProgressBarToolsActivity.class);
                startActivity(i);
            }
        });
        mTvWordwrapview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                container.setVisibility(View.GONE);
                Intent i=new Intent(mContext,WordWrapActivity.class);
                startActivity(i);
            }
        });
        mTvAutoscale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                container.setVisibility(View.VISIBLE);
                Fragment AutoScaleFragment=new AutoScaleTextFragment();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack("AutoScale");
                transaction.add(R.id.ll_fragment,AutoScaleFragment);
                transaction.commitAllowingStateLoss();
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        if(mBackHandedFragment == null || !mBackHandedFragment.onBackPressed()){
            if(getSupportFragmentManager().getBackStackEntryCount() == 0){
                super.onBackPressed();
            }else{
                LinearLayout ll_fragment=(LinearLayout)findViewById(R.id.ll_fragment);
                ll_fragment.setVisibility(View.GONE);
                getSupportFragmentManager().popBackStack();
            }
//        }
    }
}

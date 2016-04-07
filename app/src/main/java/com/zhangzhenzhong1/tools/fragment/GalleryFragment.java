package com.zhangzhenzhong1.tools.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.TextView;

import com.zhangzhenzhong1.tools.R;
import com.zhangzhenzhong1.tools.adapter.MidSelectGalleryAdapter;
import com.zhangzhenzhong1.tools.context.OnFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 中间滑动有选中状态的Gallery
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link GalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryFragment extends Fragment implements
        AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View rootView;
    private Gallery mMidSelectgallery;
    private Context mContext;
    private MidSelectGalleryAdapter midSelectGalleryAdapter;
    private TextView mTvValue;
    private List<String> mList;
    public GalleryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AutoScaleTextFragment.
     */
    // TODO: Rename and change types and number of parameters---数据共享
    public static GalleryFragment newInstance(String param1, String param2) {
        GalleryFragment fragment = new GalleryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        }
        initView(rootView);
        return rootView;
    }

    /**
     * 初始化可滑动到中间为选中项
     */
    private void initView(View rootView) {
        mMidSelectgallery = (Gallery) rootView.findViewById(R.id.midselectgallery);
        mTvValue = (TextView) rootView.findViewById(R.id.tv_value);

        /**
         * 测试数据
         */
        mList=new ArrayList<String>();
        for (int i=0;i<6;i++){
            mList.add("800");
        }

        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)  mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w1 = dm.widthPixels;
        int h1 = dm.heightPixels;
        midSelectGalleryAdapter = new MidSelectGalleryAdapter(mContext,
                w1, h1, mList);
        mMidSelectgallery.setAdapter(midSelectGalleryAdapter);
        mMidSelectgallery.setSpacing(40);
        mMidSelectgallery.setOnItemSelectedListener(this);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * 中间选中项Gallery
     */
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
       midSelectGalleryAdapter.setSelectItem(position);
       mTvValue.setText(mList.get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}

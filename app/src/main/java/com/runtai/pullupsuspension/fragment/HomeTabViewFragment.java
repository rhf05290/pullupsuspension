package com.runtai.pullupsuspension.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runtai.pullupsuspension.view.DividerItemDecoration;
import com.runtai.pullupsuspension.adapter.HomeTabViewAdapter;
import com.runtai.pullupsuspension.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FT
 * @time 2016-07-18 16:49
 */

// JUMP 
public class HomeTabViewFragment extends Fragment{
    
    RecyclerView mRecyclerView;
    private Context context;
    private int mPage;
    public static final String MERCHANT_DETAILS_PAGE = "MERCHANT_DETAILS_PAGE";
    private HomeTabViewAdapter mAdapter;

    List<String> mFoodData;
    List<String> mMovieData;
    List<String> mHaveFunData;
    List<String> mEvaluationData;

    public static HomeTabViewFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(MERCHANT_DETAILS_PAGE, page);
        HomeTabViewFragment tripFragment = new HomeTabViewFragment();
        tripFragment.setArguments(args);
        return tripFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(MERCHANT_DETAILS_PAGE);
        context = getActivity().getApplicationContext();
        setData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        switch (mPage) {
            case 0:
                initAdapter(mFoodData);
                break;
            case 1:
                initAdapter(mMovieData);
                break;
            case 2:
                initAdapter(mHaveFunData);
                break;
            case 3:
                initAdapter(mEvaluationData);
                break;
        }
        return view;
    }

    private void setData() {
        mFoodData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mFoodData.add("美食" + i);
        }
        mMovieData = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mMovieData.add("电影" + i);
        }
        mHaveFunData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mHaveFunData.add("玩乐" + i);
        }
        mEvaluationData = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            mEvaluationData.add("评价" + i);
        }
    }

    /**
     * 设置RecyclerView属性
     */
    private void initAdapter(List<String> data) {
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        LinearLayoutManager fullyLinearLayoutManager = new LinearLayoutManager(getActivity());
        fullyLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(fullyLinearLayoutManager);
        mAdapter = new HomeTabViewAdapter(getActivity(), data);
//        mAdapter.openLoadAnimation();
        mRecyclerView.setAdapter(mAdapter);//设置adapter
        mAdapter.setOnItemClickLitener(new HomeTabViewAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
//                startActivity(new Intent(getActivity(), Proiect_detailsActivity.class));
            }
        });
    }

}

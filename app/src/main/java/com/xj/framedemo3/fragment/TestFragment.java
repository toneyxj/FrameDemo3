package com.xj.framedemo3.fragment;

import android.app.Application;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.xj.framedemo3.MainActivity;
import com.xj.framedemo3.R;
import com.xj.framedemo3.TestRequest;
import com.xj.framedemo3.modelList.ModelUtils;
import com.xj.mainframe.adapter.BaseRecyclerAdapter;
import com.xj.mainframe.adapter.SmartViewHolder;
import com.xj.mainframe.base.BaseUtils;
import com.xj.mainframe.base.SmartLayoutUtils;
import com.xj.mainframe.base.activity.BaseActivity;
import com.xj.mainframe.base.fragment.BaseFragment;
import com.xj.mainframe.configer.APPLog;
import com.xj.mainframe.configer.ToastUtils;
import com.xj.mainframe.eventBus.EventManger;
import com.xj.mainframe.listener.HandlerMessageInterface;
import com.xj.refuresh.SmartRefreshLayout;
import com.xj.refuresh.api.RefreshLayout;
import com.xj.refuresh.listener.OnRefreshLoadMoreListener;


/**
 * Created by xj on 2018/10/29.
 */

public class TestFragment extends BaseFragment implements HandlerMessageInterface {
    private String Title;
    //可以根据这个修改item里面的内容
    private RecyclerView recyclerView;

    public void setTitle(String title) {
        Title = title;
    }

    private SmartRefreshLayout refreshLayout;
    private SmartLayoutUtils layoutUtils;
    private BaseRecyclerAdapter<ModelUtils.Model> mAdapter;

    private TestRequest testRequest;


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        APPLog.e("onDestroyView"+Title);
        testRequest.onStopRequest();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        APPLog.e("setUserVisibleHint"+Title+"    isVisibleToUser="+isVisibleToUser);
    }

    @Override
    public void initFragment(Bundle savedInstanceState) {

    }

    @Override
    public void fragmentShowing(boolean is) {

    }

    @Override
    public void initView(View view) {
        StringBuilder builder;
        StringBuffer buffer;
        refreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        layoutUtils=new SmartLayoutUtils(refreshLayout, new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull  RefreshLayout refreshLayout) {
                getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        APPLog.e("刷新二");
                        ToastUtils.getInstance().showToastShort(mAdapter.getCount());
                        if (mAdapter.getCount() > 12) {
                            Toast.makeText(getContext(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
                            TestFragment.this.refreshLayout.finishLoadMoreWithNoMoreData();//设置之后，将不会再触发加载事件
                        } else {
                            mAdapter.loadMore(ModelUtils.loadModels());
                            TestFragment.this.refreshLayout.finishLoadMore();
                        }
                    }
                }, 1000);
            }
            @Override
            public void onRefresh(@NonNull  RefreshLayout refreshLayout) {
                APPLog.e("刷新一");
                getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TestFragment.this.refreshLayout.finishRefresh();
                        TestFragment.this.refreshLayout.setNoMoreData(false);//恢复上拉状态
                    }
                }, 2000);

            }
        });
        recyclerView=layoutUtils.addRecycleView();
        layoutUtils.initListstyle();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter=new BaseRecyclerAdapter<ModelUtils.Model>(ModelUtils.loadModels(), R.layout.listitem_practice_repast) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, ModelUtils.Model model, int position) {
                holder.text(R.id.name, model.name);
                holder.text(R.id.nickname, model.nickname);
                holder.image(R.id.image, model.imageId);
                holder.image(R.id.avatar, model.avatarId);
            }
        });

        testRequest=new TestRequest(getContext(),layoutUtils.getLoading());

        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                testRequest.testRequest();
            }
        },3000);
        mAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        EventManger.getInstance().notifiyCode(MainActivity.mainE,position);
                    }
                },3000);

            }
        });
    }

    @Override
    public int getLayoutID() {
        return (R.layout.fragment);
    }
}

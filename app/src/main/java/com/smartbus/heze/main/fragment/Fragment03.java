package com.smartbus.heze.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.refreshview.CustomRefreshView;
import com.smartbus.heze.R;
import com.smartbus.heze.http.utils.BaseRecyclerAdapter;
import com.smartbus.heze.http.utils.BaseViewHolder;
import com.smartbus.heze.main.bean.OaWillDo;
import com.smartbus.heze.main.module.OaWillListContract;
import com.smartbus.heze.main.presenter.OaWillListPresenter;
import com.smartbus.heze.oasheet.OaDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/4/12.
 * 站点查询
 */

public class Fragment03 extends Fragment implements OaWillListContract.View {
    @BindView(R.id.recyclerView)
    CustomRefreshView recyclerView;
    @BindView(R.id.llNoContent)
    LinearLayout llNoContent;

    View view;
    int start = 0;
    int limit = 25;
    String type = "0";
    String type1 = "0";
    Unbinder unbinder;
    BaseRecyclerAdapter baseAdapter;
    OaWillListPresenter oaWillListPresenter;
    List<OaWillDo.ResultBean> beanList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment03, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        beanList.clear();
        baseAdapter = new BaseRecyclerAdapter<OaWillDo.ResultBean>(getActivity(), R.layout.adapter_easy_item, beanList) {
            @Override
            public void convert(BaseViewHolder holder, final OaWillDo.ResultBean resultBean) {
                holder.setText(R.id.textView, resultBean.getContent());
                holder.setOnClickListener(R.id.textView, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), OaDetailActivity.class);
                        intent.putExtra("bean",resultBean);
                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(baseAdapter);
        oaWillListPresenter = new OaWillListPresenter(getActivity(), this);
        oaWillListPresenter.getOaWillList(type,type1, start, limit);
        setClient();
    }

    /**
     * 滑动监听
     */
    private void setClient() {
        recyclerView.setOnLoadListener(new CustomRefreshView.OnLoadListener() {
            @Override
            public void onRefresh() {
                beanList.clear();
                start = 0;
                limit = 20;
                oaWillListPresenter.getOaWillList(type,type1, start, limit);
            }

            @Override
            public void onLoadMore() {
                start = limit;
                limit += 25;
                oaWillListPresenter.getOaWillList(type,type1, start, limit);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setOaWillList(OaWillDo oaWillList) {
        for (int i = 0; i < oaWillList.getResult().size(); i++) {
            beanList.add(oaWillList.getResult().get(i));
        }
        baseAdapter.notifyDataSetChanged();
        if (oaWillList.getResult().size() == 0 && beanList.size() == 0) {
            if (recyclerView != null) {
                recyclerView.setVisibility(View.GONE);
                llNoContent.setVisibility(View.VISIBLE);
            }
        } else if (oaWillList.getResult().size() == 0 && beanList.size() != 0) {
            if (recyclerView != null) {
                recyclerView.complete();
                recyclerView.onNoMore();
            }
        }
        if (oaWillList.getResult().size() < 20) {
            if (recyclerView != null) {
                baseAdapter.notifyDataSetChanged();
                recyclerView.complete();
                recyclerView.onNoMore();
            }
        } else {
            if (recyclerView != null) {
                baseAdapter.notifyDataSetChanged();
                recyclerView.complete();
            }
        }
    }

    @Override
    public void setOaWillListMessage(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }
}

package com.smartbus.heze.checkup.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.smartbus.heze.R;
import com.smartbus.heze.checkup.bean.HealthItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/7/11.
 */

public class HealthAdapter1 extends BaseAdapter {

    Context context;
    List<HealthItem.ResultBean> beanList = new ArrayList<>();
    public GetItemPosition getItemPosition;
    private LayoutInflater mInflater;//布局装载器对象

    public interface GetItemPosition {
        void getPosition(int position, String money, String tag);
    }

    public void setOnInnerItemOnClickListener(GetItemPosition getItemPosition) {
        this.getItemPosition = getItemPosition;
    }

    public HealthAdapter1(Context context, List<HealthItem.ResultBean> beanList) {
        this.context = context;
        this.beanList = beanList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //将布局文件转化为View对象
        View view = mInflater.inflate(R.layout.adapter_chlid_item,null);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        RadioButton rb1 = (RadioButton) view.findViewById(R.id.rb1);
        final RadioButton rb2 = (RadioButton) view.findViewById(R.id.rb2);
        tvName.setText("扣分");
        textView.setText(beanList.get(position).getProjectName());
        editText.setText(beanList.get(position).getScore());
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                getItemPosition.getPosition(position, editText.getText().toString(), "rb3");
                rb2.setChecked(true);
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItemPosition.getPosition(position, editText.getText().toString(), "rb2");
            }
        });
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItemPosition.getPosition(position, "0", "rb1");
            }
        });

        return view;
    }
}

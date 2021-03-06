package com.smartbus.heze.exam.module;

import com.smartbus.heze.exam.bean.SendNum;
import com.smartbus.heze.http.base.BaseDView;
import com.smartbus.heze.http.base.BasePresenter;

/**
 * Created by Administrator on 2019/4/11.
 */

public interface SendNumContract {
    interface View extends BaseDView<presenter> {
        //预算单
        void setSendNum(SendNum s);
        void setSendNumMessage(String s);
    }

    interface presenter extends BasePresenter {
        void getSendNum(String startTime, String endTime);
    }
}

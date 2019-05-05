package com.smartbus.heze;

/**
 * @author: Allen.
 * @date: 2018/3/8
 * @description: 所有接口地址集
 */

public class ApiAddress {

    public final static String mainApi = "http://192.168.2.132:8080/joffice21/" ;
//    public final static String mainApi = "http://120.192.74.58:8080/joffice/" ;

    /***********************首页*******************************/
    //登录
    public final static String login = "mobile.do";
    //公告列表
    public final static String notice = "info/newAppNews.do";
    //banner
    public final static String banner = "LineServer/docManage/DocManage!jsonModule.action";

    //部门列表
    public final static String department = "system/getAppDepStoreOrganization.do";

    //一级审批人
    public final static String oneperson = "flow/startTransProcessActivity.do";

    //二级审批人
    public final static String twoperson = "flow/mobileUsersProcessActivity.do";

    //提交预算单
    public final static String upysd = "flow/saveProcessActivity.do";

}

package com.mr235.androidcodesnippet.db.table;

/**
 * Created by Administrator on 2015/10/19.
 * 支付信息回调
 */
public interface PayStatusTable {

    /** 表名 */
    String TABLE_NAME = "pay_status";
    // 支付ID
    String PAY_ID ="PAY_ID";
    // 支付宝支付信息
    String BACK_INFO ="back_info";
    // 请求时间
    String REQ_TIME ="req_time";
    // 请求次数
    String REQ_COUNT ="req_count";
    // 支付类型：
    String PAY_TYPE ="pay_type";
}

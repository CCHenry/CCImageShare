package com.example.henryzheng.ccimageshare.M.ZuiMeiModel;

import com.example.henryzheng.ccimageshare.M.NetWork.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by henryzheng on 2016/12/6.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class ZuiMeiTotayListResponse {
    private String reason;
    private Data data;
    private int result;
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getReason() {
        return reason;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }

    public void setResult(int result) {
        this.result = result;
    }
    public int getResult() {
        return result;
    }
}

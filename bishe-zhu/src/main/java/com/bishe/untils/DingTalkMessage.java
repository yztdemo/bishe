package com.bishe.untils;


import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class DingTalkMessage {

    @Value("${ding.curl}")
    private String curl;


    public void sendDingMessage(String message,String curl) {
        try {
            DefaultDingTalkClient dingTalkClient = new DefaultDingTalkClient(curl);
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype("text");
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            text.setContent("教师修改信息:  "+message);
            request.setText(text);
            OapiRobotSendResponse response = dingTalkClient.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public void sendChangMsg(String s) {
        sendDingMessage(s,curl);
    }
}

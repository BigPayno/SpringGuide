package com.payno.rocketmq.type;

import lombok.Builder;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.Map;

import static com.payno.rocketmq.type.Constant.TOPIC;

/**
 * @author payno
 * @date 2019/11/25 15:14
 * @description
 */
public class MsgFactory {
    public static Message index(int num){
        Message message=null;
        try{
            message=new Message(
                    TOPIC,
                    "TEST",
                    "KEYS_"+String.valueOf(num),
                    String.format("HELLO！%d",num).getBytes(RemotingHelper.DEFAULT_CHARSET));
        }catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }
}

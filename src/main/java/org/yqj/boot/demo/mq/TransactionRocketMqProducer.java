package org.yqj.boot.demo.mq;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2020/6/17
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class TransactionRocketMqProducer {

    private TransactionMQProducer producer;

    private String topic;

    public TransactionRocketMqProducer(TransactionMQProducer producer, String topic) {
        this.producer = producer;
        this.topic = topic;
    }

    public SendResult pubTransactionMsg(String key, JSONObject jsonObject, Object object) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return null;
        }
        String jsonStr = jsonObject.toJSONString();
        Message msg = new Message(topic, jsonStr.getBytes());
        if(StringUtils.isNotEmpty(key)) {
            msg.setKeys(key);
        }
        log.info("publish message: {} : {}", topic, jsonStr);
        SendResult sendResult;
        try {
            sendResult = this.producer.sendMessageInTransaction(msg, object);
        } catch (Exception e) {
            log.error("rocketmq pub error, topic:{}, key:{}, payload:{}", topic, key, jsonStr, e);
            return null;
        }
        return sendResult;
    }
}

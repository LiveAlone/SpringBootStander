package org.yqj.boot.demo.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2020/6/17
 * Email: yaoqijunmail@foxmail.com
 */
@Component
@Slf4j
public class NotePostedTransactionListener implements TransactionListener {

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        log.info("********* executeLocalTransaction message:{} o: {}", message, o);
        Map<String, String> params = (Map<String, String>) o;
        if ("commit".equals(params.get("transaction"))) {
            return LocalTransactionState.COMMIT_MESSAGE;
        }else {
            return LocalTransactionState.UNKNOW;
        }
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        try {
            log.info("**************** checkLocalTransaction ext:{}", messageExt);
            return LocalTransactionState.COMMIT_MESSAGE;
        } catch (Exception e) {
            log.error("note posted validate error, body:{} cause:", messageExt.getBody(), e);
            return LocalTransactionState.UNKNOW;
        }
    }
}

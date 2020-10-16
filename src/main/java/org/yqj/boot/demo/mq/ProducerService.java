package org.yqj.boot.demo.mq;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2020/10/16
 * Email: yaoqijunmail@foxmail.com
 */
@Component
@Slf4j
public class ProducerService {

    @Resource
    private TransactionRocketMqProducer transactionRocketMqProducer;

    public void produceTest(String commit) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test", "test");
        transactionRocketMqProducer.pubTransactionMsg("test", jsonObject, ImmutableMap.of("transaction", commit));
    }

}

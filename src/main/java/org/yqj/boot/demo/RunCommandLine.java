package org.yqj.boot.demo;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yqj.boot.demo.mq.TransactionRocketMqProducer;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2018/8/15
 * Email: yaoqijunmail@foxmail.com
 */
@Component
@Slf4j
public class RunCommandLine implements CommandLineRunner{

    @Override
    public void run(String... args) throws Exception {
    }
}

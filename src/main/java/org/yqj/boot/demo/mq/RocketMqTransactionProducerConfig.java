package org.yqj.boot.demo.mq;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.xiaohongshu.infra.utils.rocketmq.RocketMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author csun
 * @date 2018-06-13
 */
@Configuration
@Slf4j
public class RocketMqTransactionProducerConfig extends RocketMQConfig {

    @Value("${rocketmq.namesrvAddr}")
    private String namesrvAddr;

    @Resource
    private NotePostedTransactionListener notePostedTransactionListener;

    private TransactionRocketMqProducer createTransactionProducer(String topic, String prefix, TransactionListener transactionListener, ExecutorService executorService) throws MQClientException {
        TransactionMQProducer producer = new TransactionMQProducer(prefix + topic);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        producer.start();
        return new TransactionRocketMqProducer(producer, topic);
    }

    @Bean
    public TransactionRocketMqProducer notePostedProducer() throws MQClientException {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("note-posted-rocket-mq-transaction-thread-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(8, 16,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5000), namedThreadFactory);
        return createTransactionProducer("LocalTransactionCommitTest", "Local_Yao", notePostedTransactionListener, executorService);
    }
}

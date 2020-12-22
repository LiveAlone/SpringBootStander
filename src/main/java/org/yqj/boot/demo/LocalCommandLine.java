package org.yqj.boot.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2020/12/22
 * Email: yaoqijunmail@foxmail.com
 */
@Component
@Slf4j
public class LocalCommandLine implements CommandLineRunner {

    @Value("${application.name}")
    private String applicationName;

    @Override
    public void run(String... args) throws Exception {
        log.info("local command line runner application name is :{}", applicationName);
    }
}

package org.yqj.boot.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.concurrent.ThreadLocalRandom;

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
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        mBeanServer.registerMBean(new ServerInfo(), new ObjectName("serverInfoMBean:name=serverInfo"));
    }

    public static class ServerInfo implements ServerInfoMBean{
        @Override
        public int getCmdCount(){
            return ThreadLocalRandom.current().nextInt(1000);
        }

        @Override
        public void printTestContent(String content) {
            System.out.println(content);
        }
    }

    public interface ServerInfoMBean {

        int getCmdCount();

        void printTestContent(String content);
    }
}

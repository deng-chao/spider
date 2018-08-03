package name.dengchao.spider;

import lombok.extern.slf4j.Slf4j;
import name.dengchao.spider.spider.Spider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Booter implements InitializingBean {

    // -Dcom.sun.management.jmxremote.port=9999
    // -Dcom.sun.management.jmxremote.authenticate=false
    // -Dcom.sun.management.jmxremote.ssl=false
    // -Xmx1024m
    // -javaagent:C:\Users\izene\workspace\myagent\myagent.jar

    public static void main(String[] args) {
        System.getProperties().put("org.apache.commons.logging.simplelog.defaultlog", "fatal");
        SpringApplication.run(Booter.class, args);
    }

    @Autowired
    Spider spider;

    @Override
    public void afterPropertiesSet() throws Exception {
        Thread t = new Thread(spider);
        t.start();
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 70000; i++) {
                String url = "http://www.xiaohuasheng.cn/read/userbooklist_" + i;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("offer url: " + url);
                spider.getToVisitUrls().offer(url);
            }
        });
        producer.start();
    }
}

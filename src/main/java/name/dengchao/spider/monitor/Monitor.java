package name.dengchao.spider.monitor;

import name.dengchao.spider.MyShutdownHook;
import name.dengchao.spider.spider.Spider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.concurrent.BlockingQueue;

@Component
public class Monitor implements MonitorMBean, InitializingBean {

    @Value("#{iter['filter-file.path']}")
    private String filterFilePath;

    @Value("#{iter['to-visit-url.path']}")
    private String toVisitUrlFile;

    @Autowired
    private MyShutdownHook hook;

    @Resource(name = "urlQueue")
    private BlockingQueue<String> toVisitUrls;

    @Autowired
    private Spider spider;

    @Override
    public int getToVisitUrlsSize() {
        return toVisitUrls.size();
    }

    @Override
    public String getBloomFilterSize() {
        return "0";
    }

    @Override
    public long getGrapedSize() {
        return spider.visitedUrlCnt;
    }

    @Override
    public void addToVisitUrl(String url) {
        toVisitUrls.add(url);
    }

    @Override
    @ManagedOperation
    public void exit(int sign) {
        spider.setGoon(false);
        hook.run();
        System.exit(sign);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName monitorName = new ObjectName("name.dengchao:spider=Monitor");
        server.registerMBean(this, monitorName);
    }
}

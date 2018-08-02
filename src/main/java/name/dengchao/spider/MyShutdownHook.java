package name.dengchao.spider;

import com.google.common.hash.BloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import name.dengchao.spider.spider.Spider;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.concurrent.BlockingQueue;

@Component
public class MyShutdownHook extends Thread {

    @Resource(name = "urlQueue")
    private BlockingQueue<String> toVisitUrls;

    @Autowired
    private BloomFilter<CharSequence> filter;

    @Value("#{iter['filter-file.path']}")
    String filterFilePath;

    @Value("#{iter['to-visit-url.path']}")
    String toVisitUrlFile;

    @Value("#{iter['counter.path']}")
    private String counterPath;

    @Autowired
    Spider spider;

    @Override
    public void run() {
        try {
            File f = new File(filterFilePath);
            filter.writeTo(new FileOutputStream(f));
            BufferedWriter bw = new BufferedWriter(new FileWriter(toVisitUrlFile));
            for (String toVisitUrl : toVisitUrls) {
                bw.write(toVisitUrl);
                bw.write(System.lineSeparator());
            }
            bw.flush();
            bw.close();
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(counterPath));
            bw2.write(String.valueOf(spider.visitedUrlCnt));
            bw2.flush();
            bw2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package name.dengchao.spider;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.google.common.io.Files;
import com.thoughtworks.xstream.XStream;
import name.dengchao.spider.domain.SaverConverter;
import name.dengchao.spider.domain.Url;
import name.dengchao.spider.domain.UrlMatcher;
import name.dengchao.spider.domain.Urls;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
@ComponentScan("name.dengchao.spider")
public class SpiderConfig implements InitializingBean {

    @Autowired
    SaverConverter saverConverter;

    @Bean(name = "urlMap")
    public Multimap<UrlMatcher, Url> map() throws IOException {

        XStream xs = new XStream();
        xs.processAnnotations(new Class[] { Urls.class });
        xs.registerConverter(saverConverter);
        Urls config = new Urls();
        Resource res = new ClassPathResource("NewFile.xml");
        config = (Urls) xs.fromXML(res.getInputStream(), config);
        List<Url> urls = config.getUrls();

        Multimap<UrlMatcher, Url> multimap = MultimapBuilder.hashKeys(16).arrayListValues().build();
        for (Url url : urls) {
            if (url.getMatcher().getScheme() == null) {
                url.getMatcher().setScheme("http");
            }
            if (url.getMatcher().getPort() == 0) {
                url.getMatcher().setPort(-1);
            }
            multimap.put(url.getMatcher(), url);
        }
        return multimap;
    }

    @Value("#{iter['to-visit-url.path']}")
    String toVisitUrlFile;

    @Value("#{iter['starter.path']}")
    String startPoint;

    @Bean(name = "urlQueue")
    public BlockingQueue<String> urlQueue() throws Exception {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        File f = new File(toVisitUrlFile);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
        queue.addAll(Files.readLines(f, StandardCharsets.UTF_8));

        File startPointFile = new File(startPoint);
        if (!startPointFile.exists()) {
            startPointFile.getParentFile().mkdirs();
            startPointFile.createNewFile();
        }
        queue.addAll(Files.readLines(startPointFile, StandardCharsets.UTF_8));
        return queue;
    }

    @Value("#{iter['filter-file.path']}")
    String filterFilePath;

    @Bean
    public BloomFilter<CharSequence> visitedUrlFilter() throws IOException {
        File filterFile = new File(filterFilePath);
        BloomFilter<CharSequence> filter = null;
        if (!filterFile.exists()) {
            filterFile.getParentFile().mkdirs();
            filterFile.createNewFile();
            filter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), 10_000_000, 0.00001);
            filter.writeTo(new FileOutputStream(filterFile));
        }
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(filterFile));
        filter = BloomFilter.readFrom(bin, Funnels.stringFunnel(StandardCharsets.UTF_8));
        bin.close();
        return filter;
    }

    @Bean(name = "iter")
    public PropertiesFactoryBean iter() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("iter.properties"));
        bean.setFileEncoding(StandardCharsets.UTF_8.name());
        return bean;
    }

    @Bean
    public HttpClientBuilder httpClientBuilder() {
        RequestConfig reqconfig = RequestConfig.custom().setConnectTimeout(60_000).setSocketTimeout(60_000)
                .setConnectionRequestTimeout(60_000).build();
        return HttpClientBuilder.create().setDefaultRequestConfig(reqconfig);
    }

    @Autowired
    MyShutdownHook shutdownHook;

    @Override
    public void afterPropertiesSet() throws Exception {
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }
}

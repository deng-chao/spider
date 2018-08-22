package name.dengchao.spider.monitor;

public interface MonitorMBean {

    int getToVisitUrlsSize();

    String getBloomFilterSize();

    long getGrapedSize();

    void addToVisitUrl(String url);

    void exit(int sign) throws Exception;
}

package name.dengchao.spider.spider;

public interface VisitedUrlRecorder {

    boolean visited(String url);

    void record(String url);
}

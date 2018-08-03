package name.dengchao.spider.msg;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.BlockingQueue;

@Component
public class LocalQueueListener implements Listener {

    @Getter
    @Resource(name = "urlQueue")
    private BlockingQueue<String> toVisitUrls;

    @Setter
    private Handler handler;

    @Override
    public void start() {
        try {
            String url = toVisitUrls.take();
            handler.handle(url);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

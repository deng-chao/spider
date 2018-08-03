package name.dengchao.spider.msg;

import com.hujiang.basic.framework.plugin.mq.MqPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.StandardEnvironment;

public class RabbitMqListener implements Listener {

    @Autowired
    private StandardEnvironment env;

    private Handler handler;

    @Override
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void start() {
        com.hujiang.basic.framework.plugin.mq.consume.Handler<String> h = msg -> handler.handle(msg);
        MqPlugin.init(env).listener().threads(1).handler(h).build();
    }
}

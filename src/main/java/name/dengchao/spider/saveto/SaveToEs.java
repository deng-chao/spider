package name.dengchao.spider.saveto;

import java.net.InetAddress;
import java.util.Date;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.alibaba.fastjson.JSONObject;

public class SaveToEs implements SaveTo {

    Client client;

    String index;
    String type;
    
    @Override
    public void save(JSONObject json) throws Exception {
        IndexRequest request = new IndexRequest();
        request.index(index).type(type).source(json);
        client.index(request);
    }

    @Override
    public void setPath(String path) {
        String parts[] = path.split("/");
        index = parts[0];
        type = parts[1];
    }

    private String esHost = "192.168.36.158";
    private int esPort = 9300;
    private String clusterName = "lambo";

    @Override
    public void initial() throws Exception {
        Settings settings = Settings.settingsBuilder().put("cluster.name", clusterName)
                .put("client.transport.ping_timeout", "30s")
                .put("client.transport.sniff", false).build();

        TransportClient client = TransportClient.builder().settings(settings).build();
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort));
        this.client = client;
    }

    public static void main(String[] args) throws Exception {
        SaveToEs saveToEs = new SaveToEs();
        saveToEs.initial();
        JSONObject json = new JSONObject();
        json.put("url", "www.baidu.com");
        json.put("content", "老王是一个牛逼的老王, 老黄也是一个牛逼的老黄");
        json.put("date", new Date());
        saveToEs.save(json);
    }
}

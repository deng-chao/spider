package name.dengchao.spider.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

@XStreamAlias("header")
public class ReqHeader {

    @XStreamAsAttribute
    String key;

    @XStreamAsAttribute
    String val;

    public String getKey() {
        return key;
    }

    public String getVal() {
        return val;
    }

    public Header toHttpHeader() {
        return new BasicHeader(key, val);
    }
}
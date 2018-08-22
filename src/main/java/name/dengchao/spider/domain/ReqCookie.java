package name.dengchao.spider.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("cookie")
public class ReqCookie {

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
}
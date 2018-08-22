package name.dengchao.spider.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XStreamAlias("request")
public class Request {

    @XmlElement
    List<ReqHeader> headers;

    @XmlElement
    List<ReqCookie> cookies;

    public List<ReqHeader> getHeaders() {
        return headers;
    }

    public List<ReqCookie> getCookies() {
        return cookies;
    }
}
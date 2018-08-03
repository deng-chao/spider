package name.dengchao.spider.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;

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
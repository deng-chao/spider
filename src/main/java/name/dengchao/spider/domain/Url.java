package name.dengchao.spider.domain;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("url")
public class Url {

	UrlMatcher matcher;
	
	Request request;
	
	Saver saver;
	
	List<Processor> processors;

	public UrlMatcher getMatcher() {
		return matcher;
	}

	public Request getRequest() {
		return request;
	}

	public List<Processor> getProcessors() {
		return processors;
	}

	public Saver getSaver() {
		return saver;
	}
}

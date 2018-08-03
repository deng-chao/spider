package name.dengchao.spider.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("urls")
public class Urls {

	@XmlElement
	@XStreamImplicit
	List<Url> urls;

	public List<Url> getUrls() {
		return urls;
	}
}
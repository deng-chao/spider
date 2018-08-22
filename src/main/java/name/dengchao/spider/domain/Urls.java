package name.dengchao.spider.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XStreamAlias("urls")
public class Urls {

    @XmlElement
    @XStreamImplicit
    List<Url> urls;

    public List<Url> getUrls() {
        return urls;
    }
}
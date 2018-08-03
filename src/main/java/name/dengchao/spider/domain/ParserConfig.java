package name.dengchao.spider.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("parser")
public class ParserConfig {

    @XStreamAsAttribute
    private String type;
}

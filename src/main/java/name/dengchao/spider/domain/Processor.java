package name.dengchao.spider.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;

@Data
@XStreamAlias("processor")
public class Processor {

    @XStreamAsAttribute
    String xpath;

    @XStreamAsAttribute
    String op;

    @XStreamAsAttribute
    String tag;

    @XStreamAsAttribute
    String val;

    @XStreamAsAttribute
    boolean require;
}
package name.dengchao.spider.spider;

import name.dengchao.spider.domain.Processor;

public interface ProcessorMatcher {

	Processor match(String url);
}

package iter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import name.dengchao.spider.SpiderConfig;
import name.dengchao.spider.spider.Spider;

public class ConfigBasedSpiderTest {

	ApplicationContext ctx = new AnnotationConfigApplicationContext(SpiderConfig.class);
	
	//-Dcom.sun.management.jmxremote.port=9999
	//-Dcom.sun.management.jmxremote.authenticate=false
	//-Dcom.sun.management.jmxremote.ssl=false
	//-Xmx1024m
	//-javaagent:C:\Users\izene\workspace\myagent\myagent.jar
	
	@Test
	public void testGrapHtml() throws Exception {
		Spider spider = ctx.getBean(Spider.class);
		spider.start();
		Thread.currentThread().join();
	}
	
	@Test
	public void testUrlMatch() throws Exception {
		Pattern p = Pattern.compile("/\\w+-?\\w+/\\d+\\.html");
		Matcher m = p.matcher("/hangzhou-yhq/77928188.html");
		System.out.println(m.find());
	}
}

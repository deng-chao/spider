package name.dengchao.spider;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import name.dengchao.spider.spider.Spider;

@SpringBootApplication
public class Booter implements InitializingBean {

	// -Dcom.sun.management.jmxremote.port=9999
	// -Dcom.sun.management.jmxremote.authenticate=false
	// -Dcom.sun.management.jmxremote.ssl=false
	// -Xmx1024m
	// -javaagent:C:\Users\izene\workspace\myagent\myagent.jar
	
	public static void main(String[] args) {
	    System.getProperties().put("org.apache.commons.logging.simplelog.defaultlog", "fatal");
		SpringApplication.run(Booter.class, args);
	}
	
	@Autowired
	Spider spider;

	@Override
	public void afterPropertiesSet() throws Exception {
		Thread t = new Thread(spider);
		t.start();
	}
}

package iter;

import java.util.List;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import name.dengchao.spider.domain.Urls;
import name.dengchao.spider.domain.SaverConverter;
import name.dengchao.spider.domain.Url;

import com.thoughtworks.xstream.XStream;

public class XmlConverterTest {

	@Test
	public void testReaderXml() throws Exception {

	    XStream xs = new XStream();
	    xs.registerConverter(new SaverConverter());
	    xs.processAnnotations(new Class[] { Urls.class });
	    Urls config = new Urls();
	    Resource res = new ClassPathResource("NewFile.xml");
	    config = (Urls) xs.fromXML(res.getInputStream(), config);
	    List<Url> urls = config.getUrls();
	    for (Url url : urls) {
	        System.out.println(url.getProcessors().get(0).getXpath());
			System.out.println(url.getSaver());
		}
	}
}

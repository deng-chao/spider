package name.dengchao.spider;

import name.dengchao.spider.spider.Spider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    Spider spider;

    @RequestMapping(value = "/ctrl/urls", method = RequestMethod.POST)
    public void addUrls(@RequestParam("url") String url) {
        spider.addTovisitUrl(url);
    }

}

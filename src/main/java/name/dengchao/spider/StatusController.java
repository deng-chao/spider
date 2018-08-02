package name.dengchao.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import name.dengchao.spider.spider.Spider;

import com.alibaba.fastjson.JSONObject;

@RestController
public class StatusController {

	@Autowired
	Spider spider;

	@RequestMapping(value = "status", method = RequestMethod.GET)
	public JSONObject status() {
		JSONObject jobj = new JSONObject();
		jobj.put("Visited Url Cnt", spider.visitedUrlCnt);
		return jobj;
	}
}

package name.dengchao.spider.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import name.dengchao.spider.saveto.SaveTo;

@XStreamAlias("saver")
public class Saver {

	@XStreamAsAttribute
	private SaveTo val;
	
	@XStreamAsAttribute
	private String path;

	public SaveTo getVal() {
		return val;
	}

	public void setVal(SaveTo val) {
		this.val = val;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}

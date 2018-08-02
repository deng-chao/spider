package name.dengchao.spider.domain;

import java.util.Objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("matcher")
public class UrlMatcher {

	@XStreamAsAttribute
	String scheme = "http";

	@XStreamAsAttribute
	String host;

	@XStreamAsAttribute
	int port = -1;

	@XStreamAsAttribute
	String path;

	public String getScheme() {
		return scheme;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getPath() {
		return path;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public int hashCode(){
		return Objects.hash(scheme, host, port);
	}
	
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof UrlMatcher)){
			return false;
		}
		UrlMatcher other = (UrlMatcher) obj;
		return Objects.equals(this.scheme, other.scheme) && 
				Objects.equals(this.host, other.host) && 
				Objects.equals(this.port, other.port);
	}
	
	public String toUrl(){
		return scheme + "://" + host + (port == -1 || port == 80 ? "" : ":" + port) + path;
	}
}
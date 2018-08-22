package name.dengchao.spider.saveto;

import com.alibaba.fastjson.JSONObject;

/**
 * The implementions of this interface should have a default constructor,
 *
 * @author Administrator
 */
public interface SaveTo {

    void save(JSONObject json) throws Exception;

    void setPath(String path);

    void initial() throws Exception;
}

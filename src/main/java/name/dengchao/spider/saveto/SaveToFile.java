package name.dengchao.spider.saveto;

import com.alibaba.fastjson.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SaveToFile implements SaveTo {

    private String path;

    private Path p;

    @Override
    public void save(JSONObject json) throws Exception {
//        if (json.getString("content") == null) {
//            return;
//        }
        String toWrite = json.toJSONString() + System.lineSeparator();
        if (!Files.exists(p)) {
            Files.createFile(p);
        }
        Files.write(p, toWrite.getBytes(), StandardOpenOption.APPEND);
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void initial() throws Exception {
        p = Paths.get(path);
    }
}

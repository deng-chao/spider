package name.dengchao.spider.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.*;

public class DataFormatter {

    @Test
    public void format() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("F:\\dengchao\\Desktop\\book_content.dic"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("F:\\dengchao\\Desktop\\book_content.csv"));
        String line = null;
        while ((line = br.readLine()) != null) {
            JSONObject json = JSON.parseObject(line);
            String author = json.getString("author");
            if (author == null) {
                continue;
            }
            if (author != null) {
                author = author.replaceAll("\\n", "").replaceAll("\n", "").replaceAll(",", ";").trim();
                json.put("author", author);
            }
            String isbn = json.getString("isbn");
            isbn = isbn == null ? "无" : isbn;
            String press = json.getString("press");
            if (press != null) {
                press = press.replaceAll("\\n", "").replaceAll("\n", "").replaceAll(",", ";").trim();
                json.put("press", press);
            }
            String desc = json.getString("desc");
            if (desc != null) {
                desc = desc.replaceAll("\\n", "").replaceAll("\n", "").replaceAll(",", ";").trim();
                json.put("desc", desc);
            }
            String csvLine = author + ", " + isbn + ", " + press + ", " + desc + System.lineSeparator();
            System.out.println(csvLine);
            bw.write(csvLine);
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

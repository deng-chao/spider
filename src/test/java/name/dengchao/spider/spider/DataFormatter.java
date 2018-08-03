package name.dengchao.spider.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
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
            if (isbn != null) {
                isbn = isbn.replaceAll("\\n", "").replaceAll("\n", "").replaceAll(",", ";").trim();
                json.put("isbn", isbn);
            }
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
            String imageUrl = json.getString("imageUrl");
            String title = json.getString("title");
            if (title != null) {
                title = title.replaceAll("\\n", "").replaceAll("\n", "").replaceAll(",", ";").trim();
                json.put("desc", title);
            }
            String url = json.getString("url");
            String csvLine = title + "," + author + "," + url + "," + isbn + "," + press + "," + imageUrl + "," + json.getString("Evaluation") + "," + desc + System.lineSeparator();
            System.out.println(csvLine);
            bw.write(csvLine);
        }
        bw.flush();
        bw.close();
        br.close();
    }

    @Test
    public void formatBookList() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("F:\\dengchao\\Desktop\\book_list.dic"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("F:\\dengchao\\Desktop\\book_list.csv"));
        String line = null;
        while ((line = br.readLine()) != null) {
            JSONObject json = JSON.parseObject(line);
            String title = json.getString("title");
            if (title == null) {
                continue;
            }
            Object tags = json.get("tags");
            String tagsStr = "";
            if (tags instanceof JsonArray) {
                tagsStr = tags.toString();
            }
            String csvLine = json.getString("url") + "," + title + "," + json.getString("time") + "," + json.getString("user") + "," + json.getString("desc") + "," + json.get("tags") + System.lineSeparator();
            bw.write(csvLine);
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

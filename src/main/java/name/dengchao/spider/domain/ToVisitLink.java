package name.dengchao.spider.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ToVisitLink {
    private String url;
    private String from;
    private Date createTime;
}

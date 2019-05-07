package entity;

import lombok.Data;

import java.util.List;

/**
 * @Description: 分页结果集
 * @author: Arnold
 * @since: 2019/5/7 21:23
 * @version: v1.0.0
 */
@Data
public class PageResult<T> {
    private Long total;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}

package entity;

import jdk.net.SocketFlow;
import lombok.Data;

/**
 * @Description: 前端返回通用结果集
 * @author: Arnold
 * @since: 2019/5/7 21:06
 * @version: v1.0.0
 */
@Data
public class Result {
    private boolean flag;
    private Integer code;
    private String message;
    private Object data;

    public Result() {
    }

    public  Result(String message) {
        this(true, StatusCode.OK, message);
    }

    public Result(String message, Object data) {
        this(true, StatusCode.OK, message, data);
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}

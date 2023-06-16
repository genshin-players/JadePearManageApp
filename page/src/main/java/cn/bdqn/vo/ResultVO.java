package cn.bdqn.vo;

import lombok.Data;
import java.io.Serializable;
/**
 * 用来把字符串转换成json的
 * @param <T>
 */
@Data
public class ResultVO<T> implements Serializable {
    private String code;
    private String msg;
    private T data;
}


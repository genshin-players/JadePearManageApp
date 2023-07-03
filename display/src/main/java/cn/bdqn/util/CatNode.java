package cn.bdqn.util;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 商品分类模型
 */
public class CatNode implements Serializable {

    private static final long serialVersionUID = 5747987393315287054L;

    @JsonProperty(value="n")
    private String name;
    @JsonProperty(value = "i")
    private List<?> item;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<?> getItem() {
        return item;
    }

    public void setItem(List<?> item) {
        this.item = item;
    }
}

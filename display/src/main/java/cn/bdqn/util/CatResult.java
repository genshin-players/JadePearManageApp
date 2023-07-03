package cn.bdqn.util;

import java.io.Serializable;
import java.util.List;

public class CatResult implements Serializable {
    private static final long serialVersionUID = 6491837307317707524L;
    private List<?> data;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}

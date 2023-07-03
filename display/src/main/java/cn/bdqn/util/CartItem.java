package cn.bdqn.util;

import java.io.Serializable;

/**
 * 购物车实体类
 */
public class CartItem implements Serializable {
    private Long id;
    private String title;
    private String sellPoint;
    private String image;
    private Long price;
    private int num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sellPoint='" + sellPoint + '\'' +
                ", image='" + image + '\'' +
                ", num=" + num +
                ", price=" + price +
                '}';
    }
}

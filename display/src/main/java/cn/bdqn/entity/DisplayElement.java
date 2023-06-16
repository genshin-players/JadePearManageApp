package cn.bdqn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 显示元素
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DisplayElement extends Model<DisplayElement> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 显示id
     */
    private Integer displayId;

    /**
     * 元素类型id
     */
    private Integer elementTypeId;

    /**
     * 顺序（第一段，第二段等等）
     */
    private Integer routine;

    /**
     * 值
     */
    private String value;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}

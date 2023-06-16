package com.dddqmmx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer level;

    private Integer parentId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

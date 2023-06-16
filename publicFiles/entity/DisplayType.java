package com.dddqmmx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 显示类型
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DisplayType extends Model<DisplayType> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 例:班级上课展示、师资力量、校园背景、学员作品展示、学校通报、排班汇报
     */
    private String name;

    /**
     * 是否为对内展示，1.对内（true） 2.对外（false）
     */
    private Boolean isInner;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

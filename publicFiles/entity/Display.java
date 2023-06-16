package com.dddqmmx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 显示内容
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Display extends Model<Display> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容类型
     */
    private Integer displayTypeId;

    /**
     * 封面图片（待定）
     */
    private String coverImage;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer publishUserId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

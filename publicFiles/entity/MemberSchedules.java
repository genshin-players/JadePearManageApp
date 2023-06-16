package com.dddqmmx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 学社成员工作安排
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberSchedules extends Model<MemberSchedules> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 工作学社成员id
     */
    private Integer memberId;

    /**
     * 工作日期
     */
    private LocalDate date;

    /**
     * 工作类型id
     */
    private Integer workTypeId;

    private LocalDate createTime;

    private LocalDate updateTime;

    private Integer createUserId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

package cn.bdqn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 班级
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Classes extends Model<Classes> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    /**
     * 教员id
     */
    private Integer instructorId;

    /**
     * 辅导员id(班主任)
     */
    private Integer adviserId;

    private LocalDate createTime;

    /**
     * 班级类型id
     */
    private Integer subjectId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

package cn.bdqn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 出勤
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Attendence extends Model<Attendence> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 班级id
     */
    private Integer classId;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 日期，格式”yyyy-MM-dd HH:mm:sss“
     */
    private LocalDateTime date;

    /**
     * 0.缺勤 1.迟到 2.在 3.请假
     */
    private Integer isPresent;

    /**
     * 汇报者id
     */
    private Integer reportUserId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

package cn.bdqn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 报名
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SignUp extends Model<SignUp> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 辅导员（班主任）id
     */
    private Integer adivserId;

    /**
     * 班级id
     */
    private Integer classId;

    /**
     * 活动id
     */
    private Integer activityId;

    /**
     * 报名学生id
     */
    private Integer signupStudentId;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}

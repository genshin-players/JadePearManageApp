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
 * 用户（所有学生，老师，管理员）
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Users extends Model<Users> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private Integer roleId;

    /**
     * json格式，账号信息，包含用户名密码（待定）
     */
    private String accountInfo;

    /**
     * json格式，身份信息，包含姓名、年龄、性别、联系方式等
     */
    private String identityInfo;

    private LocalDate createTime;

    private LocalDate updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

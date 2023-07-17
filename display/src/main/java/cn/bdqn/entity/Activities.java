package cn.bdqn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author dddqmmx
 * @since 2023-07-12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Activities extends Model<Activities> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer displayId;

    /**
     * 报名人数
     */
    private Integer signupNumber;

    /**
     * 报名开始时间，格式”yyyy-MM-dd HH:mm:sss“
     */
    private Date startTime;

    /**
     * 报名截止时间，格式”yyyy-MM-dd HH:mm:sss“
     */
    private Date endTime;

    /**
     * 点赞（热度）
     */
    private Integer likes;

    /**
     * 创建日期
     */
    private LocalDate createTime;

    /**
     * 更新日期
     */
    private LocalDate updateTime;

    /**
     * 1代表TRUE(生效),0代表FALSE（不生效）
     */
    private Boolean isActive;

    private Integer typeId;

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}

package cn.bdqn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.*;

/**
 * <p>
 * 显示内容
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Display extends Model<Display> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    private String content;

    /**
     * 内容类型
     */
    private Integer displayTypeId;

    /**
     * 封面图片（待定）
     */
    private String coverImage;

    private Date createTime;

    private Date updateTime;

    private Integer publishUserId;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}

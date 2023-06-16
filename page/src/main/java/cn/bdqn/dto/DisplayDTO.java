package cn.bdqn.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
@NoArgsConstructor
@AllArgsConstructor
public class DisplayDTO  {

    private Integer id;
    private String title;
    private String content;
    private Integer displayTypeId;
    private String coverImage;
    private Date createTime;
    private Date updateTime;
    private Integer publishUserId;
}

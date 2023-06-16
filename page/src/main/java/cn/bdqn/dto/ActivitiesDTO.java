package cn.bdqn.dto;

import lombok.Data;

import java.util.Date;
@Data
public class ActivitiesDTO {
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
    private Date createTime;

    /**
     * 更新日期
     */
    private Date updateTime;

    /**
     * 1代表TRUE(生效),0代表FALSE（不生效）
     */
    private Boolean isActive;

    private DisplayDTO display;
}

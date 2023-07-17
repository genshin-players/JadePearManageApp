package cn.bdqn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author dddqmmx
 * @since 2023-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ActivitiesType extends Model<ActivitiesType> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}

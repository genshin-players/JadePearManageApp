package cn.bdqn.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* <p>
    * 角色
    * </p>
*
* @author ljj
* @since 2023-06-11
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;


}

package cn.bdqn.vo;

import cn.bdqn.entity.Classes;
import cn.bdqn.entity.Schedules;
import cn.bdqn.entity.Users;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 社员负责班级的列表
 */
@Data
public class MemberWorkClassVO {
    private Users member;
    private List<Map<String,Object>> workClassesAndStudents;
    private Schedules schedules;
}

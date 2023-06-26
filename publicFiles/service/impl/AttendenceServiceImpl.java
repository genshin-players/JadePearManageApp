package cn.bdqn.service.impl;

import cn.bdqn.entity.Attendence;
import cn.bdqn.mapper.AttendenceMapper;
import cn.bdqn.service.IAttendenceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出勤 服务实现类
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
@Service
public class AttendenceServiceImpl extends ServiceImpl<AttendenceMapper, Attendence> implements IAttendenceService {

}

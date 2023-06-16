package com.dddqmmx.service.impl;

import com.dddqmmx.entity.Attendence;
import com.dddqmmx.mapper.AttendenceMapper;
import com.dddqmmx.service.IAttendenceService;
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

package cn.bdqn.service.impl;

import cn.bdqn.entity.*;
import cn.bdqn.entity.Class;
import cn.bdqn.mapper.*;
import cn.bdqn.service.SchedulesService;
import cn.bdqn.vo.MemberWorkCardInfoVO;
import cn.bdqn.vo.MemberWorkDetailInfoVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 学社成员工作安排 服务实现类
 * </p>
 *
 * @author 
 * @since 2023-06-09
 */
@Service
public class SchedulesServiceImpl extends ServiceImpl<SchedulesMapper, Schedules> implements SchedulesService {
    @Autowired
    UsersMapper usersMapper;
    @Autowired
    SchedulesMapper schedulesMapper;
    @Autowired
    SchedulesTypeMapper schedulesTypeMapper;
    @Autowired
    ClassesMapper classesMapper;
    @Autowired
    ClassMapper classMapper;


    /**
     * 学社出勤页面展示工作卡片的数据
     * @param workDate 工作日期
     * @return 卡片上的数据
     */
    @Override
    public List<MemberWorkCardInfoVO> getMemberWorkCardInfo(String workDate) {
        //保存页面信息的列表
        List<MemberWorkCardInfoVO> voList=new ArrayList<>();



        //对应日期所有成员工作记录
        List<Schedules> schedules=schedulesMapper.selectList(new QueryWrapper<Schedules>().eq("date",workDate));
        if(schedules!=null&&schedules.size()>0){
            for(Schedules s:schedules){
                MemberWorkCardInfoVO vo=new MemberWorkCardInfoVO();
                vo.setSchedules(s);
                //获取成员
                Users users= usersMapper.selectById(s.getMemberId());
                vo.setMember(users);
                //获取布置人
                Users createUser=usersMapper.selectById(s.getCreateUserId());
                vo.setCreateUser(createUser);
                //获取工作类型
                SchedulesType schedulesType = schedulesTypeMapper.selectById(s.getWorkTypeId());
                vo.setSchedulesType(schedulesType);
                //获取班级
                Class class1 = classMapper.selectOne(new QueryWrapper<Class>().eq("student_id", s.getMemberId()));
                Classes classes = classesMapper.selectById(class1.getClassId());
                vo.setClasses(classes);
                voList.add(vo);
            }

            return voList;
        }




        return null;
    }


    /**
     * 获取学社成员指定时间所有的工作记录
     *
     * @param memberId 学社成员编号
     * @return 所有数据
     */
    @Override
    public List<MemberWorkDetailInfoVO> getMemberWorkDetailInfo(Integer memberId) {
        List<MemberWorkDetailInfoVO> voList=new ArrayList<>();


        List<Schedules> schedules = schedulesMapper.selectList(new QueryWrapper<Schedules>()
                .eq("member_id", memberId));
        if(schedules!=null&&schedules.size()>0){
            for (Schedules s:schedules){
                MemberWorkDetailInfoVO vo=new MemberWorkDetailInfoVO();
                //获取学社成员
                Users member=usersMapper.selectById(s.getMemberId());
                vo.setMember(member);
                //获取班级
                Class c=classMapper.selectOne(new QueryWrapper<Class>().eq("student_id",s.getMemberId()));
                Classes classes= classesMapper.selectById(c.getClassId());
                vo.setClasses(classes);
                //获取班主任
                Users adviser= usersMapper.selectById(classes.getAdviserId());
                vo.setAdviser(adviser);
                //获取排班人
                Users createUser=usersMapper.selectById(s.getCreateUserId());
                vo.setCreateUser(createUser);
                //获取工作记录
                vo.setSchedules(s);
                //工作类型
                SchedulesType type=schedulesTypeMapper.selectById(s.getWorkTypeId());
                vo.setSchedulesType(type);

                voList.add(vo);
            }
        }
        return voList;
    }
}

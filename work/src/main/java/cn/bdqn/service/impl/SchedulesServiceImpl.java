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
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    WorkClassMapper workClassMapper;


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

                //判断工作类型如果为1查班 查询负责班级
                if(s.getWorkTypeId()==1){
                    List<WorkClass> workClasses=workClassMapper.selectList(new QueryWrapper<WorkClass>().eq("schedules_id",s.getId()));
                    vo.setWorkClasses(workClasses);
                }

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




        return voList;
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

                //判断工作类型如果为1查班 查询负责班级
                if(s.getWorkTypeId()==1){
                    List<WorkClass> workClasses=workClassMapper.selectList(new QueryWrapper<WorkClass>().eq("schedules_id",s.getId()));
                    vo.setWorkClasses(workClasses);
                }


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

    /**
     * 给指定成员添加工作
     * @param schedules
     * @param classIdArray
     * @return
     */
    @Transactional
    public Integer assignOneWork(Schedules schedules,Integer[]classIdArray){
        //判断工作类型不为查班1 则普通加入
        if(schedules.getWorkTypeId()!=1){
            schedules.setUpdateTime(new Date());
            schedules.setStatus(0);
            return schedulesMapper.insert(schedules);
        }else {
            try {
                schedules.setUpdateTime(new Date());
                schedules.setStatus(0);
                int count=schedulesMapper.insert(schedules);


                if(count>0){
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String format = sdf.format(schedules.getUpdateTime());
                    Schedules s = schedulesMapper.selectOne(new QueryWrapper<Schedules>().
                            eq("work_type_id", schedules.getWorkTypeId()).
                            eq("member_id", schedules.getMemberId()).
                            eq("date", schedules.getDate()).
                            eq("update_time", format).
                            eq("create_user_id", schedules.getCreateUserId()).
                            eq("status",schedules.getStatus()));

                    if (s != null) {
                        for (int i = 0; i < classIdArray.length; i++) {

                            WorkClass workClass = new WorkClass();
                            workClass.setClassId(classIdArray[i]);
                            workClass.setSchedulesId(s.getId());
                            int i1=workClassMapper.insert(workClass);
                            if(i1<=0){throw new RuntimeException("添加工作类别出现异常！");}
                        }
                    } else {
                        throw new RuntimeException("未找到工作记录");
                    }
                }else {
                    throw new RuntimeException("添加失败");
                }




            }catch (Exception e){
                throw new RuntimeException("操作失败");
            }

            return 1;
        }
    }


    /**
     * 给指定成员修改工作记录
     * @param schedules
     * @param classIdArray
     * @return
     */
    @Transactional
    public Integer updateOneWork(Schedules schedules,Integer[]classIdArray){
        //判断工作类型不为查班1 则普通修改
        if(schedules.getWorkTypeId()!=1){
            schedules.setUpdateTime(new Date());
            schedules.setStatus(0);
            return schedulesMapper.updateById(schedules);
        }else {
            try {
                schedules.setUpdateTime(new Date());
                schedules.setStatus(0);
                int count=schedulesMapper.updateById(schedules);


                if(count>0){
                    //删除所有此成员班级记录
                    int c=workClassMapper.delete(new QueryWrapper<WorkClass>()
                            .eq("schedules_id",schedules.getId()));

                    if(c>0){//删除成功


                        if (schedules.getId() != null) {
                            for (int i = 0; i < classIdArray.length; i++) {

                                WorkClass workClass = new WorkClass();
                                workClass.setClassId(classIdArray[i]);
                                workClass.setSchedulesId(schedules.getId());
                                int i1=workClassMapper.insert(workClass);
                                if(i1<=0){throw new RuntimeException("修改工作类别出现异常！");}
                            }
                        } else {
                            throw new RuntimeException("未找到工作记录");
                        }
                    }else {
                        throw new RuntimeException("修改失败");
                    }
                }else {
                    throw new RuntimeException("操作失败！");
                }








            }catch (Exception e){
                throw new RuntimeException("操作失败");
            }

            return 1;
        }
    }


    /**
     * 将指定成员删除记录
     *
     * @param schedulesId
     * @return
     */
    @Override
    @Transactional
    public Integer deleteOneWork(Integer schedulesId) {
        int i1=workClassMapper.delete(new QueryWrapper<WorkClass>().
                eq("schedules_id",schedulesId));

        int i = schedulesMapper.deleteById(schedulesId);
        if(i<=0){throw new RuntimeException("删除工作类别出现异常！");}

        return 1;
    }


    /**
     * 给多个成员添加工作
     *
     * @param schedules
     * @param memberIdArray
     * @param classIdArray
     * @return
     */
    @Transactional
    @Override
    public Integer assignMoreWork(Schedules schedules, Integer[] memberIdArray, Integer[] classIdArray) throws InterruptedException {
        for (int i = 0; i < memberIdArray.length; i++) {
            //判断工作类型是否为查班
            if(schedules.getWorkTypeId()!=1){
                schedules.setId(null);
                schedules.setUpdateTime(new Date());
                schedules.setMemberId(memberIdArray[i]);
                schedules.setStatus(0);
                schedulesMapper.insert(schedules);
            }else {//查班

                try {
                    schedules.setId(null);
                    schedules.setUpdateTime(new Date());
                    schedules.setMemberId(memberIdArray[i]);
                    schedules.setStatus(0);
                    int count=schedulesMapper.insert(schedules);


                    if(count>0){
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String format = sdf.format(schedules.getUpdateTime());
                        Schedules s = schedulesMapper.selectOne(new QueryWrapper<Schedules>().
                                eq("work_type_id", schedules.getWorkTypeId()).
                                eq("member_id", schedules.getMemberId()).
                                eq("date", schedules.getDate()).
                                eq("update_time", format).
                                eq("create_user_id", schedules.getCreateUserId()).
                                eq("status",schedules.getStatus()));

                        if (s != null) {
                            for (int j = 0; j < classIdArray.length; j++) {

                                WorkClass workClass = new WorkClass();
                                workClass.setClassId(classIdArray[j]);
                                workClass.setSchedulesId(s.getId());
                                int i1=workClassMapper.insert(workClass);
                                if(i1<=0){throw new RuntimeException("添加工作类别出现异常！");}
                            }
                        } else {
                            throw new RuntimeException("未找到工作记录");
                        }
                    }else {
                        throw new RuntimeException("添加失败");
                    }




                }catch (Exception e){
                    throw new RuntimeException("操作失败");
                }

            }

        }
        return 1;
    }
}
package cn.bdqn.service;

import cn.bdqn.entity.Schedules;
import cn.bdqn.vo.MemberWorkCardInfoVO;
import cn.bdqn.vo.MemberWorkDetailInfoVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 学社成员工作安排 服务类
 * </p>
 *
 * @author 
 * @since 2023-06-09
 */
public interface SchedulesService extends IService<Schedules> {

    /**
     * 学社出勤页面展示工作卡片的数据
     * @param workDate 工作日期
     * @return 卡片上的数据
     */
    public List<MemberWorkCardInfoVO> getMemberWorkCardInfo(String workDate);

    /**
     * 获取学社成员指定时间所有的工作记录
     * @param memberId 学社成员编号
     * @return 所有数据
     */
    public List<MemberWorkDetailInfoVO> getMemberWorkDetailInfo(Integer memberId);

}

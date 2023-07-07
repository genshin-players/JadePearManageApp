package cn.bdqn.vo.displayvo;

import cn.bdqn.dto.ActivitiesDTO;
import cn.bdqn.dto.DisplayDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivitiesPageVO {
    private Integer total;
    private List<ActivitiesDTO> list;
    private Integer pageNum;
    private Integer pageSize;
    private Integer size;
    private Integer startRow;
    private Integer endRow;
    private Integer pages;
    private Integer prePage;
    private Integer nextPage;
    private Boolean isFirstPage;
    private Boolean isLastPage;
    private Boolean hasPreviousPage;
    private Boolean hasNextPage;
    private Integer navigatePages;
    private List<Integer> navigatepageNums;
    private Integer navigateFirstPage;
    private Integer navigateLastPage;
}

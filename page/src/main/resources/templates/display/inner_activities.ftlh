
<!--**********************************
    Content body start
***********************************-->
<#include "../header.ftlh">
<div class="content-body">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xl-12">
                <div class="d-flex mb-4 justify-content-between align-items-center flex-wrap">
                    <form id="search" action="/display/inner_activities">
                        <div class="table-search">
                            <div class="input-group search-area mb-xxl-0 mb-4">
                                <input name="title" type="text" class="form-control" placeholder="按照标题查询">
                                <span class="input-group-text"><a href="javascript:$('#search').submit()"><i class="flaticon-381-search-2"></i></a></span>
                            </div>
                        </div>
                    </form>
                    <a href="javascript:window.location.href='/display/edit_activities'" class="btn btn-primary mb-xxl-0 mb-4 "><i class="far fa-file-word me-2"></i>创建活动</a>
                </div>
                <div class="tab-content">
                    <div class="tab-pane active show" id="All">
                        <div class="table-responsive">
                            <table class="table card-table default-table display mb-4 dataTablesCard table-responsive-xl " id="guestTable-all">
                                <thead>
                                <tr>
                                    <th class="bg-none">
                                        <div class="form-check style-1">
                                            <input class="form-check-input" type="checkbox" value="" id="checkAll">
                                        </div>
                                    </th>
                                    <th>ID</th>
                                    <th>标题</th>
                                    <th>报名人数</th>
                                    <th>开始时间</th>
                                    <th>结束时间</th>
                                    <th>创建时间</th>
                                    <th>修改时间</th>
                                    <th>是否生效</th>
                                    <th>创建者</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if activitiesList??>
                                    <#list activitiesList as activities>
                                        <tr>
                                            <td>
                                                <div class="form-check style-1">
                                                    <input class="form-check-input" type="checkbox" value="">
                                                </div>
                                            </td>
                                            <td>
                                                <div class="media-bx">
                                                    <h4 class="mb-0 mt-1 text-black">${activities.id}</h4>
                                                </div>
                                            </td>
                                            <td>
                                                <div>
                                                    <h4 class="mb-0 mt-1 text-black">${activities.display.title}</h4>
                                                </div>
                                            </td>
                                            <td>
                                                <div>
                                                    <h4 class="mb-0 mt-1 text-black">${activities.signupNumber}</h4>
                                                </div>
                                            </td>
                                            <td>
                                                <div>
                                                    <h5>${activities.startTime?string("yyyy-MM-dd")}</h5>
                                                    <span class="fs-14">${activities.startTime?string("hh:mm")}</span>
                                                </div>
                                            </td>
                                            <td>
                                                <div>
                                                    <h5>${activities.endTime?string("yyyy-MM-dd")}</h5>
                                                    <span class="fs-14">${activities.endTime?string("hh:mm")}</span>
                                                </div>
                                            </td>
                                            <td>
                                                <div>
                                                    <h5>${activities.createTime?string("yyyy-MM-dd")}</h5>
                                                    <span class="fs-14">${activities.createTime?string("hh:mm")}</span>
                                                </div>
                                            </td>
                                            <td>
                                                <div>
                                                    <h5>${activities.updateTime?string("yyyy-MM-dd")}</h5>
                                                    <span class="fs-14">${activities.updateTime?string("hh:mm")}</span>
                                                </div>
                                            </td>
                                            <td>
                                                <div>
                                                    <#if activities.isActive == true>
                                                        <span class="badge light badge-success">
														<i class="fa fa-circle text-success me-1"></i>
														生效
													</span>
                                                    </#if>
                                                    <#if activities.isActive == false>
                                                        <span class="badge light badge-warning">
														<i class="fa fa-circle text-warning me-1"></i>
														不生效
													</span>
                                                    </#if>

                                                </div>
                                            </td>
                                            <td><a href="javascript:void(0);" class="btn-link">林忆宁</a></td>
                                            <td>
                                                <div class="dropdown dropstart">
                                                    <a href="javascript:void(0);" class="btn-link" data-bs-toggle="dropdown" aria-expanded="false">
                                                        <svg width="24" height="24" viewbox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                            <path d="M11 12C11 12.5523 11.4477 13 12 13C12.5523 13 13 12.5523 13 12C13 11.4477 12.5523 11 12 11C11.4477 11 11 11.4477 11 12Z" stroke="#262626" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"></path>
                                                            <path d="M18 12C18 12.5523 18.4477 13 19 13C19.5523 13 20 12.5523 20 12C20 11.4477 19.5523 11 19 11C18.4477 11 18 11.4477 18 12Z" stroke="#262626" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"></path>
                                                            <path d="M4 12C4 12.5523 4.44772 13 5 13C5.55228 13 6 12.5523 6 12C6 11.4477 5.55228 11 5 11C4.44772 11 4 11.4477 4 12Z" stroke="#262626" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"></path>
                                                        </svg>
                                                    </a>
                                                    <div class="dropdown-menu">
                                                        <a class="dropdown-item" href="/display/edit_activities?id=${activities.id}">编辑</a>
                                                        <a class="dropdown-item" href="javascript:deleteActivities(${activities.id},${activities.displayId})">删除</a>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </#list>
                                </#if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!--**********************************
    Content body end
***********************************-->


<!--**********************************
    Footer start
***********************************-->
<div class="footer">

    <div class="copyright">
        <p>Copyright &copy; 2021.Company name All rights reserved.<a target="_blank" href="https://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p>
    </div>
</div>
<!--**********************************
    Footer end
***********************************-->

<!--**********************************
   Support ticket button start
***********************************-->

<!--**********************************
   Support ticket button end
***********************************-->


</div>
<!--**********************************
    Main wrapper end
***********************************-->
<script>
    function deleteActivities(activitiesId,displayId){
        if (confirm("确定要删除吗")){
            $.ajax({
                type:"GET",
                url:"/display/deleteActivitiesById",
                data:{activitiesId:activitiesId,displayId:displayId},
                dataType:"json",
                success:function (res) {
                    console.log(res)
                    if (res.code == 200) {
                        alert("删除成功");
                        location.href = '/display/inner_activities';
                    }else {
                        alert("删除失败")
                    }
                }
            })
        }
    }
</script>
<!--**********************************
    Scripts
***********************************-->
<!-- Required vendors -->
<script src="/js/global.min.js"></script>
<script src="/js/jquery.nice-select.min.js"></script>

<!-- Datatable -->
<script src="/js/jquery.dataTables.min.js"></script>
<script src="/js/datatables.init.js"></script>

<script src="/js/custom.min.js"></script>
<script src="/js/deznav-init.js"></script>
<script src="/js/demo.js"></script>
<script src="/js/styleSwitcher.js"></script>

</body>
</html>

<!--**********************************
    Content body start
***********************************-->
<#include "../header.ftlh">
<div class="content-body">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xl-12">
                <div class="d-flex mb-4 justify-content-between align-items-center flex-wrap">
                    <form id="search" action="/display/external_performance">
                        <div class="table-search">
                            <div class="input-group search-area mb-xxl-0 mb-4">
                                <input name="title" type="text" class="form-control" placeholder="按照标题查询">
                                <span class="input-group-text"><a href="javascript:$('#search').submit()"><i class="flaticon-381-search-2"></i></a></span>
                            </div>
                        </div>
                    </form>
                    <a href="javascript:window.location.href='/display/editor?type=对外展览'" class="btn btn-primary mb-xxl-0 mb-4 "><i class="far fa-file-word me-2"></i>创建校外展览内容</a>
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
                                    <th>创建时间</th>
                                    <th>修改时间</th>
                                    <th>创建者</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list displayDTOList as displayDTO>
                                    <tr>
                                        <td>
                                            <div class="form-check style-1">
                                                <input class="form-check-input" type="checkbox" value="">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="media-bx">
                                                <h4 class="mb-0 mt-1 text-black">${displayDTO.id}</h4>
                                            </div>
                                        </td>
                                        <td>
                                            <div>
                                                <h4 class="mb-0 mt-1 text-black">${displayDTO.title}</h4>
                                            </div>
                                        </td>
                                        <td>
                                            <div>
                                                <h5>${displayDTO.createTime?string("yyyy-MM-dd")}</h5>
                                                <span class="fs-14">${displayDTO.createTime?string("hh:mm")}</span>
                                            </div>
                                        </td>
                                        <td>
                                            <div>
                                                <h5>${displayDTO.updateTime?string("yyyy-MM-dd")}</h5>
                                                <span class="fs-14">${displayDTO.updateTime?string("hh:mm")}</span>
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
                                                    <a class="dropdown-item" href="javascript:toEditor(${displayDTO.id});">编辑</a>
                                                    <a class="dropdown-item" href="javascript:deleteDisplay(${displayDTO.id});">删除</a>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </#list>
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
    function toEditor(id){
        let str = "id="+id;
        window.location.href = "/display/editor?type=每日一推&"+str;
    }
    function deleteDisplay(id){
        if (confirm("确定要删除吗")){
            $.ajax({
                type:"GET",
                url:"/display/deleteDisplayById",
                data:{id:id},
                dataType:"json",
                success:function (res) {
                    console.log(res)
                    if (res.code == 200) {
                        alert("删除成功")
                        location.href = '/display/external_performance';
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
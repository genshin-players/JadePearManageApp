<#include "../header.ftlh">
<#--班级出勤 之  卡片点进去的详情页面-->



    <!--**********************************
            Sidebar end
        ***********************************-->

    <!--**********************************
            Content body start
        ***********************************-->
    <div class="content-body">
        <div class="container-fluid">


            <!-- row -->


            <div class="row">



                <div class="col-12">
                    <div class="card" style="box-shadow: 10px 10px 20px 10px rgba(0, 0, 255, .2);opacity: 0.8 ">
                        <div class="card-header">
                            <h4 class="card-title"><span style="font-weight: bolder;font-size: 20px">${classes.name}  ${pageDate}</span>出勤详情</h4>
                            <button class="btn btn-info" onclick="javascript:window.location.href='/toAddStudentAttendance'">添加出勤记录</button>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table id="example4" class="display" style="min-width: 845px">
                                    <thead>
                                    <tr>
                                        <th>编号</th>
                                        <th>姓名</th>
                                        <th>班级</th>
                                        <th>班主任</th>
                                        <th>出勤状态</th>
                                        <th>汇报人</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <#list resultVO.data as detail>
                                        <tr>
                                            <td>${detail.attendence.id}</td>
                                            <#assign json=detail.student.identityInfo?eval/>
                                            <td>${json.realname}</td>
                                            <td>${detail.classes.name}</td>
                                            <#assign json=detail.adviser.identityInfo?eval/>
                                            <td>${json.realname}</td>
                                            <td>
                                                <#if detail.attendence.isPresent==0>
                                                    <span class="badge light badge-danger">
														<i class="fa fa-circle text-danger me-1"></i>
														缺勤
                                                    </span>
                                                <#elseif detail.attendence.isPresent==1>
                                                    <span class="badge light badge-warning">
														<i class="fa fa-circle text-warning me-1"></i>
														迟到
                                                    </span>
                                                <#elseif detail.attendence.isPresent==2>
                                                    <span class="badge light badge-success">
														<i class="fa fa-circle text-success me-1"></i>
														出勤
                                                    </span>
                                                <#elseif detail.attendence.isPresent==3>
                                                    <span class="badge light badge-dark">
														<i class="fa fa-circle text-dark me-1"></i>
														请假
                                                    </span>
                                                </#if>

                                            </td>
                                            <#assign json=detail.reportUser.identityInfo?eval/>
                                            <td>${json.realname}</td>
                                            <#---->
                                            <td>
                                                <div class="dropdown ms-auto text-end">
                                                    <div class="btn-link" data-bs-toggle="dropdown">
                                                        <svg width="24px" height="24px" viewbox="0 0 24 24" version="1.1"><g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd"><rect x="0" y="0" width="24" height="24"></rect><circle fill="#000000" cx="5" cy="12" r="2"></circle><circle fill="#000000" cx="12" cy="12" r="2"></circle><circle fill="#000000" cx="19" cy="12" r="2"></circle></g></svg>
                                                    </div>
                                                    <div class="dropdown-menu dropdown-menu-end">
                                                        <a class="dropdown-item" href="/toUpdateStudentAttendance?attendanceId=${detail.attendence.id}&&attendanceDate=${pageDate}">修改记录</a>
                                                        <a class="dropdown-item" href="#" onclick="delAttendance(${detail.attendence.id})">删除记录</a>
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

<!--**********************************
        Scripts
    ***********************************-->
<!-- Required vendors -->
<script src="/js/global.min.js"></script>
<script src="js/Chart.bundle.min.js"></script>
<!-- Apex Chart -->
<script src="js/apexchart.js"></script>

<!-- Datatable -->
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/datatables.init.js"></script>

<script src="js/jquery.nice-select.min.js"></script>

<script src="js/custom.min.js"></script>
<script src="js/deznav-init.js"></script>
<script src="js/demo.js"></script>
<script src="js/styleSwitcher.js"></script>

<script>

    function delAttendance(attendanceId) {
        $.ajax({
            type:"post",
            url:"http://localhost:8003/delStudentAttendance",
            data:{attendanceId:attendanceId},
            dataType:"json",
            success:function (data) {
                if(data.data>0){
                    window.location.reload();
                }
            },
            error:function () {
                alert("删除失败");
            }
        });
    }
</script>
</body>
</html>
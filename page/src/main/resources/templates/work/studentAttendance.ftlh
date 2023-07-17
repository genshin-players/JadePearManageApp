<#include "../header.ftlh"/>


<#--班级出勤页面-->


    <!--**********************************
        Sidebar end
    ***********************************-->

    <!--**********************************
        Content body start
    ***********************************-->
<style>
    .card:hover{
        cursor: pointer;
        transform: scale3d(1.03,1.03,1);
    }
</style>
    <div class="content-body">
        <div class="container-fluid">

            <div class="row page-titles header-panel">
                <ol class="text-center">
                    <button id="prevDay" class="btn btn-info" onclick="prevDayF()">上一日</button>
                    <span id="workTime" class="text-warning text" style="font-weight: bolder; font-size: 30px">${pageDate}</span>
                    <button id="nextDay" class="btn btn-info" onclick="nextDayF()">下一日</button>
                </ol>
                <hr style="height: 2px">
            </div>
            <#--<hr style="height: 2px">-->
            <div class="row" id="cardBox">
                <#--<#list resultVO.data as c>
                    ${c} <br>
                    ${resultVO.data?size}
                </#list>-->



                <#if resultVO.data?size!=0>
                    <#list resultVO.data as card>
                        <div class="col-xl-3">
                            <div
                                <#if card.attendanceNumber==card.classSize>
                                    class="card text-white bg-success"
                                <#elseif card.attendanceNumber < card.classSize-5 ||card.attendanceNumber==0>
                                    class="card text-white bg-danger"
                                <#elseif card.attendanceNumber gte card.classSize-5>
                                    class="card text-white bg-warning"
                                </#if>

                                    onclick="toDetailPage(this)"
                                 data_classId="${card.classes.id}" data_attendanceDate="${pageDate}" >
                                <div class="card-header">
                                    <a  class="card-title text-white" style="font-weight: bolder;font-size: 25px">${card.classes.name}  <span><#if card.attendanceNumber==card.classSize>(全)<#else>(缺)</#if>
                                        <br><span class="text-dark" style="font-weight: bolder;font-size: 10px">${card.attendanceDate?string('yyyy-MM-dd HH:mm:ss')}</span>
                                        <#assign json=card.reportUser.identityInfo?eval/>
                                        &nbsp;<span class="text-dark" style="font-weight: bolder;font-size: 10px">${json.realname}记录</span>
                                        </span></a>

                                </div>
                                <div class="card-body mb-0">
                                    <span class="text-white" style="font-weight: bolder;font-size: 18px">总人数:</span><span style="font-size: 15px;margin-left: 20px">${card.classSize}人</span><br>
                                    <hr>
                                    <span class="text-white" style="font-weight: bolder;font-size: 18px">出勤数:</span><span style="font-size: 15px;margin-left: 20px">${card.attendanceNumber}人</span><br>
                                    <hr>
                                    <span class="text-white" style="font-weight: bolder;font-size: 18px">迟到:</span><span style="font-size: 15px;margin-left: 40px">${card.latecomersNumber}人</span><br>
                                    <hr>
                                    <span class="text-white" style="font-weight: bolder;font-size: 18px">缺勤:</span><span style="font-size: 15px;margin-left: 40px">${card.absenteesNumber}人</span><br>
                                    <hr>
                                    <span class="text-white" style="font-weight: bolder;font-size: 18px">请假:</span><span style="font-size: 15px;margin-left: 40px">${card.leaverNumber}人</span><br>
                                </div>
                            </div>
                        </div>
                    </#list>

                    <#else>
                    <div class="row">
                        <div class="col-lg-12" align="center">
                            <span class="text" style="color:red;font-weight: bolder;font-size: 30px" >无记录</span>
                        </div>
                    </div>
                </#if>





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
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.4/jquery.js"></script>
<script src="/js/global.min.js"></script>
<script src="/js/jquery.nice-select.min.js"></script>
<script src="/js/custom.min.js"></script>
<script src="/js/deznav-init.js"></script>
<script src="/js/demo.js"></script>
<script src="/js/styleSwitcher.js"></script>

<script>
    //custom select box
    var currDate;


    $(function(){
        currDate= new Date();
        var pageD=$("#workTime").html();
        var date=new Date(pageD);
        if(date.getFullYear()>=currDate.getFullYear()&&date.getMonth()>=currDate.getMonth()&&date.getDate()>=currDate.getDate()){
            $("#nextDay").css("display","none");
        }
    });

    function prevDayF() {
        var pageD=$("#workTime").html();
        var date=new Date(pageD);
        window.location.href="/stuAttendance?attendanceDate="+date.getFullYear()+"-"+(date.getUTCMonth()+1)+"-"+(date.getDate()-1);
    }

    function nextDayF() {
        var pageD=$("#workTime").html();
        var date=new Date(pageD);

        window.location.href="/stuAttendance?attendanceDate="+date.getFullYear()+"-"+(date.getUTCMonth()+1)+"-"+(date.getDate()+1);
    }


//卡片点击时间 去详情页面a
    function toDetailPage(t) {
        var classId=t.getAttribute("data_classId");
        var attendanceDate=t.getAttribute("data_attendanceDate");
        window.location.href="/stuAttendance/toStuAttendanceDetail?attendanceDate="+attendanceDate+"&classId="+classId;
    }


</script>
</body>
</html>
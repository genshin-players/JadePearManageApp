<#include "../header.ftlh"/>


<#--安排工作页面-->
<link rel="stylesheet" href="/css/select2.min.css">
<link href="/css/nice-select.css" rel="stylesheet">
<link href="/css/style.css" rel="stylesheet">


<!-- Daterange picker -->
<link href="/css/daterangepicker.css" rel="stylesheet">
<!-- Clockpicker -->
<link href="/css/bootstrap-clockpicker.min.css" rel="stylesheet">
<!-- asColorpicker -->
<link href="/css/asColorPicker.min.css" rel="stylesheet">
<!-- Material color picker -->
<link href="/css/bootstrap-material-datetimepicker.css" rel="stylesheet">

<!-- Pick date -->
<link rel="stylesheet" href="/css/default.css">
<link rel="stylesheet" href="/css/default.date.css">
<link href="/css/icon.css" rel="stylesheet">
<!-- Custom Stylesheet -->
<link href="/css/nice-select.css" rel="stylesheet">
<link href="/css/style.css" rel="stylesheet">




<!--**********************************
           Content body start
       ***********************************-->
<div class="content-body">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xl-2"></div>
            <div class="col-xl-8">
                <div class="card bg-light bg" style="box-shadow: 10px 10px 20px 10px rgba(0, 0, 255, .2);opacity: 0.8 ">
                    <div class="card-header">
                        <span class="card-title text-center text-primary" style="font-weight: bolder;font-size: 25px">安排工作</span>
                    </div>
                    <div class="card-body">



                        <div class="row">
                            <div class="col-lg-2">
                                <span class="text-white fw-bolder" style="font-size: 18px">工作人员:</span>
                            </div>
                            <div class="col-lg-10">


                                    <select class="multi-select"  multiple="multiple" name="memId">
                                        <#list memberList as m>
                                            <#assign json=m.identityInfo?eval/>
                                            <option value="${m.id}">${json.realname}</option>
                                        </#list>

                                    </select>






                            </div>

                            <br>
                            <hr style="margin-top: 10px;background-color: white">
                        </div>

                        <div class="row">
                            <div class="col-lg-2">
                                <span class="text-white fw-bolder" style="font-size: 18px">工作时间:</span>
                            </div>
                            <div class="col-lg-4">
                                <input type="text" class="form-control mdate" placeholder="选择日期"  style="height: 35px" name="workTime" onchange="checkTime()">
                            </div>
                            <div class="col-lg-4">

                            </div>




                            <br>
                            <hr style="margin-top: 10px;background-color: white">
                        </div>

                        <div class="row" >
                            <div class="col-lg-2">
                                <span class="text-white fw-bolder" style="font-size: 18px">工作内容:</span>
                            </div>
                            <div class="col-lg-4">
                                <select class="single-select-placeholder js-states" name="workType" onchange="checkWorkType()">
                                    <option value="0">请选择工作内容</option>
                                    <#list workTypeList as type>
                                        <option value="${type.id}">${type.name}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                            <hr  style="margin-top: 10px;background-color: white" >


                            <div class="row classChoose" style="display: none">
                                <div class="col-lg-2">
                                    <span class="text-white fw-bolder" style="font-size: 18px">选择班级:</span>
                                </div>
                                <div class="col-lg-10">
                                    <select class="multi-select" name="classId" multiple="multiple" >
                                    <#list classesList.data as c>
                                        <option value="${c.id}">${c.name}</option>
                                    </#list>
                                    </select>
                                </div>
                                <br>
                                <hr class="classChoose" style="display:none;margin-top: 10px;background-color: white">
                            </div>



                                <div class="row">
                                    <div class="col-lg-4">

                                    </div>
                                    <div class="col-lg-4" align="center">
                                        <button class="btn btn-outline-secondary" onclick="submitWork()">提交</button>
                                    </div>


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

<!--**********************************
    Scripts
***********************************-->
<!-- Required vendors -->
<script src="/js/global.min.js"></script>
<script src="/js/jquery.nice-select.min.js"></script>
<script src="/js/select2.full.min.js"></script>
<script src="/js/select2-init.js"></script>
<script src="/js/custom.min.js"></script>
<script src="/js/deznav-init.js"></script>
<script src="/js/demo.js"></script>
<script src="/js/styleSwitcher.js"></script>


<!--**********************************
        Scripts
    ***********************************-->
<!-- Required vendors -->
<script src="static/js/global.min.js"></script>
<script src="static/js/bootstrap-select.min.js"></script>
<script src="static/js/Chart.bundle.min.js"></script>
<!-- Apex Chart -->
<script src="static/js/apexchart.js"></script>



<!-- Daterangepicker -->
<!-- momment js is must -->
<script src="/js/moment.min.js"></script>
<script src="/js/daterangepicker.js"></script>
<!-- clockpicker -->
<script src="/js/bootstrap-clockpicker.min.js"></script>
<!-- asColorPicker -->
<script src="/js/jquery-asColor.min.js"></script>
<script src="/js/jquery-asGradient.min.js"></script>
<script src="/js/jquery-asColorPicker.min.js"></script>
<!-- Material color picker -->
<script src="/js/bootstrap-material-datetimepicker.js"></script>
<!-- pickdate -->
<script src="/js/picker.js"></script>
<script src="/js/picker.time.js"></script>
<script src="/js/picker.date.js"></script>

<!-- Daterangepicker -->
<script src="/js/bs-daterange-picker-init.js"></script>
<!-- Clockpicker init -->
<script src="/js/clock-picker-init.js"></script>
<!-- asColorPicker init -->
<script src="/js/jquery-asColorPicker.init.js"></script>
<!-- Material color picker init -->
<script src="/js/material-date-picker-init.js"></script>
<!-- Pickdate -->
<script src="/js/pickadate-init.js"></script>

<script src="/js/jquery.nice-select.min.js"></script>

<script src="/js/custom.min.js"></script>
<script src="/js/deznav-init.js"></script>
<script src="/js/demo.js"></script>
<script src="/js/styleSwitcher.js"></script>


<script>



    //检查时间 不能是今天之前
    function checkTime() {
        let val= $("[name=workTime]").val();
        var date1=val.split("-");
        var dateNow=new Date();
        if(date1[0]<dateNow.getFullYear()||date1[1]<(dateNow.getUTCMonth()+1)||date1[2]<dateNow.getDate()){
            alert("工作时间不能是今天之前");
            $("[name=workTime]").val(dateNow.getFullYear()+"-"+(dateNow.getUTCMonth()+1)+"-"+dateNow.getDate());
        }
    }

    //检查工作安排是否是查班 查班的话需要加上班级选择
    function checkWorkType() {
        var val=$("[name=workType]").val();
        console.log(val);
        if(val=="1"){
            $(".classChoose").css("display","flex");
        }else {
            $(".classChoose").css("display","none");
        }
    }

    //提交按钮点击事件
    function submitWork() {
        var member_id=$("[name=memId]").val();
        var workTime=$("[name=workTime]").val();
        var workType=$("[name=workType]").val();
        var classId=$("[name=classId]").val();


        if(member_id.length<=0){
            alert("工作人员不得为空");
        }else if(workTime==null){
            alert("工作日期不得为空");
        }else if(workType==null||workType==0){
            alert("工作类型不得为空");
        }else {

            if(workType==1&&classId.length>0){
                if(workType==1&&member_id.length==1){
                    $.ajax({
                        type:"post",
                        url:"/assignWork/assignMoreWork",
                        dataType:"json",
                        data:{
                            memberIdArray:member_id,
                            date:workTime,
                            workTypeId:workType,
                            classIdArray:classId,
                            createUserId:1
                        },
                        success:function(res) {
                            console.log(res);
                            if(res.data >0){
                                window.location.href="work/memberWork?workDate="+workTime;
                            }else {
                                alert("添加失败");
                            }
                        }
                    });
                }else {
                    alert("当工作内容为查班时工作人员只能有一名，可以吩咐其他成员辅助指定成员查班");
                }


            }else if(workType==1&&classId.length<=0){
                alert("社员负责班级不得为空");
            }else {
                $.ajax({
                    type:"post",
                    url:"/assignWork/assignMoreWork",
                    dataType:"json",
                    data:{
                        memberIdArray:member_id,
                        date:workTime,
                        workTypeId:workType,
                        createUserId:1
                    },
                    success:function(res) {
                        console.log(res);
                        if(res.data >0){
                            window.location.href="work/memberWork?workDate="+workTime;
                        }else {
                            alert("添加失败");
                        }
                    }
                });
            }

        }
    }

</script>

</body>
</html>
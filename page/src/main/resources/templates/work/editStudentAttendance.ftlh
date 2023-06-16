<#include "../header.ftlh"/>


<#--班级出勤编辑页面-->
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
                        <span class="card-title text-center text-primary" style="font-weight: bolder;font-size: 25px">${editType}学生出勤</span>
                    </div>

                    <div class="card-body">

                        <div class="row">
                            <div class="col-lg-3">
                            </div>
                            <div class="col-lg-2">
                                <span class="text-white fw-bolder" style="font-size: 18px">选择班级:</span>
                            </div>
                            <div class="col-lg-4">
                                <select class="single-select-placeholder js-states" readonly name="classId" onchange="classChange()">
                                    <option value="0">请选择班级</option>
                                    <#list resultVO.data.classesList as c>
                                        <option class="classIdOp" value="${c.id}">${c.name}</option>
                                    </#list>
                                </select>
                            </div>


                            <br>
                            <hr style="margin-top: 10px;background-color: white">
                        </div>

                        <div class="row">
                            <div class="col-lg-3">

                            </div>
                            <div class="col-lg-2">
                                <span class="text-white fw-bolder" style="font-size: 18px">选择学生:</span>
                            </div>
                            <div class="col-lg-4">
                                <select class="single-select-placeholder js-states" name="studentId" onchange="studentClick()">
                                    <option value="0">请选择学生</option>
                                </select>
                            </div>




                            <br>
                            <hr style="margin-top: 10px;background-color: white">
                        </div>

                        <div class="row">
                            <div class="col-lg-3">

                            </div>
                            <div class="col-lg-2">
                                <span class="text-white fw-bolder" style="font-size: 18px">出勤状态:</span>
                            </div>
                            <div class="col-lg-4">
                                <select class="single-select-placeholder js-states" name="isPresent">
                                    <option value="-1">请选择出勤状态</option>
                                    <option value="0">缺勤</option>
                                    <option value="1">迟到</option>
                                    <option value="2">出勤</option>
                                    <option value="3">请假</option>
                                </select>
                            </div>

                            <br>
                            <hr style="margin-top: 10px;background-color: white">

                            <div class="row">
                                <div class="col-lg-3">

                                </div>
                                <div class="col-lg-2">
                                    <span class="text-white fw-bolder" style="font-size: 18px">报告人员:</span>
                                </div>
                                <div class="col-lg-4">
                                    <select class="single-select-placeholder js-states" name="reportUserId">
                                        <option value="0">请选择报告人员(学社成员)</option>
                                        <#list resultVO.data.userList as repotUser>
                                            <#if repotUser.roleId==5>
                                                <#assign json=repotUser.identityInfo?eval/>
                                                <option value="${repotUser.id}" <#if attendance??&&attendance.data.reportUserId==repotUser.id>selected</#if>>${json.realname}</option>
                                            </#if>
                                        </#list>
                                    </select>
                                </div>

                                <br>
                                <hr style="margin-top: 10px;background-color: white">
                            </div>


                            <div class="row">
                                <div class="col-lg-3">

                                </div>
                                <div class="col-lg-2">
                                    <span class="text-white fw-bolder" style="font-size: 18px">日期:</span>
                                </div>
                                <div class="col-lg-4">
                                    <input type="text" class="form-control mdate" placeholder="选择日期" name="date"  style="height: 35px;margin-left: 10px">
                                </div>

                                <br>
                                <hr style="margin-top: 10px;background-color: white">



                                <div class="row">
                                    <div class="col-lg-4">

                                    </div>
                                    <div class="col-lg-4" align="center">
                                        <button class="btn btn-outline-secondary" onclick="submitEdit()">提交</button>
                                    </div>


                                    </div>
                                    <br>

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
<script src="/js/global.min.js"></script>
<script src="/js/bootstrap-select.min.js"></script>
<script src="/js/Chart.bundle.min.js"></script>
<!-- Apex Chart -->
<script src="/js/apexchart.js"></script>



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
    $(function () {
        $("[name=classId]").find("option[value='2']").click();


        $.ajax({
            type:"get",
            url:"http://localhost:8003/getStudentByClass",
            data:{classId:0},
            dataType:"json",
            success:function (data) {
                $(".myOp").remove();
                var str="";
                for (let i = 0; i < data.studentByClass.length; i++) {
                    const info=JSON.parse(data.studentByClass[i].identityInfo);
                    str+="<option class='myOp' value='"+data.studentByClass[i].id+"'>"+info.realname+"</option>";
                }
                $("[name=studentId]").append(str);
            }
        });
    });


    function classChange() {
        var classId=$("[name=classId]").val();
        if(classId!=0&&classId!=null){
            $.ajax({
                type:"get",
                url:"http://localhost:8003/getStudentByClass",
                data:{classId:classId},
                dataType:"json",
                success:function (data) {
                    $(".myOp").remove();
                    var str="";
                    for (let i = 0; i < data.studentByClass.length; i++) {
                        const info=JSON.parse(data.studentByClass[i].identityInfo);
                        str+="<option class='myOp' value='"+data.studentByClass[i].id+"'>"+info.realname+"</option>";
                    }
                    $("[name=studentId]").append(str);
                }
            });
        }else {
            $.ajax({
                type:"get",
                url:"http://localhost:8003/getStudentByClass",
                data:{classId:0},
                dataType:"json",
                success:function (data) {
                    $(".myOp").remove();
                    var str="";
                    for (let i = 0; i < data.studentByClass.length; i++) {
                        const info=JSON.parse(data.studentByClass[i].identityInfo);
                        str+="<option class='myOp' value='"+data.studentByClass[i].id+"'>"+info.realname+"</option>";
                    }
                    $("[name=studentId]").append(str);
                }
            });
        }

    }

    function studentClick() {
        var studentId=$("[name=studentId]").val();

        $.ajax({
            type:"get",
            url:"http://localhost:8003/getStudentByClass",
            data:{classId:0},
            dataType:"json",
            success:function (data) {

                $(".classIdOp").remove();
                var str="";
                for (let j = 0; j < data.classList.length; j++) {
                    var classId=0;
                    //找出选中学生的班级id
                    for (let i = 0; i < data.studentAndClass.length; i++) {
                        //console.log(data.studentAndClass[i].studentId+"=="+studentId+"  "+data.studentAndClass[i].studentId==studentId+"\n");
                        if(data.studentAndClass[i].studentId==studentId){
                            classId=data.studentAndClass[i].classId;
                        }
                    }
                    if(classId==data.classList[j].id){
                        str+="<option class='classIdOp' value='"+data.classList[j].id+"' selected>"+data.classList[j].name+"</option>";
                    }else {
                        str+="<option class='classIdOp' value='"+data.classList[j].id+"'>"+data.classList[j].name+"</option>";
                    }

                }
                $("[name=classId]").append(str);

            }
        });
    }


    function submitEdit() {
        var classId=$("[name=classId]").val();
        var studentId=$("[name=studentId]").val();
        var isPresent=$("[name=isPresent]").val();
        var reportUserId=$("[name=reportUserId]").val();
        var attendanceDate=$("[name=date]").val();


        if(classId==0){
            alert("请选择班级");
        }else if(studentId==0){
            alert("请选择学生");
        }else if(isPresent==-1){
            alert("请选择出勤状态");
        }else if(reportUserId==0){
            alert("请选择报告人");
        }else if(attendanceDate=null||attendanceDate==""){
            alert("请选择报告日期");
        }else {
            $.ajax({
                type:"post",
                url:"http://localhost:8003/getAttendanceByStuIdAndDate",
                data:{"stuId":studentId,"attendanceDate":$("[name=date]").val()},
                dataType:"json",
                success:function (data) {

                    if(data.data.length>0){

                        alert($("[name=studentId] option:selected").html()+"在"+$("[name=date]").val()+"已经有出勤记录，请勿重复添加，有需要可前往修改。");

                    }else {
                        $.ajax({
                            type:"post",
                            url:"http://localhost:8003/addStudentAttendance",
                            data:{
                                "classId":classId,
                                "studentId":studentId,
                                "isPresent":isPresent,
                                "reportUserId":reportUserId,
                                "attendanceDate":$("[name=date]").val(),
                            },
                            dataType:"json",
                            success:function(data){
                                console.log(data);
                                alert("添加成功");
                                window.location.href="/toStuAttendance?attendanceDate="+$("[name=date]").val();
                            },
                            error:function(data){
                                alert("添加失败");
                            }
                        });
                    }
                }
            });


        }
    }
</script>
</body>
</html>
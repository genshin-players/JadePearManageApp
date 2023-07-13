<#include "../header.ftlh">


<!--**********************************
            Content body start
        ***********************************-->
<div class="content-body">
    <div class="container-fluid">
        <!-- row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title">添加学生</h4>
                    </div>
                    <div class="card-body">
                        <div class="form-validation">

                            <form class="needs-validation" novalidate="">
                                <div class="row">
                                    <div class="mb-3 row">
                                        <label class="col-lg-4 col-form-label" for="validationCustom06">用户名
                                            <span class="text-danger">*</span>
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="validationCustom06" placeholder="输入姓名" required="" name="username" >
                                            <div class="invalid-feedback">usersById
                                                Please enter a Currency.
                                            </div>
                                        </div>
                                    </div>

                                    <div class="mb-3 row">
                                        <label class="col-lg-4 col-form-label" for="validationCustom06">姓名
                                            <span class="text-danger">*</span>
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="validationCustom06" placeholder="输入姓名" required="" name="realname" />
                                            <div class="invalid-feedback">
                                                Please enter a Currency.
                                            </div>
                                        </div>
                                    </div>


                                    <div class="mb-3 row">
                                        <label class="col-lg-4 col-form-label" for="validationCustom05">班级
                                            <span class="text-danger">*</span>
                                        </label>
                                        <div class="col-lg-6">
                                            <select class="default-select wide form-control" id="validationCustom05" name="class_id"  >
                                                <option data-display="艾欧尼亚班" value="1">艾欧尼亚班</option>
                                                <option value="2" >暗影岛班</option>
                                                <option value="3" >诺克萨斯班</option>
                                            </select>
                                            <div class="invalid-feedback">
                                                Please select a one.
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mb-3 row">
                                        <label class="col-lg-4 col-form-label" for="validationCustom07">年龄
                                            <span class="text-danger">*</span>
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="validationCustom07" placeholder="输入年龄" required="" name="age"  >
                                            <div class="invalid-feedback">
                                                Please enter a url.
                                            </div>
                                        </div>
                                    </div>

                                    <div class="mb-3 row">
                                        <label class="col-lg-4 col-form-label" for="validationCustom05">性别
                                            <span class="text-danger">*</span>
                                        </label>
                                        <div class="col-lg-6">
                                            <select class="default-select wide form-control" id="validationCustom05" name="gender"  >
                                                <option data-display="男" value="男">男</option>
                                                <option value="女" >女</option>
                                            </select>
                                            <div class="invalid-feedback">
                                                Please select a one.
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mb-3 row">
                                        <label class="col-lg-4 col-form-label" for="validationCustom09">联系方式 <span class="text-danger">*</span>
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="validationCustom09" placeholder="输入老师方式（电话号）" required="" name="phone"  >
                                            <div class="invalid-feedback">
                                                Please enter a digits.
                                            </div>
                                        </div>
                                    </div>

                                    <div class="mb-3 row">
                                        <label class="col-lg-4 col-form-label" for="validationCustom09">身份证号码 <span class="text-danger">*</span>
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="validationCustom09" placeholder="输入老师方式（电话号）" required="" name="identity" >
                                            <div class="invalid-feedback">
                                                Please enter a digits.
                                            </div>
                                        </div>
                                    </div>

                                    <div class="mb-3 row">
                                        <div class="input-group mb-3">
                                            <button id="uploadButton" class="btn btn-primary btn-sm" type="button">上传头像</button>
                                            <div class="form-file">
                                                <input id="coverImage" type="file" class="form-file-input form-control">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="mb-3 row">
                                        <div class="col-lg-8 ms-auto">
                                            <button type="submit" class="btn btn-primary" onclick="submitEdit()">提交</button>
                                        </div>
                                    </div>
                                </div>
                        </div>

                        </form>


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
<script src="js/global.min.js"></script>

<script src="/js/global.min.js"></script>
<script src="/js/jquery.nice-select.min.js"></script>

<!-- Datatable -->
<script src="/js/jquery.dataTables.min.js"></script>
<script src="/js/datatables.init.js"></script>

<script src="/js/custom.min.js"></script>
<script src="/js/deznav-init.js"></script>
<script src="/js/demo.js"></script>
<script src="/js/styleSwitcher.js"></script>


<script>

    var ciurl = "";

    function submitEdit() {

        alert("添加")

        var addTusername=$("[name=username]").val();
        var addTrealname=$("[name=realname]").val();
        var addTclass_id=$("[name=class_id]").val();
        var addTage=$("[name=age]").val();
        var addTgender=$("[name=gender]").val();
        var addTphone=$("[name=phone]").val();
        var addTidentity=$("[name=identity]").val();

        alert("Class_IDDIDIDIID"+addTclass_id);
        $.ajax({
            type:"post",
            url:"http://localhost:8003/user/teacher/add_Suser",
            data:{
                "username":addTusername,
                "realname":addTrealname,
                "class_id":addTclass_id,
                "age":addTage,
                "gender":addTgender,
                "phone":addTphone,
                "identity":addTidentity,
                "account_info":ciurl

            },

            dataType:"json",
            success:function(data){
                window.location.href="http://localhost:8003/user/teacher"
            },
            error:function(data){
                alert("添加失败");
            }
        });
    }

    $("#uploadButton").click(function (){
        let file = $('#coverImage')[0].files[0]; // 获取选择的文件
        let formData = new FormData(); // 创建FormData对象
        formData.append('file', file); // 将文件添加到FormData对象
        console.log(formData)
        // 发送Ajax请求
        $.ajax({
            url: '/upload/updAvatar',
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function(data) {
                // 上传成功后的处理逻辑
                alert("上传成功！")
                ciurl = data.imgUrl;
                alert(data.imgUrl)
            },
            error: function(data) {
                // 上传失败后的处理逻辑
                alert("上传失败！")
                console.log(data)
            }
        });
    })

</script>



</body>
</html>

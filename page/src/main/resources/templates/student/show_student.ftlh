<#--

<!--**********************************
    Content body start
***********************************&ndash;&gt;
<#include "../header.ftlh">
<div class="content-body">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xl-12">
                <div class="d-flex mb-4 justify-content-between align-items-center flex-wrap">
                    <div class="table-search">
                        <div class="input-group search-area mb-xxl-0 mb-4">
                            <input type="text" class="form-control" placeholder="按照班级查询">
                            <span class="input-group-text"><a href="javascript:void(0)"><i class="flaticon-381-search-2"></i></a></span>
                        </div>
                    </div>
                    <a href="/update_addstudent" class="btn btn-primary mb-xxl-0 mb-4 "><i class="far fa-file-word me-2"></i>添加学生</a>
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
                                    <th>用户名</th>
                                    <th>姓名</th>
                                    <th>年龄</th>
                                    <th>性别</th>
                                    <th>联系方式</th>
                                    <th>身份证号码</th>
                                    <th>操作</th>
                                </tr>
                                </thead>


                                <tbody>

                                <#list  users as user>
                                    <tr>
                                        <td>
                                            <div class="form-check style-1">
                                                <input class="form-check-input" type="checkbox" value="">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="media-bx">
                                                <h4 class="mb-0 mt-1 text-black">${user.id}</h4>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="media-bx">
                                                <h4 class="mb-0 mt-1 text-black">${user.username}</h4>
                                            </div>
                                        </td>
                                        <td>
                                            <div>

                                               <#assign json=user.identityInfo?eval/>
                                                <h4 class="mb-0 mt-1 text-black">${json.realname}</h4>
                                            </div>
                                        </td>
                                        <td>
                                            <div>

                                               <h5>${json.age}</h5>

                                            </div>
                                        </td>
                                        <td>
                                            <div>
                                                <h5>${json.gender}</h5>
                                            </div>
                                        </td>
                                        <td>
                                            <div>
                                                <h5>${json.phone}</h5>
                                            </div>
                                        </td>
                                        <td>
                                            <div>
                                               <h5>${json.identity}</h5>
                                            </div>
                                        </td>
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
                                                    <a class="dropdown-item" href="/update_addteacher">修改</a>
                                                    <a class="dropdown-item" href="/user/deleteStudent?id=${user.id}">删除</a>
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
***********************************&ndash;&gt;



<!--**********************************
    Footer start
***********************************&ndash;&gt;
<div class="footer">

    <div class="copyright">
        <p>Copyright &copy; 2021.Company name All rights reserved.<a target="_blank" href="https://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p>
    </div>
</div>
<!--**********************************
    Footer end
***********************************&ndash;&gt;

<!--**********************************
   Support ticket button start
***********************************&ndash;&gt;

<!--**********************************
   Support ticket button end
***********************************&ndash;&gt;


</div>
<!--**********************************
    Main wrapper end
***********************************&ndash;&gt;

<!--**********************************
    Scripts
***********************************&ndash;&gt;
<!-- Required vendors &ndash;&gt;
<script src="/js/global.min.js"></script>
<script src="/js/jquery.nice-select.min.js"></script>

<!-- Datatable &ndash;&gt;
<script src="/js/jquery.dataTables.min.js"></script>
<script src="/js/datatables.init.js"></script>

<script src="/js/custom.min.js"></script>
<script src="/js/deznav-init.js"></script>
<script src="/js/demo.js"></script>
<script src="/js/styleSwitcher.js"></script>

</body>
</html>
-->

<!--**********************************
    Content body start
***********************************-->
<#include "../header.ftlh">
<div class="content-body">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xl-12">
                <div class="d-flex mb-4 justify-content-between align-items-center flex-wrap">
                    <div class="table-search">
                        <div class="input-group search-area mb-xxl-0 mb-4">
                            <input type="text" class="form-control" placeholder="按照老师姓名查询">
                            <span class="input-group-text"><a href="javascript:void(0)"><i class="flaticon-381-search-2"></i></a></span>
                        </div>
                    </div>
                    <a href="/update_addstudent" class="btn btn-primary mb-xxl-0 mb-4 "><i class="far fa-file-word me-2"></i>添加学生</a>
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
                                    <th>头像</th>
                                    <th>用户名</th>
                                    <th>班级</th>
                                    <th>姓名</th>
                                    <th>年龄</th>
                                    <th>性别</th>
                                    <th>联系方式</th>
                                    <th>身份证号码</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list  users as user>
                                    <tr>
                                        <td>
                                            <div class="form-check style-1">
                                                <input class="form-check-input" type="checkbox" value="">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="media-bx">
                                                <h4 class="mb-0 mt-1 text-black">${user.id}</h4>
                                            </div>
                                        </td>

                                        <td>
                                            <div class="media-bx">
                                                <img src="${user.accountInfo}" alt="cg" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="media-bx">
                                                <h4 class="mb-0 mt-1 text-black" va>${user.username}</h4>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="media-bx">
                                                <h4 class="mb-0 mt-1 text-black" va>${user.name}</h4>
                                            </div>
                                        </td>
                                        <td>
                                            <div>
                                                <#assign json=user.identityInfo?eval/>
                                                <h4 class="mb-0 mt-1 text-black">${json.realname}</h4>
                                            </div>
                                        </td>
                                        <td>
                                            <div>

                                                <h5>${json.age}</h5>

                                            </div>
                                        </td>
                                        <td>
                                            <div>
                                                <h5>${json.gender}</h5>
                                            </div>
                                        </td>
                                        <td>
                                            <div>
                                                <h5>${json.phone}</h5>
                                            </div>
                                        </td>
                                        <td>
                                            <div>
                                                <h5>${json.identity}</h5>
                                            </div>
                                        </td>
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
                                                    <a class="dropdown-item" href="/user/showUser_ById?id=${user.id}">修改</a>
                                                    <a class="dropdown-item" href="/user/deleteTeacher?id=${user.id}">删除</a>
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

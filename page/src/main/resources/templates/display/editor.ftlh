<#include "../header.ftlh"/>
<!--**********************************
            Content body start
        ***********************************-->
<div class="content-body">
    <div class="container-fluid">
        <div class="row page-titles">
            <ol class="breadcrumb">
                <li id="Menu" class="breadcrumb-item active">${type}</li>
                <li class="breadcrumb-item"><a href="javascript:void(0)">CkEditor</a></li>
            </ol>
        </div>

        <!-- row -->
        <div class="row">
            <div class="col-xl-12 col-xxl-12">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title">标题</h4>
                    </div>
                    <div class="card-body">
                        <h4>内容标题：</h4>
                        <input class="form-control" id="title" value="${display.title}" placeholder="title">
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xl-12 col-xxl-12">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title">编辑内容</h4>
                    </div>
                    <div class="card-body custom-ckeditor">
                        <div id="editor"></div>
                        <button id="submit" type="submit" class="btn btn-outline-success btn-xs" value="提交">提交</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--**********************************
    Content body end
***********************************-->
<!-- Required vendors -->
<script src="/js/global.min.js"></script>
<script src="/js/jquery.nice-select.min.js"></script>

<#--<script src="/js/ckeditor.js"></script>-->
<#--<script src="/js/ckeditor5.js"></script>-->
<script src="/js/ckeditor5super-build.js"></script>
<script src="/js/custom.min.js"></script>
<script src="/js/deznav-init.js"></script>
<script src="/js/demo.js"></script>
<script src="/js/styleSwitcher.js"></script>
<script src="/js/sweetalert2.min.js"></script>
<script>
    let editor;
    let str;
    let signupNum = "${signupNum}";
    let startTime = "${startTime}";
    let endTime = "${endTime}";
    <#--let title = "${title}";-->
    // This sample still does not showcase all CKEditor 5 features (!)
    // Visit https://ckeditor.com/docs/ckeditor5/latest/features/index.html to browse all the features.
    CKEDITOR.ClassicEditor.create(document.getElementById("editor"), {
        // https://ckeditor.com/docs/ckeditor5/latest/features/toolbar/toolbar.html#extended-toolbar-configuration-format
        toolbar: {
            items: [
                'exportPDF','exportWord', '|',
                'findAndReplace', 'selectAll', '|',
                'heading', '|',
                'bold', 'italic', 'strikethrough', 'underline', 'code', 'subscript', 'superscript', 'removeFormat', '|',
                'bulletedList', 'numberedList', 'todoList', '|',
                'outdent', 'indent', '|',
                'undo', 'redo',
                '-',
                'fontSize', 'fontFamily', 'fontColor', 'fontBackgroundColor', 'highlight', '|',
                'alignment', '|',
                'link', 'insertImage', 'blockQuote', 'insertTable', 'mediaEmbed', 'codeBlock', 'htmlEmbed', '|',
                'specialCharacters', 'horizontalLine', 'pageBreak', '|',
                'textPartLanguage', '|',
                'sourceEditing'
            ],
            shouldNotGroupWhenFull: true
        },
        // Changing the language of the interface requires loading the language file using the <script> tag.
        // language: 'es',
        list: {
            properties: {
                styles: true,
                startIndex: true,
                reversed: true
            }
        },
        // https://ckeditor.com/docs/ckeditor5/latest/features/headings.html#configuration
        heading: {
            options: [
                { model: 'paragraph', title: 'Paragraph', class: 'ck-heading_paragraph' },
                { model: 'heading1', view: 'h1', title: 'Heading 1', class: 'ck-heading_heading1' },
                { model: 'heading2', view: 'h2', title: 'Heading 2', class: 'ck-heading_heading2' },
                { model: 'heading3', view: 'h3', title: 'Heading 3', class: 'ck-heading_heading3' },
                { model: 'heading4', view: 'h4', title: 'Heading 4', class: 'ck-heading_heading4' },
                { model: 'heading5', view: 'h5', title: 'Heading 5', class: 'ck-heading_heading5' },
                { model: 'heading6', view: 'h6', title: 'Heading 6', class: 'ck-heading_heading6' }
            ]
        },
        // https://ckeditor.com/docs/ckeditor5/latest/features/editor-placeholder.html#using-the-editor-configuration
        placeholder: 'Welcome to CKEditor 5!',
        // https://ckeditor.com/docs/ckeditor5/latest/features/font.html#configuring-the-font-family-feature
        fontFamily: {
            options: [
                'default',
                'Arial, Helvetica, sans-serif',
                'Courier New, Courier, monospace',
                'Georgia, serif',
                'Lucida Sans Unicode, Lucida Grande, sans-serif',
                'Tahoma, Geneva, sans-serif',
                'Times New Roman, Times, serif',
                'Trebuchet MS, Helvetica, sans-serif',
                'Verdana, Geneva, sans-serif'
            ],
            supportAllValues: true
        },
        // https://ckeditor.com/docs/ckeditor5/latest/features/font.html#configuring-the-font-size-feature
        fontSize: {
            options: [ 10, 12, 14, 'default', 18, 20, 22 ],
            supportAllValues: true
        },
        // Be careful with the setting below. It instructs CKEditor to accept ALL HTML markup.
        // https://ckeditor.com/docs/ckeditor5/latest/features/general-html-support.html#enabling-all-html-features
        htmlSupport: {
            allow: [
                {
                    name: /.*/,
                    attributes: true,
                    classes: true,
                    styles: true
                }
            ]
        },
        // Be careful with enabling previews
        // https://ckeditor.com/docs/ckeditor5/latest/features/html-embed.html#content-previews
        htmlEmbed: {
            showPreviews: true
        },
        // https://ckeditor.com/docs/ckeditor5/latest/features/link.html#custom-link-attributes-decorators
        link: {
            decorators: {
                addTargetToExternalLinks: true,
                defaultProtocol: 'https://',
                toggleDownloadable: {
                    mode: 'manual',
                    label: 'Downloadable',
                    attributes: {
                        download: 'file'
                    }
                }
            }
        },
        // https://ckeditor.com/docs/ckeditor5/latest/features/mentions.html#configuration
        mention: {
            feeds: [
                {
                    marker: '@',
                    feed: [
                        '@apple', '@bears', '@brownie', '@cake', '@cake', '@candy', '@canes', '@chocolate', '@cookie', '@cotton', '@cream',
                        '@cupcake', '@danish', '@donut', '@dragée', '@fruitcake', '@gingerbread', '@gummi', '@ice', '@jelly-o',
                        '@liquorice', '@macaroon', '@marzipan', '@oat', '@pie', '@plum', '@pudding', '@sesame', '@snaps', '@soufflé',
                        '@sugar', '@sweet', '@topping', '@wafer'
                    ],
                    minimumCharacters: 1
                }
            ]
        },
        // The "super-build" contains more premium features that require additional configuration, disable them below.
        // Do not turn them on unless you read the documentation and know how to configure them and setup the editor.
        removePlugins: [
            // These two are commercial, but you can try them out without registering to a trial.
            // 'ExportPdf',
            // 'ExportWord',
            'CKBox',
            'CKFinder',
            'EasyImage',
            // This sample uses the Base64UploadAdapter to handle image uploads as it requires no configuration.
            // https://ckeditor.com/docs/ckeditor5/latest/features/images/image-upload/base64-upload-adapter.html
            // Storing images as Base64 is usually a very bad idea.
            // Replace it on production website with other solutions:
            // https://ckeditor.com/docs/ckeditor5/latest/features/images/image-upload/image-upload.html
            // 'Base64UploadAdapter',
            'RealTimeCollaborativeComments',
            'RealTimeCollaborativeTrackChanges',
            'RealTimeCollaborativeRevisionHistory',
            'PresenceList',
            'Comments',
            'TrackChanges',
            'TrackChangesData',
            'RevisionHistory',
            'Pagination',
            'WProofreader',
            // Careful, with the Mathtype plugin CKEditor will not load when loading this sample
            // from a local file system (file://) - load this site via HTTP server if you enable MathType.
            'MathType',
            // The following features are part of the Productivity Pack and require additional license.
            'SlashCommand',
            'Template',
            'DocumentOutline',
            'FormatPainter',
            'TableOfContents'
        ]
    }).then(
        newEditor => {
            editor = newEditor
        }).catch( error => {
        console.log(error)
    });

    function zalert(str){
        sweetAlert("Oops...", str, "error")
    }

    document.querySelector("#submit").addEventListener("click", ()=>{
        // getting data from editor
        const editorData = editor.getData();
        const title = $("#title").val();
        console.log(editorData);
        if (${display.id} === -1){
            alert("进入添加")
            if (editorData.trim()===""){
                zalert("内容不得为空")
            } else if (editorData.trim() !== str) {
                const date = new Date();
                const year = date.getFullYear(); // 获取年份，例如：2022
                const month = String(date.getMonth() + 1).padStart(2, '0'); // 获取月份，并补零，例如：09
                const day = String(date.getDate()).padStart(2, '0'); // 获取日期，并补零，例如：05
                const hour = String(date.getHours()).padStart(2, '0'); // 获取小时，并补零，例如：14
                const minute = String(date.getMinutes()).padStart(2, '0'); // 获取分钟，并补零，例如：30
                const second = String(date.getSeconds()).padStart(2, '0'); // 获取秒数，并补零，例如：45
                <#--const formattedDate = `${year}-${month}-${day} ${hour}:${minute}:${second}`;-->
                const formattedDate = year + '-' + month + '-' + day + " " + hour + ":" + minute + ":" + second
                console.log(formattedDate); // 输出类似于 "2022-09-05 14:30:45" 的字符串
                $.ajax({
                    async: true,
                    dataType: "json",
                    data: {
                        title: title,
                        content: editorData,
                        displayTypeId: 1,
                        createTime: formattedDate
                    },
                    method: "POST",
                    url: "/display/insertDisplay",
                    success: function () {
                        if ("${type}" === "学社活动") {
                            $.ajax({
                                async: true,
                                dataType: "json",
                                data: {
                                    title: title,
                                    signupNum: signupNum,
                                    startTime: startTime,
                                    endTime: endTime,
                                    createTime: formattedDate
                                },
                                method: "POST",
                                url: "/display/insertActivities",
                                success: function () {
                                    if ("${type}" === "学社活动"){
                                        window.location.href = "/display/inner_activities"
                                    }
                                    if ("${type}" === "每日一推"){
                                        window.location.href = "/display/daily_info"
                                    }
                                    if ("${type}" === "对外展览"){
                                        window.location.href = "/display/external_performance"
                                    }
                                },
                                error: function () {
                                    zalert("insert activities error")
                                }
                            });
                        }
                    },
                    error: function () {
                        zalert("failed")
                    }
                })
            } else {
                zalert("请添加内容描述")
            }
        } else {
            alert("进入修改")
            if (editorData.trim()===""){
                zalert("内容不得为空")
            } else if (editorData == `${display.content?no_esc}`){
                alert("内容未改变")
                if ("${type}" === "学社活动"){
                    window.location.href = "/display/inner_activities"
                }
                if ("${type}" === "每日一推"){
                    window.location.href = "/display/daily_info"
                }
                if ("${type}" === "对外展览"){
                    window.location.href = "/display/external_performance"
                }
            }
            else {
                $.ajax({
                    async:true,
                    dataType:"json",
                    data:{
                        id: "${display.id}",
                        title:title,
                        content: editorData
                    },
                    method:"POST",
                    url:"/display/updDisplay",
                    success:function (){
                        $.ajax({
                            async:true,
                            dataType:"json",
                            data:{
                                id:"${activitiesId}",
                                signupNumber:signupNum,
                                startTime:startTime,
                                endTime:endTime
                            },
                            method:"POST",
                            url:"/display/updActivities",
                            success:function (){
                                if ("${type}" === "学社活动"){
                                    window.location.href = "/display/inner_activities"
                                }
                                if ("${type}" === "每日一推"){
                                    window.location.href = "/display/daily_info"
                                }
                                if ("${type}" === "对外展览"){
                                    window.location.href = "/display/external_performance"
                                }
                            },
                            error:function () {
                                zalert("update Failed")
                            }
                        })
                    },
                    error:function (){
                        zalert("更新失败！")
                    }
                })
            }
        }
    })

    /**
     * fill in the data which transporting from edit Activities page once this page is load
     */


    $(document).ready(function (){
        $("#warningBtn").click();
        if (${display.id} === -1){
            if ("${type}" === '学社活动'){
                console.log(signupNum,startTime,endTime)
                str = "<p>本次活动每班报名人数为：<span style='font-size:18px;'>" +
                    signupNum +
                    "</span>！</p><p>活动报名时间为：<span style='font-size:18px;'><i><strong>" +
                    startTime +
                    "</strong></i></span>至<span style='font-size:18px;'><i><strong>" +
                    endTime +
                    "</strong></i></span></p>";
                editor.setData(str);
            }
        } else {
            editor.setData(`${display.content?no_esc}`);
        }

    })
</script>
<!-- External css -->
<style>
    .custom-ckeditor {
        width: 1000px;
        margin: 20px auto;
    }
    .ck-editor__editable[role="textbox"] {
        /* editing area */
        min-height: 200px;
    }
    .ck-content .image {
        /* block images */
        max-width: 80%;
        margin: 20px auto;
    }
</style>
<link href="/css/sweetalert2.min.css" rel="stylesheet">
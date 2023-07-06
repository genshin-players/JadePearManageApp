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
                        <h4 class="card-title">标题封面</h4>
                    </div>
                    <div class="card-body">
                        <h4>封面图片：</h4>
                        <input type="hidden" id="coverImageUrl" value="">
                        <div class="input-group mb-3">
                            <button id="uploadButton" class="btn btn-primary btn-sm" type="button">上传</button>
                            <div class="form-file">
                                <input id="coverImage" type="file" class="form-file-input form-control">
                            </div>
                        </div>
                        <div id="imageContainer"></div>
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
<#--<script src="//cdn.ckeditor.com/4.16.1/full/ckeditor.js"></script>-->
<script src="/js/custom.min.js"></script>
<script src="/js/deznav-init.js"></script>
<script src="/js/demo.js"></script>
<script src="/js/styleSwitcher.js"></script>
<script src="/js/sweetalert2.min.js"></script>
<script>
    let Editor;
    let str;
    let signupNum = "${signupNum}";
    let startTime = "${startTime}";
    let endTime = "${endTime}";
    var ciurl = "";
    <#--let title = "${title}";-->
    // This sample still does not showcase all CKEditor 5 features (!)
    // Visit https://ckeditor.com/docs/ckeditor5/latest/features/index.html to browse all the features.
    CKEDITOR.ClassicEditor.create(document.getElementById("editor"), {
        fileRepository: {
            uploadUrl: '/display/uploadImage'
        },
        /*image:{
            upload:{
                url: "/display/uploadImage",
                method:"POST",
                headers:{
                    'content-type': 'multipart/form-data'
                },
                withCredentials: true
            }
        },*/
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
            // 'CKBox',
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
            Editor = newEditor;
            /*//非base64图片上传处理
            editor.model.document.on('change:data', () => {
                //监听编辑器内容变化事件
                editor.model.document.on('change:data', () => {
                    //查找所有的img元素
                    const images = [];
                    editor.model.document.getDescendants().filter(item => item.is('element') && item.name === 'img').forEach(image => {
                        images.push(image);
                    });
                    images.forEach(image => {
                        //如果当前img元素是通过上传文件得到的非base64格式图片
                        if (image.isExternal()) {
                            //获取文件的URL
                            const imageUrl = image.getAttribute('src');
                            //使用XMLHttpRequest对象上传文件到服务器
                            const xhr = new XMLHttpRequest();
                            xhr.open('POST', 'your_upload_api_url');
                            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                            xhr.onload = () => {
                                if (xhr.status === 200) {
                                    //上传成功，获取返回的图片URL
                                    const response = JSON.parse(xhr.responseText);
                                    const imageUrl = response.imageUrl;
                                    //使用图片URL替换img元素
                                    editor.model.change(writer => {
                                        const newImage = writer.createElement('image', {
                                            src: imageUrl
                                        });
                                        writer.insert(newImage, image);
                                        writer.remove(image);
                                    });
                                } else {
                                    //上传失败，提示错误信息
                                    console.error('Failed to upload image:', xhr.statusText);
                                }
                            };
                            xhr.onerror = () => {
                                console.error('Failed to upload image:', xhr.statusText);
                            };

            <#--xhr.send(`image=${image}`);-->
                        }
                    });
                });
            });*/

            // 定义一个上传图片的方法
            /*function uploadImage(blobInfo) {
                return new Promise((resolve, reject) => {
                    const xhr = new XMLHttpRequest();
                    xhr.open('POST', '/display/uploadImage'); // 修改为正确的上传图片的 URL
                    xhr.onload = () => {
                        if (xhr.status === 200) {
                            // 上传成功，返回图片的 URL
                            const response = JSON.parse(xhr.responseText);
                            resolve({default: response.url});
                        } else {
                            // 上传失败，返回错误信息
                            reject(xhr.statusText);
                        }
                    };
                    xhr.onerror = () => {
                        // 上传失败，返回错误信息
                        reject(xhr.statusText);
                    };
                    const formData = new FormData();
                    formData.append('image', blobInfo.blob(), blobInfo.filename());
                    xhr.send(formData);
                });
            }

            // 监听编辑器内容变化事件
            editor.model.document.on('change:data', () => {
                const images = editor.model.document.getRoot().getChildren().filter(child => child.is('image'));
                images.forEach(image => {
                    if (image.isExternal()) {
                        // 如果图片是通过上传文件得到的非 Base64 格式图片
                        const imageUrl = image.getAttribute('src');
                        // 调用上传图片的方法，将图片上传到服务器
                        uploadImage({ blob: null, filename: () => imageUrl }).then(url => {
                            // 替换编辑器中的图片元素
                            editor.model.change(writer => {
                                writer.setAttribute('src', url.default, image);
                            });
                        }).catch(error => {
                            console.error('Failed to upload image:', error);
                        });
                    }
                });
            });*/
        })
        .catch( error => {
        console.log(error)
    });

    //拖拽与复制粘贴的图片文件上传 操你妈这他妈是ckeditor4的5用不了

    /*CKEDITOR.on('instanceReady', function (ev){
        ev.editor.on('dragstart',function (evt){
            let target = evt.data.$.dataTransfer;
            if (target && target.files && target.files.length){
                let formData = new FormData;
                formData.append('file', target.files[0]);
                let xhr = new XMLHttpRequest();
                xhr.open("POST",'/display/upload',true);
                xhr.onload = function () {
                    let response = xhr.responseText;
                    let img = ev.editor.document.createElement('img');
                    img.setAttribute('src',response);
                    ev.editor.insert(img)
                };
                xhr.send(formData);
            }
        });

        ev.editor.on('paste', function (evt) {
            var item = evt.data.dataValue;
            if (item && item.type && item.type.indexOf('image/') === 0) {
                var formData = new FormData();
                formData.append('file', item.getAsFile());
                var xhr = new XMLHttpRequest();
                xhr.open('POST', '/upload', true);
                xhr.onload = function () {
                    var response = xhr.responseText;
                    var img = ev.editor.document.createElement('img');
                    img.setAttribute('src', response);
                    ev.editor.insertElement(img);
                };
                xhr.send(formData);
            }
        });
    })*/

    $("#uploadButton").click(function (){
        let file = $('#coverImage')[0].files[0]; // 获取选择的文件
        let formData = new FormData(); // 创建FormData对象
        formData.append('file', file); // 将文件添加到FormData对象
        formData.append('id', "${display.id}")
        console.log(formData)
        // 发送Ajax请求
        $.ajax({
            url: '/display/saveCoverImage',
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function(data) {
                // 上传成功后的处理逻辑
                zalert("上传成功！","success")
                $("#imageContainer").html("");
                let img = $('<img>');
                img.attr('src', data.imgUrl);
                img.css({
                    'height': "300px",
                    'width': "auto"
                });
                img.appendTo('#imageContainer');
                ciurl = data.imgUrl;
                console.log(ciurl)
            },
            error: function(data) {
                // 上传失败后的处理逻辑
                zalert("上传失败！","error")
                console.log(data)
            }
        });
    })

    function zalert(str, type){
        sweetAlert(type, str, type)
    }

    document.querySelector("#submit").addEventListener("click", ()=>{
        // getting data from editor
        const editorData = editor.getData();
        const title = $("#title").val();
        console.log(editorData);
        if (${display.id} === -1){
            alert("进入添加")
            if (editorData.trim()===""){
                zalert("内容不得为空","error")
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
                                    createTime: formattedDate,
                                    coverImage: $("#coverImageUrl").val()
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
                                    zalert("insert activities error","error")
                                }
                            });
                        }
                    },
                    error: function () {
                        zalert("failed","error")
                    }
                })
            } else {
                zalert("请添加内容描述","error")
            }
        } else {
            alert("进入修改")
            if (editorData.trim()===""){
                zalert("内容不得为空","error")
            } else if (editorData == `${display.content?no_esc}`){
                alert("内容未改变")
                toLastPage()
            } else {
                let param = {id: "${display.id}",title:title,content: editorData,coverImage:$("#coverImageUrl").val()};
                alert(param);
                console.log(param)
                $.ajax({
                    async:true,
                    dataType:"json",
                    data:param,
                    method:"POST",
                    url:"/display/updDisplay",
                    success:function (){
                        if ("${type}" === "学社活动"){
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
                                    toLastPage()
                                },
                                error:function () {
                                    zalert("update Failed","error")
                                }
                            })
                        } else {
                            toLastPage()
                        }
                    },
                    error:function (){
                        zalert("更新失败！","error")
                    }
                })
            }
        }
    })

    function toLastPage(){
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

    /**
     * fill in the data which transporting from edit Activities page once this page is load
     */


    $(document).ready(function (){
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
                Editor.setData(str);
            }
        } else {
            Editor.setData(`${display.content?no_esc}`);
            let img = $(`<img>`);
            $("#coverImageUrl").val("${coverImage}");
            img.attr('src', coverImageUrl);
            img.css({
                'height': "300px",
                'width': "auto"
            });
            img.appendTo('#imageContainer');
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
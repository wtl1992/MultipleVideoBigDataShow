$(function () {

    /**
     * 处理点击事件，进行选择多媒体
     */
    $("#selectMediaDiv").click(function () {
        $("input[type=file]").click();
    });

    var fileObj = null;

    $("input[type=file]").on('change', function (event) {
        //e.currentTarget.files 是一个数组，如果支持多个文件，则需要遍历
        fileObj = event.currentTarget;
    });


    $("input[type=button]").click(function () {
        var formData = new FormData();
        formData.append("multipartFile",fileObj.files[0]);
        formData.append("type","voice");
        var type = $("#type").val();
        $.ajax({
            type: "POST",
            url: "weiXinUploadMedia/uploadMedia",
            data:formData,
            processData:false,  //tell jQuery not to process the data
            contentType: false,  //tell jQuery not to set contentType
            dataType: "json",
            success: function (data) {
                if (data != undefined && data.status === "success"){
                    alert("上传"+type+"成功！！！");
                }
                else{
                    alert(type+"上传失败！！！");
                }
            }
        });
    });

});
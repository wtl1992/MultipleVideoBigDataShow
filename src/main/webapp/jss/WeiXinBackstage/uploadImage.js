$(function () {

    /**
     * 处理点击事件，进行选择图片
     */
    $("#selectImageDiv").click(function () {
        $("input[type=file]").click();
    });

    $("input[type=file]").on('change', function(event){
        //e.currentTarget.files 是一个数组，如果支持多个文件，则需要遍历
        var fileObj = event.currentTarget;
        var windowURL = window.URL || window.webkitURL;
        $("img").attr("src",windowURL.createObjectURL(fileObj.files[0]));
    });


});
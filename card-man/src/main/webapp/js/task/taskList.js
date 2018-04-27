var searchTask = function () {
    var form = document.getElementById("searchFrom");
    form.submit();
}

var selectCheckboxAll = function (evt) {
    var checkbox = document.getElementsByName("taskIds");
    if (evt.checked) {
        for (var i = 0; i < checkbox.length; i++) {
            checkbox[i].checked = true;
        }
        evt.checked = true;
    } else {
        for (var i = 0; i < checkbox.length; i++) {
            checkbox[i].checked = false;
        }
        evt.checked = false;
    }
}

var taskReset = function (url, taskId) {
    var result = confirm('确定重置该任务吗，请谨慎操作！');
    if (result) {
        jQuery.ajax({
            type: 'POST',
            url: url,
            data: {id: taskId},
            success: function (data) {
                alert("更新成功");
                window.location.reload();
            },
            error: function () {
                alert("出错了");
            }
        })
    }

}

//根据输入的task主键，动态改变数据源
var changeShardId = function () {
    var taskId = jQuery("#id").val();
    if (null == taskId || taskId.length != 19) {
        return false;
    }
    var prefix = taskId.substr(0, 2);
    $("#shardId ").val(prefix + "00000000000000000");
}

//清空表单查询条件
var clearSearchForm = function () {
    $("#taskId ").val("");
    $("#uniqueId ").val("");
    $("#executeStatus ").val("");
    $("#taskType ").val("");
    $("#retryStrategy ").val("");
    $("#yn ").val("");
    $("#previouscreated ").val("");
    $("#postcreated ").val("");
    $("#previousExpTime ").val("");
    $("#postExpTime ").val("");
    $("#previousModified ").val("");
    $("#postModified ").val("");
    $("#bizId ").val("");
    $("#operationUser ").val("");
    $("#previousRetryTimes ").val("");
    $("#postRetryTimes ").val("");
}
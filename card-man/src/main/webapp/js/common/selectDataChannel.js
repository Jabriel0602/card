//操作选择渠道控件
var onSelectDataChannel = function (evt) {
    var id = evt.value;
    var dataClannel = document.getElementsByName("dataChannels");
    //点击全选
    if (id == -1) {
        if (evt.checked) {
            for (var i = 1; i < dataClannel.length; i++) {
                dataClannel[i].checked = true;
            }
        } else {
            for (var i = 1; i < dataClannel.length; i++) {
                dataClannel[i].checked = false;
            }
        }
    }
    //点击其它
    else {
        //判断是否存在没选的
        var flog = false;
        for (var i = 1; i < dataClannel.length; i++) {
            if(!dataClannel[i].checked){
                flog =true;
            }
        }

        if (flog) {
                dataClannel[0].checked = false;
        } else {
            dataClannel[0].checked = true;
        }
    }
}

var setDataChannelValue = function(){
    var check = document.getElementsByName("dataChannels");
    if(check[0].checked){
        document.getElementById("dataChannel").value = check[0].value;
        return;
    }
    var number = 0;
    for (var i = 1; i < check.length; i++) {
       if(check[i].checked){
           number = number + Number(check[i].value);
       }
    }
    if(number ==0){
        document.getElementById("dataChannel").value = '';
        return;
    }
    document.getElementById("dataChannel").value = number;
}

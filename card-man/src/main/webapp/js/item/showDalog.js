
var closeDiv = function(){
    document.getElementById("showDiv").setAttribute("style", "display:none;");
}
var checkShipSku = function(venderIdTemp,skuIdTemp,url){
    var skuId = document.getElementById(skuIdTemp).value;
    var venderId = document.getElementById(venderIdTemp).value;
    if(skuId==null || skuId==''){
        alert("商品SKU不能为空！");
        return;
    }

    if(venderId==null || venderId==''){
        alert("店铺ID不能为空！");
        return
    }

    jQuery.ajax({
        type: 'POST',
        url:  url ,
        data: { skuId: skuId, venderId: venderId },
        success: function(data){
            if(data == "false"){
                alert("无该商品信息！");
            }else{
                var product = eval("("+data+")");
                if(product.productId !=undefined){
                    document.getElementById("textProductId").innerHTML=product.productId;
                }
                if(product.title != undefined){
                    document.getElementById("textTitle").innerHTML=product.title;
                }
                if(product.price != undefined){
                    document.getElementById("textPrice").innerHTML=(product.skuPrice/100)+"元";
                }
                if(product.venderId != undefined){
                    document.getElementById("textVenderId").innerHTML=product.venderId;
                }
                if( product.shopId!=undefined){
                  document.getElementById("textShopId").innerHTML = product.shopId;
                }
                if(product.note != undefined){
                    document.getElementById("textSubTitle").innerHTML = product.subTitle;
                }
                document.getElementById("showDiv").setAttribute("style", "");
            }
        },
        error : function(){
            alert("出错了");
        }
    })
}
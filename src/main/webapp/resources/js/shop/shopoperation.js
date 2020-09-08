$(function () {
    var shopId = getQueryString('shopId');
    var isEdit = shopId?true:false;
    var initUrl = '/O2O/shopadmin/getshopinitinfo';
    var registerUrl ='/O2O/shopadmin/registershop';
    var shopinfoUrl='/O2O/shopadmin/getshopbyid?shopId='+shopId;
    var editShopUrl = '/O2O/shopadmin/modifyshop';
    var flag_name = false;
    var err_name="";
    var flag_addr = false;
    var err_addr="";
    var flag_phone = false;
    var err_phone="";
    if(!isEdit){
        getShopInitInfo();
    }
    else {
        getShopInfo(shopId);
        flag_name = true;
        flag_addr = true;
        flag_phone = true;

    }

    function getShopInfo(shopId) {

        $.getJSON(shopinfoUrl, function (data) {
            var shop = data.shop;
            $("#shop-name").val(shop.shopName);
            $("#shop-addr").val(shop.shopAddr);
            $("#shop-phone").val(shop.phone);
            $("#shop-desc").val(shop.shopDesc);
            var shopCategory = '<option data-id="' + shop.shopCategory.shopCategoryId + '" selected>' + shop.shopCategory.shopCategoryName + '</option>';
            var tempAreaHtml = '';
            data.areaList.map(function (item, index) {
                tempAreaHtml += '<option data-id="' + item.areaId + '" >' + item.areaName + '</option>';
            });
            $("#shop-category").html(shopCategory);
            $("#shop-category").attr('disabled', 'disabled');
            $("#area").html(tempAreaHtml);
            $("#area option[data-id='" + shop.area.areaId + "']").attr("selected", "selected");
        })

    }

    function getShopInitInfo() {
        $.getJSON(initUrl,function (data) {
            if(data.success){
                var tempHtml = '';
                var tempAreaHtml = ''
                data.shopCategoryList.map(function (item,index) {
                    tempHtml+='<option data-id="'+item.shopCategoryId+'">'+item.shopCategoryName+'</option>'
                });
                data.areaList.map(function (item,index) {
                    tempAreaHtml+='<option data-id="'+item.areaId+'">'+item.areaName+'</option>'
                })
                $("#shop-category").html(tempHtml);
                $("#area").html(tempAreaHtml);
            }
        });
    }

    $("#shop-name").blur(function () {
        var shop_name = $("#shop-name").val();
        if(shop_name.trim()==""||shop_name==null){
            flag_name = false;
            err_name="店铺名称不能为空"
            $("#err_name").html(err_name);
        }
        else {
            flag_name =true;
            err_name="";
            $("#err_name").html(err_name);
        }

    })
    $("#shop-addr").blur(function () {
        var shop_addr = $("#shop-addr").val();
        if(shop_addr.trim()==""||shop_addr==null){
            flag_addr = false;
            err_addr="详细地址不能为空";
            $("#err_addr").html(err_addr);
        }
        else {
            flag_addr =true;
            err_addr="";
            $("#err_addr").html(err_addr);
        }

    })
    $("#shop-phone").blur(function () {
        var shop_phone = $("#shop-phone").val();
        var reg = /^1[3456789]\d{9}$/;
        if(shop_phone.trim()==""||shop_phone==null||!reg.test(shop_phone)){
            flag_phone = false;
            err_phone = "手机号为空或不符合格式要求"
            $("#err_phone").html(err_phone);
        }
        else {
            flag_phone =true;
            err_phone="";
            $("#err_phone").html(err_phone);
        }
    })
    $("#btn01").click(function () {
        if(flag_phone&&flag_addr&&flag_name){
            var shop={};
            if(isEdit){
                shop.shopId =shopId;
            }
            shop.shopName=$("#shop-name").val();
            shop.shopAddr=$("#shop-addr").val();
            shop.phone=$("#shop-phone").val();
            shop.shopDesc=$("#shop-desc").val();
            shop.shopCategory={
                shopCategoryId:$("#shop-category").find('option').not(function () {
                    return !this.selected;
                }).data('id')};

            shop.area={
                areaId:$("#area").find('option').not(function () {
                    return !this.selected;
                }).data('id')};

            var shopImg = $("#shop-img")[0].files[0];
            var formData = new FormData();
            formData.append('shopImg',shopImg);
            formData.append('shopStr',JSON.stringify(shop));
            var verifyCodeActual = $("#j_kaptcha").val();
            if(verifyCodeActual==null){
                $.toast("请输入验证码");
                return;
            }
            else {
                formData.append('verifyCodeActual',verifyCodeActual);
            }
            $.ajax({
                url:(isEdit?editShopUrl:registerUrl),
                type:'POST',
                data:formData,
                contentType:false,
                processData:false,
                success:function (data) {
                    if(data.success) {
                        $.toast("提交成功");
                        $("#kaptcha_img").click();
                    }
                    else {
                        $.toast("提交失败"+data.errMsg);
                        $("#kaptcha_img").click();
                    }
                }
            })
        }
        else {
            $("#kaptcha_img").click();
            alert("输入信息有误");

        }
    })
    

})
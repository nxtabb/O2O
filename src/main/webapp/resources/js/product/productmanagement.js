$(function () {
    var listUrl = '/O2O/shopadmin/getproductlistbyshop?pageIndex=1&pageSize=999';
    var statusUrl = '/O2O/shopadmin/modifyproduct';
    getList();
    
    
    function changeItemStatus(id,enableStatus) {
        var product={};
        product.productId = id;
        product.enableStatus = enableStatus;
        $.ajax({
            url:statusUrl,
            type:'POST',
            data:{
                productStr:JSON.stringify(product),
                statusChange:true
            },
            dataType:'json',
            success:function (data) {
                if(data.success){
                    getList();
                }
                else {
                    alert("操作失败!");
                }


            }
        })

    }
    
    
    function getList() {
        $.getJSON(listUrl,function (data) {
            if(data.success){
                var productList=data.productList;
                var tempHtml ='';
                productList.map(function (item,index) {
                    var textOp = "下架";
                    var contraryStatus=0;
                    if(item.enableStatus==0){
                        textOp="上架";
                        contraryStatus = 1;
                    }else {
                        contraryStatus=0;
                    }
                    tempHtml += '<div class="row row-product">'
                        +'<div class="col-33">'
                        +item.productName
                        +'</div>'
                        +'<div class="col-20">'
                        + item.priority
                        +'</div>'
                        +'<div class="col-43">'
                        +'<a href="#" class="edit" data-id="'
                        +item.productId
                        +'" data-status"'
                        +item.enableStatus
                        +'">编辑</a>'
                        +'<a href="#" class="status" data-id="'
                        +item.productId
                        +'" data-status="'
                        +contraryStatus
                        +'">'
                        +textOp
                        +'</a>'
                        +'<a href="#" class="preview" data-id="'
                        +item.productId
                        +'" data-status"'
                        +item.enableStatus
                        +'">预览</a>'
                        +'</div>'
                        +'</div>';
                });
                $('.product-wrap').html(tempHtml);
            }

        });

        $(".product-wrap").on('click','a',function (e) {
            var target = $(e.currentTarget);
            if(target.hasClass('edit')){
                window.location.href='/O2O/shopadmin/productoperation?productId='+e.currentTarget.dataset.id;
            }else if(target.hasClass('status')){
                changeItemStatus(e.currentTarget.dataset.id,e.currentTarget.dataset.status);
            }else if(target.hasClass('preview')){
                window.location.href='/O2O/frontend/productdetail?productId='+e.currentTarget.dataset.id;
            }

        })

    }




})
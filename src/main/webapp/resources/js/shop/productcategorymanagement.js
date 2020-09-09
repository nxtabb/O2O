$(function () {
    var listUrl = '/O2O/shopadmin/getproductcategorylist';
    var addUrl ='/O2O/shopadmin/addproductcategorys';
    var deleteUrl ='/O2O/shopadmin/removeproductcategory';
    getList();
    
    function getList() {
        $.getJSON(listUrl,function (data) {
            if(data.success){
                var dataList =data.data;
                var tempHtml ='';
                $(".category-wrap").html('');
                dataList.map(function (item,index) {
                    tempHtml+=''
                        +'<div class="row row-product-category now">'
                        +'<div class="col-33 product-category-name">'
                        +item.productCategoryName
                        +'</div>'
                        +'<div class="col-33">'
                        +item.priority
                        +'</div>'
                        +'<div class="col-33" ><a href="#" class="button delete" data-id="'
                        +item.productCategoryId
                        +'">删除</a></div>'
                    +'</div>';
                });
                $(".category-wrap").append(tempHtml);
            }

        });
    }

    $("#new").click(function () {
        var tempHtml ='<div class="row row-product-category temp">'
            +'<div class="col-33 product-category-name"><input class="category-input category" type="text" placeholder="类别"></div>'
            +'<div class="col-33"><input class="category-input priority" type="number" placeholder="优先级"></div>'
            +'<div class="col-33"><a href="#" class="button delete">删除</a></div>';
        $(".category-wrap").append(tempHtml);
    });

    $("#submit").click(function () {
        var tempArr = $(".temp");
        var productCategoryList=[];
        tempArr.map(function (index,item) {
            var tempObj={};
            tempObj.productCategoryName = $(item).find(".category").val();
            tempObj.priority = $(item).find(".priority").val();
            if(tempObj.productCategoryName!=null&&tempObj.priority!=null){
                productCategoryList.push(tempObj);
            }
        });
        $.ajax({
            url:addUrl,
            type:'POST',
            data:JSON.stringify(productCategoryList),
            contentType:'application/json',
            success:function (data) {
                if(data.success){
                    alert("添加成功");
                    getList();
                }
                else {
                    alert("添加失败了，请重试");
                }
            }
        })
    })
    $('.category-wrap').on('click','.row-product-category.temp .delete',
        function (e) {
            $(this).parent().parent().remove();
        });

    $('.category-wrap').on('click','.row-product-category.now .delete',
        function (e) {
            var target = e.currentTarget;

                $.ajax({
                    url:deleteUrl,
                    type: 'POST',
                    data:{
                        productCategoryId:target.dataset.id
                    },
                    dataType:'json',
                    success:function (data) {
                        if(data.success){
                            alert("删除成功");
                            getList();
                        }
                        else{
                            alert("删除失败");
                        }
                    }
                });
        })
})
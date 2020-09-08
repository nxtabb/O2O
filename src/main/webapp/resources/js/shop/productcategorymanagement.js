$(function () {
    var listUrl = '/O2O/shopadmin/getproductcategorylist';
    var addUrl ='/O2O/shopadmin/addproductcategorys';
    var deleteUrl ='/O2O/shopadmin/removeproductcategory';
    getList();
    
    function getList() {
        // $.getJSON(listUrl,function (data) {
        //     if(data.success){
        //         var dataList =data.data;
        //         var tempHtml ='';
        //         dataList.map(function (item,index) {
        //             tempHtml+=''
        //                 +'<div class="row row-product-category now">'
        //                 +'<div class="col-33 product-category-name">'
        //                 +item.productCategoryName
        //                 +'</div>'
        //                 +'<div class="col-33">'
        //                 +item.prority
        //                 +'</div>'
        //             +'<div class="col-33" ><a href="#" class="b"  </div>'
        //         })
        //
        //     }
        //
        // })

    }

})
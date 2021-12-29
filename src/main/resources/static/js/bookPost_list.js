$(window).on("load", function(){
    loadList();
});

$("#bookname, #writeremail, #createtime, #viewcount").on("click", function(){
    loadList($(this));
});


function loadList(element){
    var idName = $(element).attr("id");
    var className = $(element).attr("class");
    var span = $($(element).children("span"));
    var orderString = "";

    if(element == null || element == undefined){
            orderString = "createtime desc";
    }else{
        if(className == "asc"){
            $(element).removeClass("asc").addClass("desc");
            span.text("↓");
        }
        if(className == "desc"){
            $(element).removeClass("desc").addClass("asc");
            span.text("↑");
        }

        var timeMethod = $("#createtime").attr("class");
        if(idName == "createtime"){
            orderString = "createtime "+timeMethod;
        }else{
            orderString = $(element).attr("id")+" "+$(element).attr("class")+", createtime "+timeMethod;
        }
    }
    //console.log(orderString);

    var page = $(".pagination li.active a").text();
    var categoryName = $(".categoryName").attr("id").toLowerCase();

    var queryParam = {
        "orderString":orderString,
        "page":page,
        "categoryName":categoryName
    };

     $.ajax({
        type:"GET",
        url:"/category/listOrder",
        data: queryParam,
    }).done(function(fragment){
        $("#listTable").replaceWith(fragment);
    });
}
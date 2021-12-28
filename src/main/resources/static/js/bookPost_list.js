
$("#createtime").on("click", function(){
    var className = $(this).attr("class");
    if(className == null || className == "" || className == undefined){
        $(this).addClass("asc");
    }
    if(className == "asc"){
        $(this).removeClass("asc");
        $(this).addClass("desc");
    }
    if(className == "desc"){
        $(this).removeClass("desc");
    }

     $.ajax({
        type:"POST",
        url:"/category/listOrder",
        contentType: 'application/json',
        data: JSON.stringify(member),
        success: function(data) {
          var inputs = "#"+elementId;
          var helps = "#"+elementId+"Help";
          $(helps).css("display", "block");

          data.forEach(e => {
            if(elementId == e.field){
              if(e.status == true){
                $(inputs).addClass("is-valid").removeClass("is-invalid");
                $(helps).addClass("valid-feedback").removeClass("invalid-feedback");
                $(helps).html(""+e.message);
              }else{
                $(inputs).addClass("is-invalid").removeClass("is-valid");
                $(helps).addClass("invalid-feedback").removeClass("valid-feedback");
                $(helps).html(""+e.message);
              }
            }
          });

        },
        error: function(){

        }
    });
});
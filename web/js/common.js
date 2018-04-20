function isEmptyJson(data) {
    if (isJsonFormat(data) && data != null && data != "" && data != undefined){
        $.each(data,function (i, e) {
            if(e.value.length == 0){
                return true;
            };
        });
    }else{
        return true;
    }
    return false;
}

function isJsonFormat( data ) {
    try {
        $.parseJSON( data );
    } catch (e) {
        return false;
    }
    return true;
}

function mgzAjx(async, url, data_type, type, data,success) {
    if(success == "" || success == null || success == undefined || !$.isFunction(success)){
        success = "";
    }
    $.ajax({
        async:async,
        url:url,
        dataType:data_type,
        type:type,
        data:data,
        success:success
    });
}

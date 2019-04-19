Ext.Ajax.on('requestcomplete', function (conn, response, options) {
    //如果已经
    if (response.getResponseHeader && response.getResponseHeader("sessionInvalid")) {
        window.location = 'login.html';
    }
//    //没有这个函数说明是form.submit,切session过期
//    if (!response.getResponseHeader && response.responseText.indexOf("success") == -1) {
//        window.location = 'login.html';
//    }
});  
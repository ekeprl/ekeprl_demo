
'use strict';

var util = {

    //이메일 양식 지정
    inputEmailform : function (obj){
        obj.value = obj.replace(/[^A-Za-z0-9.@]/g, '');
        },

    //비밀번호 양식 지정
    inputPwform : function (obj) {
        obj.value = obj.replace(/^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$/, '');
        },

    logoutConfirm : function () {
        if(confirm('로그아웃 하시겠습니까?')) {
            document.location.href = '/logout';
            }
        },


}
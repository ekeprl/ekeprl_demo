'use strict';

var doc = window.document;
doc.addEventListener('DOMContentLoaded', function(){
    javascriptFun.initHtml();
});



var userid = doc.getElementById("userid");
var userpw = doc.getElementById("userpw");



    var javascriptFun = {

        initHtml : function () {

            doc.getElementById('joinBtn').addEventListener("click", function () {
                javascriptFun.toJoin();
            });

            doc.getElementById('loginBtn').addEventListener("click",function (){
                javascriptFun.loginChk();
            });

        },


        toJoin: function() {

            var form = document.createElement('form');
            form.method = 'POST';
            form.action = '/join';

            document.body.appendChild(form);

            form.submit();
        },


        loginChk: function () {

            userid.value = userid.value.trim();

            if(userid.value.length < 1) {
                userid.focus();
                alert("아이디를 입력해주세요");
            }else if(userpw.value.length < 1) {
                userpw.focus();
                alert("비밀번호를 입력해주세요");
            }else {
                var loginForm = doc.getElementById('loginForm');
                loginForm.method="POST";
                loginForm.action='/login-security-try';
                loginForm.submit();
            }
        },

}
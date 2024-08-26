'use strict';

var doc = window.document;
doc.addEventListener('DOMContentLoaded', function(){
    JoinJsFun.initHtml();
});

var userid = doc.getElementById('userid');
var userpw = doc.getElementById('password');

var join_duplicateBtn = doc.getElementById('checkDuplicateBtn');



var JoinJsFun = {

    initHtml : function () {

        join_duplicateBtn.addEventListener("click", function(){
          JoinJsFun.duplicateChk();

        });


    },


    duplicateChk : function (userid) {
        if(!userid) {
            alert("아이디를 입력해주세요.")
        }else{
            var xhr = new XMLHttpRequest();
            xhr.timeout = 1000*8;
            xhr.open('POST', '/join-duplicateChk', true);
            xhr.responseType = 'text';
            xhr.setRequestHeader('Pragma', 'no-cache');
            xhr.setRequestHeader('Cache-Control', 'no-cache, must-revalidate');
            xhr.onreadystatechange = function(){ };
            xhr.ontimeout = function() { alert('요청 응답 시간 초과\n' + xhr.responseURL); };
            xhr.onload = function () {
                var response = Json.parse(xhr.response);
                if(res.RESULT === 'OK' && response.status === 'error'){
                    alert(response.message);
                }else if(res.RESULT === 'OK' && response.status === 'success') {
                    alert(response.message);
                }


            };
            xhr.onerror = function() { alert('ERROR : ' + xhr.status); };
        }


    }



}
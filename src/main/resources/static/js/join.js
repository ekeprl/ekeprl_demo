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


    duplicateChk : function () {

        if(userid.value.length < 5) {
            alert("아이디를 5글자 이상으로 입력해주세요.")
        }else{
            var fd = new FormData();
            fd.append('userid', userid.value);

            var xhr = new XMLHttpRequest();
            xhr.open('POST', '/join-duplicateChk', true);
            xhr.responseType = 'text';
            xhr.setRequestHeader('Pragma', 'no-cache');
            xhr.setRequestHeader('Cache-Control', 'no-cache, must-revalidate');
            xhr.onreadystatechange = function () { };
            xhr.onload = function () {
                if (xhr.status === 200) { // 응답이 성공적으로 완료된 경우
                    //formdata에 담아서 전달
                    var response = JSON.parse(xhr.response);
                    if(response.RESULT !== 'OK'){
                        alert(response.MESSAGE);
                    }

                } else {
                    alert('ERROR : ' + xhr.status);
                }
            };
            xhr.onerror = function() { alert('ERROR : ' + xhr.status); };
            xhr.send(fd);
        }


    }



}
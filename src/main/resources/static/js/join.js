'use strict';

var doc = window.document;
doc.addEventListener('DOMContentLoaded', function(){
    JoinJsFun.initHtml();
});

var userid = doc.getElementById('userid');
var userpw = doc.getElementById('password');
var confirm_password = doc.getElementById('confirm_password');
var email = doc.getElementById('email');

var join_duplicateBtn = doc.getElementById('checkDuplicateBtn');

var join_submitBtn = doc.getElementById('submitBtn');


//아이디 중복체크
var isduplicate = false;



var JoinJsFun = {

    initHtml : function () {

        join_duplicateBtn.addEventListener("click", function(){

          JoinJsFun.duplicateChk();

        });

        join_submitBtn.addEventListener("click", function(){

            JoinJsFun.submitChk();

        });


        email.addEventListener("keyup", function (){
            util.inputEmailform(this);
        })

        userpw.addEventListener("keyup", function (){
            util.inputPwform(this);
        })



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
                    if(response.RESULT === 'OK'){
                        console.log(" 메시지 : " + response.MESSAGE);
                        alert(response.MESSAGE);
                        isduplicate= true;
                        return isduplicate;
                    }else {
                        alert(response.MESSAGE);
                        isduplicate= true;
                        return isduplicate;
                    }

                }
            };
            xhr.onerror = function() { alert('ERROR : ' + xhr.status); };
            xhr.send(fd);
        }
    },

    submitChk : function() {

        if(isduplicate === false ) {
            alert("아이디 중복체크를 완료해주세요.");
        }else if(!email.value){
            alert("이메일을 입력해주세요.");
        }else if(!userpw.value){
            alert("비밀번호를 입력해주세요.");
        }else if(userpw.value !== confirm_password.value ){
            alert("비밀번호가 다릅니다. 다시 입력해주세요");
        }else {
            var fd = new FormData();
            fd.append('userid', userid.value);
            fd.append('userpw', userpw.value);
            fd.append('email', email.value);


            var xhr = new XMLHttpRequest();
            xhr.open('POST', '/join-submit', true);
            xhr.responseType = 'text';
            xhr.setRequestHeader('Pragma', 'no-cache');
            xhr.setRequestHeader('Cache-Control', 'no-cache, must-revalidate');
            xhr.onload = function () {
                if (xhr.status === 200) { // 응답이 성공적으로 완료된 경우
                    alert("회원가입이 완료되었습니다.")
                    window.location.href = '/login';
                    }else{
                    alert("회원가입에 실패했습니다. 다시 시도해주세요.")
                }

                }
            }
            xhr.onerror = function() { alert('ERROR : ' + xhr.status); };
            xhr.send(fd);

    },





}
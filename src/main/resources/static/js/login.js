
'use strict';

var doc = window.document;
doc.addEventListener('DOMContentLoaded', function(){
    javascriptFun.initHtml();
});


    var javascriptFun = {

        initHtml : function () {

            doc.getElementById('joinBtn').addEventListener("click", function () {
                javascriptFun.toJoin();
            });

        },


        toJoin: function() {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', '/join', true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    window.location.href = "/join";
                }
            };
            xhr.send();
        }



}
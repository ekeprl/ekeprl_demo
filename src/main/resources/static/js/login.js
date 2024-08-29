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
           /* var xhr = new XMLHttpRequest();
            xhr.open('POST', '/join', true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    doc.location.href = "/join";
                }
            };
            xhr.send();*/

            var form = document.createElement('form');
            form.method = 'POST';
            form.action = '/join'; // The URL to which the form will be submitted

            // Optionally add hidden inputs if you need to send additional data
            // var input = document.createElement('input');
            // input.type = 'hidden';
            // input.name = 'key';
            // input.value = 'value';
            // form.appendChild(input);

            // Append the form to the body
            document.body.appendChild(form);

            // Submit the form
            form.submit();
        }



}
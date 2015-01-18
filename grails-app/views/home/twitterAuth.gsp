<%--
  Created by IntelliJ IDEA.
  User: balexand
  Date: 1/14/2015
  Time: 7:40 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <script src="https://apis.google.com/js/client:platform.js" async defer></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
</head>

<body>
<hi>hello</hi>
<script>
    function success(data){
        var obj = JSON.parse(data);
        console.log('hi, '+obj["displayName"]+'.');
    }
    $.ajax({
        cache: false,
        type: "post",
        url: "${createLink(controller:'SpringSecurityOAuth',action:'twitterLogin')}",
        data: {a: 'a'},
        async: true,
        success: function (data) {
            success ( data   );
        },
        error: function (jqXHR, exception) {
            console.log('error!');
        }
    });</script>

<button onclick="launchLogin">Twitter</button>

</body>
</html>
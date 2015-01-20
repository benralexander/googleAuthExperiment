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

    var searchTwitter = function(text) {
        var success = function (data){
            var obj = JSON.parse(data);
            console.log('hi, '+obj["displayName"]+'.');
        };
        var inputFieldText = $('#searchterms').val();
        // let's go query twitter
        $.ajax({
            cache: false,
            type: "post",
            url: "${createLink(controller:'SpringSecurityOAuth',action:'twitterLogin')}",
            data: {a: inputFieldText},
            async: true,
            success: function (data) {
                success ( data   );
            },
            error: function (jqXHR, exception) {
                console.log('error!');
            }
        });

    };
</script>

<button onclick="searchTwitter()">search twitter</button>
<span>search terms<input type="text" name="tosearch" id='searchterms' maxlength="25"/></span><br>

</body>
</html>
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
<hi>practicing authentication</hi>
<ul>
    <li><a href="${createLink(controller:'home',action:'hello')}"> go home</a></li>
    <li><a href="${createLink(controller:'home',action:'googleAuthentication')}">Google authentication</a></li>
    <li><a href="${createLink(controller:'home',action:'twitterAuthentication')}">twitter authentication</a></li>
</ul>

</body>
</html>
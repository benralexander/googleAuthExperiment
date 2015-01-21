<%--
  Created by IntelliJ IDEA.
  User: balexand
  Date: 1/16/2015
  Time: 10:08 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Google authentication demo</title>
</head>

<body>
<h1>authentication successful</h1>
Why hello there ${displayName}. I believe that I have the following insights into your character:
<dl>
    <dt>your email</dt><dd>${email}</dd>
    <dt>Google ID</dt><dd>${ID}</dd>
    <dt>domain</dt><dd>${domain}</dd>
    <dt>language</dt><dd>${language}</dd>
    <dt>displayName</dt><dd>${displayName}</dd>
    <dt>objectType</dt><dd>${objectType}</dd>
</dl>
</body>
</html>
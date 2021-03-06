<!DOCTYPE HTML>
<html>
<head>
    <title>Widen Simple One-Way SSO - HTTP POST (application/x-www-form-urlencoded) Example</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>

<h1>Widen Simple One-Way SSO</h1>
<h2>HTTP POST (application/x-www-form-urlencoded) Example</h2>
<form action="${endpoint}" method="post">
    <input type="hidden" name="timestamp" value="${timestamp}">
    <input type="hidden" name="guid" value="${guid}">
    <input type="hidden" name="email" value="${email}">
    <input type="hidden" name="first_name" value="${first_name}">
    <input type="hidden" name="last_name" value="${last_name}">
    <input type="hidden" name="roles" value="${roles}">
    <input type="hidden" name="signature" value="${signature}">
    <button>Login</button>
</form>

</body>
</html>

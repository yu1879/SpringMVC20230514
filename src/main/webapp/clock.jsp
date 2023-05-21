<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clock</title>
</head>
<body style="padding: 10px">
	<img alt="100"
		src="${pageContext.request.servletContext }/images/clock.png">
	<p>
	<h1>現在時刻:${data}</h1>
</body>
</html>
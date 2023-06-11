<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spform"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
<meta charset="UTF-8">
<title>Person Form (Valid)</title>
</head>
<body style="padding: 15px">
	<spform:form class="pure-form" method="post" modelAttribute="person"
		action="${ pageContext.request.contextPath }/mvc/session13/person/">
		<fieldset>
			<legend>Person Form (Valid)</legend>
			姓名：
			<spform:input path="name" />
			<p />
			年齡：
			<spform:input path="age" />
			<p />
			會員：
			<spform:radiobutton path="member" value="true" />
			會員
			<spform:radiobutton path="member" value="false" />
			非會員
			<p />
			生日：
			<spform:input path="birth" type="date" />
			<p />
			<button type="sumbit" class="pure-button bure-button-primary">新增</button>
			<p />
			<!-- 列出所有錯誤訊息 -->
			<spform:errors path="*"></spform:errors>
		</fieldset>
	</spform:form>

	${ people }

</body>
</html>
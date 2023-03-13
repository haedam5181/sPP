<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="<c:url value='/resources/user/css/common.css' />" rel="stylesheet" type="text/css">
	<link href="<c:url value='/resources/user/css/create_account_form.css' />" rel="stylesheet" type="text/css">
	
	<script src="<c:url value='/resources/user/js/create_account_form.js' />" type="text/javascript"></script>
	
</head>
<body>
	
	<jsp:include page="../include/header.jsp"/>
	
	<section>
		<div id="section_wrap">
			
			<div class="word">
				 CREATE USER ACCOUNT FORM
			</div>
			
			<div class="create_account_form">
				
				<form action="<%=request.getContextPath()%>/user/member/create_account_confirm" name="create_account_form" method="post">
				
					<input type="text" name="t_id" placeholder="INPUT YOUR ID">
					<br>
					<input type="password" name="t_pw" placeholder="INPUT YOUR PW">					
					<br>
					<input type="button" value="CREATE ACCOUNT" onclick="createAccountForm();">
					<input type="reset" value="RESET">
				
				</form>
				
			</div>
			
			<div class="login">
				<a href="<%=request.getContextPath()%>/user/member/login_form">LOG IN</a>
			</div>
			
		</div>
	</section>
	
	<jsp:include page="../include/footer.jsp" />
	
</body>
</html>
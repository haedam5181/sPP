<%@page import="com.office.smartPlug.user.member.UserMemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value='/resources/user/css/header.css' />" rel="stylesheet" type="text/css">

<header>
	<div id="header_wrap">
		
		<div class="logo">
			smart <span>Plug</span>
		</div>
		
		<div class="gnb">
		
			<% 
				UserMemberVo userMemberVo =	(UserMemberVo) session.getAttribute("logined_userMemberVo");
			%>
			
			<%
				if (userMemberVo == null) {
			%>
			
			<a href="<c:url value='/' />">home</a>&nbsp;&nbsp; | &nbsp;&nbsp;
			<a href="<c:url value='/user/member/login_form' />">log-in</a>&nbsp;&nbsp;
			
			<%
                } else {
            %>
            
			<a href="<c:url value='/' />">home</a>&nbsp;&nbsp; | &nbsp;&nbsp;
			<a href="<c:url value='/user/smartPlug/smartPlug_list' />">조회 서비스</a>&nbsp;&nbsp; | &nbsp;&nbsp;
			<a href="<c:url value='/user/control' />">상태 및 컨트롤</a>&nbsp;&nbsp; | &nbsp;&nbsp;
			<a href="<c:url value='/user/member/logout_confirm' />">log-out</a>&nbsp;&nbsp; | &nbsp;&nbsp;
			<a href="<c:url value='/user/member/my_page' />">my page</a>&nbsp;&nbsp; | &nbsp;&nbsp;
			
			
			<%
                }
            %>
		</div>
		
	</div>
</header>
<%@page import="com.office.smartPlug.user.member.UserMemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="<c:url value='/resources/user/css/common.css' />"
	rel="stylesheet" type="text/css">
<link href="<c:url value='/resources/user/css/smartPlug_list.css' />"
	rel="stylesheet" type="text/css">

<jsp:include page="../include/common_js.jsp" />
<jsp:include page="../include/smartPlug_list_vo_js.jsp" />
<jsp:include page="../include/smartPlug_list_js.jsp" />

</head>
<body>

	<jsp:include page="../include/header.jsp" />

	<section>
		<div id="section_wrap">

			<%
			UserMemberVo userMemberVo =	(UserMemberVo) session.getAttribute("logined_userMemberVo");
			%>

			<div class="word">
				<span class="t_id"><%=userMemberVo.getT_id()%></span> smartPlug LIST
			</div>

			<div class="smartPlug_list_date_write">

				<select name="smartPlug_list_year_select">
					<option value="2020">2020</option>
					<option value="2021">2021</option>
					<option value="2022">2022</option>
					<option value="2023">2023</option>
					<option value="2024">2024</option>
				</select> 년 
				<select name="smartPlug_list_month_select">
					<option value="-1">-- 선택 --</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
				</select> 월 
				<select name="smartPlug_list_date_select">

				</select> 일
			</div>

			<!-- 목록(Template 이용) 시작 -->
			<div class="smartPlug_list">
				<table>
					<thead>
						<tr>
							<td>NO</td>
							<td>아이디</td>
							<td>ON/OFF</td>
							<td>등록일</td>
							<td>수정일</td>
						</tr>
					</thead>
				</table>
			</div>
			<!-- 목록(Template 이용) 끝 -->

			<!-- 페이지 시작 -->
			<div class="page_wrap">

				<div class="page_number">
				</div>

				<div class="page_goto">
					<input type="number" name="page_target_num">&nbsp; &#47; 
					<span class="page_total_num"></span> 
					<a class="goto_page_btn" href="#none">Go to page</a>
				</div>

			</div>
			<!-- 페이지 끝 -->


		</div>
	</section>

	<jsp:include page="../include/footer.jsp" />

</body>
</html>

<template id="smartPlug_list_item">

	<tbody>
		<tr>
			<td class="d_no"></td>
			<td class="t_id"></td>
			<td class="d_statuts"></td>
			<td class="d_reg_date"></td>
			<td class="d_mod_date"></td>
		</tr>
	</tbody>

</template>

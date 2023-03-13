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
<link href="<c:url value='/resources/user/css/my_page.css' />"
	rel="stylesheet" type="text/css">

<jsp:include page="../include/common_js.jsp" />
<jsp:include page="../include/my_page_js.jsp" />

</head>
<body>

	<jsp:include page="../include/header.jsp" />

	<section>
		<div id="section_wrap">

			<div class="word">MY PAGE</div>

			<div class="my_page">

				<form action="<c:url value='/user/member/my_page_confirm'/>"
					name="my_page_form" method="post">

					<input type="hidden" name="t_no" value="${userMemberVo.t_no}">

					<table>
						<tr>
							<td>NO</td>
							<td>${userMemberVo.t_no}</td>
						</tr>
						<tr>
							<td>아이디</td>
							<td>${userMemberVo.t_id}</td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td>********* <a
								href="<c:url value='/user/member/pw_modify'/>"><input
									type="button" value="비밀번호변경"></a></td>
						</tr>


					</table>

				</form>

			</div>

		</div>
	</section>

	<jsp:include page="../include/footer.jsp" />

</body>
</html>
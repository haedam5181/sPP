<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

   <link href="<c:url value='/resources/user/css/common.css'/>" rel="stylesheet" type="text/css">
   <link href="<c:url value='/resources/user/css/control.css'/>" rel="stylesheet" type="text/css">
   
   <jsp:include page="../include/common_js.jsp"/>
   <jsp:include page="../include/control_js.jsp"/>
   
</head>
<body>
   
   <jsp:include page="../include/header.jsp"/>
   
   <section>
      <div id="section_wrap">
         
         <div class="onoff_img">
              <img src="<c:url value="/resources/user/img/onoff.jpg"/>">
           </div>
           
           <div style="background-image: url('/resources/user/img/onoff.jpg')"></div>
         
         <div class="on_off_btn">
            <a class="on_btn" href="#none" onclick="ajax_controlSmartPlug(1);">ON</a> &nbsp;  &nbsp;
            <a class="off_btn" href="#none" onclick="ajax_controlSmartPlug(0);">OFF</a>
         </div>
         <div class="plug_status">
               PLUG STATUS: <span class="plug_status"></span>
           </div>
           
           
         
      </div>
   </section>
   
   <jsp:include page="../include/footer.jsp"/>
   
</body>
</html>
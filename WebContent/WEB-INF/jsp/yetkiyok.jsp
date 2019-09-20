<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML>


<html>
<head>
<meta http-equiv="Content-Type"
	content="application/vnd.ms-excel; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="<c:url value="/assets/gthbLogo.png"/>" />
<title>500 Sayfa Bulunamadı</title>


</head>
<body>

	<!-- header -->
	<jsp:include page="header.jsp" />
	<!-- /header -->

	<br>
	<div id="content" class="col-md-5 col-md-offset-3 col-xs-5 bg-danger "
		align="center">
		<hr></hr>
		<H1>Sayfaya giriş yetkiniz bulunmamaktadır....</H1>
		<h4>
			Anasayfaya sayfaya dönmek için <a href="#"
				onclick="javascript:window.location.href='../anasayfa';">tıklayınız...
			</a>

		</h4>

		<!--<span
				class="glyphicon glyphicon-arrow-left"></span>  -->

		<!-- /row -->
		<!-- footer -->
		<jsp:include page="footer.jsp" />
		<!-- /footer -->
	</div>



</body>
</html>
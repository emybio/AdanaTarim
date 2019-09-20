<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<table>
			<c:forEach items="${ilceListesi } " var="ilce" varStatus="i">
				<tr>
					<td>${i.count}</td>
					<td>-${ilce.[0]}</td>

					<td><a href="#" class="btn btn-info">${ilceListesi }</a></td>
					<td><a href="#" class="btn btn-danger">Sil</a></td>
				</tr>



			</c:forEach>
		</table>
	</div>
</body>
</html>
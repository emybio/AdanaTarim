<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<style type="text/css">
/* * {
	margin: 0px;
	padding: 0px;
}

table {
	margin: 0px;
	padding: 0px;
	width: 100%;
} 
table thead {
	background-color: rgba(255, 255, 255, 0.6);
}

table tbody {
	border: 1px solid black;
	background-color: rgba(255, 255, 255, 0);
}

table tfoot {
	background-color: rgba(255, 255, 255, .6);
}

table tbody tr th {
	text-align: center;
	border: 1px solid black;
}

table tbody tr td {
	text-align: center;
	border: 1px solid black;
}*/
</style>

</head>

<body>
	<c:set var="list" value="${aracCikisListesi}"></c:set>
	<c:set var="i" value="0"></c:set>
	<c:set var="listSize" value="0"></c:set>
	<div id="content" class="col-md-8  col-xs-12">
		<br>
		<div class="container col-md-offset-4">
			<div class="container-fluid">
				<div id="container">


					<c:choose>
						<c:when test="${cookie.id.value ne 1}">
							<c:if test="${param.id eq cookie.id.value }">
								<table class="table table-bordered table-hover bg-danger">
									<tbody>
										<tr>
											<td>AYLAR</td>
											<td>GÜN SAYISI</td>

										</tr>

										<c:forEach items="${aylar }" varStatus="sira" var="a">

											<tr>
												<td>${a}.Ay</td>

												<c:forEach items="${aracCikisListesi }" varStatus="sira"
													var="l">
													<c:if test="${a eq l.donemAy }">
														<c:set var="i" value="${i+1 }"></c:set>
														<c:set var="listSize" value="${listSize+1 }"></c:set>
													</c:if>
												</c:forEach>
												<td>${i }</td>
											</tr>
											<c:set var="i" value="0"></c:set>
										</c:forEach>
										<tr>
											<td>TOPLAM :</td>
											<td>${listSize}</td>
										</tr>
								</table>
							</c:if>
						</c:when>

						<c:when test="${cookie.id.value eq 1}">
							<table class="table table-bordered table-hover bg-danger">
								<tbody>
									<tr>
										<td>AYLAR</td>
										<td>GÜN SAYISI</td>

									</tr>

									<c:forEach items="${aylar }" varStatus="sira" var="a">

										<tr>
											<td>${a}.Ay</td>

											<c:forEach items="${aracCikisListesi }" varStatus="sira"
												var="l">
												<c:if test="${a eq l.donemAy }">
													<c:set var="i" value="${i+1 }"></c:set>
													<c:set var="listSize" value="${listSize+1 }"></c:set>
												</c:if>
											</c:forEach>
											<td>${i }</td>
										</tr>
										<c:set var="i" value="0"></c:set>
									</c:forEach>
									<tr>
										<td>TOPLAM :</td>
										<td>${listSize}</td>
									</tr>
							</table>
						</c:when>
					</c:choose>

				</div>
			</div>
		</div>
	</div>
</body>
</html>
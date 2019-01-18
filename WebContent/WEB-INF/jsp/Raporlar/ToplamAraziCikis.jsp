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
	<script type="text/javascript">
		var jq = jQuery.noConflict();

		jq(document).ready(function() {

		})
	</script>
	<c:set var="list" value="${aracCikisListesi}"></c:set>
	<c:set var="i" value="0"></c:set>
	<c:set var="j" value="0"></c:set>
	<c:set var="k" value="0"></c:set>
	<c:set var="listSize" value="0"></c:set>
	<div id="content" class="col-md-8  col-xs-12">
		<br>
		<div class="container col-md-offset-4">
			<div class="row">
				<c:choose>
					<c:when test="${cookie.id.value ne 1}">
						<c:if test="${param.id eq cookie.id.value }">
							<table class="table table-bordered table-hover bg-danger "
								style="font-size: 50px;">
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
											<td class="ilkUcAy">${i }</td>

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
						<table class="table  table-bordered bg-success "
							style="font-size: 16px; font-family: Monospace;">
							
							<thead >
							<tr>
									<th>AYLAR</th>
									<th>3 AYLIK</th>
									<th>6 AYLIK</th >
								</tr>
							</thead>
							<tbody>
								
								<!-- BİRİNCİ ÜÇ AYLIK BİRİNCİ ALTI AYLIK -->
								<tr>
									<td><table class="" border="1" style="width: 100%;" cellpadding="0" cellspacing="0"  frame="void">
											<c:forEach items="${aylar }" varStatus="sira" var="a"
												begin="0" end="2">

												<tr>
													<td>${a}.Ay</td>
													<c:forEach items="${aracCikisListesi }" varStatus="sira"
														var="l">
														<c:if test="${a eq l.donemAy }">
															<c:set var="i" value="${i+1 }"></c:set>
															<c:set var="j" value="${j+1 }"></c:set>
															<c:set var="k" value="${k+1 }"></c:set>
															<c:set var="listSize" value="${listSize+1 }"></c:set>
														</c:if>
													</c:forEach>
													<td class="birinciUcAylik birinciAltiAylik">${i }</td>

												</tr>
												<c:set var="i" value="0"></c:set>
											</c:forEach>
										</table></td>
									<td rowspan="" align="center"
										style="text-align: center; vertical-align: middle; line-height: 90px;">${j }</td>

									<c:set var="j" value="0"></c:set>
								</tr>
								<!-- İKİNCİ ÜÇ AYLIK BİRİNCİ ALTI AYLIK -->
								<tr>
									<td><table class="" border="1" style="width: 100%;" cellpadding="0" cellspacing="0"  frame="void">
											<c:forEach items="${aylar }" varStatus="sira" var="a"
												begin="3" end="5">
												<tr>
													<td>${a}.Ay</td>
													<c:forEach items="${aracCikisListesi }" varStatus="sira"
														var="l">
														<c:if test="${a eq l.donemAy }">
															<c:set var="i" value="${i+1 }"></c:set>
															<c:set var="j" value="${j+1 }"></c:set>
															<c:set var="k" value="${k+1 }"></c:set>
															<c:set var="listSize" value="${listSize+1 }"></c:set>
														</c:if>
													</c:forEach>
													<td class="ikinciUcAylik birinciAltiAylik">${i }</td>

												</tr>
												<c:set var="i" value="0"></c:set>
											</c:forEach>
										</table></td>
									<td align="center"
										style="text-align: center; vertical-align: middle; line-height: 90px;">${j}</td>
									<td align="center" style="border-top: none;">${k}</td>

									<c:set var="j" value="0"></c:set>
									<c:set var="k" value="0"></c:set>
								</tr>
								<!-- ÜÇÜNCÜ ÜÇ AYLIK İKİNCİ ALTI AYLIK -->
								<tr>
									<td><table class="" border="1" style="width: 100%;" cellpadding="0" cellspacing="0"  frame="void">
											<c:forEach items="${aylar }" varStatus="sira" var="a"
												begin="6" end="8">
												<tr>
													<td>${a}.Ay</td>
													<c:forEach items="${aracCikisListesi }" varStatus="sira"
														var="l">
														<c:if test="${a eq l.donemAy }">
															<c:set var="i" value="${i+1 }"></c:set>
															<c:set var="j" value="${j+1 }"></c:set>
															<c:set var="k" value="${k+1 }"></c:set>
															<c:set var="listSize" value="${listSize+1 }"></c:set>
														</c:if>
													</c:forEach>
													<td class="ucuncuUcAylik ikinciAltiAylik">${i }</td>


												</tr>
												<c:set var="i" value="0"></c:set>
											</c:forEach>
										</table></td>
									<td rowspan="" align="center"
										style="text-align: center; vertical-align: middle; line-height: 90px;">${j}</td>

									<c:set var="j" value="0"></c:set>
								</tr>


								<!-- DÖRDÜNCÜ ÜÇ AYLIK İKİNCİ ALTI AYLIK -->
								<tr>
									<td><table class="" border="1" style="width: 100%;" cellpadding="0" cellspacing="0"  frame="void">
											<c:forEach items="${aylar }" varStatus="sira" var="a"
												begin="9" end="11">
												<tr>
													<td>${a}.Ay</td>
													<c:forEach items="${aracCikisListesi }" varStatus="sira"
														var="l">
														<c:if test="${a eq l.donemAy }">
															<c:set var="i" value="${i+1 }"></c:set>
															<c:set var="j" value="${j+1 }"></c:set>
															<c:set var="k" value="${k+1 }"></c:set>
															<c:set var="listSize" value="${listSize+1 }"></c:set>
														</c:if>
													</c:forEach>
													<td class="dorduncuUcAylik ikinciAltiAylik">${i }</td>

												</tr>
												<c:set var="i" value="0"></c:set>
											</c:forEach>
										</table></td>
									<td rowspan="" align="center"
										style="text-align: center; vertical-align: middle; line-height: 90px;">${j}</td>
									<td align="center" style="border-top: none; width: 300px">${k}</td>
								</tr>
								<tr>
									<td>TOPLAM :</td>
									<td colspan="2" align="center">${listSize}</td>
								</tr>
						</table>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>


</body>
</html>
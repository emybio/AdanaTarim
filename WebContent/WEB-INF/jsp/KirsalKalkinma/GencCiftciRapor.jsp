<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>JQuery-validation demo | Bootstrap</title>
<style type="text/css">
select {
	-webkit-appearance: none;
	-moz-appearance: none;
	text-indent: 1px;
	text-overflow: '';
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<div class="pull-right">
					<h3>
						<a href=" ./gencCiftciXlsxExport" class="btn btn-default float-left"><img
							alt="Excel Report" class="rounded" width="35px"
							src="<c:url value='/assets/images/xlsx-3.png'/>"> TÜM
							LİSTEYİ EXCEL'E AKTAR</a>
					</h3>
				</div>
				<div class="page-header">&nbsp;</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">İlçelere Göre Genç Çiftçi Listesi</h3>
					</div>
					<div class="panel-body">
						<div style="overflow-x: auto;">

							<c:forEach items="${ilceler}" var="ilce">
								<script type="text/javascript">
									//alert('${ilce}');
									var jq = jQuery.noConflict();

									jq
											.ajax({
												type : "GET",
												url : '${pageContext.request.contextPath}/kirsal-kalkinma/ilcelereGoreGencCiftciListele.json',
												contentType : "application/x-www-form-urlencoded;charset=UTF-8",
												data : {
													ilce : '${ilce.isim}',
												},
												success : function(data) {

													var tblRow;
													var array = [];
													var soyisim = "";
													var kategori;
													for (var i = 0; i < data.length; i++) {

														/* tblRow =  data[i].durum ;
														console.log(tblRow);
														array.push(tblRow); */
														if (data[i].soyadi != null) {
															soyisim = data[i].soyadi;
														} else {

															soyisim = "";
														}

														/* if (typeof data[i].kategori.tip.tip.isim !== "undefined") {

															kategori = data[i].kategori.tip.tip.isim
																	+ " "
																	+ data[i].kategori.tip.isim
																	+ " "
																	+ data[i].kategori.isim
															console
																	.log(kategori);

														} else */
														/* 
														 if (typeof data[i].kategori.tip.isim !== "undefined") {
														 kategori = data[i].kategori.isim
														 + "<br>"
														 + data[i].kategori.tip.isim
														
														 } else if (typeof data[i].kategori.tip.tip.isim != "undefined") {

														 kategori = data[i].kategori.tip.tip.isim
														 + "<br>"
														 + data[i].kategori.tip.isim
														 + "<br>"
														 + data[i].kategori.isim
														

														 } else if (typeof data[i].kategori.isim === null) {

														 kategori = data[i].kategori.tip.tip.isim
														 + "<br>"
														 + data[i].kategori.tip.isim
														 } */
														kategori = data[i].kategori.isim
														tblRow = "<tr><td style ='word-break:break-all;'>"
																+ data[i].mahalle
																+ "</td><td style ='word-break:break-all;width:15em;'>"
																+ kategori
																+ "</td><td style ='word-break:break-all;'>"
																+ data[i].yil
																+ "</td><td style ='word-break:break-all;width:12em;'>"
																+ data[i].adi
																+ " "
																+ soyisim
																+ "</td><td style ='word-break:break-all;'>"
																+ data[i].hibeTutari
																+ "</td><td style ='word-break:break-all;'>"
																+ data[i].kapasite
																+ " "
																+ "<span style='text-transform:capitalize;'>"
																+ data[i].kapasiteBirim
																+ "</span>"
																+ "</td></tr>";
														jq(tblRow)
																.appendTo(
																		"#tr${ilce.isim}");
													}

												},
												complete : function(data) {

												},
												error : function(xhr,
														textStatus, errorThrown) {

													console.log(textStatus
															+ "***" + xhr
															+ "***"
															+ errorThrown);
												}

											});
								</script>
								<div class="panel panel-default">

									<div class="panel-heading">
										<div class="container-fluid ">
											<div class="col-sm-10">

												<button type="button"
													class="btn btn-info btn-sm float-left pull-left"
													data-toggle="collapse" data-target="#${ilce.isim}">${ilce.isim}
												</button>
											</div>
											<div class="col-sm-2">
												<a href=" ./gencCiftciXlsxExport?ilce=${ilce.isim}"
													class="float-left"><img alt="Excel Report"
													class="rounded" width="35px"
													src="<c:url value='/assets/images/xlsx-3.png'/>"></a>
											</div>
										</div>
									</div>
									<div class="panel-body">
										<div id="${ilce.isim}"
											class="collapse  table-responsive text-centered  ${ilce.isim}">
											<table
												class="table table-sm table-bordered table-hover bg-default "
												id="tr${ilce.isim }">
												<tr class="baslik">
													<%-- <td align="center"></td> --%>
													<th align="center">MAHALLE</th>
													<th align="center">YATIRIM KONUSU</th>
													<th align="center">YIL</th>
													<th align="center">YATIRIMCI ADI</th>
													<th align="center">HİBE TUTARI</th>
													<th align="center">KAPASİTE</th>

												</tr>
											</table>
										</div>
									</div>
								</div>
							</c:forEach>

						</div>


					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
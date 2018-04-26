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
<title>${title}</title>


<style type="text/css">
#sonSatir>td {
	font-weight: bold;
}

#yillaraGore td {
	text-align: center;
	border: 1 solid black;
}
</style>
</head>
<body>
	<div class="container-fluid" id="yazdir">
		<div class="container col-sm-5">
			<button type="button" class="btn btn-info" data-toggle="collapse"
				data-target="#ilce">İLÇEYE GÖRE RAPOR</button>
			<hr />
			<div id="ilce" class="collapse">
				<script type="text/javascript">
					var jq = jQuery.noConflict();

					function xxx() {

						//jq("#payment").change(function() {

						var value = jq("#payment option:selected").val();
						var theDiv = jq("#" + value);
						//	alert(value);

						theDiv.slideDown();
						theDiv.siblings('[class^= Ceyhan ]').slideUp();
						//	theDiv.siblings('[class^=${ilce}]').slideUp();
						//	});

					}
				</script>
				<%-- 	<select id="payment" onchange="xxx();">
					<c:forEach items="${ilceler}" var="ilce">
						<option value="${ilce }">${ilce }</option>
					</c:forEach>
				</select> --%>
				<c:forEach items="${ilceler}" var="ilce">
					<script type="text/javascript">
						//alert('${ilce}');
						var jq = jQuery.noConflict();

						jq
								.ajax({
									type : "GET",
									url : '${pageContext.request.contextPath}/kirsal-kalkinma/ilcelereGoreListele.json',
									contentType : "application/x-www-form-urlencoded;charset=UTF-8",
									data : {
										ilce : '${ilce}',
									},
									success : function(data) {

										var tblRow;
										var array = [];
										for (var i = 0; i < data.length; i++) {

											/* tblRow =  data[i].durum ;
											console.log(tblRow);
											array.push(tblRow); */

											tblRow = "<tr><td style ='word-break:break-all;'>"
													+ data[i].kategori
													+ "</td><td style ='word-break:break-all;'>"
													+ data[i].etapNo
													+ "</td><td style ='word-break:break-all;'>"
													+ data[i].yatirimciAdi
													+ "</td><td style ='word-break:break-all;'>"
													+ data[i].projeAdi
													+ "</td><td style ='word-break:break-all;'>"
													+ data[i].projeBedeli
													+ "</td><td style ='word-break:break-all;'>"
													+ data[i].hibeTutari
													+ "</td><td style ='word-break:break-all;'>"
													+ data[i].kapasite
													+ " "
													+ data[i].kapasiteBirim
													+ "</td><td style ='word-break:break-all;'>"
													+ data[i].istihdam
													+ "</td><td style ='word-break:break-all;'>"
													+ data[i].durum
													+ "</td></tr>";
											jq(tblRow).appendTo("#tr${ilce}");
										}

									},
									complete : function(data) {

									},
									error : function(xhr, textStatus,
											errorThrown) {

										console.log(textStatus + "***" + xhr
												+ "***" + errorThrown);
									}

								});
					</script>
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="container-fluid ">
								<div class="col-sm-10">

									<button type="button"
										class="btn btn-info btn-sm float-left pull-left"
										data-toggle="collapse" data-target="#${ilce}">${ilce}
									</button>
								</div>
								<div class="col-sm-2">
									<a href=" ./xlsxExport?ilce=${ilce}" class="float-left"><img
										alt="Excel Report" class="rounded" width="35px"
										src="<c:url value='/assets/images/xlsx-3.png'/>"></a>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<div id="${ilce}"
								class="collapse  table-responsive text-centered  ${ilce}">
								<table
									class="table table-sm table-bordered table-hover bg-default "
									id="tr${ilce }">
									<tr class="baslik">
										<%-- <td align="center"></td> --%>
										<th align="center">YATIRIM KONUSU</th>
										<th align="center">ETAP NO</th>
										<th align="center">YATIRIMCI ADI</th>
										<th align="center">PROJE ADI</th>
										<th align="center">PROJE BEDELİ</th>
										<th align="center">HİBE TUTARI</th>
										<th align="center">KAPASİTE</th>
										<th align="center">İSTİHDAM</th>
										<th align="center">DURUM</th>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="container-fluid">
			<div class="container col-md-5">
				<button type="button" class="btn btn-info" data-toggle="collapse"
					data-target="#etapNo">ETAP NO'YA GÖRE RAPOR</button>
				<hr />
				<div id="etapNo" class="collapse">
					<table class="table table-sm table-striped bg-info table-fixed "
						style="text-align: center; width: 100%;">
						<c:set var="list" value="${tumEkonomikYatirimListesi}" />
						<c:set var="listSize" value="${fn:length(list)}" />
						<thead>
							<tr>
								<td colspan="7"><h3>KIRSAL KALKINMA EKONOMİK
										YATIRIMLAR</h3></td>
							</tr>
						</thead>
						<tr>
							<td colspan="7" align="left"><b>${listSize}&nbsp;adet&nbsp;kayıt</b></td>
						</tr>
						<tr class="baslik">
							<td><img alt="Excel Report" class="rounded" width="25px"
								src="<c:url value='/assets/images/xlsx-3.png'></c:url>">
								Excel Rapor</td>
							<td align="center">ETAP NO</td>
							<td align="center">ADET</td>
							<td align="center">PROJE BEDELİ (TL)</td>
							<td align="center">HİBE TUTARI (TL)</td>
							<td align="center">KAPASİTE</td>
							<td align="center">İSTİHDAM</td>
						</tr>
						<tbody class="govde">
							<!------------------------ETAP NO 2---------------------------------------------->
							<c:set var="projeBedeli" value="0" />
							<c:set var="hibeTutari" value="0" />
							<c:set var="kapasite" value="0" />
							<c:set var="istihdam" value="0" />
							<c:set var="etapAdeti" value="0" />
							<c:forEach items="${tumEkonomikYatirimListesi }" var="yatirim"
								varStatus="sira">
								<c:if test="${yatirim.etapNo == 2 }">
									<c:set var="projeBedeli"
										value="${projeBedeli + yatirim.projeBedeli}" />
									<c:set var="hibeTutari"
										value="${hibeTutari + yatirim.hibeTutari}" />
									<c:set var="kapasite" value="${kapasite + yatirim.kapasite}" />
									<c:set var="istihdam" value="${istihdam + yatirim.istihdam}" />
									<c:set var="etapAdedi" value="${etapAdedi+1}" />
								</c:if>
							</c:forEach>
							<tr>
								<td><a href=" ./xlsxExport?etapNo=2"
									class="btn btn-primary btn-xs">2. Etap Excel'e Aktar</a></td>
								<td>2</td>
								<td>${etapAdedi}</td>
								<td><fmt:formatNumber pattern="#,##0.00" type="currency"
										value="${projeBedeli}" var="projeBedeli"></fmt:formatNumber>${projeBedeli}</td>
								<td><fmt:formatNumber pattern="#,##0.00 TL" type="currency"
										value="${hibeTutari}" var="hibeTutari"></fmt:formatNumber>${hibeTutari}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${kapasite}" var="kapasite"></fmt:formatNumber>${kapasite}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${istihdam}" var="istihdam"></fmt:formatNumber>${istihdam}</td>
							</tr>
							<!------------------------ETAP NO 3---------------------------------------------->

							<c:set var="projeBedeli2" value="0" />
							<c:set var="hibeTutari2" value="0" />
							<c:set var="kapasite2" value="0" />
							<c:set var="istihdam2" value="0" />
							<c:set var="etapAdeti2" value="0" />
							<c:forEach items="${tumEkonomikYatirimListesi }" var="yatirim"
								varStatus="sira">
								<c:if test="${yatirim.etapNo == 3 }">
									<c:set var="projeBedeli2"
										value="${projeBedeli2 + yatirim.projeBedeli}" />
									<c:set var="hibeTutari2"
										value="${hibeTutari2 + yatirim.hibeTutari}" />
									<c:set var="kapasite2" value="${kapasite2 + yatirim.kapasite}" />
									<c:set var="istihdam2" value="${istihdam2 + yatirim.istihdam}" />
									<c:set var="etapAdedi2" value="${etapAdedi2+1}" />
								</c:if>
							</c:forEach>
							<tr>
								<td><a href=" ./xlsxExport?etapNo=3"
									class="btn btn-primary btn-xs">3. Etap Excel'e Aktar</a></td>
								<td>3</td>
								<td>${etapAdedi2}</td>
								<td><fmt:formatNumber pattern="#,##0.00" type="currency"
										value="${projeBedeli2}" var="projeBedeli2"></fmt:formatNumber>${projeBedeli2}</td>
								<td><fmt:formatNumber pattern="#,##0.00 TL" type="currency"
										value="${hibeTutari2}" var="hibeTutari2"></fmt:formatNumber>${hibeTutari2}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${kapasite2}" var="kapasite2"></fmt:formatNumber>${kapasite2}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${istihdam2}" var="istihdam2"></fmt:formatNumber>${istihdam2}</td>
							</tr>
							<!-- ------------ETAP NO 4----------------- -->
							<c:set var="projeBedeli3" value="0" />
							<c:set var="hibeTutari3" value="0" />
							<c:set var="kapasite3" value="0" />
							<c:set var="istihdam3" value="0" />
							<c:set var="etapAdeti3" value="0" />
							<c:forEach items="${tumEkonomikYatirimListesi }" var="yatirim"
								varStatus="sira">
								<c:if test="${yatirim.etapNo == 4 }">
									<c:set var="projeBedeli3"
										value="${projeBedeli3 + yatirim.projeBedeli}" />
									<c:set var="hibeTutari3"
										value="${hibeTutari3 + yatirim.hibeTutari}" />
									<c:set var="kapasite3" value="${kapasite3 + yatirim.kapasite}" />
									<c:set var="istihdam3" value="${istihdam3 + yatirim.istihdam}" />
									<c:set var="etapAdedi3" value="${etapAdedi3+1}" />
								</c:if>
							</c:forEach>
							<tr>
								<td><a href=" ./xlsxExport?etapNo=4"
									class="btn btn-primary btn-xs">4. Etap Excel'e Aktar</a></td>
								<td>4</td>
								<td>${etapAdedi3}</td>
								<td><fmt:formatNumber pattern="#,##0.00" type="currency"
										value="${projeBedeli3}" var="projeBedeli3"></fmt:formatNumber>${projeBedeli3}</td>
								<td><fmt:formatNumber pattern="#,##0.00 TL" type="currency"
										value="${hibeTutari3}" var="hibeTutari3"></fmt:formatNumber>${hibeTutari3}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${kapasite3}" var="kapasite3"></fmt:formatNumber>${kapasite3}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${istihdam3}" var="istihdam3"></fmt:formatNumber>${istihdam3}</td>
							</tr>
							<!---------------ETAP NO 5--------------------  -->
							<c:set var="projeBedeli4" value="0" />
							<c:set var="hibeTutari4" value="0" />
							<c:set var="kapasite4" value="0" />
							<c:set var="istihdam4" value="0" />
							<c:set var="etapAdeti4" value="0" />
							<c:forEach items="${tumEkonomikYatirimListesi }" var="yatirim"
								varStatus="sira">
								<c:if test="${yatirim.etapNo == 5 }">
									<c:set var="projeBedeli4"
										value="${projeBedeli4 + yatirim.projeBedeli}" />
									<c:set var="hibeTutari4"
										value="${hibeTutari4 + yatirim.hibeTutari}" />
									<c:set var="kapasite4" value="${kapasite4 + yatirim.kapasite}" />
									<c:set var="istihdam4" value="${istihdam4 + yatirim.istihdam}" />
									<c:set var="etapAdedi4" value="${etapAdedi4+1}" />
								</c:if>
							</c:forEach>
							<tr>
								<td><a href=" ./xlsxExport?etapNo=5"
									class="btn btn-primary btn-xs">5. Etap Excel'e Aktar</a></td>
								<td>5</td>
								<td>${etapAdedi4}</td>

								<td><fmt:formatNumber pattern="#,##0.00" type="currency"
										value="${projeBedeli4}" var="projeBedeli4"></fmt:formatNumber>${projeBedeli4}</td>
								<td><fmt:formatNumber pattern="#,##0.00 TL" type="currency"
										value="${hibeTutari4}" var="hibeTutari4"></fmt:formatNumber>${hibeTutari4}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${kapasite4}" var="kapasite4"></fmt:formatNumber>${kapasite4}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${istihdam4}" var="istihdam4"></fmt:formatNumber>${istihdam4}</td>
							</tr>

							<!-------------ETAP NO 6----------------------  -->
							<c:set var="projeBedeli5" value="0" />
							<c:set var="hibeTutari5" value="0" />
							<c:set var="kapasite5" value="0" />
							<c:set var="istihdam5" value="0" />
							<c:set var="etapAdeti5" value="0" />
							<c:forEach items="${tumEkonomikYatirimListesi }" var="yatirim"
								varStatus="sira">
								<c:if test="${yatirim.etapNo == 6 }">
									<c:set var="projeBedeli5"
										value="${projeBedeli5 + yatirim.projeBedeli}" />
									<c:set var="hibeTutari5"
										value="${hibeTutari5 + yatirim.hibeTutari}" />
									<c:set var="kapasite5" value="${kapasite5 + yatirim.kapasite}" />
									<c:set var="istihdam5" value="${istihdam5 + yatirim.istihdam}" />
									<c:set var="etapAdedi5" value="${etapAdedi5+1}" />
								</c:if>
							</c:forEach>
							<tr>
								<td><a href=" ./xlsxExport?etapNo=6"
									class="btn btn-primary btn-xs">6. Etap Excel'e Aktar</a></td>
								<td>6</td>
								<td>${etapAdedi5}</td>
								<td><fmt:formatNumber pattern="#,##0.00" type="currency"
										value="${projeBedeli5}" var="projeBedeli5"></fmt:formatNumber>${projeBedeli5}</td>
								<td><fmt:formatNumber pattern="#,##0.00 TL" type="currency"
										value="${hibeTutari5}" var="hibeTutari5"></fmt:formatNumber>${hibeTutari5}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${kapasite5}" var="kapasite5"></fmt:formatNumber>${kapasite5}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${istihdam5}" var="istihdam5"></fmt:formatNumber>${istihdam5}</td>
							</tr>



							<!--------------ETAP NO 7---------------------  -->
							<c:set var="projeBedeli6" value="0" />
							<c:set var="hibeTutari6" value="0" />
							<c:set var="kapasite6" value="0" />
							<c:set var="istihdam6" value="0" />
							<c:set var="etapAdeti6" value="0" />
							<c:forEach items="${tumEkonomikYatirimListesi }" var="yatirim"
								varStatus="sira">
								<c:if test="${yatirim.etapNo == 7 }">
									<c:set var="projeBedeli6"
										value="${projeBedeli6 + yatirim.projeBedeli}" />
									<c:set var="hibeTutari6"
										value="${hibeTutari6 + yatirim.hibeTutari}" />
									<c:set var="kapasite6" value="${kapasite6 + yatirim.kapasite}" />
									<c:set var="istihdam6" value="${istihdam6 + yatirim.istihdam}" />
									<c:set var="etapAdedi6" value="${etapAdedi6+1}" />
								</c:if>
							</c:forEach>
							<tr>
								<td><a href=" ./xlsxExport?etapNo=7"
									class="btn btn-primary btn-xs">7. Etap Excel'e Aktar</a></td>
								<td>7</td>
								<td>${etapAdedi6}</td>

								<td><fmt:formatNumber pattern="#,##0.00" type="currency"
										value="${projeBedeli6}" var="projeBedeli6"></fmt:formatNumber>${projeBedeli6}</td>
								<td><fmt:formatNumber pattern="#,##0.00 TL" type="currency"
										value="${hibeTutari6}" var="hibeTutari6"></fmt:formatNumber>${hibeTutari6}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${kapasite6}" var="kapasite6"></fmt:formatNumber>${kapasite6}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${istihdam6}" var="istihdam6"></fmt:formatNumber>${istihdam6}</td>
							</tr>

							<!--------------ETAP NO 8---------------------  -->
							<c:set var="projeBedeli7" value="0" />
							<c:set var="hibeTutari7" value="0" />
							<c:set var="kapasite7" value="0" />
							<c:set var="istihdam7" value="0" />
							<c:set var="etapAdeti7" value="0" />

							<c:forEach items="${tumEkonomikYatirimListesi }" var="yatirim"
								varStatus="sira">
								<c:if test="${yatirim.etapNo == 8 }">
									<c:set var="projeBedeli7"
										value="${projeBedeli7 + yatirim.projeBedeli}" />
									<c:set var="hibeTutari7"
										value="${hibeTutari7 + yatirim.hibeTutari}" />
									<c:set var="kapasite7" value="${kapasite7 + yatirim.kapasite}" />
									<c:set var="istihdam7" value="${istihdam7 + yatirim.istihdam}" />
									<c:set var="etapAdedi7" value="${etapAdedi7+1}" />

								</c:if>
							</c:forEach>

							<tr>
								<td><a href=" ./xlsxExport?etapNo=8"
									class="btn btn-primary btn-xs">8. Etap Excel'e Aktar</a></td>
								<td>8</td>
								<td>${etapAdedi7}</td>

								<td><fmt:formatNumber pattern="#,##0.00" type="currency"
										value="${projeBedeli7}" var="projeBedeli7"></fmt:formatNumber>${projeBedeli7}</td>
								<td><fmt:formatNumber pattern="#,##0.00 TL" type="currency"
										value="${hibeTutari7}" var="hibeTutari7"></fmt:formatNumber>${hibeTutari7}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${kapasite7}" var="kapasite7"></fmt:formatNumber>${kapasite7}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${istihdam7}" var="istihdam7"></fmt:formatNumber>${istihdam7}</td>
							</tr>


							<!--------------ETAP NO 9---------------------  -->
							<c:set var="projeBedeli8" value="0" />
							<c:set var="hibeTutari8" value="0" />
							<c:set var="kapasite8" value="0" />
							<c:set var="istihdam8" value="0" />
							<c:set var="etapAdeti8" value="0" />

							<c:forEach items="${tumEkonomikYatirimListesi }" var="yatirim"
								varStatus="sira">
								<c:if test="${yatirim.etapNo == 9 }">
									<c:set var="projeBedeli8"
										value="${projeBedeli8 + yatirim.projeBedeli}" />
									<c:set var="hibeTutari8"
										value="${hibeTutari8 + yatirim.hibeTutari}" />
									<c:set var="kapasite8" value="${kapasite8 + yatirim.kapasite}" />
									<c:set var="istihdam8" value="${istihdam8 + yatirim.istihdam}" />
									<c:set var="etapAdedi8" value="${etapAdedi8+1}" />

								</c:if>
							</c:forEach>

							<tr>
								<td><a href=" ./xlsxExport?etapNo=9"
									class="btn btn-primary btn-xs">9. Etap Excel'e Aktar</a></td>
								<td>9</td>
								<td>${etapAdedi8}</td>

								<td><fmt:formatNumber pattern="#,##0.00" type="currency"
										value="${projeBedeli8}" var="projeBedeli8"></fmt:formatNumber>${projeBedeli8}</td>
								<td><fmt:formatNumber pattern="#,##0.00 TL" type="currency"
										value="${hibeTutari8}" var="hibeTutari8"></fmt:formatNumber>${hibeTutari8}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${kapasite8}" var="kapasite8"></fmt:formatNumber>${kapasite8}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${istihdam8}" var="istihdam8"></fmt:formatNumber>${istihdam8}</td>
							</tr>


							<!--------------ETAP NO 10---------------------  -->
							<c:set var="projeBedeli9" value="0" />
							<c:set var="hibeTutari9" value="0" />
							<c:set var="kapasite9" value="0" />
							<c:set var="istihdam9" value="0" />
							<c:set var="etapAdeti9" value="0" />

							<c:forEach items="${tumEkonomikYatirimListesi }" var="yatirim"
								varStatus="sira">
								<c:if test="${yatirim.etapNo == 10 }">
									<c:set var="projeBedeli9"
										value="${projeBedeli9 + yatirim.projeBedeli}" />
									<c:set var="hibeTutari9"
										value="${hibeTutari9 + yatirim.hibeTutari}" />
									<c:set var="kapasite9" value="${kapasite9 + yatirim.kapasite}" />
									<c:set var="istihdam9" value="${istihdam9 + yatirim.istihdam}" />
									<c:set var="etapAdedi9" value="${etapAdedi9+1}" />

								</c:if>
							</c:forEach>

							<tr>
								<td><a href=" ./xlsxExport?etapNo=10"
									class="btn btn-primary btn-xs">10. Etap Excel'e Aktar</a></td>
								<td>10</td>
								<td>${etapAdedi9}</td>

								<td><fmt:formatNumber pattern="#,##0.00" type="currency"
										value="${projeBedeli9}" var="projeBedeli9"></fmt:formatNumber>${projeBedeli9}</td>
								<td><fmt:formatNumber pattern="#,##0.00 TL" type="currency"
										value="${hibeTutari9}" var="hibeTutari9"></fmt:formatNumber>${hibeTutari9}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${kapasite9}" var="kapasite9"></fmt:formatNumber>${kapasite9}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${istihdam9}" var="istihdam9"></fmt:formatNumber>${istihdam9}</td>
							</tr>

							<!--------------ETAP NO 11---------------------  -->
							<c:set var="projeBedeli10" value="0" />
							<c:set var="hibeTutari10" value="0" />
							<c:set var="kapasite10" value="0" />
							<c:set var="istihdam10" value="0" />
							<c:set var="etapAdeti10" value="0" />

							<c:forEach items="${tumEkonomikYatirimListesi }" var="yatirim"
								varStatus="sira">
								<c:if test="${yatirim.etapNo == 11 }">
									<c:set value="${yatirim.etapNo}" var="etapNo11" />
									<c:set var="projeBedeli10"
										value="${projeBedeli10 + yatirim.projeBedeli}" />
									<c:set var="hibeTutari10"
										value="${hibeTutari10 + yatirim.hibeTutari}" />
									<c:set var="kapasite10"
										value="${kapasite10 + yatirim.kapasite}" />
									<c:set var="istihdam10"
										value="${istihdam10 + yatirim.istihdam}" />
									<c:set var="etapAdedi10" value="${etapAdedi10+1}" />

								</c:if>
							</c:forEach>

							<tr>
								<td><a href=" ./xlsxExport?etapNo=11"
									class="btn btn-primary btn-xs">11. Etap Excel'e Aktar</a></td>
								<td>${etapNo11 }</td>
								<td>${etapAdedi10}</td>

								<td><fmt:formatNumber pattern="#,##0.00" type="currency"
										value="${projeBedeli10}" var="projeBedeli10"></fmt:formatNumber>${projeBedeli10}</td>
								<td><fmt:formatNumber pattern="#,##0.00 TL" type="currency"
										value="${hibeTutari10}" var="hibeTutari10"></fmt:formatNumber>${hibeTutari10}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${kapasite10}" var="kapasite10"></fmt:formatNumber>${kapasite10}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${istihdam10}" var="istihdam10"></fmt:formatNumber>${istihdam10}</td>
							</tr>


							<!--------------ETAP NO 12---------------------  -->
							<c:set var="projeBedeli11" value="0" />
							<c:set var="hibeTutari11" value="0" />
							<c:set var="kapasite11" value="0" />
							<c:set var="istihdam11" value="0" />
							<c:set var="etapAdeti11" value="0" />
							<c:forEach items="${tumEkonomikYatirimListesi }" var="yatirim"
								varStatus="sira">
								<c:if test="${yatirim.etapNo == 12 }">
									<c:set value="${yatirim.etapNo}" var="etapNo12" />
									<c:set var="projeBedeli11"
										value="${projeBedeli11 + yatirim.projeBedeli}" />
									<c:set var="hibeTutari11"
										value="${hibeTutari11 + yatirim.hibeTutari}" />
									<c:set var="kapasite11"
										value="${kapasite11 + yatirim.kapasite}" />
									<c:set var="istihdam11"
										value="${istihdam11 + yatirim.istihdam}" />
									<c:set var="etapAdedi11" value="${etapAdedi11+1}" />
								</c:if>
							</c:forEach>
							<tr>
								<td><a href=" ./xlsxExport?etapNo=12"
									class="btn btn-primary btn-xs">12. Etap Excel'e Aktar</a></td>
								<td>${etapNo12 }</td>
								<td>${etapAdedi11}</td>
								<td><fmt:formatNumber pattern="#,##0.00" type="currency"
										value="${projeBedeli11}" var="projeBedeli11"></fmt:formatNumber>${projeBedeli11}</td>
								<td><fmt:formatNumber pattern="#,##0.00 TL" type="currency"
										value="${hibeTutari11}" var="hibeTutari11"></fmt:formatNumber>${hibeTutari11}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${kapasite11}" var="kapasite11"></fmt:formatNumber>${kapasite11}</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${istihdam11}" var="istihdam11"></fmt:formatNumber>${istihdam11}</td>
							</tr>
							<!--------------ETAP NO 13---------------------  -->
							<c:set var="projeBedeli12" value="0" />
							<c:set var="hibeTutari12" value="0" />
							<c:set var="kapasite12" value="0" />
							<c:set var="istihdam12" value="0" />
							<c:set var="etapAdeti12" value="0" />
							<c:forEach items="${tumEkonomikYatirimListesi }" var="yatirim"
								varStatus="sira">
								<c:if test="${yatirim.etapNo == 13 }">
									<c:set value="${yatirim.etapNo}" var="etapNo13" />
									<c:set var="projeBedeli12"
										value="${projeBedeli12 + yatirim.projeBedeli}" />
									<c:set var="hibeTutari12"
										value="${hibeTutari12 + yatirim.hibeTutari}" />
									<c:set var="kapasite12"
										value="${kapasite12 + yatirim.kapasite}" />
									<c:set var="istihdam12"
										value="${istihdam12 + yatirim.istihdam}" />
									<c:set var="etapAdedi12" value="${etapAdedi12+1}" />
								</c:if>
							</c:forEach>
							<c:if test="${etapNo13}">
								<tr>
									<td>${etapNo13}</td>
									<td>${etapAdedi12}</td>
									<td><fmt:formatNumber pattern="#,##0.00" type="currency"
											value="${projeBedeli12}" var="projeBedeli12"></fmt:formatNumber>${projeBedeli12}</td>
									<td><fmt:formatNumber pattern="#,##0.00 TL"
											type="currency" value="${hibeTutari12}" var="hibeTutari12"></fmt:formatNumber>${hibeTutari12}</td>
									<td><fmt:formatNumber pattern="#,##0" type="number"
											value="${kapasite12}" var="kapasite12"></fmt:formatNumber>${kapasite12}</td>
									<td><fmt:formatNumber pattern="#,##0" type="number"
											value="${istihdam12}" var="istihdam12"></fmt:formatNumber>${istihdam12}</td>
								</tr>
							</c:if>
							<tr id="sonSatir">
								<td><a href=" ./xlsxExport" class="btn btn-primary btn-xs">Tüm
										Listeyi Excel'e Aktar</a></td>
								<td>GENEL TOPLAM:</td>
								<td>${listSize }</td>
								<td><fmt:formatNumber pattern="#,##0.00" type="currency"
										value="${tumEkonomikYatirimListesi.stream().map(yatirim -> yatirim.projeBedeli).sum()}"
										var="projeBedeli"></fmt:formatNumber>${projeBedeli }</td>
								<td><fmt:formatNumber pattern="#,##0.00" type="currency"
										value="${tumEkonomikYatirimListesi.stream().map(yatirim -> yatirim.hibeTutari).sum()}"
										var="hibeTutari"></fmt:formatNumber>${hibeTutari }</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${tumEkonomikYatirimListesi.stream().map(yatirim -> yatirim.kapasite).sum()}"
										var="kapasite"></fmt:formatNumber>${kapasite }</td>
								<td><fmt:formatNumber pattern="#,##0" type="number"
										value="${tumEkonomikYatirimListesi.stream().map(yatirim -> yatirim.istihdam).sum()}"
										var="istihdam"></fmt:formatNumber>${istihdam }</td>
							</tr>
						</tbody>
					</table>
					<c:forEach items="${tumEkonomikYatirimListesi }" var="yatirim"
						varStatus="sira">
						<c:if test="${yatirim.etapNo eq 2 }">
							<c:set var="etapNo" value="${yatirim.etapNo}"></c:set>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="container col-sm-5">
			<button type="button" class="btn btn-info" data-toggle="collapse"
				data-target="#ilc">ETAP VE KATEGORİYE GÖRE RAPOR</button>
			<hr />
			<div id="ilc" class="collapse">
				<c:forEach items="${etapNoListesi}" var="etapNo">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="container-fluid ">
								<div class="col-sm-10">
									<button type="button"
										class="btn btn-info btn-sm float-left pull-left"
										data-toggle="collapse" data-target="#${etapNo}">${etapNo}.
										ETAP</button>
								</div>
								<div class="col-sm-2">
									<a href=" ./xlsxExport?ilce=${etapNo}" class="float-left"><img
										alt="Excel Report" class="rounded" width="35px"
										src="<c:url value='/assets/images/xlsx-3.png'/>"></a>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<div id="${etapNo}"
								class="collapse  table-responsive text-centered  ${etapNo}">
								<table
									class="table table-sm table-bordered table-hover bg-default "
									id="tr${etapNo}">
									<tr class="baslik">
										<th align="center">PROJE ADI</th>
										<th align="center">PROJE ADETİ</th>
									</tr>
									<c:set var="i" value="0"></c:set>
									<c:forEach items="${tumEkonomikYatirimListesi }" var="yatirim">
										<c:if test="${yatirim.etapNo == etapNo }">
											<c:if test="${fn:contains(yatirim.projeAdi,'PERVANE') }">
												<c:set var="i" value="${i+1}">
												</c:set>
											</c:if>
										</c:if>
									</c:forEach>
									<c:if test="${i ne 0 }">
										<tr>
											<td>PERVANE</td>
											<td>${i}</td>
										</tr>
									</c:if>
								</table>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

</body>
</html>
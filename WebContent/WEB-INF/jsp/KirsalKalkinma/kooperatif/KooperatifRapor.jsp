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
<style type="text/css">
select {
	-webkit-appearance: none;
	-moz-appearance: none;
	text-indent: 1px;
	text-overflow: '';
}
</style>

<script type="text/javascript">
	var tableToExcel = (function() {
		var uri = 'data:application/vnd.ms-excel;base64,', template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8"><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>', base64 = function(
				s) {
			return window.btoa(unescape(encodeURIComponent(s)))
		}, format = function(s, c) {
			return s.replace(/{(\w+)}/g, function(m, p) {
				return c[p];
			})
		}
		return function(table, name) {
			if (!table.nodeType)
				table = document.getElementById(table)
			var ctx = {
				worksheet : name || 'Worksheet',
				table : table.innerHTML
			}
			window.location.href = uri + base64(format(template, ctx))
		}
	})();
	function ilceyeVeTureGoreKayitSayisi(ilce, tur, id) {
		{
			jq
					.ajax({
						type : "GET",
						url : "./ilceyeVeTureGoreKayitSayisi",
						dataType : "JSON",
						contentType : "application/x-www-form-urlencoded;charset=UTF-8",
						data : {
							ilce : ilce,
							tur : tur,
						},
						beforeSend : function() {
							// jq('span').text("İçerik Yükleniyor....");
						},
						success : function(gelen) {
							jq("#" + id).text(Number(gelen));
							//console.log(id + "-" + gelen);
						},
						complete : function() {

							jq(document)
									.ready(
											function() {
												var rowTotal = 0;
												jq("." + ilce)
														.each(
																function() {

																	var value = jq(
																			this)
																			.text();
																	if (!isNaN(value)) {
																		rowTotal += parseFloat(value);
																	}

																});

												jq("#toplam" + ilce).text(
														rowTotal);

												var columnTotal = 0;

												jq("." + tur)
														.each(
																function() {

																	var value = jq(
																			this)
																			.text();
																	if (!isNaN(value)) {
																		columnTotal += parseFloat(value);
																	}

																});

												jq("#toplam" + tur).text(
														columnTotal);
												console
														.log(jq("#toplam" + tur)
																+ " : "
																+ jq(
																		"#toplam"
																				+ tur)
																		.text(
																				columnTotal));

											});

						},
						error : function(xhr, textStatus, errorThrown) {
							//	alert(textStatus);
						}
					})

			;

		}

	};

	function JSONtureVeIlceyeGoreKooperatifler(ilce, tur) {

		var spanID = ilce + tur;
		{
			jq
					.ajax({
						type : "GET",
						url : "${pageContext.request.contextPath}/kirsal-kalkinma/JSONtureVeIlceyeGoreKooperatifler.json",
						dataType : "JSON",
						contentType : "application/x-www-form-urlencoded;charset=UTF-8",
						data : {
							id : tur,
							ilce_id : ilce,
						},
						beforeSend : function() {
							// jq('span').text("İçerik Yükleniyor....");
						},
						success : function(gelen) {

							for (var i = 0; i < gelen.length; i++) {
								console.log(tur + "-" + ilce + "----"
										+ gelen[i].kooperatifAdi);

								jq(".altTablo" + spanID).text(
										gelen[i].kooperatifAdi);
							}
						},

						complete : function() {

						},
						error : function(xhr, textStatus, errorThrown) {
							//	alert(textStatus);
						}
					})

			;

		}

	};

	function durumDegistir(durum) {

		if (durum != "") {
			var res = encodeURI("./durumaGoreListele?durum=" + durum);
			window.location.href = decodeURI(res);
		} else {

			window.location.href = "./kooperatifRapor";
		}
	};
</script>
</head>
<body>
<br>
	<div class="col-md-12  ">

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					AKTİF/DAĞILMIŞ DURUMA GÖRE KOOPERATİF ORTAK SAYILARI <a href="#" style="align:right;"
						onclick="tableToExcel('altTable', 'Kategori ve İlçeye Göre Rapor')"
						class="btn float-left" ><img alt="Excel Report" class="rounded"
						width="20px" src="<c:url value='/assets/images/xlsx-3.png'/>">

						${empty param.durum  ? 'Tüm Tabloyu Aktar' : param.durum +=' Durumda Olanları Aktar'}
					</a>

				</h3>


			</div>
			<table class="table table-hover table-border " id="altTable">
				<tr>
					<td>İLÇE</td>
					<td>KOOPERTATİF TÜRÜ</td>
					<td>KOOPERATİF ADI</td>
					<td>ORTAK SAYISI</td>
					<td>DURUMU <select class="form-control" name="durum"
						onchange="durumDegistir(this.value);">
							<option value="">SEÇ...</option>
							<c:forEach items="${durumListesi }" var="durum">
								<option ${param.durum eq durum ? 'selected' : ''  }>${durum }</option>
							</c:forEach>

					</select></td>
				</tr>
				<c:set var="koopSayisi" value="0">
				</c:set>
				<c:set var="ilceSayisi" value="0">
				</c:set>
				<%-- 	<c:forEach items="${kategoriListesi}" var="kategori" varStatus="x">
				<c:forEach items="${ilceListesi}" var="ilce" varStatus="y"> --%>
				<c:forEach items="${ kooperatif}" var="koop" varStatus="i">
					<%-- <c:if test="${koop.kooperatifIlceID.isim eq ilce.isim }">
							<c:set var="ilceSayisi" value="${ilceSayisi+1 }">
							</c:set>
							<c:if test="${koop.kooperatifTurID.turAdi eq kategori.turAdi  }">
								<c:set var="koopSayisi" value="${koopSayisi+1 }">
								</c:set> --%>
					<tr>
						<td align="left">${koop.kooperatifIlceID.isim}</td>
						<td align="left" class="">${koop.kooperatifTurID.turAdi }<span></span>
						</td>
						<td align="left">${ koop.kooperatifAdi}</td>
						<td align="left">${ koop.ortakSayisi}</td>
						<td align="left">${ koop.durum}</td>
					</tr>
					<%-- </c:if>
						</c:if> --%>
				</c:forEach>
				<%-- </c:forEach>
			</c:forEach> --%>


			</table>
			<br> <br> <br>
		</div>
	</div>
	<div class="col-sm-12 ">
		<br>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					İLÇELERE GÖRE KOOPERATİF SAYILARI <a href="#"
						onclick="tableToExcel('raporTable', 'Kategori ve İlçeye Göre Rapor')"
						class="float-left"><img alt="Excel Report" class="rounded"
						width="35px" src="<c:url value='/assets/images/xlsx-3.png'/>"></a>
				</h3>
			</div>
			<div class="panel-body">
				<div style="overflow-x: auto;" id="raporDiv">
					<c:set var="toplam" value="${0}"></c:set>
					<c:set var="string" value=""></c:set>

					<table class="table table-hover table-border" id="raporTable">
						<tr>
							<td></td>
							<c:forEach items="${kategoriListesi}" var="kategori">
								<td>${kategori.turAdi}</td>
							</c:forEach>

							<td>TOPLAM</td>
						</tr>

						<c:forEach items="${ilceListesi}" var="ilce">

							<tr>
								<td>${ilce.isim}</td>
								<c:forEach items="${kategoriListesi}" var="kategori"
									varStatus="x">
									<script type="text/javascript">
										ilceyeVeTureGoreKayitSayisi(
												'${ilce.isim}',
												'${kategori.id}',
												'${ilce.isim}${kategori.id}');
									</script>
									<td><span id="${ilce.isim}${kategori.id}"
										class="${ilce.isim }  ${kategori.id}"> </span></td>
								</c:forEach>
								<td><span id="toplam${ilce.isim }"></span></td>
							</tr>
						</c:forEach>
						<tr>
							<td>TOPLAM</td>
							<c:forEach items="${kategoriListesi}" var="kategori">
								<td><span id="toplam${kategori.id}"></span></td>
							</c:forEach>
							<td><b>${fn:length(kooperatif)}</b></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
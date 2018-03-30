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
<meta http-equiv="content-type"
	content="application/vnd.ms-excel; charset=UTF-8">
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
						window.location.href = uri
								+ base64(format(template, ctx))
					}
				})()
			</script>





			<div class="col-sm-8 col-sm-offset-2">

				<div class="pull-right">
					<h3>
						<a href="./genc-ciftci" class="btn btn-success btn-md"> <span
							class="glyphicon glyphicon-plus"></span> YENİ KAYIT
						</a>
					</h3>
				</div>
				<div class="pull-right">
					<h3>

						<input type="button" class="btn" onclick="tableToExcel('testTable', 'Rapor')"
							value="Excel'e Aktar">

					</h3>
				</div>
				<div class="page-header">&nbsp;</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Genç Çiftçi Listesi</h3>
					</div>
					<div class="panel-body">
						<div style="overflow-x: auto;">
							<table
								class="table table-sm table-bordered table-hover bg-default "
								id="testTable">
								<caption>Genç Çiftçi Listesi</caption>
								<colgroup align="center"></colgroup>
								<colgroup align="left"></colgroup>
								<colgroup span="2" align="center"></colgroup>
								<colgroup span="3" align="center"></colgroup>
								<thead valign="top">
									<tr class="baslik">
										<%-- <td align="center"></td> --%>
										<th align="center">Yararlanıcı</th>
										<th align="center">Proje Konusu</th>
										<th align="center">Uygulama Yeri</th>
										<th align="center">Kapasite</th>
										<th align="center">Hibe Tutarı</th>
										<th align="center">Yıl</th>

										<th align="center" colspan="2">Sil / Güncelle</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${gencCiftci}" var="genc">
										<tr>
											<td>${genc.yararlaniciAdi }&nbsp;${genc.yararlaniciSoyadi }</td>


											<c:if
												test="${empty genc.kategori.tip.tip.isim and empty genc.kategori.tip.isim}">

												<td>${genc.kategori.isim}</td>

											</c:if>
											<c:if
												test="${empty genc.kategori.tip.tip.isim and !empty genc.kategori.tip.isim}">

												<td>${genc.kategori.tip.isim}-${genc.kategori.isim}</td>

											</c:if>
											<c:if
												test="${!empty genc.kategori.tip.tip.isim and !empty genc.kategori.tip.isim}">

												<td>${genc.kategori.tip.tip.isim}-${genc.kategori.tip.isim}-${genc.kategori.isim}</td>

											</c:if>
											<td>${genc.mahalle.tip.tip.isim}-${genc.mahalle.tip.isim}-${genc.mahalle.isim}</td>
											<td>${genc.kapasite}-<span class="text-capitalize">${genc.kapasiteBirim}</span>
											</td>
											<td>${genc.hibeTutari}</td>
											<td>${genc.yil}</td>

											<td><a
												href="${pageContext.request.contextPath }/kirsal-kalkinma/gencCiftciSil?id=${genc.id}"
												onclick="javascript:return confirm('${genc.yararlaniciAdi} ${ genc.yararlaniciSoyadi } isimli kaydı : \n Silmek İstediğinize Emin misiniz?');"
												class="btn btn-danger btn-sm">Sil</a></td>
											<td><a
												href="${pageContext.request.contextPath }/kirsal-kalkinma/gencCiftciGuncelle/${genc.id}"
												class="btn btn-primary btn-sm">Güncelle</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>


					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
</script>
</head>
<body>
	<br>
	<div class="col-md-8 col-sm-offset-2 ">

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					ARICILIK ÖDEME CETVELİ <a href="#" style="align: right;"
						onclick="tableToExcel('altTable', 'Kategori ve İlçeye Göre Rapor')"
						class="btn float-left"><img alt="Excel Report" class="rounded"
						width="20px" src="<c:url value='/assets/images/xlsx-3.png'/>">
						${empty param.durum  ? 'Tüm Tabloyu Aktar' : param.durum +=' Durumda Olanları Aktar'}
					</a> <a href="./aricilik" class="pull-right"> <span
						class="glyphicon glyphicon-plus"></span> YENİ KAYIT
					</a>
				</h3>
			</div>
			<table class="table table-hover table-border " id="altTable">
				<tr>
					<td>SIRA</td>
					<td>YIL</td>
					<td>İLÇE</td>
					<td>ADI</td>
					<td>BABA ADI</td>
					<td>DOĞUM TARİHİ</td>
					<td>TOPLAM KDV HARİÇ FATURA BEDELİ (TL)</td>
					<td>HİBE DESTEK BEDELİ (%60)</td>
					<td>ÖDENECEK BEDEL</td>
					<td align="center" colspan="2">Sil / Güncelle</td>
				</tr>
				<c:forEach items="${ tumListe}" var="arici" varStatus="i">
					<tr>
						<td align="left">${i.count}</td>
						<td align="left" class="">${arici.yil}</td>
						<td align="left" class="">${arici.ilce.isim }</td>
						<td align="left">${ arici.yararlaniciAdiSoyadi}</td>
						<td align="left">${ arici.yararlaniciBabaAdiSoyadi}</td>
						<td align="left">${ arici.dogumTarihi}</td>
						<td align="left">${ arici.faturaBedeli}</td>
						<td align="left"><c:set value="${ arici.faturaBedeli*0.6 }"
								var="N"></c:set> ${N+((N%1>0.5)?(1-(N%1))%1:-(N%1))}</td>
						<td align="left">${ N gt 5000 ? '5000': N+(1-(N%1))%1}</td>
						<td><a
							href="${pageContext.request.contextPath }/kirsal-kalkinma/ariciSil?id=${arici.id}"
							onclick="javascript:return confirm('${arici.yararlaniciAdiSoyadi} isimli kaydı : \n Silmek İstediğinize Emin misiniz?');"
							class="btn btn-danger btn-sm ">Sil</a></td>
						<td><a
							href="${pageContext.request.contextPath }/kirsal-kalkinma/ariciGuncelle/${arici.id}"
							class="btn btn-success btn-sm">Güncelle</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br> <br>
	</div>
</body>
</html>
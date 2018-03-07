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
} */
table thead {
	background-color: rgba(255, 255, 255, 0.6);
}

table tbody {
	border: 1px solid black;
	background-color: rgba(255, 255, 255, 1);
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
}
</style>

</head>

<body>
	<div id="content" class="col-md-8  col-xs-12">

		<div class="container col-md-offset-4">
			<div class="container-fluid">

				<div id="container">
					<table>
						<thead>
							<tr>
								<td colspan="4" style="margin-left: 20px;"><img
									style="margin-left: 75px;"
									src="${pageContext.request.contextPath}/assets/gthbLogo.png"
									width="35px"></td>
								<td align="right"></td>
								<td align="right">Personel Adı: ${kullanici.adi }</td>
							</tr>
							<tr>
								<td colspan="4">GIDA TARIM VE HAYVANCILIK BAKANLIĞI</td>
								<td align="right"></td>
								<td align="right">Personel Unvan: ${kullanici.unvan }</td>
							</tr>
							<tr>
								<td colspan="4"><span style="margin-left: 35px;">ADANA
										İL MÜDÜRLÜĞÜ</span></td>
								<td align="right"></td>
								<td align="right">Personel SicilNo: ${kullanici.sicilNo }</td>
							</tr>
							<tr>
								<td align="right" colspan="4"></td>
								<td align="right"></td>
								<td align="right">Dönem:${aylar}<!--  <select
					style="border: none; -webkit-appearance: none;" name="birinciAy"
					id="birinciAy" onchange="ucAylikToplam()">
						<option value="" label="--- Seçiniz ---" />
						<option value="01" label="Ocak"></option>
						<option value="02" label="Şubat"></option>
						<option value="03" label="Mart"></option>
						<option value="04" label="Nisan"></option>
						<option value="05" label="Mayıs"></option>
						<option value="06" label="Haziran"></option>
						<option value="07" label="Temmuz"></option>
						<option value="08" label="Ağustos"></option>
						<option value="09" label="Eylül"></option>
						<option value="10" label="Ekim"></option>
						<option value="11" label="Kasım"></option>
						<option value="12" label="Aralık"></option>
				</select> --></td>
							</tr>

							<tr>
								<td style="border: none; height: 125px;" colspan="6"></td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="5%">Gün</th>
								<th>Gidilen yer</th>
								<th>Gidiş Saati</th>
								<th>Geliş Saati</th>
								<th>Araç Plakası</th>
								<th width="40%">Yapılan İşin Özeti</th>
							</tr>
							<c:forEach items="${aracCikisListesi}" var="cikis">
								<c:set var="string1" value="${cikis.tarih}" />
								<c:set var="string2" value="${fn:split(string1, '-')}" />
								<c:set var="lastString" value="${string2[fn:length(string2)-1]}" />
								<tr>
									<td><c:out value="${lastString }"></c:out></td>
									<td>${cikis.ilce.isim}-${cikis.mahalle.isim}</td>
									<td>${cikis.cikisSaati}</td>
									<td>${cikis.girisSaati}</td>
									<c:if test="${cikis.ozelPlaka ne ''}">
										<td>${cikis.ozelPlaka}</td>
									</c:if>

									<c:if test="${cikis.resmiPlaka ne ''}"> 
										<td>${cikis.resmiPlaka}</td>
									</c:if>
									<td>${cikis.aciklama}</td>
								<tr>
							</c:forEach>
							<c:set var="comment_value" value="${aracCikisListesi}"></c:set>
							<c:forEach begin="1" end="${22-fn:length(comment_value) }">
								<tr>
									<td style="height: 15px;"></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<td style="border: none; height: 125px;" colspan="6"></td>
							</tr>
							<tr>
								<td colspan="3">Arazi Gün Sayısı <b>
										${fn:length(comment_value)}</b> gün
								</td>
								<td colspan="3" align="right">Tasdik Olunur</td>
							</tr>
							<tr>
								<td colspan="4">2018/${aylar} Döneminde Tarafımdan Yapılan
									Arazi Çalışmalarına Ait Rapordur</td>
								<td align="right" colspan="2">../.../2018</td>
							</tr>
							<tr>
								<td colspan="6" style="height: 20px;"></td>
							</tr>
							<tr>
								<td align="center" colspan="2"><b>${kullanici.adi}</b></td>
								<td></td>
								<td></td>
								<td></td>
								<c:if
									test="${kullanici.birim eq 'AraziToplulaştırmaveTarımsalAltyapı'}">
									<td align="center"><b>Metin Toprak</b></td>
								</c:if>

								<c:if
									test="${kullanici.birim eq 'BitkiselÜretimveBitkiSağlığı'}">
									<td align="center"><b>Yusuf İzzet Işık</b></td>
								</c:if>
								<c:if test="${kullanici.birim eq 'BalıkçılıkveSuÜrünleri'}">
									<td align="center"><b>Gürdal Ersoy</b></td>
								</c:if>

								<c:if test="${kullanici.birim eq 'ÇayırMeraveYemBitkileri'}">
									<td align="center"><b>Mehmet Türker</b></td>
								</c:if>


								<c:if test="${kullanici.birim eq 'GıdaveYem'}">
									<td align="center"><b>Semahattin Toktaş</b></td>
								</c:if>


								<c:if
									test="${kullanici.birim eq 'HayvanSağlığıveYetiştiriciliği'}">
									<td align="center"><b>Sonay Aslan</b></td>
								</c:if>



								<c:if test="${kullanici.birim eq 'İdariveMaliİşler'}">
									<td align="center"><b>Kemalettin Manga</b></td>
								</c:if>



								<c:if test="${kullanici.birim eq 'KırsalKalkınmaveÖrgütlenme'}">
									<td align="center"><b>Nilay Artok</b></td>
								</c:if>

								<c:if
									test="${kullanici.birim eq 'KoordinasyonveTarımsalVeriler'}">
									<td align="center"><b>Birsen Ergün</b></td>
								</c:if>
							</tr>
							<tr>
								<td align="center" colspan="2"><b>${kullanici.unvan}</b></td>
								<td></td>
								<td></td>
								<td></td>
								<td align="center"><b> Şube Müdürü</b></td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
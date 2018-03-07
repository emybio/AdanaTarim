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
<title>${title }</title>


<style type="text/css">
/* .table-fixed tbody {
	height: 75px;
}

.table-fixed {
	overflow-y: auto;
	display: block;
	height: 250px;
	width: 100%;
}
 */
.balik td {
	text-decoration: bold;
}
</style>
<script type="text/javascript">
	jq(document).ready(function() {

		/* 		jq('.money').mask("##0.00", {
			 reverse : true
			 }); */

	});
</script>
</head>
<body>

	<div class="container-fluid">
		<div id="container" class="col-sm-6">
			<h3>Ekonomik Yatırımlar</h3>

			<form:form action="ekle" method="post" commandName="ekonomikYatirim">
				<form:hidden path="id" />
				<table
					class="table table-sm table-striped bg-danger  table-responsive "
					style="text-align: center; width: 50%;">
					<tr align="center" style="text-align: center">
						<td colspan="11">EKONOMİK YATIRIMLAR</td>
					</tr>
					<tr>
						<td>İLÇE</td>
						<td>YATIRIM KONUSU</td>
						<td>ETAP NO</td>
						<td>YATIRIMCI ADI</td>
						<td>PROJE ADI</td>
						<td>PROJE BEDELİ</td>
						<td>HİBE TUTARI</td>
						<td>KAPASİTE</td>
						<td>BİRİM</td>
						<td>İSTİHDAM</td>
						<td>DURUM</td>
					</tr>
					<tr>
						<td><form:select path="ilce.id">
								<form:options items="${ ilceListesi}" itemLabel="isim"
									itemValue="id" />
							</form:select></td>
						<td><form:select path="kategori.id">
								<form:options items="${ kategoriListesi}"
									itemLabel="kategoriAdi" itemValue="id" />
							</form:select></td>
						<td><form:select path="etapNo">
								<form:option value="2">2. Etap</form:option>
								<form:option value="3">3. Etap</form:option>
								<form:option value="4">4. Etap</form:option>
								<form:option value="5">5. Etap</form:option>
								<form:option value="6">6. Etap</form:option>
								<form:option value="7">7. Etap</form:option>
								<form:option value="8">8. Etap</form:option>
								<form:option value="9">9. Etap</form:option>
								<form:option value="10">10. Etap</form:option>
								<form:option value="11">11. Etap</form:option>
								<form:option value="12">12. Etap</form:option>
							</form:select></td>
						<td><form:input path="yatirimciAdi" /> <%-- <form:select path="yatirimciSayisi">
								<form:option value="1">1</form:option>
								<form:option value="2">2</form:option>
								<form:option value="3">3</form:option>
								<form:option value="4">4</form:option>
								<form:option value="5">5</form:option>
								<form:option value="6">6</form:option>
								<form:option value="7">7</form:option>
								<form:option value="8">8</form:option>
								<form:option value="9">9</form:option>
								<form:option value="10">10</form:option>
								<form:option value="11">11</form:option>
								<form:option value="12">12</form:option>
								<form:option value="13">13</form:option>
								<form:option value="14">14</form:option>
								<form:option value="15">15</form:option>
								<form:option value="16">16</form:option>
								<form:option value="17">17</form:option>
								<form:option value="18">18</form:option>
								<form:option value="19">19</form:option>
								<form:option value="20">20</form:option>
							</form:select> --%></td>
						<td><form:input path="projeAdi" /></td>
						<td><form:input path="projeBedeli" class="money" /></td>
						<td><form:input path="hibeTutari" class="money" /></td>
						<td><form:input path="kapasite" /></td>
						<td><form:select path="kapasiteBirim">

								<form:option value="0">Seçiniz</form:option>
								<form:option value="lt">Litre</form:option>
								<form:option value="da">Dekar</form:option>
								<form:option value="burukbas">Büyük Baş</form:option>
								<form:option value="kucukbas">Küçük Baş</form:option>
								<form:option value="ton">Ton</form:option>
								<form:option value="adet/yıl">Adet/Yıl</form:option>
								<form:option value="kw/h">kw/h</form:option>
							</form:select></td>
						<td><form:input path="istihdam" /></td>
						<td><form:select path="duru">

								<form:option value="false">Devam Ediyor</form:option>
								<form:option value="true">Tamamlandı</form:option>

							</form:select></td>
					</tr>
					<tr>
						<c:if test="${tusYazisi == 'Kaydet'}">
							<td colspan="11"><input id="kaydetBtn" type="submit"
								class="btn btn-info  btn-md pull-right" value="${tusYazisi }"></td>
						</c:if>
						<c:if test="${tusYazisi == 'Guncelle'}">
							<td colspan="11"><input type="submit"
								class="btn btn-info  btn-md pull-right" value="Güncelle">
								<input type="button" class="btn btn-danger btn-md pull-right"
								value="Vazgeç"
								onclick="javascript:location.href='./ekonomikYatirimVazgec'">
							</td>
						</c:if>
					</tr>
				</table>
				<table class="table table-sm table-striped bg-info table-fixed "
					style="text-align: center; width: 100%;">
					<c:set var="list" value="${tumEkonomikYatirimListesi}" />
					<c:set var="listSize" value="${fn:length(list)}" />
					<!-- 
				<tr align="center" style="text-align: center; font: bold">
					<td colspan="12"><H5>YATIRIM BİLGİLERİ</H5></td>
				</tr> -->
					<tr>
						<td colspan="11" align="left"><b>${listSize}&nbsp;adet&nbsp;kayıt</b></td>
					</tr>

					<tr class="baslik">

						<td align="center">İLÇE</td>
						<td align="center">YATIRIM KONUSU</td>
						<td align="center">ETAP NO</td>
						<td align="center">YATIRIMCI ADI</td>
						<td align="center">PROJE ADI</td>
						<td align="center">PROJE BEDELİ</td>
						<td align="center">HİBE TUTARI</td>
						<td align="center">KAPASİTE</td>
						<td align="center">İSTİHDAM</td>
						<td align="center">DURUM</td>
					</tr>
					<tbody class="govde">

						<c:forEach items="${tumEkonomikYatirimListesi }" var="yatirim"
							varStatus="sira">


							<tr id="siraNo${sira.count }">
								<td align="center">${yatirim.ilce.isim}</td>
								<td align="center">${yatirim.kategori.kategoriAdi}</td>
								<td align="center">${yatirim.etapNo}</td>
								<td align="center">${yatirim.yatirimciAdi}</td>
								<td align="center">${yatirim.projeAdi}</td>
								<td align="center"><fmt:formatNumber pattern="#,##0.00"
										type="currency" value="${yatirim.projeBedeli}"
										var="projeBedeli"></fmt:formatNumber>${projeBedeli }</td>



								<td align="center"><fmt:formatNumber pattern="#,##0.00"
										type="currency" value="${yatirim.hibeTutari}"
										var="projeBedeli"></fmt:formatNumber>${projeBedeli }</td>
								<td align="center">${yatirim.kapasite}&nbsp;${yatirim.kapasiteBirim}</td>
								<td align="center">${yatirim.istihdam}</td>
								<td align="center">${yatirim.durum}</td>
								<td><a
									href="${pageContext.request.contextPath }/kirsal-kalkinma/ekonomikYatirimSil?id=${yatirim.id}"
									onclick="javascript:return confirm('${yatirim.etapNo}. etap ${yatirim.yatirimciAdi} isimli kaydı : \n Silmek İstediğinize Emin misiniz?');"
									class="btn btn-danger btn-sm">Sil</a></td>
								<td><a
									href="${pageContext.request.contextPath }/kirsal-kalkinma/ekonomikYatirimGuncelle/${yatirim.id}"
									class="btn btn-primary btn-sm">Güncelle</a></td>
							</tr>


						</c:forEach>



						<tr>
							<td colspan="5" align="right">GENEL TOPLAM:</td>

							<td><fmt:formatNumber pattern="#,##0.00" type="currency"
									value="${tumEkonomikYatirimListesi.stream().map(yatirim -> yatirim.projeBedeli).sum()}"
									var="projeBedeli"></fmt:formatNumber>${projeBedeli }</td>
							<td><fmt:formatNumber pattern="#,##0.00 TL" type="currency"
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
			</form:form>
		</div>
	</div>
</body>
</html>
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
body {
	margin-bottom: 0px;
	margin-top: 0px;
	margin-right: 2px;
	margin-left: 2px;
	padding-bottom: 0px;
	padding-top: 0px;
	padding-right: 0px;
	padding-left: 0px;
}

.foot {
	display: none;
}

.navbar {
	display: none;
}
/*
table {  
	margin-left: 0px; margin-right : 0px;
	padding: 0px;
	margin-right: 0px;
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

table thead {
	background-color: rgba(255, 255, 255, 0.6);
	margin-bottom: 10px;
}

table tbody tr td {
	border: 1px solid black;
}
table thead {
	background-color: rgba(255, 255, 255, 0.6);
	border: 1px solid black;
}*/
</style>

</head>

<body>


	<div class="container" style="width: 99%; background-color: white;">


		<div class="row"
			style="background-color: rgba(255, 255, 255, 0.3); margin-bottom: 15px; margin-top: 15px;">
			<table width="100%" style="margin-top: 5px;">
				<thead>
					<tr>
						<td align="center" style="border: solid 1px black;"><img
							style="height: 90%;"
							src="${pageContext.request.contextPath}/assets/yeni_logo.png"></td>


						<td align="center" colspan="3"
							style="border: solid 1px black; font-family: 'Times New Roman', Times, serif; font-size: 12px;"><b>ADANA
								<br>İL TARIM VE ORMAN MÜDÜRLÜĞÜ<br> 6245 SAYILI KANUN
								KAPSAMINDAKİ <br>GÖREVLERE AİT AYLIK PUANTAJ FORMU <br>(Arazi
								ve Seyyar Görev Tazminatı)
						</b></td>
						<td colspan="2" style="border: solid 1px black;">
							<table width="100%" height="100%">
								<tr>
									<td>Doküman Kodu</td>
									<td style="border-left-style: solid; border-left-width: 1px;">İLM.01.İKS.FRM_74</td>
								</tr>
								<tr>
									<td style="border: solid 1px black; border-left-style: none;">Revizyon
										No</td>
									<td style="border: solid 1px black; border-left-style: none;">01</td>
								</tr>
								<tr>
									<td style="border: solid 1px black; border-left-style: none;">Revizyon
										Tarihi</td>
									<td style="border: solid 1px black; border-left-style: none;">01.08.2018</td>
								</tr>
								<tr>
									<td style="border: solid 1px black; border-left-style: none;">Yürürlük
										Tarihi</td>
									<td style="border: solid 1px black; border-left-style: none;">05.02.2018</td>
								</tr>
								<tr>
									<td>Sayfa No</td>
									<td style="border-left-style: solid; border-left-width: 1px;">1/1</td>
								</tr>

							</table>
				</thead>
			</table>
		</div>

		<div class="row" style="margin-bottom: 0px;">
			<table width="100%" border="1">

				<tr>

					<%-- <td colspan="6"><b>ADI
							SOYDI&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b>
						&nbsp;${kullanici.adi }<br> <b>ÜNVANI&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
					</b>&nbsp;${kullanici.unvan }<br> <b>ŞUBE
							ADI&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b>
						&nbsp;${kullanici.birim}<br> <b>TARİH(AY/YIL)&nbsp;: </b>&nbsp;.../2019</td> --%>

					<td colspan="6"
						style="padding-left: 20px; font-weight: bold; font-family: 'Times New Roman', Times, serif; font-size: 12px;">
						<b> ADI
							SOYDI&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
					</b> &nbsp;${kullanici.adi }<br> <b>ÜNVANI&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
					</b>&nbsp;${kullanici.unvan }<br> <b>ŞUBE
							ADI&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b>
						&nbsp;${kullanici.birim}<br> <b>TARİH(AY/YIL)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
					</b>&nbsp;.../2019
					</td>
				</tr>
				<tbody style="text-align: center;">
					<tr>
						<th style="text-align: center; height: 0.90cm;">Günler</th>
						<th style="text-align: center;">Göreve Gidilen Yer</th>
						<th style="text-align: center;">Görev Başlama Saati</th>
						<th style="text-align: center;">Görev Bitiş Saati</th>
						<th style="text-align: center;">Görev Aracı Plakası</th>
						<th style="text-align: center;">Yapılan İşin Özeti</th>
					</tr>
					<c:forEach items="${aracCikisListesi}" var="cikis">
						<c:set var="string1" value="${cikis.tarih}" />
						<c:set var="string2" value="${fn:split(string1, '-')}" />
						<c:set var="lastString" value="${string2[fn:length(string2)-1]}" />
						<tr>
							<td style="height: 0.54cm;"><c:out value="${lastString }"></c:out></td>
							<td>${cikis.ilce.isim}-${cikis.mahalle.isim}</td>
							<td>${cikis.cikisSaati}</td>
							<td>${cikis.girisSaati}</td>
							<c:if test="${empty cikis.resmiPlaka}">
								<td>${cikis.ozelPlaka}</td>
							</c:if>
							<c:if test="${!empty cikis.resmiPlaka }">
								<td>${cikis.resmiPlaka}</td>
							</c:if>
							<td>${cikis.aciklama}</td>
						<tr>
					</c:forEach>
					<c:set var="comment_value" value="${aracCikisListesi}"></c:set>
					<c:forEach begin="1" end="${22-fn:length(comment_value) }">
						<tr>
							<td style="height: 0.54cm;"></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<table width="100%" style="margin-left:;">
				<tfoot>
					<tr>
						<td
							style="padding-left: 5px; border: none; font-family: 'Times New Roman', Times, serif; font-size: 13px;"><b>Toplam
								Arazi Görevi Gün Sayısı: ${fn:length(comment_value)} gün</b></td>
					</tr>
					<tr>
						<td align="justify"
							style="padding-top: 15px; padding-left: 5px; font-family: 'Times New Roman', Times, serif; font-size: 13px;"><b>Yukarıda
								gösterilen puantajdaki bilgilerin doğruluğunu, aksi bir durumun
								söz konusu olduğunda ödemenin <br>tarafımızdan yasal
								faiziyle birlikte iade edileceğini beyan ve taahhüt ederim.
						</b></td>

					</tr>
					<tr>
						<td colspan="6" style="height: 20px;"></td>
					</tr>
			</table>
			<table width="100%" style="margin-left:;">
				<tfoot>
					<tr>
						<td align="left" style="padding-left: 10%;"><b>${kullanici.adi}</b></td>
						<c:if
							test="${kullanici.birim eq 'Arazi Toplulaştırma ve Tarımsal Altyapı'}">
							<td align="right" style="padding-right: 10%;"><b>Metin
									Toprak</b></td>
						</c:if>
						<c:if
							test="${kullanici.birim eq 'Bitkisel Üretim ve Bitki Sağlığı'}">
							<td align="right"><b>Yusuf İzzet Işık</b></td>
						</c:if>
						<c:if test="${kullanici.birim eq 'Balıkçılık ve Su Ürünleri'}">
							<td align="center"><b>Gürdal Ersoy</b></td>
						</c:if>
						<c:if test="${kullanici.birim eq 'Çayır Mera ve Yem Bitkileri'}">
							<td align="right"><b>Mehmet Türker</b></td>
						</c:if>
						<c:if test="${kullanici.birim eq 'Gıda ve Yem'}">
							<td align="right"><b>Semahattin Toktaş</b></td>
						</c:if>
						<c:if
							test="${kullanici.birim eq 'Hayvan Sağlığı ve Yetiştiriciliği'}">
							<td align="right"><b>Sonay Aslan</b></td>
						</c:if>
						<c:if test="${kullanici.birim eq 'İdari ve Mali İşler'}">
							<td align="right"><b>Kemalettin Manga</b></td>
						</c:if>
						<c:if test="${kullanici.birim eq 'Kırsal Kalkınma ve Örgütlenme'}">
							<td align="right"><b>Nilay Artok</b></td>
						</c:if>
						<c:if
							test="${kullanici.birim eq 'Koordinasyon ve Tarımsal Veriler'}">
							<td align="right" style="padding-right: 50px;"><b>Birsen
									Ergün</b></td>
						</c:if>
					</tr>
					<tr>
						<td align="left" style="padding-left: 11%;"><b>${kullanici.unvan}</b></td>
						<td align="right" style="padding-right: 10%;"><b> Şube
								Müdürü</b></td>
					</tr>
					<tr>
						<td align="left" style="padding-top: 15px; padding-left: 10%;"><b>..../..../2019</b></td>
						<td align="right" style="padding-top: 15px; padding-right: 10%;"><b>..../..../2019</b></td>
					</tr>

					<tr>
						<td align="left" style="padding-top: 15px; padding-left: 12%;"><b>İmza</b></td>
						<td align="right" style="padding-top: 15px; padding-right: 12%;"><b>İmza</b></td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
<div>assdsdsa</div>
</body>
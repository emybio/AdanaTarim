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
	margin: 0 0 0 0;
}

textarea {
	width: 100%;
	height: 40vh;
	resize: none;
	-webkit-box-sizing: border-box; /* <=iOS4, <= Android  2.3 */
	-moz-box-sizing: border-box; /* FF1+ */
	box-sizing: border-box;
	resize: none; /* Chrome, IE8, Opera, Safari 5.1*/
}
/* Basic table styling */
table {
	border-collapse: collapse;
	background-color: rgba(255, 255, 255, 0.6);
}

thead tr td {
	border: 1px solid black;
}

br {
	display: block;
	margin-top: 10px;
	line-height: 22px;
}

@PAGE {
	margin: 0cm;
}
</style>
</head>
<body>
	<div class="container-fluid" id="yazdir">
		<div class="container">
			<div id="container" class="col-md-10">
				<table align="center" width="100%">
					<thead>
						<tr>
							<td align="center" style="padding: 20px; width: 15%;"><img
								src="${pageContext.request.contextPath}/assets/gthbLogo.png"
								width="75%"></td>
							<td
								style="text-align: center; font-size: 20px; font-family: Times New Roman; width: 100%; padding: 15px;"
								colspan="4">T.C. <br>ADANA VALİLİĞİ <br> İL GIDA
								TARIM VE HAYVANCILIK MÜDÜRLÜĞÜ
							</td>
						</tr>
					</thead>
					<c:forEach items="${aracCikisListesi}" var="cikis">
						<tr style="">

							<td colspan="3" align="right"
								style="padding-top: 25px; margin-right: 85px; font-family: Times New Roman; font-size: 17px; padding-bottom: 15px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>GÖREV
									DÖNÜŞ RAPORU</b>

							</td>
							<td colspan="2" align="right"
								style="font-family: times new roman; font-size: 15px;">Tarih:
								<fmt:parseDate value="${cikis.tarih}" pattern="yyyy-mm-dd"
									var="tarih" /> <fmt:formatDate value="${tarih}"
									pattern="dd/mm/YYYY" dateStyle="full" />
							</td>
						</tr>
						<tr>
							<td colspan="5" align="justify"
								style="font-family: times new roman; font-size: 15px; border: 1px solid #000; text-align: justify;">
								<!-- <textarea
									style="text-align: justify; font-family: Times New Roman; font-size: 20px;"> -->
								<br> &nbsp;&nbsp;&nbsp;&nbsp;Saat ${cikis.cikisSaati} 'da <c:if
									test="${!empty cikis.resmiPlaka }"></c:if> ${cikis.resmiPlaka}
								<c:if test="${!empty cikis.ozelPlaka }"></c:if>
								${cikis.ozelPlaka} Plakalı araçla ${cikis.ilce.isim} İlçesi
								${cikis.mahalle.isim} Mahallesi'ne gidildi. İlgili arazide
								${cikis.aciklama} yapıldı. <br> <br> <br> <br>
								<br> <br> <br> <br> <br>
								&nbsp;&nbsp;&nbsp;&nbsp;Görev tamamlanarak Saat
								${cikis.girisSaati} 'da <c:set var="kullanici"
									value="${cikis.kullaniciList}" /> <c:if
									test="${kullanici[0].birim eq 'HayvanSağlığıveYetiştiriciliği' }">
Hayvan Sağlığı ve Yetiştiriciliği
								</c:if> <c:if
									test="${kullanici[0].birim eq 'AraziToplulaştırmaveTarımsalAltyapı' }">

Arazi Toplulaştırma ve Tarımsal Altyapı
								</c:if> <c:if
									test="${kullanici[0].birim eq 'BitkiselÜretimveBitkiSağlığı'}">
Bitkisel Üretim ve Bitki Sağlığı
								</c:if> <c:if test="${kullanici[0].birim eq 'BalıkçılıkveSuÜrünleri'}">
Balıkçılık ve Su Ürünleri			
								</c:if> <c:if test="${kullanici[0].birim eq 'ÇayırMeraveYemBitkileri'}">
Çayır Mera ve Yem Bitkileri							
								</c:if> <c:if test="${kullanici[0].birim eq 'GıdaveYem'}">
Gıda ve Yem								
								</c:if> <c:if test="${kullanici[0].birim eq 'İdariveMaliİşler'}">
İdari ve Mali İşler								
								</c:if> <c:if
									test="${kullanici[0].birim eq 'KırsalKakinmaveÖrgütlenme'}">
Kırsal Kalkınma ve Örgütlenme									
								</c:if> <c:if
									test="${kullanici[0].birim eq 'KoordinasyonveTarımsalVeriler'}">
Koordinasyon ve Tarımsal Veriler									
								</c:if> Şube Müdürlüğü'ne dönülmüştür. <br> <br> <br>&nbsp;&nbsp;&nbsp;&nbsp;Bilgilerinize
								arz ederim. <!--  </textarea> --> <br> <br> <br>
								<br> <br>
							</td>
						</tr>
						<tr>
							<c:set var="list" value="${cikis.kullaniciList}" />
							<c:set var="listSize" value="${fn:length(list)}" />

							<!--Çıkış Listesinde 5 Kişi Varsa  -->
							<c:if test="${listSize ==5 }">
								<c:set var="kullanici" value="${cikis.kullaniciList}" />
								<td align="center"
									style="padding-top: 40px; font-family: Times New Roman; font-size: 15px;">

									${kullanici[0].adi }<br> ${kullanici[0].unvan }
								</td>
								<td align="center"
									style="padding-top: 40px; font-family: Times New Roman; font-size: 15px;">

									${kullanici[1].adi }<br> ${kullanici[1].unvan }
								</td>
								<td align="center"
									
									style="padding-top: 40px; font-family: Times New Roman; font-size: 15px;">

									${kullanici[2].adi }<br> ${kullanici[2].unvan }
								</td>
								<td align="center"
									style="padding-top: 40px; font-family: Times New Roman; font-size: 15px;">

									${kullanici[3].adi }<br> ${kullanici[3].unvan }
								</td>


								<td align="center"
									style="padding-top: 40px; font-family: Times New Roman; font-size: 15px; width: 28%">

									${kullanici[4].adi }<br> ${kullanici[4].unvan }
								</td>
							</c:if>

							<!--Çıkış Listesinde 4 Kişi Varsa  -->
							<c:if test="${listSize ==4 }">

								<%-- <c:forEach items="${cikis.kullaniciList}" var="kullanici"
									varStatus="index">
									<td align="center"
										style="padding-top: 40px; font-family: Times New Roman; font-size: 15px;">${kullanici.adi }<br>
										${kullanici.unvan }
									</td>
								</c:forEach> --%>

								<c:set var="kullanici" value="${cikis.kullaniciList}" />
								<td colspan="2" align="center" width="25%"
									style="padding-top: 40px; font-family: Times New Roman; font-size: 15px;">

									${kullanici[0].adi }<br> ${kullanici[0].unvan }
								</td>
								<td align="center" width="25%"
									style="padding-top: 40px; font-family: Times New Roman; font-size: 15px;">

									${kullanici[1].adi }<br> ${kullanici[1].unvan }
								</td>
								<td align="center" width="25%"
									style="padding-top: 40px; font-family: Times New Roman; font-size: 15px; padding-right: 50px">

									${kullanici[2].adi }<br> ${kullanici[2].unvan }
								</td>
								<td colspan="2" align="center" width="25%"
									style="padding-top: 40px; font-family: Times New Roman; font-size: 15px;">

									${kullanici[3].adi }<br> ${kullanici[3].unvan }
								</td>


							</c:if>




							<!--Çıkış Listesinde 3 Kişi Varsa  -->
							<c:if test="${listSize ==3 }">
								<c:set var="kullanici" value="${cikis.kullaniciList}" />
								<td colspan="2" align="center" width="33%"
									style="padding-top: 40px; font-family: Times New Roman; font-size: 15px; padding-left: 100px">

									${kullanici[0].adi }<br> ${kullanici[0].unvan }
								</td>
								<td align="center" width="34%"
									style="padding-top: 40px; font-family: Times New Roman; font-size: 15px;">

									${kullanici[1].adi }<br> ${kullanici[1].unvan }
								</td>
								<td colspan="2" align="center" width="33%"
									style="padding-top: 40px; font-family: Times New Roman; font-size: 15px; padding-right: 100px">

									${kullanici[2].adi }<br> ${kullanici[2].unvan }
								</td>
							</c:if>
							<!--Çıkış Listesinde 2 Kişi Varsa  -->
							<c:if test="${listSize ==2 }">
								<c:set var="kullanici" value="${cikis.kullaniciList}" />
								<td></td>
								<td colspan="2" align="left" width="20%"
									style="padding-top: 40px; font-family: Times New Roman; font-size: 20px; ">

									${kullanici[0].adi }<br> ${kullanici[0].unvan }
								</td>
								
								<td colspan="2" align="center" width="20%"
									style="padding-top: 40px; font-family: Times New Roman; font-size: 20px; padding-right: 100px">

									${kullanici[1].adi }<br> ${kullanici[1].unvan }
								</td>
							</c:if>

							<!--Çıkış Listesinde 1 Kişi Varsa  -->
							<c:if test="${listSize ==1 }">
								<c:set var="kullanici" value="${cikis.kullaniciList}" />
								<td colspan="5" align="center" width="20%"
									style="padding-top: 40px; font-family: Times New Roman; font-size: 20px; padding-left: 50px">

									${kullanici[0].adi }<br> ${kullanici[0].unvan }
								</td>
							</c:if>
						</tr>
						<tr>
							<td colspan="5"
								style="text-align: CENTER; font-family: Times New Roman; font-size: 20px; padding-top: 100px;">
								GÖRÜLDÜ</td>
						</tr>
						<tr>
							<td colspan="5"
								style="text-align: CENTER; font-family: Times New Roman; font-size: 20px;"><fmt:formatDate
									value="${tarih}" pattern="dd/mm/YYYY" dateStyle="full" /></td>
						</tr>
						<tr>
							<c:set var="kullanici" value="${cikis.kullaniciList}" />
							<c:if
								test="${kullanici[0].birim eq 'HayvanSağlığıveYetiştiriciliği' }">
								<td colspan="5"
									style="text-align: CENTER; font-family: Times New Roman; font-size: 20px; padding-top: 10px;">
									Sonay ASLAN</td>
							</c:if>
							<c:if
								test="${kullanici[0].birim eq 'AraziToplulaştırmaveTarımsalAltyapı' }">
								<td colspan="5"
									style="text-align: CENTER; font-family: Times New Roman; font-size: 20px; padding-top: 10px;">
									Metin TOPRAK</td>
							</c:if>
							<c:if
								test="${kullanici[0].birim eq 'BitkiselÜretimveBitkiSağlığı'}">
								<td colspan="5"
									style="text-align: CENTER; font-family: Times New Roman; font-size: 20px; padding-top: 10px;">Yusuf
									İzzet Işık</td>
							</c:if>
							<c:if test="${kullanici[0].birim eq 'BalıkçılıkveSuÜrünleri'}">
								<td colspan="5"
									style="text-align: CENTER; font-family: Times New Roman; font-size: 20px; padding-top: 10px;">Gürdal
									Ersoy</td>
							</c:if>

							<c:if test="${kullanici[0].birim eq 'ÇayırMeraveYemBitkileri'}">
								<td colspan="5"
									style="text-align: CENTER; font-family: Times New Roman; font-size: 20px; padding-top: 10px;">Mehmet
									Türker</td>
							</c:if>


							<c:if test="${kullanici[0].birim eq 'GıdaveYem'}">
								<td colspan="5"
									style="text-align: CENTER; font-family: Times New Roman; font-size: 20px; padding-top: 10px;">Semahattin
									Toktaş</td>
							</c:if>


							<c:if test="${kullanici[0].birim eq 'İdariveMaliİşler'}">
								<td colspan="5"
									style="text-align: CENTER; font-family: Times New Roman; font-size: 20px; padding-top: 10px;">Kemalettin
									Manga</td>
							</c:if>
							<c:if
								test="${kullanici[0].birim eq 'KırsalKakinmaveÖrgütlenme'}">
								<td colspan="5"
									style="text-align: CENTER; font-family: Times New Roman; font-size: 20px; padding-top: 10px;">Nilay
									Artok</td>
							</c:if>
							<c:if
								test="${kullanici[0].birim eq 'KoordinasyonveTarımsalVeriler'}">
								<td colspan="5"
									style="text-align: CENTER; font-family: Times New Roman; font-size: 20px; padding-top: 10px;">Birsen
									Ergün</td>
							</c:if>
						</tr>
						<tr>
							<td colspan="5"
								style="text-align: CENTER; font-family: Times New Roman; font-size: 20px;">
								Şube Müdürü</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
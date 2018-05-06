<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style>
.pic_ina {
	height: 90px;
	width: 30%;
	float: left;
}

.con_ina {
	height: auto;
	width: 67%;
	margin-left: 3%;
	float: left;
}

.btn_inr {
	float: right;
	margine-top: 25px;
}

.btn_inl {
	float: left;
	margine-top: 25px;
}

.btn_inl_padl {
	margin-left: 25px;
}
</style>

</head>
<body>
	<br>
	<div class="container">


		<div class="row ">

			<div class="col-lg-8 col-lg-offset-2 ">
				<hr>
				<c:forEach items="${kooperatif}" var="kooperatif">
					<div class="alert alert-warning "
						style="border-top-left-radius: 10em; border-bottom-right-radius: 10em;">

						<div class="panel-body">
							<h2>${kooperatif.kooperatifAdi }</h2>
							<%-- <img src="<c:url value="/assets/images/personelimage/avatar"/>"
								class="pic_ina " /> --%>
							<div class="con_ina">
								<b>Kooperatif Bşk: <%-- <fmt:parseDate
										value="${kooperatif.kurulusYili } " pattern="dd-mm-yyyy"
										var="tarih" /> <fmt:formatDate value="${tarih}"
										pattern="YYYY" dateStyle="short" /> --%>
									${kooperatif.koopBask}
								</b> <br> <b>Kuruluş Yılı: <%-- <fmt:parseDate
										value="${kooperatif.kurulusYili } " pattern="dd-mm-yyyy"
										var="tarih" /> <fmt:formatDate value="${tarih}"
										pattern="YYYY" dateStyle="short" /> --%>
									${kooperatif.kurulusYili }
								</b> <br> <b> Ortak Sayısı: ${kooperatif.ortakSayisi}</b> <br>
								<b> İntibak Yılı: ${kooperatif.intibakYili == '00.00.0000' ? 'Yok':kooperatif.intibakYili}</b>
								<br> <b> Proje: ${kooperatif.uygulamaProjesi } </b> <br>
								<c:if
									test="${!empty kooperatif.intibakYili or kooperatif.intibakYili != '' } ">
									<b> intibak Yılı: <%-- <fmt:parseDate
												value="${kooperatif.intibakYili } " pattern="dd-mm-yyyy"
												var="tarih" /> <fmt:formatDate value="${tarih}"
												pattern="dd.MM.YYYY" dateStyle="short" /> --%>${kooperatif.intibakYili }
									</b>
								</c:if>


								<b> Son Gn Krl Tar: ${kooperatif.sonGenelKurulTarihi }<%--  <fmt:parseDate
											value=" ${kooperatif.sonGenelKurulTarihi }"
											pattern="yyyy-mm-dd" var="tarih" /> <fmt:formatDate
											value="${tarih}" pattern="dd-mm-YYYY" dateStyle="short" /> --%>
								</b><br> <b> Koop Durumu: ${kooperatif.durum}<%--  <fmt:parseDate
											value=" ${kooperatif.sonGenelKurulTarihi }"
											pattern="yyyy-mm-dd" var="tarih" /> <fmt:formatDate
											value="${tarih}" pattern="dd-mm-YYYY" dateStyle="short" /> --%>
								</b> <br>
							</div>
						</div>



						<span id="goster${kooperatif.id}"><button type="button"
								class="btn btn-warning btn-xs btn-lg btn_inl"
								data-toggle="modal" data-target="#myModal${kooperatif.id}">Denetim
								Kurulu</button></span>
						<!-- Modal -->
						<div class="modal fade" id="myModal${kooperatif.id}" role="dialog">
							<div class="modal-dialog">

								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">${kooperatif.kooperatifAdi}
											Kooperatifi Denetim Kurulu Listesi</h4>
									</div>
									<div class="modal-body">

										<c:forEach items="${kooperatif.denetimKuruluList}"
											var="denetim" varStatus="index">
											<span class="personelGoster"></span>

											<p>${index.count}-${denetim}</p>
										</c:forEach>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Kapat</button>
									</div>
								</div>

							</div>
						</div>
						<span id="goster${kooperatif.id}"><button type="button"
								class="btn btn-primary btn-xs btn-lg btn_inl"
								data-toggle="modal" data-target="#myModal${kooperatif.id+1}">Yönetim
								Kurulu</button></span>
						<!-- Modal -->
						<div class="modal fade" id="myModal${kooperatif.id+1}"
							role="dialog">
							<div class="modal-dialog">

								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">${kooperatif.kooperatifAdi}
											Kooperatifi Yönetim Kurulu Listesi</h4>
									</div>
									<div class="modal-body">

										<c:forEach items="${kooperatif.yonetimKuruluList}"
											var="denetim1" varStatus="index">
											<span class="personelGoster"></span>

											<p>${index.count}-${denetim1}</p>
										</c:forEach>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Kapat</button>
									</div>
								</div>

							</div>
						</div>
						<span id="goster${kooperatif.id}"><button type="button"
								class="btn btn-info btn-xs btn-lg btn_inl" data-toggle="modal"
								data-target="#myModal${kooperatif.id+2}">İletişim
								Bilgileri</button></span>
						<!-- Modal -->
						<div class="modal fade" id="myModal${kooperatif.id+2}"
							role="dialog">
							<div class="modal-dialog">

								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">${kooperatif.kooperatifAdi}
											Kooperatifi İletişim Bilgileri</h4>
									</div>
									<div class="modal-body">
										<p>
											<b>ADRES :</b> ${kooperatif.adres}
										</p>
										<p>
											<b>TELEFON : </b>${kooperatif.telefon}</p>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Kapat</button>
									</div>
								</div>

							</div>
						</div>
						<a href="#" onclick="window.history.back();"
							class="btn btn-default btn-xs btn_inr btn_inl_padl"
							style="margin-right: 75px;"><span class="fa fa-reply">
								Geri </span> </a><a href="./kooperatifDuzenle/${kooperatif.id}"
							class="btn btn-warning btn-xs btn_inr "><span
							class="fa fa-edit"> Düzenle </span> </a> <a
							onclick="return confirm('Silmek İstediğinize Emin misiniz?');"
							href="./kooperatifSil?id=${kooperatif.id}"
							class="btn btn-danger btn-xs btn_inr btn_inl_padl"><span
							class="fa fa-times"> Sil </span> </a>



					</div>
				</c:forEach>
			</div>
		</div>
	</div>

</body>
</html>
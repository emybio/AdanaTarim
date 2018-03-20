<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<meta http-equiv="Content-Type" content=" charset=UTF-8">

<script type="text/javascript">
	/* jq(document).ready(function() {

		jq(' li').click(function(e) {
			
			//alert("tıklandı");
			//e.preventDefault();
			jq(' li').removeClass('active');
			jq(this).addClass('active');
		});

	}) */
	jq(document).ready(
			function() {

				jq('.navbar .dropdown').hover(
						function() {
							jq(this).find('.dropdown-menu').first().stop(true,
									true).slideDown(200);
						},
						function() {
							jq(this).find('.dropdown-menu').first().stop(true,
									true).slideUp(200)
						});
			});
</script>
<nav class="navbar   navbar-fixed-top   navbar-light"
	style="background-color: #e3f2fd;">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				style="background-color: #e3f2fd;" data-toggle="collapse"
				data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar" style="background-color: black;"></span> <span
					class="icon-bar" style="background-color: black;"></span> <span
					class="icon-bar" style="background-color: black;"></span>
			</button>
			<div class="navbar-collapse collapse">
				<a class="navbar-brand" href="#"
					style="background-color: rgba(255, 255, 2550.5)"> <img
					src="<c:url value='/assets/gthbLogo.png'/>" class="user-image"
					width="30" height="25" />





				</a>
			</div>
		</div>
		<div id="navbar" class="navbar-collapse collapse">


			<ul class="nav navbar-nav navbar-right">

				<!--GEREKLİ GÖRÜLDÜĞÜNDE EKLEMEK İÇİN REMLENDİ  -->
				<!-- <li><a href="#">Dashboard</a></li>
				<li><a href="#">Settings</a></li>
				<li><a href="#">Profile</a></li>
				<li><a href="#">Help</a></li> -->

				<li class="active"><a
					href="${pageContext.request.contextPath}/anasayfa"> <span
						class="fa fa-home fa-fw" aria-hidden="true"> </span>&nbsp;Anasayfa
				</a></li>
				<li id="chart-types"
					class="dropdown sub-menu collapse ${fn:contains(pageContext.request.requestURI,'satis-cesitleri') ? 'in' : ''} "><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"><i
						class="fa fa-area-chart" aria-hidden="true"></i>&nbsp;<span>Arazi
							Toplulaştırma</span> <span class="caret"></span></a>
					<ul class="dropdown-menu ">
						<li><a
							href="${pageContext.request.contextPath}/satis-cesitleri/satis">Satış
								Ekle</a></li>

						<li><a
							href="${pageContext.request.contextPath}/raporlar/satisrapor">
								<span class="fa fa-file-pdf-o fa-fw" aria-hidden="true">
							</span>&nbsp; Rapor Görüntüle
						</a></li>
					</ul></li>




				<li id="chart-types"
					class="dropdown sub-menu collapse ${fn:contains(pageContext.request.requestURI,'satis-cesitleri') ? 'in' : ''} "><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"><i
						class="fa fa-industry" aria-hidden="true"></i>&nbsp;<span>Kırsal
							Kalkınma</span> <span class="caret"></span></a>
					<ul class="dropdown-menu ">
						<li><a
							href="${pageContext.request.contextPath}/kirsal-kalkinma/ekonomik-yatirimlar"><span
								class="fa fa-try"></span> Ekonomik Yatırımlar</a></li>

						<li><a
							href="${pageContext.request.contextPath}/kirsal-kalkinma/genc-ciftci">
								<span class="fa fa-female"></span> Genç Çiftçi <span
								class="fa fa-male"></span>
						</a></li>
						<li><a
							href="${pageContext.request.contextPath}/kirsal-kalkinma/ilce-listesi"><span
								class="fa fa-th" aria-hidden="true"> </span>&nbsp;Kooperatif /
								Birlik</a></li>

						<li><a
							href="${pageContext.request.contextPath}/kirsal-kalkinma/ekonomikYatirimRapor">
								<span class="fa fa-file-pdf-o" aria-hidden="true"> </span>&nbsp;
								Rapor Görüntüle
						</a></li>
					</ul></li>


				<li class="dropdown"><a href="#" data-toggle="dropdown"
					class="dropdown-toggle"> <i class="fa fa-car"
						aria-hidden="true"></i>&nbsp;<span>Arazi Çıkışları</span><span
						class="caret"></span></a>

					<ul class="dropdown-menu">
						<li><a
							href="${pageContext.request.contextPath}/arazi-cikislari/arac-islemleri">Çıkış
								İşlemleri</a></li>
						<li><a
							href="${pageContext.request.contextPath}/arazi-cikislari/arac-talep">Araç
								Talepler</a></li>
					</ul></li>
				<c:if test="${cookie.id.value eq 1  }">
					<li class="dropdown"><a href="#" data-toggle="dropdown"
						class="dropdown-toggle"> <i class="fa fa-dashboard"
							aria-hidden="true"></i>&nbsp;<span>Dashboard</span><span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/yonetim/sabitler">Köy/Mahalle/İlçe/İl
									Ekleme</a></li>

							<li><a
								href="${pageContext.request.contextPath}/kirsal-kalkinma/sabitler">Genç
									Çiftçi Kategori Ekleme</a></li>
							<li><a
								class="${fn:contains(pageContext.request.requestURI,'roller') ? 'in' : ''}"
								href="${pageContext.request.contextPath}/yonetim/rol-yonetimi">Kullanici
									Yetki Belirleme</a></li>
						</ul></li>
				</c:if>
				<c:if test="${empty cookie.id.value }">
					<li><a
						href="${pageContext.request.contextPath}/kullanici-islemleri/kullanici"><span
							class="fa fa-user"></span> Sign Up</a></li>

					<li><a href="${pageContext.request.contextPath}/anasayfa"><span
							class="fa fa-sign-in"></span> Login</a></li>
				</c:if>
				<c:if test="${!empty cookie.id.value }">
					<li class="dropdown"><a href="#" data-toggle="dropdown"
						class="dropdown-toggle"> <i class="fa fa-user"
							aria-hidden="true"></i>&nbsp;<span>${cookie.isim.value }</span><span
							class="caret"></span></a> <c:if test="${cookie.id.value eq 1 }">
							<ul class="dropdown-menu">
								<li><a
									href="${pageContext.request.contextPath}/kullanici-islemleri/kullanici">Kullanıcı
										Bilgileri</a></li>
								<li><a
									class="${fn:contains(pageContext.request.requestURI,'roller') ? 'in' : ''}"
									href="${pageContext.request.contextPath}/kullanici-islemleri/kullanici-profile"><span
										class="fa fa-xs fa-gear">Ayarlar</span></a></li>
							</ul>
						</c:if></li>
					<li><a href="${pageContext.request.contextPath}/cikis"><span
							class="fa fa-sign-out"></span> Logout</a></li>
				</c:if>
			</ul>
			<form class="navbar-form navbar-right"
				action="${pageContext.request.contextPath}/./getir">
				<input type="text" class="form-control" placeholder="Search...">
			</form>
			<!---------------------------------------------------------------------------------------------------------->
			<%-- <ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath}/anasayfa">
						<i
						class="fa fa-home sub-menu collapse ${fn:contains(pageContext.request.requestURI,'anasayfa') ? 'in' : ''}"
						aria-hidden="true"></i> <span>ANASAYFA</span>
				</a></li>
				<li role="separator" class="divider"></li>

				<li data-toggle="collapse" href="#chart-types" aria-expanded="false"
					aria-controls="chart-types"><a href="#"> <i
						class="fa fa-area-chart" aria-hidden="true"></i><span>SATIŞLAR</span>
				</a></li>

				<li>
					<ul id="chart-types"
						class="sub-menu collapse ${fn:contains(pageContext.request.requestURI,'satis-cesitleri') ? 'in' : ''}">
						<li><a
							href="${pageContext.request.contextPath}/satis-cesitleri/satis">Satış
								Yoluyla Devir (5403)</a></li>

					</ul>
				</li>
				<c:if test="${cookie.id.value==1 || cookie.id.value==9}">
					<!-- /chart types -->
					<li role="separator" class="divider"></li>
					<li data-toggle="collapse" href="#features" aria-expanded="false"
						aria-controls="features"><a href="#"> <i
							class="fa fa-indent" aria-hidden="true"></i><span>RAPORLAR</span>
					</a></li>
					<li>
						<!-- !HATIRLATMA! sayfa yenilendiğinde aktif olan link sidebar kısmında seçili olarak geliyor -->
						<ul id="features"
							class="sub-menu collapse ${fn:contains(pageContext.request.requestURI,'raporlar') ? 'in' : ''}">
							<!-- HATIRLATMA SONU! -->
							<li><a
								href="${pageContext.request.contextPath}/raporlar/satisrapor">Satış
									Yoluyla Devir (5403)</a></li>
						</ul>
					</li>
					<!-- /chart types -->



					<li role="separator" class="divider"></li>

					<li data-toggle="collapse" href="#izin" aria-expanded="false"
						aria-controls="features"><a href="#"> <i
							class="fa fa-map-marker" aria-hidden="true"></i><span>İZİN
								İŞLEMLERİ</span>
					</a></li>
					<li>
						<!-- !HATIRLATMA! sayfa yenilendiğinde aktif olan link sidebar kısmında seçili olarak geliyor -->
						<ul id="izin"
							class="sub-menu collapse ${fn:contains(pageContext.request.requestURI,'izin-islemleri') ? 'in' : ''}">
							<!-- HATIRLATMA SONU! -->
							<li><a
								href="${pageContext.request.contextPath}/izin-islemleri/izin-formu">İzin
									İşlemleri</a></li>
						</ul>
					</li>
				</c:if>
				<!-- /chart types -->
				<li role="separator" class="divider"></li>
				<li data-toggle="collapse" href="#arazi" aria-expanded="false"
					aria-controls="features"><a href="#"> <i
						class="fa fa-star" aria-hidden="true"></i><span>ARAZİ
							ÇIKIŞLARI</span>
				</a></li>
				<li>
					<!-- !HATIRLATMA! sayfa yenilendiğinde aktif olan link sidebar kısmında seçili olarak geliyor -->
					<ul id="arazi"
						class="sub-menu collapse ${fn:contains(pageContext.request.requestURI,'arazi-cikislari') ? 'in' : ''}">
						<!-- HATIRLATMA SONU! -->
						<li><a
							href="${pageContext.request.contextPath}/arazi-cikislari/arac-islemleri">Araç
								İşlemleri</a></li>
					</ul>
				</li>

				<c:if test="${cookie.id.value==1 }">
					<!-- /chart types -->
					<li role="separator" class="divider"></li>
					<li data-toggle="collapse" href="#sabitler" aria-expanded="false"
						aria-controls="features"><a href="#"> <i
							class="fa fa-star" aria-hidden="true"></i><span>YÖNETİM
								PANELİ</span>
					</a></li>

					<li>
						<!-- !HATIRLATMA! sayfa yenilendiğinde aktif olan link sidebar kısmında seçili olarak geliyor -->
						<ul id="sabitler"
							class="sub-menu collapse ${fn:contains(pageContext.request.requestURI,'yonetim') ? 'in' : ''}">
							<!-- HATIRLATMA SONU! -->
							<li><a
								href="${pageContext.request.contextPath}/yonetim/sabitler">Köy/Mahalle/İlçe/İl
									Ekleme</a></li>
							<li><a
								class="${fn:contains(pageContext.request.requestURI,'roller') ? 'in' : ''}"
								href="${pageContext.request.contextPath}/yonetim/rol-yonetimi">Kullanici
									Yetki Belirleme</a></li>
						</ul>
					</li>
				</c:if>
			</ul> --%>


		</div>

	</div>
</nav>

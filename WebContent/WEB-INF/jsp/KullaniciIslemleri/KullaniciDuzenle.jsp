<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<c:if test="${kullanici.id==0 }">
	<script type="text/javascript">
		var jq = jQuery.noConflict();

		jq(document).ready(function() {
			jq("#cepTelefonu").val('');
		});
	</script>

</c:if>

<div class="container-fluid">
	<div class="container">
		<div class="row main">

			<div class="main-login main-center"> 

				<h5>Sistem Giriş Kayıt Bilgileri</h5>
				<img alt="Kullanici img" align="top"
					src="${pageContext.request.contextPath}/assets/images/personelimage/avatar"
					width="50px">
				<form:form method="POST" action="kullaniciEkle"
					commandName="kullanici" class="form" enctype="multipart/form-data">
					<div class="form-group">
						Kullanıcı Adınız
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa"
									aria-hidden="true"></i></span>
								<form:input type="text" class="form-control" path="adi" id="adi"
									placeholder="Ör: ali.veli" />
							</div>
						</div>
					</div>

					<div class="form-group">
						Şifre
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
								<form:input class="form-control" path="sifre" id="password"
									type="password" placeholder="Şifrenizi Giriniz"
									data-validation="length alphanumeric"
									data-validation-length="min4" />
							</div>
						</div>
					</div>

					<div class="form-group">
						Şifre Tekrarı
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-lock fa-lg" aria-hidden="true"></i></span> <input
									class="form-control" name="confirm" id="confirm"
									type="password" placeholder="Şifrenizi Tekrarlayınız"
									data-validation="length alphanumeric"
									data-validation-length="min4" />
							</div>
						</div>
					</div>
					<div class="form-group">
						İsminiz
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-tag fa-lg" aria-hidden="true"></i></span>
								<form:input type="text" class="form-control" path="isimSoyisim"
									id="name" placeholder="Ör: Ali Veli MEHLİ" />
							</div>
						</div>
					</div>
					<div class="form-group">
						Ünvanınız
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-star fa-lg" aria-hidden="true"></i></span>
								<form:select path="unvan" class="form-control input-md">
									<form:option value="0">---Seçiniz------------</form:option>
									<form:option value="Ziraat Mühendis">Mühendis (Ziraat)</form:option>
									<form:option value="Endüstri Mühendis">Mühendis (Endüstri)</form:option>
									<form:option value="Türün Teknolojisi Mühendis">Mühendis (Türün Teknolojisi)</form:option>
									<form:option value="Harita Mühendis">Mühendis (Harita)</form:option>
									<form:option value="Bilgisayar Mühendis">Mühendis (Bilgisayar)</form:option>
									<form:option value="Mühendis">Mühendis</form:option>
									<form:option value="Veteriner Hekim">Veteriner Hekim</form:option>
									<form:option value="Sosyolog">Sosyolog</form:option>
									<form:option value="Bilgisayar İşletmeni">Bilgisayar İşletmeni</form:option>
									<form:option value="4/C">4/C</form:option>
									<form:option value="İşçi">İşçi</form:option>
									<form:option value="VHKİ">V.H.K.İ.</form:option>
									<form:option value="Memur">Memur</form:option>
									<form:option value="Hizmetli Memur">Hizmetli Memur</form:option>
									<form:option value="İşçi">İşçi</form:option>
									<form:option value="Ziraat Tekniker">Tekniker (Ziraat)</form:option>
									<form:option value="Tekniker">Tekniker</form:option>
									<form:option value="Teknisyen">Teknisyen</form:option>
									<form:option value="Şube Müdürü">Şube Müdürü</form:option>
									<form:option value="İl Müdürü">İl Müdürü</form:option>
									<form:option value="İl Müdür Yardımcısı">İl Müdür Yardımcısı</form:option>
								</form:select>
							</div>
						</div>
					</div>
					<div class="form-group">
						Şubeniz
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-building fa-lg" aria-hidden="true"></i></span>
								<form:select path="birim" class="form-control input-xs">
									<form:option value="0">---Seçiniz------------</form:option>
									<form:option value="AraziToplulaştırmaveTarımsalAltyapı">Arazi Toplulaştırma ve Tarımsal Altyapı</form:option>
									<form:option value="BitkiselÜretimveBitkiSağlığı">Bitkisel Üretim ve Bitki Sağlığı</form:option>
									<form:option value="BalıkçılıkveSuÜrünleri">Balıkçılık ve Su Ürünleri</form:option>
									<form:option value="ÇayırMeraveYemBitkileri">Çayır Mera ve Yem Bitkileri</form:option>
									<form:option value="GıdaveYem">Gıda ve Yem</form:option>
									<form:option value="HayvanSağlığıveYetiştiriciliği">Hayvan Sağlığı ve Yetiştiriciliği</form:option>
									<form:option value="İdariveMaliİşler">İdari ve Mali İşler</form:option>
									<form:option value="KırsalKalkınmaveÖrgütlenme">Kırsal Kalkınma ve Örgütlenme</form:option>
									<form:option value="KoordinasyonveTarımsalVeriler">Koordinasyon ve Tarımsal Veriler</form:option>
								</form:select>
							</div>
						</div>
					</div>

					<div class="form-group">
						Email
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-at fa"
									aria-hidden="true"></i></span> <input type="text" class="form-control"
									name="email" id="email" placeholder="Ör: ali.veli@tarim.gov.tr" />
							</div>
						</div>
					</div>



					<!-- Cep Telefonu -->
					<div class="form-group">
						Cep Telefonu
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-phone fa-lg" aria-hidden="true"></i></span>
								<form:input path="cepTelefonu" id="cepTelefonu"
									class="form-control input-xs" name="cepTelefonu" type="phone"
									placeholder="Ör: 5321234567"
									data-validation="length alphanumeric"
									data-validation-length="min4" />
							</div>
						</div>
					</div>

					<!-- Sicil No -->
					<div class="form-group">
						Sicil No
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-yelp fa-lg" aria-hidden="true"></i></span>
								<form:input path="sicilNo" id="sicilNo"
									class="form-control input-xs" type="text"
									placeholder="Ör: 2015-1232"
									data-validation="length alphanumeric"
									data-validation-length="min4" />
							</div>
						</div>
					</div>


					<!-- İzin Hakkı -->
					<div class="form-group">
						İzin Hakkı
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-globe fa-lg" aria-hidden="true"></i></span>

								<form:select path="izinHakki" class="form-control input-xs"
									id="izinHakki">
									<form:option value="0">---Seçiniz------------</form:option>
									<form:option value="2">20 Gün</form:option>
									<form:option value="3">30 Gün</form:option>
								</form:select>
							</div>
						</div>
					</div>


					<div class="form-group ">
						<input type="submit"
							class="btn btn-primary btn-lg btn-block login-button"
							value="Kayıt">
					</div>
				</form:form>

			</div>
		</div>
	</div>




</div>

<%-- 
<form:form method="POST" action="kullaniciEkle" commandName="kullanici"
	enctype="multipart/form-data">
	<div class="float float-left">
		<table class="table  table-sm">
			<tr>
				<td>İsim Soyisim</td>
				<td><form:input path="adi" class="form-control input-xs" /></td>
			</tr>
			<tr>
				<td>Kullanici Adı</td>
				<td><form:input path="isimSoyisim"
						class="form-control input-xs" /></td>
			</tr>
			<tr>
				<c:if test="${cookie.id.value==1 }">
					<td>Rol</td>
					<td><form:select path="roles.id" items="${roller}"
							class="form-control input-xs" data-live-search="true"
							itemLabel="rollAdi" data-tokens="id" itemValue="id">
							<form:option value="0">---Seçiniz------------</form:option>
					<form:option value="2">20 Gün</form:option>
					<form:option value="3">30 Gün</form:option>
						</form:select></td>
				</c:if>

			</tr>
			<tr>
				<td>Şifre</td>
				<td><form:input path="sifre" class="form-control input-xs" /></td>
			</tr>
			<tr>
				<td>Ünvanı</td>
				<td><form:select path="unvan" class="form-control input-xs">
						<form:option value="0">---Seçiniz------------</form:option>
						<form:option value="Ziraat Mühendis">Mühendis (Ziraat)</form:option>
						<form:option value="Endüstri Mühendis">Mühendis (Endüstri)</form:option>
						<form:option value="Türün Teknolojisi Mühendis">Mühendis (Türün Teknolojisi)</form:option>
						<form:option value="Harita Mühendis">Mühendis (Harita)</form:option>
						<form:option value="Bilgisayar Mühendis">Mühendis (Bilgisayar)</form:option>
						<form:option value="Mühendis">Mühendis</form:option>
						<form:option value="Veteriner Hekim">Veteriner Hekim</form:option>
						<form:option value="Sosyolog">Sosyolog</form:option>
						<form:option value="Bilgisayar İşletmeni">Bilgisayar İşletmeni</form:option>
						<form:option value="4/C">4/C</form:option>
						<form:option value="İşçi">İşçi</form:option>
						<form:option value="VHKİ">V.H.K.İ.</form:option>
						<form:option value="Memur">Memur</form:option>
						<form:option value="Hizmetli Memur">Hizmetli Memur</form:option>
						<form:option value="İşçi">İşçi</form:option>
						<form:option value="Ziraat Tekniker">Tekniker (Ziraat)</form:option>
						<form:option value="Tekniker">Tekniker</form:option>
						<form:option value="Teknisyen">Teknisyen</form:option>
						<form:option value="Şube Müdürü">Şube Müdürü</form:option>
						<form:option value="İl Müdürü">İl Müdürü</form:option>
						<form:option value="İl Müdür Yardımcısı">İl Müdür Yardımcısı</form:option>
					</form:select></td>
			</tr>
			<tr>
				<td>Sicil No</td>
				<td><form:input path="sicilNo" class="form-control input-xs" /></td>
			</tr>

		</table>
	</div>
	<div class="float float-left">
		<table class="table  table-sm">
			<tr>
				<td>Birimi</td>
				<td><form:select path="birim" class="form-control input-xs">
						<form:option value="0">---Seçiniz------------</form:option>
						<form:option value="AraziToplulaştırmaveTarımsalAltyapı">Arazi Toplulaştırma ve Tarımsal Altyapı</form:option>
						<form:option value="BitkiselÜretimveBitkiSağlığı">Bitkisel Üretim ve Bitki Sağlığı</form:option>
						<form:option value="BalıkçılıkveSuÜrünleri">Balıkçılık ve Su Ürünleri</form:option>
						<form:option value="ÇayırMeraveYemBitkileri">Çayır Mera ve Yem Bitkileri</form:option>
						<form:option value="GıdaveYem">Gıda ve Yem</form:option>
						<form:option value="HayvanSağlığıveYetiştiriciliği">Hayvan Sağlığı ve Yetiştiriciliği</form:option>
						<form:option value="İdariveMaliİşler">İdari ve Mali İşler</form:option>
						<form:option value="KırsalKalkınmaveÖrgütlenme">Kırsal Kalkınma ve Örgütlenme</form:option>
						<form:option value="KoordinasyonveTarımsalVeriler">Koordinasyon ve Tarımsal Veriler</form:option>
					</form:select></td>
			</tr>
			<tr>
				<td>İzin Hakkı</td>
				<td><form:select path="izinHakki" class="form-control input-xs">
						<form:option value="0">---Seçiniz------------</form:option>
						<form:option value="2">20 Gün</form:option>
						<form:option value="3">30 Gün</form:option>
					</form:select></td>
			</tr>
			<tr>
				<td>E-Posta</td>
				<td><form:input path="ePosta" class="form-control input-xs" /></td>

			</tr>
			<tr>
				<td>Cep Telefonu</td>
				<td><form:input path="cepTelefonu" id="cepTelefonu"
						class="form-control input-xs" /></td>
			</tr>
			<tr>
				<c:if test="${cookie.id.value==1 }">
					<td>Durum</td>
					<td><form:select path="durum" id="durum"
							class="form-control input-xs">
							<form:option value="9">---Seçiniz------------</form:option>
							<form:option value="1">Aktif</form:option>
							<form:option value="0">Pasif</form:option>
						</form:select></td>
				</c:if>
			</tr>

			<tr>
			<td>Resim</td>

			<c:if test="${kullanici.id ne 0}">
				<td><form:input type="image" path="pic" width="100px"
						src="${pageContext.request.contextPath}/kullanici-islemleri/photoGoster2?id=${kullanici.id} " /></td>
			</c:if>
			<c:if test="${kullanici.id eq 0}">
				<td><form:input type="image" path="pic" width="50px"
						height="50px"
						src="${pageContext.request.contextPath}/assets/images/personelimage/avatar" /></td>
			</c:if>
			<td><input type="file" name="file" multiple="multiple" />
				${message }</td>
		</tr>
			<tr>
				<c:if test="${kullanici.id!=0 }">
					<td align="center"><input type="button" value="Vazgeç"
						onclick="javascript:location.href='./kullaniciVazgec'"
						class="btn btn-danger"></td>
				</c:if>
				<td colspan="6" align="center"><input type="submit"
					value="Kaydet" class="btn btn-info"></td>
			</tr>
		</table>
	</div>
</form:form> --%>
<c:if test="${cookie.id.value==1 }">

	<table class="table table-striped bg-info">
		<tr>
			<td>Sıra</td>
			<td>İsim Soyisim</td>
			<td>Rol</td>
			<td>Şifre</td>
			<td>Sicil No</td>
			<td>Birimi</td>
			<td>Ünvanı</td>
			<td>E-Posta</td>
			<td>Cep Telefonu</td>
			<td>Resim</td>
			<td>Durumu</td>
		</tr>
		<c:forEach items="${kullaniciListesi}" var="kullanici"
			varStatus="siraNo">
			<tr>
				<td><a
					href="${pageContext.request.contextPath}/kullanici-islemleri/kullaniciGuncelle/${kullanici.id}">${siraNo.count }</a>
				</td>
				<td>${kullanici.adi}</td>
				<td>${kullanici.roles.rollAdi}</td>
				<td>**********</td>
				<td>${kullanici.sicilNo}</td>
				<td>${kullanici.birim}</td>
				<td>${kullanici.unvan}</td>
				<td>${kullanici.ePosta}</td>
				<td>${kullanici.cepTelefonu}</td>
				<td><c:if test="${!empty kullanici.pic  }">

						<a href="#"><img
							src="${pageContext.request.contextPath}/kullanici-islemleri/photoGoster2?id=${kullanici.id} "
							width="50px" height="50px" /></a>
					</c:if> <c:if test="${empty kullanici.pic  }">

						<img src="<c:url value="/assets/images/personelimage/avatar"/>"
							class="pic_ina " width="50px" height="50px" />

					</c:if></td>
				<!--${pageContext.request.contextPath}/kullanici-islemleri/productImage?id=${kullanici.id}  -->
				<c:if test="${kullanici.durum=='1'.charAt(0)}">
					<td>Aktif</td>
				</c:if>
				<c:if test="${kullanici.durum=='0'.charAt(0)}">
					<td>Pasif</td>
				</c:if>
				<c:if test="${kullanici.durum=='9'.charAt(0)}">
					<td>---HATA---</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</c:if>
<%-- 
 <form class="form-horizontal">
	<fieldset>

		<!-- Form Name -->
		<legend>Kullanici Tanimlama</legend>

		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="adi">Adı</label>
			<div class="col-md-4">
				<input id="adi" name="adi" type="text" placeholder="Adı"
					class="form-control input-xs" required=""> <span
					class="help-block">Ör:Emrah DENİZER</span>
			</div>
		</div>

		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="kullaniciAdi">Kullanıcı
				Adı</label>
			<div class="col-md-4">
				<input id="kullaniciAdi" name="kullaniciAdi" type="text"
					placeholder="Kullanıcı Adı" class="form-control input-md"
					required="required"> <span class="help-block">Ör:emrah.denizer</span>
			</div>
		</div>

		<!-- Password input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="sifre">Şifre</label>
			<div class="col-md-4">
				<input id="sifre" name="sifre" type="password" placeholder="şifre"
					class="form-control input-md" required=""> <span
					class="help-block">Harf-karakter-rakam</span>
			</div>
		</div>

		<!-- Password input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="sifreTekrar">Şifre
				Tekrarı</label>
			<div class="col-md-4">
				<input id="sifreTekrar" name="sifreTekrar" type="password"
					placeholder="şifre" class="form-control input-md" required="">
				<span class="help-block">Harf-karakter-rakam</span>
			</div>
		</div>

		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="unvan">Ünvan</label>
			<div class="col-md-4">
				<input id="unvan" name="unvan" type="text" placeholder="ünvan"
					class="form-control input-md" required=""> <span
					class="help-block">Ör:Mühendis</span>
			</div>
		</div>

		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="sicilNo">Sicil No</label>
			<div class="col-md-4">
				<input id="sicilNo" name="sicilNo" type="text"
					placeholder="sicil no" class="form-control input-md" required="">
				<span class="help-block">Ör: 2012-1552</span>
			</div>
		</div>

		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="birim">Birim</label>
			<div class="col-md-4">
				<input id="birim" name="birim" type="text" placeholder="birim"
					class="form-control input-md" required=""> <span
					class="help-block">Ör:Arazi Edindirme</span>
			</div>
		</div>

		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="ePosta">E-Mail</label>
			<div class="col-md-4">
				<input id="ePosta" name="ePosta" type="text" placeholder="@mail"
					class="form-control input-md" required=""> <span
					class="help-block">Ör:emrah.denizer@tarim.gov.tr</span>
			</div>
		</div>

		<!-- Text input-->
		<div class="form-group">
			<label class="col-md-4 control-label" for="cepTelefonu">Cep
				Telefonu</label>
			<div class="col-md-4">
				<input id="cepTelefonu" name="cepTelefonu" type="text"
					placeholder="cep telefonu" class="form-control input-md"
					required=""> <span class="help-block">Ör:555 6667788</span>
			</div>
		</div>

		<!-- Multiple Radios -->
		<div class="form-group">
			<label class="col-md-4 control-label" for="izinHakki">İzin
				Hakkı</label>
			<div class="col-md-4">
				<div class="radio">
					<label for="izinHakki-0"> <input type="radio"
						name="izinHakki" id="izinHakki-0" value="2" checked="checked">
						20 gün
					</label>
				</div>
				<div class="radio">
					<label for="izinHakki-1"> <input type="radio"
						name="izinHakki" id="izinHakki-1" value="3"> 30 Gün
					</label>
				</div>
			</div>
		</div>

		<!-- Select Basic -->
		<div class="form-group">
			<label class="col-md-4 control-label" for="kullaniciRol">Rol</label>
			<div class="col-md-4">
				<select id="kullaniciRol" name="kullaniciRol" class="form-control">
					<option value="1">Option one</option>
					<option value="2">Option two</option>
				</select>
			</div>
		</div>

		<!-- Multiple Radios -->
		<div class="form-group">
			<label class="col-md-4 control-label" for="durum">User Status</label>
			<div class="col-md-4">
				<div class="radio">
					<label for="durum-0"> <input type="radio" name="durum"
						id="durum-0" value="1" checked="checked"> Active
					</label>
				</div>
				<div class="radio">
					<label for="durum-1"> <input type="radio" name="durum"
						id="durum-1" value="0"> Passive
					</label>
				</div>
			</div>
		</div>     
		<!-- Button -->
		<div class="form-group">
			<label class="col-md-4 control-label" for="submitButton"></label>
			<div class="col-md-4">
				<button id="submitButton" name="submitButton"
					class="btn btn-primary btn-md pull-right ">Kaydet</button>
			</div>
		</div>
		<!-- Button Drop Down -->
		<div class="form-group">
			<label class="col-md-4 control-label" for="buttondropdown">Button
				Drop Down</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="buttondropdown" name="buttondropdown"
						class="form-control" placeholder="placeholder" type="text"
						required="">
					<div class="input-group-btn">
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown">
							Action <span class="caret"></span>
						</button>
						<ul class="dropdown-menu pull-right">
							<li><a href="#">Option one</a></li>
							<li><a href="#">Option two</a></li>
							<li><a href="#">Option three</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

	</fieldset>
</form>
 --%>

<style>
#playground-container {
	height: 500px;
	overflow: hidden !important;
	-webkit-overflow-scrolling: touch;
}

body, html {
	height: 100%;
	background-repeat: no-repeat;
	/* background: url(https://i.ytimg.com/vi/4kfXjatgeEU/maxresdefault.jpg); */
	font-family: 'Oxygen', sans-serif;
	background-size: cover;
}

.main {
	margin: 50px 15px;
}

h1.title {
	font-size: 50px;
	font-family: 'Passion One', cursive;
	font-weight: 400;
}

hr {
	width: 10%;
	color: #fff;
}

.form-group {
	margin-bottom: 15px;
}

label {
	margin-bottom: 15px;
}

input, input::-webkit-input-placeholder {
	font-size: 11px;
	padding-top: 3px;
}

.main-login {
	background-color: #000;
	/* shadows and rounded borders */
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
	-moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	-webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.form-control {
	height: auto !important;
	padding: 8px 12px !important;
}

.input-group {
	-webkit-box-shadow: 0px 2px 5px 0px rgba(0, 0, 0, 0.21) !important;
	-moz-box-shadow: 0px 2px 5px 0px rgba(0, 0, 0, 0.21) !important;
	box-shadow: 0px 2px 5px 0px rgba(0, 0, 0, 0.21) !important;
}

#button {
	border: 1px solid #ccc;
	margin-top: 28px;
	padding: 6px 12px;
	color: #666;
	text-shadow: 0 1px #fff;
	cursor: pointer;
	-moz-border-radius: 3px 3px;
	-webkit-border-radius: 3px 3px;
	border-radius: 3px 3px;
	-moz-box-shadow: 0 1px #fff inset, 0 1px #ddd;
	-webkit-box-shadow: 0 1px #fff inset, 0 1px #ddd;
	box-shadow: 0 1px #fff inset, 0 1px #ddd;
	background: #f5f5f5;
	background: -moz-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #f5f5f5),
		color-stop(100%, #eeeeee));
	background: -webkit-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
	background: -o-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
	background: -ms-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
	background: linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f5f5f5',
		endColorstr='#eeeeee', GradientType=0);
}

.main-center {
	margin-top: 30px;
	margin: 0 auto;
	max-width: 400px;
	padding: 10px 40px;
	background: #009edf;
	color: #FFF;
	text-shadow: none;
	-webkit-box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
	-moz-box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
	box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
}

span.input-group-addon i {
	color: #009edf;
	font-size: 17px;
}

.login-button {
	margin-top: 5px;
}

.login-register {
	font-size: 11px;
	text-align: center;
}
</style>
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
<title>Aşı Kartı</title>
</head>
<body>
	<div class="container">
		<div class="row">

			<div class="col-sm-8 col-sm-offset-2">

				<div class="page-header">&nbsp;</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Aşı Takip Kartı-${birim }</h3>
					</div>
					<div class="panel-body">
						<form:form id="signupForm1" method="post" class="form-horizontal"
							action="asiKartiEkle" role="form" commandName="asiKarti">
							<form:hidden path="id" />
							<div class="form-group">
								<label class="col-sm-4 control-label" for="ilce">Verilen
									İlçe</label>
								<div class="col-sm-5">
									<form:select path="ilce.id" id="ilce" class="form-control"
										name="ilce">
										<form:option value=""></form:option>
										<form:options items="${ilceListesi}" itemValue="id"
											itemLabel="isim" />

									</form:select>
								</div>

								<label class="col-sm-4 control-label" for="asiCinsi">Verilen
									Aşının Cinsi</label>
								<div class="col-sm-5">
									<form:select path="asiCinsi.id" id="asiCinsi"
										class="form-control" name="asiCinsi">
										<form:option value=""></form:option>
										<form:options items="${tumAsiCesitleri}" itemValue="id"
											itemLabel="asiAdi" />

									</form:select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="verilisTarih">Veriliş
									Tarihi</label>
								<div class="col-sm-5">
									<form:input class="form-control" name="verilisTarih"
										path="verilisTarih" id="verilisTarih" type="date"></form:input>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="teslimAlanAdSoyad">Teslim
									Alanın Adı Soyadı Adı</label>
								<div class="col-sm-5">
									<form:input class="form-control" name="teslimAlanAdSoyad"
										path="teslimAlanAdSoyad" id="teslimAlanAdSoyad"></form:input>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="miktar">Doz</label>
								<div class="col-sm-5">
									<form:input class="form-control" name="miktar" path="miktar"
										id="miktar"></form:input>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="birimFiyati">Teslim
									Fiyatı</label>
								<div class="col-sm-5">
									<form:input class="form-control" id="birimFiyati"
										name="birimFiyati" path="birimFiyati"></form:input>
								</div>
							</div>
							<%-- 
							<div class="form-group">
								<label class="col-sm-4 control-label" for="odemeTarihi">Yatırılan
									Aşının Tarihi</label>
								<div class="col-sm-5">
									<form:input class="form-control" name="odemeTarihi"
										path="odemeTarihi" id="odemeTarihi" type="date"></form:input>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="odemeYeri">Yatırılan
									Yer</label>
								<div class="col-sm-5">
									<form:input class="form-control" name="odemeYeri"
										path="odemeYeri" id="odemeYeri"></form:input>
								</div>
							</div> --%>
							<div class="form-group">
								<div class="col-sm-8 col-sm-offset-2">
									<button type="submit" class="btn btn-primary pull-right btn-md"
										name="signup1" value="Sign up">${tusYazisi}</button>
									<c:if test="${tusYazisi eq 'Guncelle' }">
										<button type="button" class="btn btn-danger btn-md pull-right"
											name="vazgec" value="Vazgeç"
											onclick="javascript:location.href='./aricilikVazgec'">Vazgeç
										</button>
									</c:if>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>

		</div>


	</div>
	<div class="container">
		<div class="col-lg-12">
			<table class="table table-bordered table-hover bg-success">
				<tr>
					<td>#</td>
					<td>VERİLEN İLÇE</td>
					<td>VERİLEN AŞININ CİNSİ</td>
					<td>VERİLİŞ TARİHİ</td>
					<td>TESLİM ALANIN ADI SOYADI</td>
					<td>MİKTAR DOZ/ADET</td>
					<td>TESLİM FİYATI</td>
					<td>TESLİM TUTARI</td>

					<td colspan="2">${toplam }</td>

				</tr>

				<c:forEach items="${tumAsiKartListesi }" var="kart" varStatus="i">

					<tr>
						<td>${i.count}</td>
						<td>${kart.ilce.id}</td>
						<td>${kart.asiCinsi.asiAdi}</td>
						<td>${kart.verilisTarih}</td>
						<td>${kart.teslimAlanAdSoyad }</td>
						<td>${kart.miktar}</td>
						<td>${kart.birimFiyati }</td>
						<td>${kart.birimFiyati * kart.miktar }</td>


						<td><a href="#" class="btn btn-info">Düzenle</a></td>
						<td><a href="#" class="btn btn-danger">Sil</a></td>
					</tr>



				</c:forEach>
			</table>

		</div>
	</div>
	<script type="text/javascript">
		jq(document)
				.ready(
						function() {
							var date = new Date();
							var year = date.getFullYear();

							var jq = jQuery.noConflict();
							/* jq.validator.setDefaults({
								submitHandler : function() {
									alert("submitted!");
								}
							}); */

							jq("#signupForm1")
									.validate(
											{
												rules : {
													dogumTarihi : {
														required : true,

													},
													ilce : {
														required : true,

													},
													slctTipler : {
														required : true,
														min : {
															param : 1,
														}
													},

													yil : {
														maxlength : 4,
														required : true,
														min : {
															param : 2000,
														},
														max : {
															param : year,
														},
													},
													yararlaniciAdiSoyadi : "required",
													yararlaniciBabaAdiSoyadi : "required",
													hibeTutari : {
														required : true,
														min : {
															param : 1,
														},
														max : {
															param : 30000,
														},

													},
													faturaBedeli : {

														required : true,
														min : {
															param : 1,
														},
													},
													kapasite : {

														required : true,
														min : {
															param : 1,
														},
													},
													username1 : {
														required : true,
														minlength : 3
													},
													password1 : {
														required : true,
														minlength : 5
													},
													confirm_password1 : {
														required : true,
														minlength : 5,
														equalTo : "#password1"
													},
													email1 : {
														required : true,
														email : true
													},
													agree1 : "required"
												},
												messages : {
													kapasiteBirim : "Lütfen birim seçiniz.",
													dogumTarihi : "Lütfen dogum tarihi seçiniz",
													yararlaniciAdiSoyadi : "Lütfen yararlanici adını giriniz",
													yararlaniciBabaAdiSoyadi : "Lütfen yararlanici Baba adını giriniz",
													ilce : "Lütfen ilçe seçiniz.",
													faturaBedeli : {
														required : "Lütfen fatura tutarını giriniz",
														min : "Lütfen {0} 'dan büyük bir değer giriniz.",
													},
													hibeTutari : {
														required : "Lütfen hibe tutarını giriniz",
														min : "Lütfen {0} 'dan büyük bir değer giriniz.",
														max : "En fazla 30.000,00 TL girebilirsiniz. "
													},
													kapasite : {
														required : "Lütfen kapasite giriniz.",
														min : "{0} veya {0}'den büyük değer giriniz. "
													},
													yil : {
														required : "Lütfen tarih giriniz",
														min : "Girilen tarih {0} yılından sonra olmalıdır.",
														max : "Girilen tarih {0} yılından önce olmalıdır.",
														maxlength : "Geçersiz bir taih girdiniz!"
													},
													username1 : {
														required : "Please enter a username",
														minlength : "Your username must consist of at least 2 characters"
													},
													password1 : {
														required : "Please provide a password",
														minlength : "Your password must be at least 5 characters long"
													},
													confirm_password1 : {
														required : "Please provide a password",
														minlength : "Your password must be at least 5 characters long",
														equalTo : "Please enter the same password as above"
													},
													email1 : "Please enter a valid email address",
													kategori : "Lütfen kategori seçiniz",
												},
												errorElement : "em",
												errorPlacement : function(
														error, element) {
													// Add the `help-block` class to the error element
													error
															.addClass("help-block");

													// Add `has-feedback` class to the parent div.form-group
													// in order to add icons to inputs
													element
															.parents(
																	".col-sm-5")
															.addClass(
																	"has-feedback");

													if (element.prop("type") === "radio") {
														error
																.insertAfter(element
																		.parent("label"));
													} else {
														error
																.insertAfter(element);
													}

													// Add the span element, if doesn't exists, and apply the icon classes to it.
													if (!element.next("span")[0]) {
														jq(
																"<span class='glyphicon glyphicon-remove form-control-feedback'></span>")
																.insertAfter(
																		element);
													}
												},
												success : function(label,
														element) {
													// Add the span element, if doesn't exists, and apply the icon classes to it.
													if (!jq(element).next(
															"span")[0]) {
														jq(
																"<span class='glyphicon glyphicon-ok form-control-feedback'></span>")
																.insertAfter(
																		jq(element));
													}
												},
												highlight : function(element,
														errorClass, validClass) {
													jq(element)
															.parents(
																	".col-sm-5")
															.addClass(
																	"has-error")
															.removeClass(
																	"has-success");
													jq(element)
															.next("span")
															.addClass(

															"glyphicon-remove")
															.removeClass(
																	"glyphicon-ok");
												},
												unhighlight : function(element,
														errorClass, validClass) {
													jq(element)
															.parents(
																	".col-sm-5")
															.addClass(
																	"has-success")
															.removeClass(
																	"has-error");
													jq(element)
															.next("span")
															.addClass(
																	"glyphicon-ok")
															.removeClass(
																	"glyphicon-remove");
												}
											});

						});
	</script>





</body>
<br>
<br>
</html>
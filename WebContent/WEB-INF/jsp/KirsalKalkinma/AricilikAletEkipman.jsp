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

			<div class="col-sm-8 col-sm-offset-2">
				<h3>
					<a href="./aricilik-liste" class="btn btn-default pull-right">Arici
						Listesi <span class="glyphicon glyphicon-list"></span>
					</a>
				</h3>
				<div class="page-header">&nbsp;</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Arıcılık</h3>
					</div>
					<div class="panel-body">
						<form:form id="signupForm1" method="post" class="form-horizontal"
							action="aricilikEkle" role="form" commandName="aricilik">
							<form:hidden path="id" />
							<div class="form-group">
								<label class="col-sm-4 control-label" for="yil">Destek
									Yılı</label>
								<div class="col-sm-5">
									<form:input class="form-control" name="yil" path="yil" id="yil"></form:input>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="yararlaniciAdiSoyadi">Adı
									Soyadı</label>
								<div class="col-sm-5">
									<form:input class="form-control" name="yararlaniciAdiSoyadi"
										path="yararlaniciAdiSoyadi" id="yararlaniciAdiSoyadi"></form:input>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label"
									for="yararlaniciBabaAdiSoyadii">Baba Adı</label>
								<div class="col-sm-5">
									<form:input class="form-control"
										name="yararlaniciBabaAdiSoyadi"
										path="yararlaniciBabaAdiSoyadi" id="yararlaniciBabaAdiSoyadi"></form:input>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="dogumTarihi">Doğum
									Tarihi</label>
								<div class="col-sm-5">
									<form:input class="form-control" name="dogumTarihi"
										path="dogumTarihi" id="dogumTarihi" type="date"></form:input>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="ilce">İlçe</label>
								<div class="col-sm-5">
									<form:select class="form-control " id="ilce" name="ilce"
										path="ilce.id">
										<option value="">Seçiniz</option>
										<c:forEach items="${ilceListesi}" var="tip">
											<option value="${tip.id}"
												<c:if test="${ aricilik ne null }">
												${tip.isim eq aricilik.ilce.isim ? 'selected' : '' }</c:if>>${tip.isim }</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="telNo">Telefon
									No</label>
								<div class="col-sm-5">
									<form:input class="form-control" name="telNo" path="telNo"
										id="telNo"></form:input>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="faturaBedeli">Fatura
									Bedeli</label>
								<div class="col-sm-5">
									<form:input class="form-control" name="faturaBedeli"
										path="faturaBedeli" id="faturaBedeli" ></form:input>
								</div>
							</div>
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
</html>

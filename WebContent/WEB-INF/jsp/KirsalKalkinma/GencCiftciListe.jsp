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
<title>JQuery-validation demo | Bootstrap</title>
<style type="text/css">
select {
	-webkit-appearance: none;
	-moz-appearance: none;
	text-indent: 1px;
	text-overflow: '';
}
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<div class="pull-right">
					<h3>
						<a href="./genc-ciftci" class="btn btn-success btn-md"> <span
							class="glyphicon glyphicon-plus"></span> YENİ KAYIT
						</a>
					</h3>
				</div>
				<div class="page-header">&nbsp;</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Genç Çiftçi Listesi</h3>
					</div>
					<div class="panel-body">
						<table
							class="table table-sm table-bordered table-hover bg-default "
							id="tr${ilce }">
							<tr class="baslik">
								<%-- <td align="center"></td> --%>
								<th align="center">Yararlanıcı</th>
								<th align="center">Proje Konusu</th>
								<th align="center">Uygulama Yeri</th>
								<th align="center">Kapasite</th>
								<th align="center">Hibe Tutarı</th>
								<th align="center">Yıl</th>
								<th align="center">Durum</th>
								<th align="center" colspan="2">Sil / Güncelle</th>
							</tr>
							<c:forEach items="${gencCiftci}" var="genc">
								<tr>
									<td>${genc.yararlaniciAdi }&nbsp;${genc.yararlaniciSoyadi }</td>

									<c:if test="${empty genc.kategori.tip.tip.isim }">

										<td>${genc.kategori.isim}</td>

									</c:if>
									<c:if test="${!empty genc.kategori.tip.tip.isim }">

										<td>${genc.kategori.tip.tip.isim}-${genc.kategori.tip.isim}-${genc.kategori.isim}</td>

									</c:if>
									<td>${genc.mahalle.tip.tip.isim}-${genc.mahalle.tip.isim}-${genc.mahalle.isim}</td>
									<td>${genc.kapasite}-${genc.kapasiteBirim}</td>
									<td>${genc.hibeTutari}</td>
									<td>${genc.yil}</td>
									<td>Devam</td>
									<td><a
										href="${pageContext.request.contextPath }/kirsal-kalkinma/ekonomikYatirimSil?id=${yatirim.id}"
										onclick="javascript:return confirm('${yatirim.etapNo}. etap ${yatirim.yatirimciAdi} isimli kaydı : \n Silmek İstediğinize Emin misiniz?');"
										class="btn btn-danger btn-sm">Sil</a></td>
									<td><a
										href="${pageContext.request.contextPath }/kirsal-kalkinma/ekonomikYatirimGuncelle/${yatirim.id}"
										class="btn btn-primary btn-sm">Güncelle</a></td>
							</c:forEach>

						</table>


					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		/* $.validator.setDefaults({
			submitHandler : function() {
				alert("submitted!");
			}
		}); */
		var jq = jQuery.noConflict();

		jq(document)
				.ready(
						function() {
							//Chosen Select aktif olduğunda validate etkin olmuyor!
							/* 	jq(".chosen-select").chosen({
									max_selected_options : 5,
									no_results_text : "Kayıt Yok!",
									width : "200px"
								}); */

							jq("#signupForm1")
									.validate(
											{
												rules : {
													ilce : {
														required : true,
														min : {
															param : 1,
														}
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
															param : 2018,
														},
													},
													yararlaniciAdi : "required",
													yararlaniciSoyadi : "required",
													hibeTutari : {

														required : true,
														min : {
															param : 1,
														}

													},

													kapasite : "required",
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
													slctTipler : "Lütfen Kategori seçiniz",
													yararlaniciAdi : "Lütfen yararlanici adını giriniz",
													yararlaniciSoyadi : "Lütfen yararlanici soyadını giriniz",
													ilce : "Lütfen ilçe seçiniz.",
													hibeTutari : {
														required : "Lütfen hibe tutarını giriniz",
														min : "Lütfen {0} 'dan büyük bir değer giriniz.",
													},
													kapasite : "Lütfen kapasite giriniz.",
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
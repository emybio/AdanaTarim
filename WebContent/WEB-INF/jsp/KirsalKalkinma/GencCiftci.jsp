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

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<div class="page-header"></div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Genç Çiftçi</h3>
					</div>
					<div class="panel-body">

						<%-- <div class="form-group">
							<label class="col-sm-4 control-label" for="yararlaniciAdi">Genel
								Müdürlük</label>
							<div class="col-sm-5">
								<form:select path="tip.tip.tip.id" class="form-control"
									onChange="ikisibirada(this.value)" id="slctTipler">
									<form:option value="0">Seçiniz</form:option>
									<form:options items="${tipListesi}" itemValue="id"
										itemLabel="isim" />
								</form:select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-4 control-label" for="yararlaniciAdi">Üst
								Kategori</label>
							<div class="col-sm-5">
								<select name="tip.tip.id" id="slctAltTip" class="form-control"
									onChange="ikisibiradamarkalar(this.value)">
									<form:option value="0">Seçiniz</form:option>
									<form:options items="${altTipListesi}" itemValue="id"
										itemLabel="isim" />
								</select>
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-4 control-label" for="yararlaniciAdi">Orta
								Kategori</label>
							<div class="col-sm-5">
								<select name="tip.id" id="slctMarka" class="form-control"
									onChange="modelGetir(this.value)">
									<form:option value="0">Seçiniz</form:option>
									<form:options items="${markaListesi}" itemValue="id"
										itemLabel="isim" />
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="yararlaniciAdi">Alt
								Kategori</label>
							<div class="col-sm-5">
								<select name="durum" id="durum" class="form-control"
									onChange="modelGetir1(this.value)">
									<option value="1">Aktif</option>
								</select>
							</div>
						</div>
 --%>



						<form:form id="signupForm1" method="post" class="form-horizontal"
							action="" role="form" commandName="gencCiftci">

							<div class="form-group">
								<label class="col-sm-4 control-label" for="kategori">Ketagori</label>
								<div class="col-sm-5">
									<form:input class="form-control " id="kategori"
										path="kategori" name="kategori" />
								</div>
							</div>


							<div class="form-group">
								<label class="col-sm-4 control-label" for="yararlaniciAdi">İsim</label>
								<div class="col-sm-5">
									<form:input class="form-control " id="yararlaniciAdi"
										path="yararlaniciAdi" name="yararlaniciAdi" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="yararlaniciSoyadi">Soyisim</label>
								<div class="col-sm-5">
									<form:input class="form-control " id="yararlaniciSoyadi"
										path="yararlaniciSoyadi" name="yararlaniciSoyadi" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="yil">Yıl</label>
								<div class="col-sm-5">
									<form:input class="form-control " id="yil" path="yil"
										name="yil" />
								</div>
							</div>


							<div class="form-group">
								<label class="col-sm-4 control-label" for="hibeTutari">Hibe
									Tutari</label>
								<div class="col-sm-5">
									<form:input class="form-control " id="hibeTutari"
										path="hibeTutari" name="hibeTutari" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="ilce">İlçe</label>
								<div class="col-sm-5">
									<form:select items="${ ilceListesi}" itemLabel="isim"
										required="true" itemValue="id"
										class="form-control chosen-select chosen-ltr" id="ilce"
										path="ilce" name="ilce" />
								</div>
							</div>




							<div class="form-group">
								<label class="col-sm-4 control-label kategori"
									for="confirm_password1">Kategori</label>
								<div class="col-sm-5 col-sm-offset-4">
									<div class="radio">
										<label> <input type="radio" id="aricilik"
											name="agree1" value="agree" />Arıcılık
										</label> <label> <input type="radio" id="hayvansal"
											name="agree1" value="agree" />Bitkisel Üretim
										</label> <label> <input type="radio" id="bitkisel"
											name="agree1" value="agree" />Hayvancılık
										</label>
									</div>


								</div>
							</div>


							<div class="form-group">
								<div class="col-sm-9 col-sm-offset-4">
									<button type="submit" class="btn btn-primary" name="signup1"
										value="Sign up">Sign up</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$.validator.setDefaults({
			submitHandler : function() {
				alert("submitted!");
			}
		});

		$(document)
				.ready(
						function() {

							//Chosen Select aktif olduğunda validate etkin olmuyor!
							/* 	jq(".chosen-select").chosen({
									max_selected_options : 5,
									no_results_text : "Kayıt Yok!",
									width : "200px"
								}); */

							$("#signupForm1")
									.validate(
											{
												rules : {
													kategori : "required",
													yararlaniciAdi : "required",
													yararlaniciSoyadi : "required",
													ilce : "required",
													lastname1 : "required",
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

													yararlaniciAdi : "Lütfen yararlanici adını giriniz",
													yararlaniciSoyadi : "Lütfen yararlanici soyadını giriniz",
													ilce : "Lütfen İlçe Seçiniz",
													lastname1 : "Please enter your lastname",
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
														$(
																"<span class='glyphicon glyphicon-remove form-control-feedback'></span>")
																.insertAfter(
																		element);
													}
												},
												success : function(label,
														element) {
													// Add the span element, if doesn't exists, and apply the icon classes to it.
													if (!$(element)
															.next("span")[0]) {
														$(
																"<span class='glyphicon glyphicon-ok form-control-feedback'></span>")
																.insertAfter(
																		$(element));
													}
												},
												highlight : function(element,
														errorClass, validClass) {
													$(element)
															.parents(
																	".col-sm-5")
															.addClass(
																	"has-error")
															.removeClass(
																	"has-success");
													$(element)
															.next("span")
															.addClass(

															"glyphicon-remove")
															.removeClass(
																	"glyphicon-ok");
												},
												unhighlight : function(element,
														errorClass, validClass) {
													$(element)
															.parents(
																	".col-sm-5")
															.addClass(
																	"has-success")
															.removeClass(
																	"has-error");
													$(element)
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
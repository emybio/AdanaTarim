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
</style>
</head>
<body>
	<div class="container">
		<div class="row">

			<div class="col-sm-8 col-sm-offset-2">
				<h3>
					<a href="./genc-ciftci-liste" class="btn btn-default pull-right">Genç
						Çiftçi Listesi <span class="glyphicon glyphicon-list"></span>
					</a>
				</h3>
				<div class="page-header">&nbsp;</div>
				<div class="panel panel-default">
					<div class="panel-heading">

						<h3 class="panel-title">Genç Çiftçi</h3>

					</div>
					<div class="panel-body">

						<form:form id="signupForm1" method="post" class="form-horizontal"
							action="gencCiftciEkle" role="form" commandName="gencCiftci">

							<div class="form-group">
								<label class="col-sm-4 control-label" for="slctTipler">Kategori
									1</label>
								<div class="col-sm-5">
									<select class="form-control" onChange="ikisibirada(this.value)"
										id="slctTipler" name="slctTipler">
										<option value="0">Seçiniz</option>
										<c:forEach items="${tipListesi}" var="tip">
											<option value="${tip.id}">${tip.isim }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="slctAltTip">Kategori
									2 (Eğer varsa)</label>
								<div class="col-sm-5">
									<select id="slctAltTip" class="form-control"
										onChange="ikisibiradamarkalar(this.value)" name="slctAltTip">
										<%-- <option value="0">Seçiniz</option>
										<options items="${altTipListesi}" itemValue="id"
											itemLabel="isim" /> --%>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="slctMarka">Kategori
									3 (Eğer varsa)</label>
								<div class="col-sm-5">
									<form:select id="slctMarka" class="form-control"
										path="kategori.id" name="kategori"
										onChange="modelGetir(this.value)">
										<%-- <option value="0">Seçiniz</option>
										<options items="${markaListesi}" itemValue="id"
											itemLabel="isim" /> --%>
									</form:select>
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
								<label class="col-sm-4 control-label" for="ilce">Kapasite</label>
								<div class="col-sm-5">
									<form:input path="kapasite" class="form-control " id="kapasite"
										name="kapasite" />
								</div>
							</div>


							<div class="form-group">
								<label class="col-sm-4 control-label" for="ilce">Kapasite
									Birim</label>
								<div class="col-sm-5">
									<form:select path="kapasiteBirim" class="form-control "
										id="kapasiteBirim" name="kapasiteBirim">
										<form:option value="">Seçiniz</form:option>
										<form:option value="kovan">Kovan</form:option>
										<form:option value="lt">Litre</form:option>
										<form:option value="da">Dekar</form:option>
										<form:option value="buyukbas">Büyük Baş</form:option>
										<form:option value="kucukbas">Küçük Baş</form:option>
										<form:option value="ton">Ton</form:option>
										<form:option value="adet/yıl">Adet/Yıl</form:option>
										<form:option value="kw/h">kw/h</form:option>
										<form:option value="kg">kg</form:option>
										<form:option value="kg/Yıl">kg/Yıl</form:option>
										<form:option value="Ton/Yıl">Ton/Yıl</form:option>
										<form:option value="m&sup2;">m&sup2;
										</form:option>
									</form:select>
								</div>
							</div>


							<div class="form-group">
								<label class="col-sm-4 control-label" for="ilce">İlçe</label>
								<div class="col-sm-5">
									<select class="form-control " id="ilce" name="ilce"
										onChange="gencCiftciIlce(this.value)">
										<option value="0">Seçiniz</option>
										<c:forEach items="${ilceListesi}" var="tip">
											<option value="${tip.id}">${tip.isim }</option>
										</c:forEach>
									</select>
								</div>
							</div>


							<div class="form-group">
								<label class="col-xs-4 control-label" for="mahalle">Mahalle</label>
								<div class="col-sm-5">
									<select name="mahalle" id="mahalle" class="form-control">
										<c:forEach items="${markaListesi}" var="tip">
											<option value="${tip.id}">${tip.isim }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- 	<div class="form-group">
								<label class="col-xs-4 control-label" for="cars">DATA </label>
								<div class="col-sm-5">
									<input type="text" list="cars" class="form-control" />
									<datalist id="cars" class="form-control">
										<option>Volvo</option>
										<option>Saab</option>
										<option>Mercedes</option>
										<option>Audi</option>
									</datalist>
								</div>
							</div> -->
							<!-- 
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
							</div> -->


							<div class="form-group">
								<div class="col-sm-8 col-sm-offset-2">
									<button type="submit" class="btn btn-primary pull-right btn-md"
										name="signup1" value="Sign up">Ekle</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var jq = jQuery.noConflict();
		jq.validator.setDefaults({
			submitHandler : function() {
				alert("submitted!");
			}
		});
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
													kapasiteBirim : {
														required : true,

													},
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
														},
														max : {
															param : 30000,
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
													slctTipler : "Lütfen Kategori seçiniz",
													yararlaniciAdi : "Lütfen yararlanici adını giriniz",
													yararlaniciSoyadi : "Lütfen yararlanici soyadını giriniz",
													ilce : "Lütfen ilçe seçiniz.",
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<script src='<c:url value="../assets/script/jquery.mask.js"  ></c:url>'></script>
<script
	src='<c:url value="../assets/script/jquery.mask.min.js"  ></c:url>'></script>


</head>
<script type="text/javascript">
	jq(document)
			.ready(
					function() {

						jq('#add')
								.click(
										function() {
											alert();
											jq('.block:last')
													.after(
															'<div class="block"><input type="text" /><span class="remove">Remove Option</span></div>');
										});
						jq('.optionBox').on('click', '.remove', function() {
							jq(this).parent().remove();
						});
					});
	//	jq(":input").inputmask();

	/* jq("#telefon").inputmask({
		"mask" : "(999) 999 9999"
	}); */
</script>
<script type="text/javascript">
	jq(function() {
		jq.mask.definitions['~'] = "[+-]";
		jq("#tarih").mask("99/99/9999", {
			completed : function() {
				alert("completed!");
			}
		});
		jq("#phone").mask("(999) 999-9999");
		//jq("#telefon").mask("(999) 999-9999? x99999");
		jq("#telefon").mask("9999 999 9999");
		jq("#tin").mask("99-9999999");
		jq("#ssn").mask("999-99-9999");
		jq("#telefon2").mask("a*-999-a999", {
			placeholder : " "
		});
		jq("#eyescript").mask("~9.99 ~9.99 999");
		jq("#po").mask("PO: aaa-999-***");
		jq("#pct").mask("99%");

		jq("#telefon").blur(
				function() {
					jq("#info").html("Girilen telefon no : " + jq(this).val())
							.addClass("alert alert-danger");
				})

		/* .dblclick(function() {
		jq(this).unmask();
		jq("#info").remove(); 
		});*/
	});
</script>

<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">


				<div class="page-header">
					<c:if test="${!empty message }">
						<c:if test="${message=='succeed'}">
							<div class="alert alert-info" role="alert">
								<p>Kayıt başarıyla girildi</p>
							</div>
						</c:if>
						<c:if test="${message=='failed'}">
							<div class="alert alert-danger " role="alert">
								<p>Kaydetme işlemi başarısız..</p>
							</div>
						</c:if>
					</c:if>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading text-center">
						<h3 class="panel-title">Kooperatif Tanımlama</h3>
					</div>
					<div class="panel-body">
						<form:form action="kooperatifEkle" commandName="kooperatif"
							autocomplete="off" class="form-horizontal" role="form"
							method="post">
							<form:hidden path="id" />
							<div class="form-group">
								<label class="col-sm-4 control-label" for="iletisimBilgileri">Ketagori</label>
								<div class="col-sm-5">
									<form:select class="form-control " id="kooperatifTurID"
										items="${turListesi}" itemLabel="turAdi" itemValue="id"
										path="kooperatifTurID.id" name="kooperatifTurID">
									</form:select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="kooperatifIlceID">Bulunduğu
									İlçe</label>
								<div class="col-sm-5">
									<form:select class="form-control " id="kooperatifIlceID"
										items="${ilceler }" itemLabel="isim" itemValue="id"
										path="kooperatifIlceID.id" name="kooperatifIlceID" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="kooperatifAdi">Kooperatif
									Adi</label>
								<div class="col-sm-5">
									<form:input class="form-control " id="kooperatifAdi"
										required="true" path="kooperatifAdi" name="kooperatifAdi" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="kooperatifAdi">Kooperatif
									Başkanı</label>
								<div class="col-sm-5">
									<form:input class="form-control " id="koopBask" required="true"
										path="koopBask" name="koopBask" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="uygulamaProjesi">Uygulama
									Projesi</label>
								<div class="col-sm-5">
									<form:input class="form-control " id="uygulamaProjesi"
										path="uygulamaProjesi" name="uygulamaProjesi" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="kurulusYili">Kuruluş
									Yılı</label>
								<div class="col-sm-5">
									<form:input class="form-control tarih" id="kurulusYili"
										type="date" required="true" path="kurulusYili"
										name="kurulusYili" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="intibakYili">İntibak
									Yılı</label>
								<div class="col-sm-5">
									<form:input type="date" class="form-control tarih "
										id="intibakYili" path="intibakYili" name="intibakYili" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="sonGenelKurulTarihi">Son
									Genel Kurul Tarihi</label>
								<div class="col-sm-5">
									<form:input type="date" class="form-control tarih"
										id="sonGenelKurulTarihi" path="sonGenelKurulTarihi"
										required="true" name="sonGenelKurulTarihi" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="ortakSayisi">Ortak
									Sayısı</label>
								<div class="col-sm-5">
									<form:input type="number" min="1" max="50" required="true"
										class="form-control " id="ortakSayisi" path="ortakSayisi"
										name="ortakSayisi" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="yonetimKuruluList">Yönetim
									Kurulu</label>
								<div class="col-sm-5">
									<c:if test="${kooperatif.id ne 0}">
										<c:set value="${kooperatif.yonetimKuruluList }" var="liste1"></c:set>
										<c:set var="listSize1" value="${fn:length(liste1)}" />
										<c:if test="${listSize1 ==1 }">
											<form:input class="form-control " id="yonetimKuruluList"
												path="yonetimKuruluList" name="yonetimKuruluList"
												value="${liste1[0]}" />
										</c:if>
										<c:if test="${listSize1 ==2 }">
											<form:input class="form-control " id="yonetimKuruluList"
												path="yonetimKuruluList" name="yonetimKuruluList"
												value="${liste1[0]},${liste1[1]}" />
										</c:if>
										<c:if test="${listSize1 ==3 }">

											<form:input class="form-control " id="yonetimKuruluList"
												path="yonetimKuruluList" name="yonetimKuruluList"
												value="${liste1[0]},${liste1[1]},${liste1[2]}" />

										</c:if>
										<c:if test="${listSize1 ==4 }">
											<form:input class="form-control " id="yonetimKuruluList"
												path="yonetimKuruluList" name="yonetimKuruluList"
												value="${liste1[0]},${liste1[1]},${liste1[2]},${liste1[3]}" />
										</c:if>
										<c:if test="${listSize1 ==5 }">
											<form:input class="form-control " id="yonetimKuruluList"
												path="yonetimKuruluList" name="yonetimKuruluList"
												value="${liste1[0]},${liste1[1]},${liste1[2]},${liste1[3]},${liste1[4]}" />
										</c:if>

										<c:if test="${listSize1 gt 5 }">
											<form:input class="form-control " id="yonetimKuruluList"
												path="yonetimKuruluList" name="yonetimKuruluList"
												value="${liste1[0]},${liste1[1]},${liste1[2]},${liste1[3]},${liste1[4]}" />
										</c:if>
									</c:if>
									<c:if test="${kooperatif.id eq 0}">

										<form:input class="form-control " id="yonetimKuruluList"
											path="yonetimKuruluList" name="yonetimKuruluList" />
									</c:if>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-5">
									<div class="optionBox">
										<div class="block">
											<input type="text" /> <span class="remove">Remove
												Option</span>
										</div>
										<div class="block">
											<input type="text" /> <span class="remove">Remove
												Option</span>
										</div>
										<div class="block">
											<a id="add">Add Option</a>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="denetimKuruluList">Denetim
									Kurulu</label>
								<div class="col-sm-5">
									<c:if test="${kooperatif.id ne 0}">
										<c:set value="${kooperatif.denetimKuruluList }" var="liste"></c:set>
										<c:set var="listSize" value="${fn:length(liste)}" />
										<c:if test="${listSize ==1 }">

											<form:input class="form-control " id="denetimKuruluList"
												path="denetimKuruluList" name="denetimKuruluList"
												value="${liste[0]}" />
										</c:if>
										<c:if test="${listSize ==2 }">

											<form:input class="form-control " id="denetimKuruluList"
												path="denetimKuruluList" name="denetimKuruluList"
												value="${liste[0]},${liste[1]}" />

										</c:if>
										<c:if test="${listSize ==3 }">

											<form:input class="form-control " id="denetimKuruluList"
												path="denetimKuruluList" name="denetimKuruluList"
												value="${liste[0]},${liste[1]},${liste[2]}" />

										</c:if>
										<c:if test="${listSize ==4 }">
											<form:input class="form-control " id="denetimKuruluList"
												path="denetimKuruluList" name="denetimKuruluList"
												value="${liste[0]},${liste[1]},${liste[2]},${liste[3]}" />
										</c:if>
										<c:if test="${listSize ==5 }">
											<form:input class="form-control " id="denetimKuruluList"
												path="denetimKuruluList" name="denetimKuruluList"
												value="${liste[0]},${liste[1]},${liste[2]},${liste[3]},${liste[4]}" />
										</c:if>
									</c:if>
									<c:if test="${kooperatif.id eq 0}">

										<form:input class="form-control " id="denetimKuruluList"
											path="denetimKuruluList" name="denetimKuruluList" />
									</c:if>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="iletisimBilgileri">İletişim
									Bilgileri</label>
								<div class="col-lg-8">

									<div class="col-md-4">
										<label class="col-sm-4 control-label" for="adres">Adres</label>
										<form:input class="form-control " id="adres" name="adres"
											path="adres" data-inputmask="'alias': 'date'"></form:input>
									</div>
									<div class="col-md-4">
										<label class="col-sm-4 control-label" for="telefon">Telefon</label>
										<form:input class="form-control " id="telefon" name="telefon"
											type="phone" path="telefon" placeholder="0555 123 4567 "
											data-valid-example="05/18"></form:input>
										<!--pattern="\+90\d{3}[\-]\d{3}[\-]\d{4}"  -->
										<div id="info"></div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-9 col-sm-offset-3">
									<button type="submit" class="btn btn-success pull-right "
										name="signup1" value="Kaydet">Kaydet</button>
								</div>
								<div class="col-sm-6">

									<button type="button" value="" class="btn btn-danger pull-left"
										onclick="javascript:history.back()">
										<span class="fa fa-reply"> Bir Önceki Sayfa </span>
									</button>






									<a type="button" href="./ilce-listesi"
										class="btn btn-primary pull-left ">İlce Listesine Git </a>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
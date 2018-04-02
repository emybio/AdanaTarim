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
<meta http-equiv="content-type"
	content="application/vnd.ms-excel; charset=UTF-8">
<title>JQuery-validation demo | Bootstrap</title>
<style type="text/css">
select {
	-webkit-appearance: none;
	-moz-appearance: none;
	text-indent: 1px;
	text-overflow: '';
}
</style>
<script type="text/javascript">
	var jq = jQuery.noConflict();
	var tableToExcel = (function() {
		var uri = 'data:application/vnd.ms-excel;base64,', template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8"><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>', base64 = function(
				s) {
			return window.btoa(unescape(encodeURIComponent(s)))
		}, format = function(s, c) {
			return s.replace(/{(\w+)}/g, function(m, p) {
				return c[p];
			})
		}
		return function(table, name) {
			if (!table.nodeType)
				table = document.getElementById(table)
			var ctx = {
				worksheet : name || 'Worksheet',
				table : table.innerHTML
			}
			window.location.href = uri + base64(format(template, ctx))
		}
	})() 
	function yillaraGoreGetir() {
		
		
		jq.ajax({
			type : "GET",
			url : "./filtreyeGoreGencCiftciListeGetir",
			data : {
				yil : jq("#yil").val(),
				ilce : jq("#ilce").val(),
				kategori : jq("#kategori").val()
			},
			success : function(gelen) {
				//console.log(gelen);
				var link="./filtreyeGoreGencCiftciListeGetir?yil="
					+ jq("#yil").val() + "&ilce=" + jq("#ilce").val()
					+ "&kategori=" + jq("#kategori").val();
				window.location.href= ('page2','Title',link);

			},
			error : function(xhr, textStatus, errorThrown) {
				alert(textStatus);
			}
		});

	}
	//select boxta seçilen değerin submittern sonra da seçili kalaması için script kodu;
	jq(document).ready(
			function() {
				jq("#yi").val("${requestScope.yillar}").attr('selected',
						'${requestScope.secilenYil}');
				
								
				jq("#filtreText").click(function(){
					var link="./filtreyeGoreGencCiftciListeGetir?yil=&ilce=&kategori=" ;
					window.location.href= ('page2','Title',link);
				});
				
			});
	
	
	
	
	function ilceyeVeKategoriyeGoreKayitSayisi(ilce,kategori,id) {
		 {
			 var asd= "#"+id+kategori;
			console.log("id gosterimi : " + asd)
			
			jq.ajax({
				type : "GET",
				url : "./ilceyeVeKategoriyeGoreKayitSayisi",
				dataType : "JSON",
				contentType : "application/x-www-form-urlencoded;charset=UTF-8",
				data : {
					kategori : kategori,
					ilce : ilce,
				},
				 beforeSend:function() { 
				//	 jq("#raporTable").html("<tr><td>İçerik Yükleniyor....</td></tr>");
			     },
				success : function(gelen) { 
					console.log(gelen);
					jq("#"+id+kategori).text(gelen);
				},
				error : function(xhr, textStatus, errorThrown) {
				//	alert(textStatus);
				}
			});
		}
	}


</script>



</head>
<body>

	<%-- <div class="row">
			<div class="pull-right hidden">
				<h3>
					<input name="foo" value="${fn:escapeXml(param.yil)}">
				</h3>
			</div>
			
		</div> --%>
	<div class="col-sm-10 col-sm-offset-1">
		<div class="page-header">&nbsp;</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">

					Genç Çiftçi Listesi <a href="./genc-ciftci" class="pull-right">
						<span class="glyphicon glyphicon-plus"></span> YENİ KAYIT
					</a>

				</h3>
			</div>
			<div class="panel-body">
				<div style="overflow-x: auto;">

					<table class="table table-hover table-border">
						<tr>
							<td colspan="8"><input class="form-control" id="filtreText"
								style="font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;"
								value="${fn:escapeXml((param.ilce== '' or param.ilce== null or  empty param.ilce) and (param.kategori== '' or param.kategori== null or empty param.kategori) and (param.yil == '' or param.yil == null or empty param.yil) ? 'FİLTRE' : 'FİLTREYİ TEMİZLE')}${fn:escapeXml(param.ilce == '' ? '' : ':')}${fn:escapeXml(param.ilce)}${fn:escapeXml(param.kategori=='' ? '' : ':')}${fn:escapeXml(param.kategori)}${fn:escapeXml(param.yil ==null|| param.yil =='' ? '' : ':')}${fn:escapeXml(param.yil)}"
								readonly="readonly"></td>
						</tr>
						<tr>
							<td colspan="3"><select class="form-control" name="ilce"
								id="ilce" onchange="yillaraGoreGetir();">
									<option value="">İlçeye Göre Filtrele</option>
									<c:forEach items="${ilceListesi}" var="ilce">
										<option value="${ilce.isim}"
											${requestScope.secilenIlce == ilce.isim? 'selected' : ''}>${ilce.isim }</option>
									</c:forEach>
							</select></td>
							<td colspan="3"><select class="form-control" name="kategori"
								id="kategori" onchange="yillaraGoreGetir();">
									<option value="">Kategoriye Göre Filtrele</option>
									<c:forEach items="${kategoriListesi}" var="kategori">
										<option value="${kategori.isim}"
											${requestScope.secilenKategori == kategori.isim? 'selected' : ''}>${kategori.isim }</option>
									</c:forEach>
							</select></td>
							<td colspan="1"><select class="form-control" name="yil"
								id="yil" onchange="yillaraGoreGetir();">
									<c:set var="sel" value='selected = "selected"' />
									<option value="">Yıllara Göre Filtrele</option>
									<c:forEach items="${yillar}" var="yil">
										<option value="${yil}"
											${requestScope.secilenYil == yil ? 'selected' : ''}>${yil }</option>
									</c:forEach>
							</select></td>
							<td colspan="1"><a class="btn btn-success pull-right"
								href="#" onclick="tableToExcel('testTable', 'Rapor')">Listeyi
									Excel'e Aktar</a></td>
						</tr>
					</table>
					<table
						class="table table-sm table-bordered table-hover bg-default "
						id="testTable">
						<%-- <caption>Genç Çiftçi Listesi</caption>
								<colgroup align="center">
									<col span="2" style="background-color: red">
									<col style="background-color: yellow">
								</colgroup>
								<colgroup align="left">
									<col span="2" style="background-color: red">
									<col style="background-color: yellow">
								</colgroup>
								<colgroup span="2" align="center">
									<col span="2" style="background-color: red">
									<col style="background-color: yellow">
								</colgroup>
								<colgroup span="3" align="center">
									<col span="2" style="background-color: red">
									<col style="background-color: yellow">
								</colgroup> --%>
						<thead valign="top">
							<c:set var="t" value="0"></c:set>
							<c:set var="x" value="0"></c:set>
							<tr>
								<td colspan="4"><b>${fn:length(gencCiftci)} </b>adet kayıt</td>
								<td colspan="4"><c:forEach items="${gencCiftci}" var="genc"
										varStatus="i">
${genc.mahalle.tip.id==11 ? x:x+1}
										<c:set var="t" value="${t+genc.hibeTutari }"></c:set>
									</c:forEach> <fmt:setLocale value="tr_TR" /> <fmt:formatNumber
										pattern="#,##0.00" type="currency" value="${t}" var="t"></fmt:formatNumber>

									${t }&&&&${x }</td>
							</tr>
							<tr class="baslik">
								<%-- <td align="center"></td> --%>
								<th align="center">Yararlanıcı</th>
								<th align="center">Proje Konusu</th>
								<th align="center">Uygulama Yeri</th>
								<th align="center">Kapasite</th>
								<th align="center">Hibe Tutarı</th>
								<th align="center">Yıl</th>

								<th align="center" colspan="2">Sil / Güncelle</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${gencCiftci}" var="genc" varStatus="i">
								<tr>
									<td>${i.count}.${genc.yararlaniciAdi }&nbsp;${genc.yararlaniciSoyadi }</td>


									<c:if
										test="${empty genc.kategori.tip.tip.isim and empty genc.kategori.tip.isim}">

										<td>${genc.kategori.isim}</td>

									</c:if>
									<c:if
										test="${empty genc.kategori.tip.tip.isim and !empty genc.kategori.tip.isim}">

										<td>${genc.kategori.tip.isim}-${genc.kategori.isim}</td>

									</c:if>
									<c:if
										test="${!empty genc.kategori.tip.tip.isim and !empty genc.kategori.tip.isim}">

										<td>${genc.kategori.tip.tip.isim}-${genc.kategori.tip.isim}-${genc.kategori.isim}</td>

									</c:if>
									<td>${genc.mahalle.tip.tip.isim}-${genc.mahalle.tip.isim}-${genc.mahalle.isim}</td>
									<td>${genc.kapasite}-<span class="text-capitalize">${genc.kapasiteBirim}</span>
									</td>
									<td><fmt:setLocale value="tr_TR" /> <fmt:formatNumber
											pattern="#,##0.00" type="currency" value="${genc.hibeTutari}"
											var="hibeTutari"></fmt:formatNumber> ${hibeTutari}</td>
									<td>${genc.yil}</td>

									<td><a
										href="${pageContext.request.contextPath }/kirsal-kalkinma/gencCiftciSil?id=${genc.id}"
										onclick="javascript:return confirm('${genc.yararlaniciAdi} ${ genc.yararlaniciSoyadi } isimli kaydı : \n Silmek İstediğinize Emin misiniz?');"
										class="btn btn-danger btn-sm form-control">Sil</a></td>
									<td><a
										href="${pageContext.request.contextPath }/kirsal-kalkinma/gencCiftciGuncelle/${genc.id}"
										class="btn btn-primary btn-sm form-control">Güncelle</a></td>
								</tr>
							</c:forEach>
							<c:if test="${empty gencCiftci}">
								<tr>
									<td colspan="8"
										style="width: 50em; line-height: 50em; text-align: center;">
										<h1>GÖSTERİLECEK KAYIT YOK</h1>
									</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>



		<div class="page-header">&nbsp;</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">DENEME</h3>
			</div>
			<div class="panel-body">
				<div style="overflow-x: auto;">
					<c:set var="toplam" value="${0}"></c:set>
					<c:set var="string" value=""></c:set>

					<table class="table table-hover table-border" id="raporTable">
						<tr>
							<td></td>
							<c:forEach items="${kategoriListesi}" var="kategori">
								<td>${kategori.isim}</td>
							</c:forEach>
						</tr>

						<c:forEach items="${ilceListesi}" var="ilce">

							<tr>
								<td>${ilce.isim}</td>
								<c:forEach items="${kategoriListesi}" var="kategori"
									varStatus="x">

									<c:forEach items="${gencCiftci}" var="genc" varStatus="g">
										<c:set
											value="${genc.mahalle.tip.id eq 11 and genc.kategori.id eq 1 }"
											var="deger" />

										<c:if test="${deger eq true}">


											<c:set var="toplam" value="${toplam+1}"></c:set>

											<c:set var="string" value="${kategori.isim}"></c:set>
											<!-- <script type="text/javascript">
													ilceyeVeKategoriyeGoreKayitSayisi('${ilce.id}','${kategori.id}','${toplam}');
													</script> -->

										</c:if>
									</c:forEach>

									<td><span id="${toplam}${kategori.id}"> <%-- ${string}--${x.last}-${toplam} --%>
									</span></td>
								</c:forEach>
							</tr>
						</c:forEach>
						${toplam}

					</table>

				</div>
			</div>
		</div>
	</div>
</body>
</html>
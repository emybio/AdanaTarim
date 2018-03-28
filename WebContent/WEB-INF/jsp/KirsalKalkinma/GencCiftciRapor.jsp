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
						<div style="overflow-x: auto;">
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
										<td>${genc.kapasite}-<span class="text-capitalize">${genc.kapasiteBirim}</span>
										</td>
										<td>${genc.hibeTutari}</td>
										<td>${genc.yil}</td>
										<td>Devam</td>
										<td><a
											href="${pageContext.request.contextPath }/kirsal-kalkinma/gencCiftciSil?id=${genc.id}"
											onclick="javascript:return confirm('${genc.yararlaniciAdi} ${ genc.yararlaniciSoyadi } isimli kaydı : \n Silmek İstediğinize Emin misiniz?');"
											class="btn btn-danger btn-sm">Sil</a></td>
										<td><a
											href="${pageContext.request.contextPath }/kirsal-kalkinma/gencCiftciGuncelle/${genc.id}"
											class="btn btn-primary btn-sm">Güncelle</a></td>
								</c:forEach>

							</table>

						</div>


					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
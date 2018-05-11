<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<style type="text/css">
td a:link {
	display: block;
	text-decoration: none;
	height: 100%;
}

td a:hover {
	
}
</style>
</head>
<body>
	<br>
	<div class="container-fluid">
		<div class="container">
			<div class="col-sm-6 col-sm-offset-2 ">
				<div class="pull-left">

					<form class="navbar-form navbar-right" action="./koopBul">
						<h3>
							<input type="text" class="form-control"
								placeholder="Kooperatif bul..." name="koopAdi">
						</h3>
					</form>

				</div>
				<div class="pull-right">
					<h3>
						<a href="./kooperatifTanimlama" class="btn btn-success btn-xs">
							<span class="glyphicon glyphicon-plus"></span> YENİ KAYIT
						</a>
					</h3>
				</div>
			</div>
		</div>
		<div class="container col-sm-offset-4">
			<table class="bg-default " style="border-radius: 5em 5em 5em 5em">
				<thead style="background-color: rgba(255, 255, 255, .5)">
					<tr>
						<th class="col-sm-4 text-center"
							style="font-family: 'Times New Roman', Helvetica, Arial, sans-serif !important; border-top-left-radius: 5em; border-top-right-radius: 5em;"><h3>İLÇE
								LİSTESİ</h3></th>
					</tr>

				</thead>
				<tbody>
					<c:if test="${!empty ilceler}">
						<c:forEach items="${ilceler}" var="ilce" varStatus="sira">
							<tr>
								<td><a class="btn btn-info btn-lg  "
									href="./ilceyeGoreKooperatifTurleri?id=${ilce.id}&isim=${ilce.isim}">${ilce.isim}</a></td>
							</tr>
						</c:forEach>

					</c:if>


					<c:if test="${empty ilceler}">
						<tr>
							<td class="text-center"><h1>GÖSTERİLECEK KAYIT YOK !</h1></td>
						</tr>

					</c:if>

				</tbody>
				<tfoot style="background-color: rgba(255, 255, 255, .5)">
					<tr>
						<td
							style="width: 30%; font-family: 'Times New Roman', Helvetica, Arial, sans-serif !important; border-bottom-left-radius: 5em; border-bottom-right-radius: 5em;">
							<h3>&nbsp;</h3>

						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</body>
</html>
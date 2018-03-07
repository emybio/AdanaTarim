<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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


	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<hr>
				<table class="table table-hover bg-info "
					style="border-radius: 5em 5em 5em 5em">
					<thead style="background-color: rgba(255, 255, 255, .5)">
						<tr>
							<th class="col-sm-4 text-center"
								style="width: 30%; font-family: 'Times New Roman', Helvetica, Arial, sans-serif !important; border-top-left-radius: 5em; border-top-right-radius: 5em;"><h3>Kooperatif
									Listesi</h3></th>
						</tr>

					</thead>
					<tbody>

						<c:forEach items="${kooperatif }" var="kooperatif"
							varStatus="sira">

							<tr>
								<td>
									<div class="col-lg-10">
										<div class="col-lg-8">
											<a class="btn btn-info btn-xs"
												href="./kooperatifKarti?id=${kooperatif.id}">${kooperatif.kooperatifAdi}</a>
										</div>
										<div class="btn-group btn-group-xs col-sm-offset-2 pull-right">
											<a class="btn btn-warning btn-md  disabled"
												href="./kooperatifDuzenle/${kooperatif.id}">Düzenle</a> <a
												class="btn btn-danger btn-md  disabled"
												onclick="return confirm('Silmek İstediğinize Emin misiniz?');"
												href="./kooperatifSil?id=${kooperatif.id}">Sil</a>
										</div>
									</div>
								</td>

							</tr>

						</c:forEach>

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
	</div>
</body>
</html>
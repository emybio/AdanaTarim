<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
			<div class="row">
				<div class="col-sm-8 col-sm-offset-2">
					<hr>
					<table class=" bg-default " style="border-radius: 5em 5em 5em 5em">

						<thead style="background-color: rgba(255, 255, 255, .5)">
							<tr>
								<th class="col-sm-4 text-center"
									style="width: 30%; font-family: 'Times New Roman', Helvetica, Arial, sans-serif !important; border-top-left-radius: 5em; border-top-right-radius: 5em;"><h3>${ilceler }&nbsp;İlçesinde&nbsp;Yer&nbsp;Alan&nbsp;
										Kooperatif&nbsp; Türleri</h3></th>
							</tr>

						</thead>
						<tbody>
							<c:forEach items="${kooperatif }" var="kooperatif"
								varStatus="sira">
								<c:set var="koop" value="${kooperatif}"></c:set>
								<tr>
									<td><a class="btn btn-info btn-lg "
										href="./tureVeIlceyeGoreKooperatifler?id=${kooperatif.id}&ilce_id=${ilceler }">${kooperatif.turAdi}
											Kooperatifi</a></td>

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
	</div>

</body>
</html>
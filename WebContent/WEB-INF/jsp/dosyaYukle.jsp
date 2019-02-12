<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${title}</title>
<script src="<c:url value="/assets/js/getrequest.js" />"></script>
<script src="<c:url value="/assets/js/postrequest.js" />"></script>
<script type="text/javascript">
	var url = "${pageContext.request.contextPath}"

	
	
	// DO DELETE
	function ajaxDelete(id){
		if (confirm(jq("#"+id+" a").first().text()+"silinsin mi ? " )) {
		jq.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			data : {
				id : id
			},
			url : "./api/file/delete/"+id,
			success: (data) => {
				jq("#"+id).fadeOut("slow");
				
			/*	if(	 jq("#listFiles").html("")){
					
					 jq("#btnGetFiles").show("2000");
					 jq("#btnHideFiles").hide("2000");
					
					
				};*/
				
				
			},
			error : (err) => {
				
				jq("#listFiles").html(err.responseText);
			}
		});	}
	} 
</script>

</head>

<body>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<div class="container col-sm-offset-4">
		<div class="row ">
			<div class="col-sm-6">
				<h2>SİSTEME DOSYA YÜKLE</h2>
				<form method="POST" enctype="multipart/form-data"
					id="fileUploadForm">
					<div class="form-group">
						<label class="control-label" for="uploadfile">Yüklenecek
							Dosya:</label> <input type="file" class="form-control" id="uploadfile"
							name="uploadfile"></input>
					</div>

					<button type="submit" class="btn btn-success" id="btnSubmit">Yükle</button>
					<button type="button" class="btn btn-warning" id="btnGetFiles">Dosyaları
						Listele</button>
					<button type="button" class="btn btn-warning" id="btnHideFiles"
						style="display: none;">Listeyi Kaldır</button>
				</form>
				<hr />

			</div>
		</div>
		<div class="row ">
			<div class="container-fluid col-sm-6">
				<table>

				</table>
			</div>
		</div>

		<table class="table" id="listFile">
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>

		</table>

	</div>



</body>
</html>
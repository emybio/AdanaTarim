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

	console.log(url);
</script>
</head>

<body>
	<br>

	<div class="container col-sm-offset-4">
		<div class="row ">
			<div class="col-sm-6">
				<h2>Upload MultipartFile to PostgreSQL</h2>
				<form method="POST" enctype="multipart/form-data"
					id="fileUploadForm">
					<div class="form-group">
						<label class="control-label" for="uploadfile">Upload File:</label>
						<input type="file" class="form-control" id="uploadfile"
							name="uploadfile"></input>
					</div>

					<button type="submit" class="btn btn-success" id="btnSubmit">Yükle</button>
					<button type="button" class="btn btn-warning" id="btnGetFiles">Dosyaları
						Listele</button>
				</form>
				<hr />
				<div id="listFiles"></div>
			</div>
		</div>
	</div>



</body>
</html>
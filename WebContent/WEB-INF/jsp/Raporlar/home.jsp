<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring MVC PDF View Demo (iText)</title>
</head>
<body>
	<div align="center">
		<h1>Spring MVC PDF View Demo (using iText library)</h1>
		<h3>
			<a href="./downloadPDF" target="blank">Download PDF Document</a>
		</h3>
		<form id="myForm" action="./pdfExport" method="POST">
			<input type="submit" value="PDF export">
		</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><style>
.center {
	text-align: center;
	margin-left: auto;
	margin-right: auto;
	margin-bottom: auto;
	margin-top: auto;
}
</style>

<div class="container">
	<div class="row">
		<div class="span12">
			<div class="panel hero-unit center">
				<h1>
					HATA <small><font face="Tahoma" color="red"> 404</font></small>
				</h1>
				<br />
				<h2>Hata Mesajı:</h2>

				<p>${errors }</p>
				<p>
					<b>Or you could just press this neat little button:</b>
				</p>
				<a href="#" class="btn btn-large btn-info"><i
					class="icon-home icon-white" onclick="window.history.back();"></i>
					Önceki Sayfaya Dön</a>
			</div>
			<br />
		</div>
	</div>
</div>

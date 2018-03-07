<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
input {
	width: 100px;
}

select {
	outline: none !important;
}

#date {
	width: 10em;
}

.alert1 {
	outline-color: red;
	border-color: red;
	display: none;
}

.uyariYazisi {
	font: bold;
	color: red;
}

.pic_ina {
	height: 90px;
	width: 30%;
	float: left;
}

.con_ina {
	height: 100px;
	width: 67%;
	margin-left: 3%;
	float: left;
}

.btn_inr {
	float: right;
	margine-top: 25px;
}
</style>
<script type="text/javascript">
function talepsil(id) {
	if (confirm("Sileyim mi " + id)) {
		// jq('#satirno'+id).remove();
		jq.ajax({
			type : "POST",
			url : "./talepSil",
			dataType : "JSON",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			data : {
				id : id
			},
			success : function(gelen) { // vtden silmesi için ne yaptın
				// alert("buraya kadar hata olacaK mı?");
				jq('.satirno' + id).remove();
			},
			error : function(xhr, textStatus, errorThrown) {
				alert(textStatus);
			}
		});
	}
}



</script>
<div class="container">
	<div class="container">


		<div class="col-lg-8">
			<h3>Talep Ekle</h3>
			<div class="table-responsive">
				<table class="table table-striped bg-info"
					style="text-align: center;">
					<thead style="text-align: center; height: 300px;">
						<tr>
							<th colspan="2" style="text-align: center;">TALEP EDİLEN</th>
							<th rowspan="2" style="text-align: center;">TARİH</th>
						</tr>
						<tr>
							<th style="text-align: center;">İLÇE</th>
							<th style="text-align: center;">MAHALLE</th>
						</tr>
					</thead>
					<form:form commandName="aracTalep" method="post"
						action="aracTalepEkle">
						<form:hidden path="id" />
						<tr>

							<!--ilce-->
							<td>
								<div class="ilce">
									<form:select path="ilce.id" id="slctAltTip"
										class="form-control btn bg-info"
										onChange="ikisibiradamarkala(this.value)">
										<form:option value=""></form:option>
										<form:options items="${ilceListesi}" itemValue="id"
											itemLabel="isim" />
									</form:select>
								</div>
							</td>
							<td>
								<div class="mahalle">
									<form:select path="mahalle.id" id="slctMarka"
										class="form-control btn bg-info mahalleSec">
										<form:options items="${markaListesi}" itemValue="id"
											itemLabel="isim" />
									</form:select>
									<span class="uyariYazisi"></span>
								</div>
							</td>
							<td style="size: 250px;"><div class="tarih">
									<form:input path="tarih" type="date" onkeydown="return false"
										class="form-control col-lg-12 " id="date" />
									<span class="uyariYazisi"></span>
								</div></td>
						</tr>
						<tr>
							<c:if test="${tusYazisi=='Kaydet' }">
								<td colspan="9" align="right"><input type="submit"
									class="btn btn-primary pull-right btn-lg" value="${tusYazisi}" /></td>
							</c:if>
							<c:if test="${tusYazisi=='Güncelle' }">
								<td colspan="5" align="right"><input type="submit"
									class="btn btn-primary btn-block" value="${tusYazisi}" /></td>
								<td colspan="4"><input type="button"
									class="btn btn-danger btn-block"
									onclick="javascript:location.href='./vazgecTalep' "
									value="Vazgeç"></td>
							</c:if>
						</tr>
					</form:form>
				</table>
			</div>
		</div>


		<div class="col-sm-6 col-md-4 col-lg-8">
			<h3 class="sub-header">Araç Talep Listesi</h3>
			<div class="table-responsive">
				<table class="table table-striped bg-info" id="aracListesi">
					<thead>
						<tr>
							<th>Sil</th>
							<th>Edit</th>
							<th>#</th>
							<th width="150px" id="goster">Talep Eden</th>
							<th>Gidilecek Yer</th>
							<th>Tarih</th>
							<th>Durum</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${aracTalepListesi}" var="talep"
							varStatus="sira">

							<c:if test="${talep.isActive eq true }">
								<tr class="satirno${talep.id} ${talep.isActive} ">
									<td><img
										src="<c:url value="/assets/images/Delete-32.png" />"
										width="21px" onclick="talepsil(${talep.id})"
										title="Silmek İçin Tıklayın" /></td>
									<td><a href="./talepDuzenle/${talep.id}"><img
											src="<c:url value="/assets/images/duzenle.png" />"
											width="21px" title="Değiştirmek İçin Tıklayın" /></a></td>
									<td>${sira.count }</td>
									<td>${talep.islemyapan.adi}</td>
									<td>${talep.ilce.isim}-${talep.mahalle.isim}</td>
									<td><fmt:parseDate value="${talep.tarih}"
											pattern="yyyy-mm-dd" var="tarih" /> <fmt:formatDate
											value="${tarih}" pattern="dd/mm/YYYY EEEE" dateStyle="full" /></td>

									<c:if test="${talep.isActive eq true}">

										<td align="center"><img
											src="<c:url value="/assets/images/142.gif" />" width="50px"
											title="İşlem Beklemede..." /></td>
									</c:if>

									<c:if test="${talep.isActive eq false}">

										<td align="center"><img
											src="<c:url value="/assets/images/ok.png" />" width="15px"
											title="İşlem Tamamlanı !" /></td>
									</c:if>

								</tr>

							</c:if>

						</c:forEach>
						<tr>
							<td>Geçmiş Talepler</td>
						</tr>

						<c:forEach items="${aracTalepListesi}" var="talep"
							varStatus="sira">

							<c:if test="${talep.isActive eq false }">

								<script>

					jq(document).ready(function(){
						
				jq(".false").css({'background-color':'#ececec','color':'#b9b9b9','text-decoration': 'line-through'}); 
					/* 	jq(".false").addClass('danger'); */
							/* jq(".false td").addClass('text-primary'); */
							jq(".false a").css({'pointer-events':'none','cursor':'default'});
							jq(".false img").css({'pointer-events':'none','cursor':'default'});
	
										});
								</script>
								<tr class="satirno${talep.id} ${talep.isActive} ">
									<td><img
										src="<c:url value="/assets/images/Delete-32.png" />"
										width="21px" onclick="talepsil(${talep.id})"
										title="Silmek İçin Tıklayın" /></td>
									<td><a href="./talepDuzenle/${talep.id}"><img
											src="<c:url value="/assets/images/duzenle.png" />"
											width="21px" title="Değiştirmek İçin Tıklayın" /></a></td>
									<td>${sira.count }</td>
									<td>${talep.islemyapan.adi}</td>
									<td>${talep.ilce.isim}-${talep.mahalle.isim}</td>
									<td><fmt:parseDate value="${talep.tarih}"
											pattern="yyyy-mm-dd" var="tarih" /> <fmt:formatDate
											value="${tarih}" pattern="dd/mm/YYYY EEEE" dateStyle="full" /></td>

									<c:if test="${talep.isActive eq true}">

										<td align="center"><img
											src="<c:url value="/assets/images/142.gif" />" width="50px"
											title="İşlem Beklemede..." /></td>
									</c:if>

									<c:if test="${talep.isActive eq false}">

										<td align="center"><img
											src="<c:url value="/assets/images/ok.png" />" width="15px"
											title="İşlem Tamamlanı !" /></td>
									</c:if>

								</tr>





							</c:if>

						</c:forEach>





					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

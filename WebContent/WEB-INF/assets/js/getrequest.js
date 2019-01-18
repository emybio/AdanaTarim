var jq = jQuery.noConflict();

jq( document ).ready( () => {
	 jq("#btnHideFiles").hide();
	 
	 
if(	 jq("#listFiles").html("")){
	
	 jq("#btnGetFiles").show("2000");
	 jq("#btnHideFiles").hide("2000");
	
};
	 
	 
	jq("#btnHideFiles").click( (event) => {
		 event.preventDefault();
		 jq("#listFile").fadeOut("slow").html("");
		 jq("#btnGetFiles").removeClass("disabled").text("Dosyaları Listele").show("2000");
		 jq("#btnHideFiles").hide("2000");
	});

	
	// var url= "${pageContext.request.contextPath}"
	// var url = window.location;
	// var contextPath='<%=request.getContextPath()%>';
	
	
	// GET REQUEST
		jq("#btnGetFiles").click( (event) => {
	 event.preventDefault();
	
	 jq("#btnGetFiles").text("Dosyalar listeleniyor, bekleyiniz...").addClass("disabled");
	
		ajaxGet();
	});
	
	// DO GET
	function ajaxGet(){
		alert("get request..");
		jq.ajax({
			type : "GET",
			dataType: "json",	
			url : "./api/file/all",
			success: (data) => {
				// clear old data
			jq("#listFile").html("");
			
				/*
				 * render list of files
				 */
				jq.each(data, (index, file) => {
				
					jq("#listFile").append('<tr id="'+file.id+'"><td><a  href=' +url +'/api/file/' + file.id +'>' + file.dosyaAdi + ' &nbsp;&nbsp; <i class="fa fa-download" aria-hidden="true"></i></a></td><td><a   href=javascript:ajaxDelete('  + file.id +')>SİL</td></tr>').fadeIn("easing");
				});
				
				jq("#listFile").addClass("table table-striped")
				
				 jq("#btnGetFiles").hide("2000");
				 jq("#btnHideFiles").show("2000");
			},
			error : (err) => {
				
				jq("#listFiles").html(err.responseText);
			}
		});	
	}  
	
		// "<tr id='satirNo'"+file.id+"><td><a
		// style='text-decoration:none;font-size:20px;' href=" +url
		// +"/api/file/" + file.id +">" + file.dosyaAdi + " &nbsp;&nbsp; <i
		// class='fa fa-download' aria-hidden='true'></i></a></td></tr>"
	
 
	
	
	
	
	
	
})        
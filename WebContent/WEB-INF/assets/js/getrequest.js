var jq = jQuery.noConflict();

jq( document ).ready( () => {
	
	// var url= "${pageContext.request.contextPath}"
	// var url = window.location;
	// var contextPath='<%=request.getContextPath()%>';
	
	// GET REQUEST
	jq("#btnGetFiles").click( (event) => {
		event.preventDefault();
		ajaxGet();
	});
	
	// DO GET
	function ajaxGet(){
		jq.ajax({
			type : "GET",
			dataType: "json",	
			url : "./api/file/all",
			success: (data) => {
				// clear old data
			jq("#listFiles").html("");
				
				/*
				 * render list of files
				 */
				jq("#listFiles").append('<ul style="background-color:blue;">');
				jq.each(data, (index, file) => {
					
					jq("#listFiles").append('<li><a style="text-decoration:none;font-size:20px;" href=' +url +'/api/file/' + file.id	 +'>' + file.dosyaAdi + '    &nbsp;&nbsp; <i class="fa fa-download" aria-hidden="true"></i></a></li>');
				});
				jq("#listFiles").append('</ul>');
				
				jq("#btnGetFiles").text("Listeyi Kaldır");
			},
			error : (err) => {
				
				jq("#listFiles").html(err.responseText);
			}
		});	
	}  
})        
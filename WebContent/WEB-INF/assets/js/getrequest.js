var jq = jQuery.noConflict();

jq( document ).ready( () => {
	 jq("#btnHideFiles").hide();
	 
	 
if(	 jq("#listFiles").html("")){
	
	 jq("#btnGetFiles").show("2000");
	 jq("#btnHideFiles").hide("2000");
	
};
	 
	 
	jq("#btnHideFiles").click( (event) => {
		 event.preventDefault();
		 jq("#listFiles").fadeOut("slow").html("");
		 jq("#btnGetFiles").show("2000");
		 jq("#btnHideFiles").hide("2000");
	});

	
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
				jq.each(data, (index, file) => {
				
					jq("#listFiles").hide().append('<tr id="'+file.id+'"><td><a style="text-decoration:none;font-size:20px;" href=' +url +'/api/file/' + file.id +'>' + file.dosyaAdi + ' &nbsp;&nbsp; <i class="fa fa-download" aria-hidden="true"></i></a></td><td><a  style="text-decoration:none;font-size:20px;" href=javascript:ajaxDelete('  + file.id +')>SİL</td></tr>').fadeIn("easing");
				});
				// jq("#listFiles").append('</table>');
				
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
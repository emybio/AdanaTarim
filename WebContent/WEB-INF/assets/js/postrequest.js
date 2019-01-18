var jq = jQuery.noConflict();
jq(document).ready( () => {
	jq("#btnSubmit").click((event) => {
        // stop submit the form, we will post it manually.
        event.preventDefault();
        doAjax();
    });
 
});
/*GEÇİCİ OLARAK KOYULDU*/
//DO GET
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
			
				jq("#listFiles").append('<tr id="'+file.id+'"><td><a style="text-decoration:none;font-size:20px;" href=' +url +'/api/file/' + file.id +'>' + file.dosyaAdi + ' &nbsp;&nbsp; <i class="fa fa-download" aria-hidden="true"></i></a></td><td><a  style="text-decoration:none;font-size:20px;" href=javascript:ajaxDelete('  + file.id +')>SİL</td></tr>').fadeIn("easing");
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
 
function doAjax() {
	jq("#btnSubmit").prop('disabled',true);
    // Get form
    var form = jq('#fileUploadForm')[0];
    var data = new FormData(form);
    var $el = jq('#uploadfile');
    ajaxGet()
	
    jq.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "./upload",
        data: data,
        processData: false, // prevent jQuery from automatically transforming
							// the data into a query string
        contentType: false,
        cache: false,
        success: (data) => {
        	console.log("Data : "+data)
        	jq("#listFiles").text(data);
        	jq("#btnSubmit").prop('disabled',false);
        	
        	// upload edildikten sonra seçili dosyayı inputtan siler
        	jq('#uploadfile').wrap('<form>').closest('form').get(0).reset();
        	
        	// resetlendikten sonra sayfayı refresh etmeden yeni dosya yüklemek
			// için kod.Bunu yazmazsak eğer
        	// dosya seçsek bile submit yaptığımızda hata alırız.
        	console.log("**************");
        	// jq("#btnGetFiles").removeClass("disabled").text("Dosyaları Listele").show("2000");
        	console.log("/////////////");
        //	 jq("#btnHideFiles").hide("2000");
            $el.unwrap();
        }, 
        
        error: (e) => {
        	jq("#listFiles").text(e.responseText);
        	jq("#btnSubmit").prop('disabled',false);
        }
    });
}
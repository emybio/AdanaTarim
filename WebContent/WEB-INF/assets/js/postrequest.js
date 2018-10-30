var jq = jQuery.noConflict();
jq(document).ready( () => {
	jq("#btnSubmit").click((event) => {
        // stop submit the form, we will post it manually.
        event.preventDefault();
        doAjax();
    });
 
});
 
function doAjax() {
	jq("#btnSubmit").prop('disabled',true);
    // Get form
    var form = jq('#fileUploadForm')[0];
    var data = new FormData(form);
    var $el = jq('#uploadfile');
    
	
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
        
        	jq("#listFiles").text(data);
        	jq("#btnSubmit").prop('disabled',false);
        	
        	//upload edildikten sonra seçili dosyayı inputtan siler
        	jq('#uploadfile').wrap('<form>').closest('form').get(0).reset();
        	
        	//resetlendikten sonra sayfayı refresh etmeden yeni dosya yüklemek için kod.Bunu yazmazsak eğer 
        	//dosya seçsek bile submit yaptığımızda hata alırız. 
            $el.unwrap();
        }, 
        
        error: (e) => {
        	jq("#listFiles").text(e.responseText);
        	jq("#btnSubmit").prop('disabled',false);
        }
    });
}
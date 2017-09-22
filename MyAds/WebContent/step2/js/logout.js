/**
 * 
 */
$(document).ready(function(e){
	$("#btn_logOut").click(function(e){
		$.ajax({
			type:"GET",
			url:"../step2/logout_admin.adm",
			success:function(dat){
				console.log(dat);
				if (dat=="true"){
					window.location.href="../step2/";
				}else{
					
				}
				
			}
		});
	});
	$("#post_view").click(function(e){
		$.ajax({
			type:"GET",
			url:"../step2/count_newPosting.adm",
			success:function(dat){
				$("#countNewPosting").text('['+dat+']');								
			}
		});
	});
});
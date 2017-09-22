$(document).ready(function(e) {
	// Area(Add_slide) when click to browse img
	 $('body').delegate('.wrap_img','click',function(){
	  $('.btn_upload').click();
	 });

	 // Preview Image when we select image already
	 $('body').delegate('#btn_uploadimg','change',function(e){
	  var files = this.files;
	  /*num  = files.length;
	  if(num==1){
	   $('.wrap_img').addClass('oneimg');
	  }else if(num==2){
	   $('.wrap_img').addClass('twoimg');
	  }else if(num>=3){
	   $('.wrap_img').addClass('morethentwoimg');
	  }*/
	  var files = e.target.files;
	  $('.wrap_img').children('img').remove();
	  //$.each(files,function(i,file){ // Open for upload multiple image
	   var reader = new FileReader();
	   //reader.readAsDataURL(file); // use when upload multipe image
	   reader.onload = function(e){
	    var tem = '<img src="'+e.target.result+'">';
	    $('.wrap_img').append(tem);
	   }
	  //});
	  reader.readAsDataURL(files[0]);
	 });
});
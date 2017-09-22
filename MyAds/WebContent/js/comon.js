$(document).ready(function(e) {
	$('.nav .storecate a.allcate').removeClass('on');
	$('.nav .storecate a.allcate').click(function(e) {
		$(this).toggleClass('on');
	});
	
	$('.btn_gotop').click(function(e) {
		$('html, body').animate({scrollTop : 0},800);
		return false;
	});
	
	
	//2015-12-03
	var $this;
	// Area(Add_slide) when click to browse img
	 $('body').delegate('.wrap_img','click',function(){
		 $this	=	$(this);
	 // $('.btn_upload').click();
		 $(this).next('.btn_upload').click();
	 });

	 // Preview Image when we select image already
	 $('body').delegate('#btn_uploadimg','change',function(e){
	  var files = this.files;
	  num  = files.length;
	  $('.wrap_img').removeClass('one_imge two_images three_images four_images');
	   $('.wrap_img').addClass('myicon');
	  if(num==1){
	   $('.wrap_img').addClass('one_imge');
	   $('.wrap_img').removeClass('myicon');
	  }else if(num==2){
	   $('.wrap_img').addClass('two_images');
	   $('.wrap_img').removeClass('myicon');
	  }else if(num==3){
	   $('.wrap_img').addClass('three_images');
	   $('.wrap_img').removeClass('myicon');
	  }else if(num>=4){
	   $('.wrap_img').addClass('four_images');
	   $('.wrap_img').removeClass('myicon');
	 }

	  var files = e.target.files;
	 //$('.wrap_img').children('img').remove();
	  $this.children('img').remove();
	  $.each(files,function(i,file){ // Open for upload multiple image
		  var reader = new FileReader();
		  reader.readAsDataURL(file); // use when upload multipe image
		  reader.onload = function(e){
		  var tem = '<img src="'+e.target.result+'">';
	      //.$('.wrap_img').append(tem);
	      $this.append(tem);
	   }
	  });
	  reader.readAsDataURL(files[0]);
	 });
	 
	 
	 

	 	/*2016-01-28:3:26 PM*/
	 
		// Area(Add_slide) when click to browse img
		 /*$('body').delegate('.wrap_img','click',function(){
		  $('.btn_upload').click();
		 });*/

		 // Preview Image when we select image already
		/* $('body').delegate('#btn_uploadimg','change',function(e){
		  var files = this.files;
		  num  = files.length;
		  $('.wrap_img').removeClass('one_imge two_images three_images four_images');
		   $('.wrap_img').addClass('myicon');
		  if(num==1){
		   $('.wrap_img').addClass('one_imge');
		   $('.wrap_img').removeClass('myicon');
		  }else if(num==2){
		   $('.wrap_img').addClass('two_images');
		   $('.wrap_img').removeClass('myicon');
		  }else if(num==3){
		   $('.wrap_img').addClass('three_images');
		   $('.wrap_img').removeClass('myicon');
		  }else if(num>=4){
		   $('.wrap_img').addClass('four_images');
		   $('.wrap_img').removeClass('myicon');
		 }

		  var files = e.target.files;
		  $('.wrap_img').children('img').remove();
		  $.each(files,function(i,file){ // Open for upload multiple image
		   var reader = new FileReader();
		   reader.readAsDataURL(file); // use when upload multipe image
		   reader.onload = function(e){
		    var tem = '<img src="'+e.target.result+'">';
		    $('.wrap_img').append(tem);
		   }
		  });
		  reader.readAsDataURL(files[0]);
		 });
		 */
		 
		 /*2016-01-28:3:26 PM*/
	 
	 
	 //pagination
	 var page_num	=	$('.pag_num').children('a').length;

		// next one by one
		$('body').delegate('.next','click',function(){
			var page_act	=	parseInt($('.pag_num').find('a.on').text());
			if(page_act!=0){$('.first, .prev').addClass('on');}
			if(page_act==(page_num-1)){$('.last, .next').removeClass('on');}
			if(page_act==page_num){page_act=(page_num-1);}
			$(this).prev('.pag_num').find('a').removeClass('on');
			$('.pag_num').find("a:eq("+page_act+")").addClass('on');
		});

		// next to last
		$('body').delegate('.last','click',function(){
			$('.first, .prev').addClass('on');
			$('.last, .next').removeClass('on');
			$(this).prevAll('.pag_num').find('a').removeClass('on');
			page_act	=	page_num-1;
			$('.pag_num').find("a:eq("+page_act+")").addClass('on');
		});

		// Prev one by one
		$('body').delegate('.prev','click',function(){
			var page_act	=	parseInt($('.pag_num').find('a.on').text())-2;
			if(page_act!=(page_num-1)){
				$('.last, .next').addClass('on');	
			}
			if(page_act==0){
				$('.first, .prev').removeClass('on');	
			}
			if(page_act==-1){
				page_act=0;	
			}
			$(this).next('.pag_num').find('a').removeClass('on');
			$('.pag_num').find("a:eq("+page_act+")").addClass('on');
		});

		// First All
		$('body').delegate('.first','click',function(){
			$('.last, .next').addClass('on');
			$('.first, .prev').removeClass('on');
			$(this).nextAll('.pag_num').find('a').removeClass('on');
			$('.pag_num').find("a:eq(0)").addClass('on');
		});
		
		$('body').delegate('.pag_num > a','click',function(){
			$(this).siblings('a').removeClass('on');
			$(this).addClass('on');
			$('.btn_pag_cntr').addClass('on');
			if($(this).text()==1){
				$('.first, .prev').removeClass('on');
			}else if($(this).text()==page_num){
				$('.last, .next').removeClass('on');
			}
		});
	
	 
});
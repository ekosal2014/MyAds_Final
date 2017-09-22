// JavaScript Document
$(function(){
		$('select').not('#demo-select_2').CustomSelect();
		$('#demo-select_2').CustomSelect({visRows:10, search:true, modifier: 'mod'});
		$('#button_1').on('click', function(event){
			event.preventDefault();
			var $btn = $(this);
			if($('#demo-select_1').is(':disabled')){
				$btn.text('Set disable');
				$('#demo-select_1').removeAttr('disabled').trigger('update');
			}else{
				$btn.text('Set enable');
				$('#demo-select_1').attr({'disabled':'disabled'}).trigger('update');
			}
		});
	});
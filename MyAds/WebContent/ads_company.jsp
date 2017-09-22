
<jsp:directive.include file="ads_header.jsp" />
	<jsp:directive.include file="ads_help.jsp" />

	<script>
		function register(){
			frmcompany.submit();
		}
	</script>

	<!-- body_section -->
	<div class="body_section">
		<!-- cnt_wrap -->
		<div class="cnt_wrap">
			<!-- body_cnt -->
			<div class="body_cnt">
				<!-- container -->
				<div class="container">
				
					<!-- mypost header -->
					<div class="mypost_header">
						<div class="cboth">
							<h1 class="f_left"><span style="color:#0174b3;">MyAds's</span> <span style="color:#f7153a;">Company Register</span></h1>
						</div>
					</div>
					<!-- //mypost header -->
				
					<!-- wrap_function -->
					<div class="wrap_func">
						<div class="f_right">
							welcome to be good relation
						</div>

						<div class="register">
							<form method="post" action="addnew_com.ads" id="frmAddCompany" name="frmcompany">
								<table summary="">
									<caption></caption>
									<colgroup>
									<col style="width:200px;">
									<col>
									</colgroup>
									<tbody>
										<tr>
											<th><div>Company name <span class="require">*</span></div></th>
											<td><div><input type="text" value="" placeholder="Enter company name" name="txt_com_name" style="width:308px;" required="required"></div></td>
										</tr>
										<tr>
											<th><div>Type <span class="require">*</span></div></th>
											<td><div><input type="text" value="" placeholder="Enter company type" name="txt_type" style="width:308px;" required="required"></div></td>
										</tr>
										<tr>
											<th><div>Fax <span class="require">*</span></div></th>
											<td><div><input type="text" value="" placeholder="Enter your fax number" name="txt_fax" style="width:308px;" required="required"></div></td>
										</tr>
										<tr>
											<th><div>Email address <span class="require">*</span></div></th>
											<td><div><input type="text" value="" placeholder="Enter email address" name="txt_email" style="width:308px;" required="required"></div></td>
										</tr>
										<tr>
											<th><div>Address <span class="require">*</span></div></th>
											<td><div><textarea style="width:304px;height:70px;" placeholder="Enter your real current address" name="txt_address" required="required"></textarea></div></td>
										</tr>
										<tr>
											<th colspan="2" class="t_right"><div><a href="javascript:" id="addCompany" class="btn_create_acc">Create My Company</a> <!-- <input type="submit" value="Create My Company" class="btn_create_acc"> --> </div></th>
										</tr>
									</tbody>
								</table>
							</form>
						</div>
					</div>
					<!-- //wrap_function -->
				</div>
				<!-- //container -->
			</div>
			<!-- //body_cnt -->
		</div>
		<!-- //cnt_wraps -->
	</div>
	<!-- //body_section -->

	<jsp:directive.include file="ads_footer.jsp" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/3.51/jquery.form.min.js"></script>
	<script src="js/jquery.validate.min.js"></script>
	<script src="js/additional-methods.js"></script>
    <script src="js/jquery.validate.js"></script>
	<script>
		$(document).ready(function(e){
			$(".hide").css("display","none");
			$("#profile_setting").click(function(e){				
				if ($(this).parents(".welcomebox").find("div").hasClass("show")){					
					$(this).parents(".welcomebox").find("div").addClass("hide");
					$(this).parents(".welcomebox").find("div").removeClass("show");
				}else{
					$(this).parents(".welcomebox").find("div").removeClass("hide");
					$(this).parents(".welcomebox").find("div").addClass("show");
				}
			});	
			$("#addCompany").click(function(e){
				if($('#frmAddCompany').valid()){ 
					$('#frmAddCompany').ajaxSubmit({
						url: "./addnew_com.ads",
						type: 'POST',
						success: function(data) { 			
						        if(data){
						        	alert('YOU HAVE BEEN INSERTED SUCCESSFULLY.')
						        	window.location.href="./lay_reg.ads";
						        }else{
						        	alert('YOU HAVE ERRORS WHEN INSERT UPDATE PROFILE.');
						        }
						        //ajaxindicatorstop()
						        
						},error:function(data,status,er) { 
							//ajaxindicatorstop()
						    console.log("error: "+data+" status: "+status+" er:"+er);
						}
					}); 
				}
			});
			
		});
		
	</script>
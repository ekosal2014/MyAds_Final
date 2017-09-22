
<jsp:directive.include file="ads_header.jsp" />
	<jsp:directive.include file="ads_help.jsp" />

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
							<h1 class="f_left"><span style="color:#0174b3;">MyAdspages</span> <span style="color:#f7153a;">Account Setting</span></h1>
						</div>
					</div>
					<!-- //mypost header -->
				
					<!-- wrap_function -->
					<div class="wrap_func">
						<div class="f_right">
							add more company informatoin here
						</div>

						<div class="register change">
							  <form action="" id="form-image-change" method="post" enctype="multipart/form-data">
								<!-- photo -->
								<div class="single_photo">
									<div class="uploadimage" >
										 <a href="javascript:" class="wrap_img user single" id="edit-image" style="cursor: pointer;">
											<!-- <span class="getphoto">photo</span> -->
											<!--<span class="addmore">Add more photos</span>-->
											<%
												if (member.getPhoto()!="" && member.getPhoto()!=null){
											%>
												<img alt="" src="profile/<%=member.getPhoto() %>">
											<%
												}else{
											%>
												<img alt="" src="img/ico/user_img.png">
											<%
												}
											%>
										</a>
										
										<input type="file" class="btn_upload" id="btn_uploadimg" name="txt_photo" style="display:none;" required="required">
										<!-- <span style="display:none" class="storeimg"></span> -->
									</div>
									<a href="javascript:" class="btn_edit" id="btn_save_change_image" style="margin-top:10px;">Save</a>
									<!-- <span class="disable_photo" style="display:block;"><span class="blind">disable photo</span> </span> -->
								</div>
								<input type="hidden" value="<%=member.getId() %>" placeholder="Enter your name" name="txt_id" style="width:463px;" required="required">
								<!-- //photo -->
							</form>
							<form method="post" action="" id="eidtposting" >	
								<a href="javascript:" class="btn_edit" id="btn-edit-all">Edit All</a>
								<a href="javascript:" class="btn_cancel">Cancel</a>
								<table summary="">
									<caption></caption>
									<colgroup>
									<col style="width:200px;">
									<col>
									</colgroup>
									<tbody>
										<tr>
											<th><div>Username</div></th>
											<td>
												<!-- default read -->
												<div class="dis" style="display:block;">
													<input type="text" value="<%=member.getName() %>" style="width:400px;" disabled="disabled" class="dis hid_ipt txt-control-show">
													<a href="javascript:" class="btn_edit">Edit</a>
												</div>
												<!--// default read -->

												<!-- display edit -->
												<div class="hide" style="display:none;">
													<input type="text" class="txt-control-hide" value="<%=member.getName() %>" placeholder="Enter your name" name="txt_name" style="width:463px;" required="required" data-rule-required="true" data-msg-required="Please enter your username!"><br>
													<input type="hidden" value="<%=member.getId() %>" placeholder="Enter your name" name="txt_id" style="width:463px;" required="required">
													<a href="javascript:" class="btn_save">Save</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<a href="javascript:" class="btn_cancel">Cancel</a>
												</div>
												<!-- //display edit -->
											</td>
										</tr>
										<tr>
											<th><div>Your full name</div></th>
											<td>
												<!-- default read -->
												<div class="dis" style="display:block;">
													<input type="text" value="<%=member.getRealName() %>" style="width:400px;" disabled="disabled" class="dis hid_ipt txt-control-show">
													<a href="javascript:" class="btn_edit">Edit</a>
												</div>
												<!--// default read -->

												<!-- display edit -->
												<div class="hide" style="display:none;">
													<input type="text" class="txt-control-hide" value="<%=member.getRealName() %>" placeholder="Enter your real name" name="txt_realname" style="width:463px;" required="required" data-rule-required="true" data-msg-required="Please enter your full name!"><br>
													<a href="javascript:" class="btn_save">Save</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<a href="javascript:" class="btn_cancel">Cancel</a>
												</div>
												<!-- //display edit -->
											</td>
										</tr>
										<tr>
											<th><div>Your company name</div></th>
											<td>
												<!-- default read -->
												<div class="dis" style="display:block;">
													<input type="text" value="<%=member.getCompanyDto().getComname() %>" style="width:400px;" disabled="disabled" class="dis hid_ipt">
													<a href="javascript:" class="btn_edit">Edit</a>
												</div>
												<!--// default read -->
												
												<!-- display edit -->
												<div class="hide" style="display:none;padding:0;">
													<div style="height:60px;">
														<select style="width:350px;height:40px;" name="txt_companyid" required="required">
															
															<%
															  //out.println("Com +"+member.getCompanyDto().getComid());
															  ResultSet rsth=MyComobox.getCompany();
																while(rsth.next()){
																	//out.println(rsth.getString("ComId"));
															%>
															<option value="<%= rsth.getString("ComId") %>"
															    <% if(member.getCompanyDto().getComid()==Integer.valueOf(rsth.getString("ComId"))) { %> selected="selected" <%} %>
															><%= rsth.getString("ComName") %></option>
															<%
																}
															%>
														</select>
														<a href="lay_com.ads" class="btn_create_acc" style="padding-left:10px;padding-right:10px;">Add New</a>
													</div>
													<a href="javascript:" class="btn_save">Save</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<a href="javascript:" class="btn_cancel">Cancel</a>
												</div>
												<!-- //display edit -->
											</td>
										</tr>
										<tr>
											<th><div>Gender</div></th>
											<td>
												<!-- default read -->
												<div class="dis" style="display:block;">
													<input type="text" value="<%=member.getSex() %>" style="width:400px;" disabled="disabled" class="dis hid_ipt">
													<a href="javascript:" class="btn_edit">Edit</a>
												</div>
												<!--// default read -->
												
												<!-- display edit -->
												<div class="hide" style="display:none;">
													<label><input type="radio" <% if(member.getSex().equals("Male")){ %> checked  <% } %> value="Male" name="txt_sex" required="required"> Male</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<label><input type="radio" <% if(member.getSex()=="Female"){ %> checked  <% } %> value="Female" name="txt_sex" required="required"> Female</label><br>
													<a href="javascript:" class="btn_save">Save</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<a href="javascript:" class="btn_cancel">Cancel</a>
												</div>
												<!-- //display edit -->
											</td>
										</tr>
										<tr>
											<th><div>Password</div></th>
											<td>
												<!-- default read -->
												<div class="dis" style="display:block;">
													<input type="password" value="<%=member.getPassword() %>" style="width:400px;" disabled="disabled" class="dis hid_ipt">
													<a href="javascript:" class="btn_edit">Edit</a>
												</div>
												<!--// default read -->
												
												<!-- display edit -->
												<div class="hide" style="display:none;padding:0;">
													<div><p>Current</p><input type="password" value="" class="txt-control-hide" placeholder="Current password" name="txt_pass" style="width:463px;" data-rule-required="true" data-msg-required="Please enter your old password!"></div>
													<div><p>New</p><input type="password" value="" class="txt-control-hide" placeholder="New password" name="txt_nex_pass" id="txt_nex_pas" style="width:463px;" required="required" data-rule-required="true" data-msg-required="Please enter your new password!"></div>
													<div><p>Re-Type New</p><input type="password" class="txt-control-hide" value="" placeholder="Re-type new password" name="txt_pass_comfirm" style="width:463px;" required="required" data-rule-required="true" data-rule-equalTo="#txt_nex_pas" data-msg-required="Please enter your comfirm password!"></div>
													<a href="javascript:" class="btn_save_password">Save</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<a href="javascript:" class="btn_cancel_password">Cancel</a>
												</div>
												<!-- //display edit -->
											</td>
										</tr>
										<tr>
											<th><div>Email address</div></th>
											<td>
												<!-- default read -->
												<div class="dis" style="display:block;">
													<input type="text" value="<%=member.getEmail() %>" style="width:400px;" disabled="disabled" class="dis hid_ipt txt-control-show">
													<a href="javascript:" class="btn_edit">Edit</a>
												</div>
												<!--// default read -->
												
												<!-- display edit -->
												<div class="hide" style="display:none;">
													<input type="text" class="txt-control-hide" value="<%=member.getEmail() %>" placeholder="Enter your email address" name="txt_email" style="width:463px;" required="required"><br>
													<a href="javascript:" class="btn_save">Save</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<a href="javascript:" class="btn_cancel">Cancel</a>
												</div>
												<!-- //display edit -->
											</td>
										</tr>
										<tr>
											<th><div>Phone Number</div></th>
											<td>
												<!-- default read -->
												<div class="dis" style="display:block;">
													<input type="text" value="<%=member.getPhone() %>" style="width:400px;" disabled="disabled" class="dis hid_ipt txt-control-show">
													<a href="javascript:" class="btn_edit">Edit</a>
												</div>
												<!--// default read -->
												
												<!-- display edit -->
												<div class="hide" style="display:none;">
													<input type="text" class="txt-control-hide" value="<%=member.getPhone() %>" placeholder="Enter your phone number" name="txt_phone" style="width:463px;" required="required" data-rule-required="true" data-rule-number="true" data-rule-digits="true" data-msg-required="Please enter your phone number!"><br>
													<a href="javascript:" class="btn_save">Save</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<a href="javascript:" class="btn_cancel">Cancel</a>
												</div>
												<!-- //display edit -->
											</td>
										</tr>
										<tr>
											<th><div>Your Address</div></th>
											<td>
												<!-- default read -->
												<div class="dis" style="display:block;">
													<textarea  style="width:420px;height:70px;display:inline-block; " disabled="disabled" class="dis hid_ipt txt-control-show"><%=member.getAddress() %></textarea>
													<a href="javascript:" class="btn_edit">Edit</a>
												</div>
												<!--// default read -->
												
												<!-- display edit -->
												<div class="hide" style="display:none;">
													<textarea style="width:459px;height:70px;" class="txt-control-hide" placeholder="Enter your real current address" name="txt_address" required="required" data-rule-required="true" data-msg-required="Please enter your description!"><%=member.getAddress() %></textarea><br>
													<a href="javascript:" class="btn_save">Save</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<a href="javascript:" class="btn_cancel">Cancel</a>
												</div>
												<!-- //display edit -->
											</td>
										</tr>
										<tr style="display:;">
											<th colspan="2" class="t_right" style="padding:10px 0;"><div> <a href="javascript:" class="btn_create_acc" id="btn_change_acc">Change My Account</a><!-- <input type="submit" value="Change My Account" class="btn_create_acc"> --> </div></th>
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
			//$(".hide").css("display","none");
			$("#profile_setting").click(function(e){
				if ($(this).parents(".welcomebox").find("div").hasClass("show")){					
					$(this).parents(".welcomebox").find("div").addClass("hide");
					$(this).parents(".welcomebox").find("div").removeClass("show");
				}else{
					$(this).parents(".welcomebox").find("div").removeClass("hide");
					$(this).parents(".welcomebox").find("div").addClass("show");
				}
			});	
			
			$(".register table tbody td .dis .btn_edit").click(function(e){
				$(this).parent(".dis").hide();
				$(this).parent(".dis").parent("td").find(".hide").show();
			});
			$(".register table tbody td .hide .btn_cancel").click(function(e){
				var txt=$(this).parent(".hide").parent("td").find(".dis").find(".txt-control-show").val();
				//alert(txt);
				$(this).parent(".hide").parent("td").find(".hide").find(".txt-control-hide").val(txt);				
				$(this).parent(".hide").parent("td").find(".dis").show();
				$(this).parent(".hide").parent("td").find(".hide").hide();
				
			});
			
			$(".register table tbody td .hide .btn_cancel_password").click(function(e){
				$(this).parent(".hide").parent("td").find(".hide").find(".txt-control-hide").val('');
				$(this).parent(".hide").parent("td").find(".dis").show();
				$(this).parent(".hide").parent("td").find(".hide").hide();
			});
			
			
			$("#btn-edit-all").click(function(e){
				$(".dis").hide();
				$(".hide").show();
				$(".btn_save").hide();
				$(".btn_cancel").hide();
				$(".btn_save_password").hide();
				$(".btn_cancel_password").hide();
		    });
			
			$(".register table tbody td .hide .btn_save").click(function(e){
				 var obj=$(this);
				 if($('#eidtposting').valid()){  //call valid for form2 and show the errors
		              // alert('submit form');  //only if the form is valid submit the form
					 $('#eidtposting').ajaxSubmit({
							url: "./lay_editaccount_setting.ads",
							type: 'POST',
							success: function(data) { 			
							        if(data){
							        	alert('YOU HAVE BEEN INSERTED SUCCESSFULLY.')
							        	var txt=obj.parent(".hide").parent("td").find(".hide").find(".txt-control-hide").val();
							        	obj.parent(".hide").parent("td").find(".dis").find(".txt-control-show").val(txt);
										//alert(txt);
										//obj.parent(".hide").parent("td").find(".hide").find(".txt-control-hide").val(txt);				
										obj.parent(".hide").parent("td").find(".dis").show();
										obj.parent(".hide").parent("td").find(".hide").hide();
							        	//location.href="lay_account_setting.ads";
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
			
			$(".register table tbody td .hide .btn_save_password").click(function(e){
				 var obj=$(this);
				 if($('#eidtposting').valid()){  //call valid for form2 and show the errors
		              // alert('submit form');  //only if the form is valid submit the form
					 $('#eidtposting').ajaxSubmit({
							url: "./lay_editaccount_setting.ads",
							type: 'POST',
							success: function(data) { 			
							        if(data){
							        	alert('YOU HAVE BEEN INSERTED SUCCESSFULLY.')
							        	var txt=obj.parent(".hide").parent("td").find(".hide").find("#txt_nex_pas").val();
							        	obj.parent(".hide").parent("td").find(".dis").find(".txt-control-show").val(txt);
										//alert(txt);
										//obj.parent(".hide").parent("td").find(".hide").find(".txt-control-hide").val(txt);				
										obj.parent(".hide").parent("td").find(".dis").show();
										obj.parent(".hide").parent("td").find(".hide").hide();
										obj.parent(".hide").parent("td").find(".hide").find("#txt-control-hide").val('');
							        	//location.href="lay_account_setting.ads";
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
			
			$("#btn_change_acc").click(function(e){
				if($('#eidtposting').valid()){  //call valid for form2 and show the errors
		              // alert('submit form');  //only if the form is valid submit the form
					 $('#eidtposting').ajaxSubmit({
							url: "./lay_editaccount_setting.ads",
							type: 'POST',
							success: function(data) { 			
							        if(data){
							        	alert('YOU HAVE BEEN INSERTED SUCCESSFULLY.')
							        	$(".dis").show();
										$(".hide").hide();
										$(".btn_save").show();
										$(".btn_cancel").show();								
										$(".btn_save_password").show();
										$(".btn_cancel_password").show();
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
			
			$(".register .single_photo #btn_save_change_image").click(function(e){
				if ($("#btn_uploadimg").val()==null || $("#btn_uploadimg").val()==""){
					alert("Please image profile!")
					return;
				}

				if($('#form-image-change').valid()){  //call valid for form2 and show the errors
		              // alert('submit form');  //only if the form is valid submit the form
					 $('#form-image-change').ajaxSubmit({
							url: "./edit_image_account_setting.ads",
							type: 'POST',
							success: function(data) { 			
							        if(data){
							        	alert('YOU HAVE BEEN INSERTED SUCCESSFULLY.')							        	
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

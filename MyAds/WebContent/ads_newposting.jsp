
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
							<h1 class="f_left"><span style="color:#0174b3;">MyAds's</span> <span style="color:#f7153a;">New Posting</span></h1>
						</div>
					</div>
					<!-- //mypost header -->
				
					<!-- wrap_function -->
					<div class="wrap_func">
						<div class="f_right" style="width:255px;">
							<h1 class="tit">Posting Policies</h1>
							<div class="mypolicy">
								<ol>
									<li>1.  <strong style="color:red;">Do not allow</strong> for uploading  <strong style="color:red;">sexy photo.</strong></li>
									<li>2. Please upload your products photos( You can upload <strong style="color:green;">1,2,3,.... more photos</strong>)</li>
									<li>3. After your uploaded you will see your posting after <strong style="color:green;">approvaled</strong>.</li>
									<li>4. Try to posting in right way follow posting demo in point 5</li>
									<li>5. Demo for Create Your Posting<br>
											 <video controls autoplay style="width:100%;height:150px;">
											  <source src="demo/demo.wmv" type="video/mp4">
											</video> 
									</li>
								</ol>
							</div>
						</div>

						

						<div class="register" style="width:880px;">
							<form method="post" action="add_newposting.ads" novalidate="novalidate" name="frmregister" id="frmcreatepost" enctype="multipart/form-data">
								<table summary="">
									<caption></caption>
									<colgroup>
									<col style="width:125px;">
									<col>
									<col>
									</colgroup>
									<tbody>
										<tr style="display:;">
											<th scope="row"><div>Member ID <span class="require">*</span></div></th>
											<td><div><input type="text" value="<%= member.getId() %>" name="txt_memid"></div></td>
										</tr>
										<tr>
											<th scope="row"><div>Your Product Name <span class="require">*</span></div></th>
											<td><div><input type="text" value="" placeholder="Enter your product title" name="txt_pro_tit" style="width:308px;" required="required" ></div></td>
											<td rowspan="7" style="vertical-align:top;">
												<div><strong>Photo <span class="require">*</span></strong>
													<div class="uploadimage" style="margin-bottom:8px;">
														<a href="#none" class="wrap_img myicon user">
															<span class="getphoto">Click me to get photos</span>
															<!--<span class="addmore">Add more photos</span>-->
														</a>
														<input type="file" class="btn_upload" id="btn_uploadimg" name="txt_photo" style="display:none;" multiple required="required" accept="image/*">
														<span style="display:none" class="storeimg"></span>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<th><div>Category Type <span class="require">*</span></div></th>
											<td>
												<div style="height:50px;">
													<select style="width:300px;height:70px;" name="txt_subcatid" id="txt_subcatid" required="required" >
														<option value="" data-modifier="mod">All Categories</option>
															<%
																/* List<MainCategoryDto> rst=CategoryDao.getCategory();
															    List<SubCategoryDto> rst1=CategoryDao.getSubCategory(); */
																for(int j=0;j<rst.size();j++){
															%>
															<optgroup label="<%= rst.get(j).getName() %>">
																		<%
														                	for(int i=0;i<rst1.size();i++)  {  
														                		if (rst.get(j).getId()==rst1.get(i).getId()){
																		%>
																				<option value="<%= rst1.get(i).getSubid() %>"><%= rst1.get(i).getName() %></option>
																		<%
																		     }
														                	}
																		 %>
															</optgroup>
															<%
															     }
															%>
													</select>
												</div>
											</td>
										</tr>
										<tr>
											<th><div>Key Notice <span class="require">*</span></div></th>
											<td><div><input type="text" value="" placeholder="Enter key notice" name="txt_keynotice" style="width:308px;" required="required"></div></td>
										</tr>
										<tr>
											<th><div>Price <span class="require">*</span></div></th>
											<td><div><input type="text" value="" placeholder="Enter price" name="txt_price" style="width:308px;" required="required"></div></td>
										</tr>
										<tr>
											<th><div>Discount <span class="require">*</span></div></th>
											<td><div><input type="text" value="" placeholder="Enter discount price" name="txt_discount" style="width:308px;" required="required"></div></td>
										</tr>
										<tr>
											<th><div>Phone Number <span class="require">*</span></div></th>
											<td><div><input type="text" value="" placeholder="Enter your phone number" name="txt_phone" style="width:308px;" required="required" ></div></td>
										</tr>
										<tr>
											<th><div>Your Address <span class="require">*</span></div></th>
											<td><div><textarea style="width:304px;height:50px;" placeholder="Enter your real current address" name="txt_address" required="required" data-rule-required="true" data-msg-required="Please enter your address"></textarea></div></td>
										</tr>
										<tr>
											<th><div>Product Description <span class="require">*</span></div></th>
											<td><div><textarea style="width:304px;height:160px;" placeholder="Enter your description" name="txt_dsc"  data-rule-required="true" data-msg-required="Please enter your description!"></textarea></div></td>
										</tr>
										<tr>
											<th colspan="3" class="t_right"><div><!-- <a href="javascript:" class="btn_create_acc" id="btn_create_acc">Create My Posting</a> --><input type="submit" value="Create My Posting" class="btn_create_acc"> </div></th>
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
    
	<script type="text/javascript">
		$(document).ready(function(e){
			$("#profile_setting").click(function(e){				
				if ($(this).parents(".welcomebox").find("div").hasClass("show")){					
					$(this).parents(".welcomebox").find("div").addClass("hide");
					$(this).parents(".welcomebox").find("div").removeClass("show");
				}else{
					$(this).parents(".welcomebox").find("div").removeClass("hide");
					$(this).parents(".welcomebox").find("div").addClass("show");
				}
			});	
			
			$("#frmcreatepost").validate({
				debug: true,
				rules:{
					txt_pro_tit:{
						required:true
					},
					txt_subcatid: {
			               required: function () {
			                   if ($("#txt_subcatid option[value='0']")) {
			                       return true;
			                   } else {
			                	   alert(1234);
			                       return false;
			                   }
			               }
			         },
			         txt_photo: { 
			        	 required: true, 
			        	 accept: "png|jpe?g|gif", 
			        	filesize: 1048576  
			        },
			        txt_price:{
			        	required:true,
			        	number:true,
			            digits: true,
			            range : [0, 1000000]
			        },
			        txt_discount:{
			        	required:true,
			        	number:true,
			            digits: true,
			            range : [0, 100]
			        },
			        txt_phone:{
			        	required:true,
			        	number:true,
			            digits: true,
			        }
				},
				messages:{
					txt_pro_tit:{
						required:'Input Title Of Product.'
					},
					txt_subcatid:{
						required:'Please Select Sub Category!'
					},
					 txt_photo: {
						 required:"File must be JPG, GIF or PNG, less than 1MB"
					 },
					 txt_price:{
						 required:"Please Your Price of Product!"
					 },
					 txt_discount:{
						 required:"Please Your Price Discount of Product!"
					 },
					 txt_phone:{
						 required:"Please Your Phone Number!"
					 }
					
					
				},
				submitHandler: function(form) {
					$(form).ajaxSubmit({
						url: "./add_newposting.ads",
						type: 'POST',
						success: function(data) { 					
							
						        if(data){
						        	alert('YOU HAVE BEEN INSERTED SUCCESSFULLY.');
						        	location.href="lay_myadspages.ads";
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

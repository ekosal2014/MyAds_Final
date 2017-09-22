
<%@page import="myads.model.dto.PostingDto"%>
<jsp:directive.include file="ads_header.jsp" />
	<jsp:directive.include file="ads_help.jsp" />
    <%
    	PostingDto postingDto=(PostingDto) request.getSession().getAttribute("postingDto");
    %>
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
							<h1 class="f_left"><span style="color:#0174b3;">MyAds's</span> <span style="color:#f7153a;">Edit Posting</span></h1>
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

						

						<div class="register change cboth" style="width:510px;padding-left:20px;padding-right:370px;">
							<!-- update image -->
							<div class="update_img">
								
									<div style="text-align:left;padding:5px;border:1px solid #eee;"><strong>Photo <span class="require">*</span></strong>
										<!-- photo_wrap -->
										<div class="photo_wrap" style="height:auto;padding:0 10px; ">
											<%
											    //out.println(postingDto.getImageList().size());
												for(int i=0;i<postingDto.getImageList().size();i++){
											%>
											
											<div class="t_right">
											    <form method="post" action="" name="frmregister" id="frmeditphoto<%=postingDto.getImageList().get(i).getImage_id() %>" enctype="multipart/form-data">
												<a href="javascript:" class="btn_remove" onclick="removeImage(this)">Remove</a>
												<ul class="multi_photo">
													<li>
														<!-- photo -->
														<div style="width:100px;float:left;">
															<a href="javascript:" class="wrap_img user single">
																<!-- <span class="getphoto">Brow</span> -->
																<!--<span class="addmore">Add more photos</span>-->
															    <img alt="" src="uploads/<%=postingDto.getImageList().get(i).getImage() %>" >
																
															</a>
															<input type="file" class="btn_upload" id="btn_uploadimg" name="txt_photo" style="display:none;" required="required">
															<!-- <span style="display:none" class="storeimg"></span> -->
														</div>
														<a href="javascript:" class="btn_edit ico_save" style="margin-top:10px;margin-left:15px;" >Save</a>
														<a href="javascript:" class="btn_edit" style="margin-top:10px;">Edit</a>
														<input type="hidden" value="<%= postingDto.getPostingId() %>"  name="txt_pro_id" class="txt_pro_id" required="required">
														<input type="hidden" value="<%=postingDto.getImageList().get(i).getImage_id() %>" name="txt_id"  class="txt_id" required="required">
														<!--  display(none/block) -->
														<!-- <span class="disable_photo" style="display:none;"><span class="blind">disable photo</span> </span> -->
														<!--  //display(none/block) -->
														<!-- //photo -->
													</li>
												</ul>
												</form>
											</div>
											<% } %>	
											<a href="javascript:" class="btn_edit" id="add_image_more" onclick="addmoreimage(this,<%=postingDto.getPostingId() %>)" style="margin-top:10px;">Add Image More</a>												
										</div>
										<!-- //photo_wrap -->
									</div>
									
									<!-- <a href="javascript:" class="btn_create_acc mgt10" id="btn_create_photo">Save All Photos</a> -->
								
							</div>
							<!-- //update image -->
						
						
							<!-- //text update -->
							<div class="update_text">
								<form method="post"  name="frmregister" id="eidtposting" >
									<table summary="" style="text-align:left;">
										<caption></caption>
										<colgroup>
										<col style="width:140px;">
										<col>
										</colgroup>
										<tbody>
											<tr style="display:;">
												<th scope="row"><div>Member ID <span class="require">*</span></div></th>
												<td>
													<div>
														<input type="text" value="<%= postingDto.getMemberDto().getId() %>" name="txt_memid">
														<input type="text" value="<%= postingDto.getPostingId() %>"  name="txt_pro_id" required="required">
													</div>
												</td>
											</tr>
											<tr>
												<th><div>Category Type <span class="require">*</span></div></th>
												<td>
													<div style="height:50px;">
														<select style="width:300px;height:70px;" name="txt_subcatid" required="required">
															<option value="0" data-modifier="mod">All Categories</option>
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
																					<option value="<%= rst1.get(i).getSubid() %>" 
																					   <% if (postingDto.getSubCategory().getSubid()==rst1.get(i).getSubid()){ %>
																					   		selected="selected"
																					   <% } %>																				
																					><%= rst1.get(i).getName() %></option>
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
												<th><div>Product Name <span class="require">*</span></div></th>
												<td><div><input type="text" value="<%=postingDto.getTitle() %>" placeholder="Enter key notice" name="txt_pro_tit" style="width:308px;" required="required"></div></td>
											</tr>
											<tr>
												<th><div>Key Notice <span class="require">*</span></div></th>
												<td><div><input type="text" value="<%=postingDto.getKey() %>" placeholder="Enter key notice" name="txt_keynotice" style="width:308px;" required="required"></div></td>
											</tr>
											<tr>
												<th><div>Price <span class="require">*</span></div></th>
												<td><div><input type="text" value="<%=postingDto.getPrice() %>" placeholder="Enter price" name="txt_price" style="width:308px;" required="required"></div></td>
											</tr>
											<tr>
												<th><div>Discount <span class="require">*</span></div></th>
												<td><div><input type="text" value="<%=postingDto.getDiscount() %>" placeholder="Enter discount price" name="txt_discount" style="width:308px;" required="required"></div></td>
											</tr>
											<tr>
												<th><div>Phone Number <span class="require">*</span></div></th>
												<td><div><input type="text" value="<%=postingDto.getPhone() %>" placeholder="Enter your phone number" name="txt_phone" style="width:308px;" required="required"></div></td>
											</tr>
											<tr>
												<th><div>Your Address <span class="require">*</span></div></th>
												<td><div><textarea style="width:304px;height:50px;" placeholder="Enter your real current address" name="txt_address" required="required">
														<%=postingDto.getAdr() %>
												</textarea></div></td>
											</tr>
											<tr>
												<th><div>Product Description <span class="require">*</span></div></th>
												<td><div><textarea style="width:304px;height:160px;" placeholder="Enter your description" name="txt_dsc" required="required">
													<%=postingDto.getDsc() %>
												</textarea></div></td>
											</tr>
											<tr style="display:none;">
												<!-- <th colspan="2" class="t_right"><div><a href="javascript:" class="btn_create_acc" id="btn_create_acc">Save My Posting</a> --><!--<input type="submit" value="Create My Posting" class="btn_create_acc">--> </div></th>
											</tr>
										</tbody>
									</table>
									 <a href="javascript:" class="btn_create_acc mgt10" id="btn_create_acc" style="margin-right:34px;">Save My Posting</a> 
								</form>
							</div>
							<!-- //text update -->
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
	  $("#profile_setting").click(function(e){				
			if ($(this).parents(".welcomebox").find("div").hasClass("show")){					
				$(this).parents(".welcomebox").find("div").addClass("hide");
				$(this).parents(".welcomebox").find("div").removeClass("show");
			}else{
				$(this).parents(".welcomebox").find("div").removeClass("hide");
				$(this).parents(".welcomebox").find("div").addClass("show");
			}
		});	
		$("#btn_create_acc").click(function(e){
			if($('#eidtposting').valid()){  //call valid for form2 and show the errors
	              // alert('submit form');  //only if the form is valid submit the form
				 $('#eidtposting').ajaxSubmit({
						url: "./lay_editposting.ads",
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
		
 		$(".photo_wrap .t_right .multi_photo a.ico_save").click(function(e){
			var form='#frmeditphoto'+$(this).parent('li').find(".txt_id").val();
			if ($(this).parent('li').find("#btn_uploadimg").val()==null || $(this).parent('li').find("#btn_uploadimg").val()==""){
				alert("Please attach your image!!");	
				return;
			}
			$(form).ajaxSubmit({
				url: "./lay_editpostingphoto.ads",
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

		});
		
		$(".photo_wrap .t_right .btn_remove").click(function(e){
			var obj=$(this);
			var form='#frmeditphoto'+$(this).parents(".t_right").find("ul").find("li").find(".txt_id").val();
			$(form).ajaxSubmit({
				url: "./lay_removepostingphoto.ads",
				type: 'POST',
				success: function(data) { 
					    console.log(data);
				        if(data=="true"){
				        	alert('YOU HAVE BEEN INSERTED SUCCESSFULLY.')
				        	obj.parents(".t_right").fadeOut("3000");
				        	obj.parents(".t_right").remove();
				        }else{
				        	alert('YOU HAVE ERRORS WHEN INSERT UPDATE PROFILE.');
				        }
				        //ajaxindicatorstop()
				        
				},error:function(data,status,er) { 
					//ajaxindicatorstop()
				    console.log("error: "+data+" status: "+status+" er:"+er);
				}
			}); 
			
			
		}); 
		
	});
	function addmoreimage(obj,id){
		$.ajax({
			type : "POST",
   			url : "${pageContext.request.contextPath }/lay_addMoreImage.ads",
			data : "pro_id="+id,
			success : function(dat) { 
				console.log(dat);
				var html='<div class="t_right">'+		    
				             '<form method="post" action="" name="frmregister" id="frmeditphoto'+dat+'" enctype="multipart/form-data">'+
					         '<a href="javascript:" onclick="removeImage(this)" class="btn_remove">Remove</a>' +
						     ' 	<ul class="multi_photo">'+
								'<li>'+
									'<!-- photo -->'+
									'<div style="width:100px;float:left;">'+
										'<a href="javascript:" class="wrap_img user single">'+
										    '<img alt="" src="img/ico/user_img.png" >'+				
										'</a>'+
										'<input type="file" class="btn_upload" id="btn_uploadimg" name="txt_photo" style="display:none;" required="required">'+
									'</div>'+
									'<a href="javascript:" class="btn_edit ico_save" onclick="addimageMore(this)" style="margin-top:10px;margin-left:15px;">Save</a>'+
									'<a href="javascript:" class="btn_edit" style="margin-top:10px;">Edit</a>'+
									'<input type="hidden" value="'+id+'"  name="txt_pro_id" class="txt_pro_id" required="required">'+
									'<input type="hidden" value="'+dat+'" name="txt_id"  class="txt_id" required="required">'+
								'</li>'+
							  '</ul>'+
							'</form>'+
						'</div>'+
						'<a href="javascript:" style="display:none;" class="btn_edit" id="add_image_more" onclick="addmoreimage(this,'+id+')" style="margin-top:10px;">Add Image More</a>';
					$(".photo_wrap").append(html);
					obj.remove();
			},error : function(e) {
				console.log("ERROR: ", e);
				
			},
			done : function(e) {
				console.log("DONE");
			}
   			
   		});
				
		}
	
	    function removeImage(obj){	    	
			var form='#frmeditphoto'+$(obj).parents(".t_right").find("ul").find("li").find(".txt_id").val();
			$(form).ajaxSubmit({
				url: "./lay_removepostingphoto.ads",
				type: 'POST',
				success: function(data) { 
					    console.log(data);
				        if(data=="true"){
				        	alert('YOU HAVE BEEN INSERTED SUCCESSFULLY.')
				        	$(obj).parents(".photo_wrap").find("#add_image_more").css("display","block");
				        	$(obj).parents(".t_right").fadeOut("3000");
				        	$(obj).parents(".t_right").remove();
				        	
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
	    
	    function addimageMore(obj){
	    	var form='#frmeditphoto'+$(obj).parent('li').find(".txt_id").val();
			if ($(obj).parent('li').find("#btn_uploadimg").val()==null || $(obj).parent('li').find("#btn_uploadimg").val()==""){
				alert("Please attach your image!!");	
				return;
			}
			$(form).ajaxSubmit({
				url: "./lay_addMorepostingphoto.ads",
				type: 'POST',
				success: function(data) { 
					    console.log(data[1]);
				        if(data[0]=="true"){
				        	alert('YOU HAVE BEEN INSERTED SUCCESSFULLY.')
				        	var html="";
				        	$(".photo_wrap").empty();
				        	for (var i=0;i<data[1].length;i++){
				        		 html+='<div class="t_right">'+		    
						             '<form method="post" action="" name="frmregister" id="frmeditphoto'+data[1][i]["image_id"]+'" enctype="multipart/form-data">'+
							         '<a href="javascript:" onclick="removeImage(this)" class="btn_remove">Remove</a>' +
								     ' 	<ul class="multi_photo">'+
										'<li>'+
											'<!-- photo -->'+
											'<div style="width:100px;float:left;">'+
												'<a href="javascript:" class="wrap_img user single">'+
												    '<img alt="" src="uploads/'+data[1][i]["image"]+'" >'+				
												'</a>'+
												'<input type="file" class="btn_upload" id="btn_uploadimg" name="txt_photo" style="display:none;" required="required">'+
											'</div>'+
											'<a href="javascript:" class="btn_edit ico_save" onclick="addimageMore(this)" style="margin-top:10px;margin-left:15px;">Save</a>'+
											'<a href="javascript:" class="btn_edit" style="margin-top:10px;">Edit</a>'+
											'<input type="hidden" value="'+data[1][i]["post_id"]+'"  name="txt_pro_id" class="txt_pro_id" required="required">'+
											'<input type="hidden" value="'+data[1][i]["image_id"]+'" name="txt_id"  class="txt_id" required="required">'+
										'</li>'+
									  '</ul>'+
									'</form>'+
								'</div>';					
				        	}				        	
				        	html +='<a href="javascript:" style="display:block;" class="btn_edit" id="add_image_more" onclick="addmoreimage(this,'+data[1][0]["post_id"]+')" style="margin-top:10px;">Add Image More</a>';
				        	//alert(html);
				        	$(".photo_wrap").append(html);
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
	</script>

<!-- saved from url=(0014)about:internet -->
    <%@page import="myads.model.util.EncryptionUtil"%>
<%@page import="myads.model.dto.PostingDto"%>
<%@include file="ads_header.jsp" %>
    <%@include file="ads_help2.jsp" %>
    <%
    	List<MainCategoryDto> listSubCategory=(List<MainCategoryDto>) request.getSession().getAttribute("CategoryList1");
   		PostingDto postingDto=(PostingDto)request.getSession().getAttribute("productDto");
   		List<PostingDto> listRelativeProduct=(List<PostingDto>) request.getSession().getAttribute("listRelativeProduct");
   		
    %>
	<!-- body_section -->
	<div class="body_section">
		<!-- cnt_wrap -->
		<div class="cnt_wrap">
			<!-- body_cnt -->
			<div class="body_cnt">
				<div class="tit_detail">
					<!-- title -->
					<div class="title f_left">
						<h2>BETTER SHOPPING</h2>
						<p class="space"><strong>Store <span>No. 1315520</span></strong></p>
					</div>
					<!-- //title -->

					<!-- advertise -->
					<div class="adv f_right">
						<a href="#none"><img src="img/adv/metfone.gif" alt="metfone"></a>
					</div>
					<!-- //advertise -->
				</div>

				<!-- container -->
				<div class="container">
					<div class="step">
						<span><a href="./">Home</a> > </span>
						<span><a href="./mycategory.ads?id=<%=EncryptionUtil.encode(String.valueOf(postingDto.getMainCategory().getId())) %>"><%=postingDto.getMainCategory().getName() %></a> > </span>
						<span><a href="./mycategory.ads?id=<%=EncryptionUtil.encode(String.valueOf(postingDto.getMainCategory().getId())) %>&subid=<%=EncryptionUtil.encode(String.valueOf(postingDto.getSubCategory().getSubid())) %>"><%=postingDto.getSubCategory().getName() %></a> > </span> 
						<span><a href="javascript:"><%=postingDto.getTitle() %></a></span>
					</div>
					<!-- cnt -->
					<div class="cnt womenclothes cboth" style="padding-left:220px;padding-right:0;"><!-- <div class="cnt (switch class) cboth" style="padding-left:220px;padding-right:0;"> -->

						<!-- lbn_cnt -->
						<div class="lbn_cnt">
								<!-- navigation -->
							<div class="nav">
								<h3 class="storecate">Store Categories
									<!--<a href="#none" class="allcate on"><span class="blind">all categories</span></a>-->
									<a href="#none" class="allcate"><span class="blind">all categories</span></a>
									<div class="lbn">
										<dl>
											<dt><strong>CATEGORIES</strong>&nbsp;See all ></dt>
											<%
													/* List<MainCategoryDto> rst=CategoryDao.getCategory();
												    List<SubCategoryDto> rst1=CategoryDao.getSubCategory(); */
													for(int j=0;j<rst.size();j++){
												%>
													<dd><a href="mycategory.ads?id=<%=EncryptionUtil.encode(String.valueOf(rst.get(j).getId())) %>" class="<%= rst.get(j).getIco_cls_name() %>"><%= rst.get(j).getName() %></a>
														<div class="more">
															<ul>
																<%
												                	for(int i=0;i<rst1.size();i++)  {  
												                		if (rst.get(j).getId()==rst1.get(i).getId()){
																%>
																	<li><a href="mycategory.ads?id=<%=EncryptionUtil.encode(String.valueOf(rst.get(j).getId())) %>&subid=<%=EncryptionUtil.encode(String.valueOf(rst1.get(i).getId())) %>" target="_blank"><%= rst1.get(i).getName() %></a></li>
																<%
																     }
												                	}
																 %>
															</ul>
														</div>
													</dd>
												<%
													}
												%>        
										</dl>
									</div>
								</h3>
								<div class="item">
									<dl>
										<dt><a href="./mycategory.ads?id=<%=EncryptionUtil.encode(String.valueOf(listSubCategory.get(0).getId())) %>" role="<%=listSubCategory.get(0).getId() %>">
											<%=listSubCategory.get(0).getName() %>
											
										</a></dt>
										<dd>
											<ul>
												<%
													for(int i=0;i<listSubCategory.size();i++){
												%>
													<li><a href="./mycategory.ads?id=<%=EncryptionUtil.encode(String.valueOf(listSubCategory.get(0).getId())) %>&subid=<%=EncryptionUtil.encode(String.valueOf(listSubCategory.get(i).getSubcategory().getId())) %>" role="<%=listSubCategory.get(i).getSubcategory().getId() %>"><%=listSubCategory.get(i).getSubcategory().getName() %></a></li>
												<%
													}
												%>
											</ul>
										</dd>
									</dl>
								</div>
							</div>
							<!-- //navigation -->

							<!-- adv -->
							<div class="adv mgt20">
								<img src="img/adv/adv_detail01.png" alt="">
							</div>
							<!--// adv -->
						</div>
						<!-- lbn_cnt -->

						<!-- ccnt_cnt -->
						<div class="cbn_cnt">
							<!-- ccnt_sec3 -->
							<div class="ccnt_sec3">
								<div class="sub_rbn1">
									<!-- sub_rbn1 -->
									<div class="sub_rbn_cnt">
										<form>
											<legend>Order Form</legend>
											<div class="frm_order">
												<div style="height:100%;background-color:#fff;">
													<table summary="">
														<caption></caption>
														<colgroup>
														<col style="width:100px;">
														<col>
														</colgroup>
														<tbody>
															<tr>
																<th><div>Price</div></th>
																<td><div><strong>US $ <%=postingDto.getPrice() %></strong>/price</div></td>
															</tr>
															<tr>
																<th><div>Sallers</div></th>
																<td><div><%=postingDto.getMemberDto().getName() %></div></td>
															</tr>
															<tr>
																<th><div>Contact</div></th>
																<td><div style="color:blue;"><%=postingDto.getMemberDto().getPhone() %></div></td>
															</tr>
															<tr>
																<th><div>Email</div></th>
																<td><div style="color:blue;text-decoration:underline;"><%=postingDto.getMemberDto().getEmail() %></div></td>
															</tr>
															<tr>
																<th><div>Address</div></th>
																<td><div><%=postingDto.getMemberDto().getAddress() %></div></td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</form>
									</div>
									<!-- //sub_rbn1 -->

									<!-- sub_cnt -->
									<div class="sub_cnt">
										<p class="dsc"><%=postingDto.getTitle() %></p>
										<p><%=postingDto.getKey() %></p>
										 <!-- <p>
											<span class="fav"s><a href="#none"></a><a href="#none"></a><a href="#none"></a><a href="#none"></a><a href="#none"></a></span>
											<span><strong>100.0%</strong> of buyers enjoyed this products (2 votes) &nbsp;&nbsp; | &nbsp;&nbsp; <strong>50</strong> orders</span>
										</p> -->

										<div class="mode" style="padding:0;border:1px solid #eee;">
											<!-- <h2>Key notice</h2> -->
											<div id="cxslide_fade" class="cxslide_fade" style="background-color:#fff;">
												<div class="box" style="height:320px;">
													<ul class="list">
													
													    <%
													        if (postingDto.getImageList().size()<=6){
													    	for(int i=0;i<postingDto.getImageList().size();i++){													    	
													    %>
																<li style="width:535px;height:320px;background-color:#fff;">
																	<a href="#none">
																		<img src="uploads/<%=postingDto.getImageList().get(i).getImage() %>" style="width:auto;height:auto;max-height:320px;">
																		<!-- can add more text here -->
																	</a>
																</li>
														<%
													    		}
													        }else{
						       									for(int i=0;i<6;i++){
														%>
															<li style="width:535px;height:320px;background-color:#fff;">
																	<a href="#none">
																		<img src="uploads/<%=postingDto.getImageList().get(i).getImage() %>" style="width:auto;height:auto;max-height:320px;">
																		<!-- can add more text here -->
																	</a>
															</li>
														<%	
						       									}
													        }
														%>
													</ul>
												</div>
											
												<ul class="btn clearfix" style="height:50px;">
												     <%
													    	for(int i=0;i<postingDto.getImageList().size();i++){													    	
													    %>
													<li>
														<a href="#none">
															<img src="uploads/<%=postingDto.getImageList().get(i).getImage() %>" style="width:79px;height:50px;">
														</a>
													</li>
													<%
													    	}
													%>
												</ul>
											</div>
											<script>
												$('#cxslide_x').cxSlide({
													plus:true,
													minus:true
												});
											
												$('#cxslide_y').cxSlide({
													type:'y'
												});
											
												$('#cxslide_fade').cxSlide({
													events:'mouseover',
													type:'fade',
													speed:300
												});
											</script>
										</div>
									</div>
									<!-- //sub_cnt -->
								</div>
							</div>
							<!-- //ccnt_sec3 -->

							<!-- share_box -->
							<div class="share_box mgt10">
								<div class="share">
									<h4>Share To</h4>
									<p>
										<a href="http://twitter.com/home?status=" title="Share on Twitter" target="_blank"><img src="img/share/twitter.png" alt="" style="width:40px;height:40px;"></a>
										<a href="https://www.facebook.com/sharer/sharer.php?u=" title="Share on Facebook" target="_blank"><img src="img/share/facebook.jpg" alt="" style="width:40px;height:30px;"></a>
										<a href="https://plus.google.com/share?url=" title="Share on Google+" target="_blank"><img src="img/share/google-plus.png" alt="" style="width:40px;height:40px;"></a>
									</p>
									<p>Please shar everything here to you friends to get closely life ................</p>
								</div>
								<div class="adv">
									<img src="img/adv/adv07.png" alt="">
								</div>
							</div>
							<!-- //share_box -->

							<div class="tit_wrap mgt10">
								<h3>Products Description</h3>
							</div>

							<!-- description_cnt -->
							<div class="dsc_cnt">
								<!-- rbn_adv -->
								<div class="rbn_adv" style="border:1px solid;">
									<img src="img/adv/adv_right01.png" width="230" alt="">
								</div>
								<!-- //rbn_adv -->

								<!-- product_description -->
								<div class="pro_dsc">
									<p>
										<%=postingDto.getDsc() %>
									</p>
									<%
										for(int i=0;i<postingDto.getImageList().size();i++){
										
									%>
									<div class="img" style="width:670px;height:auto;">
										<img src="uploads/<%=postingDto.getImageList().get(i).getImage() %>" alt="" style="width:auto;max-width:670px;height:auto;margin-bottom:10px;">
									</div>
									<%
										}
									%>
									<div class="adv mgt30">
										<img src="img/adv/adv08.png" alt="">
									</div>
								</div>
								<!-- //product_description -->
							</div>
							<!-- //description_cnt -->

							<!-- related_products -->
							<div class="related_pro mgt30" style="padding:0 20px;">
								<script	type="text/javascript">
									jQuery(document).ready(
									function()
									{
										//-----------------------------------------
										$("#slider-carousel").carousel({activate: function(){},	timerAnimSlide:400,	infinite:true, resizeItem:{width:100}, responsive:{minWidth:645}});
										//$("#slider-carousel-2").carousel({activate:	function(){}, timerAnimSlide:400, infinite:true, resizeItem:{width:50},	responsive:{minWidth:645}});
										
										//-----------------------------------------
									});
								</script>
								<h3>Related Products</h3>
								<div class="center">
									<div class="wrapper-center">
										<div id="slider-carousel-2">
											<div class="wrapper-setas">
												<a href="#"	class="seta-dir	setaDir"><em class="sprite seta-pag-dir-md"></em></a>
												<a href="#"	class="seta-esq	setaEsq"><em class="sprite seta-pag-esq-md"></em></a>
												
												<div class="clear"></div>
											</div>
											<div id="container-slider-carousel-2" class="container">
												<div id="content-silder-carousel-2"	class="content">
													<div class="wrapper-itens">
													    <%													       
													       for(int i=0;i<listRelativeProduct.size();i++){
													    %>
														 	<div class="item_related">
																<a href="product_details.ads?id=<%=listRelativeProduct.get(i).getMainCategory().getId_security() %>&subid=<%=listRelativeProduct.get(i).getSubCategory().getSubid_security() %>&proid=<%=listRelativeProduct.get(i).getPostingId_security() %>">
																	<span><img src="uploads/<%=listRelativeProduct.get(i).getImage().getImage() %>" alt="" class="img-responsive"></span>
																	<dl>
																		<dt><%=listRelativeProduct.get(i).getTitle() %></dt>
																		<dd><%=listRelativeProduct.get(i).getPrice() %>$</dd>
																		<dd><%=listRelativeProduct.get(i).getKey() %></dd>
																	</dl>
																</a>
														    </div> 
														<%
													       }
														%> 
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- //related_products -->

						</div>
						<!-- ccnt_cnt -->
					</div>
					<!-- //cnt -->
					<a href="#none" class="btn_gotop"><span class="blind">gotop</span></a>
				</div>
				<!-- //container -->
			</div>
			<!-- //body_cnt -->
		</div>
		<!-- //cnt_wraps -->
	</div>
	<!-- //body_section -->

	<!-- footer_section -->
	<div class="footer_section">
		<div class="footer_cnt cboth">
			<p class="f_left">Cambodia Added Years 20016</p>
			<p class="f_right">
				<a href="#none">Recently Viewed</a>
				<ul class="blind">
					<li><a href="#none">1</a></li>
					<li><a href="#none">1</a></li>
					<li><a href="#none">1</a></li>
				</ul>
			</p>
		</div>
	</div>
	<!-- //footer_section -->

</div>
<!-- //wrap -->

</body>
</html>

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
			$("#btn_Click").click(function(e){
				var txt_search=$(this).parent(".opt").find("#txt_search").val();
				var cate	  =$(this).parent(".opt").find("#demo-select_1").val();
				var location  =$(this).parent(".opt").find("#demo-select_2").val();
				var url       =cate+"&product="+txt_search+"&location="+location+"&cp=1";
				if (cate==null || cate==""){
					url="id=&subid="+"&product="+txt_search+"&location="+location+"&cp=1";
				}
				//alert(url);
				window.location.href = "${pageContext.request.contextPath }/search_myproducts.ads?"+url;
				//window.location.href = "${pageContext.request.contextPath }/search_myproducts.ads?"+url;
			});
		});
</script>



<%@page import="myads.model.util.EncryptionUtil"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="myads.model.dto.PostingDto"%>
<%@page import="myads.model.dao.CategoryDao"%>
<%@page import="myads.model.dto.SubCategoryDto"%>
<%@page import="myads.model.dto.MainCategoryDto"%>
<%@page import="java.util.List"%>
<%@page import="myads.model.sqlConnection.SqlConnection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>

<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page isErrorPage="true" import="java.io.*" %>
     <jsp:directive.include file="ads_header.jsp" />
     
	<!-- body_section -->
	<div class="body_section">
		<!-- cnt_wrap -->
		<div class="cnt_wrap" style="position:relative;">

			<!-- banner_wrap -->
			<div class="banner_wrap">
				<!-- banner_cnt -->
				<div class="banner_cnt cboth">
					<!-- lbn -->
					<div class="lbn">
						<dl>
							<dt><strong>CATEGORIES</strong>&nbsp;See all ></dt>
								
								<%
									/* List<MainCategoryDto> rst=CategoryDao.getCategory();
								    List<SubCategoryDto> rst1=CategoryDao.getSubCategory(); */
								    
									for(int j=0;j<rst.size();j++){
								%>
									<dd><a href="mycategory.ads?id=<%=EncryptionUtil.encode(String.valueOf(rst.get(j).getId()))%>" class="<%= rst.get(j).getIco_cls_name() %>"><%= rst.get(j).getName() %></a>
										<div class="more">
											<ul>
												<%
								                	for(int i=0;i<rst1.size();i++)  {  
								                		if (rst.get(j).getId()==rst1.get(i).getId()){
												%>
													<li><a href="mycategory.ads?id=<%=rst.get(j).getId_security() %>&subid=<%=rst1.get(i).getSubid_security() %>" target="_blank"><%= rst1.get(i).getName() %></a></li>
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
					<!-- //lbn -->

					<!-- cnt_wrap -->
					<div class="cnt_wrap">
						<!-- cnt -->
						<div class="cnt cboth">
							<!-- slide_case -->
							<div class="slide_case">
								<div class="slide_cnt">
									<!-- slides_case -->
									<div class="slides_case" style="position:relative;width:716px;height:420px;margin:0 auto;">
										<!-- slide_container-->
										<div id="slider1_container" style="position:absolute;width:716px;height:420px;">
											<!-- Slides Container -->
											<div u="slides" style="cursor:move;position:relative;top:0px;width:716px;height:420px;overflow:hidden;">
												<!-- slide1 -->
												<div>
													<!-- main slide -->
													<img u="image" src="img/bg/bg_banner/red.png"/>
													<!-- //main slide -->

													<!-- sub_slide1 -->
													<div u="caption" t="NO" t3="RTT|2" r3="137.5%" du3="3000" d3="500" style="position:absolute;width:445px;height:300px;top:20px;left:280px;z-index:9999;">
														<img src="img/bg/bg_sub_banner/c-phone.png" style="position:absolute;width:445px;height:300px;top:0px;left:0px;"/>
														<img u="caption" t="CLIP|LR" du="4000" t2="NO" src="img/bg/bg_sub_banner/abc.png" style="position:absolute;width:102px;height:78px;top:70px;left:130px;"/>
														<img u="caption" t="ZMF|10" t2="NO" src="img/bg/bg_sub_banner/text.png" style="position:absolute;width:80px;height:53px;top:153px;left:163px;"/>
														<img u="caption" t="RTT|10" t2="NO" src="img/bg/bg_sub_banner/fruit.png" style="position:absolute;width:140px;height:90px;top:60px;left:220px;"/>
														<img u="caption" t="T" du="3000" t2="NO" src="img/bg/bg_sub_banner/rolling1.png" style="position:absolute;width:200px;height:155px;top:57px;left:121px;"/>
													</div>
													<!-- sub_slide1 -->

													<!-- sub_slide2 -->
													<div u="caption" t="RTT|2" r="-75%" du="1600" d="2500" t2="NO" style="position:absolute;width:470px;height:220px;top:40px;left:220px;z-index:9999;">
														<img src="img/bg/bg_sub_banner/c-phone-horizontal.png" style="position:absolute;width:470px;height:220px;top:0px;left:0px;"/>
														<img u="caption" t3="MCLIP|L" du3="2000" src="img/bg/bg_sub_banner/c-slide-1.jpg" style="position:absolute;width:379px;height:213px;top:4px;left:45px;"/>
														<img u="caption" t="MCLIP|R" du="2000" t2="NO" src="img/bg/bg_sub_banner/c-slide-3.jpg" style="position:absolute;width:379px;height:213px;top:4px; left:45px;"/>
														<img u="caption" t="RTTL|BR" x="500%" y="500%" du="1000" d="-3000" r="-30%"] t3="L" x3="70%" du3="1600" src="img/bg/bg_sub_banner/finger.png" style="position:absolute;width:257px;height:300px;top:80px;left:200px;"/>
														<img src="img/bg/bg_sub_banner/rolling2.png" style="position:absolute;width:379px;height:213px;top:4px;left:45px;"/>
													</div>
													<!-- sub_slide2 -->

													<h2 class="title_case1">First-One</h2>
													<div class="detail_case1">
														<p>Detail</p>
														<p>Detail</p>
														<p>Detail</p>
														<a href="#none">Try it now</a>
													</div>
												</div>
												<!-- //slide1 -->

												<!-- slide2 -->
												<div>
													<img u="image" src="img/bg/bg_banner/purple.jpg"/>
													<h2 class="title_case1">Second-Two</h2>
													<div class="detail_case1">
														<p>Detail</p>
														<a href="#none">Try it now</a>
													</div>
												</div>
												<!-- //slide2 -->

												<!-- slide3 -->
												<div>
													<img u="image" src="img/bg/bg_banner/blue.jpg"/>
													<h2 class="title_case1">Third-Three</h2>
													<div class="detail_case1">
														<p>Detail</p>
														<a href="#none">Try it now</a>
													</div>
												</div>
												<!-- //slide3 -->

												<!-- slide4 -->
												<div>
													<img u="image" src="img/bg/bg_banner/blue.jpg"/>
													<h2 class="title_case1">Third-Three</h2>
													<div class="detail_case1">
														<p>Detail</p>
														<a href="#none">Try it now</a>
													</div>
												</div>
												<!-- //slide4 -->

												<!-- slide5 -->
												<div>
													<img u="image" src="img/bg/bg_banner/blue.jpg"/>
													<h2 class="title_case1">Third-Three</h2>
													<div class="detail_case1">
														<p>Detail</p>
														<a href="#none">Try it now</a>
													</div>
												</div>
												<!-- //slide5 -->
											</div>
											<!-- //Slides Container -->

											<!-- bullet_navigator_container -->
											<div u="navigator" class="jssorb21" style="bottom:-30px;right:6px;">
												<div u="prototype"></div>
											</div>
											<!-- //bullet_navigator_container -->

											<!-- arrow_left -->
											<span u="arrowleft" class="jssora21l"></span>
											<!-- //arrow_left -->

											<!-- arrow_right -->
											<span u="arrowright" class="jssora21r"></span>
											<!-- //arrow_right -->
										</div>
										<!-- slide_container -->
									</div>
									<!-- //slides_case -->
								</div>
							</div>
							<!-- //slide_case -->

							<!-- banner_ads -->
							<div class="banner_ads">
								<div class="adv"><a href="#none"><img src="img/bg/bg_adv1.png" alt=""><span class=""></span></a></div>
								<div class="adv"><a href="#none"><img src="img/bg/bg_adv2.png" alt=""><span class=""></span></a></div>
							</div>
							<!-- //banner_ads -->
						</div>
						<!-- //cnt -->
					</div>
					<!-- //cnt_wrap -->
				</div>
				<!-- //banner_cnt -->
			</div>
			<!-- banner_wrap -->

			<!-- body_cnt -->
			<div class="body_cnt">
				<!-- container -->
				<div class="container">
					<!-- cnt -->
					<div class="cnt cboth">

						<!-- tbl_myproducts_wrap -->
						<div class="tbl_myproducts_wrap">
							<!-- tbl_popular -->
							<div class="tbl_popular">
								<h2 class="poplar">Popular Products<!-- <a href="#none" class="btn_view_more"><span class="count_items"><span class="txt_r"><strong>550</strong></span> Products</span></a> --></h2>
								<div class="mypopular">
									<!-- cover -->
									<div class="cover">
										<img src="img/popular/1.png" alt="" style="width:auto;max-width:283px;height:402px;">
									</div>
									<!-- //cover -->

									<!-- items -->
									<div class="items_wrap">
										<ul>
											<%
												List<PostingDto> popularPosting=category.getPopularPosting();
												for(int i=0;i<popularPosting.size();i++){
											%>
											<li style="text-align: center;">
												<img src="uploads/<%=popularPosting.get(i).getImage().getImage() %>" alt="" style="width:auto;max-width:170px;height:150px;">
												<p><a href="product_details.ads?id=<%=popularPosting.get(i).getMainCategory().getId_security() %>&subid=<%=popularPosting.get(i).getSubCategory().getSubid_security() %>&proid=<%=popularPosting.get(i).getPostingId_security() %>" target="_blank">
													<%=popularPosting.get(i).getTitle() %>													
												</a></p>
											</li>
											<%
												}
											%>				
										</ul>
									</div>
									<!-- //items -->
								</div>
							</div>
							<!-- //tbl_popular -->

							<!-- //tbl_last post -->
							<div class="tbl_popular mgt20">
								<h2 class="poplar">Last Postings<!-- <a href="#none" class="btn_view_more"><span class="count_items"><span class="txt_r"><strong>550</strong></span> Products</span></a> --></h2>
								<div class="mypopular">
									<!-- cover -->
									<div class="cover">
										<img src="img/popular/1.png" alt="" style="width:auto;max-width:283px;height:402px;">
									</div>
									<!-- //cover -->

									<!-- items -->
									<div class="items_wrap">
										<ul>
										    <%
										         List<PostingDto> lastPosting= category.getLastPosting();
										    	 for(int i=0;i<lastPosting.size();i++){
										    %>
											<li style="text-align: center;">
												<img src="uploads/<%=lastPosting.get(i).getImage().getImage() %>" alt="" style="width:auto;max-width:170px;height:150px;">
												<p><a href="product_details.ads?id=<%=lastPosting.get(i).getMainCategory().getId_security() %>&subid=<%=lastPosting.get(i).getSubCategory().getSubid_security() %>&proid=<%=lastPosting.get(i).getPostingId_security() %>" target="_blank">
													<%=lastPosting.get(i).getTitle() %>
												</a></p>
											</li>
											<%
										    	 }
											%>
										</ul>
									</div>
									<!-- //items -->
								</div>
							</div>
							<!-- //tbl_last post -->




							
							<%
					        	
					             //String query3 = "select *  from tbl_category";								
							     List<PostingDto> content=category.getContentBody();
					             for(int j=0;j<rst.size();j++) {
					                //for(rs1.size)
					                 //out.println(rs1.size());
							%>
							<!-- tbl_myproducts -->
							<div class="tbl_myproducts <%= rst.get(j).getIco_cls_name()%> mgt30">
								<!-- category closthes -->
								<div class="womenclothes">
									<h2 class="title" style="padding:0 5px 0 1px;"><a href="mycategory.ads?id=<%=EncryptionUtil.encode(String.valueOf(rst.get(j).getId())) %>"><%= rst.get(j).getName() %></a></h2>
							
									<div class="sub_cat">
										<ul>
											<%
							                     for(int i=0;i<rst1.size();i++){
							                    	 if (rst.get(j).getId()==rst1.get(i).getId()){
											%>
														<li><a href="mycategory.ads?id=<%=rst.get(j).getId_security() %>&subid=<%=rst1.get(i).getSubid_security() %>" class="<%= rst1.get(i).getClass_name() %>" target="_blank"><%= rst1.get(i).getName() %></a></li>
											<%
											     }
							                     }
							                     
											 %>
										</ul>
									</div>
								</div>
								<!-- //category closthes -->

								<!-- product_content -->
								<div class="tbl_product_content">
									<!-- slider -->
									<div class="myslider">
										<div class="slider-wrapper">
											<div class="slider-box"><img src="img/bg/slide-1.jpg" alt="" style="width:359px;height:525px;"></div>
											<div class="slider-box"><img src="img/bg/slide-2.jpg" alt="" style="width:359px;height:525px;"></div>
											<div class="slider-box"><img src="img/bg/slide-3.jpg" alt="" style="width:359px;height:525px;"></div>
											<div class="slider-box"><img src="img/bg/slide-3.jpg" alt="" style="width:359px;height:525px;"></div>
											<div class="slider-box"><img src="img/bg/slide-1.jpg" alt="" style="width:359px;height:525px;"></div>
											<div class="slider-box"><img src="img/bg/slide-2.jpg" alt="" style="width:359px;height:525px;"></div>
										</div>
									</div>
									<!-- //slider -->
									<!-- six_products -->
									<div class="six_products">
										<ul>
										  <%
												for(int index=0;index<content.size();index++){
													if (rst.get(j).getId()==content.get(index).getMainCategory().getId()){
											%>
											<li style="text-align:center;">
												<a href="product_details.ads?id=<%=rst.get(j).getId_security() %>&subid=<%=content.get(index).getSubCategory().getSubid_security() %>&proid=<%=content.get(index).getPostingId_security() %>" target="_blank">
													<img src="uploads/<%=content.get(index).getImage().getImage() %>" alt="" style="width:auto;max-width:204px;height:207px;">
													<dl>
													<dt style="white-space:nowrap;overflow:hidden;"><%=content.get(index).getTitle() %>
													</dt>
													<dd style="text-overflow: ellipsis;white-space:nowrap;overflow:hidden;"><%=content.get(index).getKey() %></dd>
													</dl>
												</a>
											</li>	
											<%
													}
												}
											%>										
										</ul>
									</div>
									<!-- //six_products -->
								
								</div>
								<!-- //product_content -->
							</div>
							<!-- //tbl_myproducts -->

								
							<%
							     }
					            
							%>

						</div>
						<!-- //tbl_myproducts_wrap -->

						<!-- more content -->
						<div class="more_content">
							More Content
						</div>
						<!-- //more content -->
						
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

	<jsp:directive.include file="ads_footer.jsp" />
	<script type="text/javascript">
		$(document).ready(function(e){
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
			});
			$("#profile_setting").click(function(e){				
				if ($(this).parents(".welcomebox").find("div").hasClass("show")){					
					$(this).parents(".welcomebox").find("div").addClass("hide");
					$(this).parents(".welcomebox").find("div").removeClass("show");
				}else{
					$(this).parents(".welcomebox").find("div").removeClass("hide");
					$(this).parents(".welcomebox").find("div").addClass("show");
				}
			});	
		}); 
</script>

<!-- saved from url=(0014)about:internet -->
	<%@page import="java.awt.print.Paper"%>
<%@page import="myads.model.util.EncryptionUtil"%>
<%@page import="myads.model.util.Pagination"%>
<%@page import="myads.model.dto.PostingDto"%>
<jsp:directive.include file="ads_header.jsp" />
	<jsp:directive.include file="ads_help2.jsp" />
	<%
		List<PostingDto> listProdict=(List<PostingDto>) request.getSession().getAttribute("productByCategory");
		List<MainCategoryDto> listSubCategory=(List<MainCategoryDto>) request.getSession().getAttribute("CategoryList");
		List<PostingDto> listRelativeProduct=(List<PostingDto>) request.getSession().getAttribute("listRelative");
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
						<span><a href="./mycategory.ads?id=<%=Pagination.category %>"><%=listSubCategory.get(0).getName() %></a> > </span>
						<%
							for (int i=0;i<listSubCategory.size();i++){
								String subcate=listSubCategory.get(i).getSubcategory().getSubid_security();
								String subCateName=listSubCategory.get(i).getSubcategory().getName();
							if (Pagination.subcategory.trim().equals(subcate.trim())){

						%>
							<span><a href="./mycategory.ads?id=<%=Pagination.category %>&subid=<%=Pagination.subcategory %>"><%=subCateName %></a> > </span>
						<%
							}
						 }
						%>
					</div>
					<!-- cnt -->
					<div class="cnt womenclothes cboth" style="padding-left:220px;padding-right:200px;"><!-- <div class="cnt (switch class) cboth" style="padding-left:220px;padding-right:200px;"> -->
						<!-- lbn_cnt -->
						<div class="lbn_cnt">
							<!-- navigation -->
							<div class="nav">
								<h3 class="storecate">Store Categories
									<!--<a href="#none" class="allcate on"><span class="blind">all categories</span></a>-->
									<a href="javascript:" class="allcate"><span class="blind">all categories</span></a>
									<!-- lbn -->
									<div class="lbn">
										<dl>
											<dt><strong>CATEGORIES</strong>&nbsp;See all ></dt>
												
												<%
													/* List<MainCategoryDto> rst=CategoryDao.getCategory();
												    List<SubCategoryDto> rst1=CategoryDao.getSubCategory(); */
													for(int j=0;j<rst.size();j++){
												%>
													<dd><a href="mycategory.ads?id=<%=rst.get(j).getId_security() %>" class="<%= rst.get(j).getIco_cls_name() %>"><%= rst.get(j).getName() %></a>
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
								</h3>
								<div class="item" id="subCategoryList">
									<dl>
										<dt><a href="./mycategory.ads?id=<%=listSubCategory.get(0).getId_security() %>" role="<%=listSubCategory.get(0).getId() %>">
											<%=listSubCategory.get(0).getName() %>
											
										</a></dt>
										<dd>
											<ul>
												<%
													for(int i=0;i<listSubCategory.size();i++){
												%>
													<li><a href="./mycategory.ads?id=<%=listSubCategory.get(0).getId_security() %>&subid=<%=listSubCategory.get(i).getSubcategory().getSubid_security() %>" role="<%=listSubCategory.get(i).getSubcategory().getId()%>"><%=listSubCategory.get(i).getSubcategory().getName() %></a></li>
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

						<!-- lbn_cnt -->
						<div class="rbn_cnt">
							<div class="adv mgt20">
								<img src="img/com/com_pro3.png" alt="" style="width:200px;">
							</div>
							<div class="adv mgt20">
								<img src="img/com/com_pro3.png" alt="" style="width:200px;">
							</div>
							<div class="adv mgt20">
								<img src="img/com/com_pro3.png" alt="" style="width:200px;">
							</div>
						</div>
						<!-- lbn_cnt -->

						<!-- ccnt_cnt -->
						<div class="cbn_cnt mgb15">
							<!-- table product3 -->
							<div class="tbl_product3">
								<!------------------------------------------- open ----------------------------------------------------------------------------- -->
								<ul id="productsList">
									<%
										for(int i=0;i<listProdict.size();i++){
									%>
									<li style="text-align:center;">
										<a href="product_details.ads?id=<%=listProdict.get(i).getMainCategory().getId_security() %>&subid=<%=listProdict.get(i).getSubCategory().getSubid_security() %>&proid=<%=listProdict.get(i).getPostingId_security() %>" target="_blank">
											<img src="uploads/<%=listProdict.get(i).getImage().getImage() %>" alt="" style="width:auto;max-width:237px;height:250px;">
											<p class="title"><%=listProdict.get(i).getTitle() %></p>
											<p><%=listProdict.get(i).getKey() %></p>
											<dl>
												<dd><strong>US $<%=listProdict.get(i).getPrice() %></strong>/price</dd>
											</dl>
										</a>
									</li>
									<%
										}
									%>
								</ul>
								<!------------------------------------------- close ----------------------------------------------------------------------------- -->
							
							</div>
							<!-- //table product3 -->

							<!-- Paging wrap -->
							<div class="paging_wrap mgt30">
								<!-- pagination -->
								<div class="paging" id="paging"><!-- 비활성상태는 on class 제거 -->
								    <input type="hidden" value="1" id="txtcurrentpage">
								    <input type="hidden" value="<%=Pagination.category %>" id="url-catetory">
								    <input type="hidden" value="<%=Pagination.subcategory %>" id="url-subcatetory">
									<a href="javascript:" class="btn_pag_cntr first pagingIndex" rel="1"><span class="blind">first</span></a><a href="javascript:" class="btn_pag_cntr prev" id="Pre-page"><span class="blind">previous</span></a>
									<span class="pag_num">
									    <a href="javascript:" class="on pagingIndex" rel="1">1<input type="hidden" value="1" class="index"></a>
										<%
											for(int i=2;i<=Pagination.totalpage;i++){
										%>
											<a href="javascript:" class="pagingIndex" rel="<%=i %>"><%=i %></a>
										<%
											}
										%>
									</span>
									<a href="javascript:" class="btn_pag_cntr next on" id="Next-page"><span class="blind">next</span></a><a href="javascript:" class="btn_pag_cntr last on pagingIndex" rel="<%=Pagination.totalpage %>"><span class="blind" >last</span></a>
								</div> 
								<!-- //pagination -->
								
							</div>
							<!-- //Paging wrap -->

							<!-- related_products -->
							<div class="related_pro mgt30">
								<script	type="text/javascript">
									jQuery(document).ready(
									function()
									{
										//-----------------------------------------
										$("#slider-carousel").carousel({activate: function(){},	timerAnimSlide:400,	infinite:true, resizeItem:{width:100}, responsive:{minWidth:645}});
										$("#slider-carousel-2").carousel({activate:	function(){}, timerAnimSlide:400, infinite:true, resizeItem:{width:50},	responsive:{minWidth:645}});
										
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
																<span><img src="img/com/lorempixel.jpg" alt="" class="img-responsive"></span>
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
			
			$("#subCategoryList ul li a").click(function(e){
				var categoryId=$(this).parents("dd").prev("dt").find("a").attr("role");
				var subcategoryId=$(this).attr("role");
			});
			
			$(".paging a.pagingIndex").click(function(e){				
 				$("#txtcurrentpage").val($(this).attr("rel")); 		
				$.ajax({
	    			type : "POST",
	       			url : "${pageContext.request.contextPath }/paging_mycategory.ads",
	    			data :"id="+$("#url-catetory").val()+"&subid="+$("#url-subcatetory").val()+"&cp="+$(this).attr("rel"),
	    			success : function(dat) {    				
	    				console.log(dat);
	    				var html='';
	    				var page='';
	    				for(var i=0;i<dat.length;i++){
	    					html+='<li>'
	    					      +'<a href="product_details.ads?id='+dat[i]["mainCategory"].id_security+'&subid='+dat[i]["subCategory"].subid_security+'&pid='+dat[i]["postingId_security"]+'">'
	    					      +'<img src="uploads/'+dat[i]["image"].image+'" alt="" style="width:236px;height:250px;">'
	    					      +'<p class="title">'+dat[i]["Title"]+'</p>'
	    					      +'<p>'+dat[i]["Key"]+'</p>'
	    					      +'</a><dl>'
	    					      +'<dd><strong>US $'+dat[i]["Price"]+'</strong>/price</dd>'
	    					      +'</dl></li>';	

	    				}
	    				$("#productsList").empty();
	    				$("#productsList").append(html);
	    				
	    				
	    			},
	    			error : function(e) {
	    				console.log("ERROR: ", e);
	    				
	    			},
	    			done : function(e) {
	    				console.log("DONE");
	    			}
	    		}); 
			});
			
			$("#Next-page").click(function(e){
				var total = $("#paging a:last-child").attr("rel");
				var current=parseInt($("#txtcurrentpage").val())+1;
				if (total<current) return;
				$.ajax({
	    			type : "POST",
	       			url : "${pageContext.request.contextPath }/paging_mycategory.ads",
	    			data : "id="+$("#url-catetory").val()+"&subid="+$("#url-subcatetory").val()+"&cp="+current,
	    			success : function(dat) {    				
	    				console.log(dat);
	    				var html='';
	    				for(var i=0;i<dat.length;i++){
	    					html+='<li>'
	    					      +'<a href="product_details.ads?id='+dat[i]["mainCategory"].id_security+'&subid='+dat[i]["subCategory"].subid_security+'&pid='+dat[i]["postingId_security"]+'">'
	    					      +'<img src="uploads/'+dat[i]["image"].image+'" alt="" style="width:236px;height:250px;">'
	    					      +'<p class="title">'+dat[i]["Title"]+'</p>'
	    					      +'<p>'+dat[i]["Key"]+'</p>'
	    					      +'</a><dl>'
	    					      +'<dd><strong>US $'+dat[i]["Price"]+'</strong>/price</dd>'
	    					      +'</dl></li>';							

	    				}
	    				$("#productsList").empty();
	    				$("#productsList").append(html);
	    				$("#txtcurrentpage").val(current);
	    				
	    			},
	    			error : function(e) {
	    				console.log("ERROR: ", e);
	    				
	    			},
	    			done : function(e) {
	    				console.log("DONE");
	    			}
	    		});
			});
			
			$("#Pre-page").click(function(e){
				if($("#txtcurrentpage").val()==1){
					return;
				}
				$("#txtcurrentpage").val($("#txtcurrentpage").val()-1);
				$.ajax({
	    			type : "POST",
	       			url : "${pageContext.request.contextPath }/paging_mycategory.ads",
	    			data : "id="+$("#url-catetory").val()+"&subid="+$("#url-subcatetory").val()+"&cp="+$("#txtcurrentpage").val(),
	    			success : function(dat) {    				
	    				console.log(dat);
	    				var html='';
	    				var page='';
	    				for(var i=0;i<dat.length;i++){
	    					html+='<li>'
	    					      +'<a href="product_details.ads?id='+dat[i]["mainCategory"].id_security+'&subid='+dat[i]["subCategory"].subid_security+'&pid='+dat[i]["postingId_security"]+'">'
	    					      +'<img src="uploads/'+dat[i]["image"].image+'" alt="" style="width:236px;height:250px;">'
	    					      +'<p class="title">'+dat[i]["Title"]+'</p>'
	    					      +'<p>'+dat[i]["Key"]+'</p>'
	    					      +'</a><dl>'
	    					      +'<dd><strong>US $'+dat[i]["Price"]+'</strong>/price</dd>'
	    					      +'</dl></li>';							
						

	    				}
	    				$("#productsList").empty();
	    				$("#productsList").append(html);
	    				
	    				
	    			},
	    			error : function(e) {
	    				console.log("ERROR: ", e);
	    				
	    			},
	    			done : function(e) {
	    				console.log("DONE");
	    			}
	    			
	    		});

			});
		});
	</script>
	<jsp:directive.include file="ads_footer.jsp" />

	
<%@page import="myads.model.dto.PostingDto"%>
<%@page import="myads.model.util.Pagination"%>
<%@page import="myads.model.dao.PostingDao"%>
<%@page import="myads.model.dto.PostingListDto"%>
<jsp:directive.include file="ads_header.jsp" />
	<jsp:directive.include file="ads_help.jsp" />

	<script>
		function register(){
			frmregister.submit();
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
					<div class="mypost_header cboth">
						<div class="f_left cboth">
							<h1 class="f_left"><span style="color:#0174b3;">MyAds's</span> <span style="color:#f7153a;">Posting</span></h1>
						</div>
						<div class="f_right mypost_srch">
							<label><input type="text" placeholder="Find your posts...!" style="width:400px;" id="txtSearch"></label>
							<a href="javascript:" class="btn_create_acc btn_mypost" id="btnSearch">Search</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="lay_newposting.ads" class="btn_create_acc btn_mypost">Add New Post</a>
						</div>
					</div>
					<!-- //mypost header -->
					
					<!-- mypost content -->
					<div class="mypost_cnt mgt20">
						<ul id="productList">
						
							<%
							    int countPage=Pagination.totalpage;
								List<PostingDto> postinglist=(List<PostingDto>)request.getSession().getAttribute("postingList");
								for(int i=0;i<postinglist.size();i++){
							    
							%>
							<li>
								<div class="img"><a href="#none"><img src="uploads/<%=postinglist.get(i).getImage().getImage() %>" alt=""></a></div>
								<dl>
									<dt>Product Name:<a href="product_details.ads?id=<%=postinglist.get(i).getMainCategory().getId_security() %>&subid=<%=postinglist.get(i).getSubCategory().getSubid_security() %>&proid=<%=postinglist.get(i).getPostingId_security() %>">
									<%=postinglist.get(i).getTitle() %></a></dt>
									<dd>Category Type: <%=postinglist.get(i).getSubCategory().getName() %></dd>
									<dd><strong>Price: <%=postinglist.get(i).getPrice() %></strong></dd>
									<dd><strong>Discount: <%=postinglist.get(i).getDiscount() %></strong></dd>
									<dd>Phone Number: <%=postinglist.get(i).getPhone() %></dd>
									<dd>Your Address: <%=postinglist.get(i).getAdr() %></dd>
								</dl>
								<div class="btn_wrap">
									<a href="javascript:" class="btn_post disable_post">Disable Post</a>
									<a href="javascript:" class="btn_post enabl_post" rel="<%=postinglist.get(i).getPostingId() %>" onclick="deleteProduct(this)">Delete Post</a>
									<a href="./edit_product.ads?id=<%=postinglist.get(i).getPostingId() %>" class="btn_post edit_post" rel="<%=postinglist.get(i).getPostingId() %>">Edit Post</a>
								</div>
							</li>
							<%
								}
							%>
						</ul>
					</div>
					<!-- //mypost content -->

					<!-- Paging wrap -->
					<div class="paging_wrap mgt30">
						<!-- pagination -->
						<%
							if (countPage>0){						
							
						%>
						<div class="paging" id="paging"><!-- ë¹íì±ìíë on class ì ê±° -->
						    <input type="hidden" value="1" id="txtcurrentpage">
							<a href="javascript:" class="btn_pag_cntr first indexPage" rel="1" onclick="clickNextPage(this)"><span class="blind">first</span></a><a href="javascript:" class="btn_pag_cntr prev" id="pre-page" onclick="prePerPage()"><span class="blind">previous</span></a>
							<span class="pag_num">
							  <!--   <a href="javascript:" class="on indexPage" onclick="clickNextPage(this)" rel="1">1</a> -->
							    <%
							       for(int i=1;i<=countPage;i++){
							    %>
									<a href="javascript:" rel="<%=i %>" class="indexPage" onclick="clickNextPage(this)"><%=i %></a>
								<%
							       }
								%>
							</span>
							<a href="javascript:" class="btn_pag_cntr next on" id="next-page" onclick="nextPerPage()"><span class="blind">next</span></a><a href="javascript:" class="btn_pag_cntr last on indexPage" rel="<%=countPage %>" onclick="clickNextPage(this)"><span class="blind">last</span></a>
						</div> 
						<!-- //pagination -->
						<%
							}
						%>
					</div>
					<!-- //Paging wrap -->
					
					
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
			
			$("#profile_setting").click(function(e){				
				if ($(this).parents(".welcomebox").find("div").hasClass("show")){					
					$(this).parents(".welcomebox").find("div").addClass("hide");
					$(this).parents(".welcomebox").find("div").removeClass("show");
				}else{
					$(this).parents(".welcomebox").find("div").removeClass("hide");
					$(this).parents(".welcomebox").find("div").addClass("show");
				}
			});	
			/* $("#productList li .btn_wrap a.enabl_post").click(function(e){
				if(!confirm('Do You to Delete Product ID='+$(this).attr("rel"))){
					return ;
				}
					
				$.ajax({
				type : "POST",
       			url : "${pageContext.request.contextPath }/delete_product.ads",
    			data : "id="+$(this).attr("rel")+"&txtSearch="+$("#txtSearch").val()+"&cp=1",
    			success : function(dat) {    
    				console.log(dat);
    				if (dat==false){
    					alert("Delete Product Not Successfull");
    					return ;
    				}else{
    					alert("Delete Product successfull");
    				}
    				var html='';
    				for(var i=0;i<dat[1].length;i++){
    					html+='<li>'
    					      +'<div class="img"><a href="#none"><img src="uploads/'+dat[1][i]["img"]+'" alt=""></a></div>'
    					      +'<dl>'
    					      +'<dt>Product Name:<a href="#none">'+dat[1][i]["ProductName"]+'</a></dt>'
    					      +'<dd>Category Type: '+dat[1][i]["SubCateName"]+'</dd>'
    					      +'<dd><strong>Price: '+dat[1][i]["Price"]+'</strong></dd>'
    					      +'<dd><strong>Discount: '+dat[1][i]["discount"]+'</strong></dd>'
    					      +'<dd>Phone Number: '+dat[1][i]["Phone"]+'</dd>'
						      +'<dd>Your Address: '+dat[1][i]["Adr"]+'</dd>'
						      +'</dl>'
							  +'<div class="btn_wrap"><a href="javascript:" class="btn_post disable_post">Disable Post</a>'
							  +'<a href="javascript:" class="btn_post enabl_post" rel="'+dat[1][i]["PostingId"]+'">Delete Post</a><a href="./edit_product.ads?"'+dat[1][i]["PostingId"]+'"" class="btn_post edit_post" rel="'+dat[1][i]["PostingId"]+'">Edit Post</a>'
							  +'</div></li>';							

    				}
    				var paging="";
    				paging+='<input type="hidden" value="0" id="txtcurrentpage">'
				       +'<a href="javascript:" class="btn_pag_cntr first" rel="1"><span class="blind">first</span></a><a href="javascript:" class="btn_pag_cntr prev"><span class="blind">previous</span></a>'
				       +'<span class="pag_num">'
    				   +'<a href="javascript:" class="on indexPage" >1</a>';
    				for(var i=2;i<=dat[0];i++){
    					       paging+='<a href="javascript:" rel="'+i+'" class="indexPage">'+i+'</a>'	;						
    				}
    				paging+='</span><a href="javascript:" class="btn_pag_cntr next on"><span class="blind">next</span></a><a href="javascript:" class="btn_pag_cntr last on indexPage" rel="'+dat[0]+'"><span class="blind">last</span></a>'
    				console.log(paging);
    				$("#productList").empty();
    				$("#productList").append(html);
    				$("#paging").empty();
    				$("#paging").append(paging);
    				$("#txtcurrentpage").val(1);
    				
    				
    			},
    			error : function(e) {
    				console.log("ERROR: ", e);
    				
    			},
    			done : function(e) {
    				console.log("DONE");
    			}
    		});
			}); */
			$("#btnSearch").click(function(e){
				$.ajax({
	    			type : "POST",
	       			url : "${pageContext.request.contextPath }/lay_search_myadspages.ads",
	    			data : "txtSearch="+$("#txtSearch").val()+"&cp=1",
	    			success : function(dat) {    				
	    				console.log(dat);
	    				var html='';
	    				for(var i=0;i<dat[1].length;i++){
	    					html+='<li>'
	    					      +'<div class="img"><a href="#none"><img src="uploads/'+dat[1][i]["image"].image+'" alt=""></a></div>'
	    					      +'<dl>'
	    					      +'<dt>Product Name:<a href="product_details.ads?id='+dat[1][i]["mainCategory"].id_security+'&subid='+dat[1][i]["subCategory"].subid_security+'&proid='+dat[1][i]["postingId_security"]+'">'+dat[1][i]["Title"]+'</a></dt>'
	    					      +'<dd>Category Type: '+dat[1][i]["subCategory"].name+'</dd>'
	    					      +'<dd><strong>Price: '+dat[1][i]["Price"]+'</strong></dd>'
	    					      +'<dd><strong>Discount: '+dat[1][i]["discount"]+'</strong></dd>'
	    					      +'<dd>Phone Number: '+dat[1][i]["Phone"]+'</dd>'
							      +'<dd>Your Address: '+dat[1][i]["Adr"]+'</dd>'
							      +'</dl>'
								  +'<div class="btn_wrap"><a href="javascript:" class="btn_post disable_post">Disable Post</a>'
								  +'<a href="javascritp:" class="btn_post enabl_post" rel="'+dat[1][i]["PostingId"]+'" onclick="deleteProduct(this)">Delete Post</a><a href="./edit_product.ads?id="'+dat[1][i]["PostingId"]+'" class="btn_post edit_post" rel="'+dat[1][i]["PostingId"]+'">Edit Post</a>'
								  +'</div></li>';							

	    				}
	    				var paging="";
	    				paging+='<input type="hidden" value="0" id="txtcurrentpage">'
					       +'<a href="javascript:" class="btn_pag_cntr first" rel="1" onclick="clickNextPage(this)"><span class="blind">first</span></a><a href="javascript:" onclick="prePerPage()"  class="btn_pag_cntr prev"><span class="blind">previous</span></a>'
					       +'<span class="pag_num">'
	    				   ;
	    				for(var i=1;i<=dat[0];i++){
	    					       paging+='<a href="javascript:" rel="'+i+'" class="indexPage" onclick="clickNextPage(this)">'+i+'</a>'	;						
	    				}
	    				paging+='</span><a href="javascript:" onclick="nextPerPage()" class="btn_pag_cntr next on"><span class="blind">next</span></a><a href="javascript:" class="btn_pag_cntr last on indexPage" rel="'+dat[0]+'" onclick="clickNextPage(this)"><span class="blind">last</span></a>'
	    				console.log(paging);
	    				$("#productList").empty();
	    				$("#productList").append(html);
	    				$("#paging").empty();
	    				$("#paging").append(paging);
	    				
	    				
	    			},
	    			error : function(e) {
	    				console.log("ERROR: ", e);
	    				
	    			},
	    			done : function(e) {
	    				console.log("DONE");
	    			}
	    		});
				
			});
			$( "#txtSearch" ).keypress(function() {
				$( "#btnSearch" ).trigger( "click" ); 
			});
			$( "#txtSearch" ).keyup(function() {
				$( "#btnSearch" ).trigger( "click" ); 
			});
			$( "#txtSearch" ).keydown(function() {
				$( "#btnSearch" ).trigger( "click" ); 
			});
			
/* 			$(".paging a.indexPage").click(function(e){
				$("#txtcurrentpage").val($(this).attr("rel"));
				$.ajax({
	    			type : "POST",
	       			url : "${pageContext.request.contextPath }/lay_search_myadspages.ads",
	    			data : "txtSearch="+$("#txtSearch").val()+"&cp="+$(this).attr("rel"),
	    			success : function(dat) {    				
	    				console.log(dat);
	    				var html='';
	    				var page='';
	    				for(var i=0;i<dat[1].length;i++){
	    					html+='<li>'
	    					      +'<div class="img"><a href="#none"><img src="uploads/'+dat[1][i]["img"]+'" alt=""></a></div>'
	    					      +'<dl>'
	    					      +'<dt>Product Name:<a href="#none">'+dat[1][i]["ProductName"]+'</a></dt>'
	    					      +'<dd>Category Type: '+dat[1][i]["SubCateName"]+'</dd>'
	    					      +'<dd><strong>Price: '+dat[1][i]["Price"]+'</strong></dd>'
	    					      +'<dd><strong>Discount: '+dat[1][i]["discount"]+'</strong></dd>'
	    					      +'<dd>Phone Number: '+dat[1][i]["Phone"]+'</dd>'
							      +'<dd>Your Address: '+dat[1][i]["Adr"]+'</dd>'
							      +'</dl>'
								  +'<div class="btn_wrap"><a href="#none" class="btn_post disable_post">Disable Post</a>'
								  +'<a href="javascript:" class="btn_post enabl_post" rel="'+dat[1][i]["PostingId"]+'">Delete Post</a><a href="./edit_product.ads?"'+dat[1][i]["PostingId"]+'"" class="btn_post edit_post" rel="'+dat[1][i]["PostingId"]+'">Edit Post</a>'
								  +'</div></li>';							

	    				}
	    				$("#productList").empty();
	    				$("#productList").append(html);
	    				$('.paging a').click(function(e) {
	    					$(this).siblings('a').removeClass('on');
	    					$(this).addClass('on');
	    				});
	    				
	    			},
	    			error : function(e) {
	    				console.log("ERROR: ", e);
	    				
	    			},
	    			done : function(e) {
	    				console.log("DONE");
	    			}
	    		});
			}); */
			
			
			/* $("#pre-page").click(function(e){
				if($("#txtcurrentpage").val()==1){
					return;
				}
			
				$("#txtcurrentpage").val($("#txtcurrentpage").val()-1);
				$.ajax({
	    			type : "POST",
	       			url : "${pageContext.request.contextPath }/lay_search_myadspages.ads",
	    			data : "txtSearch="+$("#txtSearch").val()+"&cp="+$("#txtcurrentpage").val(),
	    			success : function(dat) {    				
	    				console.log(dat);
	    				var html='';
	    				var page='';
	    				for(var i=0;i<dat[1].length;i++){
	    					html+='<li>'
	    					      +'<div class="img"><a href="#none"><img src="uploads/'+dat[1][i]["img"]+'" alt=""></a></div>'
	    					      +'<dl>'
	    					      +'<dt>Product Name:<a href="#none">'+dat[1][i]["ProductName"]+'</a></dt>'
	    					      +'<dd>Category Type: '+dat[1][i]["SubCateName"]+'</dd>'
	    					      +'<dd><strong>Price: '+dat[1][i]["Price"]+'</strong></dd>'
	    					      +'<dd><strong>Discount: '+dat[1][i]["discount"]+'</strong></dd>'
	    					      +'<dd>Phone Number: '+dat[1][i]["Phone"]+'</dd>'
							      +'<dd>Your Address: '+dat[1][i]["Adr"]+'</dd>'
							      +'</dl>'
								  +'<div class="btn_wrap"><a href="#none" class="btn_post disable_post">Disable Post</a>'
								  +'<a href="javascript:" class="btn_post enabl_post" rel="'+dat[1][i]["PostingId"]+'">Enable Post</a><a href="./edit_product.ads?"'+dat[1][i]["PostingId"]+'"" class="btn_post edit_post" rel="'+dat[1][i]["PostingId"]+'">Edit Post</a>'
								  +'</div></li>';							

	    				}
	    				$("#productList").empty();
	    				$("#productList").append(html);
	    				
	    				
	    			},
	    			error : function(e) {
	    				console.log("ERROR: ", e);
	    				
	    			},
	    			done : function(e) {
	    				console.log("DONE");
	    			}
	    			
	    		});

			}); */
			
			
			/* $("#next-page").click(function(e){
				var total = $("#paging a:last-child").attr("rel");
				var current=parseInt($("#txtcurrentpage").val())+1;
				if (total<current) return;
				$.ajax({
	    			type : "POST",
	       			url : "${pageContext.request.contextPath }/lay_search_myadspages.ads",
	    			data : "txtSearch="+$("#txtSearch").val()+"&cp="+current,
	    			success : function(dat) {    				
	    				console.log(dat);
	    				var html='';
	    				var page='';
	    				for(var i=0;i<dat[1].length;i++){
	    					html+='<li>'
	    					      +'<div class="img"><a href="#none"><img src="uploads/'+dat[1][i]["img"]+'" alt=""></a></div>'
	    					      +'<dl>'
	    					      +'<dt>Product Name:<a href="#none">'+dat[1][i]["ProductName"]+'</a></dt>'
	    					      +'<dd>Category Type: '+dat[1][i]["SubCateName"]+'</dd>'
	    					      +'<dd><strong>Price: '+dat[1][i]["Price"]+'</strong></dd>'
	    					      +'<dd><strong>Discount: '+dat[1][i]["discount"]+'</strong></dd>'
	    					      +'<dd>Phone Number: '+dat[1][i]["Phone"]+'</dd>'
							      +'<dd>Your Address: '+dat[1][i]["Adr"]+'</dd>'
							      +'</dl>'
								  +'<div class="btn_wrap"><a href="#none" class="btn_post disable_post">Disable Post</a>'
								  +'<a href="javascript:" class="btn_post enabl_post" rel="'+dat[1][i]["PostingId"]+'">delete Post</a><a href="./edit_product.ads?"'+dat[1][i]["PostingId"]+'"" class="btn_post edit_post" rel="'+dat[1][i]["PostingId"]+'">Edit Post</a>'
								  +'</div></li>';							

	    				}
	    				$("#productList").empty();
	    				$("#productList").append(html);
	    				$("#txtcurrentpage").val(current);
	    				
	    			},
	    			error : function(e) {
	    				console.log("ERROR: ", e);
	    				
	    			},
	    			done : function(e) {
	    				console.log("DONE");
	    			}
	    		});
			}); */
		});
		function deleteProduct(obj){
			if(!confirm('Do you want to delete this product...?')){
				return ;
			}
				
			$.ajax({
			type : "POST",
   			url : "${pageContext.request.contextPath }/delete_product.ads",
			data : "id="+$(obj).attr("rel")+"&txtSearch="+$("#txtSearch").val()+"&cp=1",
			success : function(dat) {    
				console.log(dat);
				if (dat==false){
					alert("Delete Product Not Successfull");
					return ;
				}else{
					alert("Delete Product successfull");
				}
				var html='';
				for(var i=0;i<dat[1].length;i++){
					html+='<li>'
					      +'<div class="img"><a href="#none"><img src="uploads/'+dat[1][i]["image"].image+'" alt=""></a></div>'
					      +'<dl>'
					      +'<dt>Product Name:<a href="product_details.ads?id='+dat[1][i]["mainCategory"].id_security+'&subid='+dat[1][i]["subCategory"].subid_security+'&proid='+dat[1][i]["postingId_security"]+'">'+dat[1][i]["Title"]+'</a></dt>'
					      +'<dd>Category Type: '+dat[1][i]["subCategory"].name+'</dd>'
					      +'<dd><strong>Price: '+dat[1][i]["Price"]+'</strong></dd>'
					      +'<dd><strong>Discount: '+dat[1][i]["discount"]+'</strong></dd>'
					      +'<dd>Phone Number: '+dat[1][i]["Phone"]+'</dd>'
					      +'<dd>Your Address: '+dat[1][i]["Adr"]+'</dd>'
					      +'</dl>'
						  +'<div class="btn_wrap"><a href="javascript:" class="btn_post disable_post">Disable Post</a>'
						  +'<a href="javascript:" class="btn_post enabl_post" rel="'+dat[1][i]["PostingId"]+'" onclick="deleteProduct(this)">Delete Post</a><a href="./edit_product.ads?id='+dat[1][i]["PostingId"]+'" class="btn_post edit_post" rel="'+dat[1][i]["PostingId"]+'">Edit Post</a>'
						  +'</div></li>';							

				}
				var paging="";
				paging+='<input type="hidden" value="0" id="txtcurrentpage">'
			       +'<a href="javascript:" class="btn_pag_cntr first" rel="1" onclick="clickNextPage(this)"><span class="blind">first</span></a><a href="javascript:" class="btn_pag_cntr prev" onclick="prePerPage()" ><span class="blind">previous</span></a>'
			       +'<span class="pag_num">'
				  ;
				for(var i=1;i<=dat[0];i++){
					       paging+='<a href="javascript:" rel="'+i+'" class="indexPage" onclick="clickNextPage(this)">'+i+'</a>'	;						
				}
				paging+='</span><a href="javascript:"  class="btn_pag_cntr next on" onclick="nextPerPage()"><span class="blind">next</span></a><a href="javascript:" class="btn_pag_cntr last on indexPage" rel="'+dat[0]+'" onclick="clickNextPage(this)"><span class="blind">last</span></a>'
				console.log(paging);
				$("#productList").empty();
				$("#productList").append(html);
				$("#paging").empty();
				$("#paging").append(paging);
				$("#txtcurrentpage").val('1');
				
				
			},
			error : function(e) {
				console.log("ERROR: ", e);
				
			},
			done : function(e) {
				console.log("DONE");
			}
		});
		}
		
		function nextPerPage(){
			var total = $("#paging a:last-child").attr("rel");
			var current=parseInt($("#txtcurrentpage").val())+1;
			if (total<current) return;
			$.ajax({
    			type : "POST",
       			url : "${pageContext.request.contextPath }/lay_search_myadspages.ads",
    			data : "txtSearch="+$("#txtSearch").val()+"&cp="+current,
    			success : function(dat) {    				
    				console.log(dat);
    				var html='';
    				var page='';
    				for(var i=0;i<dat[1].length;i++){
    					html+='<li>'
    					      +'<div class="img"><a href="#none"><img src="uploads/'+dat[1][i]["image"].image+'" alt=""></a></div>'
    					      +'<dl>'
    					      +'<dt>Product Name:<a href="product_details.ads?id='+dat[1][i]["mainCategory"].id_security+'&subid='+dat[1][i]["subCategory"].subid_security+'&proid='+dat[1][i]["postingId_security"]+'">'+
    					      dat[1][i]["Title"]+'</a></dt>'
    					      +'<dd>Category Type: '+dat[1][i]["subCategory"].name+'</dd>'
    					      +'<dd><strong>Price: '+dat[1][i]["Price"]+'</strong></dd>'
    					      +'<dd><strong>Discount: '+dat[1][i]["discount"]+'</strong></dd>'
    					      +'<dd>Phone Number: '+dat[1][i]["Phone"]+'</dd>'
						      +'<dd>Your Address: '+dat[1][i]["Adr"]+'</dd>'
						      +'</dl>'
							  +'<div class="btn_wrap"><a href="#none" class="btn_post disable_post">Disable Post</a>'
							  +'<a href="javascript:" class="btn_post enabl_post" rel="'+dat[1][i]["PostingId"]+'" onclick="deleteProduct(this)">delete Post</a>'+
							  '<a href="./edit_product.ads?id='+dat[1][i]["PostingId"]+'" class="btn_post edit_post" rel="'+dat[1][i]["PostingId"]+'">Edit Post</a>'
							  +'</div></li>';							

    				}
    				$("#productList").empty();
    				$("#productList").append(html);
    				$("#txtcurrentpage").val(current);
    				
    			},
    			error : function(e) {
    				console.log("ERROR: ", e);
    				
    			},
    			done : function(e) {
    				console.log("DONE");
    			}
    		});
		
		}
		function clickNextPage(obj){
			$("#txtcurrentpage").val($(obj).attr("rel"));
			$.ajax({
    			type : "POST",
       			url : "${pageContext.request.contextPath }/lay_search_myadspages.ads",
    			data : "txtSearch="+$("#txtSearch").val()+"&cp="+$(obj).attr("rel"),
    			success : function(dat) {    				
    				console.log(dat);
    				var html='';
    				var page='';
    				for(var i=0;i<dat[1].length;i++){
    					html+='<li>'
    					      +'<div class="img"><a href="#none"><img src="uploads/'+dat[1][i]["image"].image+'" alt=""></a></div>'
    					      +'<dl>'
    					      +'<dt>Product Name:<a href="product_details.ads?id='+dat[1][i]["mainCategory"].id_security+'&subid='+dat[1][i]["subCategory"].subid_security+'&proid='+dat[1][i]["postingId_security"]+'">'+dat[1][i]["Title"]+'</a></dt>'
    					      +'<dd>Category Type: '+dat[1][i]["subCategory"].name+'</dd>'
    					      +'<dd><strong>Price: '+dat[1][i]["Price"]+'</strong></dd>'
    					      +'<dd><strong>Discount: '+dat[1][i]["discount"]+'</strong></dd>'
    					      +'<dd>Phone Number: '+dat[1][i]["Phone"]+'</dd>'
						      +'<dd>Your Address: '+dat[1][i]["Adr"]+'</dd>'
						      +'</dl>'
							  +'<div class="btn_wrap"><a href="#none" class="btn_post disable_post">Disable Post</a>'
							  +'<a href="javascript:" class="btn_post enabl_post" rel="'+dat[1][i]["PostingId"]+'" onclick="deleteProduct(this)">Delete Post</a><a href="./edit_product.ads?id='+dat[1][i]["PostingId"]+'" class="btn_post edit_post" rel="'+dat[1][i]["PostingId"]+'">Edit Post</a>'
							  +'</div></li>';							

    				}
    				$("#productList").empty();
    				$("#productList").append(html);
    				$('.paging a').click(function(e) {
    					$(this).siblings('a').removeClass('on');
    					$(this).addClass('on');
    				});
    				
    			},
    			error : function(e) {
    				console.log("ERROR: ", e);
    				
    			},
    			done : function(e) {
    				console.log("DONE");
    			}
    		});
		  
		}
		
		function prePerPage(){
			if($("#txtcurrentpage").val()==1){
				return;
			}		
			$("#txtcurrentpage").val($("#txtcurrentpage").val()-1);
			$.ajax({
    			type : "POST",
       			url : "${pageContext.request.contextPath }/lay_search_myadspages.ads",
    			data : "txtSearch="+$("#txtSearch").val()+"&cp="+$("#txtcurrentpage").val(),
    			success : function(dat) {    				
    				console.log(dat);
    				var html='';
    				var page='';
    				for(var i=0;i<dat[1].length;i++){
    					html+='<li>'
    					      +'<div class="img"><a href="#none"><img src="uploads/'+dat[1][i]["image"].image+'" alt=""></a></div>'
    					      +'<dl>'
    					      +'<dt>Product Name:<a href="product_details.ads?id='+dat[1][i]["mainCategory"].id_security+'&subid='+dat[1][i]["subCategory"].subid_security+'&proid='+dat[1][i]["postingId_security"]+'">'+dat[1][i]["Title"]+'</a></dt>'
    					      +'<dd>Category Type: '+dat[1][i]["subCategory"].name+'</dd>'
    					      +'<dd><strong>Price: '+dat[1][i]["Price"]+'</strong></dd>'
    					      +'<dd><strong>Discount: '+dat[1][i]["discount"]+'</strong></dd>'
    					      +'<dd>Phone Number: '+dat[1][i]["Phone"]+'</dd>'
						      +'<dd>Your Address: '+dat[1][i]["Adr"]+'</dd>'
						      +'</dl>'
							  +'<div class="btn_wrap"><a href="#none" class="btn_post disable_post">Disable Post</a>'
							  +'<a href="javascript:" class="btn_post enabl_post" rel="'+dat[1][i]["PostingId"]+'" onclick="deleteProduct(this)">Delete Post</a><a href="./edit_product.ads?id='+dat[1][i]["PostingId"]+'" class="btn_post edit_post" rel="'+dat[1][i]["PostingId"]+'">Edit Post</a>'
							  +'</div></li>';							

    				}
    				$("#productList").empty();
    				$("#productList").append(html);
    				
    				
    			},
    			error : function(e) {
    				console.log("ERROR: ", e);
    				
    			},
    			done : function(e) {
    				console.log("DONE");
    			}
    			
    		});

		}
    </script>

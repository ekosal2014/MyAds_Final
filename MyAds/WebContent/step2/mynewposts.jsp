
<%@page import="myads.model.dto.PostingDto"%>
<%@page import="java.util.List"%>
<jsp:directive.include file="myheader.jsp" />

<script src="js/mylib.js"></script>
<script src="js/mytablelist.js"></script>
<%
	List<PostingDto> listAllPosting=(List<PostingDto>) request.getSession().getAttribute("listAllPosting");
%>
<!-- page content -->
<div role="main" class="pgcnt">
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="x_panel">

				<div class="x_title">
					<h2>All Postings</h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#">Settings 1</a>
								</li>
								<li><a href="#">Settings 2</a>
								</li>
							</ul>
						</li>
						<li><a class="close-link"><i class="fa fa-close"></i></a></li>
					</ul>
					<div class="clearfix"></div>
				</div>
                <input type="hidden" id="newPosting" value="<%=listAllPosting.size() %>">
				<!-- x_content -->
				<div class="x_content">
					<div class="all_posts">
						<ul>
						   <%
						   			for(int i=0;i<listAllPosting.size();i++){
						   %>
							<li>
								<h4>Poster's Name <%=listAllPosting.get(i).getMemberDto().getName() %></h4>
								<dl>
									<dt><img src="../uploads/<%=listAllPosting.get(i).getImageList().get(0).getImage() %>" alt="" style="width:230px;height:240px;"></dt>
									<dd>
										<h4>Product title : <%=listAllPosting.get(i).getTitle() %></h4>
										<p>Category       : <%=listAllPosting.get(i).getSubCategory().getName() %></p>
										<p>Key Notice     : <%=listAllPosting.get(i).getKey() %></p>
										<p><strong><%=listAllPosting.get(i).getPrice() %> $</strong>/Price</p>
										<p>Discount       : <%=listAllPosting.get(i).getDiscount() %></p>
										<p>Phone          : <%=listAllPosting.get(i).getPhone() %></p>
										<p>Address        : <%=listAllPosting.get(i).getAdr()%></p>
									</dd>
								</dl>
								<a href="javascript:" class="btn_moreimg hide">See All Photos</a>
								<a href="javascript:" class="btn_approve">Approve Now 
									 <input type="hidden" id="posting_id" value="<%=listAllPosting.get(i).getPostingId() %>">
									 <input type="hidden" id="member_id" value="<%=listAllPosting.get(i).getMemberDto().getId() %>">
								</a>
								<!-- layer all images -->
								<div class="layer cboth" style="display:none;" id="slide-image">
									<ul>
										<%
											for(int j=0;j<listAllPosting.get(i).getImageList().size();j++){
										%>
											<li><img src="../uploads/<%=listAllPosting.get(i).getImageList().get(j).getImage() %>" alt="" style="width:250px;height:240px;"></li>
										<%
											}
										%>
									</ul>
								</div>
								<!-- //layer all images -->
							</li>
							<%
						   			}
							%>
						</ul>
					</div>
				</div>
				<!-- //x_content -->
				
			</div>
		</div>
	</div>
</div>
<!-- /page content -->


<script src="js/logout.js"></script>
<script>
$(document).ready(function() {

	$('#countNewPosting').text('['+$("#newPosting").val()+']');
	
	$('.mytable').DataTable({
		responsive: true,
		"lengthMenu": [10,15,25,50,],
		"language": {
			 "search":"Search : "
		}
	});
	
	$(".all_posts .btn_moreimg").click(function(e){
		$(this).parent("li").find("#slide-image").css("display","block");
		if ($(this).hasClass("on")){					
			$(this).removeClass("on")
			$(this).addClass("hide")
			$(this).parent("li").find("#slide-image").slideDown();
		}else{
			$(this).removeClass("hide")
			$(this).addClass("on")
			$(this).parent("li").find("#slide-image").slideUp();
		}
	});
	
	$(".all_posts .btn_approve").click(function(e){
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath }/step2/approve_posting.adm",
			data:"postingId="+$(this).find("#posting_id").val()+"&memberid="+$(this).find("#member_id").val(),
			success:function(dat){
				
				if (dat=="true"){
					alert("Approve Product Completed!!")
					window.location.href="../step2/new_posting.adm";
				}else{
					alert("Approve Product Not Completed!!");
				}
				
			}
		});
	});
});
</script>

<jsp:directive.include file="myfooter.jsp" />
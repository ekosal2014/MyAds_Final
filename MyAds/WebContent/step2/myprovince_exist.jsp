<%@page import="myads.model.dto.ProvinceDto"%>
<jsp:directive.include file="myheader.jsp" />

<script>
	function register(){
		frmprovince.submit();
	}
</script>

<%
	ProvinceDto myprovince=(ProvinceDto)request.getAttribute("result");
%>

<!-- page content -->
	<div role="main">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>Your Province <small><a href="myprovince.adm?id=<%= myprovince.getId() %>" class="btn_create">View</a></small></h2>
							<ul class="nav navbar-right panel_toolbox">
								<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
								</li>
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
									<ul class="dropdown-menu" role="menu">
										<li><a href="#">Settings 1</a>
										</li>
										<li><a href="#">Settings 2</a>
										</li>
									</ul>
								</li>
								<li><a class="close-link"><i class="fa fa-close"></i></a>
								</li>
							</ul>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<!-- wrap_function -->
							<div class="wrap_func">
		
								<div class="register">
									<form method="post" action="save_province.adm?id=<%= myprovince.getId() %>" name="frmprovince">
										<table summary="">
											<caption></caption>
											<colgroup>
											<col style="width:200px;">
											<col>
											</colgroup>
											<tbody>
												<tr>
													<th><div>Province Name <span class="require">*</span></div></th>
													<td><div><input type="text" value="<%= myprovince.getName() %>" placeholder="Enter province name" name="txt_province_name" style="width:100%;" required="required"></div></td>
												</tr>
												<tr>
													<th><div>Descriptions</div></th>
													<td><div><textarea style="width:100%;height:70px;" placeholder="Enter descriptions" name="txt_dsc" class="t_c777"><%= myprovince.getDsc() %></textarea></div></td>
												</tr>
												<tr>
													<th colspan="2" class="t_right"><div><!-- <a href="javascript:register()" class="btn_create">Add My Province</a> --><input type="submit" value="Save My Province" class="btn_create"></div></th>
												</tr>
											</tbody>
										</table>
									</form>
								</div>
							</div>
							<!-- //wrap_function -->
						</div>
					</div>
				</div>
			</div>
	</div>
<!-- /page content -->

<jsp:directive.include file="myfooter.jsp" />
<script src="js/logout.js"></script>
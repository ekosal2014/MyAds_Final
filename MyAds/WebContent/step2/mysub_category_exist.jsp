<%@page import="myads.model.dto.SubCategoryDto"%>
<%@page import="myads.model.dao.MyComobox"%>
<%@page import="java.sql.ResultSet"%>
<jsp:directive.include file="myheader.jsp" />

<script>
	function addsubcate(){
		frmsubcategory.submit();
	}
</script>

<%
	SubCategoryDto mysubcate=(SubCategoryDto)request.getAttribute("result");
%>

<!-- page content -->
	<div role="main">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>You Sub Category <small><a href="subcate.adm" class="btn_create">View</a></small></h2>
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
									<form method="post" action="save_subcate.adm?id=<%= mysubcate.getId() %>" name="frmsubcategory">
										<table summary="">
											<caption></caption>
											<colgroup>
											<col style="width:200px;">
											<col>
											</colgroup>
											<tbody>
												<tr>
													<th><div>Category Name <span class="require">*</span></div></th>
													<td>
														<div>
															<select style="width:100%;" name="txt_cateid">
																<option value="0">Choose Category</option>
																<%
																	ResultSet rs=MyComobox.getCategory();
																	while(rs.next()){
																%>
																<option value="<%= rs.getString("CateId") %>"><%= rs.getString("Name") %></option>
																
																<%
																	}
																%>
																<option value="<%=mysubcate.getSubid() %>" selected="selected"><%=mysubcate.getCatename() %></option>
															</select>
														</div>
													</td>
												</tr>
												<tr>
													<th><div class="t_css">Class Icon Name <span class="require">*</span></div></th>
													<td><div><input type="text" value="<%=mysubcate.getClass_name() %>" class="t_css" placeholder="Enter sub category name" name="txt_cls_name" style="width:100%;"></div></td>
												</tr>
												<tr>
													<th><div>Sub Category Name <span class="require">*</span></div></th>
													<td><div><input type="text" value="<%=mysubcate.getName() %>" placeholder="Enter sub category name" name="txt_subcate_name" style="width:100%;"></div></td>
												</tr>
												<tr>
													<th><div>Descriptions</div></th>
													<td><div><textarea style="width:100%;height:70px;" placeholder="Enter descriptions" name="txt_dsc" class="t_c777"><%=mysubcate.getDsc() %></textarea></div></td>
												</tr>
												<tr>
													<th colspan="2" class="t_right"><div><a href="javascript:addsubcate()" class="btn_create">Save My Category</a></div></th>
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
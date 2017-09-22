
<%@page import="myads.model.dao.MyComobox"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>

<jsp:directive.include file="myheader.jsp" />

<script src="js/mylib.js"></script>
<script src="js/mytablelist.js"></script>

<%
	try{
		String url="jdbc:mysql://localhost:3306/ads_db";
		String user="root";
		String password="";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url, user, password);

%>

<!-- page content -->
<div role="main" class="pgcnt">
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="x_panel">

				<div class="x_title">
					<h2>All Sub Categories <small><a href="layad_subcate.adm" class="btn_create">Add New</a> </small></h2>
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

				<!-- x_content -->
				<div class="x_content">
					<form action="layexit_maincate.adm" method="post">
						<table class="mytable">
							<colgroup>
							<col style="width:70px;">
							<col>
							<col>
							<col>
							<col>
							<col>
							<col>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><div align="center">ID</div></th>
									<th scope="col"><div>Category Name</div></th>
									<th scope="col"><div align="center">Class Icon Name</div></th>
									<th scope="col"><div>Sub Category Name</div></th>
									<th scope="col"><div>Description</div></th>
									<th scope="col"><div align="center">Active</div></th>
									<th scope="col"><div style="text-align:center;">Function</div></th>
								</tr>
							</thead>
							<tbody>
								<tr class="gradeU">
								<%
						        	
						             String query1 = "select *  from tbl_category";
						             Statement st1 = con.createStatement();
						             ResultSet rs1 = st1.executeQuery(query1);
						
						             while (rs1.next()) {
						                //for(rs1.size)
						                 //out.println(rs1.size());
								%>
									<td><div align="center"><%= rs1.getString(1)%></div></td>
									<td><div><%= rs1.getString(3)%></div></td>
									<td colspan="5">
										<div>
											
												<%
								                     String query2 = "select * from tbl_sub_category where CateId='" + rs1.getString(1) + "'";
								                     Statement st2 = con.createStatement();
								                     ResultSet rs2 = st2.executeQuery(query2);
								                     while (rs2.next()) {
												%>
							
												
												<table class="subtbl">
													<colgroup>
													<col>
													<col>
													<col>
													<col>
													</colgroup>
													<tbody>
														<tr>
															<td><div class="t_css"><%= rs2.getString(3) %></div></td>
															<td><div><%= rs2.getString(4)%></div></td>
															<td><div align="center" class="t_c777"><%= rs2.getString(5)%></div></td>
															<td><div align="center"><%= rs2.getString(6)%></div></td>
															<td>
																<div align="center">
																	<a href="layexit_subcate.adm?id=<%= rs2.getString(1)%>" class="t_g">edit</i></a>&nbsp;&nbsp;&nbsp;
																	<a href="?id=<%= rs2.getString(1)%>"><i class="t_r">delete</i></a>
																</div>
															</td>
														</tr>
													</tbody>
												</table>
												
												<%
												     }
												 %>				
											
										</div>
									</td>
								</tr>
								<%
								     }
								%>
							</tbody>
						</table> 
						<%
						      } catch (Exception e1) {
						      
						      }
						 %>
					</form>
				</div>
				<!-- //x_content -->
				
			</div>
		</div>
	</div>
</div>
<!-- /page content -->


<script>
$(document).ready(function() {
	$('.mytable').DataTable({
		responsive: true,
		"lengthMenu": [10,15,25,50,],
		"language": {
			 "search":"Search : "
		}
	});
});
</script>

<jsp:directive.include file="myfooter.jsp" />
<script src="js/logout.js"></script>
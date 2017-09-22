
<%@page import="myads.model.dao.MyComobox"%>
<%@page import="java.sql.ResultSet"%>
<jsp:directive.include file="myheader.jsp" />

<script src="js/mylib.js"></script>
<script src="js/mytablelist.js"></script>

<!-- page content -->
<div role="main" class="pgcnt">
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="x_panel">

				<div class="x_title">
					<h2>All Provinces <small><a href="layad_province.adm" class="btn_create">Add New</a> </small></h2>
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
							<col style="width:80px;">
							<col style="width:100px;">
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><div align="center">ID</div></th>
									<th scope="col"><div>Province Name</div></th>
									<th scope="col"><div>Description</div></th>
									<th scope="col"><div align="center">Active</div></th>
									<th scope="col"><div style="text-align:center;">Function</div></th>
								</tr>
							</thead>
							<tbody>
								<%
									ResultSet rs=MyComobox.getlistProvince();
									while(rs.next()){
								%>
								<tr class="gradeU">
									<td><div align="center"><%= rs.getString(1) %></div></td>
									<td><div><%= rs.getString(2) %></div></td>
									<td><div class="t_c777"><%= rs.getString(3) %></div></td>
									<td><div align="center"><%= rs.getString(4) %></div></td>
									<td>
										<div align="center">
											<a href="layexist_province.adm?id=<%= rs.getString(1) %>"><i class="t_g">edit</i></a>&nbsp;&nbsp;&nbsp;
											<a href="?id=<%= rs.getString(1) %>"><i class="t_r">delete</i></a>
										</div>
									</td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
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
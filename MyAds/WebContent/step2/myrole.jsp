<jsp:directive.include file="myheader.jsp" />

<script>
	function register(){
		frmrole.submit();
	}
</script>

<!-- page content -->
	<div role="main">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>You  Role <small><a href="myrole.adm" class="btn_create">View</a> </small></h2>
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
									<form method="post" action="addrole.adm" name="frmrole">
										<table summary="">
											<caption></caption>
											<colgroup>
											<col style="width:200px;">
											<col>
											</colgroup>
											<tbody>
												<tr>
													<th><div>Your Role <span class="require">*</span></div></th>
													<td><div><input type="text" value="" placeholder="Enter your role" name="txt_role" style="width:100%;" required="required"></div></td>
												</tr>
												<tr>
													<th><div>Descriptions</div></th>
													<td><div><textarea style="width:100%;height:70px;" placeholder="Enter descriptions" name="txt_dsc"></textarea></div></td>
												</tr>
												<tr>
													<th colspan="2" class="t_right"><div><!-- <a href="javascript:register()" class="btn_create">Add My Role</a> --><input type="submit" value="Add My Role" class="btn_create"></div></th>
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
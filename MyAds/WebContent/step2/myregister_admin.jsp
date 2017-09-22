<%@page import="myads.model.dao.MyComobox"%>
<%@page import="java.sql.ResultSet"%>
<jsp:directive.include file="myheader.jsp" />

<script>
	function register(){
		frmregister.submit();
	}
</script>

<script src="js/comon.js"></script>

	<!-- page content -->
	<div role="main">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>Register Your Account <small><a href="myadm_acc.adm" class="btn_create">View</a></small></h2>
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
									<form method="post" action="register.adm" name="frmregister">
										<table summary="">
											<caption></caption>
											<colgroup>
											<col style="width:170px;">
											<col style="width:200px;">
											<col>
											</colgroup>
											<tbody>
												<tr>
													<th rowspan="11" scope="col" style="vertical-align:top;padding-top:12px;">
														<div>&nbsp;&nbsp;&nbsp; Your Photo <span class="require">*</span>
															<div class="uploadimage" style="margin-bottom:8px;">
																<a href="#none" class="wrap_img user" title="Brow your photo">
																	<img src="img/bg/bg_teacher.png" class="bg_uploadimg">
																</a>
																<input type="file" class="btn_upload" id="btn_uploadimg" name="txt_photo" style="display:none;" multiple required="required">
																<span style="display:none" class="storeimg"></span>
															</div>
														</div>
													</th>
												</tr>
												<tr>
													<th scope="col"><div>Username <span class="require">*</span></div></th>
													<td><div><input type="text" value="" placeholder="Enter your name" name="txt_name" style="width:100%;" required="required"></div></td>
												</tr>
												<tr>
													<th scope="col"><div>Your Position As <span class="require">*</span></div></th>
													<td>
														<div>
															<select style="width:100%;" name="txt_cateid" required="required">
																<option value="0">Choose your position</option>
																<%
																	ResultSet rs=MyComobox.getPosition();
																	while(rs.next()){
																%>
																<option value="<%= rs.getString("PositId") %>"><%= rs.getString("PositAs") %></option>
																<%
																	}
																%>
															</select>
														</div>
													</td>
												</tr>
												<tr>
													<th scope="col"><div>Gender <span class="require">*</span></div></th>
													<td>
														<div class="gender">
															<label><input type="radio" value="1" checked name="txt_sex" required="required"> Male</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															<label><input type="radio" value="0" name="txt_sex" required="required"> Female</label>
														</div>
													</td>
												</tr>
												<tr>
													<th scope="col"><div>Date of Birth <span class="require">*</span></div></th>
													<td><div><input type="text" value="" placeholder="Enter you date of birth. ex: 01/01/1991" name="txt_dob" style="width:100%;" required="required"></div></td>
												</tr>
												<tr>
													<th scope="col"><div>Create Password <span class="require">*</span></div></th>
													<td><div><input type="password" value="" placeholder="Create password" name="txt_pass" style="width:100%;" required="required"></div></td>
												</tr>
												<tr>
													<th scope="col"><div>Confirm Password <span class="require">*</span></div></th>
													<td><div><input type="password" value="" placeholder="Confirm password" name="txt_confpass" style="width:100%;" required="required"></div></td>
												</tr>
												<tr>
													<th scope="col"><div>Email address <span class="require">*</span></div></th>
													<td><div><input type="text" value="" placeholder="Enter your email address" name="txt_email" style="width:100%;" required="required"></div></td>
												</tr>
												<tr>
													<th scope="col"><div>Phone Number <span class="require">*</span></div></th>
													<td><div><input type="text" value="" placeholder="Enter your phone number" name="txt_phone" style="width:100%;" required="required"></div></td>
												</tr>
												<tr>
													<th scope="col"><div>Your Address <span class="require">*</span></div></th>
													<td><div><textarea style="width:100%;height:70px;" placeholder="Enter your real current address" name="txt_address" required="required"></textarea></div></td>
												</tr>
												<tr>
													<th colspan="2" class="t_right" scope="col"><div><!-- <a href="javascript:register()" class="btn_create">Create My Account</a> --><input type="submit" value="Create My Account" class="btn_create"></div></th>
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
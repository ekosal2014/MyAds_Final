	
	<jsp:directive.include file="ads_header.jsp" />
	<jsp:directive.include file="ads_help.jsp" />

	<script>
		function login(){
			frmlogin.submit();
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
					<div class="mypost_header">
						<div class="cboth f_right" style="padding-right:78px;">
						 
							<h1 class="f_left"><span style="color:#0174b3;">MyAds's</span> <span style="color:#f7153a;">Login Account</span></h1>
						</div>
					</div>
					<!-- //mypost header -->
				
					<!-- wrap_function -->
					<div class="wrap_func">
						<div class="f_left">
							<img src="img/com/myacb.jpg" alt="" style="width:700px;height:;">
						</div>

						<div class="login">
							<form action="login.ads" method="post" name="frmlogin">
								<ul>
									<li>
										<p>Username</p>
										<p><input type="text" value="" placeholder="Enter Username or Email address" name="txt_username" style="width:308px;" required="required"></p>
									</li>
									<li>
										<p>Password <!-- <a href="#none" class="btn_forgot_pass">Forgot Password?</a> --></p>
										<p><input type="password" value="" placeholder="Password" name="txt_password" style="width:308px;" required="required"></p>
									</li>
									<li><!-- <a href="javascript:login()" class="btn_login">Login</a>--><input type="submit" value="Login" class="btn_login" style="width:100%;"> </li>
									<li><p class="f_right"><!-- Login with: <a href="#none"><img src="" alt="facebook">Facebook</a>&nbsp;&nbsp; or &nbsp;&nbsp; --><a href="lay_reg.ads">Create My Account</a></p></li>
								</ul>
							</form>
						</div>
					</div>
					<!-- //wrap_function -->
				</div>
				<!-- //container -->
			</div>
			<!-- //body_cnt -->
		</div>
		<!-- //cnt_wraps -->
	</div>
	<!-- //body_section -->

	<jsp:directive.include file="ads_footer.jsp" />
	<script>
		$(document).ready(function(){
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

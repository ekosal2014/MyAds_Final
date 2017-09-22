<!-- saved from url=(0014)about:internet -->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>We-Care</title>

    <!-- Bootstrap core CSS -->

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">

    <!-- Custom styling plus plugins -->
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/icheck/flat/green.css" rel="stylesheet">
    <link href="css/mycss.css" rel="stylesheet">


    <script src="js/jquery.min.js"></script>
	<script>
		function login(){
			frmlogin.submit();
		}
	</script>

</head>

<body class="body_login">
    
    <div class="">
        <a class="hiddenanchor" id="toregister"></a>
        <a class="hiddenanchor" id="tologin"></a>

        <div id="wrapper">
            <div id="login" class="animate form mylogin">
                <section class="login_content">
                    <form action="myhome.ads" method="post" name="frmlogin">
                        <h1>We-Care</h1>
                        <div>
                            <input type="text" class="form-control" placeholder="Username" name="txt_username" required />
                        </div>
                        <div>
                            <input type="password" class="form-control" placeholder="Password" name="txt_password" required />
                        </div>
                        <div>
                            <!-- <a class="btn btn-default submit" href="javascript:login()">Log in</a> -->
                            <input type="submit" value="Login" style="display:block;width:100%;padding:5px 0;">
                            <a class="reset_pass" href="#none">Lost your password?</a>
                        </div>
                        <div class="clearfix"></div>
                    </form>
                    <!-- form -->
                </section>
                <!-- content -->
            </div>
        </div>
    </div>

</body>

</html>
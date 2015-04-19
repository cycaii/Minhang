<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="BootstrapStyler">

        <title>后台登录</title>
        
        <!-- Bootstrap core CSS -->
<link href="../resources/bootstrap/css/bootstrap.min.css"  
	rel="stylesheet">
        <!-- Font Awesome CSS -->
        <link href="../resources/fonts/font-awesome/css/font-awesome.min.css?v=4.0.3" rel="stylesheet">

        <!-- Bootstrap Switch -->
        <link href="../resources/css/libs/bootstrap-switch.min.css?v=3.0.0" rel="stylesheet">

        <!-- Bootstrap Select -->
        <link href="../resources/css/libs/bootstrap-select.min.css" rel="stylesheet">

        <!-- Summernote -->
        <link href="../resources/css/libs/summernote.css" rel="stylesheet">
        <link href="../resources/css/libs/summernote-bs3.css" rel="stylesheet">

        <!-- IcoMoon CSS -->
        <link href="../resources/fonts/icomoon/style.css?v=1.0" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="../resources/css/styler/style.css" rel="stylesheet" type="text/css">

        <!-- Sign In and Sign Up page styling -->
        <link href="../resources/css/styler/signin.css" rel="stylesheet">

    </head>

    <body style="background-color:#efefef;">        
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-push-4 col-xs-10 col-xs-push-1 col-sm-8 col-sm-push-2">
                    <h1 class="brand brand-big align-center">
                        <a href="index.html">
                            <img src="../image/logo-big.png"><br>
                        </a>
                    </h1>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 col-md-push-4 col-xs-10 col-xs-push-1 col-sm-8 col-sm-push-2">
                    <section id="middle">

                        <div id="content" class="signin-page">

                            <div class="panel-group" id="signin-page">
                            
                                <div class="panel panel-outline panel-no-padding">
                                    <div id="signin" class="panel-collapse collapse in">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">后台登录</h3>
                                        </div>
                                        <div class="panel-body">
                                            <form method="post" action="admin-login.action" id="form-login" class="form-horizontal">
                                                <div class="form-group">
                                                    <div class="col-xs-12">
                                                        <div class="input-group">
                                                            <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                                            <input type="text" id="login-email" name="loginid" class="form-control input-lg" placeholder="用户名">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-xs-12">
                                                        <div class="input-group">
                                                            <span class="input-group-addon"><i class="fa fa-key"></i></span>
                                                            <input type="password" id="login-password" name="pwd" class="form-control input-lg" placeholder="密码">
                                                            <span class="input-group-btn">
                                                                <button class="btn btn-lg btn-primary" type="submit">登录</button>
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group"></div>
                                            </form>
                                        </div><!-- /.panel-body -->
                                    </div><!-- /.panel-collapse -->
                                </div><!-- /.panel -->
                            
                            </div><!-- /.panel-group -->
                        </div><!-- /#content -->

                    </section>
                </div><!-- /.col-md-10 -->

            </div><!-- /.row -->
        </div><!-- /.container -->

        <div class="modal fade" tabindex="-1" role="dialog" id="tnc-modal">
            <div class="modal-dialog modal-lg">
                
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel">Terms &amp; Conditions</h4>
                    </div>
                    <div class="modal-body">
                        <h4>Praesent commodo cursus magna</h4>
                        <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Sed posuere consectetur est at lobortis. Integer posuere erat a ante venenatis dapibus posuere velit aliquet. Nullam quis risus eget urna mollis ornare vel eu leo.</p>

                        <h4>Nullam quis risus eget urna</h4>
                        <p>Maecenas sed diam eget risus varius blandit sit amet non magna. Integer posuere erat a ante venenatis dapibus posuere velit aliquet. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed posuere consectetur est at lobortis. Nulla vitae elit libero, a pharetra augue.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Disagree</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Agree and Proceed Registration</button>
                    </div>
                </div>
            </div>
        </div>


        <!-- jQuery -->
        <script src="../js/libs/jquery-1.11.0.min.js"></script>

        <!-- Bootstrap core JavaScript -->
        <script src="../bootstrap/js/bootstrap.min.js?v=3.1.1"></script>

        <!-- Bootstrap Switch -->
        <script src="../js/libs/bootstrap-switch.min.js?v=3.0.0"></script>

        <!-- Bootstrap Select -->
        <script src="../js/libs/bootstrap-select.min.js"></script>

        <!-- Bootstrap File -->
        <script src="../js/libs/bootstrap-filestyle.js"></script>

        <!-- Sparkline -->
        <script src="../js/libs/jquery.sparkline.min.js"></script>

        <!-- Summernote -->
        <script src="../js/libs/summernote.min.js"></script>

        
        <!-- Theme script -->
        <script src="../js/styler/script.js"></script>

                
    </body>
</html>
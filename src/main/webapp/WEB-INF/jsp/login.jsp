<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html class="bg-black">
<head>
<meta charset="UTF-8">
<title>prepareForInterview | Log in</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<!-- bootstrap 3.0.2 -->
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet" type="text/css" />
<!-- font Awesome -->
<link href="<c:url value="/resources/css/font-awesome.min.css" />"
	rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link href="<c:url value="/resources/css/takeInterview.css" />"
	rel="stylesheet" type="text/css" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
<style type="text/css">
.center-screen {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	text-align: center;
	min-height: 100vh;
}
</style>
</head>
<body class="bg-black">

	<div class="form-box center-screen" id="login-box">
		<!-- <div class="header">Sign In</div> -->
		<form action="<c:url value='/j_spring_security_check' />"
			method='POST'>
			<div class="body bg-gray">
				<div class="form-group">
					<input type="text" name="username" class="form-control"
						placeholder="username" />
				</div>
				<div class="form-group">
					<input type="password" name="password" class="form-control"
						placeholder="Password" />
				</div>
				<!--  <div class="form-group">
                        <input type="checkbox" name="remember_me"/> Remember me
                    </div> -->
			</div>
			<div class="footer">
				<button type="submit" class="btn bg-olive btn-block">Log in</button>

				<!-- <p><a href="#">I forgot my password</a></p>
                    
                    <a href="register.html" class="text-center">Register!</a> -->
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>

		<!-- <div class="margin text-center">
                <span>Sign in using social networks</span>
                <br/>
                <button class="btn bg-light-blue btn-circle"><i class="fa fa-facebook"></i></button>
                <button class="btn bg-aqua btn-circle"><i class="fa fa-twitter"></i></button>
                <button class="btn bg-red btn-circle"><i class="fa fa-google-plus"></i></button>

            </div> -->
	</div>


	<!-- jQuery 2.0.2 -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"
		type="text/javascript"></script>
	<!-- <script src="<c:url value="/resources/js/jquery.js" />"></script>  -->

</body>
</html>
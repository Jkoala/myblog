<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet" type="text/css" media="all" />
<!-- stylesheet -->
<script
	src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>



<!-- nav header -->
<header>
	<div class="container">
		<!-- navigation -->
		<nav class="navbar navbar-default">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<div class="w3-logo">
					<h1>
						<a href="#">Road trip</a>
					</h1>
				</div>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a class="active"
						href="${pageContext.request.contextPath}/index.html">主页</a></li>
					<li><a class="scroll"
						href="${pageContext.request.contextPath}/blogger/aboutMe.html">关于我</a></li>
					<li><a class="scroll"
						href="${pageContext.request.contextPath}/blogger/myAlbum.html">我的相册</a></li>
					<li><a class="scroll"
						href="${pageContext.request.contextPath}/blogger/myCode.html">我的代码</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">小工具<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a class="scroll" href="#w3layouts-team">爬虫</a></li>
							<li><a class="scroll" href="#w3ls-testimonials">摇塞子</a></li>
						</ul></li>
					<li class="dropdown"><a
						href="${pageContext.request.contextPath}/blogger/myAlbum.html"
						class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">个人专栏<span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a class="scroll" href="#w3layouts-team">Algorithm</a></li>
							<li><a class="scroll" href="#w3ls-testimonials">Python</a></li>
						</ul></li>
					<li><a class="scroll" href="#w3-contact">联系我</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>
		<div class="clearfix"></div>
		<!-- //navigation -->
	</div>
</header>
<!-- nav header -->
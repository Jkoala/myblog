<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	//检查输入
	function checkData(){
		var q=document.getElementById("q").value.trim();
		if(q==null || q==""){
			alert("请输入您要查询的关键字！");
			return false;
		}else{
			return true;
		}
	}
	
	function search1(){
		if(checkData()){
			//trim()用于去掉 一个字符串的字头和字尾的空白符
			var q=document.getElementById("q").value.trim();
			//重新加载该页面
			window.location.href="/blog/q.html?q="+q;
		}
	}

</script>


<div class="row">
	<div class="col-md-12" style="padding-top: 10px">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="${pageContext.request.contextPath}/index.html"><font color="black"><strong>首页</strong></font></a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="${pageContext.request.contextPath}/blogger/aboutMe.html"><font color="black"><strong>关于博主</strong></font></a></li>
						<li><a href="${pageContext.request.contextPath}/download.html"><font color="black"><strong>本站源码下载</strong></font></a></li>
					</ul>
					 <div style="float: right; width: 310px; height:50px; padding-top:9px; overflow: hidden;">
						<div class="input-group">
							<input type="text" id="q" name="q" value="${q }" class="form-control" onkeyup= "if(event.keyCode==13)search1()" />
							<div class="input-group-btn">
								<button type="button" onclick="search1()"   class="btn btn-default">搜索</button>
							</div>
						</div>
					</div>
					
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
	</div>
</div>
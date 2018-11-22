<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/component.css"/><!-- css for banner slider -->
    <script src="${pageContext.request.contextPath}/static/js/modernizr.custom.js"></script><!-- js required for banner slider -->
    <script type="text/javascript">
	//检查输入
	function search() {
		var q = document.getElementById("query").value.trim();
		if (q == null || q == "") {
			alert("请输入您要查询的关键字！");
		} else {
			window.location.href = "/blog/q.html?q=" + q;
		}
	}
</script>
<!-- slider -->
<div class="w3-slider">
    <div id="boxgallery" class="boxgallery" data-effect="effect-1">
        <div class="panel"><img src="${pageContext.request.contextPath}/static/images/1.jpg" alt="Image 1"/></div>
        <div class="panel"><img src="${pageContext.request.contextPath}/static/images/2.jpg" alt="Image 2"/></div>
        <div class="panel"><img src="${pageContext.request.contextPath}/static/images/3.jpg" alt="Image 3"/></div>
        <div class="panel"><img src="${pageContext.request.contextPath}/static/images/4.jpg" alt="Image 4"/></div>
    </div>
    <div class="w3layouts-header-title">
        <h2><input placeholder="please search here" style="border: 0px;text-align: center;background-color: transparent;" id="query">
        <a  id="search" style="cursor: pointer;"><img src="/static/images/ico/search.png" alt="search" onclick="search()"></a>
        </h2>	
    </div>
</div><!-- /container -->
<!-- js required for banner slider -->
<script src="${pageContext.request.contextPath}/static/js/classie.js"></script>
<script src="${pageContext.request.contextPath}/static/js/boxesFx.js"></script>
<!-- //js required for banner slider -->
<script>
    new BoxesFx(document.getElementById('boxgallery'));
</script>
<!-- //slider -->
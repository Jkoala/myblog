<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
		<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/layui/css/layui.css">
<%-- <link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css"> --%>
    <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet" type="text/css" media="all"/><!-- stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/blog.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-2.1.4.min.js"></script><!-- Required-js -->
<script
	src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
	
	
<title>${pageTitle}</title>
<style type="text/css">
body {
	padding-top: 0px;
	padding-bottom: 40px;
}
</style>
</head>
<body>
		<jsp:include page="/foreground/common/head.jsp"/>	
		<jsp:include page="/foreground/common/menu.jsp"/>
	<!--main ariticle view-->
<div style="background-color: #e4e4e4;">
    <div class="container">
        <div class="row" id="main_info_row">
            <div class="col-md-9">
					<jsp:include page="${mainPage }"/>
            </div>
            <div class="col-md-3">
                <div class="data_list">
                    <div class="data_list_title">
                        <img
                                src="${pageContext.request.contextPath}/static/images/user_icon.png" />
                        博主信息
                    </div>
                    <div class="user_image">
                        <img
                                src="${pageContext.request.contextPath}/static/userImages/${blogger.imageName}" />
                    </div>
                    <div class="nickName">${blogger.nickName}</div>
                    <div class="userSign">${blogger.sign}</div>
                </div>

                <div class="data_list">
                    <div class="data_list_title">
                        <img
                                src="${pageContext.request.contextPath}/static/images/byType_icon.png" />
                        按日志类别
                    </div>
                    <div class="datas">
                        <ul>
                            <c:forEach var="blogTypeCount" items="${blogTypeCountList }">
                                <li><span><a href="${pageContext.request.contextPath}/index.html?typeId=${blogTypeCount.id}">${blogTypeCount.typeName }(${blogTypeCount.blogCount })</a></span></li>
                            </c:forEach>

                        </ul>

                    </div>
                </div>



                <div class="data_list">
                    <div class="data_list_title">
                        <img
                                src="${pageContext.request.contextPath}/static/images/byDate_icon.png" />
                        按日志日期
                    </div>
                    <div class="datas" >
                        <ul>
                            <c:forEach var="blogCount" items="${blogCountList }">
                                <li><span><a href="${pageContext.request.contextPath}/index.html?releaseDateStr=${blogCount.releaseDateStr}">${blogCount.releaseDateStr }(${blogCount.blogCount })</a></span></li>
                            </c:forEach>

                        </ul>
                    </div>
                </div>

                <div class="data_list">
                    <div class="data_list_title">
                        <img
                                src="${pageContext.request.contextPath}/static/images/link_icon.png" />
                        友情链接
                    </div>
                    <div class="datas">
                        <ul>
                            <c:forEach var="link" items="${linkList }">
                                <li><span><a style=" word-wrap: break-word;" href="${link.linkUrl }" target="_blank">${link.linkName }</a></span></li>
                            </c:forEach>

                        </ul>
                    </div>
                </div>

            </div>
        </div>

    </div>
</div>
<!--main ariticle view-->

		

		<jsp:include page="/foreground/common/foot.jsp"/>

	</div>
</body>
</html>
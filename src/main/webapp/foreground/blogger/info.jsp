<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/info.css">
<div class="about-content">
    <div class="w1000">
        <div class="item-info">
            <div class="title">
                <h3>
                    我的介绍
                </h3>
            </div>
            <div class="cont" style="padding-left: 100px;">
                <img src="${pageContext.request.contextPath}/static/images/aboutMe.jpg">
                <div class="per-info">
                    <p>
                        <span class="name">小消</span><br />
                        <span class="age">20岁</span><br />
                        <span class="Career">武汉纺织大学物联网学生</span><br />
                        <span class="interest" style="line-height:50px;font-size: 24px;font-family:STKaiti;">${blogger.profile }</span>
                    </p>
                </div>
            </div>
        </div>
        <div class="item-contact">
            <div class="title">
                <h3>联系方式</h3>
            </div>
            <div class="cont">
                <div class="text">
                    <p class="WeChat">微信：<span>1234567890</span></p>
                    <p class="qq">qq：<span>123456789</span></p>
                    <p class="iphone">电话：<span>123456789</span></p>
                </div>
            </div>
        </div>
    </div>
</div>
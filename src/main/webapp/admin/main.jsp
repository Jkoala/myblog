<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 让浏览器开启极速模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="renderer" content="webkit">
<!-- 让浏览器开启极速模式 -->


<!-- 引入easyui支持 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/easy-ui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/easy-ui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/easy-ui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/easy-ui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/easy-ui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/easy-ui/locale/easyui-lang-zh_CN.js"></script>
<!-- 引入easyui支持 -->


<style>
/*把easyui的菜单样式简单换一下。 稍微高一点。 颜色变一下  */
#tree ul li div{
	padding-bottom:5px;
	padding-top:5px;
}
#tree li ul li div:hover{
	background-color:#9FF;
}

</style>
<script type="text/javascript">
	//这是一个侧边栏的  展开函数  看能否在layui  找到替代的
	var url;
	function load_tree(){
			//拿到导航的json格式数据
		var treeData;
			/* result是一个拓扑序列 */
		$.post("${pageContext.request.contextPath}/admin/blogger/getTreeMenu.do",{},function(result){ 
	        //console.log(result);
			treeData = eval(result);
			 //console.log(treeData);
			 //tree应该是easyui的一个函数
			   $("#tree").tree({
				   //把整个json数据传过去
				  data:treeData,
				  lines:true,
				  onClick:function(node){
					  if(node.url){
					  	 openTab(node.text,node.url,node.iconCls);//或者把id统计交给openTab处理。
					  }
				  },
				  onLoadSuccess:function(){
				       $("#tree").tree('expandAll');
				  }
			  });	
		});
	}

	//初始化即记载菜单函数
	$(function(){
		load_tree();//加载树形菜单 
	});


	
	function openTab(text,url,iconCls){
		//不弹出框的  函数
		if(text=='修改密码'){
			openPasswordModifyDialog();
			return;
		}
		if(text=='刷新系统缓存'){
			refreshSystem();
			return;
		}
		if(text=='lucene'){
			refreshLucene();
			return;
		}
		if(text=='安全退出'){
			logout();
			return;
		}
		//弹出 的函数
		if($("#tabs").tabs("exists",text)){
			$("#tabs").tabs("select",text);
		}else{
			var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/admin/page/"+url+"'></iframe>";
			$("#tabs").tabs("add",{
				title:text,
				iconCls:iconCls,
				closable:true,
				content:content
			});
		}
	}
	
	
	
	function openPasswordModifyDialog(){
		$("#dlg").dialog("open").dialog("setTitle","修改密码");
		url="${pageContext.request.contextPath}/admin/blogger/modifyPassword.do";
		resetValue();
	}
	//用户名验证函数
	function modifyPassword(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				var newPassword=$("#newPassword").val();
				var newPassword2=$("#newPassword2").val();
				if(!$(this).form("validate")){
					return false;
				}
				if(newPassword!=newPassword2){
					$.messager.alert("系统提示","确认密码输入错误！");
					return false;
				}
				return true;
			},
			success:function(result){
				var result=eval('('+result+')');
				if(result.success){
					$.messager.alert("系统提示","密码修改成功,下一次登录失效！");
					resetValue();
					$("#dlg").dialog("close");
				}else{
					$.messager.alert("系统提示","密码修改失败！");
					return;
				}
			}
		});
	}
	//关闭密码区
	function closePasswordModifyDialog(){
		resetValue();
		$("#dlg").dialog("close");
	}
	//制空
	function resetValue(){
		$("#newPassword").val("");
		$("#newPassword2").val("");
	}
	
	//刷新系统
	function refreshSystem(){
		$.post("${pageContext.request.contextPath}/admin/system/refreshSystem.do",{},function(result){
			if(result.success){
				$.messager.alert("系统提示","已成功刷新系统缓存！");
			}else{
				$.messager.alert("系统提示","刷新系统缓存失败！");
			}
		},"json");
	}
	//刷新系统
	function refreshLucene(){
		$.post("${pageContext.request.contextPath}/admin/system/refreshLucene.do",{},function(result){
			if(result.success){
				$.messager.alert("系统提示","Lucene 刷新成功");
			}else{
				$.messager.alert("系统提示","Lucene 刷新失败");
			}
		},"json");
	}
	//退出登入
	function logout(){
		$.messager.confirm("系统提示","您确定要退出系统吗?",function(r){
			if(r){
				window.location.href="${pageContext.request.contextPath}/admin/blogger/logout.do";
			}
		});
	}
	
	
	
</script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 78px;background-color: #E0ECFF">
	<table style="padding: 5px" width="100%">
		<tr>
			<td width="50%">
				<a href="${pageContext.request.contextPath}/index.html" target="_blank"><img alt="logo" src="${pageContext.request.contextPath}/static/images/logo.png"></a>
				<%-- <a href="${pageContext.request.contextPath}/rentiyishu/list.html" target="_blank"><img alt="logo" src="${pageContext.request.contextPath}/static/images/rentiyishu.png"></a> --%>
			</td>
			<td valign="bottom" align="right" width="50%">
			<!-- 此处做用户登录信息展示 -->
				<%-- <font size="3">&nbsp;&nbsp;<strong>欢迎：</strong>${currrentMemberShip.user.id }</font>【${currrentMemberShip.user.firstName} ${currrentMemberShip.user.lastName}】【${currrentMemberShip.group.name }】 --%>
			</td>
		</tr>
	</table>
</div>
<!-- //开始的正中心部分 -->
<div region="center">
	<div class="easyui-tabs" fit="true" border="false" id="tabs">
		<div title="首页" data-options="iconCls:'icon-home'">
			<div align="center" style="padding-top: 100px"><font color="red" size="10">欢迎使用</font></div>
		</div>
	</div>
</div>
<!-- 导航栏 -->
<div region="west" style="width: 200px" title="导航菜单" split="true">
	<!--  【【【导航】】】 -->
	<ul id="tree" style="margin-top:20px; padding-left:10px;"></ul>
</div>
<!-- 底部 -->
<div region="south" style="height: 25px;padding: 5px" align="center">
	版本所有  <a href="${pageContext.request.contextPath}/index.html" target="_blank">首页</a>
</div>


<!-- //密码区 -->
<div id="dlg" class="easyui-dialog" style="width: 400px;height: 200px;padding: 10px 20px" closed="true" buttons="#dlg-buttons">
	<form id="fm" method="post">
		<table cellspacing="8px">
			<tr>
				<td>用户名：</td>
				<td>
					<input type="text" id="userName" name="userName" value="${currentUser.userName }" readonly="readonly" style="width: 200px"/>
				</td>
			</tr>
			<tr>
				<td>新密码：</td>
				<td>
					<input type="password" id="newPassword" name="newPassword" class="easyui-validatebox" required="true" style="width: 200px"/>
				</td>
			</tr>
			<tr>
				<td>确认新密码：</td>
				<td>
					<input type="password" id="newPassword2" name="newPassword2" class="easyui-validatebox" required="true" style="width: 200px"/>
				</td>
			</tr>
		</table>
	</form>
</div>

<div id="dlg-buttons">
	<a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>


 
 
 
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评论审核页面</title>


<!-- 引入easyui支持 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/easy-ui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/easy-ui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/easy-ui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/easy-ui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/easy-ui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/easy-ui/locale/easyui-lang-zh_CN.js"></script>
<!-- 引入easyui支持 -->



<script type="text/javascript">
	//检查博客是否删除
 	function formatBlogTitle(val,row){
 		if(val==null){
 			return "<font color='red'>该博客已删除！</font>";
 		}else{
 			return "<a target='_blank' href='${pageContext.request.contextPath}/blog/articles/"+val.id+".html'>"+val.title+"</a>";
 		}
 	}
 	
 	//评论的审核
 	function commentReview(state){
 		var selectedRows=$("#dg").datagrid("getSelections");
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要审核的评论！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确定要审核这<font color=red>"+selectedRows.length+"</font>条评论吗？",function(r){
			if(r){
				$.post("${pageContext.request.contextPath}/admin/comment/review.do",{ids:ids,state:state},function(result){
					if(result.success){
						$.messager.alert("系统提示","提交成功！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","提交失败！");
					}
				},"json");
			}
		});
 	}
 	
 	
 	
</script>


</head>
<body style="margin: 1px">
<table id="dg" title="评论审核管理" class="easyui-datagrid" 
  fitColumns="true" pagination="true" rownumbers="true"
  url="${pageContext.request.contextPath}/admin/comment/list.do?state=0" fit="true" toolbar="#tb">
  <thead>
  	<tr>
  		<th field="cb" checkbox="true" align="center"></th>
  		<th field="id" width="20" align="center">编号</th>
  		<th field="blog" width="200" align="center" formatter="formatBlogTitle">博客标题</th>
  		<th field="userIp" width="100" align="center">用户IP</th>
  		<th field="content" width="200" align="center">评论内容</th>
  		<th field="commentDate" width="50" align="center">评论日期</th>
  	</tr>
  </thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:commentReview(1)" class="easyui-linkbutton" iconCls="icon-ok" plain="true">审核通过</a>
		<a href="javascript:commentReview(2)" class="easyui-linkbutton" iconCls="icon-no" plain="true">审核不通过</a>
	</div>
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的备注 </title>


<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<!-- 引入easyui支持 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/easy-ui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/easy-ui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/easy-ui/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/easy-ui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/easy-ui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/easy-ui/locale/easyui-lang-zh_CN.js"></script>
<!-- 引入easyui支持 -->



<script type="text/javascript">

 	var url;
 	//打开备注框  并 url值
	function openLinkAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加备注");
		url="${pageContext.request.contextPath}/admin/remark/save.do";
	}
	//打开修改备注的框
	function openLinkModifyDialog(){
		var selectedRows=$("#dg").datagrid("getSelections");
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一个要修改的备注！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","修改备注信息");
		$("#fm").form("load",row);
		url="${pageContext.request.contextPath}/admin/remark/save.do?id="+row.id;
	}
	//保存  修改后的信息
	function saveLink(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				var result=eval('('+result+')');
				if(result.success){
					$.messager.alert("系统提示","保存成功！");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}else{
					$.messager.alert("系统提示","保存失败！");
					return;
				}
			}
		});
	}
	//清空值
	function resetValue(){
		$('#fm').form('clear');
	}
	//关闭框
	function closeLinkDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	//搜索框
	function search1(){
		var q = $("#q").val();
		$("#dg").datagrid({url:'/admin/remark/list.do',queryParams:{q:q}});
	}
	
	
</script>
</head>
<body style="margin: 1px">
<table id="dg" title="我的备注" class="easyui-datagrid" 
  fitColumns="true" pagination="true" rownumbers="true"
  url="${pageContext.request.contextPath}/admin/remark/list.do" fit="true" toolbar="#tb">
  <thead>
  	<tr>
  		<th field="cb" checkbox="true" align="center"></th>
  		<th field="id" width="20" align="center">编号</th>
  		<th field="remark" width="200" align="center">备注内容</th>
  	</tr>
  </thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:openLinkAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="javascript:openLinkModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		
		 <input type="text" id="q"  onkeydown="if(event.keyCode==13) search1()"  />
<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-search'" onclick="javascript:search1()">搜索</a>
				
				
				
	</div>
</div>
<style type="text/css">
    textarea{ resize:none; width:440px; height:270px;}
</style>

<div id="dlg" class="easyui-dialog" style="width: 600px;height: 400px;padding: 10px 20px" closed="true" buttons="#dlg-buttons">
	<form id="fm" method="post">
		<table cellspacing="8px">		
			<tr>
				<td>备注内容：</td>
				<td>
					<textarea name="remark"></textarea>
				</td>
			</tr>
			
		</table>
	</form>
</div>

<div id="dlg-buttons">
	<a href="javascript:saveLink()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closeLinkDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>
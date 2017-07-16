<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>后台管理</title>
	<link rel="stylesheet" type="text/css" href="../css/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../css/easyui/themes/icon.css">
	<link type="text/css" rel="stylesheet" href="../css/main.css"/>
	<link type="text/css" rel="stylesheet" href="../css/page.css"/>
	<script type="text/javascript" src="../js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="../js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../js/easyui/easyui-lang-zh_CN.js"></script>
	<style type="text/css">
		.panl{
			margin-left: 10px;
		}
		table tr td{
			padding-left: 20px;
		}
		table tr td a{
			font-weight: bold;
			color:red;
		}
	</style>
</head>
<body align="center">
<div style="text-align: center;font-size: 20px;margin-top: 30px;font-weight: bold;">后台管理系统信息录入</div>
<table align="left" cellpadding="0" cellspacing="0" class="topbg">
	<tr>
		<td><a id="prod_add" href="<%=request.getContextPath()%>/console/addprodinit" target="main">商品信息添加</a></td>
	</tr>
</table>
<div class="content">
	<div class="main_content">
		<iframe class="hs_iframe"  id="main" name="main" src="<%=request.getContextPath()%>/console/addprodinit" frameborder="0" marginheight="0" marginwidth="0" scrolling="no"    style="width: 1200px;height: 400px;" >


		</iframe><div class="clearfloat"></div>
	</div>
</div>
</body>
</html>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>网上直销代扣信息查询</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui/themes/icon.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css"/>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui/easyui-lang-zh_CN.js"></script>
	<style type="text/css">
		.panl{
			margin-left: 10px;
		}
		table tr td{
			padding-left: 20px;
		}
	</style>
</head>
<body align="center">
<h2>添加商品基本信息</h2>
<div class="easyui-tabs" style="width:1200px">
	<div title="添加商品信息" style="padding:10px">
		<div class="panel datagrid" style="width: auto;">
			<div class="datagrid-header-inner" style="display: block;">
				<form name="addProdinfoForm" method="post" action="<%=request.getContextPath()%>/console/addprodinfo" id="addProdinfoForm" autocomplete="off">
					<table class="datagrid-htable" border="0" cellspacing="0" cellpadding="0" style="height: 25px;">
						<tbody>
						<tr class="datagrid-header-row">
							<td field="orderDate" class="">
								<div class="datagrid-cell" style=": width152px; text-align: center;"><span>商品编号</span></div>
							</td>
							<td field="orderDate" class="">
								<input type="text" name="id" id="id"/>
							</td>
						</tr>
						<tr class="datagrid-header-row">
							<td field="applyId" class="">
								<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>商品名称</span></div>
							</td>
							<td field="applyId" class="">
								<input type="text" name="name" id="name"/>
							</td>
						</tr>
						<tr class="datagrid-header-row">
							<td field="usrName" class="">
								<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>展示名称</span></div>
							</td>
							<td field="usrName" class="">
								<input type="text" name="displayName" id="displayName"/>
							</td>
						</tr>
						<tr class="datagrid-header-row">
							<td field="bankNo" class="">
								<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>商品数量 </span></div>
							</td>
							<td field="bankNo" class="">
								<input type="text" name="stock" id="stock"/>
							</td>
						</tr>
						<tr class="datagrid-header-row">
							<td field="cardNo" class="">
								<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>商品状态</span></div>
							</td>
							<td field="cardNo" class="">
								<input type="text" name="status" id="status"/>
							</td>
						</tr>
						<tr class="datagrid-header-row">
							<td field="transAmt" class="">
								<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>商品价格（元） </span></div>
							</td>
							<td field="transAmt" class="">
								<input type="text" name="price" id="price"/>
							</td>
						</tr>
						<tr class="datagrid-header-row">
							<td field="transAmt" class="">
								<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>商品描述 </span></div>
							</td>
							<td field="state" class="">
								<input type="text" name="discribe" id="discribe"/>
							</td>
						</tr>
						<tr class="datagrid-header-row">
							<td field="state" class="">
								<div class="datagrid-cell" style="width: 157px; text-align: center;"><span>商品URL地址 </span></div>
							</td>
							<td field="state" class="">
								<input type="text" name="url" id="url"/>
							</td>
						</tr>
						<tr class="datagrid-header-row">
							<td field="state" class="">
							</td>
							<td field="state" class="">
								<button type="submit" class="easyui-linkbutton" id="js-out-excel">&nbsp;&nbsp;&nbsp;&nbsp;录入&nbsp;&nbsp;&nbsp;&nbsp;</button>
							</td>
						</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>
</div>
</div>
</body>
</html>
<script type="text/javascript">

</script>
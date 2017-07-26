<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>订单信息</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css"/>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui/easyui-lang-zh_CN.js"></script>
</head>
<body style="padding-left:10px;">
<div style="margin:20px 0;"></div>
<table id="dg" class="easyui-datagrid" title="商品订单信息查询" style="width:1100px;height:400px"
	   data-options="rownumbers:true,singleSelect:true,url:'prodorderlist',method:'get',toolbar:'#tb'" pagination="true">
	<thead>
	<tr>
		<th data-options="field:'ck',checkbox:true"></th>
		<th data-options="field:'id',width:80">订单编号</th>
		<th data-options="field:'custNo',width:150">客户编号</th>
		<th data-options="field:'status',width:200,align:'right'">订单状态</th>
		<th data-options="field:'createTime',width:80,align:'right'">生成时间</th>
		<th data-options="field:'prodId',display:'none'">订单清单编号</th>
		<th data-options="field:'prodName',width:80">产品名称</th>
		<th data-options="field:'prodPrice',width:82,align:'center'">产品价格（元）</th>
		<th data-options="field:'prodNum',width:120,align:'center'">产品数量</th>
	</tr>
	</thead>
</table>
<div id="tb" style="padding:5px;height:auto">
	<div style="margin-bottom:5px">
		<a href="#" id="modify_orderstatus" class="easyui-linkbutton" iconCls="icon-add" plain="false">修改订单状态</a>
		<a href="#" id="modify_orderinfo" class="easyui-linkbutton" iconCls="icon-edit" plain="false">修改订单信息</a>
	</div>
	<div>
		<!--Date From: <input class="easyui-datebox" style="width:80px">
		To: <input class="easyui-datebox" style="width:80px">-->
		订单编号：<input type="text" id="order_id"/>
		订单状态：
		<select class="easyui-combobox" panelHeight="auto" id="order_status" style="width:100px">
			<option value="1">生成订单</option>
			<option value="2">等待发货</option>
			<option value="3">完成</option>
			<option value="-1">订单取消</option>
			<option value="-2">订单过期</option>
		</select>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search"   id="prod_search">查询</a>
	</div>
</div>
<div style="display:none;">
	<span>选择模式: </span>
	<select onchange="$('#dg').datagrid({singleSelect:(this.value==0)})">
		<option value="0">选择单行</option>
		<option value="1">选择多行</option>
	</select><br/>
	<div style="display:none;">
		SelectOnCheck: <input type="checkbox" checked onchange="$('#dg').datagrid({selectOnCheck:$(this).is(':checked')})"><br/>
		CheckOnSelect: <input type="checkbox" checked onchange="$('#dg').datagrid({checkOnSelect:$(this).is(':checked')})">
	</div>
</div>
<div id="dd" style="display:none;width: 390px;height: 320px;">
	<form name="modifyOrderstatusForm" method="post" action="<%=request.getContextPath()%>/console/modifyorderajax" id="modifyOrderstatusForm" autocomplete="off">
		<table class="datagrid-htable" border="0" cellspacing="0" cellpadding="0" style="height: 25px;">
			<tbody>
			<tr class="datagrid-header-row">
				<td field="orderDate" class="">
					<div class="datagrid-cell" style=": width152px; text-align: center;"><span>订单编号</span></div>
				</td>
				<td field="orderDate" class="">
					<input type="hidden" name="opt" id="opt" value="0"/>
					<input type="hidden" name="id" id="id"/>
					<span id="order_status_id"></span>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="cardNo" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>订单状态</span></div>
				</td>
				<td field="cardNo" class="">
					<select class="easyui-combobox" panelHeight="auto" id="status" name="status" style="width:100px">
						<option value="1">生成订单</option>
						<option value="2">等待发货</option>
						<option value="3">完成</option>
						<option value="-1">订单取消</option>
						<option value="-2">订单过期</option>
					</select>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="state" class="">
				</td>
				<td field="state" class="">
					<button type="button" class="easyui-linkbutton" id="modify_orderstatus_bt">&nbsp;&nbsp;&nbsp;&nbsp;录入&nbsp;&nbsp;&nbsp;&nbsp;</button>
				</td>
			</tr>
			</tbody>
		</table>
	</form>
</div>

<div id="dd2" style="display:none;width: 390px;height: 320px;">
	<form name="modifyOrdersinfoForm" method="post" action="<%=request.getContextPath()%>/console/modifyorderajax" id="modifyOrdersinfoForm" autocomplete="off">
		<table class="datagrid-htable" border="0" cellspacing="0" cellpadding="0" style="height: 25px;">
			<tbody>
			<tr class="datagrid-header-row">
				<td field="orderDate" class="">
					<div class="datagrid-cell" style=": width152px; text-align: center;"><span>订单编号</span></div>
				</td>
				<td field="orderDate" class="">
					<input type="hidden" name="opt" id="opt" value="1"/>
					<input type="hidden" name="id" id="id"/>
					<span id="order_status_id"></span>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="usrName" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>客户编号</span></div>
				</td>
				<td field="usrName" class="">
					<span id="custNo"></span>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="bankNo" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>产品名称 </span></div>
				</td>
				<td field="bankNo" class="">
					<span id="prodName"></span>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="cardNo" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>产品价格（元）</span></div>
				</td>
				<td field="cardNo" class="">
					<input type="text" name="prodPrice" id="prodPrice"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="transAmt" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>产品数量 </span></div>
				</td>
				<td field="transAmt" class="">
					<input type="text" name="prodNum" id="prodNum"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="state" class="">
				</td>
				<td field="state" class="">
					<button type="button" class="easyui-linkbutton" id="modify_orderinfo_bt">&nbsp;&nbsp;&nbsp;&nbsp;录入&nbsp;&nbsp;&nbsp;&nbsp;</button>
				</td>
			</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>
<script type="text/javascript">
    var url = "<%=request.getContextPath()%>/console/prodorderlist";
    function prodsearch() {
        var order_status = $('#order_status').val();
        var order_id = $('#order_id').val();
        var handler = "<%=request.getContextPath()%>/console/prodorderlist?id=" + order_id+"&status="+order_status;
        $('#dg').datagrid('options').url = handler;
        $('#dg').datagrid('reload');
    }
    $('#prod_search').bind('click', function(){
        prodsearch();
    });

    $('#modify_orderstatus').bind('click', function(){
        set_orderinfo();
    });
    $('#modify_orderinfo').bind('click', function(){
        set_prodinfo();
    });

    function set_orderinfo(){
        if($('#dg').datagrid('getSelected') == null || $('#dg').datagrid('getSelected') == ""){
            alert("请选择要修改的商品！");
            return false;
        }
        var queryid = $('#dg').datagrid('getSelected').id;
        if(queryid == ""){
            alert("请选择要修改的商品！");
            return false;
        }
        $.ajax({
            type: "GET",
            url: "<%=request.getContextPath()%>/console/prodorderlist",
            data: {id:queryid},
            dataType: "json",
            success: function(data){
                if(data.total != 0){
                    var rows = data.rows[0];
                    $('#status').combobox('setValue',rows.status);
                    $('#id').val(rows.id);
                    $('#order_status_id').html(rows.id);
                    $('#dd').show();
                    $('#dd').dialog({
                        title: '修改订单状态',
                        width: 400,
                        height: 280,
                        closed: false,
                        cache: false,
                        modal: true
                    });
                } else {
                    alert("加载失败！");
                    window.location.reload();
                }
            }
        });
    }

    $('#modify_orderstatus_bt').bind('click', function(){
        var modurl = "<%=request.getContextPath()%>/console/modifyorderajax";
        $.ajax({
            type: "POST",
            dataType: "html",
            url: modurl,
            data: $('#modifyOrderstatusForm').serialize(),
            success: function(msg){
                var json = eval('(' + msg + ')');
                if(json.success){
                    alert( "提交成功");
                }
                window.location.reload();
            }
        });
    });

    $('#modify_orderinfo_bt').bind('click', function(){
        var modurl = "<%=request.getContextPath()%>/console/modifyorderajax";
        $.ajax({
            type: "POST",
            dataType: "html",
            url: modurl,
            data: $('#modifyOrdersinfoForm').serialize(),
            success: function(msg){
                var json = eval('(' + msg + ')');
                if(json.success){
                    alert( "提交成功");
                }
                window.location.reload();
            }
        });
    });

    function set_prodinfo(){
        if($('#dg').datagrid('getSelected') == null || $('#dg').datagrid('getSelected') == ""){
            alert("请选择要修改的商品！");
            return false;
        }
        var queryid = $('#dg').datagrid('getSelected').id;
        if(queryid == ""){
            alert("请选择要修改的商品！");
            return false;
        }
        $.ajax({
            type: "GET",
            url: "<%=request.getContextPath()%>/console/prodorderlist",
            data: {id:queryid},
            dataType: "json",
            success: function(data){
                if(data.total != 0){
                    var rows = data.rows[0];
                    $('#status').combobox('setValue',rows.status);
                    $('#id').val(rows.prodId);
                    $('#order_status_id').html(rows.id);
                    $('#custNo').html(rows.custNo);
                    $('#prodName').html(rows.prodName);
                    $('#prodPrice').val(rows.prodPrice);
                    $('#prodNum').val(rows.prodNum);
                    $('#dd2').show();
                    $('#dd2').dialog({
                        title: '修改订单信息',
                        width: 400,
                        height: 280,
                        closed: false,
                        cache: false,
                        modal: true
                    });
                } else {
                    alert("加载失败！");
                    window.location.reload();
                }
            }
        });
    }
</script>
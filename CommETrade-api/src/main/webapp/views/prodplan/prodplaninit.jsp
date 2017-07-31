<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>商品信息修改</title>
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
<table id="dg" class="easyui-datagrid" title="活动信息查询" style="width:1100px;height:400px"
	   data-options="rownumbers:true,singleSelect:true,url:'queryprodplanajax',method:'get',toolbar:'#tb'" pagination="true">
	<thead>
	<tr>
		<th data-options="field:'ck',checkbox:true"></th>
		<th data-options="field:'id',width:80">活动编号</th>
		<th data-options="field:'planName',width:150">活动名称</th>
		<th data-options="field:'planDesc',width:200,align:'right'">活动描述</th>
		<th data-options="field:'status',width:80">活动状态</th>
		<th data-options="field:'startTime',width:82,align:'center'">活动开始时间</th>
		<th data-options="field:'endTime',width:120,align:'center'">活动结束时间</th>
		<th data-options="field:'createTime',width:120,align:'center'">创建时间</th>
	</tr>
	</thead>
</table>
<div id="tb" style="padding:5px;height:auto">
	<div style="margin-bottom:5px">
		<a href="#" id="plan_add" class="easyui-linkbutton" iconCls="icon-add" plain="false">添加</a>
		<a href="#" id="plan_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="false">修改</a>
		<a href="#" id="plan_del" class="easyui-linkbutton" iconCls="icon-remove" plain="false">删除</a>
		<a href="#" id="plan_detail" class="easyui-linkbutton" iconCls="icon-remove" plain="false">详情</a>
	</div>
	<div>
		<!--Date From: <input class="easyui-datebox" style="width:80px">
		To: <input class="easyui-datebox" style="width:80px">-->
		活动编号：<input type="text" id="plan_id"/>
		活动名称：<input type="text" id="plan_name"/>
		订单状态：
		<select class="easyui-combobox" panelHeight="auto" id="plan_status" style="width:100px">
			<option value="1">有效</option>
			<option value="2">失效</option>
		</select>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search"   id="plan_search">查找</a>
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
	<form name="addProdinfoForm" method="post" action="<%=request.getContextPath()%>/console/addprodinfo" id="addProdinfoForm" autocomplete="off">
		<table class="datagrid-htable" border="0" cellspacing="0" cellpadding="0" style="height: 25px;">
			<tbody>
			<tr id="order_status_id_tp" class="datagrid-header-row">
				<td field="orderDate" class="">
					<div class="datagrid-cell" style=": width152px; text-align: center;"><span>活动编号</span></div>
				</td>
				<td field="orderDate" class="">
					<input type="hidden" name="opt" id="opt" value="1"/>
					<input type="hidden" name="id" id="id"/>
					<span id="order_status_id"></span>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="applyId" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>活动名称</span></div>
				</td>
				<td field="applyId" class="">
					<input type="text" name="planName" id="planName"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="usrName" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>活动描述</span></div>
				</td>
				<td field="usrName" class="">
					<input type="text" name="planDesc" id="planDesc"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="cardNo" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>活动状态</span></div>
				</td>
				<td field="cardNo" class="">
					<select class="easyui-combobox" panelHeight="auto" id="status" name="status" style="width:100px">
						<option value="1">正常销售</option>
						<option value="0">下架</option>
					</select>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="transAmt" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>活动开始时间 </span></div>
				</td>
				<td field="state" class="">
					<input type="text" name="startTime" id="startTime"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="transAmt" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>活动结束时间 </span></div>
				</td>
				<td field="state" class="">
					<input type="text" name="endTime" id="endTime"/>
				</td>
			</tr>
			<tr id="plan_createTime_tp" class="datagrid-header-row">
				<td field="transAmt" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>创建时间 </span></div>
				</td>
				<td field="state" class="">
					<span id="plan_createTime"></span>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="state" class="">
				</td>
				<td field="state" class="">
					<input type="hidden" value="" id="ismodifyprod"/>
					<button type="button" class="easyui-linkbutton" id="add_prod_bt">&nbsp;&nbsp;&nbsp;&nbsp;录入&nbsp;&nbsp;&nbsp;&nbsp;</button>
				</td>
			</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>
<script type="text/javascript">

    var url = "<%=request.getContextPath()%>/console/queryprodplanajax";
    $('#plan_search').bind('click', function(){
		var plan_id = $('#plan_id').val();
        var plan_name = $('#plan_name').val();
        var plan_status = $('#plan_status').val();

        var handler = "<%=request.getContextPath()%>/console/queryprodplanajax?id=" + plan_id+"&planName="+plan_name+"&status="+plan_status;
		$('#dg').datagrid('options').url = handler;
		$('#dg').datagrid('reload');
    });

    $('#paln_add').bind('click', function(){
        add_prod();
    });
    $('#plan_edit').bind('click', function(){
        set_plannfo();

    });

    function set_plannfo(){
        if($('#dg').datagrid('getSelected') == null || $('#dg').datagrid('getSelected') == ""){
            alert("请选择要修改的活动！");
            return false;
        }
        var queryid = $('#dg').datagrid('getSelected').id;
        if(queryid == ""){
            alert("请选择要修改的活动！");
            return false;
        }
        $.ajax({
            type: "GET",
            url: "<%=request.getContextPath()%>/console/queryprodplanajax",
            data: {id:queryid},
            dataType: "json",
            success: function(data){
                if(data.total != 0){
                    var rows = data.rows[0];
                    $('#plan_createTime_tp').show();
                    $('#order_status_id_tp').show();
                    $('#status').combobox('setValue',rows.status);
                    $('#order_status_id').html(rows.id);
                    $('#planName').val(rows.planName);
                    $('#planDesc').val(rows.planDesc);
                    $('#endTime').val(rows.endTime);
                    $('#startTime').val(rows.startTime);
                    $('#opt').val("1");
                    $('#plan_createTime').html(rows.createTime);
                    $('#add_prod_bt').html("&nbsp;&nbsp;&nbsp;&nbsp;修改&nbsp;&nbsp;&nbsp;&nbsp;")
                    $('#dd').show();
                    $('#dd').dialog({
                        title: '修改活动信息',
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

    $('#add_prod_bt').bind('click', function(){
        if($('#planName').val()=="" || $('#planDesc').val()==""  || $('#endTime').val()=="" ||
            $('#startTime').val()==""  ){
            alert("请填写完整活动信息！");
            return false;
        }
        var modurl = "<%=request.getContextPath()%>/console/modifyprodplan";
        $.ajax({
            type: "POST",
            dataType: "html",
            url: modurl,
            data: $('#addProdinfoForm').serialize(),
            success: function(msg){
                var json = eval('(' + msg + ')');
                if(json.success){
                    alert( "提交成功");
                }
                window.location.reload();
            }
        });
    });
    function add_prod(){
        var rows = data.rows[0];
        $('#status').combobox('setValue',rows.status);
        $('#planName').val("");
        $('#id').val("");
        $('#planDesc').val("");
        $('#endTime').val("");
        $('#startTime').val("");
        $('#opt').val("0");
        $('#plan_createTime_tp').hide();
        $('#order_status_id_tp').hide();
        $('#add_prod_bt').html("&nbsp;&nbsp;&nbsp;&nbsp;录入&nbsp;&nbsp;&nbsp;&nbsp;")
        $('#dd').show();
        $('#dd').dialog({
            title: '添加商品',
            width: 400,
            height: 280,
            closed: false,
            cache: false,
            modal: true
        });
    }


    $('#plan_del').bind('click', function(){
        var delid = $('#dg').datagrid('getSelected').id;
        //var rows = $('#dg').datagrid('getSelections');
        //var id = rows[0].id;
        if(delid == ""){
            alert("请选择要删除的活动！");
            return false;
        }
        $.ajax({
            type: "GET",
            url: "<%=request.getContextPath()%>/console/delprodinfoajax?opt=2",
            data: {id:delid},
            dataType: "json",
            success: function(data){
                if(data.success){
                    alert( "删除成功！");
                } else {
                    alert( "删除失败！");
                }
                window.location.reload();
            }
        });
    });

    $('#plan_detail').bind('click', function(){
        if($('#dg').datagrid('getSelected') == null || $('#dg').datagrid('getSelected') == ""){
            alert("请选择活动信息！");
            return false;
        }
        var queryid = $('#dg').datagrid('getSelected').id;
        if(queryid == ""){
            alert("请选择活动信息！");
            return false;
        }
        window.location.href="<%=request.getContextPath()%>/console/prodplandetailinit?planNo="+queryid;
    });

</script>
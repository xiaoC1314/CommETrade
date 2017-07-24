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
<table id="dg" class="easyui-datagrid" title="活动详情信息" style="width:1100px;height:400px"
	   data-options="rownumbers:true,singleSelect:true,method:'get',toolbar:'#tb'" pagination="true">
	<thead>
	<tr>
		<th data-options="field:'ck',checkbox:true"></th>
		<th data-options="field:'id',width:80">活动明细编号</th>
		<th data-options="field:'planNo',width:150">活动编号</th>
		<th data-options="field:'prodNo',width:200,align:'right'">产品编号</th>
		<th data-options="field:'order',width:80">排列序号</th>
		<th data-options="field:'createTime',width:120,align:'center'">创建时间</th>
	</tr>
	</thead>
</table>
<div id="tb" style="padding:5px;height:auto">
	<div style="margin-bottom:5px">
		<a href="#" id="plan_add" class="easyui-linkbutton" iconCls="icon-add" plain="false">添加</a>
		<a href="#" id="plan_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="false">修改</a>
		<a href="#" id="plan_del" class="easyui-linkbutton" iconCls="icon-remove" plain="false">删除</a>
	</div>
	<div>
		<!--Date From: <input class="easyui-datebox" style="width:80px">
		To: <input class="easyui-datebox" style="width:80px">-->
		活动编号：<input type="text" id="plan_no"/>
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
	<form name="modifyProdplandetailForm" method="post" action="<%=request.getContextPath()%>/console/modifyprodplandetail" id="modifyProdplandetailForm" autocomplete="off">
		<table class="datagrid-htable" border="0" cellspacing="0" cellpadding="0" style="height: 25px;">
			<tbody>
			<tr id="plan_detail_id_tip" class="datagrid-header-row">
				<td field="orderDate" class="">
					<div class="datagrid-cell" style=": width152px; text-align: center;"><span>活动明细编号</span></div>
				</td>
				<td field="orderDate" class="">
					<input type="hidden" name="opt" id="opt" value="1"/>
					<input type="hidden" name="id" id="id"/>
					<span id="plan_detail_id"></span>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="applyId" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>活动编号</span></div>
				</td>
				<td field="applyId" class="">
					<input type="text" name="planNo" id="planNo"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="usrName" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>产品编号</span></div>
				</td>
				<td field="usrName" class="">
					<input type="text" name="prodNo" id="prodNo"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="cardNo" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>排列序号</span></div>
				</td>
				<td field="cardNo" class="">
					<input type="text" name="order" id="order"/>
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
    var url = "<%=request.getContextPath()%>/console/queryprodplandetailajax";
    var plan_no ='${planNo}';
    $(document).ready(function(){
        serchdetail(plan_no);
    });
    $('#plan_search').bind('click', function(){
        var plan_id = $('#plan_no').val();
        serchdetail(plan_id);
    });
    function serchdetail(plan_no{
		if(plan_no==""){
		    return false;
		}
        var handler = "<%=request.getContextPath()%>/console/queryprodplandetailajax?planNo=" + plan_no;
        $('#dg').datagrid('options').url = handler;
        $('#dg').datagrid('reload');
	}


    $('#plan_add').bind('click', function(){
        add_prod();
    });
    $('#plan_edit').bind('click', function(){
        set_prodinfo();

    });

    function set_prodinfo(){
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
            url: "<%=request.getContextPath()%>/console/queryprodplandetailajax",
            data: {id:queryid},
            dataType: "json",
            success: function(data){
                if(data.total != 0){
                    var rows = data.rows[0];
                    $('#plan_detail_id_tip').show();
                    $('#plan_createTime_tp').show();
                    $('#plan_detail_id').html(rows.id);
                    $('#planNo').val(rows.planNo);
                    $('#prodNo').val(rows.prodNo);
                    $('#order').val(rows.order);
                    $('#plan_createTime').html(rows.createTime);
                    $('#opt').val("1");
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
        if($('#id').val()=="" || $('#name').val()=="" || $('#displayName').val()=="" || $('#stock').val()=="" ||
            $('#price').val()=="" || $('#discribe').val()=="" || $('#url').val()=="" ){
            alert("请填写完整商品信息！");
            return false;
        }
        var modurl = "<%=request.getContextPath()%>/console/modifyprodplandetail";
        $.ajax({
            type: "POST",
            dataType: "html",
            url: modurl,
            data: $('#modifyProdplandetailForm').serialize(),
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
        $('#planNo').val("");
        $('#prodNo').val("");
        $('#order').val("");
        $('#plan_detail_id_tip').hide();
        $('#plan_createTime_tp').hide();
        $('#opt').val("0");
        $('#add_prod_bt').html("&nbsp;&nbsp;&nbsp;&nbsp;新增&nbsp;&nbsp;&nbsp;&nbsp;")
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
        //var delid = $('#dg').datagrid('getSelected').id;
        var rows = $('#dg').datagrid('getSelections');
        if(rows.length == 0){
            alert("请选择要删除的信息！");
            return false;
        }
        var setids = rows[0].id;
        for(var i=1; i<rows.length; i++){
            setids = setids+","+rows[i].id;
        }
        $.ajax({
            type: "GET",
            url: "<%=request.getContextPath()%>/console/modifyprodplandetail?opt=2",
            data: {id:setids},
            dataType: "json",
            success: function(data){
                if(data.success){
                    alert( "删除成功");
                } else {
                    alert( "删除失败，请从新录入！");
                }
                window.location.reload();
            }
        });

    });
</script>
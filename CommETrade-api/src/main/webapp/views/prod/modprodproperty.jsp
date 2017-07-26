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
<table id="dg" class="easyui-datagrid" title="商品属性信息修改" style="width:1100px;height:400px"
	   data-options="rownumbers:true,singleSelect:true,url:'prodprotylist',method:'get',toolbar:'#tb'" pagination="true">
	<thead>
	<tr>
		<th data-options="field:'id',width:80">属性编号</th>
		<th data-options="field:'prodNo',width:80">商品编号</th>
		<th data-options="field:'propKey',width:100">属性key</th>
		<th data-options="field:'propName',width:100,align:'left'">属性名称</th>
		<th data-options="field:'propValue',width:220,align:'left'">属性值</th>
		<th data-options="field:'propDiscribe',width:180">属性描述</th>
		<th data-options="field:'status',width:60,
						formatter:function(value,row){
							if(value == '0')
								return '无效';
							else
								return '有效';
						}">属性状态</th>

	</tr>
	</thead>
</table>
<div id="tb" style="padding:5px;height:auto">
	<div style="margin-bottom:5px">
		<a href="#" id="prod_add" class="easyui-linkbutton" iconCls="icon-add" plain="false">添加</a>
		<a href="#" id="prod_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="false">修改</a>
		<a href="#" id="prod_del" class="easyui-linkbutton" iconCls="icon-remove" plain="false">删除</a>
	</div>
	<div>
		产品代码：<input type="text" name="prod_no" id="prod_no"/>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" id="prod_search">查找</a>
	</div>
</div>
<div style="margin:10px 0;">
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
			<tr class="datagrid-header-row">
				<td field="orderDate" class="">
					<div class="datagrid-cell" style=": width152px; text-align: center;"><span>商品编号</span></div>
				</td>
				<td field="orderDate" class="">
					<input type="text" name="prodNo" id="prodNo"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="applyId" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>属性key</span></div>
				</td>
				<td field="applyId" class="">
					<input type="text" name="propKey" id="propKey"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="usrName" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>属性名称</span></div>
				</td>
				<td field="usrName" class="">
					<input type="text" name="propName" id="propName"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="bankNo" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>属性值 </span></div>
				</td>
				<td field="bankNo" class="">
					<input type="text" name="propValue" id="propValue"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="bankNo" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>属性描述 </span></div>
				</td>
				<td field="bankNo" class="">
					<input type="text" name="propDiscribe" id="propDiscribe"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="cardNo" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>属性状态</span></div>
				</td>
				<td field="cardNo" class="">
					<select class="easyui-combobox" panelHeight="auto" id="status" name="status" style="width:100px">
						<option value="1">有效</option>
						<option value="0">无效</option>
					</select>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="state" class="">
				</td>
				<td field="state" class="">
					<input type="hidden" value="" id="ismodifyprod"/>
					<button type="button" class="easyui-linkbutton" id="add_prod_bt">&nbsp;&nbsp;&nbsp;&nbsp;添加&nbsp;&nbsp;&nbsp;&nbsp;</button>
				</td>
			</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>
<script type="text/javascript">
    var url = "<%=request.getContextPath()%>/console/prodprotylist";
    function prodsearch() {
        var prod_no = $('#prod_no').val();
        if(prod_no == null || prod_no == ""){
            alert("请输入商品编号！");
            return false;
        }
        var handler = "<%=request.getContextPath()%>/console/prodprotylist?prodNo=" + prod_no;
        $('#dg').datagrid('options').url = handler;
        $('#dg').datagrid('reload');
    }
    $('#prod_search').bind('click', function(){
        prodsearch();
    });

    $('#prod_add').bind('click', function(){
        add_prod();

    });
    $('#prod_edit').bind('click', function(){
        set_prodinfo();

    });
    function set_prodinfo(){
        if($('#dg').datagrid('getSelected') == null || $('#dg').datagrid('getSelected') == ""){
            alert("请选择要修改的属性！");
            return false;
        }
        var queryid = $('#dg').datagrid('getSelected').id;
        if(queryid == ""){
            alert("请选择要修改的属性！");
            return false;
        }
        $.ajax({
            type: "GET",
            url: "<%=request.getContextPath()%>/console/prodprotylist",
            data: {id:queryid},
            dataType: "json",
            success: function(data){
                if(data.rows != null){
                    var rows = data.rows[0];
                    $('#status').combobox('setValue',rows.status);
                    $('#prodNo').val(rows.prodNo);
                    $('#propKey').val(rows.prodNo);
                    $('#propName').val(rows.propName);
                    $('#propValue').val(rows.propValue);
                    $('#propDiscribe').val(rows.propDiscribe);
                    $('#ismodifyprod').val("1");
                    $('#add_prod_bt').html("&nbsp;&nbsp;&nbsp;&nbsp;修改&nbsp;&nbsp;&nbsp;&nbsp;")
                    $('#dd').show();
                    $('#dd').dialog({
                        title: '修改商品属性',
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


    $('#prod_del').bind('click', function(){
        //var delid = $('#dg').datagrid('getSelected').id;
        var rows = $('#dg').datagrid('getSelections');
        if(rows.length == 0){
            alert("请选择要删除的数据！");
            return false;
        }
        var setids = rows[0].id;
        for(var i=1; i<rows.length; i++){
            setids = setids+","+rows[i].id;
        }
        $.ajax({
            type: "GET",
            url: "<%=request.getContextPath()%>/console/modifyprodpropetyajax?opt=2",
            data: {ids:setids},
            dataType: "json",
            success: function(data){
                if(data.success){
                    alert( "删除成功");
                } else {
                    alert( "删除失败，请重新删除！");
                }
                window.location.reload();
            }
        });

    });

    $('#add_prod_bt').bind('click', function(){
        if($('#id').val()=="" || $('#name').val()=="" || $('#displayName').val()=="" || $('#stock').val()=="" ||
            $('#price').val()=="" || $('#discribe').val()=="" || $('#url').val()=="" ){
            alert("请填写完整商品信息！");
            return false;
        }
        var modurl = "<%=request.getContextPath()%>/console/modifyprodpropetyajax?opt=0";
        if("1" == $('#ismodifyprod').val()){//修改
            modurl = "<%=request.getContextPath()%>/console/modifyprodpropetyajax?opt=1"
        }


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
        $('#status').combobox('setValue',1);
        $('#prodNo').val("");
        $('#propKey').val("");
        $('#propName').val("");
        $('#propValue').val("");
        $('#propDiscribe').val("");
        $('#ismodifyprod').val("0");
        $('#add_prod_bt').html("&nbsp;&nbsp;&nbsp;&nbsp;添加&nbsp;&nbsp;&nbsp;&nbsp;")
        $('#dd').show();
        $('#dd').dialog({
            title: '添加商品属性',
            width: 400,
            height: 280,
            closed: false,
            cache: false,
            modal: true
        });
    }
</script>
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
<table id="dg" class="easyui-datagrid" title="客户信息查询修改" style="width:1100px;height:400px"
	   data-options="rownumbers:true,singleSelect:true,url:'custinfolist',method:'get',toolbar:'#tb'" pagination="true">
	<thead>
	<tr>
		<th data-options="field:'ck',checkbox:true"></th>
		<th data-options="field:'id',width:80">客户编号</th>
		<th data-options="field:'name',width:80">客户姓名</th>
		<th data-options="field:'idCardType',width:80">证件类型</th>
		<th data-options="field:'idCard',width:82">证件号码</th>
		<th data-options="field:'nickName',width:80">昵称</th>
		<th data-options="field:'phone',width:80">客户手机</th>
		<th data-options="field:'qqNo',width:82">QQ号码</th>
		<th data-options="field:'wechatNo',width:120">微信号</th>
		<th data-options="field:'isDelete',width:120">状态</th>
		<th data-options="field:'email',width:120">电子邮箱</th>
		<th data-options="field:'address',width:120">住址</th>
		<th data-options="field:'lastedLoginTime',width:120">最近登陆时间</th>
	</tr>
	</thead>
</table>
<div id="tb" style="padding:5px;height:auto">
	<div style="margin-bottom:5px">
		<a href="#" id="cust_add" class="easyui-linkbutton" iconCls="icon-add" plain="false">添加</a>
		<a href="#" id="cust_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="false">修改</a>
		<a href="#" id="cust_del" class="easyui-linkbutton" iconCls="icon-remove" plain="false">删除</a>
	</div>
	<div>
		<!--Date From: <input class="easyui-datebox" style="width:80px">
		To: <input class="easyui-datebox" style="width:80px">-->
		客户编号：<input type="text" name="cust_no" id="cust_no"/>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search"   id="cust_search">查找</a>
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
					<div class="datagrid-cell" style=": width152px; text-align: center;"><span>客户姓名</span></div>
				</td>
				<td field="orderDate" class="">
					<input type="text" name="name" id="name"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="applyId" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>证件类型</span></div>
				</td>
				<td field="applyId" class="">
					<input type="text" name="idCardType" id="idCardType"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="usrName" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>证件号码</span></div>
				</td>
				<td field="usrName" class="">
					<input type="text" name="idCard" id="idCard"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="bankNo" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>昵称</span></div>
				</td>
				<td field="bankNo" class="">
					<input type="text" name="nickName" id="nickName"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="bankNo" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>客户手机</span></div>
				</td>
				<td field="bankNo" class="">
					<input type="text" name="phone" id="phone"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="bankNo" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>QQ号码</span></div>
				</td>
				<td field="bankNo" class="">
					<input type="text" name="qqNo" id="qqNo"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="bankNo" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>微信号</span></div>
				</td>
				<td field="bankNo" class="">
					<input type="text" name="wechatNo" id="wechatNo"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="cardNo" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>状态</span></div>
				</td>
				<td field="cardNo" class="">
					<select class="easyui-combobox" panelHeight="auto" id="isDelete" name="isDelete" style="width:100px">
						<option value="0">正常</option>
						<option value="1">删除</option>
					</select>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="transAmt" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>电子邮箱 </span></div>
				</td>
				<td field="transAmt" class="">
					<input type="text" name="email" id="email"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="transAmt" class="">
					<div class="datagrid-cell" style="width: 152px; text-align: center;"><span>住址 </span></div>
				</td>
				<td field="state" class="">
					<input type="text" name="address" id="address"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="state" class="">
					<div class="datagrid-cell" style="width: 157px; text-align: center;"><span>最近登陆时间</span></div>
				</td>
				<td field="state" class="">
					<input type="text" name="lastedLoginTime" id="lastedLoginTime"/>
				</td>
			</tr>
			<tr class="datagrid-header-row">
				<td field="state" class="">
				</td>
				<td field="state" class="">
					<input type="hidden" value="" id="ismodifyprod"/>
					<button type="button" class="easyui-linkbutton" id="add_cust_bt">&nbsp;&nbsp;&nbsp;&nbsp;录入&nbsp;&nbsp;&nbsp;&nbsp;</button>
				</td>
			</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>
<script type="text/javascript">
    var url = "<%=request.getContextPath()%>/console/custinfolist";
    $('#cust_search').bind('click', function(){
        var cust_no = $('#cust_no').val();
        if(cust_no == null || cust_no == ""){
            alert("请输入客户编号！");
            return false;
        }
        var handler = "<%=request.getContextPath()%>/console/custinfolist?id=" + cust_no;
        $('#dg').datagrid('options').url = handler;
        $('#dg').datagrid('reload');
    });

    $('#cust_add').bind('click', function(){
        add_cust();
    });
    $('#cust_edit').bind('click', function(){
        set_custinfo();
    });


    function set_custinfo(){
        if($('#dg').datagrid('getSelected') == null || $('#dg').datagrid('getSelected') == ""){
            alert("请选择要修改的数据！");
            return false;
        }
        var queryid = $('#dg').datagrid('getSelected').id;
        if(queryid == ""){
            alert("请选择要修改的数据！");
            return false;
        }
        $.ajax({
            type: "GET",
            url: "<%=request.getContextPath()%>/console/custinfolist",
            data: {id:queryid},
            dataType: "json",
            success: function(data){
                if(data.total != 0){
                    var rows = data.rows[0];
                    $('#status').combobox('setValue',rows.status);
                    $('#id').val(rows.id);
                    $('#name').val(rows.name);
                    $('#displayName').val(rows.displayName);
                    $('#stock').val(rows.stock);
                    $('#price').val(rows.price);
                    $('#discribe').val(rows.discribe);
                    $('#url').val(rows.url);
                    $('#ismodifyprod').val("1");
                    $('#add_cust_bt').html("&nbsp;&nbsp;&nbsp;&nbsp;修改&nbsp;&nbsp;&nbsp;&nbsp;")
                    $('#dd').show();
                    $('#dd').dialog({
                        title: '客户信息修改',
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


    $('#cust_del').bind('click', function(){
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
            url: "<%=request.getContextPath()%>/console/modifycustinfo?opt=1",
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

    $('#add_cust_bt').bind('click', function(){
        if( $('#name').val()=="" || $('#idCard').val()=="" ||
            $('#phone').val()=="" || $('#address').val()=="" || $('#email').val()=="" ){
            alert("请填写完整客户信息！");
            return false;
        }
        var modurl = "<%=request.getContextPath()%>/console/modifycustinfo?opt=0";
        if("1" == $('#ismodifyprod').val()){//修改
            modurl = "<%=request.getContextPath()%>/console/modifycustinfo?opt=1"
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
    function add_cust(){
        $('#isDelete').combobox('setValue',0);
        $('#name').val("");
        $('#email').val("");
        $('#address').val("");
        $('#lastedLoginTime').val("");
        $('#wechatNo').val("");
        $('#qqNo').val("");
        $('#phone').val("0");
        $('#idCard').val("0");
        $('#add_cust_bt').html("&nbsp;&nbsp;&nbsp;&nbsp;录入&nbsp;&nbsp;&nbsp;&nbsp;")
        $('#dd').show();
        $('#dd').dialog({
            title: '新增客户信息',
            width: 400,
            height: 280,
            closed: false,
            cache: false,
            modal: true
        });
    }
</script>
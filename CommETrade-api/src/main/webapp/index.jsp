<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>网上直销代扣信息查询</title>
    <link rel="stylesheet" type="text/css" href="css/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="css/easyui/themes/icon.css">
	<link type="text/css" rel="stylesheet" href="css/main.css"/>
	<link type="text/css" rel="stylesheet" href="css/page.css"/>
    <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/easyui/easyui-lang-zh_CN.js"></script>
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
	<h2>网上直销代扣信息查询</h2>
	<div class="easyui-panel" style="width:1200px">
		<div style="margin:10px 0 10px 0;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			交易日期：<input class="easyui-datebox" id="transdate" name="transdate">&nbsp;&nbsp;&nbsp;&nbsp;
				
			扣款状态：<input class="easyui-combobox" data-option="valueField:'id',textField:'text'" editable="false" id="state" name="transdate">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" id="js-submit" data-options="iconCls:'icon-search'" >查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" id="js-out-excel">&nbsp;&nbsp;&nbsp;&nbsp;导出&nbsp;&nbsp;&nbsp;&nbsp;</a>

		</div>
	</div>
	<div class="count" style="margin: 5px;">
			<table style="margin-left: -18px;">
				<tr>
					<td rowspan="2" style="font-size: 14px;">
						总统计：
					</td>
					<td>
						总笔数：
						<label class="countTotal_all"></label>
					</td>
					<td>
						成功笔数：
						<label class="countSuc_all"></label>
					</td>
					<td>
						失败笔数：
						<label class="countFld_all"></label>
					</td>
					<td class="Unknow_all">
						<font style="color:red">未知笔数：</font>
						<label class=countUnknow_all></label>
					</td>
					<td class="Other_all">
						<font style="color:red">其他笔数：</font>
						<label class=countOther_all></label>
					</td>
				</tr>
				<tr>
					<td>
						总金额：
						<label class="sumAmtTotal_all"></label>
					</td>
					<td>
						成功金额：
						<label class=sumAmtSuc_all></label>
					</td>
					<td>
						失败金额：
						<label class=sumAmtFld_all></label>
					</td>
					<td class="Unknow_all">
						<font style="color:red">未知金额：</font>
						<label class=sumAmtUnknow_all></label>
					</td>
					<td class="Other_all">
						<font style="color:red">其他金额：</font>
						<label class=sumAmtOther_all></label>
					</td>
				</tr>
			</table>
	</div>
	<div class="easyui-tabs" style="width:1200px">
		<div title="收付捷" style="padding:10px">
			<table id="sfj" ></table>
			<div class="sfjcount" style="margin-top: 5px;margin-left: -21px;">
				<table>
					<tr>
						<td rowspan="2" style="font-size: 14px;">
							收付捷统计信息：
						</td>
						<td>
							总笔数：
							<label class="countTotal count"></label>
						</td>
						<td>
							成功笔数：
							<label class="countSuc count"></label>	
						</td>
						<td>
							失败笔数：
							<label class="countFld count"></label>
						</td>
						<td class="Unknow">
							<font style="color:red">未知笔数：</font>
							<label class=countUnknow></label>
						</td>
						<td class="Other">
							<font style="color:red">其他笔数：</font>
							<label class=countOther></label>
						</td>
					</tr>
					<tr>
						<td>
							总金额：
							<label class="sumAmtTotal sum"></label>
						</td>
						<td>
							成功金额：
							<label class="sumAmtSuc sum"></label>
						</td>
						<td>
							失败金额：
							<label class="sumAmtFld sum"></label>
						</td>
						<td class="Unknow">
							<font style="color:red">未知金额：</font>
							<label class=sumAmtUnknow></label>
						</td>
						<td class="Other">
							<font style="color:red">其他金额：</font>
							<label class=sumAmtOther></label>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div title="浦发直连" style="padding:10px">
			<table id="pfzl"></table>
			<div class="pfzlcout" style="margin-top: 5px;margin-left: -21px;">
				<table>
					<tr>
						<td rowspan="2" style="font-size: 14px;">
							浦发直连统计信息：
						</td>
						<td>
							总笔数：
							<label class="countTotal_pf count"></label>
						</td>
						<td>
							成功笔数：
							<label class="countSuc_pf count"></label>
						</td>
						<td>
							失败笔数：
							<label class="countFld_pf count"></label>
						</td>
						<td class="Unknow_pf">
							<font style="color:red">未知笔数：</font>
							<label class=countUnknow_pf></label>
						</td>
						<td class="Other_pf">
							<font style="color:red">其他笔数：</font>
							<label class=countOther_pf></label>
						</td>
					</tr>
					<tr>
						<td>
							总金额：
							<label class="sumAmtTotal_pf sum"></label>
						</td>
						<td>
							成功金额：
							<label class="sumAmtSuc_pf sum"></label>
						</td>
						<td>
							失败金额：
							<label class="sumAmtFld_pf sum"></label>
						</td>
						<td class="Unknow_pf">
							<font style="color:red">未知金额：</font>
							<label class=sumAmtUnknow_pf></label>
						</td>
						<td class="Other_pf">
							<font style="color:red">其他金额：</font>
							<label class=sumAmtOther_pf></label>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">

	var sfj;
	var pfzl;
	sfj=$("#sfj").datagrid({
		 title:'支付完代扣信息',
		 nowarp : false,//自动折行
		 fitColumns : true,
		 striped : true, // 隔行变色特性
		 nowarp : false,//自动折行
		 singleSelect : true,//设置datagrid一次只能选中一行
		 rownumbers:true,
		 columns:[[{
			 title:'交易日期',
			 field:'orderDate',
			 width:100,
			 align:"center",
			 
		 },{
			 title:'申请编号',
			 field:'applyId',
			 width:100,
			 align:"center",
			 
		 },{
			 title:'银行户名',
			 field:'usrName',
			 width:100,
			 align:"center",
			 
		 },{
			 title:'银行名称 ',
			 field:'bankNo',
			 width:100,
			 align:"center",
			 
		 },{
			 title:'银行卡号',
			 field:'cardNo',
			 width:100,
			 align:"center",
			 
		 },{
			 title:'交易金额 ',
			 field:'transAmt',
			 width:100,
			 align:"center",
			 
		 },{
			 title:'扣款状态 ',
			 field:'state',
			 width:100,
			 align:"center",
		 }]]
	});
	
	
	pfzl=$("#pfzl").datagrid({
		 title:'支付完代扣信息',
		 nowarp : false,//自动折行
		 fitColumns : true,
		 striped : true, // 隔行变色特性
		 nowarp : false,//自动折行
		 singleSelect : true,//设置datagrid一次只能选中一行
		 rownumbers:true,
		 columns:[[{
			 title:'交易日期',
			 field:'orderDate',
			 width:100,
			 align:"center",
			 
		 },{
			 title:'申请编号',
			 field:'applyId',
			 width:100,
			 align:"center",
			 
		 },{
			 title:'银行户名',
			 field:'usrName',
			 width:100,
			 align:"center",
			 
		 },{
			 title:'银行名称 ',
			 field:'bankNo',
			 width:100,
			 align:"center",
			 
		 },{
			 title:'银行卡号',
			 field:'cardNo',
			 width:100,
			 align:"center",
			 
		 },{
			 title:'交易金额 ',
			 field:'transAmt',
			 width:100,
			 align:"center",
		 },{
			 title:'扣款状态 ',
			 field:'state',
			 width:100,
			 align:"center",
		 }]]
	})

    var query_url = "/query/capital/report";
	var count_url="/query/capital/totalStatistics";
	
	//扣款select
	$('#state').combobox({
		valueField:'id',
		textField:'text',
		data:[{
			id:'',
			text:"全部"
		},{
			id:'6',
			text:"成功"
		},{
			id:'7',
			text:"失败"
		},{
			id:'5',
			text:"未知"
		}],
		onSelect:function(rec){
			ajaxSubmit();
		}
	});	
	
	//查询
	$("#js-submit").click(function(){
		ajaxSubmit();
	})
	
	//导出
	$("#js-out-excel").click(function(){
		var transdate = $("#transdate").datebox('getValue'); 
		var state = $("#state").combobox('getValue');
		window.location.href="/query/capital/downloadEx?transdate="+transdate+"&state="+state;
	})
	
	//交易日期
	$("#transdate").datebox({
		required:true,
		editable:false,
		value:getDate(),
		onSelect: function(date){
			ajaxSubmit();
		}
	});
	
	//默认当前时间
	function getDate() {
		var curr_time = new Date();
		var strDate = curr_time.getFullYear() + "-";
		var MM = curr_time.getMonth() + 1;
		if (MM < 10) {
			strDate += "0" + MM + "-";
		} else {
			strDate += MM + "-";
		}
		var dd = curr_time.getDate();
		if (dd < 10) {
			strDate += "0" + dd;
		} else {
			strDate += dd;
		}
		return strDate;
	}

	ajaxSubmit();
	function ajaxSubmit(){
		var transdate = $("#transdate").datebox('getValue'); 
		var state = $("#state").combobox('getValue');
		count(transdate,state);
		sfjajax(transdate,state);
		pfzlajax(transdate,state)
	}
	
	//总数统计
	function count(transdate,state){
		$.ajax({
			url:count_url,
			type:'post',
			data:{
				transdate:transdate,
				state:state
			},
			dataType:'json',
			success:function(data){
				if(data!=null){
					$(".countTotal_all").html(data.countTotal+"笔");
					$(".sumAmtTotal_all").html(data.sumAmtTotal+"元");
					$(".countSuc_all").html(data.countSuc+"笔");
					$(".sumAmtSuc_all").html(data.sumAmtSuc+"元");
					$(".countFld_all").html(data.countFld+"笔");
					$(".sumAmtFld_all").html(data.sumAmtFld+"元");
					if(data.countUnknow!=0){
						$(".Unknow_all").show();
						$(".countUnknow_all").html(data.countUnknow+"笔");
						$(".sumAmtUnknow_all").html(data.sumAmtUnknow+"元");
					}else{
						var $span=$(".Unknow_all");
						$span.css('display','none');
					}
					
					if(data.countOther!=0){
						$(".Other_all").show();
						$(".countOther_all").html(data.countOther+"笔");
						$(".sumAmtOther_all").html(data.sumAmtOther+"元");
					}else{
						$(".Other_all").hide();
					}
				}
			}
		});
	}
	
	//收付捷
	function sfjajax(transdate,state){
		$.ajax({
			url: query_url,
			type: 'post',
			data: {
				transdate:transdate,
				state:state,
				capsource:"r"
			},
			dataType: "json",
			success: function(msg){
				if(msg.data!=null){
					$("#sfj").datagrid('loadData',msg.data.records);
					$(".countTotal").html(msg.data.countTotal+"笔");
					$(".sumAmtTotal").html(msg.data.sumAmtTotal+"元");
					$(".countSuc").html(msg.data.countSuc+"笔");
					$(".sumAmtSuc").html(msg.data.sumAmtSuc+"元");
					$(".countFld").html(msg.data.countFld+"笔");
					$(".sumAmtFld").html(msg.data.sumAmtFld+"元");
					if(msg.data.countUnknow!=0){
						$(".Unknow").show();
						$(".countUnknow").html(msg.data.countUnknow+"笔");
						$(".sumAmtUnknow").html(msg.data.sumAmtUnknow+"元");
					}else{
						$(".Unknow").hide();
					}
					
					if(msg.data.countOther!=0){
						$(".Other").show();
						$(".countOther").html(msg.data.countOther+"笔");
						$(".sumAmtOther").html(msg.data.sumAmtOther+"元");
					}else{
						$(".Other").hide();
					}
				}else{
					$("#sfj").datagrid('loadData',{total:0,rows:[]});
					$(".sfjcount .count").html("0笔")
					$(".sfjcount .sum").html("0.00元")
					$(".Unknow").hide();
					$(".Other").hide();
				}
			}
		});
	}
	
	//浦发直连
	function pfzlajax(transdate,state){
		$.ajax({
			url: query_url,
			type: 'post',
			data: {
				transdate:transdate,
				state:state,
				capsource:"F"
			},
			dataType: "json",
			success: function(msg){
				if(msg.data!=null){
					$("#pfzl").datagrid('loadData',msg.data.records);
					$(".countTotal_pf").html(msg.data.countTotal+"笔");
					$(".sumAmtTotal_pf").html(msg.data.sumAmtTotal+"元");
					$(".countSuc_pf").html(msg.data.countSuc+"笔");
					$(".sumAmtSuc_pf").html(msg.data.sumAmtSuc+"元");
					$(".countFld_pf").html(msg.data.countFld+"笔");
					$(".sumAmtFld_pf").html(msg.data.sumAmtFld+"元");
					if(msg.data.countUnknow!=0){
						$(".Unknow_pf").show();
						$(".countUnknow_pf").html(msg.data.countUnknow+"笔");
						$(".sumAmtUnknow_pf").html(msg.data.sumAmtUnknow+"元");
					}else{
						$(".Unknow_pf").hide();
					}
					
					if(msg.data.countOther!=0){
						$(".Other_pf").show();
						$(".countOther_pf").html(msg.data.countOther+"笔");
						$(".sumAmtOther_pf").html(msg.data.sumAmtOther+"元");
					}else{
						$(".Other_pf").hide();
					}
				}else{
					$("#pfzl").datagrid('loadData',{total:0,rows:[]});
					$(".pfzlcout .count").html("0笔")
					$(".pfzlcout .sum").html("0.00元")
					$(".Unknow_pf").hide();
					$(".Other_pf").hide();
				}
			}
		});
	}
</script>
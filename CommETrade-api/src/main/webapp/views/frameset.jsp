<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>后台信息管理</title>
	<link rel="stylesheet" type="text/css" href="../css/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../css/easyui/themes/icon.css">
	<link type="text/css" rel="stylesheet" href="../css/main.css"/>
	<link type="text/css" rel="stylesheet" href="../css/page.css"/>
	<script type="text/javascript" src="../js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="../js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../js/easyui/easyui-lang-zh_CN.js"></script>
	<style>
		.footer {width: 100%;text-align: center;line-height: 35px;}
		.top-bg {background-color: #d8e4fe;height: 40px;text-align: center;font-weight: bold;color: gray;font-size: 18px;padding-top: 5px;}
	</style>
</head>
<body class="easyui-layout">
<div region="north" border="true" split="true" style="overflow: hidden; height: 40px;">
	<div class="top-bg">产品信息后台管理系统</div>
</div>
<div region="south" border="true" split="true" style="overflow: hidden; height: 35px;">
	<div class="footer">版权所有：<a href="###">信息管理系统</a></div>
</div>
<div region="west" split="true" title="菜单" style="width: 200px;">
	<div id="aa" class="easyui-accordion" style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;">
		<div title="商品管理" iconcls="icon-edit" style="overflow: auto; padding: 10px;">
			<ul class="easyui-tree" selected="true" >
				<li>
					<span>信息录入或修改</span>
					<ul>
						<li>
							<span><a target="mainFrame" href="<%=request.getContextPath()%>/console/addprodinit">商品基本信息录入</a></span>
						</li>
						<li>
							<span><a target="mainFrame" href="<%=request.getContextPath()%>/console/addprodinit">商品基本信息修改</a></span>
						</li>
						<li>
							<span><a target="mainFrame" href="<%=request.getContextPath()%>/console/modifyprodpropetyinit">商品属性修改</a></span>
						</li>
						<li>
							<span><a target="mainFrame" href="<%=request.getContextPath()%>/console/addprodinit">商品图片上传</a></span>
						</li>
					</ul>
				</li>
				<li>
					<span><a href="#">库存信息修改</a></span>
				</li>
				<li>
					<span><a href="#">价格信息修改</a></span>
				</li>
				<li>
					<span><a href="#">商品分类</a></span>
				</li>
				<li>
					<span><a href="#">商品排序</a></span>
				</li>
			</ul>
		</div>
		<div title="订单管理" iconcls="icon-edit" style="padding: 10px;">
			<ul class="easyui-tree">
				<li>
					<span><a href="#">订单列表</a></span>
				</li>
				<li>
					<span><a href="#">订单信息编辑</a></span>
				</li>
				<li>
					<span><a href="#">已支付列表</a></span>
				</li>
			</ul>
		</div>
		<div title="客户管理" iconcls="icon-edit" style="overflow: auto; padding: 10px;">
			<ul class="easyui-tree">
				<li>
					<span><a href="#">客户列表</a></span>
				</li>
				<li>
					<span><a href="#">客户信息编辑</a></span>
				</li>
			</ul>
		</div>
		<div title="其他信息" iconcls="icon-edit" style="overflow: auto; padding: 10px;">
			<ul class="easyui-tree">
				<li>
					<span><a href="#">特殊活动</a></span>
				</li>
				<li>
					<span><a href="#">管理员设置</a></span>
				</li>
			</ul>
		</div>
		<div title="退出" iconcls="icon-cancel" style="overflow: auto; padding: 10px;">
			退出系统
		</div>
	</div>
</div>
<div id="mainPanle" region="center" style="overflow: hidden;">
	<div id="tabs" class="easyui-tabs" fit="true" border="false">
		<div title="欢迎使用" style="padding: 20px; overflow: hidden;" id="home">
			<h1>使用说明</h1>
			<p>1.支持同时上传多张图片（商品图片库和其它库分离，当前可以通过文件目录分离）</p>
		</div>
	</div>
</div>
</body>
</html>
<script language="JavaScript">
    $(document).ready(function () {
        $('.easyui-accordion li a').click(function () {
            var tabTitle = $(this).text();
            var url = $(this).attr("href");
            addTab(tabTitle, url);
            $('.easyui-accordion li div').removeClass("selected");
            $(this).parent().addClass("selected");
        }).hover(function () {
            $(this).parent().addClass("hover");
        }, function () {
            $(this).parent().removeClass("hover");
        });

        function addTab(subtitle, url) {
            if (!$('#tabs').tabs('exists', subtitle)) {
                $('#tabs').tabs('add', {
                    title: subtitle,
                    content: createFrame(url),
                    closable: true,
                    width: $('#mainPanle').width() - 10,
                    height: $('#mainPanle').height() - 26
                });
            } else {
                $('#tabs').tabs('select', subtitle);
            }
            tabClose();
        }


        function createFrame(url) {
            var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
            return s;
        }


        function tabClose() {
			/*双击关闭TAB选项卡*/
            $(".tabs-inner").dblclick(function () {
                var subtitle = $(this).children("span").text();
                $('#tabs').tabs('close', subtitle);
            })

            $(".tabs-inner").bind('contextmenu', function (e) {
                $('#mm').menu('show', {
                    left: e.pageX,
                    top: e.pageY,
                });
                var subtitle = $(this).children("span").text();
                $('#mm').data("currtab", subtitle);
                return false;
            });
        }

        //绑定右键菜单事件
        function tabCloseEven() {
            //关闭当前
            $('#mm-tabclose').click(function () {
                var currtab_title = $('#mm').data("currtab");
                $('#tabs').tabs('close', currtab_title);
            })
            //全部关闭
            $('#mm-tabcloseall').click(function () {
                $('.tabs-inner span').each(function (i, n) {
                    var t = $(n).text();
                    $('#tabs').tabs('close', t);
                });
            });

            //关闭除当前之外的TAB
            $('#mm-tabcloseother').click(function () {
                var currtab_title = $('#mm').data("currtab");
                $('.tabs-inner span').each(function (i, n) {
                    var t = $(n).text();
                    if (t != currtab_title)
                        $('#tabs').tabs('close', t);
                });
            });
            //关闭当前右侧的TAB
            $('#mm-tabcloseright').click(function () {
                var nextall = $('.tabs-selected').nextAll();
                if (nextall.length == 0) {
                    //msgShow('系统提示','后边没有啦~~','error');
                    alert('后边没有啦~~');
                    return false;
                }
                nextall.each(function (i, n) {
                    var t = $('a:eq(0) span', $(n)).text();
                    $('#tabs').tabs('close', t);
                });
                return false;
            });
            //关闭当前左侧的TAB
            $('#mm-tabcloseleft').click(function () {
                var prevall = $('.tabs-selected').prevAll();
                if (prevall.length == 0) {
                    alert('到头了，前边没有啦~~');
                    return false;
                }
                prevall.each(function (i, n) {
                    var t = $('a:eq(0) span', $(n)).text();
                    $('#tabs').tabs('close', t);
                });
                return false;
            });
            //退出
            $("#mm-exit").click(function () {
                $('#mm').menu('hide');
            })
        }
    });
</script>
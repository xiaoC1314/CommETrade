<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>登录</title>
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
<body>
<div id="loginWin" class="easyui-window" title="后台管理系统登录" style="width:350px;height:188px;padding:5px;"
     minimizable="false" maximizable="false" resizable="false" collapsible="false" closable="false" draggable="false">
    <div class="easyui-layout" fit="true">
        <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
            <form name="loginForm" method="post" action="<%=request.getContextPath()%>/console/dologin" id="loginForm" autocomplete="off">

                <div style="padding:5px 0;">
                    <label for="login">帐号:</label>
                    <input type="text" name="login" style="width:260px;" />
                </div>
                <div style="padding:5px 0;">
                    <label for="password">密码:</label>
                    <input type="password" name="password" style="width:260px;" />
                </div>
                <div style="padding:5px 0;text-align: center;color: red;" id="showMsg"></div>
            </form>
        </div>
        <div region="south" border="false" style="text-align:right;padding:5px 0;">
            <a class="easyui-linkbutton" iconcls="icon-ok" href="javascript:void(0)" onclick="login()">登录</a>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function login() {
        if ($("input[name='login']").val() == "" || $("input[name='password']").val() == "") {
            $("#showMsg").html("用户名或密码为空，请输入");
            $("input[name='login']").focus();
        } else {
            $("#loginForm").submit();
        }
    }
</script>
</html>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>登录结果</title>
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
<div id="loginWin" class="easyui-window" title="登录失败" style="width:350px;height:188px;padding:5px;"
     minimizable="false" maximizable="false" resizable="false" collapsible="false" closable="false" draggable="false">
    <div class="easyui-layout" fit="true">
        <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
                <div style="padding:5px 0;">
                    <p>用户名或密码不对！</p>
                </div>
        </div>
        <div region="south" border="false" style="text-align:right;padding:5px 0;">
            <a class="easyui-linkbutton" iconcls="icon-back" href="javascript:void(0)" onclick="login()">返回</a>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function login() {
        window.location.href="<%=request.getContextPath()%>/console/login";
    }
</script>
</html>
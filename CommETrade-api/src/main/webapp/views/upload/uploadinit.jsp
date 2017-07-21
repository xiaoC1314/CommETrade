<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>商品上传页面页面</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css"/>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui/easyui-lang-zh_CN.js"></script>
</head>
<body>

<form name="uploadForm" id="uploadForm" action="<%=request.getContextPath()%>/console/uploadsubmit" method="post"  enctype="multipart/form-data">
    <h1>使用spring mvc提供的类的方法上传文件</h1>
    <input type="file" name="file">
    <input type="submit" value="upload"/>
</form>

<p>The filebox component represents a file field of the forms.</p>
<div style="margin:20px 0;"></div>
<div class="easyui-panel" title="Upload File" style="width:400px;padding:30px 70px 50px 70px">
    <div style="margin-bottom:20px">
        <div>Name:</div>
        <input class="easyui-textbox" style="width:100%">
    </div>
    <div style="margin-bottom:20px">
        <div>File1:</div>
        <input class="easyui-filebox" name="file1" data-options="prompt:'Choose a file...'" style="width:100%">
    </div>
    <div style="margin-bottom:20px">
        <div>File2:</div>
        <input class="easyui-filebox" name="file2" data-options="prompt:'Choose another file...'" style="width:100%">
    </div>
    <div>
        <a href="#" class="easyui-linkbutton" style="width:100%">Upload</a>
    </div>
</div>
</body>
</html>

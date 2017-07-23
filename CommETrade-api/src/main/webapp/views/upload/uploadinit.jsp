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
<h1>商品图片上传</h1>
<div style="margin:20px 0;"></div>
<div class="easyui-panel" title="商品图片上传" style="width:400px;padding:30px 70px 50px 70px">
    <form name="uploadForm" id="uploadForm" action="<%=request.getContextPath()%>/console/uploadsubmit" method="post"  enctype="multipart/form-data">
        <!--input class="easyui-filebox" name="file2" data-options="prompt:'Choose another file...'" style="width:100%"-->
        <div>
            <div>商品编号:</div>
            <input type="hidden" name="prodNo" value="${prodNo}">
            <span class="easyui-textbox" >${prodNo}</span>
        </div>
        <div >
            <div>picture:</div>
            <input id="file1" type="file" name="img" onchange="fileCheck(this);">
        </div>
        <div id="mdiv">

        </div>
        <div  style="margin-top:20px">
            <a href="javascript:addimg()" id="addImg" class="easyui-linkbutton">增加图片</a>  <a class="easyui-linkbutton" id="submitform">上传</a>
        </div>
    </form>
</div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function(){
        bindListener();
    })
    var ii = 0;
    function addimg(){
        ii = ii+1;
        $("#mdiv").append('<div class="iptdiv" ><input type="file" name="img'+ii+'" onchange="fileCheck(this);"/><a href="#" name="rmlink">X</a></div>');

        // 为新元素节点添加事件侦听器
        bindListener();
    }
    // 用来绑定事件(使用unbind避免重复绑定)
    function bindListener(){
        $("a[name=rmlink]").unbind().click(function(){
            $(this).parent().remove();
        })
    }

    $('#submitform').bind('click', function(){
        $('#uploadForm').submit();
    });


    function fileCheck(pic){
        var filetypes =[".jpg",".jpeg",".png",".gif"];
        var filepath = pic.value;
        if(filepath){
            var isnext = false;
            var fileend = filepath.substring(filepath.lastIndexOf("."));
            for(var i =0; i<filetypes.length ; i++){
                if(filetypes[i]==fileend){
                    isnext = true;
                    break;
                }
            }
            if(!isnext){
                alert("非图片格式，请重新上传！");
                pic.value ="";
                return false;
            }

        }else{
            return false;
        }
    }
</script>
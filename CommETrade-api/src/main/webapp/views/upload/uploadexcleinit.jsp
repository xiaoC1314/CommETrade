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
<form id="questionTypesManage"  method="post" enctype="multipart/form-data">
    选择文件：　<input id="uploadExcel" name="uploadExcel" class="easyui-filebox" style="width:200px" data-options="prompt:'请选择文件...'">

    　　<a href="#" class="easyui-linkbutton" style="width:122px" onclick="uploadExcel()" >导入题库</a> 　　     　　　　　
</form>
</body>
</html>
<<script>
function uploadExcel(){
    //得到上传文件的全路径
    var fileName= $('#uploadExcel').filebox('getValue');
    //获取题型
    var id= $('#questionType').combobox('getValue');
    var questionTypes=encodeURI(id);
    if(questionTypes !=""){
        //进行基本校验
        if(fileName==""){
            $.messager.alert('提示','请选择上传文件！','info');
        }else{
            //对文件格式进行校验
            var d1=/\.[^\.]+$/.exec(fileName);
            if(d1==".xls"){
                //获取题型
                var id= $('#questionType').combobox('getValue')
                var questionTypes=encodeURI(id);
                //获取课程
                var courseTypeId =$('#courseTypeId').combobox('getValue')
                var courseType=encodeURI(courseTypeId);
                //提交表单
                document.getElementById("questionTypesManage").action="${pageContext.request.contextPath}/leadtoQuestionTypes/leadInExcelQuestionBank?questionType=";
                document.getElementById("questionTypesManage").submit();
                $.messager.alert('提示','操作成功！','info');
            }else{
                $.messager.alert('提示','请选择xls格式文件！','info');
                $('#uploadExcel').filebox('setValue','');
            }
        }
    }else{
        $.messager.alert('提示','请选择课程题型！','info');
    }
}
</script>
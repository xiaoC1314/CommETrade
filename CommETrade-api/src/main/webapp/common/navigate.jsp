<%@ page language="java" pageEncoding="UTF-8"%>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<%
	/*
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
	*/
	
	com.fds.model.BaseModel model =  (com.fds.model.BaseModel)request.getAttribute("model");
	//com.opensymphony.xwork2.util.ValueStack vs = (com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack");
    com.fds.utils.Navigate navigate = (com.fds.utils.Navigate)model.getNavigate();
    int left,right,prev,next;

    int pageId = 0;
        pageId=navigate.getPageId();
    int pageCount =0;
        pageCount= navigate.getPageCount();
    int rowCount =0;
        rowCount= navigate.getRowCount();
    int curPageSize =0;
        curPageSize= rowCount - navigate.getPageOffset();

    if(curPageSize > navigate.getPageSize()) {
        curPageSize = navigate.getPageSize();
    }
    else if(curPageSize<0) {
        curPageSize = 0;
    }
    if(pageCount == 0) {
        pageCount = 1;
    }

    left  = (pageId - 3 > 0) ? (pageId - 3):1;
    right = (pageId + 3 < pageCount)?(pageId + 3):pageCount;
    prev  = (pageId - 1 > 0)?(pageId - 1):1;
    next  = (pageId + 1 < pageCount)?(pageId + 1):pageCount;
%>
<script language="javascript">

	function doNavigate(inPageId) {  
		$("input[name='navigate.pageId']").val(inPageId);
        document.forms[0].submit();
    }
	function getRowCount(){
		var rowCount = "<%=rowCount%>";
		return rowCount;
	}
	function toPage(){
		var pageId = document.getElementById("pageId").value;
		var pageCount = "<%=pageCount%>";
		
		if (pageId.length > 1) {
			if (pageId.substring(0, 1) == '0') {
				alert("输入的页数不合法.");
				return;
			}
		}
		
		if (pageId!=null&&(pageId<=0 || pageId!=Number(pageId))) {
			alert("输入的页数不合法.");
			return;
		}
		
		if (Number(pageId) > pageCount) {
			alert("输入的页数大于总页数.");
			return;
		} else {
			doNavigate(pageId);
		}
	}
</script>
<input type="hidden" name="navigate.pageId" value="${model.navigate.pageId}">
<input type="hidden" name="navigate.orderField" value="${model.navigate.orderField}">
<input type="hidden" name="navigate.orderDirection" value="${model.navigate.orderDirection}">
<table border="0" width="630" cellspacing="0" cellpadding="0" align="right" height="25" width="100%">
  <tr>
    <!--  <td width="29%" align="left" valign="middle">&nbsp; 
    </td>-->
    <td width="55%" align="right" valign="bottom"><font style="font-weight: bold" size="2">第<%=pageId%>页/共<%=pageCount%>页;本页<%=curPageSize%>条记录/共<%=rowCount%>条记录</font>
	<img src="<%=basePath%>images/report/firstPage.gif" width="17" height="11" border="0" onclick="javascript:doNavigate(1)" style="cursor:hand">
	<img src="<%=basePath%>images/report/prePage.gif" width="17" height="11" border="0" onclick="javascript:doNavigate(<%=prev%>)" style="cursor:hand">
	<%
    for(int i = left ; i <= right ; i++)  {
        if(i == pageId)
            out.println("<B><A HREF=\"javascript:doNavigate(" + i + ")\" title=\""+i+"\"><FONT COLOR=\"#FF0000\">" + i + "</FONT></A></B>");
        else
            out.println("<B><A HREF=\"javascript:doNavigate(" + i + ")\" title=\""+i+"\">" + i + "</A></B>");
    }
%>
<img src="<%=basePath%>images/report/nextPage.gif" width="17" height="11" border="0" onclick="javascript:doNavigate(<%=next%>)" style="cursor:hand">
	<img src="<%=basePath%>images/report/endPage.gif" width="17" height="11" border="0" onclick="javascript:doNavigate(<%=pageCount%>)" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
    </td>
        <td width="15%" align="right" valign="bottom"><font style="font-weight: bold">跳至第<input type="text" size="2" id="pageId">页</font>
    </td> 
    <td width="3%" align="right" valign="bottom"><input type="image"   value="查询" src="<%=basePath%>images/report/go.gif" onclick="javascript:toPage()"></td>
  </tr>
</table>
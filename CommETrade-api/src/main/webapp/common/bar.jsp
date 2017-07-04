<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="com.fds.bean.Users"%>
<%
	
	Users usr = (Users)session.getAttribute("users");
	if(null==usr){
		response.sendRedirect("/groble/login.do");
	}
		
	String logBarStyle = "";
	String expinfoBarStyle = "";
	String systemBarStyle = "";
	String logBarTitle ="日志管理";
	
	if(usr!=null && Users.USERTYPE.ADM_USER.getValue()==usr.getUsr_type()){//管理员
		systemBarStyle = "style='display:none'";
	}else if(usr!=null && Users.USERTYPE.COM_USER.getValue()==usr.getUsr_type()){//抽取用户（普通用户）
		//logBarStyle = "style='display:none'";
	
		logBarTitle = "抽取结果";
		
		systemBarStyle = "style='display:none'";
		expinfoBarStyle = "style='display:none'";
	}
	
%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>招投标专家随机抽取系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table align="center" cellpadding="0" cellspacing="0" class="topbg"><tr>
    <td class="top"><img src="/images/top-logo.png" width="425" height="32" /></td>
  </tr>
</table>

<table border="0" cellspacing="0" cellpadding="0" class="menu-top">
  <tr> 
    <td valign="bottom" class="menu-pad">
    <table border="0" cellspacing="0" cellpadding="2" width="100%">
        <tr>
	    <td class="bg-menu-text01" <%=logBarStyle %>><a href="/record/torandomdrawing.do" target="_self">专家库抽取</a></td>
	    <td class="bg-menu-text01" ><a  href="/record/resultlist.do" target="_self">抽取结果</a></td>
	    <td class="bg-menu-text01" <%=expinfoBarStyle %>><a href="/expLib/list.do" target="_self">专家信息</a></td>
	    <td class="bg-menu-text01" <%=systemBarStyle %> ><a href="/sysadmin.jsp" target="_self">系统维护</a></td>
	    <td align="right">欢迎你：<%=usr.getUsr_name() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/users/logout.do" target="_self">注销</a></td>
	    <td width="100px">&nbsp;</td>
	  </tr>
      </table> 
   </td>
  </tr>
</table>

</body>
</html>
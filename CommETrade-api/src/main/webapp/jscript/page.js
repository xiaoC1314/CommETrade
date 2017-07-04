/*
 JS分页类
 //以下代码说明，在index.shtml文件中有一个rs1的段，该段有10个文件，默认启用第一个文件。
 //这些代码由NPS自动生成。
   <script language=javascript>
    NPS.PAGE page = new NPS.PAGE('rs1','index.shtml',10,1);
	page.show();
   </script>
*/
var NPS = new Object();
NPS.PAGE = Class.create();
NPS.PAGE.prototype =
{
    _id         :   '', 
    _pageurl	:	'',
    _pagename   :   '',
    _pagesuffix :   '',
    _pagebarnum	:	10, //一次最多显示页数，如果totalpage超出该数，将仅显示_pagebarnum数（居中），其余通过select和首页、尾页显示
	_totalpage	:	1,
	_nowpage	:	1,
    _navigation_div :   '',
    _page_div    :   '',

    /**setting*/
	_next_page	:	'>',
	_pre_page	:	'<',
	_first_page	:	'首页',
	_last_page	:	'尾页',
	_text_left	:	'[',
	_text_right	:	']',

    //样式定义
    _nowstyle :     'active',
    _style    :     'normal',
    
    /**
	  * 构造函数
	*/
    initialize: function(id,pageurl,total,nowpage)
    {
        this._id = id;
        this._pageurl = pageurl;
        this._totalpage = total;
        this._navigation_div = 'nav'+id;
        this._page_div = id;
        if(nowpage!=null && nowpage!='') this._nowpage = parseInt(nowpage);

        this._pagename = '';
        this._pagesuffix = '';
        if(pageurl!=null && pageurl!='')
        {
            var pos_dot = pageurl.lastIndexOf('.');
            if(pos_dot!='-1')
            {
               this._pagename = pageurl.substring(0,pos_dot);
               this._pagesuffix = pageurl.substr(pos_dot);
            }
            else
            {
                this._pagename = pageurl;
                this._pagesuffix = '';
            }
        }
    },     
  
	/**
	  * 获取显示的分页栏,默认当前页面在最前面
	*/
	_pagebar:function()
    { 
        var navigation = document.getElementById(this._navigation_div);
        navigation.className = "npsnav";
        var context = this;
        var plus=Math.ceil(this._pagebarnum/2);
        if(this._pagebarnum-plus+this._nowpage>this._totalpage)
               plus = this._nowpage+this._pagebarnum-this._totalpage;
        
        var begin = this._nowpage-plus+1;
        if(begin<1) begin = 1;
        navigation.innerHTML = '';

        if(begin>1)
        {
            //显示首页
            var link = document.createElement("a");
            link.href = this._getlink(1);
            link.onclick = this._fetchpage.bindAsEventListener(this,1);
            link.appendChild(document.createTextNode(this._first_page));
            link.className = this._style;
            navigation.appendChild(link);
            navigation.appendChild(document.createTextNode(' '));
        }

        //显示剩余toolbar
        for(var i=begin;i<begin+this._pagebarnum && i<=this._totalpage;i++)
        {
            navigation.appendChild(document.createTextNode(this._text_left));
            var link = document.createElement("a");
            link.href = this._getlink(i);
            link.onclick = this._fetchpage.bindAsEventListener(this,i);
            link.appendChild(document.createTextNode(i));
            if(i == this._nowpage)
            {
                 link.className = this._nowstyle;
            }
            else
            {
                 link.className = this._style;
            }
            navigation.appendChild(link);
            navigation.appendChild(document.createTextNode(this._text_right));
		}

        if(i<this._totalpage)
        {
            //显示末页
            navigation.appendChild(document.createTextNode(' '));
            link = document.createElement("a");
            link.href = this._getlink(this._totalpage);
            link.onclick = this._fetchpage.bindAsEventListener(this,this._totalpage);
            link.appendChild(document.createTextNode(this._last_page));
            link.className = this._style;
            navigation.appendChild(link);
        }

        if(!(begin==1 && i==this._totalpage+1))
        {
            //没有全部显示时，显示快速下拉选择框
            var select_page_ctrl = document.createElement("select");
            select_page_ctrl.onchange = this._fetchselectedpage.bindAsEventListener(this,select_page_ctrl);
            select_page_ctrl.appendChild(document.createTextNode(this._last_page));

            for(i=1;i<=this._totalpage;i++)
            {
                var theOption=document.createElement("OPTION");
                theOption.setAttribute("value",i);       
                theOption.appendChild(document.createTextNode(i));
                select_page_ctrl.appendChild(theOption);
            }

            select_page_ctrl.value = this._nowpage;
            navigation.appendChild(select_page_ctrl);
        }
    },
    //选择select框时自动触发的时间，读取当前选中的页面，提交fetchpage处理
    _fetchselectedpage: function(event,selectbox)
    {
        this._fetchpage(event,selectbox.value);
    },
    // 导航链接点击事件处理函数，切换页面内容,自动使用AJAX读取数据
    _fetchpage: function(event, pageno)
    {
        var element = Event.element(event);
        if (this.ajax)
        {
            this.ajax.transport.abort();
        }
        this.ajax = new Ajax.Request(
                            this._getlink(pageno),
                        {
                            method: "get",
                            parameters: {},
                            onComplete: this._changepage.bind(this, pageno)
                        }
       );
       Event.stop(event);
    },
    //显示返回内容，同时更新导航栏
    _changepage: function(page, xmlhttp)
    {
        var page_div = document.getElementById(this._page_div);
        page_div.innerHTML = xmlhttp.responseText;
        this._nowpage = page;
        this._pagebar();        
    },
    //总入口
    show:function()
    {
        if (this.ajax)
        {
            this.ajax.transport.abort();
        }
        this.ajax = new Ajax.Request(
                        this._getlink(this._nowpage),
                        {
                            method: "get",
                            parameters: {},
                            onComplete: this._changepage.bind(this, this._nowpage)
                        }
       );
    },

    //计算远程页面地址
	_getlink:function(pageno){
        return  this._pagename + '_' + this._id + '_' + pageno + this._pagesuffix + '?timestamp=' + new Date().getTime();
	}
}

window.onerror=function(){return true};
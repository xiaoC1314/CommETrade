//分页
function pagerFilter(data){
    if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
        data = {
            total: data.length,
            rows: data
        }
    }
    var dg = $(this);
    var opts = dg.datagrid('options');
    var pager = dg.datagrid('getPager');
    pager.pagination({
        pageSize: 50,//每页显示的记录条数，默认为15
        pageList: [10,50,100,200],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
    });
    data.originalRows = (data.rows);
    var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
    var end = start + parseInt(opts.pageSize);
    data.rows = (data.originalRows.slice(start, end));
    return data;
}

//增加窗口
function newItem(title){
    $('#dlg').dialog('open').dialog('setTitle',title);
    $('#fm').form('clear');
    $('#fm').form('load',{deptNo:'0'});
}

//编辑窗口
function editItem(title){
    var row = $('#dg').datagrid('getSelections');
    if(1!=row.length){
        alert("请选中一行！");
        return;
    }
    if (row[0]){
        $('#dlg').dialog('open').dialog('setTitle',title);
        $('#fm').form('load',row[0]);
    }
}

//保存
function saveItem(){
    $('#fm').form('submit',{
        url: url,
        onSubmit: function(){
			if($(this).form('validate')){
				$("#dlg-buttons a:first").linkbutton("disable");
				return $(this).form('validate');
			}
        },
        success: function(result){
            var result = eval('('+result+')');
            processResult(result);
        }
    });
}

//删除
function delItem(){
    var rows = $('#dg').datagrid('getSelections');
    if (rows){
        $.messager.confirm('删除确认','确定删除?',function(r){
            var ids = new Array();
            if (r){
                $.each(rows, function (i, n) {
                    ids.push(n.id);
                });
                $.get(
                    delurl,
                    {ids:ids.toString()},
                    function(result){
                        processResult(result);
                        rows.length=0;
                    },'json');
            }
        });
    }
}

//返回结果处理了
function processResult(result){
	$("#dlg-buttons a:first").linkbutton("enable");
    if (!result.isSuccess){
        $.messager.show({
            title: '失败',
            msg: result.errorInfo
        });
    } else {
        $.messager.show({
            title: '成功',
            msg: result.errorInfo
        });
        $('#dlg').dialog('close');        // close the dialog
        $('#dg').datagrid('clearSelections');
        $('#dg').datagrid('reload');    // reload the user data
    }
}

//日期格式化
function formatDate(date){
    var month = date.getMonth()+1;
    if( "" != date ){
        if( date.getMonth() +1 < 10 ){
            month = '0' + (date.getMonth() +1);
        }
        var day = date.getDate();
        if( date.getDate() < 10 ){
            day = '0' + date.getDate();
        }
        return date.getFullYear()+'-'+month+'-'+day;
    }else{
        return "";
    }
}


function parserDate(s){
    if (!s) return new Date();
    try{
        var ss = s.split('-');
        var y = parseInt(ss[0],10);
        var m = parseInt(ss[1],10);
        var d = parseInt(ss[2],10);
        if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
            return new Date(y,m-1,d);
        } else {
            return new Date();
        }
    }catch( e ){
        var d = new Date(s).Format('yyyy-MM-dd');
        return d.toLocaleString();
    }
}

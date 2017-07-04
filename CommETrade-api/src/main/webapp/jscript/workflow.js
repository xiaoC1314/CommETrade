var Workflow = Class.create();
Workflow.prototype = {initialize:function(business_type, app_id, form_id) {
    this.business_type = business_type;
    this.app_id = app_id;
    this.form = $(form_id);
    this.div = $("workflow");
    if(!this.div) {
        var div = document.createElement("DIV");
        div.setAttribute("id","workflow");
        div.className = "workflow";
        div.style.display = 'none';
        this.form.appendChild(div);
        this.div = $("workflow");
    }
},send:function(json_data) {
    this.div.innerHTML = "Loading...";
    var params;
    if(json_data)
        params = $H({business_type:this.business_type,app_id:this.app_id,data:json_data}).toQueryString();
    else
        params = $H({business_type:this.business_type,app_id:this.app_id}).toQueryString();
    var ajax = new Ajax.Updater({success:"workflow"}, "/workflow/client/get_transition.jsp", {method:"post",parameters:params,evalScripts:true,onFailure:this.reportError.bind(this),onComplete:this._complete_send.bind(this)});
},_getParticipants:function() {
    var div_participant = $("workflow_participants");
    var transition = $("transition_id");
    if(transition && transition.value!="") {
        div_participant.innerHTML = "Loading...";
        var params = $H({business_type:this.business_type,app_id:this.app_id,transition_id:transition.value}).toQueryString();
        var ajax = new Ajax.Updater({success:"workflow_participants"}, "/workflow/client/get_participant.jsp", {method:"post",parameters:params,evalScripts:true,onFailure:this.reportError.bind(this)});
    } else{
        div_participant.innerHTML = "";
    }
},returnTo:function() {
    this.div.innerHTML = "Loading...";
    var params = $H({business_type:this.business_type,app_id:this.app_id}).toQueryString();
    var ajax = new Ajax.Updater({success:"workflow"}, "/workflow/client/get_returnjobs.jsp", {method:"post",parameters:params,evalScripts:true,onFailure:this.reportError.bind(this),onComplete:this._complete_returnto.bind(this)});
},_getParticipantsByJob:function() {
    var div_participant = $("workflow_participants");
    var job = $("job_id");
    if(job && job.value!="") {
        div_participant.innerHTML = "Loading...";
        var params = $H({business_type:this.business_type,app_id:this.app_id,job_id:job.value}).toQueryString();
        var ajax = new Ajax.Updater({success:"workflow_participants"}, "/workflow/client/get_participant.jsp", {method:"post",parameters:params,evalScripts:true,onFailure:this.reportError.bind(this)});
    } else {
        div_participant.innerHTML = "";
    }
},reject:function(){
    var params = $H({business_type:this.business_type,app_id:this.app_id}).toQueryString();
    var ajax = new Ajax.Updater({success:"workflow"}, "/workflow/client/get_reject.jsp", {method:"post",parameters:params,evalScripts:true,onFailure:this.reportError.bind(this),onComplete:this._complete_reject.bind(this)});
},revoke:function(){
    var params = $H({business_type:this.business_type,app_id:this.app_id}).toQueryString();
    var ajax = new Ajax.Updater({success:"workflow"}, "/workflow/client/get_revoke.jsp", {method:"post",parameters:params,evalScripts:true,onFailure:this.reportError.bind(this),onComplete:this._complete_revoke.bind(this)});
},stop:function(){
    var params = $H({business_type:this.business_type,app_id:this.app_id}).toQueryString();
    var ajax = new Ajax.Updater({success:"workflow"}, "/workflow/client/get_stop.jsp", {method:"post",parameters:params,evalScripts:true,onFailure:this.reportError.bind(this),onComplete:this._complete_stop.bind(this)});
},restart:function(json_data){
    this.div.innerHTML = "Loading...";
    var params;
    if(json_data)
        params = $H({business_type:this.business_type,app_id:this.app_id,restart:"1",data:json_data}).toQueryString();
    else
        params = $H({business_type:this.business_type,app_id:this.app_id,restart:"1"}).toQueryString();
    var ajax = new Ajax.Updater({success:"workflow"}, "/workflow/client/get_transition.jsp", {method:"post",parameters:params,evalScripts:true,onFailure:this.reportError.bind(this),onComplete:this._complete_send.bind(this)});
},close:function() {
    this.div.innerHTML="";
    this.div.hide();
    this.toggleCurtain();
}, _complete_send:function(){
    if($('transition_id')) $('transition_id').observe('change', this._getParticipants.bind(this));
    if($('btn_workflow_ok')) $('btn_workflow_ok').observe("click",this._send.bind(this));
    if($('btn_workflow_cancel')) $('btn_workflow_cancel').observe('click',this.close.bind(this));
    this._render();
},_complete_returnto:function(){
    if($('job_id')) $('job_id').observe('change', this._getParticipantsByJob.bind(this));
    if($('btn_workflow_ok')) $('btn_workflow_ok').observe("click",this._send.bind(this));
    if($('btn_workflow_cancel')) $('btn_workflow_cancel').observe('click',this.close.bind(this));
    this._render();
},_complete_reject:function(){
    if($('btn_workflow_ok')) $('btn_workflow_ok').observe("click",this._reject.bind(this));
    if($('btn_workflow_cancel')) $('btn_workflow_cancel').observe('click',this.close.bind(this));
    this._render();
},_complete_revoke:function(){
    if($('btn_workflow_ok')) $('btn_workflow_ok').observe("click",this._revoke.bind(this));
    if($('btn_workflow_cancel')) $('btn_workflow_cancel').observe('click',this.close.bind(this));
    this._render();
},_complete_stop:function(){
    if($('btn_workflow_ok')) $('btn_workflow_ok').observe("click",this._stop.bind(this));
    if($('btn_workflow_cancel')) $('btn_workflow_cancel').observe('click',this.close.bind(this));
    this._render();
},reportError:function(req, e) {
    alert("Ooops!\r\nThere was an error occured. Please retry again." );
},_send:function(){
    if($("transition_id").value=="") {
        alert("请选择发送线路");
        return false;
    }
    if($("participants")){
        if($("participants").value=="") {
            alert("请选择参与者");
            return false;
        }
    }
    this.form.submit();
},_returnTo:function() {
    if($("job_id").value=="") {
        alert("请选择回退节点");
        return false;
    }
    if($("participants").value=="") {
        alert("请选择下一步签批人");
        return false;
    }
    this.form.submit();
},_reject:function() {
    if($("suggest").value==""){
        alert("请填写意见");
        return false;
    }
    this.form.submit();
},_revoke:function(){
    if($("suggest").value==""){
        alert("请填写意见");
        return false;
    }
    this.form.submit();
},_stop:function(){
    if($("suggest").value==""){
        alert("请填写意见");
        return false;
    }
    this.form.submit();
},_render:function() {
    this.toggleCurtain();
    var eltDims = this.div.getDimensions();
    var browserDims = document.body.getDimensions();
    var y  = (browserDims.height - eltDims.height) / 2;
    var x = (browserDims.width - eltDims.width) / 2;
    var styles = { position : 'absolute',top : (y<0?0:y) + 'px',left: (x<0?0:x) + 'px',display: 'block' };
    this.div.setStyle(styles);
    this.div.style.display = 'block';
},toggleCurtain:function(){
    var div_area = $("modalarea");
    if (!div_area) {
        var ifrm = document.createElement("iframe");
        ifrm.setAttribute("frameborder", "0");
        ifrm.setAttribute("id", "modalarea");
        ifrm.setAttribute("src", "about:blank");
        ifrm.className = "modalarea";
        ifrm.style.display = 'none';
        document.body.appendChild(ifrm);
        div_area = $("modalarea");
    }
    if (div_area && div_area.style.display != "block") {
        div_area.style.display = "block";
        div_area.style.left = "0px";
        div_area.style.top = "0px";
        this.stretchCurtain();
        this.stretchListener = this.stretchCurtain.bindAsEventListener(this);
        Event.observe(window, "resize", this.stretchListener, false);
    } else {
        div_area.style.display = "none";
        Event.stopObserving(window, "resize", this.stretchListener, false);
        this.stretchListener = null;
    }
},stretchCurtain:function() {
    var div_area = $("modalarea");
    var browserDims = document.body.getDimensions();
    div_area.style.width = (browserDims.width) + "px";
    div_area.style.height = (browserDims.height) + "px";
}};

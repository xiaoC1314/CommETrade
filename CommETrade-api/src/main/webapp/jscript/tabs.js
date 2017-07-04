var uuid = function() {
    return new Date().getTime().toString();    
};
var TabModule = Class.create();
TabModule.prototype = {initialize:function(mod_id) {
    this.mod_id = mod_id;
    this.div_id = 'tabmod_' + mod_id;
    this.type = null;
    this.align = 2;
    this.position=0;
    this.tab = null;
    this.is_last = false;
},toggleIcon:function(ele, disabled) {
    ele = $(ele);
    if (!ele) return;
    if (disabled) {
        this.disableIcon(ele);
    } else {
        this.reenableIcon(ele);
    }
},disableIcon:function(ele) {
    ele = $(ele);
    if (!ele) return;
    ele.className = "disabledicon";
    var icon = ele.firstChild;
    if (!icon.disabled) {
        var function_click = function() {
            return false;
        };
        if (icon.href && icon.href != "#") {
            icon.bhref = icon.href;
        }
        if (icon.onclick) {
            icon.bonclick = icon.onclick;
        }
        if (icon.alt) {
            icon.balt = icon.alt;
        }
        if (icon.title) {
            icon.btitle = icon.title;
        }
        icon.alt = "";
        icon.title = "";
        icon.href = "#";
        icon.onclick = function_click;
        icon.disabled = true;
    }
}, reenableIcon : function(ele) {
    ele = $(ele);
    if (!ele) return;
    ele.className = "icon";
    var icon = ele.firstChild;
    if (icon.disabled) {
        if (icon.bhref) {
            icon.href = icon.bhref;
        }
        if (icon.bonclick) {
            icon.onclick = icon.bonclick;
        }
        if (icon.balt) {
            icon.alt = icon.balt;
        }
        if (icon.btitle) {
            icon.title = icon.btitle;
        }
        icon.disabled = false;
    }
},render : function() {
    this.toggleIcon("up_" + this.mod_id, this.position==0);
    this.toggleIcon("down_" + this.mod_id, this.is_last);
    this.toggleIcon("cent_" + this.mod_id, this.align==2);
    this.toggleIcon("lt_" + this.mod_id, this.align==1 || this.is_last);
    this.toggleIcon("rt_" + this.mod_id, this.align==3 || this.is_last);
},getElementPosition : function(id) {
    var pos_left = 0;
    var pos_top = 0;
    var elem = document.getElementById(id);
    while (elem) {
        pos_left += elem.offsetLeft;
        pos_top += elem.offsetTop;
        elem = elem.offsetParent;
    }
    return {left:pos_left,top:pos_top};
},load : function() {
    var params = $H({tabs_id:this.tab.tabs.tabs_id,tab_id:this.tab.tab_id,mod_id:this.mod_id,type:this.type}).toQueryString();
    var ajax = new Ajax.Updater({success:this.div_id}, "/modules/new_tab_module.jsp", {method:"post",parameters:params,evalScripts:true,onFailure:reportError,onComplete:this.loadded.bind(this)});
},loadded : function() {
    this.tab.render();
    setTimeout(this.onedit.bind(this), 500);
},onedit: function() {
    var func_name = "onedit_" + this.mod_id;
    if(eval('typeof ' + func_name) == 'function') {
        eval('try{ ' + func_name + '();}catch(err){}');
    }
},ondiscard: function() {
    var func_name = "ondiscard_" + this.mod_id;
    if(eval('typeof ' + func_name) == 'function') {
        eval('try{ ' + func_name + '();}catch(err){}');
    }
},onsave:function() {
    var func_name = "onsave_" + this.mod_id;
    if(eval('typeof ' + func_name) == 'function')
    {
        try
        {
            return eval(func_name + '()');
        }
        catch(err)
        {
            return false;
        }
    }
    return true;    
},remove : function() {
    if($(this.div_id)) {
        this.ondiscard();
        Element.remove(this.div_id);
    }
}};



var Tab = Class.create();
Tab.prototype = {initialize:function(tab_id,modules) {
    this.tab_id = tab_id;
    this.div_id = 'tab_' + tab_id;
    this.li_id = tab_id;
    this.position = 0;
    this.selected = false;
    this.tabs = null;
    this.modules_by_order = [];
    this.modules_by_id = [];
    if (modules) {
        modules.each(function(module_data) {
            var module = new TabModule(module_data.mod_id);
            module.type = module_data.type;
            module.tab = this;
            this.add(module, false, true);
        }.bind(this));
    }
    this._renumber();
},getLength:function() {
    return this.modules_by_order.length;
},add:function(module, index, bulk) {
    index += "";
    if (index == "false" || index.toLowerCase() == "bottom") {
        module.position = this.getLength();
    } else {
        if (index.toLowerCase() == "top") {
            module.position = 0;
        } else {
            module.position = Math.min(this.getLength(), parseInt(index));
        }
    }
    this.modules_by_order.splice(module.position, 0, module);
    this.modules_by_id[module.mod_id] = module;
    if (!bulk) {
        this._renumber();
    }
},addModule:function(type) {
    var mod = new TabModule(uuid());
    mod.type = type;
    mod.tab = this;
    this.add(mod, false, false);

    new Insertion.Bottom(this.div_id, '<div id="'+mod.div_id+'" class="mod">Loading...</div>');
    mod.load();
    return mod;
},getByPosition:function(pos) {
    return this.modules_by_order[pos];
},getById:function(id) {
    return this.modules_by_id[id];
},getPosition:function(module) {
    return this.modules_by_order.indexOf(module);
},getPositionById:function(id) {
    var module = this.modules_by_id[id];
    return this.getPosition(module);
},removeById:function(id) {
    var mod = this.modules_by_id[id];
    delete this.modules_by_id[id];
    this.modules_by_order = this.modules_by_order.reject(function(o) {
        return (o.mod_id == mod.mod_id);
    });
    mod.remove();
    this._renumber();
},removeByPosition:function(pos) {
    var mod = this.getByPosition(pos);
    mod = this.modules_by_order.splice(pos, 1);
    delete this.modules_by_id[mod.mod_id];
    mod.remove();
    this._renumber();
},remove:function() {
    this.modules_by_order.each(function(mod) {
        mod.remove();
    });
    if($(this.div_id)) Element.remove(this.div_id);
    if($(this.li_id)) Element.remove(this.li_id);
    this.cleanUp();
},onedit:function() {
    this.modules_by_order.each(function(mod) {
        mod.onedit();
    });
},onsave:function() {
    var len = this.modules_by_order.length;
    for(var i=0;i<len;i++) {
        if(!this.modules_by_order[i].onsave()) return false;
    }
    return true;
},ondiscard:function() {
    this.modules_by_order.each(function(mod) {
        mod.ondiscard();
    });
},render:function() {
    var nodeList = document.getElementById(this.li_id).childNodes;
    for (var i = 0; i < nodeList.length; i++) {
       var node = nodeList[i];
       if (node.nodeName == "SPAN") node.className = this.selected?"active":"";
    }

    $(this.div_id).style.display = this.selected?"block":"none";
    if(this.selected) {
        this.modules_by_order.each(function(mod) {
            mod.render();
        });
    }
},moveUp:function(id) {
    this.moveVert(id, 0);
},moveDown:function(id) {
    this.moveVert(id, 1);
},moveVert:function(id, down) {
    var mod = this.getById(id);
    var pos = this.getPosition(mod);
    var len = this.getLength();
    var step = (down * 2) - 1;
    if (pos === 0 && down === 0) return;
    if (pos == len - 1 && down == 1) return;

    var pos_prev = (down == 1) ? pos : pos - 1;
    var pos_next = (down == 1) ? pos + 1 : pos;
    var mod_next = this.getByPosition(step + pos);
    var mod_prev = this.getByPosition(pos_prev);
    this.modules_by_order.splice(pos_prev, 1);
    this.modules_by_order.splice(pos_next, 0, mod_prev);
    this._renumber();
    this._swapModule(mod, mod_next);    
},moveLeft:function(id) {
    this.moveHoriz(id,1);
},moveRight:function(id) {
    this.moveHoriz(id,3);
},moveCenter:function(id) {
    this.moveHoriz(id,2);
},moveHoriz:function(id,align) {
    var mod = this.getById(id);
    mod.align = align;
    mod.render();
},load:function() {
    this.modules_by_order.each(function(mod) {
        mod.load();
    });
},cleanUp:function() {
    this.div_id = null;
    this.tab_id = null;
    this.modules_by_id = null;
    this.modules_by_order = null;
},_swapModule:function(mod1, mod2) {
   var parent = $(this.div_id);
   var div_mod1 = $(mod1.div_id);
   var div_mod2 = $(mod2.div_id);
   var t1 = div_mod1.nextSibling;
   var t2 = div_mod2.nextSibling;
   if (t1) {
       parent.insertBefore(div_mod2, t1);
   }
   else {
       parent.appendChild(div_mod2);
   }

   if (t2) {
        parent.insertBefore(div_mod1, t2);
   } else {
        parent.appendChild(div_mod1);
   }
},_renumber:function() {
    var len = this.getLength();
    this.modules_by_order.each(function(o, idx) {
        o.position = idx;
        o.is_last = (idx == len - 1);
    });
}};



var TabsEditor = Class.create();
TabsEditor.prototype = {initialize:function(tabs_id,tabs_data) {
    this.cont_id = 'tabs_' + tabs_id;
    this.nav_id = 'tabsnav_' + tabs_id;
    this.tabs_id = tabs_id;
    this.tabs_by_order = [];
    this.tabs_by_id = [];
    this.tab_selected = null;
    if (tabs_data && tabs_data!='') {
        var tabs = JSONstring.toObject(tabs_data);
        tabs.each(function(tab_data) {
            var tab = new Tab(tab_data.tab_id,tab_data.module);
            tab.tabs = this;
            this.add(tab, false, true);
        }.bind(this));

        $('tabs_json_' + this.tabs_id).value = tabs_data;        
        this.tab_selected = this.tabs_by_order[0];
        this.tab_selected.selected = true;
    }
    this._renumber();
    this.render();
},getLength:function() {
    return this.tabs_by_order.length;
},add:function(tab, index, bulk) {
    index += "";
    if (index == "false" || index.toLowerCase() == "bottom") {
        tab.position = this.getLength();
    } else {
        if (index.toLowerCase() == "top") {
            tab.position = 0;
        } else {
            tab.position = Math.min(this.getLength(), parseInt(index));
        }
    }
    this.tabs_by_order.splice(tab.position, 0, tab);
    this.tabs_by_id[tab.tab_id] = tab;
    if (!bulk) this._renumber();    
},addTab:function(lable) {
    this._stopdrag();

    var tab_id = uuid();
    var tab = new Tab(tab_id);
    tab.tabs = this;
    this.add(tab, false, true);

    new Insertion.Bottom(this.cont_id, '<div id="'+tab.div_id+'" class="tab">' +
                                       '<div id="tabslink_'+tab_id+'" class="linkbar">Loading...</div>' +
                                       '</div>');
    
    new Insertion.Bottom(this.nav_id,'<li id="'+tab.li_id+'">'
                                    +'<span>'
                                    +'<input type=text id="tab_title_'+tab_id+'" name="tab_title_'+tab_id+'" value="'+lable+'" onclick="tabseditor_'+this.tabs_id+'.selectById(\''+tab_id+'\');return false;"/>'
                                    +'</span>'
                                    +'<img src="/images/modules/cross.png" onclick="tabseditor_'+this.tabs_id+'.removeById(\''+tab_id+'\');return false;"/>'
                                    +"</li>"
                          );

    var params = $H({tab_id:tab_id}).toQueryString();
    var ajax = new Ajax.Updater({success:'tabslink_' + tab_id}, "/modules/new_tab.jsp", {method:"post",parameters:params,evalScripts:true,onFailure:reportError,onComplete:this._created.bind(this,tab)});
},_created:function(tab){
    this.selectById(tab.tab_id);
    this._startdrag();
},addModule:function(type) {
    if(this.tab_selected) {
       this.tab_selected.addModule(type);
    }
},getByPosition:function(pos) {
    return this.tabs_by_order[pos];
},getById:function(id) {
    return this.tabs_by_id[id];
},getPosition:function(tab) {
    return this.tabs_by_order.indexOf(tab);
},getPositionById:function(id) {
    var tab = this.tabs_by_id[id];
    return this.getPosition(tab);
},getSelected:function() {
    return this.tab_selected;
},removeById:function(id) {
    this._stopdrag();
    var tab = this.tabs_by_id[id];
    delete this.tabs_by_id[id];
    this.tabs_by_order = this.tabs_by_order.reject(function(o) {
        return (o.tab_id == tab.tab_id);
    });
    tab.remove();
    this._renumber();
    this.selectByPosition(0);
    this._startdrag();
},removeByPosition:function(pos) {
    this._stopdrag();
    var tab = this.getByPosition(pos);
    tab = this.tabs_by_order.splice(pos, 1);
    delete this.tabs_by_id[tab.tab_id];
    tab.remove();
    this._renumber();
    this._startdrag();
},removeModule:function(tab_id,mod_id) {
    var tab = this.getById(tab_id);
    if(tab) tab.removeById(mod_id);
},moveModuleUp:function(tab_id,mod_id) {
    var tab = this.getById(tab_id);
    if(tab) tab.moveUp(mod_id);
},moveModuleDown:function(tab_id,mod_id) {
    var tab = this.getById(tab_id);
    if(tab) tab.moveDown(mod_id);
},moveModuleLeft:function(tab_id,mod_id) {
    var tab = this.getById(tab_id);
    if(tab) tab.moveLeft(mod_id);
},moveModuleRight:function(tab_id,mod_id) {
    var tab = this.getById(tab_id);
    if(tab) tab.moveRight(mod_id);
},moveModuleCenter:function(tab_id,mod_id) {
    var tab = this.getById(tab_id);
    if(tab) tab.moveCenter(mod_id);
},selectById : function(id) {
    var tab = this.getById(id);
    if(tab) {
        if(tab==this.tab_selected) return;
        if(this.tab_selected) this.tab_selected.selected = false;
        this.tab_selected = tab;
        this.tab_selected.selected = true;
        this.render();
    }
},selectByPosition : function(pos) {
    var tab = this.getByPosition(pos);
    if(tab) {
        if(tab==this.tab_selected) return;
        if(this.tab_selected) this.tab_selected.selected = false;
        this.tab_selected = tab;
        this.tab_selected.selected = true;
        this.render();
    }
},onedit:function() {
    this._stopdrag();
    this.tabs_by_order.each(function(tab) {
        tab.onedit();
    });
    this._startdrag();
},onsave:function() {
    var len = this.tabs_by_order.length;
    for(var i=0;i<len;i++) {
        if(!this.tabs_by_order[i].onsave()) return false;
    }
    $('tabs_json_' + this.tabs_id).value = this.toJSON();
    this._stopdrag();
    return true;
},ondiscard:function() {
    this.tabs_by_order.each(function(tab) {
        tab.ondiscard();
    });
    this._stopdrag();
},render:function() {
    this.tabs_by_order.each(function(tab) {
        tab.render();
    });
},_startdrag: function() {
    $j('#'+this.nav_id).dragsort({dragSelector: "li",dragEnd: this._dragend.bind(this),dragSelectorExclude: "input, textarea, a[href], img"});             
},_stopdrag: function() {
    $j('#'+this.nav_id).unbind("mousedown").find("li").css("cursor", "auto");
},_dragend: function() {
    var this_tabs = this;
    this_tabs.tabs_by_order.clear();
    $j('#'+ this.nav_id +' li').each(function() {
        var tab = this_tabs.getById($j(this).attr("id"));
        this_tabs.tabs_by_order.push(tab);
    });

    this_tabs._renumber();
},_renumber:function() {
    this.tabs_by_order.each(function(o, idx) {
        o.position = idx;
    });
},toJSON:function() {
    var tabs_json = [];
    this.tabs_by_order.each(function(tab) {
        var modules_json = [];
        tab.modules_by_order.each(function(module) {
            modules_json.push($H({mod_id:module.mod_id,type:module.type,align:module.float}));
        });

        tabs_json.push($H({tab_id:tab.tab_id,module:modules_json}));
    });

    return $A(tabs_json).toJSON();
}};
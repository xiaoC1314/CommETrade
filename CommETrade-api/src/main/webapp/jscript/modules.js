Form.Methods.unserialize = function(form, hash){
  this.unserializeFromHash = hash;
  Form.getElements(form).each((function(elem){
    try{
      switch (elem.type.toLowerCase()) {
        case 'checkbox':
        case 'radio':
          var val = eval('this.unserializeFromHash.'+elem.name).strip();
          if(val == elem.value){
            elem.checked = true;
          }
          break;
        default:
          var val = eval('this.unserializeFromHash.'+elem.name).strip();
          if((val != 'undefined' ) && (val != '')){
            elem.value = val;
          }
          break;
      }
    }catch(e){}
  }).bind(this));
}
Element.addMethods();
JSONstring = {compactOutput:false,includeProtos:false,includeFunctions:false,detectCirculars:false,restoreCirculars:true,make:function(arg, restore) {
    this.restore = restore;
    this.mem = [];
    this.pathMem = [];
    return this.toJsonStringArray(arg).join("");
},toObject:function(x) {
    eval("this.myObj=" + x);
    if (!this.restoreCirculars || !alert) {
        return this.myObj;
    }
    this.restoreCode = [];
    this.make(this.myObj, true);
    var r = this.restoreCode.join(";") + ";";
    eval("r=r.replace(/\\W([0-9]{1,})(\\W)/g,\"[$1]$2\").replace(/\\.\\;/g,\";\")");
    eval(r);
    return this.myObj;
},toJsonStringArray:function(arg, out) {
    if (!out) {
        this.path = [];
    }
    out = out || [];
    var u;
    switch (typeof arg) {
        case "object":
            this.lastObj = arg;
            if (this.detectCirculars) {
                var m = this.mem;
                var n = this.pathMem;
                for (var i = 0; i < m.length; i++) {
                    if (arg === m[i]) {
                        out.push("\"JSONcircRef:" + n[i] + "\"");
                        return out;
                    }
                }
                m.push(arg);
                n.push(this.path.join("."));
            }
            if (arg) {
                if (arg.constructor == Array) {
                    out.push("[");
                    for (var i = 0; i < arg.length; ++i) {
                        this.path.push(i);
                        if (i > 0) {
                            out.push(",\n");
                        }
                        this.toJsonStringArray(arg[i], out);
                        this.path.pop();
                    }
                    out.push("]");
                    return out;
                } else {
                    if (typeof arg.toString != "undefined") {
                        out.push("{");
                        var b = true;
                        for (var i in arg) {
                            if (!this.includeProtos && arg[i] === arg.constructor.prototype[i]) {
                                continue;
                            }
                            this.path.push(i);
                            var curr = out.length;
                            if (!b) {
                                out.push(this.compactOutput ? "," : ",\n");
                            }
                            this.toJsonStringArray(i, out);
                            out.push(":");
                            this.toJsonStringArray(arg[i], out);
                            if (out[out.length - 1] == u) {
                                out.splice(curr, out.length - curr);
                            } else {
                                b = false;
                            }
                            this.path.pop();
                        }
                        out.push("}");
                        return out;
                    }
                }
                return out;
            }
            out.push("null");
            return out;
        case "unknown":
        case "undefined":
        case "function":
            out.push(this.includeFunctions ? arg : u);
            return out;
        case "string":
            if (this.restore && arg.indexOf("JSONcircRef:") == 0) {
                this.restoreCode.push("this.myObj." + this.path.join(".") + "=" + arg.split("JSONcircRef:").join("this.myObj."));
            }
            out.push("\"");
            var a = ["\\","\\\\","\n","\\n","\r","\\r","\"","\\\""];
            arg += "";
            for (var i = 0; i < 8; i += 2) {
                arg = arg.split(a[i]).join(a[i + 1]);
            }
            out.push(arg);
            out.push("\"");
            return out;
        default:
            out.push(String(arg));
            return out;
    }
}};

var detect = navigator.userAgent.toLowerCase();
var OS,browser,version,total,thestring;
if (checkIt("konqueror")) {
    browser = "Konqueror";
    OS = "Linux";
} else {
    if (checkIt("safari")) {
        browser = "Safari";
    } else {
        if (checkIt("opera")) {
            browser = "Opera";
        } else {
            if (checkIt("msie")) {
                browser = "IE";
            } else {
                if (!checkIt("compatible")) {
                    browser = "Netscape Navigator";
                    version = detect.charAt(8);
                } else {
                    browser = "An unknown browser";
                }
            }
        }
    }
}
if (!version) {
    version = detect.charAt(place + thestring.length);
}
if (!OS) {
    if (checkIt("linux")) {
        OS = "Linux";
    } else {
        if (checkIt("x11")) {
            OS = "Unix";
        } else {
            if (checkIt("mac")) {
                OS = "Mac";
            } else {
                if (checkIt("win")) {
                    OS = "Windows";
                } else {
                    OS = "an unknown operating system";
                }
            }
        }
    }
}

function reportError(req) {
    alert("Something went wrong.  Please try again.");
}
;function checkIt(pattern) {
    place = detect.indexOf(pattern) + 1;
    thestring = pattern;
    return place;
}
;
function getElementDimensions(elem) {
    var top = 0,left = 0,width = elem.getWidth(),height = elem.getHeight();
    do{
        top += elem.offsetTop;
        left += elem.offsetLeft;
        elem = elem.offsetParent;
    } while (elem != null);
    return {top:top,left:left,right:left + width,bottom:top + height};
}
;
function getElementTop(elem) {
    var top = 0;
    do{
        top += elem.offsetTop;
        elem = elem.offsetParent;
    } while (elem != null);
    return top;
}
;
function getElementLeft(elem) {
    var left = 0;
    do{
        left += elem.offsetLeft;
        elem = elem.offsetParent;
    } while (elem != null);
    return left;
}
;
function getElementRight(elem) {
    return getElementLeft(elem) + elem.getWidth();
}
;
function getElementBottom(elem) {
    return getElementTop(elem) + elem.getHeight();
}
;
function getAbsoluteLeft(ele) {
    o = document.getElementById(ele);
    oLeft = o.offsetLeft;
    while (o.offsetParent != null) {
        oParent = o.offsetParent;
        oLeft += oParent.offsetLeft;
        o = oParent;
    }
    return oLeft;
}
;
function getAbsoluteTop(ele) {
    o = document.getElementById(ele);
    oTop = o.offsetTop;
    while (o.offsetParent != null) {
        oParent = o.offsetParent;
        oTop += oParent.offsetTop;
        o = oParent;
    }
    return oTop;
};
Element.setOpacity = function(ele, opacity) {
    ele = $(ele);
    if (window.ActiveXObject) {
        ele.style.filter = "alpha(opacity=" + Math.round(opacity * 100) + ")";
    }
    ele.style.opacity = opacity;
};
Element.getCurrentStyle = function(ele) {
    ele = $(ele);
    var style;
    if (document.defaultView) {
        style = document.defaultView.getComputedStyle(ele, "");
    } else {
        style = ele.currentStyle;
    }
    return style;
};
Element.cloneStyles = function(ele, ele2, style_name) {
    ele = $(ele);
    ele2 = $(ele2);
    var style = Element.getCurrentStyle(ele);
    for (var name in style) {
        if (browser == "Opera") {
            if (name == "height" || name == "pixelHeight" || name == "pixelWidth" || name == "posHeight" || name == "posWidth" || name == "width" || name == "font" || name == "fontSize") {
                continue;
            }
        }
        var style_value = style[name];
        if (style_value !== "" && !(style_value instanceof Object) && name != "length" && name != "parentRule") {
            if (style_name && name.indexOf(style_name) !== 0) {
                continue;
            }
            ele2.style[name] = style_value;
        }
    }
    return ele2;
};
Element.findElement = function(elem, tagName) {
    elem = $(elem);
    while (elem.parentNode && (!elem.tagName || (elem.tagName.toUpperCase() != tagName.toUpperCase()))) {
        elem = elem.parentNode;
    }
    return elem;
};
String.prototype.trim = function() {
    var res = this;
    while (res.substring(0, 1) == " ") {
        res = res.substring(1, res.length);
    }
    while (res.substring(res.length - 1, res.length) == " ") {
        res = res.substring(0, res.length - 1);
    }
    return res;
};
String.prototype.startsWith = function(s) {
    var res = this;
    return res.substring(0, s.length) == s;
};
Element.getWidth = function(ele) {
    ele = $(ele);
    return ele.offsetWidth;
};
Element.ellipsis = function(ele, len) {
    len = len || (100);
    var p = $(ele);
    if (p && p.innerHTML) {
        var html = p.innerHTML;
        if (html.length > len) {
            html = html.substring(0, len);
            html = html.replace(/\w+$/, "");
            html += "...";
            p.innerHTML = html;
        }
    }
};
Position.getViewportHeight = function() {
    if (window.innerHeight != window.undefined) {
        return window.innerHeight;
    }
    if (document.compatMode == "CSS1Compat") {
        return document.documentElement.clientHeight;
    }
    if (document.body) {
        return document.body.clientHeight;
    }
    return window.undefined;
};
Position.getViewportWidth = function() {
    if (window.innerWidth != window.undefined) {
        return window.innerWidth;
    }
    if (document.compatMode == "CSS1Compat") {
        return document.documentElement.clientWidth;
    }
    if (document.body) {
        return document.body.clientWidth;
    }
    return window.undefined;
};
Position.getDocumentHeight = function() {
    return document.documentElement.scrollHeight;
};
Position.getDocumentWidth = function() {
    return document.documentElement.scrollWidth;
};
Position.getViewportScrollX = function() {
    var x = 0;
    if (document.documentElement && document.documentElement.scrollLeft) {
        x = document.documentElement.scrollLeft;
    } else {
        if (document.body && document.body.scrollLeft) {
            x = document.body.scrollLeft;
        } else {
            if (window.pageXOffset) {
                x = window.pageXOffset;
            } else {
                if (window.scrollX) {
                    x = window.scrollX;
                }
            }
        }
    }
    return x;
};
Position.getViewportScrollY = function() {
    var y = 0;
    if (document.documentElement && document.documentElement.scrollTop) {
        y = document.documentElement.scrollTop;
    } else {
        if (document.body && document.body.scrollTop) {
            y = document.body.scrollTop;
        } else {
            if (window.pageYOffset) {
                y = window.pageYOffset;
            } else {
                if (window.scrollY) {
                    y = window.scrollY;
                }
            }
        }
    }
    return y;
};
Position.withinViewport = function(ele) {
    var off = Position.cumulativeOffset($(ele));
    var lefttop = [0 + Position.getViewportScrollX(),Position.getViewportScrollY()];
    var rightbottom = [lefttop[0] + Position.getViewportWidth(),lefttop[1] + Position.getViewportHeight()];
    return (lefttop[0] < off[0] && off[0] < rightbottom[0] && lefttop[1] < off[1] && off[1] < rightbottom[1]);
};
Position.set = function(ele, lefttop) {
    if (ele && lefttop) {
        ele.style.left = lefttop[0] + "px";
        ele.style.top = lefttop[1] + "px";
    }
};












Dragger = function(obj, bAnimate) {
    this.handleStart = this.start.bind(this);
    this.handleStop = this.stop.bind(this);
    this.handleUpdate = this._updateMouse.bind(this);
    obj.style.position = "absolute";
    this.object = obj;
    this.d = {x:0,y:0};
    this.f = [];
    if (bAnimate) {
        Event.observe(obj, "mousedown", this.handleStart, false);
        obj.onmousedown = function() {
            if (browser == "Safari") {
                return true;
            }
            return false;
        };
        Event.observe(document, "mouseup", this.handleStop, false);
    }
    if (!Dragger.updatingMouse) {
        Event.observe(document, "mousemove", this.handleUpdate, false);
        Dragger.updatingMouse = true;
    }
};
with ({p:Dragger.prototype,c:Dragger}) {
    p._updateMouse = function(e) {
        var w = window,b = document.body;
        p.mouse = {x:e.clientX + (w.scrollX || b.scrollLeft || b.parentNode.scrollLeft || 0),y:e.clientY + (w.scrollY || b.scrollTop || b.parentNode.scrollTop || 0)};
    };
    p.mouse = {x:0,y:0};
    p.dragging = false;
    p.setonbeforestart = function(func1) {
        this["onbeforestart"] = func1;
        this.object.onmousedown = function(e) {
            if (this.callEvent("onbeforestart", e)) {
                if (browser == "Safari") {
                    return true;
                }
                return false;
            }
        }.bind(this);
    };
    p.start = function(e, dest) {
        if (this["onbeforestart"] instanceof Function && !this.callEvent("onbeforestart", e)) {
        } else {
            var r,$ = this,m = $.mouse,o = $.object;
            for (var r = {l:o.offsetLeft,t:o.offsetTop,w:o.offsetWidth,h:o.offsetHeight}; o = o.offsetParent; r.l += o.offsetLeft,r.t += o.offsetTop) {
            }
            !$.dragging && ($.dragging = true,o = $.object,$.d = dest && (m.x < r.l || m.x > r.l + r.w || m.y < r.t || m.y > r.t + r.h) ? {x:r.w / 2,y:r.h / 2} : {x:m.x - o.offsetLeft,y:m.y - o.offsetTop},this.dragListener = this.drag.bindAsEventListener(this),Event.observe(document, "mousemove", this.dragListener, false),this.callEvent("onstart"));
        }
    };
    p.drag = function(e) {
        if (browser == "IE") {
            var top = document.body.parentNode.scrollTop;
            var height = top + document.body.parentNode.clientHeight;
            var y = event.clientY + top;
        } else {
            var top = window.scrollY;
            var height = window.scrollY + window.innerHeight;
            var y = e.pageY;
        }
        if (y > (height - 10)) {
            window.scrollBy(0, 6);
        } else {
            if (y > (height - 25)) {
                window.scrollBy(0, 4);
            } else {
                if (y > (height - 50)) {
                    window.scrollBy(0, 2);
                } else {
                    if (y < (top + 10)) {
                        window.scrollBy(0, -6);
                    } else {
                        if (y < (top + 25)) {
                            window.scrollBy(0, -4);
                        } else {
                            if (y < (top + 50)) {
                                window.scrollBy(0, -2);
                            }
                        }
                    }
                }
            }
        }
        var i,p,$ = this,o = $.object,m = ($._updateMouse(e),(m = $.mouse).x -= $.d.x,m.y -= $.d.y,m);
        for (i = $.f.length; i; $.f[--i] && $.f[i][0].apply(m, $.f[i][1])) {
        }
        if (o.style.posLeft) {
            o.style.posLeft = m.x,o.style.posTop = m.y;
        } else {
            o.style.left = m.x + "px",o.style.top = m.y + "px";
        }
        return !!this.callEvent("ondrag", e);
    };
    p.stop = function() {
        this.dragging = false;
        this.dragListener && (Event.stopObserving(document, "mousemove", this.dragListener, false));
        this.callEvent("onstop");
    };
    p.addFilter = function(f, _18, _19, _1a, _1b) {
        this.f[this.f.length] = [f,[].slice.call(arguments, 1)];
    };
    p.callEvent = function(e) {
        return this[e] instanceof Function ? this[e].apply(this, [].slice.call(arguments, 1)) : undefined;
    };
}
Dragger.prototype.disable = function(o) {
    Event.stopObserving(o, "mousedown", this.handleStart, false);
    Event.stopObserving(document, "mouseup", this.handleStop, false);
    Event.stopObserving(document, "mousemove", this.handleUpdate, false);
    if (this.dragListener) {
        Event.stopObserving(document, "mousemove", this.dragListener, false);
    }
};
Dragger.prototype.enable = function(o) {
    Event.observe(o, "mousedown", this.handleStart, false);
    Event.observe(document, "mouseup", this.handleStop, false);
    Event.observe(document, "mousemove", this.handleUpdate, false);
};
Dragger.filters = new function() {
    function lineLength(x, y, x0, y0) {
        return Math.sqrt((x -= x0) * x + (y -= y0) * y);
    }
    ;
    function dotLineLength(x, y, x0, y0, x1, y1, o) {
        if (o && !(o = function(x, y, x0, y0, x1, y1) {
            if (!(x1 - x0)) {
                return {x:x0,y:y};
            } else {
                if (!(y1 - y0)) {
                    return {x:x,y:y0};
                }
            }
            var temp,tg = -1 / ((y1 - y0) / (x1 - x0));
            return {x:temp = (x1 * (x * tg - y + y0) + x0 * (x * -tg + y - y1)) / (tg * (x1 - x0) + y0 - y1),y:tg * temp - tg * x + y};
        }(x, y, x0, y0, x1, y1),o.x >= Math.min(x0, x1) && o.x <= Math.max(x0, x1) && o.y >= Math.min(y0, y1) && o.y <= Math.max(y0, y1))) {
            var l1 = lineLength(x, y, x0, y0),l2 = lineLength(x, y, x1, y1);
            return l1 > l2 ? l2 : l1;
        } else {
            var a = y0 - y1,b = x1 - x0,c = x0 * y1 - y0 * x1;
            return Math.abs(a * x + b * y + c) / Math.sqrt(a * a + b * b);
        }
    }
    ;
    this.SQUARE = function(x, y, w, h) {
        this.x = this.x < x ? x : this.x > x + w ? x + w : this.x,this.y = this.y < y ? y : this.y > y + h ? y + h : this.y;
    };
    this.CIRCLE = function(x, y, ray) {
        var tg;
        lineLength(this.x, this.y, x += ray, y += ray) > ray && (this.x = Math.cos(tg = Math.atan2(this.y - y, this.x - x)) * ray + x,this.y = Math.sin(tg) * ray + y);
    };
    this.LINE = function(x, y, angle) {
        if (!(angle % 90)) {
            return this.x = x;
        }
        var tg = Math.tan(-angle * Math.PI / 180);
        Math.sin(45 * Math.PI / 180) >= Math.sin(angle * Math.PI / 180) ? this.y = (this.x - x) * tg + y : this.x = (this.y - y) / tg + x;
    };
    this.POLY = function(x0, y0, x1, y1, etc, etc, etc) {
        for (var a = [].slice.call(arguments, 0),lines = []; a.length > 3; lines[lines.length] = {y1:a.pop(),x1:a.pop(),y0:a.pop(),x0:a.pop()}) {
        }
        if (!lines.length) {
            return;
        }
        for (var l,i = lines.length - 1,o = lines[i],lower = {i:i,l:dotLineLength(this.x, this.y, o.x0, o.y0, o.x1, o.y1, 1)}; i--; lower.l > (l = dotLineLength(this.x, this.y, (o = lines[i]).x0, o.y0, o.x1, o.y1, 1)) && (lower = {i:i,l:l})) {
        }
        this.y < Math.min((o = lines[lower.i]).y0, o.y1) ? this.y = Math.min(o.y0, o.y1) : this.y > Math.max(o.y0, o.y1) && (this.y = Math.max(o.y0, o.y1));
        this.x < Math.min(o.x0, o.x1) ? this.x = Math.min(o.x0, o.x1) : this.x > Math.max(o.x0, o.x1) && (this.x = Math.max(o.x0, o.x1));
        Math.abs(o.x0 - o.x1) < Math.abs(o.y0 - o.y1) ? this.x = (this.y * (o.x0 - o.x1) - o.x0 * o.y1 + o.y0 * o.x1) / (o.y0 - o.y1) : this.y = (this.x * (o.y0 - o.y1) - o.y0 * o.x1 + o.x0 * o.y1) / (o.x0 - o.x1);
    };
};










function ModuleEdit(mod_id) {
    this.data = {id:mod_id,div_id:null,template_id:null,type:null,display_type:null,data_json:"",color:"",width:"",height:"",border:"",textalign:"",overflow:"",displaytitle:true,position:0};
    this.state = {inserting:false};
    this.editting = false;
    this.layout = {topleft:[15,150],bottomright:[15 + 450,150 + 600],width:450,height:600};
}
;
ModuleEdit.Manager = null;
ModuleEdit.options = {module_edit_reposition_duration:600,drag_enabled:true,drag_reorder_enabled:true};
ModuleEdit.prototype.addNewAbove = function(type) {
    this.state.inserting = false;
    ModuleEdit.Manager.addNew(type, this.data.position);
};
ModuleEdit.prototype.cleanUp = function() {
    ModuleEdit.Manager = null;
    this.editting = null;
    this.data = null;
    this.state = null;
};
ModuleEdit.make = function(type, template_id, pos, func_oncomplete) {
    var _bd = new Ajax.Request("/modules/new_module.jsp", {method:"post",parameters:"template_id=" + template_id + "&type=" + type + "&position=" + pos,onFailure:reportError,onSuccess:func_oncomplete});
};
ModuleEdit.toggleIcon = function(ele, disabled) {
    ele = $(ele);
    if (!ele) {
        return;
    }
    if (disabled) {
        ModuleEdit.disableIcon(ele);
    } else {
        ModuleEdit.reenableIcon(ele);
    }
};
ModuleEdit.disableIcon = function(ele) {
    ele = $(ele);
    if (!ele) {
        return;
    }
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
};
ModuleEdit.reenableIcon = function(ele) {
    ele = $(ele);
    if (!ele) {
        return;
    }
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
};
ModuleEdit.getFromJSON = function(json) {
    var mod = new ModuleEdit(json.data.mod_id);
    Object.extend(mod.data, json.data);
    Object.extend(mod.state, json.state);
    return mod;
};
ModuleEdit.toggleCurtain = function() {
    var div_area = $("modalarea");
    if (!div_area) {
        new Insertion.Bottom(document.body, "<iframe class=\"modalarea\" style=\"display:none\" frameborder=\"0\" id=\"modalarea\" src=\"about:blank\"></iframe>");
        div_area = $("modalarea");
    }
    if (div_area && div_area.style.display != "block") {
        div_area.style.display = "block";
        div_area.style.left = "0px";
        div_area.style.top = "0px";
        ModuleEdit.stretchCurtain();
        this.stretchListener = ModuleEdit.stretchCurtain.bindAsEventListener(this);
        Event.observe(window, "resize", this.stretchListener, false);
    } else {
        div_area.style.display = "none";
        Event.stopObserving(window, "resize", this.stretchListener, false);
        this.stretchListener = null;
    }
};
ModuleEdit.stretchCurtain = function() {
    var div_area = $("modalarea");
    var content_height = Element.getHeight("content");
    var sidebar_height = Element.getHeight("sidebar");
    div_area.style.width = (Element.getWidth("container") + 20) + "px";
    div_area.style.height = ((content_height > sidebar_height) ? content_height : sidebar_height) + "px";
};
ModuleEdit.prototype.beforeEdit = function() {
    var func_name = "onedit_" + this.data.id;
    if(eval('typeof ' + func_name) == 'function')
    {
        eval('try{ ' + func_name + '();}catch(err){}');
    }
};
ModuleEdit.prototype.onedit = function() {
    this.editting = true;
    this.setDirty();

    this.state.width = this.data.width;
    this.state.height = this.data.height;
    this.state.border = this.data.border;
    this.state.textalign = this.data.textalign;
    this.state.overflow = this.data.overflow;
    this.state.color = this.data.color;
    this.state.original_html = $(this.data.div_id).innerHTML;
    var form_data = $("form_" + this.data.id);
    if(form_data) {
        this.state.original_form = form_data.serialize(true);
    }

    this.beforeEdit();

    this.toggle();

    return true;
};
ModuleEdit.prototype.onsave = function() {
    if(!this.beforeSave()) return false;
    this.editting = false;
    this.toggle();
    this.save();
    return true;
};
ModuleEdit.prototype.toggle = function() {
    this._toggleTitle();
    this._toggleEditor();
    this._toggleBorder();
    this._toggleEditButton();
    this._toggleDiscardButton();
    this._toggleDeleteButton();
    this._toggleZIndex();
    this._toggleSize();
    this._toggleDrag();
    this._toggleEmptyNotification();

    if(this.editting || this.state.width==this.data.width){
        this.render();
    } else {
        this.state.width = this.data.width;
        this.state.height = this.data.height;
        this.state.border = this.data.border;
        this.state.textalign = this.data.textalign;
        this.state.overflow = this.data.overflow;
        this.state.color = this.data.color;
        ModuleEdit.Manager.render();
    }
    return true;
};
ModuleEdit.prototype._toggleZIndex = function() {
    var div = this.state.div_ele;
    if (this.editting) {
        div.style.zIndex = "10";
    } else {
        div.style.zIndex = "1";
    }
};
ModuleEdit.prototype._toggleSize = function() {
    var div = $(this.data.div_id);
    if (this.editting) {
        var width_shadow = 5;
        var pos_x = 102;
        var rate = 0.15;
        var time = ModuleEdit.options.module_edit_reposition_duration;
        var div_modules = $("modules");
        var pos_start = Position.positionedOffset(div);
        var width = 620 + (width_shadow * 2);
        var layout = ModuleEdit.Manager.layout;
        var pos_y = layout.viewportScroll[1] + (layout.viewportSize[1] * rate);
        div.style.width = width + "px";
        var fx1 = new fx.Position(div, {duration:time});
        div.style.position = "absolute";
        Position.set(div, pos_start);
        fx1.move(pos_start, [pos_x,pos_y]);
        this.computeState();
    } else {
        div.style.display = "inline";
        div.style.position = "static";
        div.style.width = "auto";
        div.style.top = div.style.left = "0px";
    }
};
ModuleEdit.prototype._toggleDrag = function() {
    var div = $(this.data.div_id);
    var div_bar = $("dragbar_" + this.data.id);
    if (ModuleEdit.options.drag_enabled && this.editting) {
        var d = new Dragger(div, false);
        div_bar.style.cursor = "move";
        Event.observe(div_bar, "mousedown", (function() {
            d.start();
            return false;
        }).bind(d), false);
        Event.observe(document, "mouseup", d.stop.bind(d), false);
    } else {
        div_bar.style.cursor = "default";
    }
};
ModuleEdit.prototype._toggleEmptyNotification = function() {
    if (this.editting) {
        $(this.data.id + "_title").hide();
        $("modempty_" + this.data.id).hide();
    } else {
        var div_content = $("txtd_" + this.data.id);
        if ( div_content && div_content.hasChildNodes()) {
            $("modempty_" + this.data.id).hide();
        } else {
            $("modempty_" + this.data.id).show();
        }

        if(this.data.displaytitle && this.data.title!="") {
            $(this.data.id + "_title").show();
        } else {
            $(this.data.id + "_title").hide();
        }

    }
};
ModuleEdit.prototype._toggleTitle = function() {
    var div_title = $(this.data.id + "_title");
    if (!div_title)  return;

    if (this.editting) {
        $(this.data.id+"_titleinput").value = div_title.innerHTML;
        $("background_"+this.data.id).value = this.data.color;
        $("width_"+this.data.id).value = this.data.width;
        $("height_"+this.data.id).value = this.data.height;
        $("border_"+this.data.id).value = this.data.border;
        $("textalign_"+this.data.id).value = this.data.textalign;
        $("overflow_"+this.data.id).value = this.data.overflow;
        $(this.data.id+"_displaytitle").value = !this.data.displaytitle;
    } else {
        this.data.title = $F(this.data.id + "_titleinput").replace(/<[^>]+(>|$)/g, "");
        if (this.data.title !== null) {
            div_title.update(this.data.title);
            if (this.data.title == "") {
                div_title.style.display = "none";
            } else {
                div_title.style.display = "";
            }
        }
        
        this.data.color = $F("background_"+this.data.id);
        this.data.width = $F("width_"+this.data.id);
        this.data.height = $F("height_"+this.data.id);
        this.data.border = $F("border_"+this.data.id);
        this.data.textalign = $F("textalign_"+this.data.id);
        this.data.overflow = $F("overflow_"+this.data.id);
        this.data.displaytitle = !$F(this.data.id+"_displaytitle");
    }
    this.toggleDisplay("colorbar_" + this.data.id);
    this.toggleDisplay("titlebar_" + this.data.id);
};
ModuleEdit.prototype._toggleEditor = function() {
    this.toggleDisplay("editor_" + this.data.id);
    this.toggleDisplay("txtd_" + this.data.id, !this.editting);
};
ModuleEdit.prototype.beforeDiscard = function() {
    var func_name = "ondiscard_" + this.data.id;
    if(eval('typeof ' + func_name) == 'function')
    {
        eval('try{ ' + func_name + '();}catch(err){}');
    }
};
ModuleEdit.prototype.discard = function() {
    if($("form_" + this.data.id) && this.state.original_form){
        Form.unserialize($("form_" + this.data.id),this.state.original_form);
    }

    $(this.data.id + "_titleinput").value = $(this.data.id + "_title").innerHTML;
    $("background_"+this.data.id).value = this.data.color;
    $("width_" + this.data.id).value = this.data.width;
    $("height_" + this.data.id).value = this.data.height;
    $("border_" + this.data.id).value = this.data.border;
    $("textalign_" + this.data.id).value = this.data.textalign;
    $("overflow_" + this.data.id).value = this.data.overflow;
    $(this.data.id + "_displaytitle").value = this.data.displaytitle;

    this.beforeDiscard();

    this.setClean();

    this.editting = false;
    ModuleEdit.Manager.editting = false;
    ModuleEdit.Manager.toggle(this.data.id);

    this.state.original_html = null;
    this.state.original_form = null;
    delete this.state.original_html;
    delete this.state.original_form;
};
ModuleEdit.prototype.computeState = function() {
    var horiz = this.getHoriz();
    this.state.floatleft = (horiz == 1) ? true : false;
    this.state.floatright = (horiz == 3) ? true : false;
    this.state.floatnone = (horiz == 2) ? true : false;
    var div = this.state.div_ele = $(this.data.div_id);
    if (this.editting) {
        var l = this.layout;
        l.topleft = Position.cumulativeOffset(div);
        l.height = Element.getHeight(div);
        l.width = Element.getWidth(div);
        l.bottomright = [l.topleft[0] + l.width,l.topleft[1] + l.height];
    }
};
ModuleEdit.prototype.toggleDisplay = function(item, bShow) {
    item = $(item);
    var show;
    if (bShow != null) {
        show = bShow;
    } else {
        show = this.editting;
    }
    if (item) {
        var display = item.style.display;
        if (show && display == "none") {
            item.style.display = "";
        } else {
            if (!show && display != "none") {
                item.style.display = "none";
            }
        }
    }
};
ModuleEdit.prototype.setDirty = function() {
    this.data.dirty = true;
};
ModuleEdit.prototype.setClean = function(req, json) {
    this.data.dirty = false;
};
ModuleEdit.prototype.getHoriz = function() {
    return (this.isLast()) ? 2 : this.data.horiz_id;
};
ModuleEdit.prototype.setHoriz = function(pos) {
    this.data.horiz_id = pos;
};
ModuleEdit.prototype.isLast = function() {
    return this.state.is_last;
};
ModuleEdit.prototype.isFloated = function() {
    return (this.getHoriz() != 2);
};
ModuleEdit.prototype.renderExhaustive = function() {
    this.computeState();
    this._renderFloat();
    this.render();
};
ModuleEdit.prototype.render = function() {
    this.computeState();
    this._renderSize();
    this._renderBackground();
    this._renderTextAlign();
    this._renderMoveButtons();
    this._renderFloatButtons();
};
ModuleEdit.prototype._renderSize = function() {
    var ele = $("modcont_" + this.data.id);
    if (!ele) return;
    if (this.data.height!="" || (this.state.height && this.state.height!=this.data.height)) {
        this.state.height = this.data.height;
        ele.style.height = this.data.height;
    }

    if (this.data.overflow!="" || (this.state.overflow && this.state.overflow!=this.data.overflow)) {
        this.state.overflow = this.data.overflow;

        var overflow = (this.data.overflow!="") ? this.data.overflow : "visible";
        ele.style.overflow = this.data.overflow;
    }
};
ModuleEdit.prototype._renderFloat = function() {
    var div = this.state.div_ele;
    if (!this.editting) {
        if (!this.state.floatnone) {
           div.style.display = "inline-block";
        } else {
            div.style.display = "block";
        }
    }
};
ModuleEdit.prototype._toggleBorder = function() {
    var ele = $(this.data.div_id);
    if (!ele) return;
    var boarder_style = (this.editting) ? "solid 1px #ccc" : "none";
    ele.style.borderRight = boarder_style;
    ele.style.borderLeft = boarder_style;
    ele.style.borderBottom = boarder_style;
};
ModuleEdit.prototype._renderBackground = function() {
    var ele = $("modcont_" + this.data.id);
    if (!ele) return;
    if (this.data.color!="" || (this.state.color && this.state.color!=this.data.color)) {
        this.state.color = this.data.color;
        ele.style.background = this.data.color;
    }

    if(this.data.border!="" || (this.state.border && this.state.border!=this.data.border)) {
        this.state.border = this.data.border;

        var boarder_style = (this.data.border!="") ? this.data.border : "none";
        ele.style.borderTop = boarder_style;
        ele.style.borderRight = boarder_style;
        ele.style.borderLeft = boarder_style;
        ele.style.borderBottom = boarder_style;
    }
};
ModuleEdit.prototype._renderTextAlign = function() {
    var ele = $("txtd_" + this.data.id);
    if(!ele) return;
    if (this.data.textalign!="" || (this.state.textalign && this.state.textalign!=this.data.textalign)) {
        this.state.textalign = this.data.textalign;
        ele.style.textAlign = this.data.textalign;
    }
};
ModuleEdit.prototype._renderMoveButtons = function() {
    ModuleEdit.toggleIcon("up_" + this.data.id, (this.editting || this.data.position == 0));
    ModuleEdit.toggleIcon("down_" + this.data.id, (this.editting || this.isLast()));
};
ModuleEdit.prototype._renderFloatButtons = function() {
    ModuleEdit.toggleIcon("cent_" + this.data.id, this.editting || this.state.floatnone);
    ModuleEdit.toggleIcon("lt_" + this.data.id, this.editting || this.state.floatleft || this.isLast());
    ModuleEdit.toggleIcon("rt_" + this.data.id, this.editting || this.state.floatright || this.isLast());
};
ModuleEdit.prototype._toggleDeleteButton = function() {
    ModuleEdit.toggleIcon(this.data.id + "_delete", (this.editting));
};
ModuleEdit.prototype._toggleDiscardButton = function() {
    this.toggleDisplay("discard_" + this.data.id);
};
ModuleEdit.prototype._toggleEditButton = function() {
    $("edit_" + this.data.id).className = (this.editting) ? "savebtn" : "editbtn";
    $("edit_" + this.data.id).innerHTML = (this.editting) ? "save&nbsp;" : "edit&nbsp;";
    $("editlink_" + this.data.id).title = (this.editting) ? "Save" : "Edit";
};
ModuleEdit.prototype.unselectDragElements = function() {
    this.state.reorder.items.each(function(item) {
        item.ele.style.backgroundColor = "";
    });
};
ModuleEdit.prototype.getElementPosition = function(id) {
    var pos_left = 0;
    var pos_top = 0;
    var elem = document.getElementById(id);
    while (elem) {
        pos_left += elem.offsetLeft;
        pos_top += elem.offsetTop;
        elem = elem.offsetParent;
    }
    return {left:pos_left,top:pos_top};
};
ModuleEdit.prototype.load = function() {
    var params = $H(this.data).toQueryString();
    var ajax = new Ajax.Updater({success:this.data.div_id}, "/modules/load_module.jsp", {method:"post",parameters:params,evalScripts:true,onFailure:reportError,onComplete:this.render.bind(this)});
};
ModuleEdit.prototype.beforeSave = function() {
    var func_name = "onsave_" + this.data.id;
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
};
ModuleEdit.prototype.save = function() {
    if (!this.data.dirty) return;
    
    var form_data = $("form_" + this.data.id);
    if ( form_data ) {
        this.data.data_json = JSONstring.make(form_data.serialize(true));
        this.data.title = $F(this.data.id + "_titleinput").replace(/<[^>]+(>|$)/g, "");
    } else {
        this.data.data_json = "";
    }

    var ajax = new Ajax.Updater({success:this.data.div_id}, "/modules/save_module.jsp", {method:"post",parameters:$H(this.data).toQueryString(),evalScripts:true,onFailure:this.reportSaveError.bind(this),onComplete:this.afterSave.bind(this)});
};
ModuleEdit.prototype.afterSave = function() {
    this.setClean();
    this.render();
};
ModuleEdit.prototype.deleteServer = function() {
    var ajax = new Ajax.Request("/modules/delete_module.jsp", {method:"post",parameters:$H(this.data).toQueryString(),onFailure:this.reportSaveError.bind(this),onComplete:this.setClean.bind(this)});
};
ModuleEdit.prototype.save_float = function() {
    if (!this.data.dirty) return;
    var ajax = new Ajax.Updater({success:this.data.div_id}, "/modules/float_module.jsp", {method:"post",parameters:$H(this.data).toQueryString(),evalScripts:true,onFailure:this.reportSaveError.bind(this),onComplete:this.afterSave.bind(this)});
};
ModuleEdit.prototype.animateHoriz = function() {
    if (this.data.horiz_id == 2) {
        var div = $(this.data.div_id);
        var width = Element.getWidth(div) * 2;
        var fx1 = new fx.Combo(div, {duration:300,width:true,height:true,opacity:false,onComplete:function() {
            this.render();
        }.bind(this)});
        fx1.customSize(Element.getHeight(div), width);
        fx1.toggle();
    } else {
        this.render();
    }
};
ModuleEdit.prototype.scrollTo = function() {
    var fx1 = new fx.Scroll({duration:100});
    fx1.scrollTo(this.data.div_id);
};
ModuleEdit.prototype.remove = function(update_to_server) {
    if (update_to_server) {
        this.data.deleted = true;
        this.setDirty();
        this.deleteServer();
        var myEffect = new fx.Height(this.data.div_id, {duration:500,transition:fx.backIn,onComplete:function() {
            Element.remove(this.data.div_id);
            ModuleEdit.Manager.render();
        }.bind(this)});
        myEffect.toggle();
    } else {
        Element.remove(this.data.div_id);
    }
};
ModuleEdit.prototype.reportSaveError = function(req, e) {
    alert("Ooops!\r\nThere was an error saving this module.  Please click EDIT and then SAVE to try again.");
};






var ModuleManager = Class.create();
ModuleManager.prototype = {initialize:function(container_div_id, template_id,modules_data) {
    this.div_id = $(container_div_id).id;
    this.template_id = template_id;
    this.modules_by_order = [];
    this.modules_by_id = [];
    this.layout = {};
    this.editting = false;
    ModuleEdit.Manager = this;
    if (modules_data) {
        var modules = JSONstring.toObject(modules_data);
        modules.each(function(module_data) {
            var module = ModuleEdit.getFromJSON(module_data);
            this.add(module, false, true);
        }.bind(this));
    }
    this._renumber();
    this.render();
},getLength:function() {
    return this.modules_by_order.length;
},add:function(module, index, bulk) {
    index += "";
    if (index == "false" || index.toLowerCase() == "bottom") {
        module.data.position = this.getLength();
    } else {
        if (index.toLowerCase() == "top") {
            module.data.position = 0;
        } else {
            module.data.position = Math.min(this.getLength(), parseInt(index));
        }
    }
    this.modules_by_order.splice(module.data.position, 0, module);
    this.modules_by_id[module.data.id] = module;
    if (!bulk) {
        if (ModuleEdit.options.drag_reorder_enabled) {
            var type = module.data.display_type.toLowerCase();
            var type_caption = "<b>" + type + "</b>";
            var html = "<div class=\"reorder_bar\" id=\"caps_reorder_" + module.data.id + "\" " + "style=\"width: 100%; height:25px; bottom: 0px; line-height:25px; z-index: 1; " + "background: url(/images/modules/mod_" + type + ".gif) 2px 50% no-repeat #e5e5e5; cursor:move;\" " + "onselectstart=\"return false\">" + type_caption + "</div>";
            var pole = new Insertion.Bottom("caps_reorder_canvas", html);
            var reorder_item_id = "caps_reorder_" + module.data.id;
            var reorder_item = new ReorderItem(reorder_item_id, module.data.id, module.isFloated());
            reorder_item.repaint();
            this.reorder.add(reorder_item);
            this.reorder.relayout();
        }
        this._renumber();
    }
},addNew:function(type, pos) {
    if (undefined == pos) {
        pos = this.getLength();
    }
    ModuleEdit.make(type, this.template_id, pos, this._handleAddNewResponse.bind(this, pos));
},getByPosition:function(pos) {
    return this.modules_by_order[pos];
},getById:function(id) {
    return this.modules_by_id[id];
},getPosition:function(module) {
    return this.modules_by_order.indexOf(module);
},getPositionById:function(id) {
    var module = this.modules_by_id[id];
    return this.getPosition(module);
},removeById:function(id, update_to_db) {
    var module = this.modules_by_id[id];
    if (module.editting) {
        return;
    }
    var can_do = ($("modempty_" + module.data.id).style.display != "none");
    if (!can_do && !confirm("Are you sure you want to delete this module?")) {
        return;
    }
    delete this.modules_by_id[id];
    this.modules_by_order = this.modules_by_order.reject(function(o) {
        return (o.data.id == module.data.id);
    });
    module.remove(update_to_db);
    this._renumber();
    if (ModuleEdit.options.drag_reorder_enabled) {
        this.reorder.remove(module.data.id);
        this.reorder.relayout();
    }
},removeByPosition:function(pos) {
    var mod = this.getByPosition(pos);
    if (mod.editting) {
        return;
    }
    mod = this.modules_by_order.splice(pos, 1);
    delete this.modules_by_id[mod.data.id];
    mod.remove(false);
    this._renumber();
},renderExhaustive:function() {
    this._renderFloatDivs();
    this.modules_by_order.each(function(mod) {
        Position.set($(mod.data.div_id), [0,0]);
        mod.renderExhaustive();
    });
    this._sweepDeadWrappers();
},render:function() {
    this._renderFloatDivs();
    this.modules_by_order.each(function(mod) {
        mod.render();
    });
    this._sweepDeadWrappers();
},onedit:function(id) {
    var mod = this.getById(id);
    if (!this.editting) {
        if ($("floating_menu")) {
            $("floating_menu").hide();
        }
        this.computeState();
        this.computeListener = this.computeState.bindAsEventListener(this);
        Event.observe(window, "resize", this.computeListener, false);
    } else {
        _floatMenu();
        Event.stopObserving(window, "resize", this.computeListener, false);
        this.computeListener = null;
    }

    var success = false;
    if(this.editting) {
        success = mod.onsave();
    } else {
        success = mod.onedit();
    }

    if (success) {
        this.displayCurtain(mod.data.id + "_modal");
        this.editting = !this.editting;
    }    
},toggle:function(id) {
    var mod = this.getById(id);
    if (this.editting) {
        if ($("floating_menu")) {
            $("floating_menu").hide();
        }
        this.computeState();
        this.computeListener = this.computeState.bindAsEventListener(this);
        Event.observe(window, "resize", this.computeListener, false);
    } else {
        _floatMenu();
        Event.stopObserving(window, "resize", this.computeListener, false);
        this.computeListener = null;
    }
    var success = mod.toggle();
    if (success) {
        this.displayCurtain(mod.data.id + "_modal");
    }
},toggleSidebar:function() {
    var sidebar = $('sidebar');
    if(!sidebar) return;
    if(sidebar.style.display!="none") {
       sidebar.hide();
    } else {
        var width = document.documentElement.clientWidth || document.body.clientWidth;
        var top = document.all?document.documentElement.scrollTop:pageYOffset;
        sidebar.style.position = "absolute";
        sidebar.style.right = 0;
        sidebar.style.top = (top + 20) + "px";
        sidebar.style.left = (width - 320) + "px";        
        sidebar.show();
    }
},moveLeft:function(id) {
    this.moveHoriz(id, 1);
},moveCenter:function(id) {
    this.moveHoriz(id, 2);
},moveRight:function(id) {
    this.moveHoriz(id, 3);
},moveHoriz:function(id, type) {
    var mod = this.getById(id);
    if (mod.editting) {
        return;
    }
    mod.setHoriz(type);
    mod.setDirty();
    mod.data.moveHoriz = true;
    mod.save_float();
    mod.data.moveHoriz = false;
    this.renderExhaustive();
    this.callEvent("onmove", this.modules_by_order);
},moveUp:function(id) {
    this.moveVert(id, 0);
},moveDown:function(id) {
    this.moveVert(id, 1);
},moveVert:function(id, down) {
    this._sweepDeadWrappers();
    var mod = this.getById(id);
    var pos = this.getPosition(mod);
    var len = this.getLength();
    var step = (down * 2) - 1;
    if (pos === 0 && down === 0) {
        return;
    }
    if (pos == len - 1 && down == 1) {
        return;
    }
    if (mod.editting) {
        return;
    }
    var pos_prev = (down == 1) ? pos : pos - 1;
    var pos_next = (down == 1) ? pos + 1 : pos;
    var mod_next = this.getByPosition(step + pos);
    var mod_prev = this.getByPosition(pos_prev);
    this.modules_by_order.splice(pos_prev, 1);
    this.modules_by_order.splice(pos_next, 0, mod_prev);
    this._saveSwap(mod, mod_next);
    this._renumber();

    this._animateSwap(mod, mod_next, step);
    this.callEvent("onmove", this.modules_by_order);
},_sweepDeadWrappers:function() {
    setTimeout(this._sweepDeadWrappersHandler.bind(this), 500);
},_sweepDeadWrappersHandler:function() {
    var eles = document.getElementsByClassName("modfloat", "modules");
    if (eles.length != 1) {
        eles.each(function(ele) {
            if (!ele.hasChildNodes() || ele.childNodes[0].nodeName != "DIV") {
                Element.remove(ele);
            }
        });
    }
    var eles = document.getElementsByClassName("floatclear", "modules");
    eles.each(function(ele) {
        if (!ele.nextSibling || ele.nextSibling.nodeName != "DIV") {
            Element.remove(ele);
        }
    });
},load:function() {
    this.modules_by_order.each(function(mod) {
        mod.load();
    });
},save:function() {
    this.modules_by_order.each(function(mod) {
        mod.save();
    });
},save_template:function() {
    var title = $F("template_title_editor");
    var bgcolor = $F("global_bgcolor");
    var params = $H({template_id:this.template_id,title:title,bgcolor:bgcolor}).toQueryString();
    var ajax = new Ajax.Request("/modules/update_vt.jsp", {parameters:params,onFailure:reportError,onSuccess:function() {
        if ( bgcolor=="") {
            $("container_wrap").style.background = "#fff";
        }
        else {
            $("container_wrap").style.background = bgcolor;
        }
    }});
},publish:function() {
    var publishas = $F('publishas');
    var format = $F('format');
    var url = $F('url');
    var site = $F('site_id');
    var topic = $F('top_id');
    if(publishas=="HTML") {
        if(site=="") {
            alert("Please choose a topic");
            return false;
        }
        if(url=="") {
            alert("Please input a URL");
            return false;
        }
        if(url.startsWith("http://") || url.startsWith("https://")) {
            alert("Invalid URL. URL can't start with http:// or https://");
            return false;
        }
    } else if(publishas=="TEMPLATE") {
        if(topic=="") {
            alert("Please choose a topic");
            return false;
        }
        if(url=="") {
            alert("Please input a URL");
            return false;
        }
        if(url.startsWith("http://") || url.startsWith("https://")) {
            alert("Invalid URL. URL can't start with http:// or https://");
            return false;
        }
    } else if(publishas=="ARTICLE") {
        if(topic=="") {
            alert("Please choose a topic");
            return false;
        }
    }

    var params = $H({template_id:this.template_id,publishas:publishas,format:format,site_id:site,top_id:topic,url:url}).toQueryString();
    var ajax = new Ajax.Request("/modules/publish_vt.jsp", {parameters:params,onFailure:reportError,onSuccess:function() {
        $('publishbar').hide();
        $('menu_box').hide();
        alert('Template published successfully.');
    }});
},cleanUp:function() {
    this.modules_by_order.each(function(m) {
        m.cleanUp();
    });
    this.div_id = null;
    this.template_id = null;
    this.modules_by_id = null;
    this.modules_by_order = null;
},computeState:function() {
    this.layout.documentSize = [Position.getDocumentWidth(),Position.getDocumentHeight()];
    this.layout.viewportSize = [Position.getViewportWidth(),Position.getViewportHeight()];
    this.layout.viewportScroll = [Position.getViewportScrollX(),Position.getViewportScrollY()];
},displayCurtain:function(modal_id) {
    ModuleEdit.toggleCurtain();
},_saveSwap:function(mod1, mod2) {
    var params = $H({template_id:mod1.data.template_id,id:mod1.data.id,id2:mod2.data.id}).toQueryString();
    var ajax = new Ajax.Request("/modules/swap_module.jsp", {parameters:params,onFailure:reportError,onComplete:function() {
    }});
},_animateSwap:function(mod1, mod2, rate) {
    var div_mod1 = $(mod1.data.div_id);
    var div_mod2 = $(mod2.data.div_id);
    Element.makePositioned(div_mod1);
    Element.makePositioned(div_mod2);
    var topleft_mod1 = Position.cumulativeOffset(div_mod1);
    var topleft_mod2 = Position.cumulativeOffset(div_mod2);
    var topleft_mod1_end = [(topleft_mod2[0] - topleft_mod1[0]) / 3,(rate) * Element.getHeight(div_mod2)];
    var topleft_mod2_end = [(topleft_mod1[0] - topleft_mod2[0]) / 3,-1 * (rate) * Element.getHeight(div_mod1)];
    var t = 400;
    var fx1 = new fx.Position(div_mod1, {duration:t,transition:fx.backIn,onComplete:function() {
        Element.undoPositioned(div_mod1);
        Element.undoPositioned(div_mod2);
        this.render();
    }.bind(this)});
    var fx2 = new fx.Position(div_mod2, {duration:t,transition:fx.backIn,onComplete:function() {
    }.bind(this)});
    div_mod1.style.zIndex = "2";
    fx1.move([0,0], [topleft_mod1_end[0],topleft_mod1_end[1]]);
    fx2.move([0,0], [topleft_mod2_end[0],topleft_mod2_end[1]]);
},_renumber:function() {
    var len = this.getLength();
    this.modules_by_order.each(function(o, idx) {
        o.data.position = idx;
        o.state.is_last = (idx == len - 1);
    });
},_renderFloatDivs:function() {
    var test_horiz = "first";
    var test_width = "";
    var div_float;
    var div_modules = $("modules");
    var ele = div_modules.firstChild;
    this.modules_by_order.each(function(mod, idx) {
        var div_mod = $(mod.data.div_id);
        var horiz = mod.getHoriz();
        if (mod.isLast()) {
            horiz = 2;
        }
        if(    horiz!=test_horiz
            || (test_width!="" && mod.data.width!="" && mod.data.width!=test_width)
            || (test_width=="" && (mod.data.width!=test_width))) {
            var css = ["modfloat"];
            switch (horiz) {
                case 1:
                    css.push("left");
                    break;
                case 3:
                    css.push("right");
                    break;
                case 2:
                    css.push("full");
                    break;
            }
            if (test_horiz != "first" && div_float) {
                if (test_horiz == 3) {
                    breakDiv = document.createElement("div");
                    breakDiv.className = "floatclear";
                    div_modules.insertBefore(breakDiv, ele);
                }
                div_modules.insertBefore(div_float, ele);
                if(mod.data.width == "100%") {
                    breakDiv = document.createElement("div");
                    breakDiv.className = "floatclear";
                    div_modules.insertBefore(breakDiv, ele);
                }
            }
            div_float = document.createElement("div");
            div_float.className = css.join(" ");

            if(mod.data.width!=""){
                div_float.style.width = mod.data.width;
            }
        }
        test_horiz = horiz;
        test_width = mod.data.width;

        div_float.appendChild(div_mod);
        if (mod.isLast()) {
            div_modules.insertBefore(div_float, ele);
        }
    });
},_handleAddNewResponse:function(index, req, json) {
    var ele = json.data.mod_id;
    if (ele && json) {
        var pole = new Insertion.Bottom("modules", req.responseText);
        var mod = ModuleEdit.getFromJSON(json);
        this.add(mod, index);
        mod.load();
    }
    this.render();
},callEvent:function(e) {
    return this[e] instanceof Function ? this[e].apply(this, [].slice.call(arguments, 1)) : undefined;
}};




var ModuleReorder = Class.create();
ModuleReorder.prototype = {initialize:function(template_id) {
    ReorderItem.reorder = this;
    this.template_id = template_id;
    this.canvas = $("caps_reorder_canvas");
    this.items = [];
    this.options = {border_repaint_delay:800,filter_additional_overhang:40,empty_space_at_top:10,ele_height:25,ele_float_bump:10,ele_float_width:130,ele_full_width:240};
    this.options.ele_space_per = this.options.empty_space_at_top + this.options.ele_height;
},add:function(item) {
    this.items.push(item);
},remove:function(remove_id) {
    var item = this.items.findAll(function(o) {
        return (o.mod_id == remove_id);
    });
    if (item && item.length > 0) {
        item = item[0];
        this.items = this.items.reject(function(o) {
            return (o.mod_id == remove_id);
        });
        Element.remove(item.ele);
    }
},findById:function(search_id) {
    return this.items.detect(function(item) {
        return (item.mod_id == search_id);
    });
},save:function() {
    this._getorder();
    var str_items = this.items.collect(function(item) {
        return {id:item.mod_id,floated:item.floated};
    });
    var json_items = JSONstring.make(str_items);
    var result = new Ajax.Request("/modules/reorder_module.jsp", {parameters:"template_id=" + this.template_id + "&json=" + json_items,onFailure:reportError,onComplete:this.onsavedone.bind(this)});
},onsavedone:function() {
    window.onbeforeunload = Prototype.emptyFunction;
    location.reload();
},ondragdone:function() {
    this._getorder();
    this.relayout();
},resetorder:function(items) {
    var items_new = [];
    items_new = items.collect(function(mod, i) {
        var item = this.findById(mod.data.id);
        item.floated = mod.isFloated();
        return item;
    }.bind(this));
    this.items = items_new;
    this.relayout(true);
},_getorder:function() {
    this.items = this.items.sortBy(function(node, i) {
        return parseInt(node.ele.style.top, 10);
    });
},relayout:function(bRepaint) {
    var i = 1;
    this.items.each(function(cap) {
        var ele = $(cap.ele);
        var float_bump = (cap.floated) ? this.options.ele_float_bump : 0;
        var top = (i * this.options.ele_space_per) + float_bump;
        ele.style.bottom = "";
        ele.style.top = top + "px";
        i++;
    }.bind(this));
    if (bRepaint) {
        this.items.each(function(cap) {
            cap.repaint();
        });
    }
    var height = (this.items.length + 2) * (this.options.ele_height + 10);
    $("caps_reorder_canvas").style.height = height + "px";
}};
var ReorderItem = Class.create();
ReorderItem.reorder = null;
ReorderItem.prototype = {initialize:function(ele, mod_id, floated) {
    ele = $(ele);
    this.mod_id = mod_id;
    this.ele = ele;
    this.opts = ReorderItem.reorder.options;
    this.floated = floated;
    this.dragged = false;
    var d = new Dragger(ele, true);
    var loc = [0,0];
    var dim = Element.getDimensions(ReorderItem.reorder.canvas);
    var overhang = this.opts.filter_additional_overhang;
    d.addFilter(Dragger.filters.SQUARE, loc[0] - overhang, loc[1], dim.width - overhang, dim.height - this.opts.ele_height);
    d.onstop = this.ondragdone.bind(this);
    d.ondrag = this.ondrag.bind(this);
},ondrag:function(e) {
    this.dragged = true;
    this.ele.style.zIndex = "100";
    this.ele.style.border = "solid 2px #c00";
},ondragdone:function() {
    if (this.dragged) {
        this.repaint();
        this.dragged = false;
        ReorderItem.reorder.ondragdone();
    }
},width:function() {
    return this.floated ? this.opts.ele_float_width : this.opts.ele_full_width;
},height:function() {
    return this.opts.ele_height;
},zindex:function() {
    return this.floated ? "2" : "1";
},repaint:function() {
    if (browser != "IE") {
        Field.focus(this.ele);
    }
    this.ele.style.left = "";
    this.ele.style.right = "5px";
    this.ele.style.zIndex = this.zindex();
    this.ele.style.width = this.width() + "px";
    setTimeout("$('" + this.ele.id + "').style.border = 'solid 1px #aaa'", this.opts.border_repaint_delay);
}};
ReorderItem.make = function(mod) {
    var title = "<b>" + mod.data.display_type + "</b>";
    var html = "<div class=\"dragbar\" id=\"caps_reorder_" + mod.data.id + "\" " + "style=\"width:100%; height:25px; bottom:0px; " + "border:solid 1px #999; font-size:9px; padding:1px; " + "overflow:hidden; right:5px; z-index:1;" + "cursor:pointer\" onselectstart=\"return false\">" + title + "</div>";
    var pole = new Insertion.Bottom("caps_reorder_canvas", html);
    var div_id = "caps_reorder_" + mod.data.id;
    var ri = new ReorderItem(div_id, mod.data.id, mod.isFloated());
    ri.repaint();
    return ri;
};


// handle floating menu
_floatMenu=function() {

    $("sidebar").hide();
    var width = document.documentElement.clientWidth || document.body.clientWidth;
    var float_menu = $('floating_menu');
    float_menu.style.position = "fixed";
    float_menu.style.right = 0;
    float_menu.style.top = "120px";
    float_menu.style.left = (width - 40) + "px";
    float_menu.show();
}
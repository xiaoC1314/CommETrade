fx.Position = Class.create();
fx.Position.prototype = Object.extend(new fx.Base(), {initialize:function(el, options) {
    this.el = $(el);
    this.setOptions(options);
    this.now = [0,0];
},step:function() {
    var time = (new Date).getTime();
    if (time >= this.options.duration + this.startTime) {
        this.now = this.to;
        clearInterval(this.timer);
        this.timer = null;
        if (this.options.onComplete) {
            setTimeout(this.options.onComplete.bind(this), 10);
        }
    } else {
        var Tpos = (time - this.startTime) / (this.options.duration);
        var tmp = [];
        tmp[0] = (this.options.transition(Tpos) * (this.to[0] - this.from[0]) + this.from[0]);
        tmp[1] = (this.options.transition(Tpos) * (this.to[1] - this.from[1]) + this.from[1]);
        this.now = tmp;
    }
    this.increase();
},increase:function() {
    this.el.style["left"] = this.now[0] + "px";
    this.el.style["top"] = this.now[1] + "px";
},move:function(from, to) {
    to = to ? to : this.now;
    this.custom(from, to);
}});
fx.Color = Class.create();
fx.Color.prototype = Object.extend(new fx.Base(), {initialize:function(el, options) {
    this.el = $(el);
    this.setOptions(options);
    this.now = 0;
    this.regex = new RegExp("#?(..?)(..?)(..?)");
    if (!this.options.fromColor) {
        this.options.fromColor = "#FFFFFF";
    }
    if (!this.options.toColor) {
        this.options.toColor = "#FFFFFF";
    }
    if (!this.options.property) {
        this.props = new Array("backgroundColor");
    } else {
        this.props = this.options.property.split(",");
    }
},increase:function() {
    var hex = "rgb(" + (Math.round(this.cs[0] + (this.ce[0] - this.cs[0]) * this.now)) + "," + (Math.round(this.cs[1] + (this.ce[1] - this.cs[1]) * this.now)) + "," + (Math.round(this.cs[2] + (this.ce[2] - this.cs[2]) * this.now)) + ")";
    for (i = 0; i < this.props.length; i++) {
        if (this.props[i] == "backgroundColor") {
            this.el.style.backgroundColor = hex;
        } else {
            if (this.props[i] == "color") {
                this.el.style.color = hex;
            } else {
                if (this.props[i] == "borderColor") {
                    this.el.style.borderColor = hex;
                }
            }
        }
    }
},toggle:function() {
    this.cs = this.regex.exec(this.options.fromColor);
    this.ce = this.regex.exec(this.options.toColor);
    for (i = 1; i < this.cs.length; i++) {
        this.cs[i - 1] = parseInt(this.cs[i], 16);
        this.ce[i - 1] = parseInt(this.ce[i], 16);
    }
    if (this.now > 0) {
        this.custom(1, 0);
    } else {
        this.custom(0, 1);
    }
},cycle:function() {
    this.toggle();
    setTimeout(this.toggle.bind(this), this.options.duration + 10);
},customColor:function(from, to) {
    this.cs = this.regex.exec(from);
    this.ce = this.regex.exec(to);
    for (i = 1; i < this.cs.length; i++) {
        if (this.cs[i].length == 1) {
            this.cs[i] += this.cs[i];
        }
        if (this.ce[i].length == 1) {
            this.ce[i] += this.ce[i];
        }
        this.cs[i - 1] = parseInt(this.cs[i], 16);
        this.ce[i - 1] = parseInt(this.ce[i], 16);
    }
    this.custom(0, 1);
},customColorRGB:function(from, to) {
    this.rgb_regex = new RegExp("^rgb.([^,]*),s?([^,]*),s?([^)]*)");
    this.cs = this.rgb_regex.exec(from);
    this.ce = this.rgb_regex.exec(to);
    if (!this.cs) {
        this.customColor(from, to);
        return;
    }
    for (i = 1; i < this.cs.length; i++) {
        this.cs[i - 1] = parseInt(this.cs[i]);
        this.ce[i - 1] = parseInt(this.ce[i]);
    }
    this.custom(0, 1);
}});
fx.Slide = Class.create();
Object.extend(Object.extend(fx.Slide.prototype, fx.Layout.prototype), {increase:function() {
    this.el.style.height = this.now + "px";
},toggle:function() {
    if (this.el.offsetHeight > 0) {
        this.custom(this.el.offsetHeight, 0);
    } else {
        this.custom(0, this.el.scrollHeight);
    }
}});
function toggleOverlay(id) {
    toggleOverlay.init(id);
    toggleOverlay.toggleCurtain();
}
;
function overlayIsOpen(id) {
    toggleOverlay.init(id);
    return toggleOverlay.curtain.style.display == "block";
}
;
toggleOverlay.init = function(id) {
    this.isIE6orBelow = false;
    var _7a0 = navigator.userAgent.toLowerCase();
    var _7a1 = _7a0.indexOf("msie") + 1;
    if (_7a1) {
        version = _7a0.charAt(_7a1 + 4);
        if (version <= 6) {
            this.isIE6orBelow = true;
        }
    }
    this.overlay = $(id);
    this.wrapper = this.getWrapper();
    this.curtain = this.getCurtain();
    this.wrapper.appendChild(this.overlay);
    if (this.isIE6orBelow) {
        this.iframe = this.getIframe();
    }
    if (navigator.userAgent.indexOf("Linux") != -1) {
        tempObjects = document.body.getElementsByTagName("object");
        this.elementsToHide = [];
        for (var i = 0; i < tempObjects.length; i++) {
            if (!$(tempObjects[i]).descendantOf(this.overlay)) {
                this.elementsToHide.push(tempObjects[i]);
            }
        }
    }
};
toggleOverlay.toggleCurtain = function(id) {
    this.overlay.toggle();
    if (this.curtain.style.display != "block") {
        this.showCurtain();
    } else {
        this.hideCurtain();
    }
};
toggleOverlay.showCurtain = function() {
    this.setElementVisibility("hidden");
    this.wrapper.style.display = "block";
    this.curtain.style.display = "block";
    if (this.isIE6orBelow) {
        this.iframe.style.display = "block";
    }
    this.stretchCurtain();
    Event.observe(window, "resize", this.stretchCurtain, false);
};
toggleOverlay.hideCurtain = function() {
    this.setElementVisibility("visible");
    this.curtain.style.display = "none";
    this.wrapper.style.display = "none";
    if (this.isIE6orBelow) {
        this.iframe.style.display = "none";
    }
    Event.stopObserving(window, "resize", this.stretchCurtain, false);
};
toggleOverlay.setElementVisibility = function(_7a4) {
    if (this.elementsToHide) {
        for (i = 0; i < this.elementsToHide.length; i++) {
            this.elementsToHide[i].style.visibility = _7a4;
        }
    }
};
toggleOverlay.getWrapper = function() {
    var id = "toggleOverlayWrapper";
    var div = $(id);
    if (div) {
        return div;
    }
    div = document.createElement("div");
    div.id = id;
    document.body.appendChild(div);
    div.style.zIndex = "1000";
    if (this.isIE6orBelow) {
        div.style.position = "absolute";
        div.style.top = Position.getViewportScrollY() + "px";
        Event.observe(window, "scroll", function() {
            div.style.top = Position.getViewportScrollY() + "px";
        });
    } else {
        div.style.position = "fixed";
    }
    return div;
};
toggleOverlay.getCurtain = function() {
    var id = "toggleOverlayCurtain";
    var _7a8 = $(id);
    if (_7a8) {
        return _7a8;
    }
    _7a8 = document.createElement("div");
    _7a8.id = id;
    this.wrapper.appendChild(_7a8);
    return _7a8;
};
toggleOverlay.getIframe = function() {
    var id = "toggleOverlayIframe";
    var _7aa = $(id);
    if (_7aa) {
        return _7aa;
    }
    _7aa = document.createElement("iframe");
    _7aa.id = id;
    _7aa.src = "";
    _7aa.frameBorder = "no";
    _7aa.scrolling = "no";
    document.body.appendChild(_7aa);
    return _7aa;
};
toggleOverlay.stretchCurtain = function() {
    if (document.documentElement) {
        height = document.documentElement.scrollHeight;
    } else {
        height = document.body.scrollHeight;
    }
    toggleOverlay.wrapper.style.height = height + "px";
    toggleOverlay.wrapper.style.width = document.body.scrollWidth + "px";
    toggleOverlay.curtain.style.height = height + "px";
    if (this.isIE6orBelow) {
        toggleOverlay.iframe.style.height = height + "px";
        toggleOverlay.iframe.style.width = document.body.scrollWidth + "px";
    }
    if (navigator.userAgent.indexOf("AppleWebKit/") > -1 && !document.evaluate) {
        wd = self["innerWidth"];
    } else {
        if (navigator.userAgent.indexOf("Opera") > -1 && parseFloat(window.opera.version()) < 9.5) {
            wd = document.body["clientWidth"];
        } else {
            wd = document.documentElement["clientWidth"];
        }
    }
    left = wd / 2 - toggleOverlay.overlay.clientWidth / 2 + "px";
    toggleOverlay.overlay.style.left = left;
};
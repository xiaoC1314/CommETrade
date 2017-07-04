String.prototype.trim = function()
{
    var res = this;
    while (res.substring(0, 1) == " ") {
        res = res.substring(1, res.length);
    }
    while (res.substring(res.length - 1, res.length) == " ") {
        res = res.substring(0, res.length - 1);
    }
    return res;
}
String.prototype.ltrim = function(){
    var res = this;
    while (res.substring(0, 1) == " ") {
        res = res.substring(1, res.length);
    }
    return res;
}
String.prototype.rtrim = function(){
    var res = this;
    while (res.substring(res.length - 1, res.length) == " ") {
        res = res.substring(0, res.length - 1);
    }
    return res;
}
String.prototype.isNumber = function(){
  var pattern=/^[+\-]?\d+(.\d+)?$/;
  flag=pattern.test(this);
  if(flag) return true;
  return false;
}
String.prototype.startsWith = function(str) {
    return this.substr(0, str.length) == str;
}
String.prototype.endsWith = function(str) {
    return this.substr(this.length - str.length) == str;
}
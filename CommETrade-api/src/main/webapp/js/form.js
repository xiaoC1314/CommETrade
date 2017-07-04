function fixWidth(percent){
    return document.body.clientWidth * percent ; //这里你可以自己做调整
}
function fixHeight(percent){
    return document.body.clientHeight * percent ; //这里你可以自己做调整
}
function openNewTabOnWorkshop(urltitle,urlcontent,urltarget){
    window.parent.openURL(urltitle,urlcontent,urltarget);
}
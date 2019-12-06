function coursewareSwf(){
	var w = $('#swfBox').width();var h = $('#swfBox').height()
	var argument = jsonlink(window.location.href).swfId
	
	swfobject.embedSWF("video/hudong/"+argument+".swf", "myContent", w, h, "9.0.0");

	
	$('#swfLink').attr('href','/gobangteach/gobangPc/coursewareLookBack.html?JuniorVideoname='+argument)
}
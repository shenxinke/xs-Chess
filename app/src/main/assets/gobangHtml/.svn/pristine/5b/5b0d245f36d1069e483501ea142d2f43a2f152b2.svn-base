function coursewareLookBack(Vue){
	
	var vm = new Vue({
		el:'#playervideo',
		data:{
			msg:''
		}
	})

	var JuniorVideoname = jsonlink(window.location.href).JuniorVideoname
	
	$.ajax({
		url:'../videos/getInteractionVideo',
		data:{
			'JuniorVideoname':'courseware_'+JuniorVideoname
		},
		success:function(data){
			console.log(data)
			vm.msg = data.data
		}
	})

}
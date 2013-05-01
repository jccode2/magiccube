$(document).ready(function() {
	
	var selectList = [];
	
	//添加餐盘
	function addPlate() {
		
	}
	
	//添加食物
	function addFood(plate, group, foodName) {

	}
	
	//切换tab
	$('.food-panel ul li').click(function(e){
		if($(this).hasClass('selected')){
			return;
		}
		
		$(this).addClass('selected').siblings().removeClass('selected');
		
	});
	
	//点击食物
	$('.food-panel .food-item').click(function(e){
		//alert($(this).data('group'));
		//alert($(this).data('foodname'));
		var html = '<li class="plate-food-item"><a class="minus-icon"></a>' + $(this).data('foodname') + '<a class="add-icon"></a></li>'
		
		$('.plate-list .curr').find('.plate-food-list').append(html);
		
	});
	
	//点击餐盘
	$('.plate-item-title').live('click', function(e){
		$(this).parent().addClass('curr').siblings().removeClass('curr');
	});
	
	//添加食物数量
	
	$('.plate-food-item .add-icon').click(function(e){
		
	});
	
	//为更多人点餐
	$('.add-plate').click(function(e){
		
		var count = $('.plate-item').length + 1;
		var html = '<div class="plate-item curr"><h2 class="plate-item-title">餐盒' + count + '</h2><ul class="plate-food-list"></ul></div>'
		$('.plate-list').find('.plate-item').removeClass('curr');
		$('.plate-list').append(html);

	})
	
	
})
$(document).ready(function() {
	
	var selectList = [];
	
	//添加餐盘
	function addPlate() {
		
	}
	
	//添加食物
	function addFood(plate, group, foodName) {

	}
	
	//获取数字
	function getNumber(String) {
		return Number(String.replace(/[^\d]/g,''));
	}
	
	//更新餐盘价格
	function updatePlatePrice(){
		var $plate = $('.plate-list .curr'),
			newPrice = 0,
			oldPrice = getNumber($plate.find('.plate-price').html());
		$plate.find('.plate-food-item').each(function(){
			var count = getNumber($(this).find('.food-count').text());
			newPrice += parseInt($(this).data('price'),10) * count;
		});
		
		//更新餐盘价格
		$plate.find('.plate-price').html('--------------------￥' + newPrice);
		//更新总价格
		var $totalPrice = $('.price-total em'),
			oldTotalPrice = getNumber($totalPrice.text()),
			newTotalPrice = oldTotalPrice + newPrice-oldPrice;
		$totalPrice.text('￥' + newTotalPrice);
	}
	
	//切换tab
	$('.food-panel ul li').hover(function(e){
		if($(this).hasClass('selected')){
			return;
		}
		
		$(this).addClass('selected').siblings().removeClass('selected');
		
		$('.food-list').toggle();
		
	});
	
	//点击食物
	$('.food-panel .food-item').click(function(e){
		//alert($(this).data('group'));
		//alert($(this).data('foodname'));
		var foodName =  $(this).data('foodname'),
			foodId =  $(this).data('foodid'),
			price = $(this).data('price'),
			$curPlate = $('.plate-list .curr'),
			$food = $curPlate.find('.plate-food-item').filter('[data-foodid="' + foodId + '"]');
		
		if($food.length===0){
			var html = '<li class="plate-food-item" data-foodname="' + foodName + '" data-foodid="' + foodId + '" data-price="' + price + '"><a class="minus-icon"></a>' + foodName + '<em class="food-count">×1</em><a class="add-icon"></a></li>'
			console.log($('.plate-list .curr').find('.plate-food-list'));
			$('.plate-list .curr').show().find('.plate-food-list').append(html);
			updatePlatePrice();
		} else {
			$food.find('.add-icon').click();
		}
		
		$('.plate-item.first').show();
		$('.plate-panel .price-total').show();
	});
	
	//点击餐盘
	$('.plate-item-title').live('click', function(e){
		$(this).parent().addClass('curr').siblings().removeClass('curr');
	});
	
	//添加食物数量
	$('.plate-food-item .add-icon').live('click', function(e){
		var $count = $(this).parent().find('.food-count'),
			count = getNumber($count.text()) + 1;
		$count.html('×' + count).show();
		
		updatePlatePrice();
		
	});
	
	//减少食物数量
	$('.plate-food-item .minus-icon').live('click', function(e){
		var $count = $(this).parent().find('.food-count'),
			count = getNumber($count.text()) - 1;
		if(count===0) {
			$(this).parent().remove();
		} else {
			$count.html('×' + count);
		}
		updatePlatePrice();
	});
	
	//为更多人点餐
	$('.add-plate').click(function(e){
		
		var count = $('.plate-item').length + 1;
		var html = '<div class="plate-item curr"><h2 class="plate-item-title">餐盒' + count + '<em class="plate-price"></em></h2><ul class="plate-food-list"></ul></div>'
		$('.plate-list').find('.plate-item').removeClass('curr');
		
		console.log(html);
		
		$('.plate-list').append(html);
		$('.plate-item.first').removeClass('first');

	});
	
	//提交订单
	$('.submit-order').click(function(e){
		
		var orderData = getPlateList();
		console.log(orderData);
		var orderHtml = getOrderHtml(orderData);
		
		$('.plate-list-detail').html(orderHtml);
		
		$('.order').modal('show');
		
	});
	
	//获取所有餐盘数据
	function getPlateList() {
		
		var data = [];
		
		$('.plate-list .plate-item').each(function(){
			var plateItem = {};
			plateItem.price = getNumber($(this).find('.plate-price').text());
			plateItem.foodList = [];
			$(this).find('.plate-food-item').each(function(){
				var food = {}, $self = $(this);
				food.foodId = $self.data('foodid');
				food.name = $self.data('foodname');
				food.price = getNumber($self.data('price'));
				food.count =  getNumber($self.find('.food-count').text());
				plateItem.foodList.push(food);
			});
			
			data.push(plateItem);
		
		});
		
		return data;
		
	}
	
	/**
	 * 生成订单页面html
	 */
	function getOrderHtml(plateList) {
		var html = '';
		for(var i=0; i < plateList.length; i++) {
			html += getOrderItemHtml(plateList[i], i);
		}
		return html;
	}
	
	/**
	 * 生成某个餐盘的html
	 */
	function getOrderItemHtml(plate, i) {
		
		var addCls = '';
		if((i+1)%3==2) {
			addCls = 'centerone';
		}
		var html = '<div class="order-plate-item ' + addCls + '">'
					+ '<h4 class="order-plate-title">餐盒' + (i+1) + '</h4>'
					+ '<ul class="order-plate-food">';
		for(var j=0; j < plate.foodList.length; j++) {
			var food = plate.foodList[j];
			html += '<li class="order-food-item">' + food.name + '</li>';
		}
		html +='</ul>';
		html +='<h1 class="order-plate-price">';
		html += '<em class="item-price">￥' + plate.price + '</em>';
		html +='</h1>';
		html += '</div>';
		
		return html;
	}
	
	
})
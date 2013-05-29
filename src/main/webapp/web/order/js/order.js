$(document).ready(function() {
	
	var orderData = null;
	
	//获取数字
	function getNumber(String) {
		return Number(String.replace(/[^\d]/g,''));
	}
	
	//是否为手机号码
	function isMobile(input) {
		var test1 = /^(13[0-9]|15[0|3|6|7|8|9]|18[6|8|9])\d{8}$/.test(input); //联通
		var test2 = /^1(3[4-9]|5[012789]|8[78])\d{8}$/.test(input); //移动
		
		return (test1||test) ;
	
	}
	
	//是否为电话号码
	function isTel(input) {
		//"兼容格式: 国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"
	    //return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/.test(this.Trim()));
	    return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(input));
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
	
	//点击地址
	$('.img-circle').click(function(){
		var $item = $(this).closest('.address-item');
		$item.addClass('curr').siblings().removeClass('curr');
		var adress = $item.find('h5').text();
		$('.adress-info').text('地址信息：' + adress);
		$('.order-address').fadeOut('slow');
	});
	
	//点击地址信息：
	$('.adress-info').click(function(){
		$('.order-address').fadeIn('slow');
	});
	
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
			//console.log($('.plate-list .curr').find('.plate-food-list'));
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
		
		//console.log(html);
		
		$('.plate-list').append(html);
		$('.plate-item.first').removeClass('first');

	});
	
	//提交订单 打开订单页面
	$('.submit-order').click(function(e){
		
		orderData = getPlateList();
		var plateList = orderData.plateList;
		var html = '';
		if(plateList.length==0) {
			alert('请先选择食物!');
			return;
		} 
		var orderHtml = getOrderHtml(plateList);
		if(plateList.length==1) {
			html += '<div class="order-plate-null"></div>' + orderHtml + '<div class="order-plate-null"></div>';
		}else if(plateList.length==2) {
			html += orderHtml + '<div class="order-plate-null"></div>';
		} else {
			html += orderHtml;
		}
		
		$('.plate-list-detail').html(html);
		$('#order-price-total').text(orderData.totalPrice);
		$('#order-reality-price').text(orderData.totalPrice);
		
		//从cookie获取电话和地址信息
		$('#address').val($.cookie('address'));
		$('#phone').val($.cookie('phone'));
		
		$('.order').modal('show');
		$('.order-plate-null').height( $('.order-plate-item').height());
		
	});
	
	//获取所有餐盘数据
	function getPlateList() {
		
		var data = {
			plateList: [],
			totalPrice: 0
		};
		
		$('.plate-list .plate-item').each(function(){
			var plateItem = {};
			plateItem.price = getNumber($(this).find('.plate-price').text());
			plateItem.foodList = [];
			$(this).find('.plate-food-item').each(function(){
				var food = {}, $self = $(this);
				food.foodId = $self.data('foodid');
				food.name = $self.data('foodname');
				food.price = Number($.trim($self.data('price')));
				food.count = getNumber($self.find('.food-count').text());
				data.totalPrice += (food.price * food.count);
				plateItem.foodList.push(food);
			});
			if(plateItem.foodList.length > 0) {
				data.plateList.push(plateItem);
			}
		
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
			var food = plate.foodList[j], strCount = '';
			if(food.count>1) {
				strCount = '×' + food.count;
			}
			html += '<li class="order-food-item">' + food.name + strCount + '</li>';
		}
		html +='</ul>';
		html +='<h1 class="order-plate-price">';
		html += '<em class="item-price">￥' + plate.price + '</em>';
		html +='</h1>';
		html += '</div>';
		
		return html;
	};
	
	//选择送餐时间
	$('.excepttime-list a').click(function(){
		$(this).addClass('curr');
		$(this).siblings().removeClass('curr');
	});
	
	
	//提交订单到后台
	$('.submit-btn').click(function(){
		
		var orderVO = {
			shopId: 1,
			userRemark: $.trim($('#remark').val()),
			address: $.trim($('#address').val()),
			phone: $.trim($('#phone').val()),
			exceptTimeType: $('.excepttime-list .curr').data('type'),
			foodList: []
		};
		
		if(orderVO.phone=='') {
			$('#phone').tooltip({
				title: '请输入手机号码'
			}).tooltip('show');
			return;
		}else if(!isMobile(orderVO.phone) && !isTel(orderVO.phone)) {
			$('#phone').tooltip('destroy').tooltip({
				title: '请输入正确的手机号码或电话号码'
			}).tooltip('show');
			return;
		}
		
		if(orderVO.address=='') {
			$('#address').attr('title','请输入你的地址').tooltip('show');
			return;
		}
		
		for(var i=0; i < orderData.plateList.length; i++){
			var plate = orderData.plateList[i];
			for(var j=0; j < plate.foodList.length; j++) {
				var food = plate.foodList[j];
				var orderItem = {
					itemId: food.foodId,
					amount: food.count,
					plate: i+1
				}
				orderVO.foodList.push(orderItem);
			}
		}
		
		//保存地址和电话信息到cookie
		$.cookie('address', orderVO.address, {path: '/'});
		$.cookie('phone', orderVO.phone, {path: '/'});
		
		OrderAction.submitOrder(orderVO, function(result){
			$('.order').modal('hide');
			alert(result.message);
		});
		
		//$(".alert").alert();
		
		
	});
	
	
	//取消按钮
	$('.cancle-btn').click(function(){
		$('.order').modal('hide');
	});
	
	
	
	
})
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/web/inc/head.jsp" %>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%
	Calendar c = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String today = sdf.format(c.getTime());
	c.add(Calendar.DATE, -1);
	String yesterday = sdf.format(c.getTime());
	c.add(Calendar.DATE, -1);
	String theDayBeforeYesterday = sdf.format(c.getTime());
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>订单管理-已处理页面</title>
		<link rel="stylesheet" href="${webRoot}/web/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="${webRoot}/web/css/order.css">
		<style type="text/css">
			body {
				/*padding: 50px 0px;*/
				/*background-color: #f5f5f5;*/
			}
		</style>
	</head>
	<body>
		<div class="row-fluid container-fixed">
			<div class="span12">
				<div class="querybar">
					<form:form method="post" modelAttribute="orderQueryForm" cssClass="form-search" action="${webRoot }/shop/history">
					<div class="row-fluid">
						<div class="span4">
							<div class="input-prepend">
								<button type="submit" class="btn"><i class="icon-search"></i></button>
								<!-- <input type="text" class="search-query search-query-width" placeholder="search" > -->
								<form:input path="keyword" cssClass="search-query search-query-width" placeholder="search"/>
							</div>
						</div>
						<div class="span4">
							<div id="btn-group-date" class="btn-group" data-toggle="buttons-radio">
								<button type="button" class="btn " value="<%=today %> 00:00:00~<%=today %> 23:59:59">最近一天</button>
								<button type="button" class="btn " value="<%=yesterday %> 00:00:00~<%=today %> 23:59:59">最近两天</button>
								<button type="button" class="btn " value="<%=theDayBeforeYesterday %> 00:00:00~<%=today %> 23:59:59">最近三天</button>
								<button type="button" class="btn " value="">全部</button>
							</div>
						</div>
						<div class="span4">
							<div id="btn-group-status" class="btn-group" data-toggle="buttons-radio">
								<button type="button" class="btn " value="1,2,3">全部</button>
								<button type="button" class="btn " value="1,3">正常</button>
								<button type="button" class="btn " value="2">异常</button>
							</div>
						</div>
					</div>
					
					<form:hidden path="pageNo"/>
					<form:hidden path="pageSize"/>
					<form:hidden path="pageCount"/>
					<form:hidden path="orderStatus"/>
					<form:hidden path="startDate"/>
					<form:hidden path="endDate"/>
					</form:form>
				</div>
				
				<div class="common-list table-list">
					<ul>
						<c:forEach items="${orderList}" var="order" >
						<li>
							<div id="table_item_${order.id }" class="list-item table-item <c:if test="${order.orderStatus == 2 }">abnormal</c:if>">
								<div class="row-fluid head">
									<div class="pull-left link left-panel">
										<span class="">${order.address }</span>
										<span class="forestgreen">${order.phone }</span>
									</div>
									<div class="pull-right">
										<span class="brown">${order.totalPrice }元 </span>
										<a href="#" class="btn btn-mini order-btn-abnormal" value="${order.id }" status="${order.orderStatus}"><i class="icon-warning-sign"></i>&nbsp;异常</a>
									</div>
								</div>
								<div class="row-fluid body">
									<div>
										<span class="order-number">订单号: ${order.id }</span>
										<span class="order-time">
											下单时间: ${order.createTime }
										</span>
										<c:if test="${!empty order.userRemark}">
										<span>备注:${order.userRemark }</span>
										</c:if>
									</div>
									<div class="food-collapse">
										<p class="food-list">
										<c:forEach items="${order.plateList }" var="plate">
											<span>餐盘${plate.no }:</span>
											<c:forEach items="${plate.foodList }" var="food">
											<span>${food.food } <c:if test="${food.amount > 1}">x${food.amount }</c:if></span>
											</c:forEach>
										</c:forEach>
										</p>
									</div>
									<div class="food-expand">
										<div>
											<ul class="plate-list">
											<c:forEach items="${order.plateList }" var="plate">
												<li>
													<ul class="food-list">
														<c:forEach items="${plate.foodList }" var="food">
														<li>${food.food } <c:if test="${food.amount > 1}">x${food.amount }</c:if></li>
														</c:forEach>
														<li><span class="price">￥${plate.price }</span></li>
													</ul>
												</li>
											</c:forEach>
											</ul>
										</div>
										<div class="exception-desc">
											<c:if test="${not empty order.exceptionDesc and order.orderStatus == 2}">
											异常描述:&nbsp;${order.exceptionDesc }
											</c:if>
										</div>
									</div>
								</div>
							</div>
						</li>
						</c:forEach>
						
						<c:if test="${fn:length(orderList) < 1}"><!-- empty -->
						<li>
							<div class="list-item table-item center">
								本列表暂无记录
							</div>
						</li>
						</c:if>
					</ul>
				</div>
				<!-- pagination -->
				<div id="page"></div>
			</div>
		</div>
        
		<div class="modal hide fade" id="exception_detail_dlg">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3>异常描述</h3>
            </div>
            <div class="modal-body">
                <textarea row="3" style="width:97%;" id="exception_detail_area"></textarea>
            </div>
            <div class="modal-footer">
                <a href="#" class="btn" data-dismiss="modal">关闭</a>
                <a href="#" class="btn btn-primary" id="btn_save_exception">保存</a>
            </div>
        </div>

		<script src="${webRoot}/web/seajs/sea.js" data-config="${webRoot}/web/js/config" data-main="${webRoot}/web/js/shop.history"></script>
	</body>
</html>

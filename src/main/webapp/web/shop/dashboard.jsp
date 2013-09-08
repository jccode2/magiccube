<?xml version="1.0" encoding="utf-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/web/inc/head.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>店长工作台</title>
		<link rel="stylesheet" href="${webRoot}/web/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="${webRoot}/web/bootstrap/css/bootstrap-responsive.min.css">
		<link rel="stylesheet" href="${webRoot}/web/css/charisma-app.css">
		<link rel="stylesheet" href="${webRoot}/web/css/order.css">
		<style type="text/css">
			body {
				/*padding: 50px 0px;*/
				/*background-color: #f5f5f5;*/
				padding-right: 20px;
			}
			.amount {
				font-size: 20px;
				font-weight: bold;
			}
		</style>
	</head>
	<body>
		<div class="row-fluid container-fixed span12">
			<ul class="breadcrumb">
				<li><a href="#">主页</a><span class="divider">/</span></li>
				<li><a href="#">店长工作台</a></li>
			</ul>
		</div>
        <div class="row-fluid">
            <div class="box span12">
                <div class="box-header well">
                    <h2><i class="icon-list-alt"></i> 今日实时销售情况</h2>
                    <div class="box-icon">
                        <a class="btn btn-minimize btn-round" href="#"><i class="icon-chevron-up"></i></a>
                    	<a class="btn btn-round" href="#" id="new_win"><i class="icon-resize-full"></i></a>
                        <!-- <a class="btn btn-close btn-round" href="#"><i class="icon-remove"></i></a> -->
                    </div>
                </div>
                <div class="box-content">
                    <div class="common-list">
                    	<table class="table table-striped table-border">
                    		<c:forEach items="${quantities}" var="entity">
                    		<tr>
                    			<td id="group_${entity.key.id }" width="70">${entity.key.groupName }</td>
                    			<td>
                    				<ul class="item-list">
			                    		<c:forEach items="${entity.value}" var="food">
			                    		<li id="food_${food.id }">
			                    			<div>
			                    				${food.foodName } ( <span class="amount">${food.amount}</span> )
			                    			</div>
			                    		</li>
			                    		</c:forEach>
			                    	</ul>
                    			</td>
                    		</tr>
                    		</c:forEach>
                    		
                    		<c:if test="${fn:length(quantities) < 1}"><!-- empty -->
							<tr>
								<td class="center">
									暂无记录
								</td>
							</tr>
							</c:if>
                    	</table>
                    	
                    </div>
                </div>
            </div>
        </div>

		
		<script src="${webRoot}/web/seajs/sea.js" data-config="${webRoot}/web/js/config" data-main="${webRoot}/web/js/shop.dashboard"></script>
	</body>
</html>

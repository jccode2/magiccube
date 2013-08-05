<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/web/inc/head.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>公告管理</title>
		<link rel="stylesheet" href="${webRoot}/web/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="${webRoot}/web/bootstrap/css/bootstrap-responsive.min.css">
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
					<div class="row-fluid">
                        <input type="button" id="btn_insert" class="btn btn-primary pull-right" value="新增"/>
					</div>
				</div>
			</div>
		</div>
        <table class="table table-striped">
            <tr>
                <th>内容</th>
                <th width="10%" align="center">排序</th>
                <th width="10%" align="center">启用</th>
                <th width="10%" align="center">操作</th>
            </tr>
            <tr>
                <td>优惠一:10点半前订餐送水果一份</td>
                <td>1</td>
                <td><input type="checkbox" name="enable" value="1" /></td>
                <td><i class="icon-ok"></i><i class="icon-remove"></i></td>
            </tr>
            <tr>
                <td>优惠二:团体10份以上，每份减1元</td>
                <td>2</td>
                <td><input type="checkbox" name="enable" value="1" /></td>
                <td><i class="icon-ok"></i><i class="icon-remove"></i></td>
            </tr>
        </table>

		<script src="${webRoot}/web/seajs/sea.js" data-config="${webRoot}/web/js/config" data-main="${webRoot}/web/js/shop.bulletin"></script>
        <script src="http://localhost:8009/swank-js/swank-js-inject.js"></script>
	</body>
</html>

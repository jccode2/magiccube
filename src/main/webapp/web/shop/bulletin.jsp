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
		<title>公告管理</title>
		<link rel="stylesheet" href="${webRoot}/web/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="${webRoot}/web/bootstrap/css/bootstrap-responsive.min.css">
		<link rel="stylesheet" href="${webRoot}/web/css/order.css">
		<style type="text/css">
			body {
				/*padding: 50px 0px;*/
				/*background-color: #f5f5f5;*/
			}
            i {
                cursor:pointer;
            }
            .bulletin-list .sort input {
                width: 20px;
                margin-bottom: 0px;
            }
            .bulletin-list .content input {
                width: 95%;
                margin-bottom: 0px;
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
        <table id="bulletin_list" class="table table-striped bulletin-list">
            <thead>
                <tr>
                    <th>内容</th>
                    <th width="10%" align="center">排序</th>
                    <th width="10%" align="center">启用</th>
                    <th width="10%" align="center">操作</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="content">优惠一:10点半前订餐送水果一份</td>
                    <td class="sort">1</td>
                    <td class="enable"><input type="checkbox" name="enable" value="1" disabled checked/></td>
                    <td><i class="icon-edit btn-edit"></i>&nbsp;<i class="icon-remove btn-remove"></i></td>
                </tr>
                <tr>
                    <td class="content">优惠二:团体10份以上，每份减1元</td>
                    <td>2</td>
                    <td class="enable"><input type="checkbox" name="enable" value="1" disabled/></td>
                    <td><i class="icon-ok btn-save"></i>&nbsp;<i class="icon-remove btn-remove"></i></td>
                </tr>
                <tr>
                    <td class="content"><input type="text" name="content" value="" /></td>
                    <td class="sort"><input type="text" name="sort" value="" /></td>
                    <td class="enable"><input type="checkbox" name="enable" value="1" /></td>
                    <td><i class="icon-ok btn-save"></i>&nbsp;<i class="icon-remove btn-remove"></i></td>
                </tr>
                <tr>
                    <td class="content">优惠二:团体10份以上，每份减1元</td>
                    <td>2</td>
                    <td class="enable"><input type="checkbox" name="enable" value="1" disabled/></td>
                    <td><i class="icon-ok btn-save"></i>&nbsp;<i class="icon-remove btn-remove"></i></td>
                </tr>
            </tbody>
        </table>

        <script id="tpl_edit_row" type="text/script">
            <tr>
                <td class="content"><input type="text" name="content" value="" /></td>
                <td class="sort"><input type="text" name="sort" value="" /></td>
                <td class="enable"><input type="checkbox" name="enable" value="1" /></td>
                <td><i class="icon-ok btn-save"></i>&nbsp;<i class="icon-remove btn-remove"></i></td>
            </tr>
        </script>

		<script src="${webRoot}/web/seajs/sea.js" data-config="${webRoot}/web/js/config" data-main="${webRoot}/web/js/shop.bulletin"></script>
        <script src="http://localhost:8009/swank-js/swank-js-inject.js"></script>
	</body>
</html>

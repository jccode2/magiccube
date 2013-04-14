<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/web/inc/header.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>餐点管理-分类管理</title>
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
						<div class="span4">
						</div>
						<div class="span4">
						</div>
						<div class="span4 btn-right">
							<button type="button" class="btn" id="btn_delete" data-toggle="button">删除</button>
							<button type="button" class="btn" id="btn_edit" data-toggle="button">编辑</button>
							<button type="button" class="btn" id="btn_insert" data-toggle="button">新增</button>
						</div>
					</div>
				</div>
				
				<div id="groupList" class="common-list group-list">
					<ul>
						<c:forEach items="${groupList}" var="group">
						<li id="item_${group.id }">
							<div class="list-item">
								<div class="pic">
									<img id="img_${group.id }" src="${webRoot}/${group.image }" alt="${group.groupName }">
								</div>
								<div class="btns hide">
									<i class="icon2-edit" value="${group.id }"></i>
								</div>
								<div class="text">
									<p class="header">${group.groupName }</p>
									<p class="detail">${group.detail }</p>
								</div>
							</div>
						</li>
						</c:forEach>
						
						<c:if test="${fn:length(groupList) < 1}"><!-- empty -->
						<li>
							<div class="list-item center">
								本列表暂无记录
							</div>
						</li>
						</c:if>
					</ul>
				</div><!-- /end of group list -->
				
				<div id="add_panel" class="panel-bottom hide">
					<form class="group-list" id="groupForm" name="groupForm" action="${webRoot}/shop/group" method="post" enctype="multipart/form-data">
						<div class="pic">
							<div class="img-wapper"><img id="image-preview" src="" data-toggle="tooltip" data-placement="right" data-original-title="图片不能为空" data-validate="imgValid();"></div>
							<div class="img-tips hide">点击上传图片</div>
							<input type="file" id="file-upload" name="file" class="hide">
						</div>
						<div class="btns">
							<button class="btn btn-primary btn-large" id="btn_save_group">保存</button>
						</div>
						<div class="text">
							<div class="control-group control-group-mini">
								<label for="" class="control-label">分类</label>
								<div class="controls"><input type="text" name="groupName" id="groupName" class="input-block-level" data-toggle="tooltip" data-original-title="分类不能为空" data-validate></div>
							</div>
							<div class="control-group control-group-mini">
								<label for="" class="control-label">描述</label>
								<div class="controls">
									<input type="text" name="detail" id="detail" class="input-block-level" data-toggle="tooltip" data-original-title="描述不能为空" data-validate>
								</div>
							</div>
						</div>
					</form>
				</div><!-- /end of add panel -->
				
				<form id="editForm" name="editForm" action="${webRoot}/shop/group" method="post" enctype="multipart/form-data">
					<input type="hidden" name="id">
					<input type="hidden" name="groupName">
					<input type="hidden" name="detail">
					<input type="file" id="edit-file-upload" name="file" class="hide">
				</form>
			</div>
		</div><!--/ end of container -->

		<script src="${webRoot}/web/seajs/sea.js" data-config="${webRoot}/web/js/config" data-main="${webRoot}/web/js/shop.group"></script>
		<script type="text/javascript">
			function imgValid() {
				return !!$("#file-upload").get(0).files[0];
			}
		</script> 
	</body>
</html>
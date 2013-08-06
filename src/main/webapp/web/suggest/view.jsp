<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>查看用户建议</title>

<style type="text/css">
.card {
	border: 1px;
	border-radius: 3px;
	border-style: solid;
	border-color: #DDDDDD;
	height: 90px;
	padding-left: 40px;
	padding-right: 40px;
	padding-top: 5px;
	margin:3px;
	margin-bottom:5px;
}

.card:hover {
	margin:0px;
	margin-bottom:5px;
	background-color: #FFEEFF
}

.card hr{
	margin-top:2px;margin-buttom:2px;
}
</style>

<script type="text/javascript">
	/* <![CDATA[ */

	/* ]]> */
</script>
</head>

<body>
	<c:forEach items="${suggests}" var="suggest">
		<div id="${suggest.id}" class="card">
			<p><span class="info">${suggest.userName }</span><span class="text-success">(${suggest.suggestTime })</span></p>
			<hr/>
			<p class="info">${suggest.suggestContent }</p>
		</div>
	</c:forEach>
</body>

</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<link href="<c:url value="/resources/css/Bootstrap.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/design.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-responsive.css"/>"
	rel="stylesheet">
<script src="<c:url value="/resources/js/mendwe.js"/>"></script>
<%-- <script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.min.js"/>"></script> --%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>me n we</title>
</head>
<body>
	<!-- <div class="wrapper"> -->
			<div class="row-fluid">

				<tiles:insertAttribute name="header"></tiles:insertAttribute>

			</div>
		
		
			<div class="row-fluid">
				<tiles:insertAttribute name="firstmain"></tiles:insertAttribute>

			</div>
		
		
			<div class="row-fluid">
				<div class="bodyPart">

					<tiles:insertAttribute name="body"></tiles:insertAttribute>
				</div>
			</div>
		
		
		
			<%-- <div class="row-fluid">
				<div class="span12">
					<div id="newpost" class="secondmain">

						<tiles:insertAttribute name="hotornot"></tiles:insertAttribute>
					</div>
				</div>
			</div> --%>
<%-- 
			<div class="row-fluid">
				<div class="span12">
					<div class="nfooter">

						<tiles:insertAttribute name="footer"></tiles:insertAttribute>
					</div>
				</div>
		</div> --%>

<!-- </div> -->
</body>
</html>


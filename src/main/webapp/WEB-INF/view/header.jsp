<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<div class="nheader">

	<div class="span2 visible-desktop">
		<font style="font-size: 20px;">me n we</font>
	</div>

<div class="span12 visible-phone">
<font style="font-size: 20px;">me n we</font>
	<a href="<c:url value="/home.html"/>" class="offset1" title="Home"
			style="#"><i class="icon-home icon-white"></i></a>&nbsp; <a href=""
			title="My Profile"><i class="icon-user icon-white"></i></a>&nbsp; <a
			href="#" title="Settings"><i class="icon-wrench icon-white"></i></a>&nbsp;
		<a href="<c:url value="/logout.html"/>" title="Log-Out"><i
			class="icon-off icon-white"></i></a>
</div>

	<div class="span5 visible-desktop">
		<form action="search.html">
			<input type="search" name="searchBox" placeholder="Search"
				style="float: left; position: relative; width: 80%; height: 15px; border-radius: 4px 0px 0px 4px;">
			<button type="submit" class="btn"
				style="float: left; border-radius: 0px 4px 4px 0px;">
				<i class="icon-search"></i>
			</button>
		</form>
	</div>



	<div class="span2 visible-desktop">
		<img src="<c:url value="/resources/images/images11.jpg"/>"
			class=" profile-img-circle"> <font style="font-size: 17px;"><%=session.getAttribute("username")%></font>
	</div>

	<div class="span3 visible-desktop">
		<a href="<c:url value="/home.html"/>" class="offset1" title="Home"
			style="#"><i class="icon-home icon-white"></i></a>&nbsp; <a href=""
			title="My Profile"><i class="icon-user icon-white"></i></a>&nbsp; <a
			href="#" title="Settings"><i class="icon-wrench icon-white"></i></a>&nbsp;
		<a href="<c:url value="/logout.html"/>" title="Log-Out"><i
			class="icon-off icon-white"></i></a>
	</div>

</div>
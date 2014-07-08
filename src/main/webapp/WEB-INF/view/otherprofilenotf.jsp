<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<div class="row-fluid">
	<div class="span2">Left Blank</div>
	<div class="span6">
	      <font style="font-size:18px; font-weight:800; color:#2F4F4F;">${userProp.name}</font><br/>
		<b>Common things between us</b>
	</div>

	<div class="span2">
		<br /> 
		<!-- <a  href="#"><i class="icon-star"></i> Star</a> -->
		<c:choose>
      <c:when  test='${userProp.isFriend}.equals("true")'>
      <a class="btn" href=""><i
			class="icon-minus-sign"></i>Friend</a>
      </c:when >
      <c:otherwise>
      <a class="btn" href=""><i class="icon-plus"></i>Friend</a>
      </c:otherwise>
      </c:choose>
	</div>
	<div class="span2">Left Blank</div>

</div>






<hr />
<div class="row-fluid">
	<div class="span2">Left Blank<br/>
	
<%-- 	<c:forEach items="${userProp}" var="listItem"   varStatus="status">
 --%>				 
					${userProp.username}
					
				<%-- </c:forEach> --%>
	</div>
	
	<div class="span2">
		<b>About</b><br />
		<ul>
			<li>Hometown</li>
			<li>Lives-in ${userProp.currentCity}</li>
			<li>Organisation working with</li>
			<li>College</li>
		</ul>
		<b>Contact</b><br />
		<ul>
			<li>Mail Id  ${userProp.mailId}</li>
			<li>Phone no</li>
			
		</ul>
		
	</div>


<%

	int mutualFriends = 5;
	int angle=0;
	if(mutualFriends ==0){
		
	}else{
		angle = 360/mutualFriends;	
	}
	
	int degree=0;
%>

	<div class="span4">

		<div class="circle-container">
    <a href='#' class="center"><img src="<c:url value="/resources/images/images41.jpg"/>" /></a>
    
    <%
    	
    	for(int x=0;x<mutualFriends;x++){
    		
    %>
    		<a href='#' class="deg<%=degree%>"><img src="<c:url value="/resources/images/images41.jpg"/>" /></a>
    		
    <%	
    	degree=degree+angle;
    }
    %>

</div>

		<br /> <br />
		<br />
		<br />
		<br />
		<br />
	</div>

	<div class="span2">
		<b>My Likes</b><br />
		<ul>
			<li>Drink</li>
			<li>Interested in / Hobby</li>
			<li>Food</li>
			<li>Veg/Non-veg</li>
		</ul>
	</div>
	<div class="span2">Left Blank</div>
</div>

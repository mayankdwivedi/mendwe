<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<div class="span2">
Left Blank
</div>

<div class="span6">
<c:forEach items="${userMap}" var="outerMap">
<div class="searchBox">
<img src="<c:url value="/resources/images/images41.jpg"/>"
				class="imgPostBox"> 
    <a href="otherprofilenotf.html?username=${outerMap.key}"><font style="font-size:18px; font-weight:500;">${outerMap.value.name}</font></a><br/>
    <font style="font-size:12px; font-weight:100; color:#c0c0c0">${outerMap.value.work}</font><br/>
   <font style="font-size:14px; font-weight:100;">${outerMap.value.currentCity}</font><br/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <font style="font-size:14px; font-weight:100;">${outerMap.value.hometown}</font>
     <img src="<c:url value="/resources/images/go_home.png"/>" class="imghomesearch"> 
    
    </div>
</c:forEach>
</div>

<div class="span2">
Right Blank
</div>

<div class="span2">
Rightmost Blank
</div>
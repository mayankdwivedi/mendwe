<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<div class="row-fluid">
  <div class="span2">Left Blank<br/>
			 
  </div>
   <div class="span8">
   <c:forEach items="${friendAndMutual}" var="listItem"   varStatus="status">
     <div class="friendbox"> 
          <img src="<c:url value="/resources/images/images41.jpg"/>" class="img-rounded" /><br/>
          <b>${listItem.key}</b><br/>
     	 <%-- <img src="<c:url value="/resources/images/images41.jpg"/>" class="img-rounded" /><br/> --%>
     	 ${listItem.value} Mutual Friends<br/>
		 <a  href="#" ><i class="icon-volume-off"></i></a>
		 <a  href="#" ><i class="icon-volume-down"></i></a>
		 <a  href="#" ><i class="icon-volume-up"></i></a>
     </div>
     </c:forEach>
	<div class="friendbox"> 
     	 <img src="<c:url value="/resources/images/images41.jpg"/>" class="img-rounded" /><br/>
     	 <b>Mayank Dwivedi</b><br/>
     	 2 Mutual Friend<br/>
		 <a  href="" ><i class="icon-volume-off"></i></a>
		 <a  href="" ><i class="icon-volume-down"></i></a>
		 <a  href="" ><i class="icon-volume-up"></i></a>
     </div>
    <div class="friendbox"> 
     	 <img src="<c:url value="/resources/images/images41.jpg"/>" class="img-rounded" /><br/>
     	 <b>Mayank Dwivedi</b><br/>
     	 2 Mutual Friend<br/>
		 <a  href="" ><i class="icon-volume-off"></i></a>
		 <a  href="" ><i class="icon-volume-down"></i></a>
		 <a  href="" ><i class="icon-volume-up"></i></a>
     </div>
          
   
   </div>
   
   <div class="span2">Left Blank</div>

</div>

<hr/>



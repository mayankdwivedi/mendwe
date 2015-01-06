<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<div class="span3">
</div>

<div class="span6">

<font style="font-size: 18px; font-weight: 800;">Story Pane</font>
<hr>
	<c:forEach items="${allStoriesMap}" var="allStoriesMapItem" varStatus="status" >
	<div class="well">
	<a href="fullstory.html?storyID=${allStoriesMapItem.key}">${allStoriesMapItem.value.title}</a><br>
	${allStoriesMapItem.value.shortDescription}<br>
	${allStoriesMapItem.value.points}<br>
	${allStoriesMapItem.value.createdBy}
	</div>
	</c:forEach>

</div>

<div class="span3">
</div>

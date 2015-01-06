<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>


<div class="span3">
</div>

<div class="span6">
<font style="font-size: 18px; font-weight: 800;">Story</font>
<hr>
${message}
<c:if test="${not empty storyInfo}">
<div class="well">
${storyInfo.title}<br>
${storyInfo.shortDescription}<br>
${storyInfo.longDescription}<br>
${storyInfo.createdDate}<br>
${storyInfo.createdBy}
</div>
</c:if>


</div>

<div class="span3">
</div>
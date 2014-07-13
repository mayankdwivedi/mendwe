<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<div class="span2 visible-desktop">
	Left Blank <br>
	
	<h4>uploaded file </h4>
	<img src="http://mayrit.herobo.com/images/downloadc.jpg">
	<c:forEach var="person" items="${friendList}">
  ${person} <a class="btn btn-primary"
			href="accept.html?fusername=${person}">Accept</a>
		<a class="btn btn-primary" href="reject.html?fusername=${person}">Reject</a>
		<br>
	</c:forEach>

</div>

<div class="span6">
	<form method="get" action="postsomething.html">
		<textarea rows="3" cols="50" id="postTextBox"
			style="width: 100%; height: 60px; font-size: 14px; resize: none;"
			placeholder="Post something ...." name="text"></textarea>
		<!-- <input type="text" id="postTextBox"
			style="width: 100%; height: 40px; font-size: 20px;"
			placeholder="Post something ...." name="text" /> -->
		<br /> <input type="submit" class="btn btn-small" value="Post" />&nbsp;

		<a href="#myModal" title="Image Upload" role="button" class="btn btn-success" data-toggle="modal"><i class="icon-camera"></i></a>
			 &nbsp; <a href="#" title="Dedicate"
			class="btn btn-success" onclick="fpostBox('Dedicate my success to')"><i
			class="icon-road"></i></a>&nbsp; <a href="#" title="Spring"
			class="btn btn-success"
			onclick="fpostBox('Source of Spring to my life')"><i
			class="icon-leaf"></i></a>&nbsp; <a href="#" title="My Star"
			class="btn btn-success" onclick="fpostBox('My star is')"><i
			class="icon-star"></i></a>&nbsp; <a href="#" title="My Love"
			class="btn btn-success" onclick="fpostBox('I Love')"><i
			class="icon-heart"></i></a>&nbsp;<a href="#" title="Shout it aloud"
			class="btn btn-success" onclick="fpostBox('STA')"><i
			class="icon-bullhorn"></i></a> &nbsp;<a href="#" class="btn btn-small"
			onclick="fpostBox('')">Reset</a>&nbsp; &nbsp; <input type="checkbox"
			class="btn" value="citylights" name="cCheckBox">&nbsp; &nbsp;
		City Lights&nbsp;
	</form>
	<hr />
	<c:forEach items="${postMap}" var="postItem" varStatus="status">
		<div class="post">
			<img src="<c:url value="/resources/images/images41.jpg"/>"
				class="imgPostBox"> <font
				style="font-size: 18px; font-weight: 500;">
				${postItem.value.username}</font><br /> <font
				style="font-size: 12px; font-weight: 100; color: #c0c0c0">${postItem.value.postdate}</font><br />
			<font style="font-size: 14px; font-weight: 100;">${postItem.value.postText}</font><br />

			<!-- <div id="result"></div>
			<button type="button" onclick="crunchifyAjax()"><i
				class="icon-thumbs-up"></i></button> -->
			<div id="${postItem.key}">${postItem.value.StotalLikes}</div>
			<a href="#" title="Like" onclick="crunchifyAjax(${postItem.key})"><i
				class="icon-thumbs-up"></i></a>&nbsp; <a href="#" title="Unlike"><i
				class="icon-thumbs-down"></i></a>&nbsp; <a href="#" title="Comment"><i
				class="icon-edit"></i></a>

		</div>

	</c:forEach>
</div>

<div class="span2 visible-desktop">
	<div class="activityBox">
		<b>Birthday and other activities</b> <input type="submit" value="btn"
			onclick="toggle();"></input>
	</div>
</div>

<div class="span2 visible-desktop">

	<a href="" title="Facebook Import"><img
		src="<c:url value="/resources/images/facebook.jpg"/>" alt="Facebook"
		style="width: 30px; height: 30px;"></a> <a href=""
		title="Gmail Import"><img
		src="<c:url value="/resources/images/gmail.jpg"/>" alt="Gmail"
		style="width: 30px; height: 30px;"></a> <a href=""
		title="Twitter Import"><img
		src="<c:url value="/resources/images/twitter.jpg"/>" alt="Twitter"
		style="width: 30px; height: 30px;"></a> <a href=""
		title="Linked-In Import"><img
		src="<c:url value="/resources/images/linkedin.jpg"/>" alt="Linked-In"
		style="width: 30px; height: 30px;"></a> <br /> <br />
	<div class="activityBox">
		<b>City Lights</b><br />
		<marquee onmouseover="this.setAttribute('scrollamount', 0, 0);"
			onmouseout="this.setAttribute('scrollamount', 2, 0);" direction="up"
			height="150px;" scrollamount="2">
			<c:forEach items="${cLightsList}" var="cLightsItem"
				varStatus="status">
				<div class="insideActivityBox">${cLightsItem}</div>
			</c:forEach>
		</marquee>
	</div>

	<hr />
	<div class="activityBox">
		<b>News</b><br />
		<c:forEach items="${newsMap}" var="newsMapItem" varStatus="status">
			<div class="insideActivityBox">${newsMapItem.value}</div>
		</c:forEach>
	</div>

</div>

<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h3 id="myModalLabel">Modal header</h3>
	</div>
	<div class="modal-body">
		<form action="fileUpload.html" method="post" enctype="multipart/form-data">
                <input type="file" name="file" />
                <input type="submit" value="upload" />
            </form>  
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		<button class="btn btn-primary">Save changes</button>
	</div>
</div>
<script src="<c:url value="/resources/js/bootstrap-modal.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>

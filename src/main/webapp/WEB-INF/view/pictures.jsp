<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	
<div class="span2">
</div>

<div class="span6">	
<font style="font-size: 18px; font-weight: 800;">Pictures</font>
<a href="#myModal" title="Image Upload" role="button" class="btn btn-success" data-toggle="modal" style="float:right;"><i class="icon-camera"></i></a><br/><br/><br/>
<hr>
<c:forEach var="imagesName" items="${imagesName}">
<img src="${imagePath}${imagesName}" class="picturebox"/>
</c:forEach>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>

</div>

<div class="span2">
Blank
</div>

<div class="span2">
Blank
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
                <input type="file" name="fileUpload" />
                <input type="submit" value="upload" />
            </form>  
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		<button class="btn btn-primary">Save changes</button>
	</div>
</div>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>



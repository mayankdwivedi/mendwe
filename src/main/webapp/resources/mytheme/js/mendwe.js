function fpostBox(text){
	document.getElementById("postTextBox").value=text;
}

function crunchifyAjax(postId) {
    alert(postId);
	$.ajax({
        url : 'ajaxtest.html',
        data : "postId=" + postId,
        success : function(data) {
            $('#'+postId).html(data);
        }
    });
}

var toggle = function() {
	  var mydiv = document.getElementById('newpost');
	  if (mydiv.style.display === 'block' || mydiv.style.display === '')
	    mydiv.style.display = 'none';
	  else
	    mydiv.style.display = 'block'
	  };
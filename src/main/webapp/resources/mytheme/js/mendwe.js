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

function togglevisibility(divid) {
	  var mydiv = document.getElementById(divid);
	  if (mydiv.style.display === 'block' || mydiv.style.display === '')
	    mydiv.style.display = 'none';
	  else
	    mydiv.style.display = 'block';
	  };
	  
	  
function firedebate(tohide,tounhide,answer,qno){
	    var tounhidediv=document.getElementById(tohide);
		 alert(tounhidediv);
		  $.ajax({
		        url : "setdebateanswers.html",
		        data : "answer=" + answer+"&qno="+qno,
		        success : function(data) {
		        	alert("##########################################Inside Ajax success");
		            $('#'+tounhide).html(data);
		        }
		    });
		  tohide.style.display = 'none';
		  tounhide.style.display = 'block';
		
	  }
	  
function crunchifyAjax2() {
    alert("inside crunchifyAjax2 ");
	$.ajax({
        url : 'ajaxtesttwo.html',
        data : "postId=2",
        success : function(data) {
            $('#bar1').html(data);
        }
    });
}
	

	  
	  
	  function setDebateAnswers(answer,qno) {
		    alert("inside loadDebateQuestion");
			$.ajax({
		        url : 'setDebqteAnswers.html',
		        data : "answer=" + answer+"qno="+qno,
		        success : function(data) {
		            $('#'+bar1).html(data);
		        }
		    });
		}
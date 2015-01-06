<div class="span3"></div>



<div class="span6">

<font style="font-size: 18px; font-weight: 800;">Add Story</font>
<hr>

<form method="post" action="submitStory.html">
  
  <input type="text"  style="width: 100%; height: 20px; font-size: 14px;" placeholder="Title" name="title" /><br/>
  <textarea rows="2" cols="50"  style="width: 100%; height: 40px; font-size: 14px; resize: none;"
			placeholder="Short Description" name="shortDescription"></textarea>
<textarea rows="5" cols="50"  style="width: 100%; height: 180px; font-size: 14px; resize: none;"
			placeholder="Long Description" name="longDescription"></textarea>
<!--   <input type="password" style="width:480px; height:40px; font-size:20px;" placeholder="Points" name="points" /><br/>
 -->  <select name="points" >
  <option value="20">20</option>
  <option value="40">40</option>
  <option value="60">60</option>
  <option value="80">80</option>
  <option value="100">100</option>
</select>
  <br/>
  <button type="submit" class="mbutton">Add Story</button>
</form>
</div>
<div class="span3">Blank</div>

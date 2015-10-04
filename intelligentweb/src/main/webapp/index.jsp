<html>

<body bgcolor="#FFFFF0">

<br/>
<br/>
<h2 style="font-family:Arial,sans-seri"" align="center">Verizon Data Intelligence System</h2>
<div style="width:100%">
<div style="width:400px;margin:0 auto" >
<form>
	<label style="font-family:Arial,sans-seri" for="pnum">Please select a number</label>&nbsp;&nbsp;
	<select name="mdn" id="pnum">
		<option value="" selected></option>
		<option value="9176275565">9176275565</option>
		<option value="3054126895">3054126895</option>
		<option value="2158275565">2158275565</option>
		<option value="2158275566">2158275566</option>
		<option value="3176271166">3176271166</option>
		<option value="3176271167">3176271167</option>
		<option value="5049134447">5049134447</option>
		<option value="6500065000">6500065000</option>
		
	</select>
	<br/><br/><br/>
	&nbsp;&nbsp;<input style="margin-left:80px;width:130px;" type="button" id="simple" value="Find Suggestion" /> </br>
	
</form>

</div>
<div id="intelligentResult" style="margin:0 auto;width:450px;font-family:Arial,sans-seri";font-size:14px;"></div>
</div>
<script src="jquery-1.11.0.min.js"></script>
<script>
$(document).ready(function(){
  $("#simple").click(function(e){
	  var pnum = $("#pnum").val();
	 
    $.ajax({type: "GET",
            url: "getdata",
            data: { mdn: pnum},
            success:function(result){
            //	alert(result);
      $("#intelligentResult").html(result);
    }});
  });
});
</script>

</body>
</html>

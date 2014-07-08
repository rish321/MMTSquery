<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MMTS Query</title>
	<link rel="stylesheet" type="text/css" href="stylesheets/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="stylesheets/bootstrap.min.css" />
	
	<script type="text/javascript" src="javascripts/jquery.js"></script>
	<script type="text/javascript" src="javascripts/jquery.min.js"></script>
	<script type="text/javascript" src="javascripts/bootstrap.js"></script>
	<script type="text/javascript" src="javascripts/bootstrap.min.js"></script>
</head>
<body>
<h1>MMTS Query System</h1>
<div class="container-fluid" style="border-radius:10px; box-shadow:10px 10px 10px 10px rgba(0,0,0,0.4); padding:20px; width:65%; margin:auto; ">
	<form action="" method="post" style="align:center">
		<div class="form-group">
			<label for="query" class="col-sm-2 control-label" style="float:left ">Ask your <i>Query</i> here : &nbsp;</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="query" style="width:600px;" placeholder="Enter your Hindi query in English here (query will be automatically transliterated to Hindi)" name="query" autocomplete="off" >
				&nbsp;
				<button type="submit" name="submit" value="Submit" class="btn btn-success">Submit</button>
			</div>
		</div>
		</br>
		<div class="container-fluid" style="border-radius:10px; box-shadow:5px 5px 6px 6px rgba(0,0,0,0.4); padding:20px; width:90%; margin:auto; ">
		<div class="form-group">
			<label for="query" class="col-sm-2 control-label" style="float:left "><b><i>Query</i></b> : &nbsp;</label>
			<div class="col-sm-10">
				<?php echo $query."</br>"; ?>
			</div>
		</div>
		</br>
		<div class="form-group">
			<label for="query" class="col-sm-2 control-label" style="float:left "><b><i>Result</i></b> : &nbsp;</label>
			<div class="col-sm-10">
				<?php echo $result."</br>"; ?>
			</div>
		</div>
		</div>
		</br>
		<div style="float:right;">
		<script>
			var s = document.createElement('script'); s.setAttribute('src','http://developer.quillpad.in/static/js/quill.js?lang=Hindi&key=1fda454ed642981b513705279fb626ee'); s.setAttribute('id','qpd_script'); document.head.appendChild(s);
		</script>
		<span id="qpd_banner">Hindi Typing On This Site Is Powered By <a href="http://www.quillpad.in/" target="_blank">Quillpad</a>.</span>
		</div>
	</form>
</div>
</body>
</html>
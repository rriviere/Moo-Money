<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>jQuery File Upload Example</title>
<script src="resources/js/jquery-1.10.1.min.js"></script>

<script src="resources/js/jquery.ui.widget.js"></script>
<script src="resources/js/jquery.iframe-transport.js"></script>
<script src="resources/js/jquery.fileupload.js"></script>

<!-- bootstrap just to have good looking page -->
<link href="resources/css/bootstrap-2.3.1-css-only/css/bootstrap.css" type="text/css" rel="stylesheet" />
<script src="resources/css/bootstrap-3.1.1-dist/js/bootstrap.min.js"></script>

<!-- we code these -->
<link href="resources/css/custom-moo-money.css" type="text/css" rel="stylesheet" />
<script src="resources/js/pages/importExpenses.js"></script>
</head>

<body>
	<div style="width: 500px; padding: 20px">

		<input id="fileupload" type="file" name="files[]"
			data-url="controller/upload.htm" multiple>

		<div id="dropzone" class="fade well">Drop files here</div>

		<div id="progress" class="progress">
			<div class="bar" style="width: 0%;"></div>
		</div>

	</div>
</body>
</html>

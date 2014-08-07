<!-- ====================================================================== -->
<!-- header files									 		 		-->
<!-- ====================================================================== -->
  <%@ include file="/WEB-INF/pages/common/header.jspf" %>
<!-- end header files -->  
  <body>
	<!-- ====================================================================== -->
	<!-- horizontal navigation									 		 		-->
	<!-- ====================================================================== -->
    <%@ include file="/WEB-INF/pages/common/navHorizontal.jspf" %>
    <!-- end horizontal navigation -->
    
    <div class="container-fluid">
      <div class="row">
		<!-- ====================================================================== -->
		<!-- vertical navigation									 		 		-->
		<!-- ====================================================================== -->
        <%@ include file="/WEB-INF/pages/common/navVertical.jspf" %>
        <!-- end vertical navigation -->  
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">Expenses</h1>

          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>Date</th>
                  <th>Description</th>
                  <th>Debit</th>
                  <th>Credit</th>
                  <th>Tags</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1,001</td>
                  <td>Lorem</td>
                  <td>ipsum</td>
                  <td>dolor</td>
                  <td><input type="text" class="eetag" name="tag"></td>
                </tr>
                <tr>
                  <td>1,002</td>
                  <td>amet</td>
                  <td>consectetur</td>
                  <td>adipiscing</td>
                  <td><input type="text" class="eetag" name="tag"></td>
                </tr>
                <tr>
                  <td>1,003</td>
                  <td>Integer</td>
                  <td>nec</td>
                  <td>odio</td>
                  <td><input type="text" class="eetag" name="tag"></td>
                </tr>
                <tr>
                  <td>1,003</td>
                  <td>libero</td>
                  <td>Sed</td>
                  <td>cursus</td>
                  <td><input type="text" class="eetag" name="tag"></td>
                </tr>
                <tr>
                  <td>1,004</td>
                  <td>dapibus</td>
                  <td>diam</td>
                  <td>Sed</td>
                  <td><input type="text" class="eetag" name="tag"></td>
                </tr>
                <tr>
                  <td>1,005</td>
                  <td>Nulla</td>
                  <td>quis</td>
                  <td>sem</td>
                  <td><input type="text" class="eetag" name="tag"></td>
                </tr>
                <tr>
                  <td>1,006</td>
                  <td>nibh</td>
                  <td>elementum</td>
                  <td>imperdiet</td>
                  <td><input type="text" class="eetag" name="tag"></td>
                </tr>
                <tr>
                  <td>1,007</td>
                  <td>sagittis</td>
                  <td>ipsum</td>
                  <td>Praesent</td>
                  <td><input type="text" class="eetag" name="tag"></td>
                </tr>
                <tr>
                  <td>1,008</td>
                  <td>Fusce</td>
                  <td>nec</td>
                  <td>tellus</td>
                  <td><input type="text" class="eetag" name="tag"></td>
                </tr>
                <tr>
                  <td>1,009</td>
                  <td>augue</td>
                  <td>semper</td>
                  <td>porta</td>
                  <td><input type="text" class="eetag" name="tag"></td>
                </tr>
                <tr>
                  <td>1,010</td>
                  <td>massa</td>
                  <td>Vestibulum</td>
                  <td>lacinia</td>
                  <td><input type="text" class="eetag" name="tag"></td>
                </tr>
                <tr>
                  <td>1,011</td>
                  <td>eget</td>
                  <td>nulla</td>
                  <td>Class</td>
                  <td><input type="text" class="eetag" name="tag"></td>
                </tr>
                <tr>
                  <td>1,012</td>
                  <td>taciti</td>
                  <td>sociosqu</td>
                  <td>ad</td>
                  <td><input type="text" class="eetag" name="tag"></td>
                </tr>
                <tr>
                  <td>1,013</td>
                  <td>torquent</td>
                  <td>per</td>
                  <td>conubia</td>
                  <td><input type="text" class="eetag" name="tag"></td>
                </tr>
                <tr>
                  <td>1,014</td>
                  <td>per</td>
                  <td>inceptos</td>
                  <td>himenaeos</td>
                  <td><input type="text" class="eetag" name="tag"></td>
                </tr>
                <tr>
                  <td>1,015</td>
                  <td>sodales</td>
                  <td>ligula</td>
                  <td>in</td>
                  <td><input type="text" class="eetag" name="tag"></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster   
     -->
	<!-- Latest compiled and minified CSS -->

<link rel="stylesheet" href="resources/css/bootstrap-tagsinput/bootstrap-tagsinput.css">
<link rel="stylesheet" href="resources/css/bootstrap-tagsinput/app.css">
<!-- Optional theme -->


    <script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/bootstrap-tagsinput/typeahead.bundle.js"></script>
    <script src="resources/js/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script>
	<script>
		jQuery(document).ready(function(){
		$.get( 'tags.htm', {}, function(tdata){
			var eetags = new Bloodhound({
  datumTokenizer: Bloodhound.tokenizers.obj.whitespace('itemDescription'),
  queryTokenizer: Bloodhound.tokenizers.whitespace,
  local: tdata
});
eetags.initialize();

var elt = $('input.eetag');
elt.tagsinput({
  maxTags: 1,
  itemValue: 'itemCode',
  itemText: 'itemDescription',
  typeaheadjs: {
    name: 'itemCode',
    displayKey: 'itemDescription',
    source: eetags.ttAdapter()
  },
  tagClass: function(item) {
    switch (item.buttonType) {
      case 'primary'   : return 'label label-primary';
      case 'important'  : return 'label label-danger label-important';
      case 'success': return 'label label-success';
      case 'default'   : return 'label label-default';
      case 'warning'     : return 'label label-warning';
    }
  },
});
//elt.tagsinput('add', { "value": 1 , "text": "Amsterdam"   , "continent": "Europe"    });

});
		});
	</script>
  </body>
</html>

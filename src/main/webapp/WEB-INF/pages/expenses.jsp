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
				<c:forEach items="${transactions}" var="element"> 
				  <tr>
				    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${element.tranDate}"/></td>
				    <td>${element.tranDescription}</td>
				    <td>${element.debit}</td>
				    <td>${element.credit}</td>
				    <td><input type="text" class="eetag" name="tag"></td>
				  </tr>
				</c:forEach>              
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

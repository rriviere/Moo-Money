<!-- ====================================================================== -->
<!-- header files									 		 		-->
<!-- ====================================================================== -->
  <%@ include file="/WEB-INF/pages/common/headerWithTagsInput.jspf" %>
  <script type="text/javascript" src="<c:url value="resources/js/pages/parseTransactions.js" />"></script>
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
            <table id="tblTransactions" class="table table-striped">
              <thead>
                <tr>
                  <th class="col-md-1">Date</th>
                  <th class="col-md-3">Description</th>
                  <th class="col-md-1">Debit</th>
                  <th class="col-md-1">Credit</th>
                  <th class="col-md-3">Category</th>
                  <th class="col-md-3">Receipt</th>
                </tr>
              </thead>
              <tbody>
				<c:forEach items="${transactions}" var="element"> 
				  <tr>
				    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${element.tranDate}"/></td>
				    <td class="colTranDesc">${element.tranDescription}</td>
				    <td>${element.debit}</td>
				    <td>${element.credit}</td>
				    <td class="colCategory"><input type="text" class="eetag" name="tag"></td>
				    <td>
			            <div class="input-group">
			                <span class="input-group-btn">
			                    <span class="btn btn-primary btn-file">
			                        Browse&hellip; <input type="file" multiple>
			                    </span>
			                </span>
			                <input type="text" class="form-control" readonly>
			            </div>
			            <span class="help-block">
			                Press browse to upload a file..
			            </span>
				    </td>
				  </tr>
				</c:forEach>              
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>

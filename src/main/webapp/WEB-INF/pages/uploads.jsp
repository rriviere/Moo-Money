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
          <h1 class="page-header">Uploads</h1>

	      <div>	        
	        <iframe id="uploadFrame" 
	    		name="uploadFrame" 		
	    		class="uploadFrame"
	    		src="importExpenses.htm">
	    	</iframe>    	 
	      </div>


          <h2 class="sub-header">Uploaded documents</h2>
          <div class="table-responsive">
            <table id="uploaded-files" class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>File Name</th>
                  <th>File Size</th>
                  <th>File Type</th>
                  <th>Download</th>
                </tr>
              </thead>
              <tbody>		
		       <c:forEach items="${files}" var="file">													
                    <tr>
                     <td>${file.fileId}</td>
                     <td>${file.fileName}</td>
                     <td>${file.fileSize} kb</td>
                     <td>${file.fileType}</td>
                     <td><a href='controller/get/${file.fileId}.htm'>Click</a></td>	
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

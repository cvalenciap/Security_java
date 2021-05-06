<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<footer class="footer">
  	<div class="container-fluid">
  		<div class="row-fluid">
  			<div class= "col-sm-12"><p class="text-center"><c:out value="${sessionScope.copy}"/></p>
  			</div>
	    </div>
  	</div>
 </footer>
 
 <!-- Menu Toggle Script -->
<script>
	$("#menu-toggle").click(function(e) {
		e.preventDefault();
		$("#wrapper").toggleClass("toggled");
		//alert('hola' + table);
		//table.clear().draw();		   
		//table.columns.adjust().draw(); // Redraw the DataTable
		
	});
</script>
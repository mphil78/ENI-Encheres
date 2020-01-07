<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 --%><!doctype html>
<html lang="fr">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Connexion</title>
  </head>
  
   <body>
		<div class="container">  
		   <nav class="navbar mb-5">
		   		<a href="./TraitementAccueil">
				<img src="${pageContext.request.contextPath}/images/imageLogo.png" width="100px" alt="image">
				</a>	
			</nav>
		</div>
			
		
		<c:if test="${ !empty requestScope.erreurConnexion }">
			<p class="text-danger">${erreurConnexion}</p>
		</c:if>
		
		   
	   <div class="container">
	   		<div class="row">
	   			<div class="col-md-8 offset-md-2"> 
	   				<div class="jumbotron"> 
	   			   		<form name="formulaire" action="./TraitementConnexion" method="post">    	
							<div class="form-group row">
					    		<label for="identifiant" class="col-3 ">Identifiant</label>
					    		<input type="text" class="form-control col-9" id="identifiant" name="identifiant">
				    		</div>
				  		  	<div class="form-group row">
					    		<label for="password" class="col-3">Mot de passe</label>
					    		<input type="password" class="form-control col-9" id="password" name="motDePasse">
							</div>
										
							<div class = "row mt-5">
								<div class = "col-6 " >	
									<button class="btn btn-primary btn-block" type="submit">Connexion</button>
								</div>
								 
								<div class = "col-6" >
									<div class="form-check col">
								  		<input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
								  		<label class="form-check-label" for="defaultCheck1">Se souvenir de moi</label>
									 </div>
									
									<div class="form-check ">
										<a href="">Mot de passe oublié</a>
									</div>
								</div>
							</div>
						
							<div>
								<a href="./MonProfile">
									<input class="btn btn-primary btn-block mt-5" type="button" value="Créer un compte"/>
								</a>
							</div>
						</form>
					</div>	
				</div>
			</div>
		</div>
				

	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>
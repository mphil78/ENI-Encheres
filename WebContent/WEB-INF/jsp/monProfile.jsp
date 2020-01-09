<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.eniencheres.bo.ArticleVendu"%>
<%@page import="fr.eni.eniencheres.bo.Categorie"%>

<!doctype html>
<html lang="fr">
<head>
<!-- Required meta tags -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.13/css/all.css">
<title>Création Compte</title>
</head>
<body>


	<div class="container">
		<!-- bg-primary"> -->
		<nav class="navbar navbar-expand-sm navbar-light bg-light mb-3">
			<div class="container">
				<a href="./"> <img
					src="${pageContext.request.contextPath}/images/imageLogo.png"
					width="100px" alt="image">
				</a>
				<c:choose>
					<c:when test="${!empty sessionScope.pseudo}">
						<ul class="nav justify-content-center">
							<li class="nav-item"><a class="nav-link"
								href="./TraitementArticle">Vendre un article</a></li>
							<li class="nav-item"><a class="nav-link"
								href="./TraitementProfile?pseudoAAfficher=${sessionScope.pseudo}">Bonjour
									${sessionScope.pseudo}</a></li>
							<li class="nav-item"><a class="nav-link"
								href="./TraitementConnexion">Déconnexion</a></li>
						</ul>
					</c:when>
					<c:when test="${empty sessionScope.pseudo}">
						<ul class="nav justify-content-center">
							<li class="nav-item"><a class="nav-link" href="./Connexion">S'inscrire
									- Se connecter </a></li>
						</ul>
					</c:when>
				</c:choose>
			</div>
		</nav>

		<div class="jumbotron">
			<h1 class="text-center mb-5 ">Mon Profil</h1>

			<c:set var="utilisateur" value="${requestScope.utilisateurAAfficher}"
				scope="page"></c:set>

			<!-- NON CONNECTE -->
			<c:choose>

				<c:when test="${empty sessionScope.pseudo}">
					<form action="./TraitementProfile?creation=true" method="post">

						<div class="row">
							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="pseudo">Pseudo : </label>
									<div class="col-8">
										<input class="form-control" type="text" name="pseudo"
											id="pseudo" required>
									</div>
								</div>
							</div>

							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="nom">Nom : </label>
									<div class="col-8">
										<input class="form-control" type="text" name="nom" id="nom" 

											required><br>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="prenom">Prénom : </label>
									<div class="col-8">
										<input class="form-control" type="text" name="prenom"
											id="prenom"  required>

									</div>
								</div>
							</div>

							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="email">Email : </label>
									<div class="col-8">
										<input class="form-control" type="email" name="email"
											id="email" required><br>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="telephone">Téléphone : </label>
									<div class="col-8">
										<input class="form-control" type="tel" name="telephone"
											id="telephone"  required>
									</div>
								</div>
							</div>

							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="rue">Rue : </label>
									<div class="col-8">

										<input class="form-control" type="text" name="rue" id="rue" 

											required><br>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="codePostal">Code Postal : </label>
									<div class="col-8">
										<input class="form-control" type="number" name="codePostal"

											id="codePostal" min="01000" max="98890"  required>

									</div>
								</div>
							</div>


							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="ville">Ville : </label>
									<div class="col-8">
										<input class="form-control" type="text" name="ville"
											id="ville" required><br>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="password">Mot de Passe : </label>
									<div class="col-8">
										<input class="form-control" type="password" name="password"
											id="password" required>
									</div>
								</div>
							</div>

							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="password2">Confirmation : </label>
									<div class="col-8">
										<input class="form-control"
											oninput='password2.setCustomValidity(password2.value != password.value ? "La confirmation du mot de passe est érronée." : "")'
											type="password" name="password2" id="password2" required><br>
									</div>
								</div>
							</div>
						</div>

						<div class="row mt-5">
							<div class="col-4">
								<button class="btn btn-primary btn-block" type="submit"
									name="creer" id="creer">Créer</button>
							</div>
							<div class="col-4">
								<a href="./TraitementAccueil">
									<button class="btn btn-primary btn-block" type="reset"
										name="annuler" id="annuler">Effacer le formulaire</button>
								</a>
							</div>
							<div class="col-4">
								<a href="./TraitementAccueil"><button
										class="btn btn-primary btn-block" type="button"
										name="retourAccueil" id="retourAccueil">Retour à
										l'accueil</button></a>
							</div>
						</div>

					</form>

				</c:when>



				<c:when test="${!empty sessionScope.pseudo}">
					<form action="./TraitementProfile?update=true" method="post">

						<div class="row">
							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="pseudo">Pseudo : </label>
									<div class="col-8">
										<input class="form-control" type="text" name="pseudo"
											id="pseudo" value=" ${utilisateur.pseudo}" readonly>
									</div>
								</div>
							</div>

							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="nom">Nom : </label>
									<div class="col-8">
										<input class="form-control" type="text" name="nom" id="nom"
											value="${utilisateur.nom}" required><br>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="prenom">Prénom : </label>
									<div class="col-8">
										<input class="form-control" type="text" name="prenom"
											id="prenom" value="${utilisateur.prenom}" required>
									</div>
								</div>
							</div>

							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="email">Email : </label>
									<div class="col-8">
										<input class="form-control" type="email" name="email"
											id="email" value="${utilisateur.email}"><br>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="telephone">Téléphone : </label>
									<div class="col-8">
										<input class="form-control" type="tel" name="telephone"
											id="telephone" value="${utilisateur.telephone}" required>
									</div>
								</div>
							</div>

							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="rue">Rue : </label>
									<div class="col-8">
										<input class="form-control" type="text" name="rue" id="rue"
											value="${utilisateur.rue}" required><br>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="codePostal">Code Postal : </label>
									<div class="col-8">
										<input class="form-control" type="text" name="codePostal"
											id="codePostal" value="${utilisateur.codePostal}" required>
									</div>
								</div>
							</div>

							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="ville">Ville : </label>
									<div class="col-8">
										<input class="form-control" type="text" name="ville"
											id="ville" value="${utilisateur.ville}" required><br>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="password3">Mot de Passe
										actuel: </label>
									<div class="col-8">
										<input class="form-control" type="password" name="password3"
											id="password3" required><br>
									</div>
								</div>
							</div>
						</div>


						<div class="row">
							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="password4">Nouveau mot de
										Passe: </label>
									<div class="col-8">
										<input class="form-control" type="password" name="password4"
											id="password4"> <!-- required> -->

									</div>
								</div>
							</div>

							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="password5">Confirmation : </label>
									<div class="col-8">
										<input class="form-control" type="password" name="password5"
											id="password5"> <!-- required> --><br>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6">
								<div class="row">
									<label class="col-4" for="credit">Crédit : </label>
									<div class="col-8">
										<input class="form-control" type="number" name="credit"
											value="${utilisateur.credit}"> <!-- disabled> -->
									</div>
								</div>
							</div>
						</div>
						
						<div class="row mt-5">
							<div class="col-4">
								<button class="btn btn-primary btn-block" type="submit"
									name="enregistrer" id="enregistrer">Enregistrer</button>
							</div>
							<div class="col-4">
								<a
									href="./TraitementProfile?pseudoASupprimer=${sessionScope.pseudo}&supprimer=true">
									<button class="btn btn-primary btn-block" type="button"
										name="supprimer" id="supprimer">Supprimer mon compte</button>
								</a>
							</div>
							<div class="col-4">
								<a href="./TraitementAccueil"><button
										class="btn btn-primary btn-block" type="button"
										name="retourAccueil" id="retourAccueil">Retour à
										l'accueil</button></a>
							</div>						
						</div>
						<div class="text-danger text-center"><br>${requestScope.erreurSuppression}</div>
					</form>
				</c:when>
			</c:choose>
		</div>
	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js">
		
	</script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>

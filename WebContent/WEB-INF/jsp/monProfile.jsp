<%@page import="fr.eni.eniencheres.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Création Compte</title>
</head>
<body>
<h1>ENI-Enchères</h1><br>
	<div>
		<h2>Mon Profil</h2>
		<% Utilisateur utilisateur = (Utilisateur)request.getAttribute("utilisateurAAfficher"); %>
		
			
		<form <%if(session.getAttribute("pseudo") == null){ %>
					action="./TraitementProfile?creation=true"
					<%}else{%>
					 action="./TraitementProfile?update=true"
					 <%}%>
					 method="post">
			
			<%
			if (session.getAttribute("pseudo") != null) {
			%>
<!-- 			oninput='password3.setCustomValidity(password3.value != password1.value ? "La confirmation du mot de passe actuel est érronée." : "")'
			oninput='password5.setCustomValidity(password5.value != password4.value ? "La confirmation du nouveau mot de passe est érronée." : "")'> -->
			
			<%
			} 
			%>
					
			
			<%
			if (session.getAttribute("pseudo") != null) {
			%>  
		  	<!-- connecté -->
				<p>
					<label for="pseudo">Pseudo : <%= utilisateur.getPseudo()%> </label>
					
				</p> 
			<%
			}else{
			%>
			
			<!-- Non connecté -->
				<p>
					<label for="pseudo">Pseudo : </label>
					<input type="text" name="pseudo" id="pseudo" required>
				</p>
			<%
			} 
			%>		
			
			
			<!-- Connecté -->
				<%
				if (session.getAttribute("pseudo") != null) {
				%>  
					<p>
						<label for="nom">Nom : </label>
						<input type="text" name="nom" id="nom" value="<%= utilisateur.getNom()%>" required><br>
					</p>
					<p>
						<label for="prenom">Prénom : </label>
						<input type="text" name="prenom" id="prenom" value="<%= utilisateur.getPrenom()%>" required>
					</p>
					<p>
						<label for="email">Email : </label>
						<input type="email" name="email" id="email" value="<%= utilisateur.getEmail()%>"><br>
					</p>
					<p>
						<label for="telephone">Téléphone : </label>
						<input type="tel" name="telephone" id="telephone" value="<%= utilisateur.getTelephone()%>" required>
					</p>
			<p>
			<label for="rue">Rue : </label>
			<input type="text" name="rue" id="rue" value="<%= utilisateur.getRue()%>" required><br>
			</p>
			<p>
			<label for="codePostal">Code Postal : </label>
			<input type="text" name="codePostal" id="codePostal" value="<%= utilisateur.getCodePostal()%>" required>
			</p>
			<p>
			<label for="ville">Ville : </label>
			<input type="text" name="ville" id="ville" value="<%=utilisateur.getVille()%>" required><br>
			</p>
			<%
			}else{
			%>
			<!-- Non connecté -->
			 <p>
				<label for="nom">Nom : </label>
				<input type="text" name="nom" id="nom"  required><br>
					</p>
					<p>
						<label for="prenom">Prénom : </label>
						<input type="text" name="prenom" id="prenom"  required>
					</p>
					<p>
						<label for="email">Email : </label>
						<input type="email" name="email" id="email" required><br>
					</p>
					<p>
						<label for="telephone">Téléphone : </label>
						<input type="text" name="telephone" id="telephone"  required>
					</p>
			<p>
			<label for="rue">Rue : </label>
			<input type="text" name="rue" id="rue"  required><br>
			</p>
			<p>
			<label for="codePostal">Code Postal : </label>
			<input type="text" name="codePostal" id="codePostal"  required>
			</p>
			<p>
			<label for="ville">Ville : </label>
			<input type="text" name="ville" id="ville"  required><br>
			</p>
			<%
			} 
			%>
			
			<%
			if (session.getAttribute("pseudo") != null) {
			%> 
			<!-- Connecté -->
				<p>
					<label for="password3">Mot de Passe actuel: </label>
					<input type="password" name="password3" id="password3" required>
				</p>
				
				<p>
					<label for="password4">Nouveau mot de Passe: </label>
					<input type="password" name="password4" id="password4" required>
				</p>
				
				<p>
					<label for="password5">Confirmation : </label>
					<input type="password" name="password5" id="password5" required><br>
				</p>    
				<p>
					<label for="credit">Crédit : </label>
					<input type="text" name="credit" value="<%= utilisateur.getCredit()%>" disabled>
					
				</p>
			<%
			}else{
			%>
			<!-- Non connecté -->
				<p>
					<label for="password">Mot de Passe : </label>
					<input type="password" name="password" id="password" required>
				</p>
				
				<p>
					<label for="password2">Confirmation : </label>
					<input oninput='password2.setCustomValidity(password2.value != password.value ? "La confirmation du mot de passe est érronée." : "")' type="password" name="password2" id="password2" required><br>
				</p>
			<%
			} 
			%>
			
			
			<%
			if (session.getAttribute("pseudo") != null) {
			%>
			<!-- Connecté -->
				
					<button type="submit" name="enregistrer" id="enregistrer">Enregistrer</button>
				</form>
					<a href="./TraitementProfile?pseudoASupprimer=<%=session.getAttribute("pseudo")%>&supprimer=true" ><button type="button" name="supprimer" id="supprimer">Supprimer mon compte</button></a>
				
			<%
			}else{
			%>
			<!-- Non connecté -->
				<p>
					<button type="submit" name="creer" id="creer">Créer</button>
				</form>
					<a href="./TraitementAccueil" ><button type="reset" name="annuler" id="annuler">Annuler</button></a>
				</p>
			<%
			} 
			%>
		
	</div>
</body>
</html>
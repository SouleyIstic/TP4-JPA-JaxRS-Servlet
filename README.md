# TP4

# Rappel:

-Comprendre les mécanismes des Servlet
-Réalisation d'une application Web en Combinant JPA du tp2 et les Servlet
-Comprendre les principes d’une architecture Rest
-Comprendre les bénéfices d’un framework comme Jersey

# Sujet:
ce Tp a pour objectif de continuer le développement d’une application type réseau social 
permettant de comparer sa consommation électrique avec ses amis, ses voisins,etc. dans la lignée de opower.

# Etape1: Chargement des dépendances

Tout d’abord, nous avons modifié notre fichier pom.xml pour ajouter les dépendances nécessaires

<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>javax.servlet-api</artifactId>
	<version>3.0.1</version>
	<scope>provided</scope>
</dependency>
Insertion et visualisation dés données en utilisant les Servlets

Nous avons crée 2 formulaires:

 # myfrom.html

<div>
	<FORM Method="POST" Action="/UserInfo">
		Name : <INPUT type=text size=20 name=name><BR> 
		Firstname: <INPUT type=text size=20 name=firstname><BR> 
		mail : <INPUT type=text size=2 name=mail><BR> 
		<INPUT type=submit value=Send>
	</FORM>
</div>
<div>
	<FORM Method="GET" Action="/UserInfo">
		<INPUT type=submit value=visualiser>
	</FORM>
</div>

On peut soit visualiser toutes les informations concernant les personnes 
enregistrées via la method (GET) ou bien ajouter des personnes à la base de données via la method (POST)

 home.html
=======
<div>
  <FORM Method="POST" Action="/HomeInfo" Name="Form1">
		piece: <INPUT type=text size=20 name=piece><BR> 
		taille: <INPUT type=float size=20 name=taille><BR>
		personne : <INPUT type=text size=2 name=person><BR> 
		<INPUT type=submit value=Send>
	</FORM>
</div>
<div>
 <form Method="GET" Action="/HomeInfo">
	<INPUT type=submit value=afficher>
 </form>
</div>	

-On peut visualiser les informations concernant les maisons 
enregistrées via la method (GET) ou bien ajouter des maisons à la base de données via la method (POST).

Création des Servlets
=======

Précédemment, nous avons mis «/UserInfo » et « /HomeInfo » comme action des formulaires. 
Ces deux url référencie l’url de notre servlet soit en GET ou bien en POST, 
les servlets jouent un rôle de contrôleurs dans notre application

nous avons crée une servlet(HomeInfo.java , UserInfo.java) pour chaque formulaire .

Dans la methode doGet,

on crée une variable de type collection pour récupérer et afficher les données qui sont dans notre base

Collection<Home> result = manager.createQuery("Select h From Home h", Home.class).getResultList();
    out.println("<HTML>\n<BODY>\n" + "<H1>Recapitulatif des informations sur les maisons</H1>\n" + "<UL>\n");
		for (Home h : result) {
		out.println("<LI> maison : " + h+ "\n");	
		}
		out.println("</UL>\n" + "</BODY></HTML>");

-Dans la methode doPost,
On crée une maison à partir de données envoyées dans le formulaire home.html

-Pareil pour la servlet UserInfo.

ConnexionManager.java

cette classe fut créée pour eviter de créer la connexion à la base de données plusieurs fois pour chaque action de l'utilisateur.

private ConnexionManager() {
		
		factory = Persistence.createEntityManagerFactory("dev");
		manager = factory.createEntityManager();
		m=this;
     	
	}
	
	public static ConnexionManager getInstance() {
		if (m == null) { // Premier appel
	         m = new ConnexionManager();
			}
		
		return m;
	}

Etape 4: Les architectures Rest

à partir de là, nous pourrions construire une architecture Rest manuellement. Cependant, gérez toutes les routes et les format de serialisation de données à la main est une tâche fastidieuse. 
Pour cela des frameworks comme Jersey et les APIs JaxRS permettent de simplifier ce travail. 

Pour construire une application avec Jersey, il faut tout d’abord modifiez le fichier pom.xml pour 
ajoutez la dépendance à Jersey et celle de jersey-json comme ceci: 
	 	 	
<dependency>
	<groupId>com.sun.jersey</groupId>
	<artifactId>jersey-servlet</artifactId>
	<version>1.18.3</version>
</dependency>
	 	 	
<dependency>
	<groupId>com.sun.jersey</groupId>
	<artifactId>jersey-json</artifactId>
	<version>1.18.3</version>
</dependency>

Pour la partie Rest nous avons créé deux fichiers, HomeService.java et PersonService.java

HomeService.java nous permet de récupérer une maison spécifique,toutes les maisons, modifier, supprimer et ajouter une maisons dans la base de données en format json.

UserInfo.java nous permet de récupérer une personne spécifique,toutes les personnes,ajouter un utilisateur dans la base de données en format en json, pour la suppression d'une personne ça ne fonctionne pas à cause des clés étrangères d’autres classes.


Ajoutez ensuite le descripteur d’applications web c’est à dire le fichier web.xml dans le répertoire 
src/main/webapp/WEB-INF/.
 Ce fichier a pour rôle de configurez les servlets. (Ce que l’on a fait par annotation précédemment). 

<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://java.sun.com/xml/ns/javaee"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	id="WebApp_ID" version="3.0">
	<display-name>MyWebProject</display-name>
	<servlet>
		<servlet-name>Jersey Web Application</servlet-name>
		<servlet-class>com.sun.jersey.spi.container.servlet.ServletContainer</servlet-class>
		<init-param>
			<param-name>com.sun.jersey.config.property.packages</param-name>
			<param-value>
				fr.istic.sir.rest
			 </param-value>
		</init-param>
				<init-param>
			<param-name>com.sun.jersey.api.json.POJOMappingFeature</param-name>
			<param-value>true</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>Jersey Web Application</servlet-name>
		<url-pattern>/rest/*</url-pattern>
	</servlet-mapping>
</web-app>



 

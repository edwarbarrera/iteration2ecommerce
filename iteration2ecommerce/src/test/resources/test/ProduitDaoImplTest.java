package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.ConnectBd;
import dao.ProduitDao;
import dao.ProduitDaoImpl;
import entity.Produit;




class ProduitDaoImplTest {
	
	//avant chaque test on reset la Bdd
	@BeforeEach 
	void testreset() throws SQLException {
		ConnectBd.reset();
	}

	// Apres chaque test on rest la Bdd
	@AfterEach
	void resetAfterTest() throws SQLException {
		ConnectBd.reset();
	}
	
	// Test d'un insert simple
	@Test
	void testInsert() throws SQLException { 
		Produit produit = new Produit(22, "test", 50, "blabla", "url", 1, 10);
		ProduitDao dao = new ProduitDaoImpl();
		dao.insert(produit);
		assertEquals(22, produit.getId()); 
		assertEquals(produit.toString(), dao.findById(22).toString());  
	}
	
	 //Test du Trigger interdisant la quantit� negative
	@Test
	void testInsertNeg() {
		try {
		Produit produit = new Produit(23, "test", -5, "blabla", "url", 1, 10);
		ProduitDao dao = new ProduitDaoImpl();
		dao.insert(produit);
		
		} catch (SQLException exc) {
		assertEquals(ConnectBd.QUANTITE_NEGATIVE, exc.getErrorCode());
		}
	}
	
	// Test de la m�thode permettant de trouver un produit par mot cl� 
	@Test
	void testFindByMotCle() {
		HashMap<Integer, Produit> produitsExpected = new HashMap<Integer, Produit>();
		Produit p1 = new Produit(5, "L'�le myst�rieuse", 200, "Une �le d�serte, en plein oc�an Pacifique. Cinq naufrag�s am�ricains organisent leur survie, accompagn�s de leur chien Top. Bient�t, ph�nom�nes inexplicables et co�ncidences troublantes se multiplient, comme si quelqu'un ou quelque chose tentait de les aider. � distance. Quel est donc le secret de l'�le ?",
	            "https://raw.githubusercontent.com/edwarbarrera/C-Users-aline-eclipse-workspace-projet_cda_1/master/WebContent/ressources/img/L'Ile%20myst%C3%A9rieuse.jpg", 1, 7.89);
		Produit p2 = new Produit(21, "Le chien des Baskerville", 50, "L'h�ritier des Baskerville est retrouv� mort. Doit-on accuser ce chien d�moniaque qui r�de sur la lande, sur les terres de la famille Baskerville ? Quiconque l'aper�oit est perdu. Pour Sherlock Holmes, cette histoire n'est qu'une obscure l�gende. Et il le prouvera. Le voil� donc sur les traces de la b�te diabolique.",
	            "https://raw.githubusercontent.com/edwarbarrera/C-Users-aline-eclipse-workspace-projet_cda_1/master/WebContent/ressources/img/Le%20chien%20des%20Baskerville%20.jpg", 1, 5.9);     
		produitsExpected.put(1, p1);
		produitsExpected.put(2, p2);
		ProduitDao dao = new ProduitDaoImpl();
		HashMap<Integer, Produit> actual = dao.findByMotCle("chien");
		assertEquals(produitsExpected.toString(), actual.toString());
	}

	// test recherche par prix Max
	@Test
	void testFindByPrice() {
		HashMap<Integer, Produit> produitsExpected = new HashMap<Integer, Produit>();
		Produit p = new Produit(7, "M�mento CSS3", 36, "Aussi l�ger que riche, ind�chirable et imperm�able, voici la 4e �dition mise � jour du m�mento CSS 3 de Rapha�l Goetter, avec une lisibilit� am�lior�e !",
	            "https://raw.githubusercontent.com/edwarbarrera/C-Users-aline-eclipse-workspace-projet_cda_1/master/WebContent/ressources/img/memento%20CSS3.jpg", 2, 5);
		produitsExpected.put(1, p);
		ProduitDao dao = new ProduitDaoImpl();
		HashMap<Integer, Produit> actual = dao.findByPrice(5);
		assertEquals(produitsExpected.toString(), actual.toString());
	}

	//Test recherche par categorie
	@Test
	void testFindByCategorie() {
		HashMap<Integer, Produit> produitsExpected = new HashMap<Integer, Produit>();
		Produit p1 = new Produit(14, "One Piece - �dition originale - Tome 01: � l'aube d'une grande aventure", 50, "Nous sommes � l'�re des pirates. Luffy, un gar�on espi�gle, r�ve de devenir le roi des pirates en trouvant le �One Piece�, un fabuleux tr�sor. Seulement, Luffy a aval� un fruit du d�mon qui l'a transform� en homme �lastique.",
	            "https://raw.githubusercontent.com/edwarbarrera/C-Users-aline-eclipse-workspace-projet_cda_1/master/WebContent/ressources/img/One%20Piece%20-%20%C3%89dition%20originale%20-%20Tome%2001.jpg", 3, 6.9);
		Produit p2 = new Produit (15, "One piece - Edition originale Vol.2", 32, "Luffy atterrit sur une �le occup�e par Baggy et son groupe, un �quipage de pirates redoutable. Il y rencontre Nami, une � voleuse de pirates �. Apprenant la nature de Luffy, elle d�cide de l�utiliser pour infiltrer les rangs de Baggy et d�rober ses tr�sors�",
	            "https://raw.githubusercontent.com/edwarbarrera/C-Users-aline-eclipse-workspace-projet_cda_1/master/WebContent/ressources/img/One%20piece%20-%20Edition%20originale%20Vol.2%20.jpg", 3, 6.9);
		Produit p3 = new Produit(16, "One Piece - �dition originale - Tome 03: Une v�rit� qui blesse", 0, "Luffy affronte Baggy. Ce dernier admet vouer une haine sans nom envers Shanks. En effet, tous deux ont appris les rudiments de la piraterie sur le m�me navire, et un malencontreux incident a provoqu� la descente aux enfers de Baggy�",
	            "https://raw.githubusercontent.com/edwarbarrera/C-Users-aline-eclipse-workspace-projet_cda_1/master/WebContent/ressources/img/One%20Piece%20-%20%C3%89dition%20originale%20-%20Tome%2003%20Une%20v%C3%A9rit%C3%A9%20qui%20blesse.jpg", 3, 6.9);
		produitsExpected.put(1, p1);
		produitsExpected.put(2, p2);
		produitsExpected.put(3, p3);
		ProduitDao dao = new ProduitDaoImpl();
		HashMap<Integer, Produit> actual = dao.findByCategorie(3);
		assertEquals(produitsExpected.toString(), actual.toString());
	}
	
	// Test par nom et cat�gorie
	@Test
	void testFindByNameCategorie() {
		HashMap<Integer, Produit> produitsExpected = new HashMap<Integer, Produit>();
		Produit p1 = new Produit(14, "One Piece - �dition originale - Tome 01: � l'aube d'une grande aventure", 50, "Nous sommes � l'�re des pirates. Luffy, un gar�on espi�gle, r�ve de devenir le roi des pirates en trouvant le �One Piece�, un fabuleux tr�sor. Seulement, Luffy a aval� un fruit du d�mon qui l'a transform� en homme �lastique.",
	            "https://raw.githubusercontent.com/edwarbarrera/C-Users-aline-eclipse-workspace-projet_cda_1/master/WebContent/ressources/img/One%20Piece%20-%20%C3%89dition%20originale%20-%20Tome%2001.jpg", 3, 6.9);
		produitsExpected.put(1, p1);
		ProduitDao dao = new ProduitDaoImpl();
		HashMap<Integer, Produit> actual = dao.findByNameCategorie("aventure", 3);
		assertEquals(produitsExpected.toString(), actual.toString());
	}
		
  // Test recheche par nom et prix
	@Test
	void testFindByNamePrice() {
		HashMap<Integer, Produit> produitsExpected = new HashMap<Integer, Produit>();
		Produit p1 = new Produit (2, "Programmer en Java", 0, "De la programmation objet en Java au d�veloppement d'applications web. Dans cet ouvrage, Claude Delannoy applique au langage Java la d�marche p�dagogique qui a fait le succ�s de ses livres sur le C et le C++. Il insiste tout particuli�rement sur la bonne compr�hension des concepts objet et sur l'acquisition de m�thodes de programmation rigoureuses.",
	            "https://raw.githubusercontent.com/edwarbarrera/C-Users-aline-eclipse-workspace-projet_cda_1/master/WebContent/ressources/img/Programmer%20en%20Java%20.jpg", 2, 12.69);
		produitsExpected.put(1, p1);
		ProduitDao dao = new ProduitDaoImpl();
		HashMap<Integer, Produit> actual = dao.findByNamePrice("java", 15);
		assertEquals(produitsExpected.toString(), actual.toString());
	}

	// test recherche par categorix et prix 
	@Test
	void testFindByPriceCategorie() {
		HashMap<Integer, Produit> produitsExpected = new HashMap<Integer, Produit>();
		Produit p1 = new Produit  (8, "Le tour du monde en 80 jours", 85, "En 1872, un riche gentleman londonien, Phileas Fogg, parie vingt mille livres qu'il fera le tour du monde en quatre-vingts jours. Accompagn� de son valet de chambre, le d�vou� Passepartout, il quitte Londres pour une formidable course contre la montre. Au prix de mille aventures, notre h�ros va s'employer � gagner ce pari.",
	            "https://raw.githubusercontent.com/edwarbarrera/C-Users-aline-eclipse-workspace-projet_cda_1/master/WebContent/ressources/img/le%20tour%20du%20monde%20en%2080%20jours.jpg", 1, 5.5);
		Produit p2 = new Produit (21, "Le chien des Baskerville" , 50, "L'h�ritier des Baskerville est retrouv� mort. Doit-on accuser ce chien d�moniaque qui r�de sur la lande, sur les terres de la famille Baskerville ? Quiconque l'aper�oit est perdu. Pour Sherlock Holmes, cette histoire n'est qu'une obscure l�gende. Et il le prouvera. Le voil� donc sur les traces de la b�te diabolique.",
		                          "https://raw.githubusercontent.com/edwarbarrera/C-Users-aline-eclipse-workspace-projet_cda_1/master/WebContent/ressources/img/Le%20chien%20des%20Baskerville%20.jpg", 1, 5.9);
		produitsExpected.put(1, p1);
		produitsExpected.put(2, p2);
		ProduitDao dao = new ProduitDaoImpl();
		HashMap<Integer, Produit> actual = dao.findByPriceCategorie(1, 6);
		assertEquals(produitsExpected.toString(), actual.toString());
	}

	// Test recherche par nom prix et categorie
	@Test
	void testFindByAll() {
		HashMap<Integer, Produit> produitsExpected = new HashMap<Integer, Produit>();
		Produit p1 = new Produit (4, "Voyage au centre de la Terre", 56, "Le professeur Lidenbrock est persuad� d'avoir d�couvert le chemin qui m�ne au centre de la Terre. Accompagn� de son neveu Axel, l'imp�tueux g�ologue part en Islande. L�, au fond d'un volcan, les deux explorateurs et leur guide s'enfoncent dans les entrailles myst�rieuses du globe. Un voyage d'une folle audace, v�ritable d�fi lanc� � la science.",
	            "https://raw.githubusercontent.com/edwarbarrera/C-Users-aline-eclipse-workspace-projet_cda_1/master/WebContent/ressources/img/Voyage%20au%20centre%20de%20la%20Terre.jpg", 1, 9.99);
		produitsExpected.put(1, p1);
		ProduitDao dao = new ProduitDaoImpl();
		HashMap<Integer, Produit> actual = dao.findByAll("voyage", 10, 1);
		assertEquals(produitsExpected.toString(), actual.toString());
	}
	
}

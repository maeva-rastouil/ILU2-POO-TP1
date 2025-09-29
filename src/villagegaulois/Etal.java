package villagegaulois;

import personnages.Gaulois;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	public String libererEtal() {
		//ajout le test dans le cas ou l'étal est occuper, on doit lancer l'exception
		if (!etalOccupe) {
	        throw new IllegalStateException("Impossible de libérer un étal qui n'a jamais été occupé.");
	    }
		
		etalOccupe = false;
		StringBuilder chaine = new StringBuilder(
				"Le vendeur " + vendeur.getNom() + " quitte son étal, ");
		int produitVendu = quantiteDebutMarche - quantite;
		if (produitVendu > 0) {
			chaine.append(
					"il a vendu " + produitVendu + " parmi " + produit + ".\n");
		} else {
			chaine.append("il n'a malheureusement rien vendu.\n");
		}
		return chaine.toString();
	}

	public String afficherEtal() {
		if (etalOccupe) {
			return "L'étal de " + vendeur.getNom() + " est garni de " + quantite
					+ " " + produit + "\n";
		}
		return "L'étal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
	    try {
	    	if (quantiteAcheter < 1) {
	    	    throw new IllegalArgumentException("La quantité achetée doit être strictement positive.");
	    	}

	        //on vérifie l'état de l'acheteur
	        if (acheteur == null) {
	            throw new IllegalArgumentException("L'acheteur ne peut pas être null.");
	        }

	        // on vérifie si l'étal est occupé ou non
	        if (!etalOccupe) {
	            throw new IllegalStateException("Impossible d'acheter : l'étal n'est pas occupé.");
	        }

	        //vérification de la quantité demandée
	        if (quantiteAcheter <= 0) {
	            throw new IllegalArgumentException("La quantité achetée doit être strictement positive.");
	        }

	        StringBuilder chaine = new StringBuilder();
	        chaine.append(acheteur.getNom() + " veut acheter " + quantiteAcheter + " " + produit + " à " + vendeur.getNom());

	        if (quantite == 0) {
	            chaine.append(", malheureusement il n'y en a plus !");
	            quantiteAcheter = 0;
	        } else if (quantiteAcheter > quantite) {
	            chaine.append(", comme il n'y en a plus que " + quantite + ", " + acheteur.getNom() + " vide l'étal de " + vendeur.getNom() + ".\n");
	            quantiteAcheter = quantite;
	            quantite = 0;
	        } else {
	            quantite -= quantiteAcheter;
	            chaine.append(". " + acheteur.getNom() + ", est ravi de tout trouver sur l'étal de " + vendeur.getNom() + "\n");
	        }

	        return chaine.toString();

	    } catch (Exception e) {
	        //affichage de la pile d'erreurs sur la sortie d'erreur
	        System.err.println("Erreur lors de l'achat :");
	        e.printStackTrace();
	        return ""; //retourne une chaîne vide en cas d'erreur
	    }
	}


	public boolean contientProduit(String produit) {
		return produit.equals(this.produit);
	}

}

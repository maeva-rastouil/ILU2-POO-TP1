package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	private Etal[] etals;
	
	//exception personnalisée
	public class VillageSansChefException extends Exception {
	    public VillageSansChefException(String message) {
	        super(message);
	    }
	}
	

	public Village(String nom, int nbVillageoisMaximum, int nbEtalsMarche) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtalsMarche); //on créer le marché
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() throws VillageSansChefException {
	    if (chef == null) {
	        throw new VillageSansChefException("Le village ne peut pas fonctionner sans chef.");
	    }

	    StringBuilder chaine = new StringBuilder();
	    if (nbVillageois < 1) {
	        chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
	    } else {
	        chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
	        for (int i = 0; i < nbVillageois; i++) {
	            chaine.append("- " + villageois[i].getNom() + "\n");
	        }
	    }
	    return chaine.toString();
	}

	
	
	
	
	
	
	
	
	
	
	//méthode utiliserEtal
	public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
        etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
	}
	
	//méthode trouverEtalLibre
	public int trouverEtalLibre() {
		for(int i=0; i < etals.length; i++) {
			if (etals[i].isEtalOccupe()) {
				return i;
			}
		}
		return -1;
	}	
	
	//méthode trouverEtals
	public Etal[] trouverEtals(String produit) {
		int compteur = 0;
		
		for(int i=0; i < etals.length; i++) {
			if(etals[i].contientProduit(produit)) {
				compteur++;
			}
		}
		
		Etal[] tableau = new Etal[compteur];
		
		int indice = 0;
		for(int i=0; i < etals.length; i++) {
			if(etals[i].contientProduit(produit)) {
				tableau[indice] = etals[i];
				indice++;
			}
		}
		
		return tableau;
	}
	
	//méthode trouverVendeur
	public Etal[] trouverVendeur(Gaulois gaulois) { 
		for(int i=0; i < etals.length; i++){ 
			if((etals[i].getVendeur())==gaulois){ 
				return etals; 
			} 
		} 
	return null; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//méthode installerVendeur
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit);
		int indice = marche.trouverEtalLibre();
		if(indice != -1) {
			marche.utiliserEtal(indice, vendeur, produit, nbProduit);
			chaine.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'etal n°" + indice);
		}
		else {
			chaine.append("Il n'y a pas d'etal disponible pour " + vendeur.getNom());
		}
		
		return chaine.toString();
	}
	
	//méthode rechercherVendeursProduit
	public String rechercheVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] etalsProduit = marche.trouverEtals(produit);
		if (etalsProduit.length == 0) {
			chaine.append("Il n'y a pas de vendeurs qui propose des " + produit + " au marché\n");
		}
		else if(etalsProduit.length == 1) {
			chaine.append("Seul le vendeur " + etalsProduit[0].getVendeur().getNom() + "propose des " + produit + " au marché\n");
		}
		else {
			chaine.append("Les vendeurs qui proposent des fleurs sont :\n");
			for (Etal etal : etalsProduit) {
			    chaine.append(" - " + etal.getVendeur().getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	//méthode rechercherEtal
	public Etal rechercherEtal(Gaulois vendeur) {
		for(int i=0; i < etals.length; i++) {
			if (etals[i].isEtalOccupe() && etals[i].getVendeur().equals(vendeur)){
				return etals[i];
			}
		}
		return null;
	}
	
	//méthode partirVendeur
	public String partirVendeur(Gaulois vendeur) {
		for(int i=0; i < etals.length; i++) { 
	        if (etals.isEtalOccupe() && etals.getVendeur().equals(vendeur)) {
	            return etals.libererEtal();
	        }
	    }
	    return vendeur.getNom() + " n'est pas installé sur un étal.\n";
	}
	
	//méthode afficheMarche
	public String afficherMarche() {
	    StringBuilder chaine = new StringBuilder();
	    chaine.append("Le marché du village \"" + nom + "\" possède plusieurs étals :\n");

	    int nbEtalVide = 0;
	    for (Etal etal : marche.getEtals()) {
	        if (etal.isEtalOccupe()) {
	            chaine.append(etal.getVendeur().getNom() + " vend " + etal.afficherEtal().split("garni de ")[1]);
	        } else {
	            nbEtalVide++;
	        }
	    }

	    chaine.append("Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n");
	    return chaine.toString();
	}

	

	
	
	
	
	
	
	
	
	
	
	//classe interne Marche
	private class Marche {
		public Etal[] etals;
		
		//Constructor 
		public Marche(int nbetals) {
			etals = new Etal[nbetals];
		}
		
		//Méthode afficheMarche
		public String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nbEtalsVide = 0;
			for(int i=0; i < etals.length; i++) {
				if(etals[i].isEtalOccupe()) {
					chaine.append(etals);
				}
				else {
					nbEtalsVide++;
				}
			}
			chaine.append("Il reste" + nbEtalsVide + "étals non utilisés dans le marché.\n");
			return chaine.toString();
		}
			
	}
}


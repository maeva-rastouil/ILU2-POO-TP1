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

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
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

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	

	
	
	
	
	
	public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
        etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
	}
	
	public int trouverEtalLibre() {
		for(int i=0; i < etals.length; i++) {
			if (etals[i].isEtalOccupe()) {
				return i;
			}
		}
		return -1;
	}	
	
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
				indice++;
				tableau[indice] =  etals[i];
			}
		}
		
		return tableau;
	}
	
	public Etal[] trouverVendeur(Gaulois gaulois) {
		for(int i=0; i < etals.length; i++) {
			if((etals[i].getVendeur())==gaulois) {
				return etals;
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	private class Marche {
		//Constructor 
		public Marche(int nbetals) {
			etals = new Etal[nbetals];
		}
		
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


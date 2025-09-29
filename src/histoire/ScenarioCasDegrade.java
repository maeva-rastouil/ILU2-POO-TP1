package histoire;

import villagegaulois.Etal;
import personnages.Gaulois;

public class ScenarioCasDegrade {
    public static void main(String[] args) {
        Etal etal = new Etal(); // étal non occupé
        Gaulois acheteur = new Gaulois("Abraracourcix", 10);

        try {
            // c.ii) appel à acheterProduit sur un étal vide
            String resultat = etal.acheterProduit(5, acheteur);
            System.out.println(resultat);
        } catch (IllegalStateException e) {
            System.err.println("Exception capturée : " + e.getMessage());
            e.printStackTrace(); // Affiche la pile sur la sortie d'erreur
        }

        System.out.println("Fin du test");
    }
}

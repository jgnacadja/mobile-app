public class Main {

    public static void main(String[] args) {

        Dossier gestionnaire = new Dossier(0, "");

        gestionnaire.create(new Dossier(1, "Personnel"));
        gestionnaire.create(new Dossier(2, "Travail"));
        gestionnaire.create(new Dossier(3, "Etudes"));

        System.out.println("Liste des dossiers :");

        for (Dossier d : gestionnaire.findAll()) {
            System.out.println(d.getIdDossier() + " - " + d.getLabel());
        }

        Dossier dossier = gestionnaire.findById(2);

        if (dossier != null) {
            System.out.println("Dossier trouve : " + dossier.getLabel());
        }

        boolean modifie = gestionnaire.update(
            new Dossier(2, "Travail universitaire")
        );

        System.out.println("Modification reussie : " + modifie);

        boolean supprime = gestionnaire.delete(dossier);

        System.out.println("Suppression reussie : " + supprime);

        System.out.println("Liste finale :");

        for (Dossier d : gestionnaire.findAll()) {
            System.out.println(d.getIdDossier() + " - " + d.getLabel());
        }
    }
}
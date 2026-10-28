import java.util.ArrayList;
import java.util.List;

public class Dossier {

    private int idDossier;
    private String label;

    private static List<Dossier> dossiers = new ArrayList<>();

    public Dossier(int idDossier, String label) {
        this.idDossier = idDossier;
        this.label = label;
    }

    public int getIdDossier() {
        return idDossier;
    }

    public void setIdDossier(int idDossier) {
        this.idDossier = idDossier;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean create(Dossier dossier) {
        for (Dossier d : dossiers) {
            if (d.getIdDossier() == dossier.getIdDossier()) {
                return false;
            }
        }

        dossiers.add(dossier);
        return true;
    }

    public boolean delete(Dossier dossier) {
        return dossiers.remove(dossier);
    }

    public boolean update(Dossier dossier) {
        for (Dossier d : dossiers) {
            if (d.getIdDossier() == dossier.getIdDossier()) {
                d.setLabel(dossier.getLabel());
                return true;
            }
        }

        return false;
    }

    public Dossier findById(int id) {
        for (Dossier d : dossiers) {
            if (d.getIdDossier() == id) {
                return d;
            }
        }

        return null;
    }

    public List<Dossier> findAll() {
        return new ArrayList<>(dossiers);
    }
}
import java.time.LocalDate;
import java.util.ArrayList;

public class User {

    private static ArrayList<User> users = new ArrayList<>();
    private static int compteur = 1; 
    private int idUser;
    private String nom;
    private String prenoms;
    private LocalDate dateIns;
    private String email;
    private String mdp;

   
    public User(String nom, String prenoms, String email, String mdp) {
        this.idUser = compteur++;
        this.nom = nom;
        this.prenoms = prenoms;
        this.dateIns = LocalDate.now();
        this.email = email;
        this.mdp = mdp;
    }

    public int getIdUser() { return idUser; }
    public LocalDate getDateIns() { return dateIns; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenoms() { return prenoms; }
    public void setPrenoms(String prenoms) { this.prenoms = prenoms; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMdp() { return mdp; }
    public void setMdp(String mdp) { this.mdp = mdp; }

    public boolean verifierMotDePasse(String motDePasse) {
        return this.mdp.equals(motDePasse);
    }

    public static boolean changerMotDePasse(int id, String ancien, String nouveau) {
    User u = findById(id);
    if (u != null && u.verifierMotDePasse(ancien)) {
        u.setMdp(nouveau);
        return true;
    }
    return false;
}

    public static void create(User u) {
        users.add(u);
    }

    public static User findById(int id) {
        for (User u : users) {
            if (u.getIdUser() == id) {
                return u;
            }
        }
        return null;
    }

    public static ArrayList<User> findAll() {
        return users;
    }

    public static boolean update(int id, String nom, String prenoms, String email) {
        User u = findById(id);
        if (u != null) {
            u.setNom(nom);
            u.setPrenoms(prenoms);
            u.setEmail(email);
            return true;
        }
        return false;
    }

    public static boolean delete(int id) {
        User u = findById(id);
        if (u != null) {
            users.remove(u);
            return true;
        }
        return false;
    }

    @Override
   public String toString() {
    return "L'Utilisateur " prenoms + " " + nom + " d'id " + idUser + " est inscrit depuis le "
           + dateIns + " avec l'email " + email + ".";
}
}
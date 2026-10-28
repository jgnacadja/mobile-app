public class User {

   private int idUser;
   private string nom;
   private string prenoms;
   private string dateIns;
   private string email;
   private string mdp;

    public User(){}
    public User(int idUser, string nom, string prenoms,string dateIns, string email, string mdp){
    this.idUser= idUser;
    this.nom= nom;
    this.prenoms= prenoms;
    this.dateIns= dateIns;
    this.email= email;
    this.mdp=mdp;
  }

  public int getidUser(){return idUser;} 
  public void setidUser(int idUser){this.idUser= idUser;
}
   public string getNom(){return nom;} 
   public void setNom(string nom){this.nom=nom;}

   public string getPrenoms(){return prenoms;} 
   public void setPrenoms(string prenoms){this.prenoms= prenoms;}

   public string getEmail(){return email;} 
   public void setEmail(string email){this.email= email;}

   public string getDateIns(){return dateIns;} 
   public void setDateIns(string dateIns){this.dateIns= dateIns;}

   public string getMdp(){return mdp;} 
   public void setMdp(string mdp){this.mdp= mdp;}
}

 
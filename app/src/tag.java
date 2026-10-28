import java.util.ArrayList;
import java.util.Date;
import java.util.List;

Class Tag{
    private int idTag;
    private String label;
    private Date dateCreation ;
    private Date dateModif;

    private static List<Tag> tags = new ArrayList<>();
    private static int compteurId = 1;

    //Construsteur
    public Tag(int idTag, String label, Date dateCreation, Date dateModif) {
        this.idTag = idTag;
        this.label = label;
        this.dateCreation = dateCreation;
        this.dateModif = dateModif;
    }
    //Methodes
    public static void createTag(String label, Date dateCreation, Date dateModif) {
        int idTag = compteurId++;
        Tag tag = new Tag(idTag, label, dateCreation, dateModif);
        tags.add(tag);
    }

    public static void updateTag(int idTag, String label, Date dateCreation, Date dateModif) {
        Tag tag = findById(idTag);
        if (tag != null) {
            tags.remove(tag);
            tags.add(new Tag(idTag, label, dateCreation, dateModif));
        }
    }
    public static void deleteTag(int idTag) {
        Tag tag = findById(idTag);
        if (tag != null) {
            tags.remove(tag);
        }
    }
    public static Tag findById(int idTag) {
        for (Tag tag : tags) {
            if (tag.getIdTag() == idTag) {
                return tag;
            }
        }
        return null;
    }
    public static List<Tag> findAll() {
        return new ArrayList<>(tags);
    }

    //Setters et  Getters
    public int getIdTag() {
        return idTag;
    }

    public void setIdTag(int idTag) {
        this.idTag = idTag;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }
}

public class Pet {
    private String id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Object[] tags;
    private Status status;

    public Pet(String id, String name, Status status) {
        this.id = id;
        this.category = new Category("0", "dogs");
        this.name = name;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public Object getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String[] getPhotoUrls() {
        return photoUrls;
    }

    public Object[] getTags() {
        return tags;
    }

    public Status getStatus() {
        return status;
    }
}

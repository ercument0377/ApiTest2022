package class06_pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
//Pojo'ya diyorum ki json formatından pojo formatına dataları dönüştürürken eger bazı değişkenleri tanımıyorsa(unknow) yoksa pojoda onları görmemezlikten gel diyorum. yukarıdaki yazdığım la
public class JsonPlaceHolderPojo {
    //POJO=> plain old java object   demek
    /*
    POJO içinde private degiskenler, , butun parametrelere sahip constructor ve
     parametreleri olmayan constructor getter ve setter ile toString() kullanılmalı

     */
    //private degisken olustur
    private Integer userId;
    private String title;
    private Boolean completed;


    // tum parametrelere sahip constructor olusturuyoruz
    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    // bos / parametresiz constructor
    public JsonPlaceHolderPojo() {

    }

    // generate getter ve setter
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }


    //toString olustur

    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}


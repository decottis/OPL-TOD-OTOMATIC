package Util;

/**
 * Created by David on 07/02/2017.
 */
public class Todo {
    private String content;
    private String language;
    private String annot;

    public String getAnnot() {
        return annot;
    }

    public void setAnnot(String annot) {
        this.annot = annot;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Todo(String content, String language,String annot) {
        this.content = content;
        this.language = language;
        this.annot = annot;
    }



}

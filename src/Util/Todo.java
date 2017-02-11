package Util;

/**
 * Created by David on 07/02/2017.
 */
public class Todo {
    public static int NUMBER_OF_COLLUMN  = 4;
    private String content;
    private String file;
    private String[] annot;
    private String line;

    public String[] getAnnot() {
        return annot;
    }

    public void setAnnot(String[] annot) {
        this.annot = annot;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.file = line;
    }

    public Todo(String content, String file,String[] annot, String line) {
        this.content = content;
        this.file = file;
        this.annot = annot;
        this.line = line;
    }

    public String annotToString(){
        String s_annot = "";
        for(int i = 0; i<annot.length; i++){
            if(i < annot.length - 1)
                s_annot += annot[i] + ".";
            else
                s_annot += annot[i];
        }
        return s_annot;
    }



}

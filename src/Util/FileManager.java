package Util;

import myToolWindow.MyToolWindowFactory;

import java.io.*;

/**
 * Created by David on 08/02/2017.
 */
public class FileManager {
    public void getAllTag() {
        for (int i = 0; i < MyToolWindowFactory.listOfFiles.size(); i++) {
            getTag(MyToolWindowFactory.listOfFiles.get(i));
        }
    }

    public void getAllFiles(File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                getAllFiles(fileEntry);
            } else {
                MyToolWindowFactory.listOfFiles.add(fileEntry);
            }
        }
    }

    public void getTag(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                if (line.toLowerCase().contains("//@tag")) {
                    int index = line.toLowerCase().indexOf("//@tag");
                    String todoReal = line.substring(index, line.length());
                    String extension = file.getName().split("\\.")[file.getName().split("\\.").length - 1];
                    int indexTodoReal = todoReal.indexOf(" ") == -1 ? todoReal.length() : todoReal.indexOf(" ");
                    String[] annotation = todoReal.substring(7, indexTodoReal).split("\\.");
                    MyToolWindowFactory.getMyTree().getListOfTodo().add(new Todo(todoReal, extension, annotation));
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

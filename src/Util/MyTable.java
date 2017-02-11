package Util;

import com.intellij.ui.table.JBTable;
import myToolWindow.MyToolWindowFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sim on 09/02/2017.
 */
public class MyTable {
    public JTable tab;
    public Object[][] data;
    public int cpt = 0;
    public Object[][] data1 = {{"salut", "toi"}, {"Hello", "you"}};


    public MyTable(){

    }

    public JTable refreshTable(Object[][] data, String[] columnName){
        cpt++;
        if(cpt == 3) {
            data = data1;
        }
        DefaultTableModel model = new DefaultTableModel(data, columnName);
        tab = new JTable( model )
        {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };

        return tab;
    }


    public static List<Todo> getTodoByTag(MyTree tree, List<Todo> l_todo){
        List<Todo> todoByTag = new ArrayList<Todo>();
        TreePath path = tree.getTreePath();
        int pathCount = path.getPathCount();
        for (Todo t: l_todo) {
            if(equalsPath(path, t.getAnnot(), pathCount))
                todoByTag.add(t);
        }
        return todoByTag;
    }

    public static Boolean equalsPath(TreePath path, String[] annot, int pathCount) {
        if(pathCount == 1)
            return true;

        if(pathCount > annot.length)
            return false;

        for(int i=0; i<pathCount; i++) {
            if(!path.getPathComponent(pathCount - 1 - i).toString().equals(annot[i]))
                return false;
        }
        return true;
    }

    public void convertListToObject(List<Todo> l_todo){
        int cpt = 0;
        Object[][] data = new Object[l_todo.size()][Todo.NUMBER_OF_COLLUMN];
        for(Todo t : l_todo){
            data[cpt][0] = t.getContent();
            data[cpt][1] = t.getLanguage();
            data[cpt][2] = t.getAnnot();
            cpt++;
        }
        this.data = data;
    }
}

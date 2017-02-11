package Util;

import com.intellij.ui.treeStructure.Tree;
import myToolWindow.MyToolWindowFactory;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sim on 09/02/2017.
 */
public class MyTree {

    private JTree myTree;
    private List<Todo> listOfTodo;
    public TreePath currentPath;
    public MyTable table;
    private MyToolWindowFactory factory;

    public MyTree(MyTable table, MyToolWindowFactory fac){
        listOfTodo = new ArrayList<Todo>();
        this.table = table;
        this.factory = fac;
    }

    public List<String[]> formatTreeOfTodo() {
        List<String[]> listTodo = new ArrayList<String[]>();
        for (int i = 0; i < listOfTodo.size(); i++) {
            listTodo.add(listOfTodo.get(i).getAnnot());
        }
        return listTodo;
    }

    public boolean createTree(DefaultMutableTreeNode node, String[] branch, int cpt, boolean last) {
        String[] tmp = Arrays.copyOfRange(branch, 0, cpt + 1);
        if (cpt == branch.length) last = true;
        boolean exist = false;
        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode tmpNode = (DefaultMutableTreeNode) node.getChildAt(i);
            if (tmpNode.getUserObject().equals(tmp[cpt]) && cpt != branch.length) {
                cpt++;
                last = createTree((DefaultMutableTreeNode) node.getChildAt(i), branch, cpt, last);
            }
        }
        if (!last) {
            DefaultMutableTreeNode newLeaf = new DefaultMutableTreeNode(tmp[cpt]);
            node.add(newLeaf);
            if (cpt != branch.length - 1) {
                cpt++;
                last = createTree((DefaultMutableTreeNode) node.getLastChild(), branch, cpt, last);
            }
        }
        return true;

    }

    public DefaultTreeModel refreshTree(JTree tree){
        List<String[]> todoFormated = formatTreeOfTodo();
        List<DefaultMutableTreeNode> createdNode = new ArrayList<DefaultMutableTreeNode>();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("tag");
        for (int i = 0; i < todoFormated.size(); i++) {
            createTree(root, todoFormated.get(i), 0, false);
        }
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        tree = new Tree();
        tree.setModel(treeModel);
        tree.setRootVisible(true);

        this.myTree = tree;
        this.myTree.addTreeSelectionListener(createSelectionListener());

        return treeModel;
    }

    public List<Todo> getListOfTodo(){
        return listOfTodo;
    }

    private TreeSelectionListener createSelectionListener() {
        final MyTree myTree = this;
        return new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                currentPath = e.getPath();
                System.out.println(currentPath);
                List<Todo> l_todo = MyTable.getTodoByTag(myTree, listOfTodo);
                table.convertListToObject(l_todo);
                factory.createUIComponents();
            }
        };
    }

    public TreePath getTreePath(){
        return this.currentPath;
    }
}

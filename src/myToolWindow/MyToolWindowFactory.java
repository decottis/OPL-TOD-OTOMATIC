package myToolWindow;

import Util.FileManager;
import Util.Todo;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.*;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.*;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Chursin
 * Date: Aug 25, 2010
 * Time: 2:09:00 PM
 */
public class MyToolWindowFactory implements ToolWindowFactory, TreeSelectionListener {

    private JButton refreshToolWindowButton;
    private JButton hideToolWindowButton;
    private JLabel currentDate;
    private JLabel currentTime;
    private JLabel timeZone;
    private JPanel myToolWindowContent;
    private JTree tree1;
    private JList list1;
    private JProgressBar progressBar1;
    private ToolWindow myToolWindow;
    public static List<File> listOfFiles;
    public static List<Todo> listOfTodo;
    private Tree filesInConnection;
    private FileManager fm;


    public MyToolWindowFactory() {
        listOfFiles = new ArrayList<File>();
        listOfTodo = new ArrayList<Todo>();
        fm = new FileManager();
        $$$setupUI$$$();
    }

    // Create the tool window content.
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        fm.getAllFiles(new File(project.getBasePath()));
        fm.getAllTag();
        $$$setupUI$$$();

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();

        //create content panel
        Content content = contentFactory.createContent(myToolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content, 0);


    }

    public void valueChanged(TreeSelectionEvent arg0) {

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

    private void createUIComponents() {
        List<String[]> todoFormated = formatTreeOfTodo();
        List<DefaultMutableTreeNode> createdNode = new ArrayList<DefaultMutableTreeNode>();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("tag");
        for (int i = 0; i < todoFormated.size(); i++) {
            createTree(root, todoFormated.get(i), 0, false);
        }
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        tree1 = new Tree();
        tree1.setModel(treeModel);
        tree1.setRootVisible(true);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        myToolWindowContent = new JPanel();
        myToolWindowContent.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        myToolWindowContent.add(tree1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Label");
        myToolWindowContent.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        list1 = new JList();
        myToolWindowContent.add(list1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        progressBar1 = new JProgressBar();
        myToolWindowContent.add(progressBar1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return myToolWindowContent;
    }
}
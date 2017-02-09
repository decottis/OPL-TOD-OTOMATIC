package myToolWindow;

import Util.FileManager;
import Util.MyTree;
import Util.Todo;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.*;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.*;
import com.intellij.ui.table.JBTable;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
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

    // UI elements
    private JButton refreshToolWindowButton;
    private JButton hideToolWindowButton;
    private JLabel currentDate;
    private JLabel currentTime;
    private JLabel timeZone;
    private JPanel myToolWindowContent;
    private JTree tree1;
    private JTable table1;
    private JButton button1;
    private ToolWindow myToolWindow;

    // Utils
    public static List<File> listOfFiles;
    private Tree filesInConnection;
    private FileManager fm;
    private static MyTree myTree;


    public MyToolWindowFactory() {
        listOfFiles = new ArrayList<File>();
        fm = new FileManager();
        myTree = new MyTree();

    }

    // Create the tool window content.
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        fm.getAllFiles(new File(project.getBasePath()));
        fm.getAllTag();

        // create my tree
        tree1 = myTree.refreshTree(tree1);

        $$$setupUI$$$();

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();

        //create content panel
        Content content = contentFactory.createContent(myToolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content, 0);


    }

    public void valueChanged(TreeSelectionEvent arg0) {

    }

    private void createUIComponents() {
        //file manager

        //create my tree
        if (myTree == null)
            myTree = new MyTree();
        if (tree1 != null)
            tree1 = myTree.refreshTree(tree1);
        else
            tree1 = new Tree();

        //create my table
        table1 = new JBTable();
    }

    public static MyTree getMyTree() {
        return myTree;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
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
        myToolWindowContent.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        tree1.setBackground(new Color(-2312));
        myToolWindowContent.add(tree1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(25, 25), null, 0, false));
        myToolWindowContent.add(table1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        button1 = new JButton();
        button1.setText("Button");
        myToolWindowContent.add(button1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return myToolWindowContent;
    }
}
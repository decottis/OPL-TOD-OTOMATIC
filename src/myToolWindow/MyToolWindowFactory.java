package myToolWindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.*;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.*;
import com.intellij.ui.treeStructure.Tree;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.*;
import java.util.Calendar;

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
    private ToolWindow myToolWindow;


    public MyToolWindowFactory() {

    }

    // Create the tool window content.
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        myToolWindow = toolWindow;

        //make nodes - test nodes
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("tree1");

        DefaultMutableTreeNode lv1 = new DefaultMutableTreeNode("lv1");
        DefaultMutableTreeNode lv12 = new DefaultMutableTreeNode("lv12");
        DefaultMutableTreeNode lv2 = new DefaultMutableTreeNode("lv2");


        root.add(lv1);
        lv1.add(lv12);
        root.add(lv2);

        lv2.add( new DefaultMutableTreeNode("lv21"));
        lv2.add( new DefaultMutableTreeNode("lv22"));
        lv2.add( new DefaultMutableTreeNode("lv23"));

        //make tree
        JTree filesInConnection = new Tree(root);
        filesInConnection.addTreeSelectionListener(this);


        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        filesInConnection.setModel(treeModel);
        filesInConnection.setRootVisible(true);

        JScrollPane filesScroll = new JBScrollPane(filesInConnection);

        //get content factory instance
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();

        //create content panel
        Content content = contentFactory.createContent(myToolWindowContent, "", false);
        Content f = contentFactory.createContent(filesInConnection, "", false);

        toolWindow.getContentManager().addContent(content, 0);
        toolWindow.getContentManager().addContent(f, 0);

    }

    public void valueChanged(TreeSelectionEvent arg0) {

    }

}
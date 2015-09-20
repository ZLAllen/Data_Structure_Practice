package hw_5;
import java.util.Scanner;
/**
 * A simple class which creates a tree with a reference to its root.<p> 
 * Zhaoqi Li<p>
 * 109051837<p>
 * Assignment 5 task 2<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 */
public class Tree {
	private TreeNode root;
/**
 * An empty constructor to create a empty tree.	
 */
	public Tree(){};

/**
 * Receive the reference to the root of the tree.
 * @return root as a TreeNode object.
 */
	public TreeNode getRoot(){
		return root;
	}
	
/**
 * Change the root of the tree.
 * @param root the new root of the tree.
 */
	public void setRoot(TreeNode root){
		this.root = root;
	}
/**
 * Return the TreeNode that matches the given name in the tree.
 * @param name the name of the TreeNode to look for.
 * @return the found TreeNode if any, null otherwise.
 */
	public TreeNode findNode(String name){
		return search(root, name);
	}
/**
 * a helper method to search for a node in the tree recursively.
 * @param source the node to be checked in the tree.
 * @param name the name of the TreeNode to look for.
 * @return the found TreeNode if any, null otherwise. 
 */
	private TreeNode search(TreeNode source, String name){
		//System.out.println(source.getName() + " " + name);
		if(source == null)
			return source;
		else{
			if(source.getName().equals(name))
				return source;
			else{
				TreeNode foundNode = search(source.getLeft(), name);
				if(foundNode != null)
					return foundNode;
				foundNode = search(source.getMiddle(), name);
				if(foundNode != null)
					return foundNode;
				return search(source.getRight(),name);
			}	
		}
	}
/**
 * Create a TreeNode object with the provided information, then add the node under the parentName	
 * @param name name of the new TreeNode.
 * @param selection menu of the new TreeNode.
 * @param message  message to be displayed by the new TreeNode.
 * @param parentName the name of the parent node where the new TreeNode is to be added under.
 * @return true if the new TreeNode is successfully added, false otherwise.
 */
	public boolean addNode(String name, String selection, String message, String parentName){
		TreeNode tmp = new TreeNode(name, selection, message);
		TreeNode parent = findNode(parentName);
		if(parent != null && (!parent.isFull())){
			if(parent.getLeft() == null)
				parent.setLeft(tmp);
			else if(parent.getMiddle() == null)
				parent.setMiddle(tmp);
			else if(parent.getRight() == null)
				parent.setRight(tmp);
			return true;
		}
		return false;
	}
/**
 * Print the menu of the given section by traversing all children paths.
 * @param parentInfo the name of the parent TreeNode which the printing traversal starts at.
 */
	public void printMenu(String parentInfo){
		TreeNode cursor = findNode(parentInfo);
		parentInfo = String.format("%-16s", cursor.getSelection());
		printMenu(parentInfo, cursor.getLeft());
		printMenu(parentInfo,cursor.getMiddle());
		printMenu(parentInfo,cursor.getRight());
	}
/**
 * A helper method to print the menu of the given section recursively.
 * @param parentInfo the name of the parent TreeNode which the printing traversal starts at.
 * @param cursor a reference to the current node in the tree.
 */
	private static void printMenu(String parentInfo, TreeNode cursor){
		if(cursor == null)
			return;
		else if(cursor.isLeaf())
			System.out.printf("%-75s%-5s\n", parentInfo + cursor.getSelection(), cursor.getMessage());
		else{
			parentInfo += cursor.getSelection() + ", ";
			printMenu(parentInfo, cursor.getLeft());
			printMenu(parentInfo, cursor.getMiddle());
			printMenu(parentInfo, cursor.getRight());
		}
	}
/**
 * Start a service session and prompt the users to select their menu by traverse down the tree.
 * At the end of the traversal, the method will print the price and summary of the user selection.
 */
	public void beginSession(){
		int choice;
		String selection = "";
		Scanner input = new Scanner(System.in);
		TreeNode cursor = root;
		while(cursor != null && (!cursor.isLeaf())){
			System.out.println();
			System.out.println(cursor.getMessage());
			TreeNode.printNode(cursor.getLeft(), 1);
			TreeNode.printNode(cursor.getMiddle(), 2);
			TreeNode.printNode(cursor.getRight(), 3);
			System.out.print("Choice: ");
			choice = input.nextInt();
			switch(choice){
				case 1:
					if(cursor.getLeft()!= null)
						cursor = cursor.getLeft();
					break;
				case 2:
					if(cursor.getMiddle()!= null)
						cursor = cursor.getMiddle();
					else{
						System.out.println("Invalid choice...");
						continue;
					}
					break;
				case 3:
					if(cursor.getRight()!= null)
						cursor = cursor.getRight();
					else{
						System.out.println("Invalid choice...");
						continue;
					}
					break;
				default:
					System.out.println("Invalid choice...");
					continue;
			}
			selection += cursor.getSelection() + "  ";
		}
		String[] result = selection.split("  ");
		String order = "The order at " + result[0] + ": ";
		for(int i = 1; i < result.length - 1 ; i++)
			order += result[i] + ", ";
		order += result[result.length - 1] + " has been sent to the kitchen. Total amount would be "
								+cursor.getMessage();
		System.out.println(order);
	}
}


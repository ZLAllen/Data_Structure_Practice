package hw_5;
/**
 * A simple class which creates a passenger TreeNode object with access to three string data values.<p> 
 * Zhaoqi Li<p>
 * 109051837<p>
 * Assignment 5 task 1<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 */
public class TreeNode {
	private String name;
	private String selection;
	private String message;
	private TreeNode left;
	private TreeNode middle;
	private TreeNode right;
/**
 * An empty constructor which creates a TreeNode object with no input parameters.	
 */
	public TreeNode(){};
/**
 * A constructor which creates a TreeNode object and assign name, selection, and message to its data field.
 * @param name	The name of the TreeNode object.
 * @param selection  The menu offered by this TreeNode.
 * @param message   An message provided by this TreeNode.
 */
	public TreeNode(String name,String selection ,String message){
		this.name = name;
		this.selection = selection;
		this.message = message;
	}
/**
 * Receive the name of the TreeNode object.
 * @return name as a string.
 */
	public String getName(){
		return name;
	}
/**
 * Change the name of the TreeNode.
 * @param name the new name of the TreeNode.
 */
	public void setName(String name){
		this.name = name;
	}
/**
 * Receive the menu offered by the selected TreeNode.
 * @return selection as a string.
 */
	public String getSelection(){
		return selection;
	}
/**
 * Change the menu of the selected TreeNode.
 * @param selection the new menu of the TreeNode.
 */
	public void setSelection(String selection){
		this.selection = selection;
	}
/**
 * Receive a message from the TreeNode.
 * @return message as a string.
 */
	public String getMessage(){
		return message;
	}
/**
 * Change the message of the TreeNode.
 * @param message the new message to be stored in the TreeNode.
 */
	public void setMessage(String message){
		this.message = message;
	}
/**
 * Receive the left child of the current TreeNode.
 * @return left as a TreeNode object.
 */
	public TreeNode getLeft(){
		return left;
	}
/**
 * Change the left child of the current TreeNode.
 * @param left the new left child.
 */
	public void setLeft(TreeNode left){
		this.left = left;
	}
/**
 * Receive the middle child of the current TreeNode.
 * @return left as a TreeNode object.
 */
	public TreeNode getMiddle(){
		return middle;
	}
/**
 * Change the middle child of the current TreeNode.
 * @param middle the new middle child.
 */
	public void setMiddle(TreeNode middle){
		this.middle = middle;
	}
/**
 * Receive the right child of the current TreeNode.
 * @return right as a TreeNode object.
 */
	public TreeNode getRight(){
		return right;
	}
/**
 * Change the right child of the current TreeNode.
 * @param right the new right child.
 */
	public void setRight(TreeNode right){
		this.right = right;
	}
/**
 * Return a boolean value indicating whether the current TreeNode is a leaf.
 * @return true if it is a leaf, false otherwise.
 */
	public boolean isLeaf(){
		return (left == null) && (middle == null) && (right == null);
	}
/**
 * Return a boolean value indicating whether the current TreeNode has full children.
 * @return true if it is full, false otherwise.
 */
	public boolean isFull(){
		return (left != null) && (middle != null) && (right != null);
	}
/**
 * Receive a number and the TreeNode, then print the number follow by the selection of the TreeNode.
 * @param node the selected TreeNode to be printed.
 * @param num the number of the TreeNode in the tree.
 */
	public static void printNode(TreeNode node, int num){
		if(node != null)
			System.out.println(num + " " + node.getSelection());
	}
}

package grozail.lab7;

/**
 * Created by grozail on 31.10.16.
 *
 */
public class Lab7 {
	public Lab7() {
		BinaryTree<Integer> binaryTree = new BinaryTree<>();
		binaryTree.insert(8);
		binaryTree.insert(3);
		binaryTree.insert(10);
		binaryTree.insert(1);
		binaryTree.insert(6);
		binaryTree.insert(4);
		binaryTree.insert(7);
		binaryTree.insert(14);
		binaryTree.insert(13);
		System.out.println("=====INORDER=====");
		binaryTree.inorderTraversal();
		System.out.println("=====POSTORDER=====");
		binaryTree.postorderTraversal();
		System.out.println("=====PREORDER=====");
		binaryTree.preorderTraversal();
		System.out.println("=====FUNCTIONAL=====");
		binaryTree.functionalTraversal((x)->{
			System.out.println(x*2);
			return null;
		});
		System.out.println("=====FINDING 4=====");
		System.out.println(binaryTree.find(4));
		System.out.println("=====FINDING 44=====");
		if (binaryTree.find(44) == null) {
			System.out.println("No such element exist");
		}
		System.out.println("=====FINDING ANCESTOR=====");
		System.out.println(binaryTree.getKey(binaryTree.findAncestor(1,7)));
		System.out.println("=====REMOVE 13(leave)=====");
		binaryTree.remove(13);
		binaryTree.inorderTraversal();
		System.out.println("=====REMOVE 10(one descendant)=====");
		binaryTree.remove(10);
		binaryTree.inorderTraversal();
		System.out.println("=====REMOVE 3(two descendant)=====");
		binaryTree.remove(3);
		binaryTree.inorderTraversal();
		binaryTree.remove(8);
		binaryTree.inorderTraversal();
		binaryTree.clear();

	}
}

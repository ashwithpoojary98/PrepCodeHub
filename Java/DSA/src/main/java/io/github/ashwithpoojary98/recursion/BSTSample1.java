package main.java.io.github.ashwithpoojary98.recursion;

public class BSTSample1<T extends Comparable<T>>  {
    
    private Node<T> root;


    public void insert(T data){
      root=insertRecord(data, root);
    }


    private Node<T> insertRecord(T data, Node<T> root){
        if(root==null){
            return new Node<>(data);
        }
        if(data.compareTo(root.data)<0){
            root.left=insertRecord(data, root.left);
        }else if(data.compareTo(root.data)>0){
            root.right=insertRecord(data, root.right);
        }
        return root;
    }

    public void delete(T data){
        
    }


    public void inOrder(){
       inOrder(root);
    }


    private void inOrder(Node<T> node){
        if(node==null){
            return;
        }
        inOrder(node.left);
        System.out.print(node.data+" ");
        inOrder(node.right);
    }

    public void preOrder(){
        preOrder(root);

    }

    private void preOrder(Node<T> node){
        if(node==null)
            return;
        System.out.print(node.data+" ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void postOrder(){
       postOrder(root);
    }

    private void postOrder(Node<T> node){
        if(node==null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data+" ");
    }






    private static class Node<T>{

        private T data;
        private Node<T> left;
        private Node<T> right;

        public Node(T data){
            this.data=data;
        }

    }


    public static void main(String[] args){
        BSTSample1<Integer> bst=new BSTSample1<>();
        bst.insert(4);
        bst.insert(3);
        bst.insert(5);
        bst.insert(7);
        bst.insert(6);
        bst.insert(2);
        bst.insert(9);
        bst.insert(10);
        bst.insert(-1);
        bst.insert(-2);
        bst.insert(1);
        bst.insert(8);

        bst.inOrder();
        System.out.println("\n--------");
        bst.postOrder();
        System.out.println("\n---------");
        bst.preOrder();
    }
}



package com.company;

class Node{

    String Symbol;
    String Code;
    int Number;
    int Count;
    Node left;
    Node right;
    Node parent;
    public Node() {

    }

    public Node(String Symbol, String Code, int nodeNumber, int counter, Node parent){
        this.Symbol = Symbol;
        this.Code = Code;
        this.Number = nodeNumber;
        this.Count = counter;
        this.parent = parent;
        left = null;
        right = null;
    }
    public Node(String Symbol, String Code, int nodeNumber, int counter, Node parent , Node left , Node right){
        this.Symbol = Symbol;
        this.Code = Code;
        this.Number = nodeNumber;
        this.Count = counter;
        parent = parent;
        this.left = left;
        this.right = right;
    }

    public void copy(Node n){
        this.Symbol = n.Symbol;
        this.Code = n.Code;
        this.Number = n.Number;
        this.Count = n.Count;
        this.left = n.left;
        this.right = n.right;
        this.parent = n.parent;
    }

}

public class Tree {
    Node root;
    Node current;
    //String S="";
    int MaxNodeNum;
    char rightCode = '1';
    char leftCode = '0';

    public Tree(int max) //constructor to create tree with a root
    {
        this.MaxNodeNum = max;
        root = new Node("" , "" , MaxNodeNum, 0 , null); //root does not have code wala symbol
        current = root;
    }

    public void add(String a){
        current = root;
        //System.out.println(a);
        if (check(current, a)) // means that node exists
        {
            current = root;
            Node tempNode = Find(current, a);
            updateNode(tempNode);
            updating(Find(root,a));
        }
        else{
            addNode(a);//new symbol
            updating(Find(root, a).parent);
        }
        //update tree after each added node
//        updating(root);
    }
    void updating(Node curr){
        if(curr == root)
            return ;
        updateNode(curr.parent); /// handle swapping
        updating(curr.parent);
    }
    void UpdateTree() /// todo remove  - i implemented it again - updating
    {
        //check if it is the root or not
        while (current.parent != null) {
            current = current.parent;
            updateNode(current);
        }
    }

    boolean check(Node start, String a){ //looping in the tree to fine the desired node
        if(start == null ) {
            return false;
        }
        else {
            if(start.Symbol != null)
                return ((start.Symbol.equals(a)) || check(start.right, a) || check(start.left, a));
            else
                return (check(start.right, a) || check(start.left, a));
        }
    }

    Node Find(Node node, String a){

        if(node == null)
            return node ;
        if(node.Symbol!= null){
            if(node.Symbol.equals(a)){
                return node ;

            }
            Node l = Find(node.left , a);
            Node r = Find(node.right , a);
            if(l!= null)
                return l ;
            else return r ;
        }
        else {
            Node l = Find(node.left , a);
            Node r = Find(node.right , a);
            if(l!= null)
                return l ;
            else return r ;
        }
    }


    void addNode(String a){ // adding a symbol that doesnt exist


        Node nyt = Find(root,  "NYT");
        if(nyt == null){
            /// first time
            nyt = root;
        }
        Node left= new Node("NYT",nyt.Code + leftCode , nyt.Number - 2 ,0,nyt);
        Node right= new Node(a,nyt.Code + rightCode , nyt.Number - 1 ,1,nyt);
        nyt.left= left;
        nyt.right =right;
        nyt.Symbol="";
        nyt.Count++;


    }


    void updateNode(Node tempNode){
        Node Swap = FindNodeToSwap(root, tempNode);
        if(Swap != null) {   /// todo handle the swapping
            swap(tempNode, Swap);
            updateSymobls(root ,null,"");
            Swap.Count++;
            //updating(Swap);   /// todo habda remove comment
        }
        else {
            tempNode.Count++;
           // updating(tempNode);   /// todo habda remove comment
        }
//        current.Count = current.Count + 1; //should increment the count by 1 every time (According to algorithm)
    }

    Node FindNodeToSwap(Node start,  Node node){ // start = root , node = what i wanna swap
        if(start == null ) {
            return null;
        }
        else if (SwapCond(node, start))
            return start;
        Node r = FindNodeToSwap(start.right, node);
        Node l = FindNodeToSwap(start.left, node);
        if( r != null){
            if(l!=null){
                return r.Number>l.Number? r : l ;
            }
            else
                return r;
        }
        else
            return l;
    }

    boolean SwapCond(Node node, Node SwapNode){ /// we handle the parent only , all other predecessors are larger than me
        if(node.parent !=SwapNode && (node.Number < SwapNode.Number) && (node.Count >= SwapNode.Count)) ///todo
            return true;
        else
            return  false;
    }

    boolean isParent(Node Child,Node Parent){
        if (Child == null)
            return false;
        if(Child.Number == Parent.Number)
            return true;
        return isParent(Child.parent, Parent);
    }

    void swap(Node myNode, Node SwapNode){ //todo
        Node temp;
        temp = new Node();
    /// we coy the old node in new node  then we change the arguments between the 2 nodes
        /// todo handle the parents and symbols of subtrees
        temp.Code = myNode.Code;
        temp.Number = myNode.Number;
        temp.parent = myNode.parent;
        temp.right = myNode.right;
        temp.left = myNode.left;
        temp.Count = myNode.Count;
        temp.Symbol = myNode.Symbol;

        myNode.Count = SwapNode.Count;
        myNode.left = SwapNode.left;
        myNode.right = SwapNode.right;
        myNode.Symbol = SwapNode.Symbol;


        SwapNode.Count = temp.Count;
        SwapNode.left = temp.left;
        SwapNode.right = temp.right;
        SwapNode.Symbol = temp.Symbol;


    }
    void updateSymobls(Node start,Node P , String s ){
        if(start ==null)
            return ;
        if(start!=root) {
            start.Code = s;
            start.parent = P;
//            start.right.setRepresentation(start.getParent().getRepresentation() + c);
        }
        updateSymobls(start.right,start,start.Code+'1');
        updateSymobls(start.left, start,start.Code+'0');
    }

    public String getSymbol(String n){
        Node get = Findcode(root, n);
        if (get == null)
            return "";
        return get.Symbol;
    }
    public String getNYT(){
        Node get = Find(root, "NYT");
        if(get == null)
            return "";
        return get.Code;
    }
    public String getCode(String n){
        Node get = Find(root, n);
        return get.Code;
    }
    Node Findcode(Node node, String a){

        if(node == null) // does not exist
            return node;
        if(node.Code!= null){ //ay haga gher el root
            if(node.Code.equals(a)){
                return node ;
            }
            Node l = Findcode(node.left , a);
            Node r = Findcode(node.right , a);
            if(l!= null)
                return l ;
            else return r ;
        }
        else { //lma ykon root
            return root;
        }
    }

}

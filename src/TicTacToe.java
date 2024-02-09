class TTCNode {
    //reminder 1 is human 2 is program
    int[][] board;
    TTCNode[] next;
    int value, type;

    /**
     * default constructor
     */
    public TTCNode() {
        this.board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = 0;
            }
        }
        this.next = new TTCNode[0];
        this.value = 0;
        this.type = 0;
    }

    /**
     * constructor with the type of the (root) node
     * @param type the type of the node 1 (human) or 2 (program)
     */
    public TTCNode(int type) {
        this.board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = 0;
            }
        }
        this.next = new TTCNode[0];
        this.value = 0;
        this.type = type;
    }

    /**
     * constructor with an existing board and a type
     * @param board
     * @param type
     */
    public TTCNode(int[][] board, int type) {
        this.board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = board[i][j];
            }
        }
        this.next = new TTCNode[0];
        this.value = 0;
        this.type = type;
    }

    /**
     * not sure yet we'll need that - is the board full?
     * @return true if the board has no free space
     */
    public boolean full() {

        boolean result = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.board[i][j] >  0) result = false;
            }
        }
        return result;

    }

    /**
     * we do not check if there are multiple winners - make sure the 
     * tree creation routine stops when there is a winner
     * @return the winner: 1 or 2 - or 0 if no winner
     */
    public int winner() {
        
        int result = 0;

        //check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                result = board[i][0];
            }
        }
    
        // check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                result = board[0][i];
            }
        }
    
        // check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            result = board[0][0];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] ) {
            result = board[0][2];
        }

        return result;
        
    }

    /**
     * a utility function to change the type of the node 
     * human <-> program
     * @param x a type
     * @return the reverse type
     */
    public static int changeType(int x) {
        if (x == 1) {
            return 2;
        } else if (x == 2) {
            return 1;
        } else {
            return 0;  // return some default value for other inputs
        }
    }

    /**
     * this function fills in the value of each node in the context of a minmac
     * each node of type 1 gets the min of its successors
     * each node of type 2 gets the max of its successors
     */
    public void minMax() {
       
    }

    /**
     * add a node to the array of successors
     * @param tmp a new TTCNode to add to the array of successors
     */
    public void add(TTCNode tmp) {

        // create a new array with a larger size
        TTCNode[] newArray = new TTCNode[this.next.length + 1];

        // copy the elements from the original array to the new array
        for (int i = 0; i < this.next.length; i++) {
            newArray[i] = this.next[i];
        }

        // add the new element to the end of the new array
        newArray[newArray.length - 1] = tmp;

        this.next = newArray;
    }

    /**
     * a utility function to print a 3x3 board
     */
    public void printBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * count how many nodes there is in the (sub)tree (recursively)
     * @return the number of nodes
     */
    public int count() {
        int total = 0;
        for (int i = 0; i < next.length; i++) {
            //count recursively
            total += ((TTCNode) next[i]).count();  
        }
        if (total == 0) total = 1;
        return total;
    }

    /**
     * This function plays 1 move for Player 1 or 2 and add the new configurations
     * to the "next" array. Then recursively calls itself on each of the new configurations
     * if they are not leaves (winning configurations).
     */
    public void createTree() {

       

    }

    public static void main(String[] args) {
        TTCNode first = new TTCNode(2);

        first.createTree();

        System.out.println("number of nodes "+first.count());

        first.minMax();
    }

}
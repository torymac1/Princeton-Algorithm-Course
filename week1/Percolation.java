import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;

public class Percolation {
   private WeightedQuickUnionUF site;
   private int[] p;
   private int length;
   private int total_open;
   public Percolation(int n){                // create n-by-n grid, with all sites blocked
       if (n <= 0){
           throw new IllegalArgumentException("n<=0");
       }
       site = new WeightedQuickUnionUF((n+2)*(n+2)+2);
       p = new int[(n+2)*(n+2)+2];
       total_open = 0;
       length = n+2;
   }
   private int xyTo1D(int i, int j){
       if (i < 0 || i > length || j < 0 || j > length){
           throw new java.lang.IndexOutOfBoundsException("out of bounds!");
       }
       return (i*length+j);
   }
   
   public    void open(int row, int col){
       if (row < 1 || row > length-2 || col < 1 || col > length-2){
           throw new java.lang.IndexOutOfBoundsException("out of bounds!");
       }
       int k = xyTo1D(row,col);  
       int k_up = xyTo1D(row-1,col);
       int k_down = xyTo1D(row+1,col);
       int k_right = xyTo1D(row,col-1);
       int k_left = xyTo1D(row,col+1);
       if (p[k] == 1){
           return;
       }
       p[k] = 1;
       total_open += 1;
       if (row == 1)
           site.union(k,length*length);
       if (row == length-2)
           site.union(k,length*length+1);
       if (p[k_up] == 1)
           site.union(k,k_up);
       if (p[k_down] == 1)
           site.union(k,k_down);
       if (p[k_left] == 1)
           site.union(k,k_left);
       if (p[k_right] == 1)
           site.union(k,k_right);
       return;
   }
   
   public boolean isOpen(int row, int col){
       if (row < 1 || row > length-2 || col < 1 || col > length-2){
           throw new java.lang.IndexOutOfBoundsException("out of bounds!");
       }
       int k=xyTo1D(row,col);
       return p[k] == 1;
   }
   
   public boolean isFull(int row, int col){
       if (row < 1 || row > length-2 || col < 1 || col > length-2){
           throw new java.lang.IndexOutOfBoundsException("out of bounds!");
       }
       int k = xyTo1D(row,col);
       return site.connected(k,length*length);
   }
   
   public     int numberOfOpenSites(){
       return total_open;
   }
   
   public boolean percolates(){
       return site.connected(length*length+1,length*length);
   }
   
   public static void main(String[] args){
   }
}
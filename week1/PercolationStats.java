import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.lang.*;

public class PercolationStats {
   private double[] x;
   private int t;
   public PercolationStats(int n, int trials){    // perform trials independent experiments on an n-by-n grid
       if (n <= 0 || trials <= 0){
           throw new IllegalArgumentException("out of range");
       }
       t=trials;
       x=new double[trials];
       
       StdRandom.setSeed(1000000L);
       for (int i = 0;i < trials;i++){
           Percolation perc = new Percolation(n);
           while (!perc.percolates()){
               perc.open(StdRandom.uniform(n)+1,StdRandom.uniform(n)+1);
           }
           x[i] = ((double) perc.numberOfOpenSites())/((double) n*n);
       }
       
   }
   
   public double mean(){
       return StdStats.mean(x);
   }
   
   public double stddev(){
       return StdStats.stddev(x);
   }
   
   public double confidenceLo(){
       double l = StdStats.mean(x)-1.96*StdStats.stddev(x)/Math.sqrt(t);
       return l;
   }
   
   public double confidenceHi(){
       double l = StdStats.mean(x)+1.96*StdStats.stddev(x)/Math.sqrt(t);
       return l;    
   }

   public static void main(String[] args){        
       PercolationStats p = new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
       StdOut.printf("%-23s%s%f\n","mean"," = ",p.mean());
       StdOut.printf("%-23s%s%f\n","stddev"," = ",p.stddev());
       StdOut.printf("%-23s%s%s%f%s\n","95% confidence interval"," = [",p.confidenceLo(),p.confidenceHi(),"]");
       
       
   }        
}
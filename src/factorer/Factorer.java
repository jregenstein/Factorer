/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package factorer;

import java.util.LinkedList;

/**
 *
 * @author jacobregenstein
 */
public class Factorer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        for(int j=1; j<100; j++){
//            for(int i = 100; i>1; i--){
//                System.out.print(twoPowerOfP(j, i));
//            }
//            System.out.println();
//        }  
//          System.out.println(nString(4,2));
        System.out.println(semiprimeFactor(570428561));
    }
    
    
    //This came from an observation that the number of powers of two in a number
    //follows the fractal pattern 0102010301020104010201030102010..., and for 
    //three there is the similar pattern of 001001002001001002001001003....
    //This is a method that makes those patterns. p determines the number used
    //to make the pattern, n determines the length of the pattern (length
    //will be approximately p^n, so the n value shouldn't be very large)
    public static String nString(int n, int p){
        if(n==0){
            String s = "";
            for(int i = 1; i<p; i++){
                s += "0";
            }
            return s;
        }
        String nless1String = nString(n-1,p);
        String returnString = nless1String;//so it only calculates nString(n-1) once
        
        for(int i = 1; i<p; i++){//this should loop p-1 times, so the sequence for p=2 produces 010201...
            returnString += n + nless1String;
        }
        return returnString;
    }
    
    //calculates the number of powers of p contained in a number very quickly,
    //alternate to listing out the pattern that the method above produces and
    //finding the number at that index
    public static int twoPowerOfP(int x, int p){
        int l = logFloor(p,x);
        int t = (int) Math.pow(p, l);
        if(x==t) return l;        
        return twoPowerOfP(x-t, p);
    }
    
    
    
    //calculates the floor of logx base b
    public static int logFloor(int b, int x){
        int i=0;
        while(Math.pow(b, i)<=x)i++;
        return i-1;
    }
    
    //used to call the the method below with the right initial values
    public static int semiprimeFactor(int m){
        return semiprimeFactor( (int) Math.ceil(Math.sqrt(m)), (int) Math.floor(Math.sqrt(m)), m);
    }
    
    static int i = 0;
    //optimize using modulus function
    //this is an attempt at using the idea behind newton's method of approximating
    //squares to factor a large number. In newtons method, you take two sides of
    //a square whos area is the number your trying to root, and adjust the sides
    //until the are each very close to the same length. In this method, we
    //start with a square who's area we are trying to factor and adjust the sides
    //till they are both integer length (or rather take integer length sides 
    //and adjust them till they multiply to the desired number)
    public static int semiprimeFactor(int p, int q, int m){
        //needs to be called with the right starting values, or else it may miss
        //the factors
        System.out.println(i++ + "P: " + p + " Q: " + q + " p*q-m: " + (p*q-m) + " m: " + m);
        if(p*q==m){
            return p;
        }else if(p*q<m){
            return semiprimeFactor((int)Math.ceil((double)m/(double)q),q, m);
        }else{
            return semiprimeFactor(p,m/p, m);
        }
    }
    
    //simple implementation of the sieve of eratosthenes
    public static void sieve(){
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 1; i<=1000; i++){
            list.add(i);
        }
        int p;
        int j;
        int count = 0;
        for(int i = 0; i<100; i++){
            j = 0;
            p = list.get(1);
            System.out.println("P is: " + p);
            while(j<list.size()){
                if(list.get(j)%p==0){
                    list.remove(j);
                    System.out.print(count + " ");
                    count = 0;
                }else{
                    j++;
                    count++;       
                }
            }
            System.out.println();
            System.out.println(list);
        }
    }
}

package org.apache.wicket.example.cdi.numberguess;


import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class Generator implements Serializable {

   private java.util.Random random = new java.util.Random(System.currentTimeMillis()); 
   private int maxNumber = 100;
   private int minNumber = 0;
   java.util.Random getRandom() {
      return random;
   }
   
   @Produces @Random int next() { 
      return getRandom().nextInt(maxNumber+1-minNumber) + minNumber; 
   }

   @Produces @MaxNumber int getMaxNumber() {
      return maxNumber;
   }
   
   @Produces @MinNumber int getMinumumNumber() {
       return minNumber;
   }
}
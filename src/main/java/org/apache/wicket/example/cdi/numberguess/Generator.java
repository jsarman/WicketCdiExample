package org.apache.wicket.example.cdi.numberguess;


import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class Generator implements Serializable {

   private java.util.Random random = new java.util.Random(System.currentTimeMillis()); 
   private int maxNumber = 100;
   
   java.util.Random getRandom() {
      return random;
   }
   
   @Produces @Random int next() { 
      return getRandom().nextInt(maxNumber+1); 
   }

   @Produces @MaxNumber int getMaxNumber() {
      return maxNumber;
   }
}
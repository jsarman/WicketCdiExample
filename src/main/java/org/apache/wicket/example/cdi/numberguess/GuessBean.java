/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.wicket.example.cdi.numberguess;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import org.apache.wicket.example.cdi.numberguess.GuessResult.Result;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.RangeValidator;

/**
 *
 * @author jsarman
 */
@ConversationScoped
public class GuessBean implements Serializable {
   
    @Inject
    Conversation conversation;
    @Inject
    @MaxNumber
    private int maxNumber;
    @Inject
    @MinNumber
    private int minNumber;
    
    @Inject
    @Random
    Instance<Integer> randomNumber;
    private int number;
    private int smallest;
    private int biggest;
    private int remainingGuesses;

    @PostConstruct
    public void init() {
        this.smallest = minNumber;
        this.remainingGuesses = 10;
        this.biggest = maxNumber;
        this.number = randomNumber.get();
    }

    public int getMinimumNumber() {
        return smallest;
    }
    
    public int getMaxNumber() {
        return maxNumber;
    }

    public IValidator<Integer> getValidator() {
        return new RangeValidator<>(smallest, biggest);
    }

    public GuessResult guessANumber(int guess) throws Exception {

        if (remainingGuesses <= 0) {
            throw new Exception("No Guesses Left");
        }

        int diff = number - guess;
        remainingGuesses--;

        Result result;
        if (diff < 0) {
            result = Result.TOO_HIGH;
        } else if (diff > 0) {
            result = Result.TOO_SMALL;
        } else {
            result = Result.CORRECT;
        }
        return new GuessResult(guess, result);
    }

    public int retriesLeft() {
        return remainingGuesses;
    }
}

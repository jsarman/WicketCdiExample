/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.wicket.example.cdi.numberguess;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import org.apache.wicket.example.cdi.ExamplePage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jsarman
 */
public class NumberGuessPage extends ExamplePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberGuessPage.class);
    @Inject
    private Conversation conversation;
    @Inject
    GuessBean guessBean;
    private Integer guessedNumber;
    
    public NumberGuessPage() {
        this(new PageParameters());
    }

    public NumberGuessPage(PageParameters parameters) {

        //setStatelessHint(true);
        if (conversation.isTransient()) {
            conversation.begin();
        }

        LOGGER.info("Beginning new Conversation with cid = {}", conversation.getId());


        final String id = conversation.getId();
        add(new FeedbackPanel("feedback"));

        final TextField<Integer> guess = new RequiredTextField<>("guess", new PropertyModel(this, "guessedNumber"));
        guess.add(guessBean.getValidator());
        guess.setType(Integer.class);

        Form<?> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {
                try {                    
                    GuessResult result = guessBean.guessANumber(guessedNumber);
                    switch (result.getResult()) {
                        case CORRECT:
                            info(guessedNumber + " is Correct");
                            break;
                        case TOO_HIGH:
                            info(guessedNumber + " is to high. You have " + guessBean.retriesLeft() + " Retries Left");
                            break;
                        case TOO_SMALL:
                            info(guessedNumber + " is too low. You have " + guessBean.retriesLeft() + " Retries Left");
                            break;
                    }
                } catch (Exception e) {

                    error("No more Tries Left");
                }
            }
        };

        form.add(guess);
        add(form);
        add(new Link("reset") {
            @Override
            public void onClick() {
                conversation.end();
                setResponsePage(NumberGuessPage.class);
            }
        });
        info("Guess A Number between " + guessBean.getMinimumNumber() + " and " +guessBean.getMaxNumber());
    }
}

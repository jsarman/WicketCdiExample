package org.apache.wicket.example.cdi;

import org.apache.wicket.cdi.CdiConfiguration;
import org.apache.wicket.cdi.ConversationPropagation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 *
 * @see org.apache.wicket.example.cdi.Start#main(String[])
 */
public class WicketApplication extends WebApplication {

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return CdiHomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        
            super.init();
            CdiConfiguration.get()
                    .setPropagation(ConversationPropagation.NONBOOKMARKABLE)
                    .configure(this);
        
    }
}

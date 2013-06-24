package org.apache.wicket.example.cdi;

import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
        try {
        BeanManager beanManager = (BeanManager) new InitialContext().lookup("java:comp/env/BeanManager");

        new CdiConfiguration(beanManager)
                .setPropagation(ConversationPropagation.NONBOOKMARKABLE)
                .configure(this);
        // add your configuration here
        } catch(NamingException ne) {
            ne.printStackTrace();
        }
    }
}

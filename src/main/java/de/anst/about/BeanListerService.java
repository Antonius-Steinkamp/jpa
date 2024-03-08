/**
 * BeanListerService.java created 08.03.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.about;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service
@Log
public class BeanListerService {

    private final ApplicationContext applicationContext;

    public BeanListerService( @Autowired ApplicationContext applicationContext) {
    	this.applicationContext = applicationContext;
    }
    
    public void listBeans() {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            log.info("Bean Name: " + beanName + " Bean Type: " + applicationContext.getType(beanName));
       }
    }
}

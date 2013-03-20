package remnants

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.plugins.GrailsPluginManager

/**
 * Created with IntelliJ IDEA.
 * User: Steffen Gates
 * Date: 3/19/13
 * Time: 11:42 PM
 * To change this template use File | Settings | File Templates.
 */
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

import javax.servlet.ServletContext

@Singleton
class ApplicationContextHolder implements ApplicationContextAware {
    private ApplicationContext ctx

    void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext
    }

    static ApplicationContext getApplicationContext() {
        getInstance().ctx
    }


    static Object getBean(String name) {
        getApplicationContext().getBean(name)
    }

    static GrailsApplication getGrailsApplication() {
        getBean('grailsApplication')
    }

    static ConfigObject getConfig() {
        getGrailsApplication().config
    }

    static ServletContext getServletContext() {
        getBean('servletContext')
    }

    static GrailsPluginManager getPluginManager() {
        getBean('pluginManager')
    }
}
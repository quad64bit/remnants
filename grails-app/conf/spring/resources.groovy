import remnants.ApplicationContextHolder

beans = {
    applicationContextHolder(ApplicationContextHolder) { bean ->
        bean.factoryMethod = 'getInstance'
    }
}


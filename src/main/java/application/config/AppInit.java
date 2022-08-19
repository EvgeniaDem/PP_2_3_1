package application.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {    // этот класс заменяет web.xml

    // Метод, указывающий на класс конфигурации
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }


    // Добавление конфигурации, в которой инициализируем ViewResolver, для корректного отображения jsp.
    @Override
    protected Class<?>[] getServletConfigClasses() {           // показываем, где находится конфигурация
        return new Class<?>[]{
                AppConfig.class
        };
    }


    //Данный метод указывает url, на котором будет базироваться приложение

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};            // значит, что ВСЕ HTML запросы будут посылаться на Dispatcherservlet
    }

}

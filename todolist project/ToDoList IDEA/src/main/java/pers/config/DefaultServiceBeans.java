package pers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.entity.ToDo;
import pers.service.DefaultService;

@Configuration
public class DefaultServiceBeans {
    /**
     * The default bean of the to do table
     */
    @Bean("AdminPublicService")
    public DefaultService<ToDo> adminService() {
        return new DefaultService<>("todo", "id", ToDo.class);
    }
}

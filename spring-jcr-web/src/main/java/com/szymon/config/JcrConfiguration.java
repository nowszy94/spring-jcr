package com.szymon.config;

import org.apache.jackrabbit.commons.JcrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springmodules.jcr.JcrSessionFactory;
import org.springmodules.jcr.JcrTemplate;
import org.springmodules.jcr.jackrabbit.JackrabbitSessionFactory;

import javax.annotation.PostConstruct;
import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.SimpleCredentials;
import java.io.IOException;

@Configuration
public class JcrConfiguration {

    @Autowired
    private JcrTemplate jcrTemplate;

    @Bean
    public Repository repository() throws IOException, RepositoryException {
//        RepositoryFactoryBean repositoryFactoryBean = new RepositoryFactoryBean();
//        repositoryFactoryBean.setConfiguration(new ClassPathResource("jackrabbit-repository.xml"));
//        repositoryFactoryBean.setHomeDir(new FileSystemResource("./target/repo"));
//        return repositoryFactoryBean;
        return JcrUtils.getRepository("http://localhost:8080/server");
    }

    @Bean
    public JcrSessionFactory sessionFactory(Repository repository) throws IOException {
        JackrabbitSessionFactory sessionFactory = new JackrabbitSessionFactory();
        sessionFactory.setRepository(repository);
        sessionFactory.setCredentials(new SimpleCredentials("admin", "admin".toCharArray()));
        return sessionFactory;
    }

    @Bean
    public JcrTemplate jcrTemplate(JcrSessionFactory sessionFactory) throws IOException {
        JcrTemplate jcrTemplate = new JcrTemplate(sessionFactory);
        jcrTemplate.setAllowCreate(true);
        return jcrTemplate;
    }

    @PostConstruct
    private void initRepository() throws RepositoryException {
        jcrTemplate.execute(session -> {
            Node rootNode = session.getRootNode();
            Node hello = rootNode.addNode("hello");
            hello.setProperty("viewName", "product");
            hello.setProperty("heading", "Hello World");
            hello.setProperty("description", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
            hello.setProperty("subheading", "Lorem ipsum dolor sit amet");
            hello.setProperty("description2", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.");
            hello.setProperty("imgUrl", "http://www.javabeat.net/wp-content/uploads/2015/06/spring-logo.png");

            Node node = rootNode.addNode("data");
            node.setProperty("hello", "world");
            node.setProperty("data1", "ABCD");
            node.setProperty("data2", "1234");
            node.setProperty("data3", "A1B2");
            session.save();
            return null;
        });
    }

}

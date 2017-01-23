package com.szymon.config;

import org.apache.jackrabbit.core.TransientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.jcr.*;
import java.io.IOException;

@Configuration
public class JcrConfiguration {

    @Autowired
    private Session session;

    @Bean
    public Repository repository() throws IOException {
        return new TransientRepository();
    }

    @Bean
    public Session session(Repository repository) throws RepositoryException {
        return repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
    }

    @PostConstruct
    private void initRepository() throws RepositoryException {
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
    }

}

package com.fsd.site.elasticsearch.config;

import java.net.InetAddress;

//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;  

//@Configuration
//@EnableElasticsearchRepositories("com.fsd.site.elasticsearch")
public class ElasticsearchConfig4Java {
//	private InetAddress hostname = "127.0.0.1";
	private int port = 9300;
	
//	@Bean
//	public ElasticsearchOperations elasticsearchTemplate() {
//		return new ElasticsearchTemplate(client());
//	}
//	
//	@Bean
//	private Client client() {
//        Client client = new TransportClient().addTransportAddress(new InetSocketTransportAddress(hostname,port));
//        return client;
//	}
}

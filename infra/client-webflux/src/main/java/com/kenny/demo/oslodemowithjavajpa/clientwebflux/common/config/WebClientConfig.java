package com.kenny.demo.oslodemowithjavajpa.clientwebflux.common.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    @Value("${target.biz.url}")
    private String bizBaseUrl;

    @Bean
    public WebClient webClient() {

        final TcpClient tcpClient = TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                .doOnConnected(connect -> connect
                        .addHandlerLast(new ReadTimeoutHandler(3000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(3000, TimeUnit.MILLISECONDS)));

        final ReactorClientHttpConnector connector = new ReactorClientHttpConnector(HttpClient.from(tcpClient));

        return WebClient.builder()
                .clientConnector(connector)
                .baseUrl(bizBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}

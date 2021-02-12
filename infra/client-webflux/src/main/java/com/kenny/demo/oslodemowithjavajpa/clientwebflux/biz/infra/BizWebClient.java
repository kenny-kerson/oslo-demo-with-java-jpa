package com.kenny.demo.oslodemowithjavajpa.clientwebflux.biz.infra;

import com.kenny.demo.oslodemowithjavajpa.clientwebflux.dto.CommonRequest;
import com.kenny.demo.oslodemowithjavajpa.clientwebflux.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.clientwebflux.dto.account.AccountInfo;
import com.kenny.demo.oslodemowithjavajpa.clientwebflux.dto.customer.BaseAccountList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class BizWebClient implements BizWebClientSpec, InitializingBean {

    private final static Long TIMEOUT_DURATION = 3L;

    private final WebClient.Builder webClientBuilder;
    private WebClient webClient;

    @Value("${target.biz.url}")
    private String bizBaseUrl;

    @Value("${target.biz.contentType")
    private String bizContentType;

    /**
     * 빈 생성 후 초기화 로직
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // WebClient
        this.webClient = WebClient.builder()
                .baseUrl(bizBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, bizContentType)
                .build();
    }

    /**
     * 기본계좌 목록조회
     *     - @PostMapping("/v1/biz/customer/get/base_account_list")
     */
    @Override
    public Mono<CommonResponse<BaseAccountList.Out>> getBaseAccountListByCstno(final CommonRequest<BaseAccountList.In> in) {

        log.debug( "__KENNY__ getBaseAccountListByCstno() webClient in : {}", in);

        final ParameterizedTypeReference<CommonResponse<BaseAccountList.Out>> parameterizedTypeReference = new ParameterizedTypeReference<>() {};

        final Mono<CommonResponse<BaseAccountList.Out>> out = webClient
                .post()
                .uri("/v1/biz/customer/get/base_account_list")
                .bodyValue(in)
                .retrieve()
                .bodyToMono(parameterizedTypeReference)
        ;

        log.debug( "__KENNY__ getBaseAccountListByCstno() webClient out : {}", out);

        return out;
    }

    /**
     * 수신계좌 정보조회
     *     - @GetMapping("/v1/biz/account/dep/info/{acno}")
     */
    @Override
    public Mono<CommonResponse<AccountInfo.Out>> getDepAccountInfo(final String acno) {

        log.debug( "__KENNY__ getDepAccountInfo() webClient in : {}", acno);

        final ParameterizedTypeReference<CommonResponse<AccountInfo.Out>> parameterizedTypeReference = new ParameterizedTypeReference<>() {};

        final Mono<CommonResponse<AccountInfo.Out>> out = webClient.get()
                .uri("/v1/biz/account/dep/info/" + acno)
                .retrieve()
                .bodyToMono(parameterizedTypeReference);

        log.debug( "__KENNY__ getDepAccountInfo() webClient out : {}", out);

        return out;
    }

    /**
     * 여신계좌 정보조회
     *     - @GetMapping("/v1/biz/account/loan/info/{acno}")
     */
    @Override
    public Mono<CommonResponse<AccountInfo.Out>> getLoanAccountInfo(final String acno) {

        log.debug( "__KENNY__ getLoanAccountInfo() webClient in : {}", acno);

        final ParameterizedTypeReference<CommonResponse<AccountInfo.Out>> parameterizedTypeReference = new ParameterizedTypeReference<>() {};

        final Mono<CommonResponse<AccountInfo.Out>> out = webClient.get()
                .uri("/v1/biz/account/loan/info/" + acno)
                .retrieve()
                .bodyToMono(parameterizedTypeReference);

        log.debug( "__KENNY__ getLoanAccountInfo() webClient out : {}", out);

        return out;
    }
}

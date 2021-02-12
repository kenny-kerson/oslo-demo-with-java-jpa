package com.kenny.demo.oslodemowithjavajpa.hub.ui;

import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonRequest;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.account.AccountInfo;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.customer.BaseAccountList;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.feign.BizFeignClient;
import com.kenny.demo.oslodemowithjavajpa.clientwebflux.biz.infra.BizWebClient;
import com.kenny.demo.oslodemowithjavajpa.common.code.StatusCode;
import com.kenny.demo.oslodemowithjavajpa.hub.ui.dto.AllAccountListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController implements CustomerControllerSpec {

    private final BizFeignClient bizFeignClient;
    private final BizWebClient bizWebClient;

    @Qualifier("supplyAsyncExecutor")
    private final Executor supplyAsyncExecutor;

    @Qualifier("thenAsyncExecutor")
    private final Executor thenAsyncExecutor;

    /**
     * 전계좌조회 CompletableFuture 병렬처리 버전
     */
    @Override
    public CommonResponse<AllAccountListDto.Out> getAllAccountListByCf(@RequestBody CommonRequest<AllAccountListDto.In> input) throws InterruptedException, ExecutionException, TimeoutException {
        log.debug("__KENNY__ getAllAccountListByCf() input : {}", input);

        /* 계좌 기본목록 조회 */
        final CommonResponse<BaseAccountList.Out> baseAccountListByCstno = bizFeignClient.getBaseAccountListByCstno(
                CommonRequest.<BaseAccountList.In>builder()
                        .cstno(input.getCstno())
                        .guid(input.getGuid())
                        .dataBody(
                                BaseAccountList.In.builder()
                                        .representationAcno(input.getDataBody().getRepresentationAcno())
                                        .build()
                        )
                        .build()
        );

        log.debug( "__KENNY__ Current Thread Info : {}", Thread.currentThread().getName());

        /* 계좌별 기본정보 조회 */

        // TODO CompletableFuture : 예외처리
        final List<CompletableFuture<CommonResponse<AccountInfo.Out>>> completableFutureList = baseAccountListByCstno.getDataBody().getGrid01()
                .stream()
                .map(el -> {
                    if ("01".equals(el.getFirstDivisionCode())) {
                        return CompletableFuture.supplyAsync(() -> {
                                log.debug( "__KENNY__ supplyAsync 01 : {}", Thread.currentThread().getName());

                                return bizFeignClient.getDepAccountInfo(el.getAcno());
                        }, supplyAsyncExecutor);

                    } else if ("02".equals(el.getFirstDivisionCode())) {
                        return CompletableFuture.supplyAsync(() -> {
                            log.debug( "__KENNY__ supplyAsync 02 : {}", Thread.currentThread().getName());

                            return bizFeignClient.getLoanAccountInfo(el.getAcno());
                        }, supplyAsyncExecutor);
                    }

                    return null;
                })
                .collect(Collectors.toList());

        final List<AllAccountListDto.Grid01> allAccountListDtoGrid01 = CompletableFuture
                .allOf(completableFutureList.toArray(new CompletableFuture[completableFutureList.size()]))
                .thenApplyAsync(el -> {
                        log.debug("__KENNY__ allOfCf thenApply : {}, Theard : {}", el, Thread.currentThread().getName());

                        final List<CommonResponse<AccountInfo.Out>> responseList = completableFutureList.stream()
                                .map(CompletableFuture::join)
                                .collect(Collectors.toList());

                        log.debug("__KENNY__ allOfCf thenApply return : {}", responseList);

                        return responseList;
                }, thenAsyncExecutor)
                .get(10L, TimeUnit.SECONDS)
                .stream()
                .map(el -> AllAccountListDto.Grid01.builder()
                        .acno(el.getDataBody().getAcno())
                        .firstDivisionCode(el.getDataBody().getFirstDivisionCode())
                        .acnoStcd(el.getDataBody().getAcnoStcd())
                        .acnoName(el.getDataBody().getAcnoName())
                        .build())
                .collect(Collectors.toList());

        // Response 조립
        return CommonResponse.<AllAccountListDto.Out>builder()
                .status(StatusCode.OK.getCode())
                .guid(input.getGuid())
                .cstno(input.getCstno())
                .dataBody(
                        AllAccountListDto.Out.builder()
                                .representationAcno(input.getDataBody().getRepresentationAcno())
                                .grid01(allAccountListDtoGrid01)
                                .build()
                )
                .build();
    }

    /**
     * 전계좌조회 WebFlux 병렬처리 버전
     */
    @Override
    public CommonResponse<AllAccountListDto.Out> getAllAccountListByWebFlux(final CommonRequest<AllAccountListDto.In> input) throws Exception {

        log.debug("__KENNY__ getAllAccountListByWebFlux() input : {}", input);

        /* 계좌 기본목록 조회 */
        final com.kenny.demo.oslodemowithjavajpa.clientwebflux.dto.CommonResponse<com.kenny.demo.oslodemowithjavajpa.clientwebflux.dto.customer.BaseAccountList.Out> baseAccountListByCstno = bizWebClient.getBaseAccountListByCstno(
                com.kenny.demo.oslodemowithjavajpa.clientwebflux.dto.CommonRequest.<com.kenny.demo.oslodemowithjavajpa.clientwebflux.dto.customer.BaseAccountList.In>builder()
                        .cstno(input.getCstno())
                        .guid(input.getGuid())
                        .dataBody(
                                com.kenny.demo.oslodemowithjavajpa.clientwebflux.dto.customer.BaseAccountList.In.builder()
                                        .representationAcno(input.getDataBody().getRepresentationAcno())
                                        .build()
                        )
                        .build()
                )
                .block();

        // 계좌별 정보조회
        final List<AllAccountListDto.Grid01> allAccountListDtoGrid01 = Flux.fromIterable(baseAccountListByCstno.getDataBody().getGrid01())
                .parallel()
                .flatMap(el -> {
                    if ("01".equals(el.getFirstDivisionCode())) {
                        return bizWebClient.getDepAccountInfo(el.getAcno());

                    } else if ("02".equals(el.getFirstDivisionCode())) {
                        return bizWebClient.getLoanAccountInfo(el.getAcno());
                    }

                    return null;
                })
                .sequential()
                .toStream()
                .map(el -> AllAccountListDto.Grid01.builder()
                        .acno(el.getDataBody().getAcno())
                        .firstDivisionCode(el.getDataBody().getFirstDivisionCode())
                        .acnoStcd(el.getDataBody().getAcnoStcd())
                        .acnoName(el.getDataBody().getAcnoName())
                        .build())
                .collect(Collectors.toList())
        ;

        // Response 조립
        return CommonResponse.<AllAccountListDto.Out>builder()
                .status(StatusCode.OK.getCode())
                .guid(input.getGuid())
                .cstno(input.getCstno())
                .dataBody(
                        AllAccountListDto.Out.builder()
                                .representationAcno(input.getDataBody().getRepresentationAcno())
                                .grid01(allAccountListDtoGrid01)
                                .build()
                )
                .build()
        ;
    }
}

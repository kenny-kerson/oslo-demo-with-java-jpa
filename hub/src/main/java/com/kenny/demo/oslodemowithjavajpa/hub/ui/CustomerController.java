package com.kenny.demo.oslodemowithjavajpa.hub.ui;

import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonRequest;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.account.AccountInfo;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.account.DepAccountInfo;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.customer.BaseAccountList;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.feign.BizFeignClient;
import com.kenny.demo.oslodemowithjavajpa.common.code.StatusCode;
import com.kenny.demo.oslodemowithjavajpa.hub.ui.dto.AllAccountListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController implements CustomerControllerSpec {

    private final BizFeignClient bizFeignClient;

    @Override
    public CommonResponse<AllAccountListDto.Out> getAllAccountList(@RequestBody CommonRequest<AllAccountListDto.In> input) throws InterruptedException, ExecutionException, TimeoutException {
        log.debug("__KENNY__ getAllAccountList input : {}", input);

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
        // Async 쓰레드풀 별도로 생성
        final Executor supplyAsyncExecutor = Executors.newFixedThreadPool(10);
        final Executor thenApplyAsyncExecutor = Executors.newFixedThreadPool(10);

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
                        });
                    }

                    return null;
                })
                .collect(Collectors.toList());

        final CompletableFuture<Void> allOfCf = CompletableFuture
                .allOf(completableFutureList.toArray(new CompletableFuture[completableFutureList.size()]));

        final List<AllAccountListDto.Grid01> allAccountListDtoGrid01 = allOfCf
                .thenApplyAsync(el -> {
                        log.debug("__KENNY__ allOfCf thenApply : {}, Theard : {}", el, Thread.currentThread().getName());

                        final List<CommonResponse<AccountInfo.Out>> responseList = completableFutureList.stream()
                                .map(CompletableFuture::join)
                                .collect(Collectors.toList());

                        log.debug("__KENNY__ allOfCf thenApply return : {}", responseList);

                        return responseList; }, thenApplyAsyncExecutor)
                .get(10L, TimeUnit.SECONDS)
                .stream()
                .map(el -> AllAccountListDto.Grid01.builder()
                        .acno(el.getDataBody().getAcno())
                        .firstDivisionCode(el.getDataBody().getFirstDivisionCode())
                        .acnoStcd(el.getDataBody().getAcnoStcd())
                        .acnoName(el.getDataBody().getAcnoName())
                        .build())
                .collect(Collectors.toList());


//        final CompletableFuture<CommonResponse<DepAccountInfo.Out>> cf =
//                CompletableFuture.supplyAsync(() -> bizFeignClient.getDepAccountInfo("3333010000001"));
//
//        cf.thenApply(el -> {
//            log.debug( "__KENNY__ thenApply el : {}", el);
//            return el;
//        });
//
//        cf.thenAccept(el -> {
//            log.debug( "__KENNY__ thenAccept el : {}", el);
//        });
//
//        try {
//            CommonResponse<DepAccountInfo.Out> outCommonResponse = cf.join();
//            log.debug( "__KENNY__ outCommonResponse : {}", outCommonResponse);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

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
}

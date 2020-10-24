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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
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

        /* 계좌별 기본정보 조회 */
        // TODO CompletableFuture : 병렬처리 & Async처리
        final List<CompletableFuture<CommonResponse<AccountInfo.Out>>> completableFutureList = baseAccountListByCstno.getDataBody().getGrid01()
                .stream()
                .map(el -> {
                    if ("01".equals(el.getFirstDivisionCode())) {
                        return CompletableFuture.supplyAsync(() -> bizFeignClient.getDepAccountInfo(el.getAcno()));

                    } else if ("02".equals(el.getFirstDivisionCode())) {
                        return CompletableFuture.supplyAsync(() -> bizFeignClient.getLoanAccountInfo(el.getAcno()));
                    }

                    return null;
                })
                .collect(Collectors.toList());

        List<AllAccountListDto.Grid01> grid01 = new ArrayList<>();

        final CompletableFuture<Void> allOfCf
                = CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[completableFutureList.size()]));

        final List<AllAccountListDto.Grid01> allAccountListDtoGrid01 = allOfCf.thenApply(el -> {
            log.debug("__KENNY__ allOfCf thenApply : {}", el);

            final List<CommonResponse<AccountInfo.Out>> responseList = completableFutureList.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList());

            log.debug("__KENNY__ allOfCf thenApply return : {}", responseList);

            return responseList;
        })
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

package com.kenny.demo.oslodemowithjavajpa.hub.ui;

import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonRequest;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.customer.BaseAccountList;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.feign.BizFeignClient;
import com.kenny.demo.oslodemowithjavajpa.common.code.StatusCode;
import com.kenny.demo.oslodemowithjavajpa.hub.ui.dto.AllAccountListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController implements CustomerControllerSpec {

    private final BizFeignClient bizFeignClient;

    @Override
    public CommonResponse<AllAccountListDto.Out> getAllAccountList(@RequestBody CommonRequest<AllAccountListDto.In> input) {
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
        List<CompletableFuture> cfList =

        CompletableFuture.allOf()

        // TODO 출력값 조립로직 변경필요!!
        return CommonResponse.<AllAccountListDto.Out>builder()
                .status(StatusCode.OK.getCode())
                .guid(input.getGuid())
                .cstno(input.getCstno())
                .dataBody(
                        AllAccountListDto.Out.builder()
                                .representationAcno(input.getDataBody().getRepresentationAcno())
                                .grid01(
                                        baseAccountListByCstno.getDataBody().getGrid01().stream()
                                                .map( el -> AllAccountListDto.Grid01.builder()
                                                        .acno(el.getAcno())
                                                        .build()
                                                )
                                                .collect(Collectors.toList())
                                )
                                .build()
                )
                .build();
    }
}

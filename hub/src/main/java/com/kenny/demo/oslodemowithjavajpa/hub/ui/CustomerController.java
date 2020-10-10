package com.kenny.demo.oslodemowithjavajpa.hub.ui;

import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonRequest;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.customer.BaseAccountList;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.feign.BizFeignClient;
import com.kenny.demo.oslodemowithjavajpa.hub.ui.dto.AllAccountListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomerControllerSpec {

    private final BizFeignClient bizFeignClient;

    @Override
    public CommonResponse<AllAccountListDto.Out> getAllAccountList(CommonRequest<AllAccountListDto.In> input) {

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
        // CompletableFuture : 병렬처리 & Async처리

        return null;
    }
}

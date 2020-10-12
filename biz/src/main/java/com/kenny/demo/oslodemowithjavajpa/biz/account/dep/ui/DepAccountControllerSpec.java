package com.kenny.demo.oslodemowithjavajpa.biz.account.dep.ui;

import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonRequest;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.account.DepAccountInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface DepAccountControllerSpec {

    @GetMapping("/v1/biz/account/dep/info/{acno}")
     CommonResponse<DepAccountInfo.Out> getDepAccountInfo(@RequestBody CommonRequest<DepAccountInfo.In> input, @PathVariable String acno);
}

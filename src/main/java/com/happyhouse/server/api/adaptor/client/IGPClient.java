package com.happyhouse.server.api.adaptor.client;

import com.happyhouse.server.api.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "igpClient", url = "${app.feign.igp-url}", configuration = {FeignConfig.class})
@FeignClient(name = "igpClient", url = "${app.feign.igp-url}")
public interface IGPClient {

    // @RequestHeader 로 받은 authHeader 는 요청시에 header 에 설정하라는 의미
    // 공통 @RequestHeader 를 셋팅해야 한다면 각각의 method에 @RequestHeader 를 적어줘야 하는 단점이 있다.
    //
    @GetMapping(value = "/api/v1/happy/user", produces = "application/json", consumes = "application/json")
    User findUserByEmail(@RequestHeader("adtcaps-header") String adtCapsHeader, @RequestParam String email);
}
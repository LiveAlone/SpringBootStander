package org.yqj.boot.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yqj.boot.demo.model.AddressResponse;
import org.yqj.boot.demo.model.BaseResponse;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2019-08-07
 * Email: yaoqijunmail@foxmail.com
 */
@RestController
public class LocalRestController {

    @RequestMapping(value = "/health", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse health(HttpServletRequest request){
        request.getParameterMap().forEach((key, value) -> {
            System.out.println("key : " + key);
            System.out.println("value : " + Arrays.asList(value).toString());
        });
        return BaseResponse.successResponse("success");
    }


    @RequestMapping(value = "/address", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse address() throws UnknownHostException {
        return BaseResponse.successResponse(new AddressResponse(InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress()));
    }
}

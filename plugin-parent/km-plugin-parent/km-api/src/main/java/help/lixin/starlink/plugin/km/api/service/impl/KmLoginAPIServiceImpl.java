package help.lixin.starlink.plugin.km.api.service.impl;

import help.lixin.starlink.plugin.km.api.mediator.KmCookieMediator;
import help.lixin.starlink.plugin.km.api.properties.KmProperties;
import help.lixin.starlink.plugin.km.api.service.IKmLoginAPIService;
import help.lixin.response.Response;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/9 5:18 下午
 * @Description
 */
public class KmLoginAPIServiceImpl implements IKmLoginAPIService {

    private KmProperties kmProperties;

    private KmCookieMediator kmCookieMediator;

//    {Unirest.config().enableCookieManagement(false);}

    @Override
    public void login() {
        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("userName",kmProperties.getUsername());
        jsonMap.put("pw",kmProperties.getPassword());

        HttpResponse<Response> result = Unirest.post(kmProperties.getUrl() + "/logi-security/api/v1/account/login")
                .body("{\"userName\":\""+kmProperties.getUsername()+"\",\"pw\":\""+kmProperties.getPassword()+"\"}")
                .contentType("application/json;charset=UTF-8")
                .asObject(Response.class);
        int status = result.getStatus();

        if (status == 200 && result.getBody().getCode() == 200) {
            kmCookieMediator.setCookie( result.getCookies().getNamed("JSESSIONID").getValue() );
            kmCookieMediator.setCreateTime(new Date());
        }

    }

    @Override
    public String fetchCookieValid(){
        Date curDate = new Date();
        if (kmCookieMediator.getCreateTime() == null || curDate.getTime() - kmCookieMediator.getCreateTime().getTime() > 60*60*24){
            login();
        }
        return kmCookieMediator.getCookie();
    }


    public KmLoginAPIServiceImpl(KmProperties kmProperties, KmCookieMediator kmCookieMediator) {
        this.kmProperties = kmProperties;
        this.kmCookieMediator = kmCookieMediator;
    }
}

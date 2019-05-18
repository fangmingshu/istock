package io.github.kingschan1204.istock.module.maindata.ctrl.api;

import com.alibaba.fastjson.JSONObject;
import io.github.kingschan1204.istock.module.maindata.po.ShareHolding;
import io.github.kingschan1204.istock.module.maindata.services.ShareHoldingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Api(description = "用户持仓管理")
@Controller
public class ShareHoldingCtl {
    @Autowired
    private ShareHoldingService shareHoldingService;

    @ApiOperation(value = "添加用户持仓", notes = "根据传入参数插入用户持仓数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "股票代码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "nameOfShare", value = "股票名字", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "number", value = "数量", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "priceFirst", value = "买入价格", required = true, paramType = "query", dataType = "Double")
    })
    @GetMapping("/shareholding/add")
    //http://localhost/shareholding/add?account=root1&code=000002&nameOfShare=%E4%B8%87%E7%A7%91A&number=1000&priceFirst=10.00
    public JSONObject addRecord(String account,
                                String code,
                                String nameOfShare,
                                Long number,
                                Double priceFirst,
                                @Autowired ShareHolding shareHolding){
        shareHolding.setAccount(account);
        shareHolding.setCode(code);
        shareHolding.setNameOfShare(nameOfShare);
        shareHolding.setNumber(number);
        shareHolding.setPriceFirst(priceFirst);
        shareHoldingService.addShareHolding(shareHolding);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",200);
        return jsonObject;
    }
}

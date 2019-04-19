package com.wjh.ims.back.component.priceTemplate;


import com.wjh.ims.back.ResponseUtil;
import com.wjh.ims.biz.priceTemplate.PriceTemplateService;
import com.wjh.ims.dto.vo.priceTemplate.PriceTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * 价格模板控制器
 */
@Component
@Path("/price/")
public class PriceTemplateComponent {

    @Autowired
    PriceTemplateService priceTemplateService;

    @GET
    @Path("template")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response template(@QueryParam("name") String name, @QueryParam("no") String no,@QueryParam("type") String type,
                             @QueryParam("start") int start, @QueryParam("rows") int rows) {
        try {
            List<PriceTemplateVO> priceTemplateVOS = priceTemplateService.getByCondition(name, no,type, start, rows);
            int count = priceTemplateService.countByCondition(name, no,type);
            return ResponseUtil.data(priceTemplateVOS, count);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.data(null, 0);
        }
    }


    @POST
    @Path("template")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response template(PriceTemplateVO priceTemplateVO) {
        try {
            priceTemplateService.addPriceTemplate(priceTemplateVO);
            return ResponseUtil.success("添加价格模板成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.failure(null);
        }
    }


    @PUT
    @Path("template/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response template(@PathParam("id") String id, PriceTemplateVO priceTemplateVO) {
        try {
            priceTemplateVO.setId(id);
            priceTemplateService.editPriceTemplate(priceTemplateVO);
            return ResponseUtil.success("修改价格模板成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.failure(null);
        }
    }

    /**
     * 导入excel 检索商品编号的下标及几个关键字段的下标，然后遍历取数，最后入库
     */
}

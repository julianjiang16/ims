package com.wjh.ims.back.component.goods;


import com.google.common.collect.Lists;
import com.wjh.ims.back.ResponseUtil;
import com.wjh.ims.back.component.BaseComponent;
import com.wjh.ims.biz.goods.GoodsService;
import com.wjh.ims.common.constant.ImsConstant;
import com.wjh.ims.common.enums.GoodsTablesFieldEnum;
import com.wjh.ims.common.utils.ExcelUtils;
import com.wjh.ims.common.utils.IDMaker;
import com.wjh.ims.common.utils.StringUtils;
import com.wjh.ims.dto.vo.goods.GoodsTypeVO;
import com.wjh.ims.dto.vo.goods.GoodsVO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

@Component
@Path("/goods/")
public class GoodsComponent extends BaseComponent {

    @Autowired
    GoodsService goodsService;

    @GET
    @Path("goods")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response goods(@QueryParam("name") String name, @QueryParam("no") String no, @QueryParam("py") String py,
                          @QueryParam("type") String type, @QueryParam("start") int start, @QueryParam("rows") int rows) {
        try {
            List<GoodsVO> goodsVOS = goodsService.getByCondition(name, no, py, type, start, rows);
            int count = goodsService.countByCondition(name, no, py, type);
            return ResponseUtil.data(goodsVOS, count);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.data(null, 0);
        }
    }

    /**
     * 添加商品
     *
     * @param goodsVO
     * @return
     */
    @POST
    @Path("goods")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response goods(GoodsVO goodsVO) {
        try {
            // 类型编号 + 商品编号
            goodsService.addGoods(goodsVO);
            return ResponseUtil.success("商品添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.failure(null);
        }
    }

    @PUT
    @Path("goods/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response goods(@PathParam("id") String id, GoodsVO goodsVO) {
        try {

            goodsVO.setId(id);
            goodsService.editGoods(goodsVO);
            return ResponseUtil.success("商品修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.failure(null);
        }
    }

    @GET
    @Path("type")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response type() {
        try {
            List<GoodsTypeVO> type = goodsService.getType();
            return ResponseUtil.data(type, type.size());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.data(null, 0);
        }
    }

    @POST
    @Path("type")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response type(GoodsTypeVO goodsTypeVO) {
        try {
            goodsTypeVO.setGmtCreate(new Date());
            goodsTypeVO.setGmtModified(new Date());
            goodsTypeVO.setId(IDMaker.get().nextId());
            goodsService.addGoodsType(goodsTypeVO);
            return ResponseUtil.success("商品类型添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.failure(null);
        }
    }

    @PUT
    @Path("type/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response type(@PathParam("id") String id, GoodsTypeVO goodsTypeVO) {
        try {
            goodsTypeVO.setGmtModified(new Date());
            goodsTypeVO.setId(id);
            goodsService.editGoodsType(goodsTypeVO);
            return ResponseUtil.success("商品类型修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.failure(null);
        }
    }

    @GET
    @Path("export")
    public void type(@Context HttpServletRequest request, @Context HttpServletResponse response, @QueryParam("field") String field,
                     @QueryParam("name") String name, @QueryParam("no") String no, @QueryParam("py") String py,
                     @QueryParam("type") String type) {
        OutputStream os =null;
        try {
            os= response.getOutputStream();
            List<String> titles=Lists.newArrayList();
            List<GoodsVO> goodsVOS = goodsService.getByCondition(name,no ,py ,type ,-1 , 10);
            List<List<String>> source=analysisFields(field,goodsVOS,titles);
            String[]  titlesArr = new String[titles.size()];
            Workbook workbook = ExcelUtils.createExcelWithoutRender(source,titles.toArray(titlesArr) , "商品");
            setResponseHeader(response, "商品表.xls");
            workbook.write(os);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<List<String>> analysisFields(String field, List<GoodsVO> goods, List<String> titles) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<List<String>> result = Lists.newArrayList();
        List<String> fields = StringUtils.splitStrByPattern(field, ImsConstant.COMMA_SEPARATOR);
        for (String f : fields) {
            GoodsTablesFieldEnum enums = GoodsTablesFieldEnum.getByCode(f);
            titles.add(enums.getDesc());
        }
        for (GoodsVO g : goods) {
            List<String> item = Lists.newArrayList();
            for (String f : fields) {
                GoodsTablesFieldEnum enums = GoodsTablesFieldEnum.getByCode(f);
                fillGoods(g, enums, item);
            }
            result.add(item);
        }
        return result;
    }

    private void fillGoods(GoodsVO goodsVO, GoodsTablesFieldEnum enums, List<String> item) {
        Class clz = goodsVO.getClass();
        Method method = null;
        try {
            method = clz.getMethod(enums.getMethod());
            item.add(method.invoke(goodsVO).toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}

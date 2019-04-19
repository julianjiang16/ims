import com.wjh.ims.dto.vo.user.UserVO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2015-2019
 * FileName: TestGoods
 * Author:   jcj
 * Date:     2019/3/25 16:31
 * Description:
 */
public class TestGoods {

    @Test
    public void test1(){
        Map map=new HashMap();
        UserVO u1=new UserVO();
        u1.setId("123");
        u1.setUserName("goods");
        UserVO u2=new UserVO();
        u2.setId("123");
        u2.setUserName("goods");
        map.put("k1",u1 );
        map.put("k2",u1 );

        List list=new ArrayList();
        list.add(u1);
        list.add(u1);
        System.err.println(list.get(0).equals(list.get(1)));
        System.err.println(list.get(0)+"----"+list.get(1));
        System.err.println(map.get("k2").equals(map.get("k1")));
    }
}

package com.htxk;

import com.htxk.ruoyi.common.utils.DateUtils;
import com.htxk.ruoyi.common.utils.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test {


    @org.junit.Test
    public void geiDate(){
        String s = new Md5Hash("202001103515" + "1234" + "254643").toHex().toString();
        System.out.println(s);
    }
}

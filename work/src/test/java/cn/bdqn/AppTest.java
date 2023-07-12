package cn.bdqn;


import com.github.davidfantasy.mybatisplus.generatorui.GeneratorConfig;
import com.github.davidfantasy.mybatisplus.generatorui.MybatisPlusToolsApplication;
import com.github.davidfantasy.mybatisplus.generatorui.mbp.NameConverter;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static void main(String[] args) {

        //使用MD5加密
        Md5Hash result = new Md5Hash("member1");
        System.out.println("md5加密后的结果：" + result);

        System.out.println("==================================");
        //加盐后加密,加密5次
        Md5Hash result1 = new Md5Hash("member3", "fuckingeveryone", 5);
        System.out.println("md5加密后加盐的结果" + result1);

    }

    @Test
    void test1(){


    }
}

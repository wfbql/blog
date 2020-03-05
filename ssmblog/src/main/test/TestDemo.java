import com.jiangfx.entity.Admin;
import com.jiangfx.entity.Blog;
import com.jiangfx.mapper.AdminMapper;
import com.jiangfx.mapper.BlogMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: 姜飞祥
 * @Description:
 * @Date: Create in 2018/10/12 22:47
 * @param: $params$
 * @return: $returns$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring/spring-*.xml"})
public class TestDemo {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void test0(){
        System.out.println("第一个测试方法*******");
        List<Blog> all = blogMapper.getAllBlog();
        System.out.println(all.toString());
    }

    @Test
    public void test1(){
        System.out.println("第二个测试方法*******");
        Admin all = adminMapper.getByUsernameAndPassword("姜飞祥","1234");
        System.out.println(all.toString());
    }
}

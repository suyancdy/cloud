package com.cdy.basicdata;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/20 14:41
 * @See: com.cestc.basicdata
 * @Modified:
 */
@Slf4j
//@SpringBootTest
public class BootTest {

//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private PeopleMapper peopleMapper;
//
//    @Test
//    public void test() throws Exception {
//        PageParam pageParam = new PageParam();
//        pageParam.setPageNum(2);
//        pageParam.setPageSize(3);
//        log.info("分页参数为: {}", pageParam.toString());
//        List<People> peopleList = peopleMapper.listByParams(pageParam);
//        peopleList.forEach(
//                i -> { log.info("====: {}", i.toString()); }
//        );
//
//    }


    public static void main(String[] args) {
//        List<Integer> integerList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            integerList.add(i);
//        }
//        if (integerList.size() > 5){
//            integerList = integerList.subList(0, 5);
//        }
//        integerList.forEach( i -> {
//            log.info("===的值为: {}", i.toString());
//        });
        log.debug("-");

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= 56; i++) {

            stringBuilder.append("'");
            stringBuilder.append(i);
            stringBuilder.append("'");
            stringBuilder.append(",");
        }
        log.debug("结果为: {}", stringBuilder.toString());



    }
}

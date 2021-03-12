package com.cdy.basicdata;

import com.cdy.basicdata.test.entity.LabelParty;
import com.cdy.basicdata.test.entity.LabelVolunteer;
import com.cdy.basicdata.test.mapper.LabelPartyMapper;
import com.cdy.basicdata.test.mapper.LabelVolunteerMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/20 14:41
 * @See: com.cestc.basicdata
 * @Modified:
 */
@Slf4j
@SpringBootTest
@MapperScan("com.cdy.basicdata.test.mapper")
public class BootTest {

//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private PeopleMapper peopleMapper;


    @Autowired
    private LabelPartyMapper labelPartyMapper;

    @Autowired
    private LabelVolunteerMapper labelVolunteerMapper;


//    @Test
//    public void idCardVerifyTest() {
//
//        List<LabelParty> labelPartyList = labelPartyMapper.listByParams();
//        log.debug("长度为: {}", labelPartyList.size());
//
//        labelPartyList
//                .stream()
//                .forEach(i -> {
//                    if (!idCardVerify(i.getIdCard())){
//                        labelPartyMapper.updateIdCardFlag(i.getId());
//                    }
//                });
//    }

    @Test
    public void volunteerIdCardVerifyTest() {

        List<LabelVolunteer> labelVolunteerList = labelVolunteerMapper.listByParams();

        log.debug("长度为: {}", labelVolunteerList.size());
        labelVolunteerList.forEach(i -> {
            if (!idCardVerify(i.getIdCard())){
                labelVolunteerMapper.updateIdCardIsDelete(i.getId());
            }
        });
        log.debug("结束");

    }


    public static boolean idCardVerify(String idCard) {
        // 如果传入的身份证号为空或空字符
        if (StringUtils.isBlank(idCard)) {
            return false;
        }
        // 仅适用于18位标准身份证号码
        if (idCard.length() != 18) {
            return false;
        }
        // 使身份证号码中的字母升大写
        idCard = idCard.toUpperCase();
        char[] idCardChars = idCard.toCharArray();
        // 重点1：加权因子计算
        final int[] factors = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        int sumFactor = 0;
        for (int i = 0; i < factors.length; i++) {
            // 数字的ASCII码是48-57
            int value = idCardChars[i] - 48;
            // 纯数字校验
            if (value < 0 || value > 9) {
                return false;
            }
            sumFactor += factors[i] * value;
        }
        // 重点2：校验码比对
        final char[] verifyCode = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

        return idCardChars[17] == verifyCode[sumFactor % 11];
    }


    public static void main(String[] args) {
        Boolean b = idCardVerify("622801199006080244");
        log.debug("真假值为: {}", b);
    }


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

}

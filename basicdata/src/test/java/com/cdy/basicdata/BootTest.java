package com.cdy.basicdata;

import com.cdy.basicdata.common.utils.RedisUtils;
import com.cdy.basicdata.system.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/20 14:41
 * @See: com.cestc.basicdata
 * @Modified:
 */
@Slf4j
@SpringBootTest
public class BootTest {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test() throws Exception {
        User user = new User(1, "123", "哈哈哈哈哈哈", 12);
        redisUtils.set("my", user);
        User u = (User) redisUtils.get("my");
        log.info("===：{}", user.equals(u));
        log.info("===: {}", u.toString());

    }


    public static void main(String[] args) {


        List<String> dataList = new ArrayList<>();
        List<Salary> salaryList = new ArrayList<>();

        double assistantSumSalary = 0;
        int assistantSum = 0;

        try {
            File file = new File("C:\\Users\\yhs\\Desktop\\abc.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String data = null;
            while ((data = bufferedReader.readLine()) != null) {
                dataList.add(data);
                String[] fieldArray = data.split(" ");
                Salary salary = new Salary();
                salary.setFirstName(fieldArray[0]);
                salary.setLastName(fieldArray[1]);
                salary.setGrade(fieldArray[2]);
                salary.setSalaryValue(Double.valueOf(fieldArray[3]));
                salaryList.add(salary);

                if (fieldArray[2].equals("assistant")){
                    assistantSum ++;
                    assistantSumSalary = assistantSumSalary + Double.valueOf(fieldArray[3]);
                }
            }
        } catch (Exception e) {
            System.err.println("读取文件的时候，发生错误！" + e.toString());
        }


        System.out.println("end");

        Map<String,List<Salary>> map = salaryList
                .stream()
                .collect(Collectors.groupingBy(Salary::getGrade));
        double  assistantAverageSalary = map.get("assistant")
                .stream()
                .mapToDouble(Salary::getSalaryValue)
                .summaryStatistics()
                .getAverage();
        System.out.println(assistantAverageSalary);

        System.out.println(assistantAverageSalary== (assistantSumSalary/assistantSum));


    }


}

@Data
class Salary{
    private String firstName;
    private String lastName;
    private String grade;
    private double salaryValue;

    public Salary() {
    }

    public Salary(String firstName, String lastName, String grade, double salaryValue) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.salaryValue = salaryValue;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", grade='" + grade + '\'' +
                ", salaryValue=" + salaryValue +
                '}';
    }
}

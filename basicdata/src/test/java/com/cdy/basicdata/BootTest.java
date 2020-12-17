package com.cdy.basicdata;

import com.cdy.basicdata.common.utils.RedisUtils;
import com.cdy.basicdata.system.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.Random;

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

    public void test() throws Exception {
        User user = new User(1, "123", "哈哈哈哈哈哈", 12);
        log.info("===: {}", user.toString());

    }


    public static void main(String[] args) {
        // produceData(1000);
        dataAnalyse();
    }

    public static void produceData(int length) {

        String assistant = "assistant";
        String associate = "associate";
        String full = "full";
        // 将等级放入数组中，通过random.nextInt(3) 随机选择等级
        String[] grade = {assistant, associate, full};
        Random random = new Random();
        // 向指定目录下的文本生成数据，如果没有文档则自动生成文档
        System.out.println("---开始生成数据");
        File file = new File("F:\\abc.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < length; i++) {
                String gradeString = grade[random.nextInt(3)]; // 随机选择等级
                double salary = 0.0;
                // 根据选择等级的按照指定的范围生成薪水值
                if (gradeString.equals(assistant)) {
                    salary = random.doubles(1, 50_000, 80_000).findFirst().getAsDouble();
                } else if (gradeString.equals(associate)) {
                    salary = random.doubles(1, 60_000, 110_000).findFirst().getAsDouble();
                } else if (gradeString.equals(full)) {
                    salary = random.doubles(1, 75_000, 130_000).findFirst().getAsDouble();
                }
                String data = "FirstName" + i + " " + "LastName" + i + " " + gradeString + " "
                        + String.format("%.2f", salary);
                bufferedWriter.write(data + "\n"); // 写入数据
                bufferedWriter.flush();
            }
            bufferedWriter.close(); // 关闭缓冲流
            fileWriter.close(); // 关闭文件流
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("---生成数据结束");
    }

    public static void dataAnalyse() {

        String assistant = "assistant";
        String associate = "associate";
        String full = "full";

        double assistantSumSalary = 0.0;
        int assistantSum = 0;

        double associateSumSalary = 0.0;
        int associateSum = 0;

        double fullSumSalary = 0.0;
        int fullSum = 0;
        System.out.println("---分析数据开始");
        try {
            File file = new File("F:\\abc.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data = null;
            while ((data = bufferedReader.readLine()) != null) { // 从指定文档中逐行读取数据
                // 将读取的数据分割为字符串数组，索引2为等级，索引3为薪水
                String[] fieldArray = data.split(" ");
                String grade = fieldArray[2];
                Double salary = Double.valueOf(fieldArray[3]);
                // 判读属于那个等级，并计算每个等级的数量总数，薪水总数
                if (grade.equals(assistant)) {
                    assistantSum++;
                    assistantSumSalary = assistantSumSalary + salary;
                } else if (grade.equals(associate)) {
                    associateSum++;
                    associateSumSalary = associateSumSalary + salary;
                } else if (grade.equals(full)) {
                    fullSum++;
                    fullSumSalary = fullSumSalary + salary;
                }
            }
            bufferedReader.close(); // 关闭缓冲流
            fileReader.close(); // 关闭文件流
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("助理教授的职工数为：" + assistantSum + "，总薪水为：" + String.format("%.2f", assistantSumSalary)
                + "，平均薪水为：" + String.format("%.2f", assistantSumSalary / assistantSum));
        System.out.println("副教授的职工数为：" + associateSum + "，总薪水为：" + String.format("%.2f", associateSumSalary) + "，平均薪水为："
                + String.format("%.2f", associateSumSalary / associateSum));
        System.out.println("教授的职工数为：" + fullSum + "，总薪水为：" + String.format("%.2f", fullSumSalary) + "，平均薪水为："
                + String.format("%.2f", fullSumSalary / fullSum));
        System.out.println("---分析数据结束");
    }


}

@Data
class Salary {
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

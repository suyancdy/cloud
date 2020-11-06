package com.cdy.basicdata.system.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/9 14:14
 * @See: com.cestc.basicdata.system.controller
 * @Modified:
 */
@Api(tags = "主页")
@Slf4j
@Controller
@RequestMapping("/index")
public class IndexController {
    /**
     * 设置
     *
     * @param paragraph
     * @param fontSize
     * @param fontFamily
     * @param rgbStr
     * @return
     */
    public static XWPFRun createByParam(XWPFParagraph paragraph, Integer fontSize, String fontFamily, String rgbStr) {
        XWPFRun run = paragraph.createRun();
        if (fontSize != null) {
            run.setFontSize(fontSize);
        }
        if (StringUtils.isNotBlank(fontFamily)) {
            run.setFontFamily(fontFamily);
        }
        if (StringUtils.isNoneBlank(rgbStr)) {
            run.setColor(rgbStr);
        }
        return run;
    }

    public static void main(String[] args) {
        log.info("---{}", StringUtils.isBlank(""));
    }



    @ApiOperation(value = "word导出")
    @RequestMapping(value = "/word/{id}", method = RequestMethod.GET)
    public void excel(@PathVariable Integer id, HttpServletResponse httpServletResponse) throws InvalidFormatException, IOException {
        // MattersOnlineVO mattersOnlineVO = onlineService.getById(id);

        //XWPFTemplate template = XWPFTemplate.compile("D:\\yhsFile\\temp\\1word\\my.docx");

        XWPFDocument document = new XWPFDocument();
        XWPFParagraph titleParagraph1 = document.createParagraph();
        titleParagraph1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun1 = titleParagraph1.createRun();
        titleRun1.setColor("000000");
        titleRun1.setFontFamily("宋体");
        titleRun1.setFontSize(18);
        titleRun1.setText("XX事项", 0);

        XWPFParagraph userInfoParagraph2 = document.createParagraph();
        userInfoParagraph2.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun noRun = createByParam(userInfoParagraph2, 12, "宋体", null);
        noRun.setText("他隔热隔热");
        noRun.addBreak();
        noRun.setText("坏晒的哈上帝阿斯顿还树蛙合法i文化氛围");


//        run.addCarriageReturn();
//
//        run.addCarriageReturn();
//        run.setText("XX照片:");
//        run.addCarriageReturn();
//        BufferedImage bufferImage = ImageIO.read(new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604320623319&di=91cb0e44407f7d081a46e6256f1199f2&imgtype=0&src=http%3A%2F%2Fy2.ifengimg.com%2Fifengimcp%2Fpic%2F20140714%2F44c27a63d6dd1792db70_size236_w1440_h900.jpg"));
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(byteArrayOutputStream);
//        ImageIO.write(bufferImage, "png", imageOutputStream);
//        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
//        byteArrayOutputStream.close();
//        imageOutputStream.close();
//        run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Generated", Units.toEMU(256), Units.toEMU(144));
//        inputStream.close();
        String fileName = "word.docx";
        httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setHeader("Content-disposition", "attachment;filename=" + fileName);
        httpServletResponse.flushBuffer();
        OutputStream outputStream = httpServletResponse.getOutputStream();
        document.write(outputStream);
        outputStream.flush();
        outputStream.close();

    }
}

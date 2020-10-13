package com.ranz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ranz.mapper.TOpLogMapper;
import com.ranz.mapper.UserMapper;
import com.ranz.pojo.TOpLog;
import com.ranz.pojo.User;
import jxl.Workbook;
import jxl.format.*;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
//@MapperScan("com.ranz.mapper")
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TOpLogMapper tOpLogMapper;

    @Test
    void contextLoads() {
        User user = userMapper.selectById(1);
        user.setAge(20);
        user.setEmail("chenx@PROANV.COM");

        User user2 = userMapper.selectById(1);
        user2.setAge(22);
        user2.setEmail("jiang@PROANV.COM");
        int i = userMapper.updateById(user2);

        int iw = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    void Test2() {
        QueryWrapper<TOpLog> objectQueryWrapper = new QueryWrapper<>();
//        objectQueryWrapper.isNotNull("name");
        objectQueryWrapper.inSql("op_log_id","select op_log_id from t_op_log  where DATE_FORMAT(update_time,'%Y-%m-%d')='2020-09-27'");
        objectQueryWrapper.orderByDesc("update_time");
        Page<TOpLog> var1=new Page<>(1,100);
        IPage<TOpLog> userIPage = tOpLogMapper.selectPage(var1, objectQueryWrapper);
        var1.getRecords().forEach(System.out::println);
        System.out.println(var1.getTotal());
    }


    @Test
    void Test3() {
        Page<TOpLog> objectPage = new Page<>(1,100);
        IPage<TOpLog> tOpLogIPage = tOpLogMapper.selectPage(objectPage, null);
        List<TOpLog> records = tOpLogIPage.getRecords();
        for (TOpLog record : records) {
            System.out.println(record.getOpDesc().toString());
        }

    }


    @Test
    void Test4() {
        TOpLog tOpLog = tOpLogMapper.selectById("00173958-7839-4fae-aa9b-401cbe0cf261");

        System.out.println("返回结果");
        System.out.println(tOpLog.getOpDesc());
        System.out.println(tOpLog.getOpType());


    }




    @Test
    void execlExport(){


        // 文件名
        SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

        String filename = sdfs.format(new Date())+".xls";

        try {

            // 写到服务器上
            String path = "/Users/JFlying/tool/static/execl/"+filename;

            // 写到服务器上（这种测试过，在本地可以，放到linux服务器就不行）
            //String path =  this.getClass().getClassLoader().getResource("").getPath()+"/"+filename;

            File name = new File(path);
//            if (name.i) {
//
//            }
            // 创建写工作簿对象
            WritableWorkbook workbook = Workbook.createWorkbook(name);
            // 工作表
            WritableSheet sheet = workbook.createSheet("地址列表", 0);
            // 设置字体;
            WritableFont font = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);

            WritableCellFormat cellFormat = new WritableCellFormat(font);
            // 设置背景颜色;
            cellFormat.setBackground(Colour.WHITE);
            // 设置边框;
            cellFormat.setBorder(Border.ALL, BorderLineStyle.DASH_DOT);
            // 设置文字居中对齐方式;
            cellFormat.setAlignment(Alignment.CENTRE);
            // 设置垂直居中;
            cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            // 分别给1,5,6列设置不同的宽度;
            sheet.setColumnView(0, 15);
            sheet.setColumnView(4, 60);
            sheet.setColumnView(5, 35);
            // 给sheet电子版中所有的列设置默认的列的宽度;
            sheet.getSettings().setDefaultColumnWidth(20);
            // 给sheet电子版中所有的行设置默认的高度，高度的单位是1/20个像素点,但设置这个貌似就不能自动换行了
            // sheet.getSettings().setDefaultRowHeight(30 * 20);
            // 设置自动换行;
            cellFormat.setWrap(true);

            // 单元格
            Label label0 = new Label(0, 0, "主键", cellFormat);
            Label label1 = new Label(1, 0, "操作时间", cellFormat);
            Label label2 = new Label(2, 0, "操作人", cellFormat);
            Label label3 = new Label(3, 0, "操作公司", cellFormat);
            Label label4 = new Label(4, 0, "操作类型", cellFormat);
            Label label5 = new Label(5, 0, "操作内容", cellFormat);
            Label label6 = new Label(6, 0, "更新人", cellFormat);
            Label label7 = new Label(7, 0, "更新时间", cellFormat);

            sheet.addCell(label0);
            sheet.addCell(label1);
            sheet.addCell(label2);
            sheet.addCell(label3);
            sheet.addCell(label4);
            sheet.addCell(label5);
            sheet.addCell(label6);
            sheet.addCell(label7);

            // 给第二行设置背景、字体颜色、对齐方式等等;
            WritableFont font2 = new WritableFont(WritableFont.ARIAL, 14, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
            WritableCellFormat cellFormat2 = new WritableCellFormat(font2);
            // 设置文字居中对齐方式;
            cellFormat2.setAlignment(Alignment.CENTRE);
            // 设置垂直居中;
            cellFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
            cellFormat2.setBackground(Colour.WHITE);
//            cellFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat2.setWrap(true);

            // 记录行数
            int n = 1;

            // 查找所有地址
            QueryWrapper<TOpLog> objectQueryWrapper = new QueryWrapper<>();
//        objectQueryWrapper.isNotNull("name");
            objectQueryWrapper.inSql("op_log_id","select op_log_id from t_op_log  where DATE_FORMAT(update_time,'%Y-%m-%d')='2020-09-28'");
            objectQueryWrapper.orderByDesc("update_time");
            Page<TOpLog> var1=new Page<>(1,500);
            IPage<TOpLog> userIPage = tOpLogMapper.selectPage(var1, objectQueryWrapper);
            List<TOpLog> addressList = var1.getRecords();

            if (addressList != null && addressList.size() > 0) {
                // 遍历
                for (TOpLog a : addressList) {
                    System.out.println("第"+n+"行数据"+a.getOpDesc());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String updateTime = sdf.format(a.getUpdateTime());
                    String createTime = sdf.format(a.getOpTime());
                    if ("查看".equals(a.getOpType())) {
                        a.setOpDesc("");
                    }
                    Label lt0 = new Label(0, n, a.getOpLogId(), cellFormat2);
                    Label lt1 = new Label(1, n, createTime, cellFormat2);
                    Label lt2 = new Label(2, n, a.getOpUserId(), cellFormat2);
                    Label lt3 = new Label(3, n, a.getSysCompanyId(), cellFormat2);
                    Label lt4 = new Label(4, n, a.getOpType(), cellFormat2);
                    Label lt5 = new Label(5, n, a.getOpDesc(), cellFormat2);
                    Label lt6 = new Label(6, n, a.getUpdateId(), cellFormat2);
                    Label lt7 = new Label(7, n, updateTime, cellFormat2);

                    sheet.addCell(lt0);
                    sheet.addCell(lt1);
                    sheet.addCell(lt2);
                    sheet.addCell(lt3);
                    sheet.addCell(lt4);
                    sheet.addCell(lt5);
                    sheet.addCell(lt6);
                    sheet.addCell(lt7);

                    n++;
                }
            }

            //开始执行写入操作
            workbook.write();
            //关闭流
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
//        // 第六步，下载excel
//
//        OutputStream out = null;
//        try {
//
//            // 1.弹出下载框，并处理中文
//            /** 如果是从jsp页面传过来的话，就要进行中文处理，在这里action里面产生的直接可以用
//             * String filename = request.getParameter("filename");
//             */
//            /**
//             if (request.getMethod().equalsIgnoreCase("GET")) {
//             filename = new String(filename.getBytes("iso8859-1"), "utf-8");
//             }
//             */
//
//
//            out =new FileOutputStream();
//            String path3 = "";
//
//            // inputStream：读文件，前提是这个文件必须存在，要不就会报错
//            InputStream is = new FileInputStream(path3);
//
//            byte[] b = new byte[4096];
//            int size = is.read(b);
//            while (size > 0) {
//                out.write(b, 0, size);
//                size = is.read(b);
//            }
//            out.close();
//            is.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }



}

package com.happy.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.happy.entity.Response;
import com.happy.jpa.master.entity.User;
import com.happy.jpa.master.repo.UserRepository;
import com.happy.jpa.master.service.UserService;
import com.happy.service.redis.RedisService;
import com.wj.entity.ExportData;
//import com.wj.idcenter.IdCenterGenerateTemplate;
import com.wj.utils.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * @author wangjun
 * @date 18-2-9 下午3:33
 * @description
 * @modified by
 */

@RestController
@Api(value = "测试controller", tags = "测试接口")
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private MessageSource messageSource;

    /*@Autowired
    private IdCenterGenerateTemplate idCenterGenerateTemplate;

    @RequestMapping("/testIdCenter")
    public Object testIdCenter() {
        Long id = idCenterGenerateTemplate.nextId();
        return id;
    }*/

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("jpaUserService")
    private UserService userService;

    @RequestMapping("/test")
    public Object test(@RequestBody JSONObject jsonObject) {
        return null;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ApiOperation(value = "测试swagger", httpMethod = "GET")
    public Object hello(@RequestParam @ApiParam(name = "name", value = "名称") String name) {

        String s = null;
        if ("zn".equals(name)) {
            Locale locale = Locale.CHINA;
            s = messageSource.getMessage("r.name", null, locale);
        }
        if ("en".equals(name)) {
            Locale locale = Locale.US;
            s = messageSource.getMessage("r.name", null, locale);
        }
        return Optional.ofNullable(s);
    }

    @RequestMapping(value = "/testRes", method = RequestMethod.GET)
    public ResponseEntity<Object> testResponseEntity() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "wangjun");
        Response response = new Response();
        response.setData(jsonObject);
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", "asdasdadsadaas");
        return new ResponseEntity<>(buildResponse(response), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/testExport", method = RequestMethod.GET)
    public void testExport(HttpServletResponse response) throws Exception {
        List<ExportData> exportDataList = new ArrayList<ExportData>();
        ExportData exportData1 = new ExportData();
        List<Object> titles1 = new ArrayList<Object>();
        titles1.add("1");
        titles1.add("2");
        titles1.add("3");
        exportData1.setTitles(titles1);
        List<List<Object>> rows1 = new ArrayList<List<Object>>();
        List<Object> row1 = new ArrayList<Object>();
        row1.add("1");
        row1.add("2");
        row1.add("3");
        rows1.add(row1);
        exportData1.setRows(rows1);
        exportData1.setTags("趋势实时数据");
        exportDataList.add(exportData1);

        ExportData exportData2 = new ExportData();
        List<Object> titles2 = new ArrayList<Object>();
        titles2.add("2");
        titles2.add("3");
        titles2.add("4");
        exportData2.setTitles(titles2);
        List<List<Object>> rows2 = new ArrayList<List<Object>>();
        List<Object> row2 = new ArrayList<Object>();
        row2.add("2");
        row2.add("3");
        row2.add("4");
        rows2.add(row2);
        exportData2.setRows(rows2);
        exportDataList.add(exportData2);
        Workbook wb = ExcelUtil.exportExcelWorkbook(exportDataList, ExcelUtil.XLSX);
        OutputStream outputStream = response.getOutputStream();
        response.reset();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.addHeader("Content-Disposition", "attachment;filename=trendAnalysisHistory.xlsx");
        //response.setContentType("application/vnd.ms-excel");
        //response.addHeader("Content-Disposition", "attachment;filename=trendAnalysisHistory.xls");
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping(value = "/testJpa", method = RequestMethod.GET)
    public Object testJpa() {
        /*User user = new User();
        user.setNickName("王军");
        user.setEmail("15289288565@163.com");
        userRepository.save(user);*/
        List<User> list = userService.findByCondition(1L, "ss", "");
        return JSONArray.toJSONString(list);
    }
}

//package com.evigel.sunnytable;
//
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.evigel.sunnytable.controller.BillController;
//import com.evigel.sunnytable.controller.ChartController;
//import com.evigel.sunnytable.controller.DataSetController;
//import com.evigel.sunnytable.controller.ExpressionController;
//import com.evigel.sunnytable.controller.FileController;
//import com.evigel.sunnytable.controller.TemplateController;
//import com.evigel.sunnytable.controller.UserController;
//import com.evigel.sunnytable.dto.BarChartDto;
//import com.evigel.sunnytable.dto.BillDto;
//import com.evigel.sunnytable.dto.DataSetDto;
//import com.evigel.sunnytable.dto.FanChartDto;
//import com.evigel.sunnytable.dto.FanChartTemplateDto;
//import com.evigel.sunnytable.dto.BarChartTemplateDto;
//import com.evigel.sunnytable.dto.FileDto;
//import com.evigel.sunnytable.dto.LineChartDto;
//import com.evigel.sunnytable.dto.LineChartTemplateDto;
//import com.evigel.sunnytable.dto.LineDataDto;
//import com.evigel.sunnytable.dto.QueryInfoDto;
//import com.evigel.sunnytable.dto.QueryTypeInfoDto;
//import com.evigel.sunnytable.dto.ScatterPlotDto;
//import com.evigel.sunnytable.dto.ScatterPlotTemplateDto;
//import com.evigel.sunnytable.dto.UncertainResult;
//import com.evigel.sunnytable.entity.Foid;
//import com.evigel.sunnytable.exception.MyException;
//import com.evigel.sunnytable.mapper.FoidMapper;
//import com.evigel.sunnytable.service.IBillService;
//import com.evigel.sunnytable.service.IChartService;
//import com.evigel.sunnytable.service.IDataSetService;
//import com.evigel.sunnytable.service.IFileService;
//import com.evigel.sunnytable.service.ITemplateService;
//import com.evigel.sunnytable.service.IUserService;
//import com.evigel.sunnytable.tool.Tool;
//
//import net.sf.json.JSONObject;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.UnsupportedEncodingException;
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringRunner.class)
//@Rollback(value = true)
//@Transactional
//@SpringBootTest
//public class InterfaceTest {
//
//    @Autowired
//    private FoidMapper foidMapper;
//
//    @Autowired
//    private DataSetController dataSetController;
//
//    @Autowired
//    private TemplateController templateController;
//
//    @Autowired
//    private ITemplateService templateService;
//
//    @Autowired
//    private IDataSetService dataSetService;
//
//    @Autowired
//    private UserController userController;
//
//    @Autowired
//    private IUserService userService;
//
//    @Autowired
//    private IFileService fileService;
//
//    @Autowired
//    private FileController fileController;
//
//    @Autowired
//    private ChartController chartController;
//
//    @Autowired
//    private IChartService chartService;
//
//    @Autowired
//    private IBillService billService;
//
//    @Autowired
//    private BillController billController;
//
//    @Autowired
//    private ExpressionController expressionController;
//
//    int id = 0;
//
//    private MockMvc dataSet;
//    private MockMvc file;
//    private MockMvc chart;
//    private MockMvc user;
//    private MockMvc template;
//    private MockMvc bill;
//    BarChartTemplateDto barChartTemplateDto;
//    LineChartTemplateDto lineChartTemplateDto;
//
//    ScatterPlotTemplateDto scatterPlotTemplateDto;
//    FanChartTemplateDto fanChartTemplateDto;
//    DataSetDto dataSetDto;
//    BarChartDto barChartDto;
//    LineChartDto lineChartDto;
//    ScatterPlotDto scatterPlotDto;
//    FanChartDto fanChartDto;
//
//    @Before
//    public void setup() {
//        dataSet = MockMvcBuilders.standaloneSetup(dataSetController).build();
//        file = MockMvcBuilders.standaloneSetup(fileController).build();
//        chart = MockMvcBuilders.standaloneSetup(chartController).build();
//        user = MockMvcBuilders.standaloneSetup(userController).build();
//        template = MockMvcBuilders.standaloneSetup(templateController).build();
//        List<String> colors = new ArrayList<>();
//        colors.add("#c1232b");
//        List<Integer> points = new ArrayList<>();
//        List<Integer> lines = new ArrayList<>();
//        String userId = "oU0Cz4gQWMD4mpUp9rInjsywGcnE";
//        barChartTemplateDto = new BarChartTemplateDto(null, "test", userId,
//                0.25, 0.0, colors, "true", "true", 14,
//                "30%,null,80%,null,vertical", "#1e90ff", "true", "false false",
//                "false false", "false false", 20, 0,
//                "false false", "");
//        lineChartTemplateDto = new LineChartTemplateDto(null, "test", userId,
//                "20",points, lines, colors, "true", 14,
//                "30%,null,80%,null,vertical", "#1e90ff", "true",
//                "true", "true", "#c1232b #27727b #fcce10 #e87c25 #b5c334 #fe8463 #9bca63 #fad860 #f3a43b #60c0dd #d7504b #c6e579 #f4e001 #f0805a #26c0c0",
//                "false false", "false false",
//                "false false", "false false", 20, 0, "false false",
//                "false", "false", "false", "#4d80e6", "0.1 0.5",
//                "0.1 0.5", "0.1 0.5", "");
//        scatterPlotTemplateDto = new ScatterPlotTemplateDto(null, "test", userId,
//                points, colors, "true", "false", "false", 12, "30%,null,80%,null,vertical",
//                "#0000ff", "false", "false", 0);
//        fanChartTemplateDto = new FanChartTemplateDto(null, "test", userId,
//                "40% 80%", 2, colors, "true", "true", 20, 10,
//                "30%,null,80%,null,vertical", "#b22222", "true", 8, "false", "false", "radius");
//
//        List<LineDataDto> lineDataDtos = new ArrayList<>();
//        List<String> lineData1 = new ArrayList<>();
//        lineData1.add("a1");
//        lineData1.add("a1");
//        LineDataDto line1 = new LineDataDto("col1",1404846020537421826L,lineData1);
//        List<String> lineData2 = new ArrayList<>();
//        lineData2.add("b1");
//        lineData2.add("b1");
//        LineDataDto line2 = new LineDataDto("col2",1404846020537421827L,lineData2);
//        lineDataDtos.add(line1);
//        lineDataDtos.add(line2);
//        dataSetDto = new DataSetDto(1L,"data",userId,lineDataDtos);
//        List<Long> yid = new ArrayList<>();
//        yid.add(1L);
//        yid.add(2L);
//        barChartDto = new BarChartDto(1L,"test2","x","y",3L,yid,0L,3L,3.0,4.0,
//                barChartTemplateDto,dataSetDto);
//        lineChartDto = new LineChartDto(1L,"test2","x","y",3L,yid,0L,3L,3.0,4.0,
//                lineChartTemplateDto,dataSetDto);
//        fanChartDto = new FanChartDto(1L,"test2","x","y",3L,yid,0L,3L,3.0,4.0,
//                fanChartTemplateDto,dataSetDto);
//        scatterPlotDto = new ScatterPlotDto(1L,"test2","x","y",3L,yid,0L,3L,3.0,4.0,
//                scatterPlotTemplateDto,dataSetDto);
//
//    }
//
////    @Test
////    public void login() throws Exception {
////        //ResponseEntity<DataSetDto> dataSetDto =  dataSetController.openDataset(1393402371683594242L);
//////        MvcResult mvcResult = dataSet.perform(MockMvcRequestBuilders.get("/data/open/1393402371683594242")
//////                .andExpect(MockMvcResultMatchers.status().isOk())
//////                .andDo(MockMvcResultHandlers.print())
//////                .andReturn();
////       userService.login("oU0Cz4ths4RwspUUgAkKoVXXjPT8");
////    }
//
//
//    @Test
//    public void openDataset() throws Exception {
//        //ResponseEntity<DataSetDto> dataSetDto =  dataSetController.openDataset(1393402371683594242L);
////        MvcResult mvcResult = dataSet.perform(MockMvcRequestBuilders.get("/data/open/1393402371683594242")
////                .andExpect(MockMvcResultMatchers.status().isOk())
////                .andDo(MockMvcResultHandlers.print())
////                .andReturn();
//        long tid = dataSetService.saveDataset(dataSetDto);
//        QueryWrapper<Foid> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("oid", tid);
//        Foid foid = foidMapper.selectOne(queryWrapper);
//        tid = foid.getFid();
//        try {
//            dataSetController.openDataset(123L);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//
//        MvcResult mvcResult = dataSet.perform(MockMvcRequestBuilders.get("/data/open/"+tid))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//        System.out.println(mvcResult.getResponse().getContentAsString());
//        userService.logout("oU0Cz4gQWMD4mpUp9rInjsywGcnE");
//    }
//
//    @Test
//    public void saveDataset() throws Exception {
//        //DataSetDto dataSetDto = dataSetService.openDataset(1393402371683594242L);
////        JSONObject jsonObject = JSONObject.fromObject(dataSetDto);
//        dataSetDto.setId(null);
//        try {
//            dataSetController.saveDataset(dataSetDto);
////            MvcResult mvcResult = dataSet.perform(MockMvcRequestBuilders.post("/data/save")
////                    .contentType(MediaType.APPLICATION_JSON)
////                    .content(net.minidev.json.JSONObject.toJSONString(jsonObject)))
////                    .andDo(MockMvcResultHandlers.print())
////                    .andReturn();
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        List<LineDataDto> lines = dataSetDto.getDataArray();
//        lines.get(0).getLineData().add("test");
//        dataSetDto.setDataArray(lines);
//        try {
//            dataSetController.saveDataset(dataSetDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//        //System.out.println(mvcResult.getResponse().getContentAsString());
//
//
//    }
//
//    @Test
//    public void replaceDataset() {
//        long tid = dataSetService.saveDataset(dataSetDto);
//        QueryWrapper<Foid> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("oid", tid);
//        Foid foid = foidMapper.selectOne(queryWrapper);
//        tid = foid.getFid();
//        dataSetDto.setId(tid);
//        try {
//            dataSetController.replaceDataset(dataSetDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        dataSetDto.setId(123L);
//        try {
//            dataSetController.replaceDataset(dataSetDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//        dataSetDto.setId(tid);
//        List<LineDataDto> lines = dataSetDto.getDataArray();
//        lines.get(0).getLineData().add("test");
//        dataSetDto.setDataArray(lines);
//        try {
//            dataSetController.replaceDataset(dataSetDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void exportDataset() {
//        long tid = dataSetService.saveDataset(dataSetDto);
//        QueryWrapper<Foid> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("oid", tid);
//        Foid foid = foidMapper.selectOne(queryWrapper);
//        tid = foid.getFid();
//        try {
//            dataSetController.exportDataset(1, dataSetDto, "oU0Cz4gQWMD4mpUp9rInjsywGcnE");
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//
//    }
//
//    @Test
//    public void getFile() throws Exception {
////        DataSetDto dataSetDto = dataSetService.openDataset(1393402371683594242L);
////        dataSetService.exportDataset("oU0Cz4rSbDwi6l5HQna_FqEFCvME", 1, dataSetDto);
//        MvcResult mvcResult = dataSet.perform(MockMvcRequestBuilders.get("/data/getFile/oU0Cz4rSbDwi6l5HQna_FqEFCvME/1"))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//        System.out.println(mvcResult.getResponse().getContentAsString());
//        //dataSetController.getFile("oU0Cz4rSbDwi6l5HQna_FqEFCvME", 1);
//    }
//
//    @Test
//    public void readFile() throws Exception {
//        String path = System.getProperty("user.dir");
//        System.out.println(path.substring(path.length() - 1));
//        if (path.substring(path.length() - 1).equals("t")) {
//            path = path.substring(0, path.length() - 6);
//            System.out.println(path);
//        }
//        ResultActions resultActions = dataSet.perform(
//                MockMvcRequestBuilders.multipart("/data/readFile/oU0Cz4rSbDwi6l5HQna_FqEFCvME").
//                        file(new MockMultipartFile("file", "tttt.xls", "", new FileInputStream(new File(path + "/src/main/resources/tttt.xls")))));
//        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//
//    @Test
//    public void displayTemplate() throws Exception {
//        MvcResult mvcResult = template.perform(MockMvcRequestBuilders.get("/template/chart/display/oU0Cz4rSbDwi6l5HQna_FqEFCvME/5"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//        System.out.println(mvcResult.getResponse().getContentAsString());
////        templateController.displayTemplate("oU0Cz4rSbDwi6l5HQna_FqEFCvME", 5);
//    }
//
//    @Test
//    public void openBarchartTemplate() throws Exception {
//        try {
//            templateController.openBarchatTemplate(123L);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//        long id = templateService.saveBarChartTemplate(barChartTemplateDto);
//        MvcResult mvcResult = template.perform(MockMvcRequestBuilders.get("/template/barchart/open/"+id))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//        System.out.println(mvcResult.getResponse().getContentAsString());
//        //templateController.openBarchatTemplate(1392993424807964674L);
//    }
//
//    @Test
//    public void openFanchartTemplate() throws Exception {
//        try {
//            templateController.openFanchartTemplate(123L);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//        long id = templateService.saveFanChartTemplate(fanChartTemplateDto);
//        MvcResult mvcResult = template.perform(MockMvcRequestBuilders.get("/template/fanchart/open/"+id))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//        System.out.println(mvcResult.getResponse().getContentAsString());
////        templateController.openFanchartTemplate(1392993424711495681L);
//    }
//
//    @Test
//    public void openLinechartTemplate() throws Exception {
//        try {
//            templateController.openLinechartTemplate(123L);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//        long id = templateService.saveLineChartTemplate(lineChartTemplateDto);
//        MvcResult mvcResult = template.perform(MockMvcRequestBuilders.get("/template/linechart/open/"+id))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//        System.out.println(mvcResult.getResponse().getContentAsString());
////        templateController.openLinechartTemplate(1392993424610832386L);
//    }
//
//    @Test
//    public void openScatterplotTemplate() throws Exception {
//        try {
//            templateController.openScatterplotTemplate(123L);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//        long id = templateService.saveScatterChartTemplate(scatterPlotTemplateDto);
//        MvcResult mvcResult = template.perform(MockMvcRequestBuilders.get("/template/scatterplot/open/"+id))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//        System.out.println(mvcResult.getResponse().getContentAsString());
////        templateController.openScatterplotTemplate(1392993424514363393L);
//    }
//
//    @Test
//    public void saveBarchatTemplate() {
//        //BarChartTemplateDto barChartTemplateDto = templateService.openBarChartTemplate(1402956499883593730L);
//        barChartTemplateDto.setId(null);
//        //templateService.saveBarChartTemplate(barChartTemplateDto);
//        id++;
//        barChartTemplateDto.setName("newname"+id);
//        try {
//            templateController.saveBarchatTemplate(barChartTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        try {
//            templateController.saveBarchatTemplate(barChartTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void saveFanchatTemplate() {
//        //FanChartTemplateDto fanChartTemplateDto = templateService.openFanChartTemplate(1402956499745181697L);
//        fanChartTemplateDto.setId(null);
//        //templateService.saveFanChartTemplate(fanChartTemplateDto);
//        id++;
//        fanChartTemplateDto.setName("newname"+id);
//        try {
//            templateController.saveFanchatTemplate(fanChartTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        try {
//            templateController.saveFanchatTemplate(fanChartTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void saveLinechatTemplate() {
//        //LineChartTemplateDto lineChartTemplateDto = templateService.openLineChartTemplate(1402956499552243714L);
//        lineChartTemplateDto.setId(null);
//        id++;
//        lineChartTemplateDto.setName("newname"+id);
//        //templateService.saveLineChartTemplate(lineChartTemplateDto);
//        try {
//            templateController.saveLinechatTemplate(lineChartTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        lineChartTemplateDto.setId(null);
//        List<Integer> pls = new ArrayList<>();
//        pls.add(1);
//        pls.add(2);
//        lineChartTemplateDto.setLine(pls);
//        lineChartTemplateDto.setPoint(pls);
//        id++;
//        lineChartTemplateDto.setName("newname"+id);
//        try {
//            templateController.saveLinechatTemplate(lineChartTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        try {
//            templateController.saveLinechatTemplate(lineChartTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void saveScatterplotTemplate() {
//        //ScatterPlotTemplateDto scatterPlotTemplateDto = templateService.openScatterChartTemplate(1402956499334139906L);
//        scatterPlotTemplateDto.setId(null);
//        id++;
//        scatterPlotTemplateDto.setName("newname"+id);
//        //templateService.saveScatterChartTemplate(scatterPlotTemplateDto);
//        try {
//            templateController.saveScatterplotTemplate(scatterPlotTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        scatterPlotTemplateDto.setId(null);
//        List<Integer> pls = new ArrayList<>();
//        pls.add(1);
//        pls.add(2);
//        scatterPlotTemplateDto.setPoint(pls);
//        id++;
//        scatterPlotTemplateDto.setName("newname"+id);
//        try {
//            templateController.saveScatterplotTemplate(scatterPlotTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        try {
//            templateController.saveScatterplotTemplate(scatterPlotTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void replaceBarchatTemplate() {
//
//        //BarChartTemplateDto barChartTemplateDto = templateService.openBarChartTemplate(1402956499883593730L);
//        //templateService.replaceBarChartTemplate(barChartTemplateDto);
//        long id = templateService.saveBarChartTemplate(barChartTemplateDto);
//        barChartTemplateDto.setId(id);
//        try {
//            templateController.replaceBarchatTemplate(barChartTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        barChartTemplateDto.setId(123L);
//        try {
//            templateController.replaceBarchatTemplate(barChartTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void replaceFanchatTemplate() {
//        //FanChartTemplateDto fanChartTemplateDto = templateService.openFanChartTemplate(1402956499745181697L);
//        //templateService.replaceFanChartTemplate(fanChartTemplateDto);
//        long id = templateService.saveFanChartTemplate(fanChartTemplateDto);
//        fanChartTemplateDto.setId(id);
//        try {
//            templateController.replaceFanchatTemplate(fanChartTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        fanChartTemplateDto.setId(123L);
//        try {
//            templateController.replaceFanchatTemplate(fanChartTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void replaceLinechatTemplate() {
//        //LineChartTemplateDto lineChartTemplateDto = templateService.openLineChartTemplate(1402956499552243714L);
//        //templateService.replaceLineChartTemplate(lineChartTemplateDto);
//        long id = templateService.saveLineChartTemplate(lineChartTemplateDto);
//        lineChartTemplateDto.setId(id);
//        try {
//            templateController.replaceLinechatTemplate(lineChartTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        lineChartTemplateDto.setId(1402956499552243714L);
//        List<Integer> pls = new ArrayList<>();
//        pls.add(1);
//        pls.add(2);
//        lineChartTemplateDto.setLine(pls);
//        lineChartTemplateDto.setPoint(pls);
//        try {
//            templateController.replaceLinechatTemplate(lineChartTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        lineChartTemplateDto.setId(123L);
//        try {
//            templateController.replaceLinechatTemplate(lineChartTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void replaceScatterplotTemplate() {
//        //ScatterPlotTemplateDto scatterPlotTemplateDto = templateService.openScatterChartTemplate(1402956499334139906L);
//        //templateService.replaceScatterChartTemplate(scatterPlotTemplateDto);
//        long id = templateService.saveScatterChartTemplate(scatterPlotTemplateDto);
//        scatterPlotTemplateDto.setId(id);
//        try {
//            templateController.replaceScatterplotTemplate(scatterPlotTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        scatterPlotTemplateDto.setId(1402956499334139906L);
//        List<Integer> pls = new ArrayList<>();
//        pls.add(1);
//        pls.add(2);
//        scatterPlotTemplateDto.setPoint(pls);
//        try {
//            templateController.replaceScatterplotTemplate(scatterPlotTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        scatterPlotTemplateDto.setId(123L);
//        try {
//            templateController.replaceScatterplotTemplate(scatterPlotTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void updateBar() {
//        barChartTemplateDto.setUserId("oU0Cz4gQWMD4mpUp9rInjsywGcnE");
//        barChartTemplateDto.setName("柱状图默认模板");
//        try {
//            templateController.updateBar(barChartTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//    }
//
//    @Test
//    public void updateFan() {
//        fanChartTemplateDto.setUserId("oU0Cz4gQWMD4mpUp9rInjsywGcnE");
//        fanChartTemplateDto.setName("扇形图默认模板");
//        try {
//            templateController.updateFan(fanChartTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//    }
//
//    @Test
//    public void updateLine() {
//        lineChartTemplateDto.setUserId("oU0Cz4gQWMD4mpUp9rInjsywGcnE");
//        lineChartTemplateDto.setName("折线图默认模板");
//        try {
//            templateController.updateLine(lineChartTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//    }
//
//    @Test
//    public void updateScatter() {
//        scatterPlotTemplateDto.setUserId("oU0Cz4gQWMD4mpUp9rInjsywGcnE");
//        scatterPlotTemplateDto.setName("散点图默认模板");
//        try {
//            templateController.updateScatter(scatterPlotTemplateDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//    }
//
//    @Test
//    public void shareTemplate() {
//        id++;
//        barChartTemplateDto.setName("newname"+id);
//        long fid = templateService.saveBarChartTemplate(barChartTemplateDto);
//        try {
//            templateController.shareTemplate("oU0Cz4ths4RwspUUgAkKoVXXjPT8", fid);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }        id++;
//        lineChartTemplateDto.setName("newname"+id);
//        fid = templateService.saveLineChartTemplate(lineChartTemplateDto);
//        try {
//            templateController.shareTemplate("oU0Cz4ths4RwspUUgAkKoVXXjPT8", fid);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }        id++;
//        fanChartTemplateDto.setName("newname"+id);
//        fid = templateService.saveFanChartTemplate(fanChartTemplateDto);
//        try {
//            templateController.shareTemplate("oU0Cz4ths4RwspUUgAkKoVXXjPT8", fid);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }        id++;
//        scatterPlotTemplateDto.setName("newname"+id);
//        fid = templateService.saveScatterChartTemplate(scatterPlotTemplateDto);
//        try {
//            templateController.shareTemplate("oU0Cz4ths4RwspUUgAkKoVXXjPT8", fid);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        try {
//            templateController.shareTemplate("oU0Cz4ths4RwspUUgAkKoVXXjPT8",1L);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void openDir() {
//        long rootid = userService.login("testuser1");
//        fileService.createDir("testuser1",rootid,"didtest");
//        //LineChartTemplateDto lineChartTemplateDto = templateService.openLineChartTemplate(1404846020277374978L);
//        lineChartTemplateDto.setUserId("testuser1");
//        id++;
//        lineChartTemplateDto.setName("newname"+id);
//        lineChartTemplateDto.setIsVisible("false");
//        templateService.saveLineChartTemplate(lineChartTemplateDto);
//        //LineChartDto lineChart = chartService.openLineChart(1404846021258842114L);
//        lineChartDto.getLineChart().setUserId("testuser1");
//        id++;
//        lineChartDto.setName("newname"+id);
//        lineChartDto.getLineChart().setName("newname"+id);
//        chartService.saveLineChart(lineChartDto);
//        try {
//            fileController.openDir("testuser1", rootid);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        ArrayList<FileDto> files = (ArrayList<FileDto>) fileService.openDir("testuser1", rootid);
//
//        try {
//            fileController.openDir("testuser1", files.get(0).getId());
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        try {
//            fileController.openDir("testuser1", files.get(1).getId());
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        try {
//            fileController.openDir("testuser1", files.get(2).getId());
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        try {
//            fileController.openDir("nouser", rootid);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//
//        try {
//            fileController.openDir("testuser1", 123L);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//
//
//    }
//
//    @Test
//    public void createDir() {
//        long rootid = userService.login("testuser1");
//        rootid = userService.login("testuser1");
//        try {
//            fileController.createDir("testuser1", rootid, "testdir");
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//
//        try {
//            fileController.createDir("testuser1", 123L, "testdir");
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void remove() {
//        long rootid = userService.login("testuser1");
//        FileDto file = fileService.createDir("testuser1", rootid, "testdir");
//        long dirid = file.getId();
//        try {
//            fileController.remove(dirid);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        //LineChartTemplateDto lineChartTemplateDto = templateService.openLineChartTemplate(1404846020277374978L);
//        lineChartTemplateDto.setUserId("testuser1");
//        id++;
//        lineChartTemplateDto.setName("newname"+id);
//        long mid = templateService.saveLineChartTemplate(lineChartTemplateDto);
//        //LineChartDto lineChart = chartService.openLineChart(1404846021258842114L);
//        lineChartDto.getLineChart().setUserId("testuser1");
//        id++;
//        lineChartDto.setName("newname"+id);
//        lineChartDto.getLineChart().setName("newname"+id);
//        long cid = chartService.saveLineChart(lineChartDto);
//        try {
//            fileController.remove(mid);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        try {
//            fileController.remove(cid);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        long tid = dataSetService.saveDataset(dataSetDto);
//        QueryWrapper<Foid> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("oid", tid);
//        Foid foid = foidMapper.selectOne(queryWrapper);
//        try {
//            fileController.remove(foid.getFid());
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//    }
//
//    @Test
//    public void moveDir() {
//        long rootid = userService.login("testuser1");
//        FileDto file = fileService.createDir("testuser1", rootid, "testdir");
//        long dirid = file.getId();
//        try {
//            fileController.moveDir(dirid, rootid);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//
//        try {
//            fileController.moveDir(dirid, 123L);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void rename() {
//        long rootid = userService.login("testuser1");
//        FileDto file = fileService.createDir("testuser1", rootid, "testdir");
//        long dirid = file.getId();
//        try {
//            fileController.rename(dirid, "rename");
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//
//        try {
//            fileController.rename(123L, "rename");
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//
//        try {
//            fileController.rename(1404846020277374978L, "rename");
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//
//        try {
//            fileController.rename(1404846021258842114L, "rename");
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//
//        try {
//            fileController.rename(1404846020801662977L, "rename");
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//    }
//
//    @Test
//    public void openBarChart() throws Exception {
//        long id = chartService.saveBarChart(barChartDto);
//        try {
//            chartController.openBarChart(123L);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//
//        MvcResult mvcResult = chart.perform(MockMvcRequestBuilders.get("/chart/barchart/open/"+id))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//        System.out.println(mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    public void openFanChart() throws Exception {
//        long id = chartService.saveFanChart(fanChartDto);
//        try {
//            chartController.openFanChart(123L);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//
//        MvcResult mvcResult = chart.perform(MockMvcRequestBuilders.get("/chart/fanchart/open/"+id))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//        System.out.println(mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    public void openLineChart() throws Exception {
//        long id = chartService.saveLineChart(lineChartDto);
//        try {
//            chartController.openLineChart(123L);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//
//        MvcResult mvcResult = chart.perform(MockMvcRequestBuilders.get("/chart/linechart/open/"+id))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//        System.out.println(mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    public void openScatterPlot() throws Exception {
//        long id = chartService.saveScatterChart(scatterPlotDto);
//        try {
//            chartController.openScatterPlot(123L);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//
//        MvcResult mvcResult = chart.perform(MockMvcRequestBuilders.get("/chart/scatterplot/open/"+id))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//        System.out.println(mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    public void saveBarChart() {
//        //BarChartDto barChart = chartService.openBarChart(1393402371771674625L);
//        barChartDto.setId(null);
//        id++;
//        barChartDto.setName("newname"+id);
//        barChartDto.getBarChart().setName("newname"+id);
//        try {
//            chartController.saveBarChart(barChartDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        barChartDto.getBarChart().setName("后端测试图1");
//        try {
//            chartController.saveBarChart(barChartDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void saveFanChart() {
//        //FanChartDto fanChart = chartService.openFanChart(1393402543796858881L);
//        fanChartDto.setId(null);
//        id++;
//        fanChartDto.setName("newname"+id);
//        fanChartDto.getFanChart().setName("newname"+id);
//        try {
//            chartController.saveFanChart(fanChartDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        fanChartDto.getFanChart().setName("后端测试图1");
//        try {
//            chartController.saveFanChart(fanChartDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void saveLineChart() {
//        //LineChartDto lineChart = chartService.openLineChart(1393402325315563522L);
//        lineChartDto.setId(null);
//        id++;
//        lineChartDto.setName("newname"+id);
//        lineChartDto.getLineChart().setName("newname"+id);
//        try {
//            chartController.saveLineChart(lineChartDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        lineChartDto.getLineChart().setName("后端测试图1");
//        try {
//            chartController.saveLineChart(lineChartDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void saveScatterPlot() {
//        //ScatterPlotDto scatterPlotDto = chartService.openScatterChart(1393402417594445825L);
//        scatterPlotDto.setId(null);
//        id++;
//        scatterPlotDto.setName("newname"+id);
//        scatterPlotDto.getScatterPlot().setName("newname"+id);
//        try {
//            chartController.saveScatterPlot(scatterPlotDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        scatterPlotDto.getScatterPlot().setName("后端测试图1");
//        try {
//            chartController.saveScatterPlot(scatterPlotDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void replaceBarChart() {
//        long id = chartService.saveBarChart(barChartDto);
//        barChartDto.setId(id);
//        id++;
//        barChartTemplateDto.setName("newname"+id);
//        id++;
//        dataSetDto.setName("newname"+id);
//        long mid = templateService.saveBarChartTemplate(barChartTemplateDto);
//        barChartDto.getBarChart().setId(mid);
//        long tid = dataSetService.saveDataset(dataSetDto);
//        QueryWrapper<Foid> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("oid", tid);
//        Foid foid = foidMapper.selectOne(queryWrapper);
//        barChartDto.getData().setId(foid.getFid());
//        try {
//            chartController.replaceBarChart(barChartDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        barChartDto.setId(123L);
//        try {
//            chartController.replaceBarChart(barChartDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void replaceFanChart() {
//        //FanChartDto fanChart = chartService.openFanChart(1393402543796858881L);
//        long id = chartService.saveFanChart(fanChartDto);
//        fanChartDto.setId(id);
//        id++;
//        fanChartTemplateDto.setName("newname"+id);
//        id++;
//        dataSetDto.setName("newname"+id);
//        long mid = templateService.saveFanChartTemplate(fanChartTemplateDto);
//        fanChartDto.getFanChart().setId(mid);
//        long tid = dataSetService.saveDataset(dataSetDto);
//        QueryWrapper<Foid> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("oid", tid);
//        Foid foid = foidMapper.selectOne(queryWrapper);
//        barChartDto.getData().setId(foid.getFid());
//        try {
//            chartController.replaceFanChart(fanChartDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        fanChartDto.setId(123L);
//        try {
//            chartController.replaceFanChart(fanChartDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void replaceLineChart() {
//        long id = chartService.saveLineChart(lineChartDto);
//        lineChartDto.setId(id);
//        id++;
//        lineChartTemplateDto.setName("newname"+id);
//        id++;
//        dataSetDto.setName("newname"+id);
//        long mid = templateService.saveLineChartTemplate(lineChartTemplateDto);
//        lineChartDto.getLineChart().setId(mid);
//        long tid = dataSetService.saveDataset(dataSetDto);
//        QueryWrapper<Foid> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("oid", tid);
//        Foid foid = foidMapper.selectOne(queryWrapper);
//        barChartDto.getData().setId(foid.getFid());
//        try {
//            chartController.replaceLineChart(lineChartDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        lineChartDto.setId(123L);
//        try {
//            chartController.replaceLineChart(lineChartDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void replaceScatterPlot() {
//        //ScatterPlotDto scatterPlot = chartService.openScatterChart(1393402417594445825L);
//        long id = chartService.saveScatterChart(scatterPlotDto);
//        scatterPlotDto.setId(id);
//        id++;
//        scatterPlotTemplateDto.setName("newname"+id);
//        id++;
//        dataSetDto.setName("newname"+id);
//        long mid = templateService.saveScatterChartTemplate(scatterPlotTemplateDto);
//        scatterPlotDto.getScatterPlot().setId(mid);
//        long tid = dataSetService.saveDataset(dataSetDto);
//        QueryWrapper<Foid> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("oid", tid);
//        Foid foid = foidMapper.selectOne(queryWrapper);
//        scatterPlotDto.getData().setId(foid.getFid());
//        try {
//            chartController.replaceScatterPlot(scatterPlotDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 0, e.getCode());
//        }
//        scatterPlotDto.setId(123L);
//        try {
//            chartController.replaceScatterPlot(scatterPlotDto);
//        } catch (MyException e) {
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void addBill() {
//        BillDto bill = new BillDto(1L, "testuser", "apple" ,
//                new Date(20210608),"food","true", 4.3);
//        billController.addBill(bill);
//    }
//
//    @Test
//    public void queryTime() {
//        BillDto bill = new BillDto(1L, "testuser", "apple",
//                new Date(20210608),"food","true", 4.3);
//        billController.addBill(bill);
//        QueryInfoDto info1 = new QueryInfoDto("testuser", new Date(20210607), new Date(20210609));
//        System.out.println(billController.queryTime(info1));
//        QueryInfoDto info2 = new QueryInfoDto("testuser", null, new Date(20210609));
//        System.out.println(billController.queryTime(info2));
//        QueryInfoDto info3 = new QueryInfoDto("testuser", new Date(20210607), null);
//        System.out.println(billController.queryTime(info3));
//        QueryInfoDto info4 = new QueryInfoDto("testuser", null, null);
//        System.out.println(billController.queryTime(info4));
//    }
//
//    @Test
//    public void queryType() {
//        BillDto bill = new BillDto(1L, "testuser", "apple",
//                new Date(20210608),"food","true", 4.3);
//        billController.addBill(bill);
//        QueryTypeInfoDto info1 = new QueryTypeInfoDto("testuser", new Date(20210607), new Date(20210609), "food");
//        System.out.println(billController.queryType(info1));
//        QueryTypeInfoDto info2 = new QueryTypeInfoDto("testuser", null, new Date(20210609), "food");
//        System.out.println(billController.queryType(info2));
//        QueryTypeInfoDto info3 = new QueryTypeInfoDto("testuser", new Date(20210607), null, "food");
//        System.out.println(billController.queryType(info3));
//        QueryTypeInfoDto info4 = new QueryTypeInfoDto("testuser", null, null, "food");
//        System.out.println(billController.queryType(info4));
//        try{
//            QueryTypeInfoDto info5 = new QueryTypeInfoDto("testuser", null, null, null);
//            System.out.println(billController.queryType(info5));
//        } catch (MyException e){
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void queryAllType() {
//        System.out.println(billController.queryAllType("oU0Cz4gQWMD4mpUp9rInjsywGcnE"));
//    }
//
//    @Test
//    public void replaceBill() {
//        BillDto bill = new BillDto(1L, "testuser", "apple",
//                new Date(20210608),"food","true", 4.3);
//        long bid = billService.addBill(bill);
//        bill.setId(bid);
//        bill.setDetail("peach");
//        try {
//            billController.replaceBill(bill);
//        } catch (MyException e){
//            assertEquals((Integer) 0, e.getCode());
//        }
//        bill.setId(1L);
//        bill.setDetail("peach");
//        try {
//            billController.replaceBill(bill);
//        } catch (MyException e){
//            assertEquals((Integer) 11, e.getCode());
//        }
//    }
//
//    @Test
//    public void deleteBill() {
//        BillDto bill = new BillDto(1L, "testuser", "apple",
//                new Date(20210608),"food","true", 4.3);
//        long bid = billService.addBill(bill);
//        try {
//            billController.deleteBill(bid);
//        } catch (MyException e){
//            assertEquals((Integer) 0, e.getCode());
//        }
//    }
//
//    @Test
//    public void getAverage() {
//        double [][] list ={{1.0,2.0,3.0},{2.0,3.0,4.0}};
//        ResponseEntity<List<Double>> actual = expressionController.getAverage(list);
//        List<Double> list2 = new ArrayList<>();
//        list2.add(2.0);
//        list2.add(3.0);
//        ResponseEntity<List<Double>> expected = ResponseEntity.ok().body(list2);
//        assertTrue(expected.equals(actual));
//    }
//
//    @Test
//    public void getSum() {
//        double [][] list ={{1.0,2.0,3.0},{2.0,3.0,4.0}};
//        ResponseEntity<List<Double>> actual = expressionController.getSum(list);
//        List<Double> list2 = new ArrayList<>();
//        list2.add(6.0);
//        list2.add(9.0);
//        ResponseEntity<List<Double>> expected = ResponseEntity.ok().body(list2);
//        assertTrue(expected.equals(actual));
//    }
//
//    @Test
//    public void getVariance() {
//        double [][] list ={{1.0,2.0,3.0},{2.0,3.0,4.0}};
//        ResponseEntity<List<Double>> actual = expressionController.getVariance(list);
//        List<Double> list2 = new ArrayList<>();
//        list2.add((double) 2/3);
//        list2.add((double) 2/3);
//        ResponseEntity<List<Double>> expected = ResponseEntity.ok().body(list2);
//        assertTrue(expected.equals(actual));
//    }
//
//    @Test
//    public void getRelativeError() {
//        double [][] list ={{1.0,2.0,3.0},{2.0,3.0,4.0}};
//        ResponseEntity<List<Double>> actual = expressionController.getRelativeError(list);
//        List<Double> list2 = new ArrayList<>();
//        list2.add(1.5);
//        list2.add(0.75);
//        ResponseEntity<List<Double>> expected = ResponseEntity.ok().body(list2);
//        assertTrue(expected.equals(actual));
//    }
//
//    @Test
//    public void getUncertainty() {
//        double [][] list ={{1.0,2.0,3.0},{2.0,3.0,4.0}};
//        ResponseEntity<List<UncertainResult>> actual = expressionController.getUncertainty(list);
//        List<UncertainResult> list2 = new ArrayList<>();
//        list2.add(new UncertainResult(0.5,0.5773502691896258,0.7637626158259734));
//        list2.add(new UncertainResult(0.5,1.1547005383792517, 1.2583057392117918));
//        ResponseEntity<List<UncertainResult>> expected = ResponseEntity.ok().body(list2);
//        assertTrue(expected.equals(actual));
//    }
//}
//

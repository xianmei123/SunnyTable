//package com.evigel.sunnytable;
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
//import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
//import org.databene.contiperf.PerfTest;
//import org.databene.contiperf.junit.ContiPerfRule;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringRunner.class)
//@Rollback(value = true)
//@Transactional
//@SpringBootTest
//public class ControllerTest {
//
//    @Test
//    public void contextLoads() {
//    }
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
//    private BillController billController;
//
//    @Autowired
//    private IBillService billService;
//
//    @Autowired
//    private ExpressionController expressionController;
//
//    @Autowired
//    private FoidMapper foidMapper;
//
//    @Rule
//    public ContiPerfRule contiPerfRule = new ContiPerfRule();
//
//    long tid = 1402994596134457346L;
//    long midFan = 1402991537455042561L;
//    long midLine = 1402991537358573570L;
//    long midScatter = 1402991537257910273L;
//    long midBar = 1402991537547317250L;
//    long pidFan = 1405197514547826690L;
//    long pidLine = 1405197347195097089L;
//    long pidScatter = 1405197571892350977L;
//    long pidBar = 1405197434176573441L;
//    String uid = "oU0Cz4gQWMD4mpUp9rInjsywGcnEL";
//    int id = 0;
//
//    @Before
//    public void setup() {
//
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void openDataset() {
//        dataSetService.openDataset(1405205364707856385L);
//        //System.out.println(dataSetDto);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void saveDataset() {
//        DataSetDto dataSetDto = dataSetService.openDataset(1405205364707856385L);
//        dataSetDto.setId(null);
//        dataSetService.saveDataset(dataSetDto);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void replaceDataset() {
//        DataSetDto dataSetDto = dataSetService.openDataset(1405205364707856385L);
//        dataSetService.replaceDataset(dataSetDto);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void exportDataset() {
//        DataSetDto dataSetDto = dataSetService.openDataset(1405205364707856385L);
//        dataSetService.exportDataset("evigel", 1, dataSetDto);
//    }
//
////    @Test
////    @PerfTest(invocations = 50,threads = 200)
////    public void getFile() throws Exception {
//////        DataSetDto dataSetDto = dataSetService.openDataset(1393402371683594242L);
//////        dataSetService.exportDataset("evigel", 1, dataSetDto);
////        Tool.getFile("evigel", 1);
////    }
//
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void displayTemplate() {
//        templateController.displayTemplate("evigel", 5);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void openBarchatTemplate() {
//        templateController.openBarchatTemplate(midBar);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void openFanchartTemplate() {
//        templateController.openFanchartTemplate(midFan);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void openLinechartTemplate() {
//
//        templateController.openLinechartTemplate(midLine);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void openScatterplotTemplate() {
//        templateController.openScatterplotTemplate(midScatter);
//    }
//
//    @Test
//    @PerfTest(invocations = 1, threads = 1)
//    public void updateBar() {
//        BarChartTemplateDto barChartTemplateDto = templateService.openBarChartTemplate(midBar);
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
//    @PerfTest(invocations = 1, threads = 1)
//    public void updateFan() {
//        FanChartTemplateDto fanChartTemplateDto = templateService.openFanChartTemplate(midFan);
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
//    @PerfTest(invocations = 1, threads = 1)
//    public void updateLine() {
//        LineChartTemplateDto lineChartTemplateDto = templateService.openLineChartTemplate(midLine);
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
//    @PerfTest(invocations = 1, threads = 1)
//    public void updateScatter() {
//        ScatterPlotTemplateDto scatterPlotTemplateDto = templateService.openScatterChartTemplate(midScatter);
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
//    @PerfTest(invocations = 50, threads = 200)
//    public void saveBarchatTemplate() {
//        BarChartTemplateDto barChartTemplateDto = templateService.openBarChartTemplate(midBar);
//        barChartTemplateDto.setId(null);
//        id++;
//        barChartTemplateDto.setName("newname" + id);
//        templateService.saveBarChartTemplate(barChartTemplateDto);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void saveFanchatTemplate() {
//        FanChartTemplateDto fanChartTemplateDto = templateService.openFanChartTemplate(midFan);
//        fanChartTemplateDto.setId(null);
//        id++;
//        fanChartTemplateDto.setName("newname" + id);
//        templateService.saveFanChartTemplate(fanChartTemplateDto);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void saveLinechatTemplate() {
//        LineChartTemplateDto lineChartTemplateDto = templateService.openLineChartTemplate(midLine);
//        lineChartTemplateDto.setId(null);
//        id++;
//        lineChartTemplateDto.setName("newname" + id);
//        templateService.saveLineChartTemplate(lineChartTemplateDto);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void saveScatterplotTemplate() {
//        ScatterPlotTemplateDto scatterPlotTemplateDto = templateService.openScatterChartTemplate(midScatter);
//        scatterPlotTemplateDto.setId(null);
//        id++;
//        scatterPlotTemplateDto.setName("newname" + id);
//        templateService.saveScatterChartTemplate(scatterPlotTemplateDto);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void replaceBarchatTemplate() {
//
//        BarChartTemplateDto barChartTemplateDto = templateService.openBarChartTemplate(midBar);
//        templateService.replaceBarChartTemplate(barChartTemplateDto);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void replaceFanchatTemplate() {
//        FanChartTemplateDto fanChartTemplateDto = templateService.openFanChartTemplate(midFan);
//        templateService.replaceFanChartTemplate(fanChartTemplateDto);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void replaceLinechatTemplate() {
//        LineChartTemplateDto lineChartTemplateDto = templateService.openLineChartTemplate(midLine);
//        templateService.replaceLineChartTemplate(lineChartTemplateDto);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void replaceScatterplotTemplate() {
//        ScatterPlotTemplateDto scatterPlotTemplateDto = templateService.openScatterChartTemplate(midScatter);
//        templateService.replaceScatterChartTemplate(scatterPlotTemplateDto);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void openDir() {
//        long rootid = userService.login("testuser1");
//        fileController.openDir("testuser1", rootid);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void createDir() {
//        long rootid = userService.login("testuser1");
//        fileController.createDir("testuser1", rootid, "testdir");
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void remove() {
//        long rootid = userService.login("testuser1");
//        FileDto file = fileService.createDir("testuser1", rootid, "testdir");
//        long dirid = file.getId();
//        fileService.remove(dirid);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void moveDir() {
//        long rootid = userService.login("testuser1");
//        FileDto file = fileService.createDir("testuser1", rootid, "testdir");
//        long dirid = file.getId();
//        fileService.move(dirid, rootid);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void rename() {
//        long rootid = userService.login("testuser1");
//        FileDto file = fileService.createDir("testuser1", rootid, "testdir");
//        long dirid = file.getId();
//        fileService.rename(dirid, "rename");
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void openBarChart() {
//        ResponseEntity<BarChartDto> barChart = chartController.openBarChart(pidBar);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void openFanChart() {
//        ResponseEntity<FanChartDto> fanChart = chartController.openFanChart(pidFan);
//
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void openLineChart() {
//        ResponseEntity<LineChartDto> lineChart = chartController.openLineChart(pidLine);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void openScatterPlot() {
//        ResponseEntity<ScatterPlotDto> scatterChart = chartController.openScatterPlot(pidScatter);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void saveBarChart() {
//        BarChartDto barChart = chartService.openBarChart(pidBar);
//        barChart.setId(null);
//        id++;
//        barChart.setName("newname" + id);
//        barChart.getBarChart().setName("newname" + id);
//        chartService.saveBarChart(barChart);
//
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void saveFanChart() {
//        FanChartDto fanChart = chartService.openFanChart(pidFan);
//        fanChart.setId(null);
//        id++;
//        fanChart.setName("newname" + id);
//        fanChart.getFanChart().setName("newname" + id);
//        chartService.saveFanChart(fanChart);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void saveLineChart() {
//        LineChartDto lineChart = chartService.openLineChart(pidLine);
//        lineChart.setId(null);
//        id++;
//        lineChart.setName("newname" + id);
//        lineChart.getLineChart().setName("newname" + id);
//        chartService.saveLineChart(lineChart);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void saveScatterPlot() {
//        ScatterPlotDto scatterPlot = chartService.openScatterChart(pidScatter);
//        scatterPlot.setId(null);
//        id++;
//        scatterPlot.setName("newname" + id);
//        scatterPlot.getScatterPlot().setName("newname" + id);
//        chartService.saveScatterChart(scatterPlot);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void replaceBarChart() {
//        BarChartDto barChart = chartService.openBarChart(pidBar);
//        chartService.replaceBarChart(barChart);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void replaceFanChart() {
//        FanChartDto fanChart = chartService.openFanChart(pidFan);
//        chartService.replaceFanChart(fanChart);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void replaceLineChart() {
//        LineChartDto lineChart = chartService.openLineChart(pidLine);
//        chartService.replaceLineChart(lineChart);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void replaceScatterPlot() {
//        ScatterPlotDto scatterPlot = chartService.openScatterChart(pidScatter);
//        chartService.replaceScatterChart(scatterPlot);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void addBill() {
//        BillDto bill = new BillDto(1L, "testuser", "apple",
//                new Date(20210608), "food", "true", 4.3);
//        billService.addBill(bill);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void queryTime() {
//        QueryInfoDto info1 = new QueryInfoDto("testuser", new Date(20210607), new Date(20210609));
//        billController.queryTime(info1);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void queryType() {
//
//        QueryTypeInfoDto info1 = new QueryTypeInfoDto("testuser", new Date(20210607), new Date(20210609), "food");
//        billController.queryType(info1);
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void queryAllType() {
//        billController.queryAllType("oU0Cz4gQWMD4mpUp9rInjsywGcnE");
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void replaceBill() {
//        BillDto bill = new BillDto(1403002552066445313L, "testuser", "apple",
//                new Date(20210608), "food", "true", 4.3);
//        billService.replaceBill(bill);
//
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void deleteBill() {
//        billService.deleteBill(1403002552066445313L);
//
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void getAverage() {
//        double[][] list = {{1.0, 2.0, 3.0}, {2.0, 3.0, 4.0}};
//        expressionController.getAverage(list);
//
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void getSum() {
//        double[][] list = {{1.0, 2.0, 3.0}, {2.0, 3.0, 4.0}};
//        expressionController.getSum(list);
//
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void getVariance() {
//        double[][] list = {{1.0, 2.0, 3.0}, {2.0, 3.0, 4.0}};
//        expressionController.getVariance(list);
//
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void getRelativeError() {
//        double[][] list = {{1.0, 2.0, 3.0}, {2.0, 3.0, 4.0}};
//        expressionController.getRelativeError(list);
//
//    }
//
//    @Test
//    @PerfTest(invocations = 50, threads = 200)
//    public void getUncertainty() {
//        double[][] list = {{1.0, 2.0, 3.0}, {2.0, 3.0, 4.0}};
//        expressionController.getUncertainty(list);
//
//    }
//
//}

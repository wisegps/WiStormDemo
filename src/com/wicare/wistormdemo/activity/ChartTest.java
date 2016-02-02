package com.wicare.wistormdemo.activity;

import java.util.ArrayList;
import java.util.List;

import com.wicare.wistorm.toolkit.WColorUtils;
import com.wicare.wistormdemo.R;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ChartTest extends Activity{

	/*	折线/ 曲线图     */
	private LineChartView lineChart;
	String[] weeks = {"周一","周二","周三","周四","周五","周六","周日"};//X轴的标注
	int[] weather = {9,7,6,7,8,6,8};//图表的数据
	private List<PointValue> mPointValues = new ArrayList<PointValue>();
	private List<AxisValue> mAxisValues = new ArrayList<AxisValue>();
	
		/*	饼图     */
	private PieChartView pieChart;
	private PieChartData pieChardata;
	List<SliceValue> values = new ArrayList<SliceValue>();
	private int[] data = {21,20,9,2,8,33,14,12}; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.charts);
		
		
		lineChart = (LineChartView)findViewById(R.id.line_chart);
		getLineChartAxisLables();//获取x轴的标注
        getLineChartAxisPoints();//获取坐标点
        initLineChart();//初始化
		
		
        pieChart = (PieChartView) findViewById(R.id.pie_chart);
        pieChart.setOnValueTouchListener(selectListener);//设置点击事件监听
        setPieChartData();
        initPieChart();
		
	}
	
	   /**
     * 初始化LineChart的一些设置
     */
    private void initLineChart(){
        Line line = new Line(mPointValues).setColor(Color.WHITE).setCubic(false);  //折线的颜色
	    List<Line> lines = new ArrayList<Line>();    
	    line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
	    line.setCubic(true);//曲线是否平滑
		line.setFilled(true);//是否填充曲线的面积
//		line.setHasLabels(true);//曲线的数据坐标是否加上备注
		line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
		line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示	
		line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示	
	    lines.add(line);  
	    LineChartData data = new LineChartData();  
	    data.setLines(lines);  
	      
	    //坐标轴  
	    Axis axisX = new Axis(); //X轴  
	    axisX.setHasTiltedLabels(true);  
	    axisX.setTextColor(Color.WHITE);  //设置字体颜色
	    axisX.setName("未来几天的天气");  //表格名称
	    axisX.setTextSize(7);//设置字体大小
	    axisX.setMaxLabelChars(7);  //最多几个X轴坐标
	    axisX.setValues(mAxisValues);  //填充X轴的坐标名称
	    data.setAxisXBottom(axisX); //x 轴在底部     
//	    data.setAxisXTop(axisX);  //x 轴在顶部
	    
	    Axis axisY = new Axis();  //Y轴  
	    axisY.setMaxLabelChars(7); //默认是3，只能看最后三个数字  
	    axisY.setName("温度");//y轴标注
	    axisY.setTextSize(7);//设置字体大小
	    
	    data.setAxisYLeft(axisY);  //Y轴设置在左边
//	    data.setAxisYRight(axisY);  //y轴设置在右边
	    
	    //设置行为属性，支持缩放、滑动以及平移  
	    lineChart.setInteractive(true);  
	    lineChart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);  
	    lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);  
	    lineChart.setLineChartData(data);  
	    lineChart.setVisibility(View.VISIBLE);  
    }
    
    
    /**
     * X 轴的显示
     */
    private void getLineChartAxisLables(){
        for (int i = 0; i < weeks.length; i++) {    
        	mAxisValues.add(new AxisValue(i).setLabel(weeks[i]));    
        }    	
    }

    /**
     * 图表的每个点的显示
     */
    private void getLineChartAxisPoints(){
        for (int i = 0; i < weather.length; i++) {    
        	mPointValues.add(new PointValue(i, weather[i]));      
        }    	
    }

	
    
    /**
     * 获取饼图数据
     */
    private void setPieChartData(){
    	
    	for (int i = 0; i < data.length; ++i) {
			SliceValue sliceValue = new SliceValue((float) data[i], WColorUtils.pickColor());//这里的颜色是我写了一个工具类 是随机选择颜色的
			values.add(sliceValue);
		}
    }
    
    
	/**
	 * 饼图初始化
	 */
	private void initPieChart() {
		pieChardata = new PieChartData();
		pieChardata.setHasLabels(true);//显示表情
		pieChardata.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比
		pieChardata.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
		pieChardata.setHasCenterCircle(true);//是否是环形显示
		pieChardata.setValues(values);//填充数据
		pieChardata.setCenterCircleColor(Color.WHITE);//设置环形中间的颜色
		pieChardata.setCenterCircleScale(0.5f);//设置环形的大小级别
		pieChardata.setCenterText1("饼图测试");//环形中间的文字1
		pieChardata.setCenterText1Color(Color.BLACK);//文字颜色
		pieChardata.setCenterText1FontSize(14);//文字大小
		
		pieChardata.setCenterText2("饼图测试");
		pieChardata.setCenterText2Color(Color.BLACK);
		pieChardata.setCenterText2FontSize(18);
		/**这里也可以自定义你的字体   Roboto-Italic.ttf这个就是你的字体库*/
//		Typeface tf = Typeface.createFromAsset(this.getAssets(), "Roboto-Italic.ttf");
//		data.setCenterText1Typeface(tf);
		
		pieChart.setPieChartData(pieChardata);
		pieChart.setValueSelectionEnabled(true);//选择饼图某一块变大
		pieChart.setAlpha(0.9f);//设置透明度
		pieChart.setCircleFillRatio(1f);//设置饼图大小
		
	}

	
	
	
	/**
	 * 监听事件
	 */
	private PieChartOnValueSelectListener selectListener = new PieChartOnValueSelectListener() {
		
		@Override
		public void onValueDeselected() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onValueSelected(int arg0, SliceValue value) {
			// TODO Auto-generated method stub
			Toast.makeText(ChartTest.this, "Selected: " + value.getValue(), Toast.LENGTH_SHORT).show();
		}
	};
    
    
    
    
}

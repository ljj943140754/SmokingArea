package com.common.service;
 
import java.util.ArrayList;
import java.util.Calendar;
 
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
 

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.common.ano.TimeStage;

@Service
public class TimerService implements ApplicationListener<ContextRefreshedEvent> {
	protected   final Log loger = LogFactory.getLog( getClass());
	private List<FixTimeStageServiceWrapper>  listfixtimewarpper = new ArrayList<FixTimeStageServiceWrapper>();
	 
	private void init(ApplicationContext cxt) {
		//1 加载bean。
		loadBean(cxt);
		//2 基础定时器。 
	     new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				 fixTime();
				 resetExcuteFlag();
			}
		}, 0,10000); 
	    
	}
	/**
	 * 还原执行标志位。
	 */
	protected void resetExcuteFlag() {
		//当前时间。
		 Calendar cal = Calendar.getInstance();
		 int c_h =  cal.get(Calendar.HOUR_OF_DAY);
		 int c_m = cal.get(Calendar.MINUTE);
		 int c_s = cal.get(Calendar.SECOND);
		 if( c_h == 0 &&  c_m == 0 && c_s >=0 && c_s<= 30 ) {
			 //1
			 for(FixTimeStageServiceWrapper wrapper : listfixtimewarpper) {
				 //如果今天已经清空过。无需清空。
				 if( wrapper.isExecutedoay() == false )continue;
				 wrapper.setExecutedoay(false);
			 }
		 }
	}
	private void loadBean(ApplicationContext cxt) {
		//1 固定时间段的Bean。
		 Map<String,FixTimeStageService> map = cxt.getBeansOfType( FixTimeStageService.class );
		 map.forEach((String key, FixTimeStageService u)->{
			 TimeStage t = u.getClass().getDeclaredAnnotation(TimeStage.class);
			 if(t == null ) {
				 loger.error(u.getClass()+"的execute方法缺少TimeStage注解");
				 return;
			 }
			 FixTimeStageServiceWrapper wrapper  = new FixTimeStageServiceWrapper();
			 wrapper.setServiceInstance( u );
			 //解析时间。
			 String time = t.value();//02:22~22:00
			 StringTokenizer stk = new StringTokenizer(time,":~ ");
			 wrapper.setStart_h(Integer.valueOf( stk.nextToken() ));
			 wrapper.setStart_min(Integer.valueOf( stk.nextToken() ) );
			 listfixtimewarpper.add( wrapper);
		 });
		 //2 固定时间频率。
		 
	}
	
	 
	/**
	 * 固定时间段定时器。
	 */
	protected void fixTime() {
		 //判断当前时间是否在指定时间内。
		//1 在Spring启动后，加载某些接口的子类。
		//2 获取子类设置的时间。
		//1~2在loadBean方法内完成的。
		
		//3 如果当前时间在指定的时间内。
		 //当前时间。
		 Calendar cal = Calendar.getInstance();
		 int c_h =  cal.get(Calendar.HOUR_OF_DAY);
		 int c_m = cal.get(Calendar.MINUTE);
		 for(FixTimeStageServiceWrapper wrapper : listfixtimewarpper) {
			 //如果今天已经执行过了。则不执行第二遍。
			 if( wrapper.isExecutedoay() ) {
				 continue;
			 }
			 if( c_h == wrapper.getStart_h() && 
				 c_m ==wrapper.getStart_min() ) {
					//4 执行子类的方法。
				 wrapper.setExecutedoay(true);
				 try {
					 wrapper.getServiceInstance().execute();
				} catch (Exception e) {
					 // 记录日记。
					loger.error("错误发生了："+e.getMessage());
				} 
			 }
		 }
	
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext cxt = event.getApplicationContext();
		init( cxt );
	}
 
}

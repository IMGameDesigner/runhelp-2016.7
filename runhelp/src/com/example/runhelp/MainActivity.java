package com.example.runhelp;

import java.util.HashMap;

import com.example.runhelp.MainActivity;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	SoundPool soundpool;
	HashMap<Integer,Integer>soundmap=new HashMap<Integer,Integer>();
ImageView pet,feed,inbutton,buybutton,outbutton,goods1,goods2,goods3,goods4,goods5,goods6;
ImageView p1,p2,p3,p4,cunliang,chengzhang,jie,tiao1,tiao2,tiao3,beginrun;
TextView feedtxt,notetxt,testtxt,bencimoneytxt;
Handler handler;SensorManager  sm;
DBConnection helper;
int caser,casert;
int eatmoney;
int x0;
int time=0,gpst=30000;
double xx,yy,zz,aa;int shuju[]=new int[151],a_t=30000,a_h;//���ٶȴ�����
int w,h;
int bencimoney=0;
int money=200,year=1,full=1400,feedzi=250,grow=0;//new year full=0,full maybe<0, full>some-> year++;
long  gpsx1=0,gpsx2=0,gpsy1=0,gpsy2=0,gpsv,gpssum;
android.view.ViewGroup.LayoutParams lp; //java����imageView��ߵ�һ��
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
    	helper = new DBConnection(this);
        final SQLiteDatabase db = helper.getWritableDatabase();
        //===============��ʾ����
        soundpool=new SoundPool(4,AudioManager.STREAM_SYSTEM,0);
        soundmap.put(1,soundpool.load(this,R.raw.pet,1));
        soundmap.put(2,soundpool.load(this,R.raw.shop,1));
        soundmap.put(3,soundpool.load(this,R.raw.run,1));
        soundmap.put(4,soundpool.load(this,R.raw.note,1));
		//FROM����д��new String[] {"user_name"��......}
Cursor c = db.query("Users", new String[] {"content"}, "name='yet'", null, null, null, null);
//===========.query(=�����=�������󷵻ص��еı��⣬��ѯ����========================��null,null=,����= ����������);
      if(c.getCount()==0)//��һ�Σ���ʼ����
      {
			ContentValues values = new ContentValues();//һ�����̶�ζ������renameֱ�����ܻ����ܶ�����
			
			values.put("name","yet");
			values.put("content", "ooo");
			db.insert("Users", null, values);
			
			values.put("name","feedzi");
			values.put("content",String.valueOf(feedzi));
			db.insert("Users", null, values);
			values.put("name","grow");
			values.put("content",String.valueOf(grow));
			db.insert("Users", null, values);
			values.put("name","year");
			values.put("content",String.valueOf(year));
			db.insert("Users", null, values);
			values.put("name","full");
			values.put("content",String.valueOf(full));
			db.insert("Users", null, values);
			values.put("name","money");
			values.put("content",String.valueOf(money));
			db.insert("Users", null, values);
			
			Time t=new Time(); 
			//�����ڲ�
			t.setToNow(); // ȡ��ϵͳʱ�䡣
							int nowyear = t.year;
							int nowmonth = t.month+1;
							int nowday = t.monthDay;
							int nowhour = t.hour;    // 0-23
							int nowmin=t.minute;
							int nows=t.second;
							
							values.put("name","tyear");
							values.put("content",String.valueOf(nowyear));
							db.insert("Users", null, values);			
							values.put("name","tmonth");
							values.put("content",String.valueOf(nowmonth));
							db.insert("Users", null, values);
							values.put("name","tday");
							values.put("content",String.valueOf(nowday));
							db.insert("Users", null, values);
							values.put("name","thour");
							values.put("content",String.valueOf(nowhour));
							db.insert("Users", null, values);
							values.put("name","tmin");
							values.put("content",String.valueOf(nowmin));
							db.insert("Users", null, values);
							values.put("name","ts");
							values.put("content",String.valueOf(nows));
							db.insert("Users", null, values);
      }else//���״Σ���ǰʱ���������
      {
 c = db.query("Users",new String[] {"content"}, "name='" + "tyear" + "'", null, null, null, null);
c.moveToFirst();
int bfyear =Integer.parseInt(c.getString(0)) ;
c = db.query("Users",new String[] {"content"}, "name='" + "tmonth" + "'", null, null, null, null);
c.moveToFirst();
int bfmonth =Integer.parseInt(c.getString(0)) ;
c = db.query("Users",new String[] {"content"}, "name='" + "tday" + "'", null, null, null, null);
c.moveToFirst();
int bfday =Integer.parseInt(c.getString(0)) ;
c = db.query("Users",new String[] {"content"}, "name='" + "thour" + "'", null, null, null, null);
c.moveToFirst();
int bfhour =Integer.parseInt(c.getString(0)) ;
c = db.query("Users",new String[] {"content"}, "name='" + "tmin" + "'", null, null, null, null);
c.moveToFirst();
int bfmin =Integer.parseInt(c.getString(0)) ;
c = db.query("Users",new String[] {"content"}, "name='" + "ts" + "'", null, null, null, null);
c.moveToFirst();
int bfs =Integer.parseInt(c.getString(0)) ;
			
			Time t=new Time(); 
			//�����ڲ�
			t.setToNow(); // ȡ��ϵͳʱ�䡣
							int nowyear = t.year;
							int nowmonth = t.month+1;
							int nowday = t.monthDay;
							int nowhour = t.hour;    // 0-23
							int nowmin=t.minute;
							int nows=t.second;
							
		 eatmoney=(Math.abs(bfyear-nowyear)*31104000+
				Math.abs(bfmonth-nowmonth)*2592000+
				Math.abs(bfday-nowday)*86400+
				Math.abs(bfhour-nowhour)*3600+
				Math.abs(bfmin-nowmin)*60+
				Math.abs(bfs-nows))/720;
      
		//�������ݣ�
      c = db.query("Users",new String[] {"content"}, "name='" + "feedzi" + "'", null, null, null, null);
      c.moveToFirst();
      feedzi =Integer.parseInt(c.getString(0)) ;
      c = db.query("Users",new String[] {"content"}, "name='" + "grow" + "'", null, null, null, null);
      c.moveToFirst();
      grow =Integer.parseInt(c.getString(0)) ;
      c = db.query("Users",new String[] {"content"}, "name='" + "year" + "'", null, null, null, null);
      c.moveToFirst();
      year =Integer.parseInt(c.getString(0)) ;
      c = db.query("Users",new String[] {"content"}, "name='" + "full" + "'", null, null, null, null);
      c.moveToFirst();
      full =Integer.parseInt(c.getString(0)) ;
      c = db.query("Users",new String[] {"content"}, "name='" + "money" + "'", null, null, null, null);
      c.moveToFirst();
      money =Integer.parseInt(c.getString(0)) ;
        //��������ݣ�
        if(eatmoney<=feedzi)
        {
        	feedzi-=eatmoney;
        }else
        {
        	eatmoney-=feedzi;
        	feedzi=0;
        	full-=eatmoney;
        	if(full<0)full=0;
        }
      }
        //====================================��ȡ��Ļ�ֱ���
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);        
         w = dm.widthPixels;//���
         h = dm.heightPixels ;//�߶�
        //===============================]
        
        pet=(ImageView)findViewById(R.id.pet);
        feed=(ImageView)findViewById(R.id.feed);
        inbutton=(ImageView)findViewById(R.id.inbutton);
        buybutton=(ImageView)findViewById(R.id.buybutton);
        outbutton=(ImageView)findViewById(R.id.outbutton);
        feedtxt=(TextView)findViewById(R.id.feedtxt);
        notetxt=(TextView)findViewById(R.id.notetxt);
        testtxt=(TextView)findViewById(R.id.testtxt);
        bencimoneytxt=(TextView)findViewById(R.id.bencimoneytxt);
        goods1=(ImageView)findViewById(R.id.goods1);
        goods2=(ImageView)findViewById(R.id.goods2);
        goods3=(ImageView)findViewById(R.id.goods3);
        goods4=(ImageView)findViewById(R.id.goods4);
        goods5=(ImageView)findViewById(R.id.goods5);
        goods6=(ImageView)findViewById(R.id.goods6);
        p1=(ImageView)findViewById(R.id.p1);
        p2=(ImageView)findViewById(R.id.p2);
        p3=(ImageView)findViewById(R.id.p3);
        p4=(ImageView)findViewById(R.id.p4);
        cunliang=(ImageView)findViewById(R.id.cunliang);
        chengzhang=(ImageView)findViewById(R.id.chengzhang);
        jie=(ImageView)findViewById(R.id.jie);
        tiao3=(ImageView)findViewById(R.id.tiao3);
        tiao2=(ImageView)findViewById(R.id.tiao2);
        tiao1=(ImageView)findViewById(R.id.tiao1);
        beginrun=(ImageView)findViewById(R.id.begin);
        
        pet.setScaleType(ImageView.ScaleType.FIT_XY);
        feed.setScaleType(ImageView.ScaleType.FIT_XY);
        inbutton.setScaleType(ImageView.ScaleType.FIT_XY);
        buybutton.setScaleType(ImageView.ScaleType.FIT_XY);
        outbutton.setScaleType(ImageView.ScaleType.FIT_XY);
        goods1.setScaleType(ImageView.ScaleType.FIT_XY);
        goods2.setScaleType(ImageView.ScaleType.FIT_XY);
        goods3.setScaleType(ImageView.ScaleType.FIT_XY);
        goods4.setScaleType(ImageView.ScaleType.FIT_XY);
        goods5.setScaleType(ImageView.ScaleType.FIT_XY);
        goods6.setScaleType(ImageView.ScaleType.FIT_XY);
        p1.setScaleType(ImageView.ScaleType.FIT_XY);
        p2.setScaleType(ImageView.ScaleType.FIT_XY);
        p3.setScaleType(ImageView.ScaleType.FIT_XY);
        p4.setScaleType(ImageView.ScaleType.FIT_XY);
        cunliang.setScaleType(ImageView.ScaleType.FIT_XY);
        chengzhang.setScaleType(ImageView.ScaleType.FIT_XY);
        jie.setScaleType(ImageView.ScaleType.FIT_XY);
        tiao3.setScaleType(ImageView.ScaleType.FIT_XY);
        tiao2.setScaleType(ImageView.ScaleType.FIT_XY);
        tiao1.setScaleType(ImageView.ScaleType.FIT_XY);
        beginrun.setScaleType(ImageView.ScaleType.FIT_XY);
        
        pet.setVisibility(View.INVISIBLE);
        feed.setVisibility(View.INVISIBLE);
        inbutton.setVisibility(View.INVISIBLE);
        buybutton.setVisibility(View.INVISIBLE);
        outbutton.setVisibility(View.INVISIBLE);
        feedtxt.setVisibility(View.INVISIBLE);
        goods1.setVisibility(View.INVISIBLE);
        goods2.setVisibility(View.INVISIBLE);
        goods3.setVisibility(View.INVISIBLE);
        goods4.setVisibility(View.INVISIBLE);
        goods5.setVisibility(View.INVISIBLE);
        goods6.setVisibility(View.INVISIBLE);
        p1.setVisibility(View.INVISIBLE);
        p2.setVisibility(View.INVISIBLE);
        p3.setVisibility(View.INVISIBLE);
        p4.setVisibility(View.INVISIBLE);
        cunliang.setVisibility(View.INVISIBLE);
        chengzhang.setVisibility(View.INVISIBLE);
        tiao3.setVisibility(View.INVISIBLE);
        tiao2.setVisibility(View.INVISIBLE);
        tiao1.setVisibility(View.INVISIBLE);
        jie.setVisibility(View.INVISIBLE);
        notetxt.setVisibility(View.INVISIBLE);
        testtxt.setVisibility(View.INVISIBLE);
        beginrun.setVisibility(View.INVISIBLE);
        bencimoneytxt.setVisibility(View.INVISIBLE);
        
      //==================��java������imageView�Ŀ��        	
    	lp=inbutton.getLayoutParams();
    	lp.width=w/3;
    	lp.height=w/9; 
    	inbutton.setLayoutParams(lp);
      //===========================================��
        inbutton.setX(2*w/3-w/20);
        inbutton.setY(h-w/9-w/20);
        
      //==================��java������imageView�Ŀ��        	
    	lp=buybutton.getLayoutParams();
    	lp.width=w/3;
    	lp.height=w/9; 
    	buybutton.setLayoutParams(lp);
      //===========================================��
        buybutton.setX(w/20);
        buybutton.setY(h-w/9-w/20);
        
      //==================��java������imageView�Ŀ��        	
    	lp=outbutton.getLayoutParams();
    	lp.width=w/3;
    	lp.height=w/9; 
    	outbutton.setLayoutParams(lp);
      //===========================================��
        outbutton.setX(2*w/3-w/20);
        outbutton.setY(h-w/9-w/20);
        
        //==================��java������imageView�Ŀ��        	
      	lp=pet.getLayoutParams();
      	lp.width=w/2;
      	lp.height=w/2; 
      	pet.setLayoutParams(lp);
        //===========================================��
          pet.setX(0);
          pet.setY(0);        
    	
        //==================��java������imageView�Ŀ��        	
        	lp=feed.getLayoutParams();
        	lp.width=w/3;
        	lp.height=w/3; 
        	feed.setLayoutParams(lp);
          //===========================================��
            feed.setX(w/2);
            feed.setY(w/5); 
            
          //==================��java������imageView�Ŀ��        	
        	lp=goods1.getLayoutParams();
        	lp.width=w*4/15;
        	lp.height=w*4/15; 
        	goods1.setLayoutParams(lp);
          //===========================================��
            goods1.setX(w/15);
            goods1.setY(w/15);    
            
          //==================��java������imageView�Ŀ��        	
        	lp=goods2.getLayoutParams();
        	lp.width=w*4/15;
        	lp.height=w*4/15; 
        	goods2.setLayoutParams(lp);
          //===========================================��
            goods2.setX(w/15+w/3);
            goods2.setY(w/15);  
            
          //==================��java������imageView�Ŀ��        	
        	lp=goods3.getLayoutParams();
        	lp.width=w*4/15;
        	lp.height=w*4/15; 
        	goods3.setLayoutParams(lp);
          //===========================================��
            goods3.setX(w/15+w*2/3);
            goods3.setY(w/15);  
            
          //==================��java������imageView�Ŀ��        	
        	lp=goods4.getLayoutParams();
        	lp.width=w*4/15;
        	lp.height=w*4/15; 
        	goods4.setLayoutParams(lp);
          //===========================================��
            goods4.setX(w/15);
            goods4.setY(w/15+w/3);  
            
          //==================��java������imageView�Ŀ��        	
        	lp=goods5.getLayoutParams();
        	lp.width=w*4/15;
        	lp.height=w*4/15; 
        	goods5.setLayoutParams(lp);
          //===========================================��
            goods5.setX(w/15+w/3);
            goods5.setY(w/15+w/3);  
            
          //==================��java������imageView�Ŀ��        	
        	lp=goods6.getLayoutParams();
        	lp.width=w*4/15;
        	lp.height=w*4/15; 
        	goods6.setLayoutParams(lp);
          //===========================================��
            goods6.setX(w/15+w*2/3);
            goods6.setY(w/15+w/3);  
            
          //==================��java������imageView�Ŀ��        	
        	lp=p1.getLayoutParams();
        	lp.width=w/5;
        	lp.height=w/5; 
        	p1.setLayoutParams(lp);
          //===========================================��
            p1.setX(w*0/4);
            p1.setY(h-w/5);  
            
          //==================��java������imageView�Ŀ��        	
        	lp=p2.getLayoutParams();
        	lp.width=w/5;
        	lp.height=w/5; 
        	p2.setLayoutParams(lp);
          //===========================================��
            p2.setX(w*1/4);
            p2.setY(h-w/5); 
            
          //==================��java������imageView�Ŀ��        	
        	lp=p3.getLayoutParams();
        	lp.width=w/5;
        	lp.height=w/5; 
        	p3.setLayoutParams(lp);
          //===========================================��
            p3.setX(w*2/4);
            p3.setY(h-w/5); 
            
          //==================��java������imageView�Ŀ��        	
        	lp=p4.getLayoutParams();
        	lp.width=w/5;
        	lp.height=w/5; 
        	p4.setLayoutParams(lp);
          //===========================================��
            p4.setX(w*3/4);
            p4.setY(h-w/5); 
            
          //==================��java������imageView�Ŀ��        	
        	lp=cunliang.getLayoutParams();
        	lp.width=w;
        	lp.height=w/6; 
        	cunliang.setLayoutParams(lp);
          //===========================================��
            cunliang.setX(0);
            cunliang.setY(w/2);   
            
          //==================��java������imageView�Ŀ��        	
        	lp=chengzhang.getLayoutParams();
        	lp.width=w;
        	lp.height=w/6; 
        	chengzhang.setLayoutParams(lp);
          //===========================================��
            chengzhang.setX(0);
            chengzhang.setY(w/2+w/6); 
            
          //==================��java������imageView�Ŀ��        	
        	lp=jie.getLayoutParams();
        	lp.width=w;
        	lp.height=w/6; 
        	jie.setLayoutParams(lp);
          //===========================================��
            jie.setX(0);
            jie.setY(w/2+w/3); 
            
          //==================��java������imageView�Ŀ��        	
        	lp=tiao1.getLayoutParams();
        	lp.width=w*21/32;
        	lp.height=w/10; 
        	tiao1.setLayoutParams(lp);
          //===========================================��
            tiao1.setX(w*3/10);
            tiao1.setY(w/2+w/20-w/60); 
            
          //==================��java������imageView�Ŀ��        	
        	lp=tiao2.getLayoutParams();
        	lp.width=w*21/32;
        	lp.height=w/10; 
        	tiao2.setLayoutParams(lp);
          //===========================================��
            tiao2.setX(w*3/10);
            tiao2.setY(w/2+w/6+w/20-w/60); 
            
          //==================��java������imageView�Ŀ��        	
        	lp=tiao3.getLayoutParams();
        	lp.width=w*21/32;
        	lp.height=w/10; 
        	tiao3.setLayoutParams(lp);
          //===========================================��
            tiao3.setX(w*3/10);
            tiao3.setY(w/2+w/3+w/20-w/60); 
 
          //==================��java������imageView�Ŀ��        	
        	lp=notetxt.getLayoutParams();
        	lp.width=w;
        	lp.height=h; 
        	notetxt.setLayoutParams(lp);
          //===========================================��
            notetxt.setX(0);
            notetxt.setY(0);
            notetxt.setText("����һֻ�������˼���ܲ��ĳ��^-^����GPS������Ϊ����GPS��λ��" +
            		"�������ܲ���ȡ��Ǯ���Ϳ��Ը��ҹ���ʳ����\n\n" +
            		"��ֻ�в�����ʱ���Ż�ɳ�\n\n" +
            		"��ʹ���˹ر����ң���Ҳ������Զ�����\n\n"+
            		"ÿ�����˴�Լ��20���Ӳ��������ң���Լһ���º��ҾͿ��Դӵ��з���\n\n"+
            		"�ҿ����ü��ٶȺ�GPS�����������ж������Ƿ����ܲ�\n\n"+
            		"��������������������"
            		);
            notetxt.setText("run outdoor"+
            		"I will eat when I close " +
            		"I have 3 heart and 3 age " +
            		"I will eat 20min a day  " +
            		"I will became biger every month ");
            notetxt.setTextColor(Color.parseColor("#FF6600"));
            
          //==================��java������imageView�Ŀ��        	
        	lp=beginrun.getLayoutParams();
        	lp.width=w/3;
        	lp.height=w/9; 
        	beginrun.setLayoutParams(lp);
          //===========================================��
            beginrun.setX(w/2-w/6);
            beginrun.setY(w/20); 
            
          //==================��java������imageView�Ŀ��        	
        	lp=testtxt.getLayoutParams();
        	lp.width=w;
        	lp.height=h; 
        	testtxt.setLayoutParams(lp);
          //===========================================��
            testtxt.setX(0);
            testtxt.setY(w/20); 
            testtxt.setText("�ܲ�ǰ�����˴�GPS����Ȼ�����ý��Ŷ.��Ҫ����������һ���������Ҳ��ܼ�⵽��");
            testtxt.setText("open GPS and run > 1min");
            testtxt.setTextColor(Color.parseColor("#FF6600"));
            
          //==================��java������imageView�Ŀ��        	
        	lp=bencimoneytxt.getLayoutParams();
        	lp.width=w;
        	lp.height=h; 
        	bencimoneytxt.setLayoutParams(lp);
          //===========================================��
            bencimoneytxt.setX(0);
            bencimoneytxt.setY(w/15); 
            bencimoneytxt.setText("0");
            bencimoneytxt.setTextSize(150);//�߶ȣ�h/18ʧ�أ�->30
            bencimoneytxt.setTextColor(Color.parseColor("#FF6600"));
            
        opencaser1();
        
        inbutton.setOnClickListener(new OnClickListener(){
  		   public void onClick(View v){
              closecaserall();
              opencaser2();
 		   }
 	   });

        buybutton.setOnClickListener(new OnClickListener(){
   		   public void onClick(View v){

  		   }
  	   });
        
        outbutton.setOnClickListener(new OnClickListener(){
   		   public void onClick(View v){
               closecaserall();
               opencaser1();
  		   }
  	   });
        
        goods1.setOnClickListener(new OnClickListener(){
    		   public void onClick(View v){
    			   if(money>=5){
    			  Toast.makeText(MainActivity.this, "buy success", Toast.LENGTH_SHORT).show();
                  money-=5;
                  feedzi+=5;changedata();}
    			   else 
    			Toast.makeText(MainActivity.this, "buy fail", Toast.LENGTH_SHORT).show();
   		   }
   	   });
        
        goods2.setOnClickListener(new OnClickListener(){
 		   public void onClick(View v){
 			  if(money>=20){
 				 Toast.makeText(MainActivity.this, "buy success", Toast.LENGTH_SHORT).show();
               money-=20;
               feedzi+=21;changedata();}
 			 else 
     			Toast.makeText(MainActivity.this, "buy fail", Toast.LENGTH_SHORT).show();
		   }
	   });
        
        goods3.setOnClickListener(new OnClickListener(){
 		   public void onClick(View v){
 			  if(money>=50){
 				 Toast.makeText(MainActivity.this, "buy 53 success", Toast.LENGTH_SHORT).show();
               money-=50;
               feedzi+=53;changedata();}
 			 else 
     			Toast.makeText(MainActivity.this, "buy fail", Toast.LENGTH_SHORT).show();
		   }
	   });
        
        goods4.setOnClickListener(new OnClickListener(){
 		   public void onClick(View v){
 			  if(money>=100){
 				 Toast.makeText(MainActivity.this, "buy 108 success", Toast.LENGTH_SHORT).show();
               money-=100;
               feedzi+=108;changedata();}
 			 else 
     			Toast.makeText(MainActivity.this, "buy fail", Toast.LENGTH_SHORT).show();
		   }
	   });
        
        goods5.setOnClickListener(new OnClickListener(){
 		   public void onClick(View v){
 			  if(money>=500){
 				 Toast.makeText(MainActivity.this, "buy 550 success", Toast.LENGTH_SHORT).show();
               money-=500;
               feedzi+=550;changedata();}
 			 else 
     			Toast.makeText(MainActivity.this, "buy fail", Toast.LENGTH_SHORT).show();
		   }
	   });
        
        goods6.setOnClickListener(new OnClickListener(){
 		   public void onClick(View v){
 			  if(money>=1000){
 				 Toast.makeText(MainActivity.this, "buy 1150 success", Toast.LENGTH_SHORT).show();
               money-=1000;
               feedzi+=1150;
               changedata();}
 			 else 
     			Toast.makeText(MainActivity.this, "buy fail", Toast.LENGTH_SHORT).show();
		   }
	   });
        
        p1.setOnClickListener(new OnClickListener(){
  		   public void onClick(View v){
  			 soundpool.play(soundmap.get(1),1,1,0,0,1);
                closecaserall();
                opencaser1();
 		   }
 	   });
        
        p2.setOnClickListener(new OnClickListener(){
   		   public void onClick(View v){
   			soundpool.play(soundmap.get(2),1,1,0,0,1);
                 closecaserall();
                 opencaser2();
  		   }
  	   });
        
        p3.setOnClickListener(new OnClickListener(){
   		   public void onClick(View v){
   			soundpool.play(soundmap.get(3),1,1,0,0,1);
                 closecaserall();
                 opencaser3();
  		   }
  	   });
        
        p4.setOnClickListener(new OnClickListener(){
   		   public void onClick(View v){
   			soundpool.play(soundmap.get(4),1,1,0,0,1);
                 closecaserall();
                 opencaser4();
  		   }
  	   });
        
        beginrun.setOnClickListener(new OnClickListener(){
    		   public void onClick(View v){       
                  //GPS���ݳ�ʼ��:
                  gpssum=0;
                  gpst=0;
                  //���ٶȴ�������ʼ����
                  a_t=0;
                  int j;
                  for(j=98;j<=130;j++)shuju[j]=0;
   		   }
   	   });
        handler=new Handler(){
    		@Override
        	public void handleMessage(Message msg){
        		if(msg.what==0x101){
        			
        			


        			//+++++���ٶȴ�������
            			a_t++;if(a_t>100000000)a_t=10000000;
            		   if(a_t>5/*(60*1/3)==1/3min*/*100)//5s��ʼ��¼
            			   if((int) Math.round(aa*10)<=150&&(int)
            			   Math.round(aa*10)>=0)shuju[(int) Math.round(aa*10)]++;
                       if(a_t==25*1/*1min*/*100){//25s������¼
                    	   int j; 
                    	   a_h=0;
                    	   for(j=98;j<=130;j++)if(shuju[j]>45)a_h=1;
                       }
                       if(a_t==30*100){//30s���߲�����
                            
                       }
        			//+++++GPS��
        			//if(gpst<30*100)testtxt.setText("���ڼ�����˵��ܲ���ʣ��ʱ�䣺"+(30-gpst/100));
        			gpst++;if(gpst>1000000)gpst=100000;
         		   if(gpst==5*100)
         		   {
         			   //writeTxtToFile("============һ������==========", filePath, fileName);
         		   }
                    if(gpst%100==0&&gpst>=5*100&&gpst<=25*100){
                 	   //:cm/s
                 	   gpsv=
                 	   Math.round(Math.sqrt(
                 	   Math.abs(gpsx1-gpsx2)*Math.abs(gpsx1-gpsx2)+
                 	   Math.abs(gpsy1-gpsy2)*Math.abs(gpsy1-gpsy2)
                 	   ));
                 	   if(gpsv>0)gpssum++;
                        //writeTxtToFile("v:"+gpsv+" ;", filePath, fileName);//�ļ��������ỻ�����
                    }
                    if(gpst==30*100){
                 	   if(gpssum>=6&&a_h==0)//�˴�������ٶȴ��������ݣ�a��gps��tӦ����һ����
                 	   {
                 		   bencimoney+=3;
                 		  bencimoneytxt.setText(""+bencimoney);
                 		   testtxt.setText(""+bencimoney);
                 		   money+=3;
                 		   changedata();
                 	   }else ;
                 		  //testtxt.setText("���ʧ�ܣ����˱�����30��������ܲ��Żᱻ��⵽Ŷ"+gpssum);
                 	   updatetest();
                    }
                    if(gpst%100==0){
         			   gpsx2=gpsx1;
         			   gpsy2=gpsy1;
         		   }
        			//+++++++++++++���붯����
        			if(caser==1){
        				casert++;
        				if(casert<50){
        					pet.setX(-(50-casert)*w*(50-casert)/5000);
        				}
        			}
        			if(caser==2){
        				casert++;
        				if(casert<50){
        					
        					//==================��java������imageView�Ŀ��        	
        		        	lp=goods6.getLayoutParams();
        		        	lp.width=(int)(w*4*casert/15/50);
        		        	lp.height=(int)(w*4*casert/15/50); 
        		        	goods6.setLayoutParams(lp); 
        		          //===========================================��
        		            goods6.setX(w/15+w*2/3+w*4/30-w*4*casert/30/50);
        		            goods6.setY(w/15+w/3+w*4/30-w*4*casert/30/50);
        		            
        		          //==================��java������imageView�Ŀ��        	
        		        	lp=goods5.getLayoutParams();
        		        	lp.width=(int)(w*4*casert/15/50);
        		        	lp.height=(int)(w*4*casert/15/50); 
        		        	goods5.setLayoutParams(lp); 
        		          //===========================================��
        		            goods5.setX(w/15+w*1/3+w*4/30-w*4*casert/30/50);
        		            goods5.setY(w/15+w/3+w*4/30-w*4*casert/30/50);
        		            
        		          //==================��java������imageView�Ŀ��        	
        		        	lp=goods4.getLayoutParams();
        		        	lp.width=(int)(w*4*casert/15/50);
        		        	lp.height=(int)(w*4*casert/15/50); 
        		        	goods4.setLayoutParams(lp); 
        		          //===========================================��
        		            goods4.setX(w/15+w*0/3+w*4/30-w*4*casert/30/50);
        		            goods4.setY(w/15+w/3+w*4/30-w*4*casert/30/50);
        		            
        		          //==================��java������imageView�Ŀ��        	
        		        	lp=goods3.getLayoutParams();
        		        	lp.width=(int)(w*4*casert/15/50);
        		        	lp.height=(int)(w*4*casert/15/50); 
        		        	goods3.setLayoutParams(lp); 
        		          //===========================================��
        		            goods3.setX(w/15+w*2/3+w*4/30-w*4*casert/30/50);
        		            goods3.setY(w/15+w*4/30-w*4*casert/30/50);
        		            
        		          //==================��java������imageView�Ŀ��        	
        		        	lp=goods2.getLayoutParams();
        		        	lp.width=(int)(w*4*casert/15/50);
        		        	lp.height=(int)(w*4*casert/15/50); 
        		        	goods2.setLayoutParams(lp); 
        		          //===========================================��
        		            goods2.setX(w/15+w*1/3+w*4/30-w*4*casert/30/50);
        		            goods2.setY(w/15+w*4/30-w*4*casert/30/50);
        		            
        		          //==================��java������imageView�Ŀ��        	
        		        	lp=goods1.getLayoutParams();
        		        	lp.width=(int)(w*4*casert/15/50);
        		        	lp.height=(int)(w*4*casert/15/50); 
        		        	goods1.setLayoutParams(lp); 
        		          //===========================================��
        		            goods1.setX(w/15+w*0/3+w*4/30-w*4*casert/30/50);
        		            goods1.setY(w/15+w*4/30-w*4*casert/30/50);
        				}
        			}
        			//++++++++++++�������ͷ���ʵʱ�仯��
        			if(caser==1){
        				//==================��java������imageView�Ŀ��        	
        	        	lp=tiao1.getLayoutParams();
        	        	lp.width=w*21*feedzi/32/1500;
        	        	tiao1.setLayoutParams(lp);
        	        	lp=tiao2.getLayoutParams();
        	        	lp.width=w*21*grow/32/1500;
        	        	tiao2.setLayoutParams(lp);
        	        	lp=tiao3.getLayoutParams();
        	        	lp.width=w*21*full/32/1500;
        	        	tiao3.setLayoutParams(lp);
        	          //===========================================��
        	        	if(feedzi>500)feed.setImageResource(R.drawable.feed3);
        	        	if(feedzi<=500&&feedzi>0)feed.setImageResource(R.drawable.feed2);
        	        	if(feedzi==0)feed.setImageResource(R.drawable.feed1);
        			}
        			//++++++++++++ÿʱÿ�̣�
        			feedtxt.setText("    my money "+money);
        			//++++++++++++timer��������
                    time++;if(time>500*10000)time=1;
                    //++++++++++++�Զ�����12min��һ��������������+�ɳ���û��ʳ��-�����ȣ�
                    if(time%72000==0)
                    {
                    	if(feedzi>=1)
                    	{
                    		feedzi-=1;
                    		if(full<1500)full++;else if(grow<1500)grow++;
                    		if(grow>=1500&&year<3){grow=0;year++;}
                    	}
                    	else
                    	{
                    		if(full>=1)full--;
                    	}
                    }
                    //++++++++++++��������仯
                    if(year==1){
                    	if(feedzi>=200){
                    		if(time%500==125)pet.setImageResource(R.drawable.onehappy1);
                    		if(time%500==250)pet.setImageResource(R.drawable.onehappy1);
                    		if(time%500==375)pet.setImageResource(R.drawable.onehappy1);
                    		if(time%500==0)pet.setImageResource(R.drawable.onehappy2);
                    	}else
                    	if(feedzi<=0){
                    		if(time%500==125)pet.setImageResource(R.drawable.onesad1);
                    		if(time%500==250)pet.setImageResource(R.drawable.onesad1);
                    		if(time%500==375)pet.setImageResource(R.drawable.onesad1);
                    		if(time%500==0)pet.setImageResource(R.drawable.onesad2);
                    	}else
                    	if(feedzi>0&&feedzi<200){
                        	if(time%500==125)pet.setImageResource(R.drawable.onenormal1);
                        	if(time%500==250)pet.setImageResource(R.drawable.onenormal2);
                        	if(time%500==375)pet.setImageResource(R.drawable.onenormal1);
                        	if(time%500==0)pet.setImageResource(R.drawable.onenormal1);
                        }
                    }
                    
                    if(year==2){
                    	if(feedzi>=200){
                    		if(time%500==125)pet.setImageResource(R.drawable.twohappy1);
                    		if(time%500==250)pet.setImageResource(R.drawable.twohappy1);
                    		if(time%500==375)pet.setImageResource(R.drawable.twohappy1);
                    		if(time%500==0)pet.setImageResource(R.drawable.twohappy2);
                    	}else
                    	if(feedzi<=0){
                    		if(time%500==125)pet.setImageResource(R.drawable.twosad1);
                    		if(time%500==250)pet.setImageResource(R.drawable.twosad1);
                    		if(time%500==375)pet.setImageResource(R.drawable.twosad1);
                    		if(time%500==0)pet.setImageResource(R.drawable.twosad2);
                    	}else
                    	if(feedzi>0&&feedzi<200){
                        	if(time%500==125)pet.setImageResource(R.drawable.twonormal1);
                        	if(time%500==250)pet.setImageResource(R.drawable.twonormal2);
                        	if(time%500==375)pet.setImageResource(R.drawable.twonormal1);
                        	if(time%500==0)pet.setImageResource(R.drawable.twonormal1);
                        }
                    }
                    
                    if(year==3){
                    	if(feedzi>=200){
                    		if(time%500==125)pet.setImageResource(R.drawable.threehappy1);
                    		if(time%500==250)pet.setImageResource(R.drawable.threehappy1);
                    		if(time%500==375)pet.setImageResource(R.drawable.threehappy1);
                    		if(time%500==0)pet.setImageResource(R.drawable.threehappy2);
                    	}else
                    	if(feedzi<=0){
                    		if(time%500==125)pet.setImageResource(R.drawable.threesad1);
                    		if(time%500==250)pet.setImageResource(R.drawable.threesad1);
                    		if(time%500==375)pet.setImageResource(R.drawable.threesad1);
                    		if(time%500==0)pet.setImageResource(R.drawable.threesad2);
                    	}else
                    	if(feedzi>0&&feedzi<200){
                        	if(time%500==125)pet.setImageResource(R.drawable.threenormal1);
                        	if(time%500==250)pet.setImageResource(R.drawable.threenormal2);
                        	if(time%500==375)pet.setImageResource(R.drawable.threenormal1);
                        	if(time%500==0)pet.setImageResource(R.drawable.threenormal1);
                        }
                    }
                    //++++++++++++++++
        		}
        	super.handleMessage(msg);	
        	}
       };
    Thread t=new Thread(new Runnable(){
           	@Override
           	public void run(){
           		while(!Thread.currentThread().isInterrupted()){
           			Message m=handler.obtainMessage();
           			m.what=0x101;
           			//======write or no
           			handler.sendMessage(m);
           			try{Thread.sleep(10);}catch(InterruptedException e){e.printStackTrace();}
           		}
           	}
           });
           t.start();
           //=====GPS����������
           LocationManager locationManager;
           String serviceName=Context.LOCATION_SERVICE;
           locationManager=(LocationManager)getSystemService(serviceName);
           Criteria criteria=new Criteria();
           criteria.setAccuracy(Criteria.ACCURACY_FINE);
           criteria.setAltitudeRequired(false);
           criteria.setBearingRequired(false);
           criteria.setCostAllowed(true);
           criteria.setPowerRequirement(Criteria.POWER_LOW);
           String provider=locationManager.getBestProvider(criteria, true);
           
           Location location =locationManager.getLastKnownLocation(provider);
           updateWithNewLocation(location);
           //��һ����������һ����gps��network���֣��ڶ����Ǹ��µ�ʱ�䵥λ���룬
           //�������Ǹ��µľ��뵥λ���ף����ĸ���λ�÷���ļ���
           locationManager.requestLocationUpdates(provider, 1000, 5, locationListener);
           //=====GPS����������end
    }
    //====GPS��������
    private void updateWithNewLocation(Location location){
    	if(location!=null){
    		double lat=location.getLatitude();
    		double lng=location.getLongitude();
    		gpsx1=Math.round(10000000*lat);
    		gpsy1=Math.round(10000000*lng);
    	}else{
    		
    	}

    	
    }
private final LocationListener locationListener=new LocationListener(){
  	   public void onLocationChanged(Location location){
  		   updateWithNewLocation(location);
  		   //λ�øı������
  	   }
  	   public void onProviderDisabled(String provider){
  		   updateWithNewLocation(null);
  	   }
  	   public void onProviderEnabled(String provider){}
  	   public void onStatusChanged(String provider,int status,Bundle extras){}
    };
    //====GPS��������end
    //======���ٶȴ�����
    private SensorListener mySensorListener=new SensorListener(){
    	public void onAccuracyChanged(int sensor,int accuracy){};
        public void onSensorChanged(int sensor, float[] values) {
            if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
            	xx=values[0];
   			    yy=values[1];
   			    zz=values[2];
                aa=Math.sqrt(xx*xx+yy*yy+zz*zz);
            }   
        }
    };
protected void onResume(){
	sm.registerListener(
			mySensorListener,SensorManager.SENSOR_ACCELEROMETER,
			SensorManager.SENSOR_DELAY_UI
			);
	super.onResume();
}
   
   
    protected void onPause() {
        // unregister listener
        sm.unregisterListener(mySensorListener);
        super.onPause();
    }  

//==========���ٶȴ�����end
    //=====���ݿ�
    public static class DBConnection extends SQLiteOpenHelper {//SQLiteOpenHelper�ฺ�𱣳ֺ����ݿ����ӵ�ͨ��
    	//��SQLiteOpenHelper�ϵ���getWritableDatabase���ܵõ�SQLiteDatabase���ǿ���db.insert()��
		private static final String DATABASE_NAME = "ws";
		private static final int DATABASE_VERSION = 1;
		private DBConnection(Context ctx) {
			super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public void onCreate(SQLiteDatabase db) {//SQLiteDatabase �ฺ��������ݿ⣬���ɾ�����¼�����
			//������Table�ʹ�����ʼ����
			String sql = "CREATE TABLE " + "Users" + " (" 
			+ "_id"  + " INTEGER primary key autoincrement, " 
			+ "name" + " text not null, " 
			+ "content" + " text not null " + ");";
			//Log.i("haiyang:createDB=", sql);
			db.execSQL(sql);
		}
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub	
		}
	}

//=====���ݿ�end
    
void opencaser1(){
	caser=1;casert=0;x0=-w/2;
	
	pet.setVisibility(View.VISIBLE);
	feed.setVisibility(View.VISIBLE);
	p1.setVisibility(View.VISIBLE);
	p2.setVisibility(View.VISIBLE);
	p3.setVisibility(View.VISIBLE);
	p4.setVisibility(View.VISIBLE);
	cunliang.setVisibility(View.VISIBLE);
	jie.setVisibility(View.VISIBLE);
	chengzhang.setVisibility(View.VISIBLE);
	tiao1.setVisibility(View.VISIBLE);
	tiao2.setVisibility(View.VISIBLE);
	tiao3.setVisibility(View.VISIBLE);
	
	p1.setImageResource(R.drawable.pic1);
	p2.setImageResource(R.drawable.pi2);
	p3.setImageResource(R.drawable.pi3);
	p4.setImageResource(R.drawable.pi4);

	pet.setX(x0);
	
}
void closecaserall(){
	caser=0;
	
	pet.setVisibility(View.INVISIBLE);
    feed.setVisibility(View.INVISIBLE);
    inbutton.setVisibility(View.INVISIBLE);
    buybutton.setVisibility(View.INVISIBLE);
	outbutton.setVisibility(View.INVISIBLE);
    feedtxt.setVisibility(View.INVISIBLE);
    goods1.setVisibility(View.INVISIBLE);
    goods2.setVisibility(View.INVISIBLE);
    goods3.setVisibility(View.INVISIBLE);
    goods4.setVisibility(View.INVISIBLE);
    goods5.setVisibility(View.INVISIBLE);
    goods6.setVisibility(View.INVISIBLE);
    cunliang.setVisibility(View.INVISIBLE);
    chengzhang.setVisibility(View.INVISIBLE);
    tiao3.setVisibility(View.INVISIBLE);
    tiao2.setVisibility(View.INVISIBLE);
    tiao1.setVisibility(View.INVISIBLE);
    jie.setVisibility(View.INVISIBLE);
    notetxt.setVisibility(View.INVISIBLE);
    testtxt.setVisibility(View.INVISIBLE);
    beginrun.setVisibility(View.INVISIBLE);
    bencimoneytxt.setVisibility(View.INVISIBLE);
}
void opencaser2(){
	caser=2;casert=0;
	
	goods1.setVisibility(View.VISIBLE);
	goods2.setVisibility(View.VISIBLE);
	goods3.setVisibility(View.VISIBLE);
	goods4.setVisibility(View.VISIBLE);
	goods5.setVisibility(View.VISIBLE);
	goods6.setVisibility(View.VISIBLE);
	feedtxt.setVisibility(View.VISIBLE);
	
	p1.setImageResource(R.drawable.pi1);
	p2.setImageResource(R.drawable.pic2);
	p3.setImageResource(R.drawable.pi3);
	p4.setImageResource(R.drawable.pi4);
	
	feedtxt.setX(0);
    feedtxt.setY(w/15+w*2/3); 
    feedtxt.setTextColor(Color.parseColor("#000000"));
}

void opencaser3(){
	caser=3;
	
	//beginrun.setVisibility(View.VISIBLE);
	testtxt.setVisibility(View.VISIBLE);
	bencimoneytxt.setVisibility(View.VISIBLE);
	
	p1.setImageResource(R.drawable.pi1);
	p2.setImageResource(R.drawable.pi2);
	p3.setImageResource(R.drawable.pic3);
	p4.setImageResource(R.drawable.pi4);
	
	updatetest();
}
void opencaser4(){
	caser=4;
	
	notetxt.setVisibility(View.VISIBLE);
	
	p1.setImageResource(R.drawable.pi1);
	p2.setImageResource(R.drawable.pi2);
	p3.setImageResource(R.drawable.pi3);
	p4.setImageResource(R.drawable.pic4);
}
void changedata(){
	//+++++++���ݴ������ݿ�(�޸�)��
	helper = new DBConnection(this);
    final SQLiteDatabase db = helper.getWritableDatabase();
	ContentValues values = new ContentValues();
	values.put("name", "feedzi");
	values.put("content", String.valueOf(feedzi));
	db.update("Users", values, "name='feedzi'",null);
	values.put("name", "grow");
	values.put("content", String.valueOf(grow));
	db.update("Users", values, "name='grow'",null);
	values.put("name", "year");
	values.put("content", String.valueOf(year));
	db.update("Users", values, "name='year'",null);
	values.put("name", "full");
	values.put("content", String.valueOf(full));
	db.update("Users", values, "name='full'",null);
	values.put("name", "money");
	values.put("content", String.valueOf(money));
	db.update("Users", values, "name='money'",null);
	
	Time t=new Time(); 
	//�����ڲ�
	t.setToNow(); // ȡ��ϵͳʱ�䡣
					int nowyear = t.year;
					int nowmonth = t.month+1;
					int nowday = t.monthDay;
					int nowhour = t.hour;    // 0-23
					int nowmin=t.minute;
					int nows=t.second;
					
					values.put("name", "tyear");
					values.put("content", String.valueOf(nowyear));
					db.update("Users", values, "name='tyear'",null);
					values.put("name", "tmonth");
					values.put("content", String.valueOf(nowmonth));
					db.update("Users", values, "name='tmonth'",null);
					values.put("name", "tday");
					values.put("content", String.valueOf(nowday));
					db.update("Users", values, "name='tday'",null);
					values.put("name", "thour");
					values.put("content", String.valueOf(nowhour));
					db.update("Users", values, "name='thour'",null);
					values.put("name", "tmin");
					values.put("content", String.valueOf(nowmin));
					db.update("Users", values, "name='tmin'",null);
					values.put("name", "ts");
					values.put("content", String.valueOf(nows));
					db.update("Users", values, "name='ts'",null);
}
void updatetest(){
	//GPS���ݳ�ʼ��:
    gpssum=0;
    gpst=0;
    //���ٶȴ�������ʼ����
    a_t=0;
    int j;
    for(j=98;j<=130;j++)shuju[j]=0;
}
}

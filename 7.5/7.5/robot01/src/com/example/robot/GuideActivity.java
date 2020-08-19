package com.example.robot;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GuideActivity extends Activity implements OnPageChangeListener{

	private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;

    // �ײ�С��ͼƬ
    private ImageView[] dots;

    // ��¼��ǰѡ��λ��
    private int currentIndex;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_guide);
        initViews();
        initDots();
		
	}
	 private void initViews() {
	        LayoutInflater inflater = LayoutInflater.from(this);

	        views = new ArrayList<View>();
	        // ��ʼ������ͼƬ�б�
	        views.add(inflater.inflate(R.layout.what_new_one, null));
	        views.add(inflater.inflate(R.layout.what_new_two, null));
	        views.add(inflater.inflate(R.layout.what_new_three, null));

	        // ��ʼ��Adapter
	        vpAdapter = new ViewPagerAdapter(views, this);

	        vp = (ViewPager) findViewById(R.id.viewpager);
	        vp.setAdapter(vpAdapter);
	        // �����¼�
	        vp.setOnPageChangeListener(this);
	    }
	 private void initDots() {
	        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

	        dots = new ImageView[views.size()];

	        // ѭ��ȡ��С��ͼƬ
	        for (int i = 0; i < views.size(); i++) {
	            dots[i] = (ImageView) ll.getChildAt(i);
	            //dots[i].setEnabled(true);// ����Ϊ��ɫ
	            dots[i].setImageResource(R.drawable.huadong); 
	        }

	        currentIndex = 0;
	        //dots[currentIndex].setEnabled(false);// ����Ϊ��ɫ����ѡ��״̬
	        dots[currentIndex].setImageResource(R.drawable.huadong01);
	    }
	 private void setCurrentDot(int position) {
	        if (position < 0 || position > views.size() - 1
	                || currentIndex == position) {
	            return;
	        }

	        dots[position].setImageResource(R.drawable.huadong01);
	        dots[currentIndex].setImageResource(R.drawable.huadong);

	        currentIndex = position;
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void onPageSelected(int arg0) {
        // ���õײ�С��ѡ��״̬
        setCurrentDot(arg0);
    }

	 public void onPageScrollStateChanged(int arg0) {
	    }
	
	 public void onPageScrolled(int arg0, float arg1, int arg2) {
	    }
}

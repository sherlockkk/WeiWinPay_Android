package com.beautyblock.www;

import java.util.Random;

import net.sourceforge.simcpux.MD5;
import net.sourceforge.simcpux.WxPayUtile;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PayActivity extends Activity {

	TextView show;
	protected static Context mContext;
	public static Handler handler = new Handler(new Callback() {
		
//		msg.what== 0 ：表示支付成功
//		msg.what== -1 ：表示支付失败
//		msg.what== -2 ：表示取消支付
		@Override
		public boolean handleMessage(Message msg) {
			// TODO Auto-generated method stub
			
			switch (msg.what) {
			case 800://商户订单号重复或生成错误
				
				break;
			case 0://支付成功
				Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
				break;
			case -1://支付失败
				Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
				break;
			case -2://取消支付
				Toast.makeText(mContext, "取消支付", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
			return false;
		}
	});

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay);
		mContext = this;
		
		show = (TextView) findViewById(R.id.editText_prepay_id);
		// 生成prepay_id
		Button payBtn = (Button) findViewById(R.id.unifiedorder_btn);
		payBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				WxPayUtile.getInstance(PayActivity.this, "1",
						"http://121.40.35.3/test", "测试商品",
						genOutTradNo()).doPay();
			}
		});

	}
	
	private String genOutTradNo() {
		Random random = new Random();
		return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
	}
}

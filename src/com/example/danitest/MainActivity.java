package com.example.danitest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textview;
	private WebView webview;
	private LayoutInflater inflater;
	private LinearLayout container;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		container = (LinearLayout) findViewById(R.id.container);
		 textview = (TextView) inflater
				.inflate(R.layout.textview, null);
		// container.addView(textview);
		 webview = (WebView) inflater.inflate(R.layout.webview, null);
		container.addView(webview);
		webview.getSettings().setJavaScriptEnabled(true);

		// webView
		// .loadUrl("http://mditto.gsshop.com/ditto/search/main.gs?dittoseq=100002");
		webview.loadUrl("http://www.naver.com");
		webview.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				return (false);
			}
		});
		

	}

	public void flipLeft(View v) {
		applyRotation1(0, -90);
	}

	private void applyRotation1(float start, float end) {
		// Find the center of image
		final float centerX = webview.getWidth() / 2.0f;
		final float centerY = webview.getHeight() / 2.0f;

		// Create a new 3D rotation with the supplied parameter
		// The animation listener is used to trigger the next animation
		final Flip3dAnimation rotation = new Flip3dAnimation(start, end,
				centerX, centerY);
		rotation.setDuration(200);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView(this, false, null,
				webview));

		
		webview.startAnimation(rotation);

	}

	private void applyRotation2(float start, float end) {
		// Find the center of image
		final float centerX = webview.getWidth() / 2.0f;
		final float centerY = webview.getHeight() / 2.0f;

		// Create a new 3D rotation with the supplied parameter
		// The animation listener is used to trigger the next animation
		final Flip3dAnimation rotation = new Flip3dAnimation(start, end,
				centerX, centerY);
		rotation.setDuration(200);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView(this, true, textview,
				null));

		textview.startAnimation(rotation);
	}

	public void flipRight(View v) {
		applyRotation2(0, 90);
	}
}

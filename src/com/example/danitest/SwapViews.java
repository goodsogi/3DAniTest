package com.example.danitest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public final class SwapViews implements Runnable {
	private boolean isSecondView;
	TextView textview;
	WebView webview;
	private LinearLayout container;
	private LayoutInflater inflater;

	public SwapViews(Activity activity, boolean isSecondView, View textview,
			View webview) {
		container = (LinearLayout) activity.findViewById(R.id.container);
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.isSecondView = isSecondView;
	}

	public void run() {

		Flip3dAnimation rotation;

		if (isSecondView) {
			
			container.removeView(textview);
			webview = (WebView) inflater.inflate(R.layout.webview, null);
			webview.getSettings().setJavaScriptEnabled(true);

			// webView
			// .loadUrl("http://mditto.gsshop.com/ditto/search/main.gs?dittoseq=100002");
			webview.loadUrl("http://www.naver.com");
			webview.setWebViewClient(new WebViewClient() {
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					return (false);
				}
			});
			webview.requestFocus();
			container.addView(webview);
			final float centerX = webview.getWidth() / 2.0f;
			final float centerY = webview.getHeight() / 2.0f;
			rotation = new Flip3dAnimation(-90, 0, centerX, centerY);

		} else {

			
			container.removeView(webview);
			textview = (TextView) inflater.inflate(R.layout.textview, null);
			container.addView(textview);
			final float centerX = textview.getWidth() / 2.0f;
			final float centerY = textview.getHeight() / 2.0f;
			textview.requestFocus();
			rotation = new Flip3dAnimation(90, 0, centerX, centerY);

		}

		rotation.setDuration(500);
		rotation.setFillAfter(true);

		rotation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				if (isSecondView) {
					webview.clearAnimation();
				} else {
					textview.clearAnimation();
				}
			}
		});

		rotation.setInterpolator(new DecelerateInterpolator());

		if (isSecondView) {
			webview.startAnimation(rotation);
		} else {
			textview.startAnimation(rotation);
		}
	}
}
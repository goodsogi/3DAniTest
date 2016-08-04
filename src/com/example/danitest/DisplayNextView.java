package com.example.danitest;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;

public final class DisplayNextView implements Animation.AnimationListener {
	private boolean isSecondView;
	View textview;
	View webview;
	private Activity activity;

	public DisplayNextView(Activity activity, boolean isSecondView, View textview,
			View webview) {
		this.activity = activity;
		this.isSecondView = isSecondView;
		this.textview = textview;
		this.webview = webview;
	}

	public void onAnimationStart(Animation animation) {
	}

	public void onAnimationEnd(Animation animation) {
		if(!isSecondView) {
		webview.post(new SwapViews(activity, isSecondView, textview, webview));
		} else {
			textview.post(new SwapViews(activity, isSecondView, textview, webview));
		}
	}

	public void onAnimationRepeat(Animation animation) {
	}
}
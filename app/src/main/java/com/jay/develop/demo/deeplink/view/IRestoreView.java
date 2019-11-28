package com.jay.develop.demo.deeplink.view;

public interface IRestoreView {
	void onMobIdGot(String mobId);
	void onMobIdError(Throwable t);
}

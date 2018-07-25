package me.amao.easy.mvp.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import me.amao.easy.R;
import me.amao.easy.di.component.DaggerWebComponent;
import me.amao.easy.di.module.WebModule;
import me.amao.easy.mvp.contract.WebContract;
import me.amao.easy.mvp.presenter.WebPresenter;
import me.amao.easy.util.AppUtils;
import me.amao.easy.util.ToolbarUtil;
import timber.log.Timber;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class WebActivity extends BaseActivity<WebPresenter> implements WebContract.View {

    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.progress)
    ProgressBar progress;
    private boolean isLoadUrl;
    private WebChromeClient.CustomViewCallback customViewCallback;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerWebComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .webModule(new WebModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_web; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        String title = getIntent().getStringExtra("name");
        ToolbarUtil.setToolbar(this , title , 0 , true);

        AppUtils.setWebView(web);

        isLoadUrl = false;
        String url = getIntent().getStringExtra("url");
        //url = "https://www.baidu.com/";
        web.loadUrl(TextUtils.isEmpty(url) ? "about:blank" : url);

        Timber.d(TAG, "url = " + url);
        web.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) return false;

                try {
                    if (!(url.startsWith("http:")|| url.startsWith("https:"))) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return true;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
                }

                //处理http和https开头的url
                view.loadUrl(url);
                return true;
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (!isLoadUrl) {
                    isLoadUrl = true;
                    view.loadUrl(url);
                }

                super.onPageStarted(view, url, favicon);
            }

            /**
             * 页面载入完成回调
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (progress == null) {
                    return;
                }
                progress.setProgress(newProgress);
                progress.setVisibility(newProgress == 100 ? View.GONE : View.VISIBLE);
            }

            // 进入全屏的时候
            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                // 赋值给callback
                customViewCallback = callback;
                // 设置webView隐藏
                web.setVisibility(View.GONE);
                // 声明video，把之后的视频放到这里面去
                // 将video放到当前视图中
//                video.setVisibility(View.VISIBLE);
//                video.addView(view);
                // 横屏显示
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                // 设置全屏
                setFullScreen();
            }

            // 退出全屏的时候
            @Override
            public void onHideCustomView() {
                if (customViewCallback != null) {
                    // 隐藏掉
                    customViewCallback.onCustomViewHidden();
                }
                // 用户当前的首选方向
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
                // 退出全屏
                quitFullScreen();
                // 设置WebView可见
                web.setVisibility(View.VISIBLE);
//                video.setVisibility(View.GONE);
//                video.removeAllViews();

            }
        });

    }


    /**
     * 设置全屏
     */
    private void setFullScreen() {
        // 设置全屏的相关属性，获取当前的屏幕状态，然后设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 全屏下的状态码：1098974464
        // 窗口下的状态吗：1098973440
    }

    /**
     * 退出全屏
     */
    private void quitFullScreen() {
        // 声明当前屏幕状态的参数并获取
        final WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setAttributes(attrs);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (web.canGoBack()) {
                // goBack()表示返回WebView的上一页面
                web.goBack();
                //退出全屏
                quitFullScreen();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (web != null) {
            web.onPause();
            web.pauseTimers();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (web != null) {
            web.resumeTimers();
            web.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        if (web != null) {
            if (web.getParent() != null) {
                ((ViewGroup) web.getParent()).removeView(web);
            }
            web.clearHistory();
            web.clearCache(true);
            web.loadUrl("about:blank"); // clearView() should be changed to loadUrl("about:blank"), since clearView() is deprecated now
            web.freeMemory();
            //web.destroy();
            web = null; // Note that mWebView.destroy() and mWebView = null do the exact same thing
        }
        super.onDestroy();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }
}

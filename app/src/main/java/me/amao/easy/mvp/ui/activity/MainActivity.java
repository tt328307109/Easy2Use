package me.amao.easy.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.amao.easy.R;
import me.amao.easy.di.component.DaggerMainComponent;
import me.amao.easy.di.module.MainModule;
import me.amao.easy.mvp.contract.MainContract;
import me.amao.easy.mvp.model.entity.ItemBeans;
import me.amao.easy.mvp.presenter.MainPresenter;
import me.amao.easy.mvp.ui.adapter.CommonAdapter;
import me.amao.easy.mvp.ui.holder.CommonViewHolder;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_sp)
    RecyclerView rvSp;
    @BindView(R.id.rv_zb)
    RecyclerView rvZb;
    @BindView(R.id.rv_xw)
    RecyclerView rvXw;
    @BindView(R.id.rv_ly)
    RecyclerView rvLy;
    @BindView(R.id.rv_job)
    RecyclerView rvJob;
    @BindView(R.id.rv_tg)
    RecyclerView rvTg;
    @BindView(R.id.rv_wm)
    RecyclerView rvWm;
    @BindView(R.id.rv_map)
    RecyclerView rvMap;
    @BindView(R.id.rv_xx)
    RecyclerView rvXx;

    @BindView(R.id.rv_dsp)
    RecyclerView rvDsp;

    List<ItemBeans> spList = new ArrayList<>();
    List<ItemBeans> zbList = new ArrayList<>();
    List<ItemBeans> dspList = new ArrayList<>();
    List<ItemBeans> xwList = new ArrayList<>();
    List<ItemBeans> lyList = new ArrayList<>();
    List<ItemBeans> dtList = new ArrayList<>();
    List<ItemBeans> jobList = new ArrayList<>();
    List<ItemBeans> wmList = new ArrayList<>();
    List<ItemBeans> tgList = new ArrayList<>();
    List<ItemBeans> xxList = new ArrayList<>();


    CommonAdapter<ItemBeans> spAdapter;
    CommonAdapter<ItemBeans> zbAdapter;
    CommonAdapter<ItemBeans> dspAdapter;
    CommonAdapter<ItemBeans> xwAdapter;
    CommonAdapter<ItemBeans> lyAdapter;
    CommonAdapter<ItemBeans> dtAdapter;
    CommonAdapter<ItemBeans> jobAdapter;
    CommonAdapter<ItemBeans> wmAdapter;
    CommonAdapter<ItemBeans> tgAdapter;
    CommonAdapter<ItemBeans> xxAdapter;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        startData();
        initRecyler();
    }


    private void jumpTo(String name, int resId, String url) {
        Toast.makeText(this , "-->" + name + "-->" + resId + "-->" + url , Toast.LENGTH_SHORT).show();
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

    private void initRecyler() {
        GridLayoutManager layoutManager1 = new GridLayoutManager(this, 6);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 6);
        GridLayoutManager layoutManager3 = new GridLayoutManager(this, 6);
        GridLayoutManager layoutManager4 = new GridLayoutManager(this, 6);
        GridLayoutManager layoutManager5 = new GridLayoutManager(this, 6);
        GridLayoutManager layoutManager6 = new GridLayoutManager(this, 6);
        GridLayoutManager layoutManager7 = new GridLayoutManager(this, 6);
        GridLayoutManager layoutManager8 = new GridLayoutManager(this, 6);
        GridLayoutManager layoutManager9 = new GridLayoutManager(this, 6);
        GridLayoutManager layoutManager10 = new GridLayoutManager(this, 6);
        rvSp.setLayoutManager(layoutManager1);
        rvZb.setLayoutManager(layoutManager2);
        rvXw.setLayoutManager(layoutManager3);
        rvLy.setLayoutManager(layoutManager4);
        rvJob.setLayoutManager(layoutManager5);
        rvTg.setLayoutManager(layoutManager6);
        rvWm.setLayoutManager(layoutManager7);
        rvMap.setLayoutManager(layoutManager8);
        rvXx.setLayoutManager(layoutManager9);
        rvDsp.setLayoutManager(layoutManager10);

        spAdapter = new CommonAdapter<ItemBeans>(this , R.layout.layout_item , spList) {
            @Override
            public void convert(CommonViewHolder holder, ItemBeans itemBeans, int position) {
                holder.setText(R.id.item_tv , itemBeans.getName())
                        .setImageResource(R.id.item_iv , itemBeans.getResId())
                        .setOnClickListener(R.id.item_rl, v -> jumpTo(itemBeans.getName() , itemBeans.getResId() , itemBeans.getUrl()));
            }
        };

        zbAdapter = new CommonAdapter<ItemBeans>(this , R.layout.layout_item , zbList) {
            @Override
            public void convert(CommonViewHolder holder, ItemBeans itemBeans, int position) {
                holder.setText(R.id.item_tv , itemBeans.getName())
                        .setImageResource(R.id.item_iv , itemBeans.getResId())
                        .setOnClickListener(R.id.item_rl, v -> jumpTo(itemBeans.getName() , itemBeans.getResId() , itemBeans.getUrl()));
            }
        };

        dspAdapter = new CommonAdapter<ItemBeans>(this , R.layout.layout_item , dspList) {
            @Override
            public void convert(CommonViewHolder holder, ItemBeans itemBeans, int position) {
                holder.setText(R.id.item_tv , itemBeans.getName())
                        .setImageResource(R.id.item_iv , itemBeans.getResId())
                        .setOnClickListener(R.id.item_rl, v -> jumpTo(itemBeans.getName() , itemBeans.getResId() , itemBeans.getUrl()));
            }
        };

        xwAdapter = new CommonAdapter<ItemBeans>(this , R.layout.layout_item , xwList) {
            @Override
            public void convert(CommonViewHolder holder, ItemBeans itemBeans, int position) {
                holder.setText(R.id.item_tv , itemBeans.getName())
                        .setImageResource(R.id.item_iv , itemBeans.getResId())
                        .setOnClickListener(R.id.item_rl, v -> jumpTo(itemBeans.getName() , itemBeans.getResId() , itemBeans.getUrl()));
            }
        };

        lyAdapter = new CommonAdapter<ItemBeans>(this , R.layout.layout_item , lyList) {
            @Override
            public void convert(CommonViewHolder holder, ItemBeans itemBeans, int position) {
                holder.setText(R.id.item_tv , itemBeans.getName())
                        .setImageResource(R.id.item_iv , itemBeans.getResId())
                        .setOnClickListener(R.id.item_rl, v -> jumpTo(itemBeans.getName() , itemBeans.getResId() , itemBeans.getUrl()));
            }
        };

        dtAdapter = new CommonAdapter<ItemBeans>(this , R.layout.layout_item , dtList) {
            @Override
            public void convert(CommonViewHolder holder, ItemBeans itemBeans, int position) {
                holder.setText(R.id.item_tv , itemBeans.getName())
                        .setImageResource(R.id.item_iv , itemBeans.getResId())
                        .setOnClickListener(R.id.item_rl, v -> jumpTo(itemBeans.getName() , itemBeans.getResId() , itemBeans.getUrl()));
            }
        };

        jobAdapter = new CommonAdapter<ItemBeans>(this , R.layout.layout_item , jobList) {
            @Override
            public void convert(CommonViewHolder holder, ItemBeans itemBeans, int position) {
                holder.setText(R.id.item_tv , itemBeans.getName())
                        .setImageResource(R.id.item_iv , itemBeans.getResId())
                        .setOnClickListener(R.id.item_rl, v -> jumpTo(itemBeans.getName() , itemBeans.getResId() , itemBeans.getUrl()));
            }
        };

        wmAdapter = new CommonAdapter<ItemBeans>(this , R.layout.layout_item , wmList) {
            @Override
            public void convert(CommonViewHolder holder, ItemBeans itemBeans, int position) {
                holder.setText(R.id.item_tv , itemBeans.getName())
                        .setImageResource(R.id.item_iv , itemBeans.getResId())
                        .setOnClickListener(R.id.item_rl, v -> jumpTo(itemBeans.getName() , itemBeans.getResId() , itemBeans.getUrl()));
            }
        };

        tgAdapter = new CommonAdapter<ItemBeans>(this , R.layout.layout_item , tgList) {
            @Override
            public void convert(CommonViewHolder holder, ItemBeans itemBeans, int position) {
                holder.setText(R.id.item_tv , itemBeans.getName())
                        .setImageResource(R.id.item_iv , itemBeans.getResId())
                        .setOnClickListener(R.id.item_rl, v -> jumpTo(itemBeans.getName() , itemBeans.getResId() , itemBeans.getUrl()));
            }
        };

        xxAdapter = new CommonAdapter<ItemBeans>(this , R.layout.layout_item , xxList) {
            @Override
            public void convert(CommonViewHolder holder, ItemBeans itemBeans, int position) {
                holder.setText(R.id.item_tv , itemBeans.getName())
                        .setImageResource(R.id.item_iv , itemBeans.getResId())
                        .setOnClickListener(R.id.item_rl, v -> jumpTo(itemBeans.getName() , itemBeans.getResId() , itemBeans.getUrl()));
            }
        };

        rvSp.setAdapter(spAdapter);
        rvZb.setAdapter(zbAdapter);
        rvDsp.setAdapter(dspAdapter);
        rvXw.setAdapter(xwAdapter);
        rvLy.setAdapter(lyAdapter);
        rvMap.setAdapter(dtAdapter);
        rvJob.setAdapter(jobAdapter);
        rvWm.setAdapter(wmAdapter);
        rvTg.setAdapter(tgAdapter);
        rvXx.setAdapter(xxAdapter);
    }

    private void startData() {
        //视频
        ItemBeans beans1 = new ItemBeans("vod", "爱奇艺", R.mipmap.sp_aqy, "");
        ItemBeans beans2 = new ItemBeans("vod", "芒果tv", R.mipmap.sp_mgtv, "");
        ItemBeans beans3 = new ItemBeans("vod", "腾讯视频", R.mipmap.sp_txsp, "");
        ItemBeans beans4 = new ItemBeans("vod", "优酷视频", R.mipmap.sp_yksp, "");
        ItemBeans beans5 = new ItemBeans("vod", "哔哩哔哩", R.mipmap.sp_bilibili, "");
        ItemBeans beans6 = new ItemBeans("vod", "AcFun", R.mipmap.sp_acfun, "");
        spList.add(beans1);
        spList.add(beans2);
        spList.add(beans3);
        spList.add(beans4);
        spList.add(beans5);
        spList.add(beans6);
        //直播
        ItemBeans beant1 = new ItemBeans("timeshift", "斗鱼直播", R.mipmap.zb_dyzb, "");
        ItemBeans beant2 = new ItemBeans("timeshift", "虎牙直播", R.mipmap.zb_hyzb, "");
        ItemBeans beant3 = new ItemBeans("timeshift", "熊猫直播", R.mipmap.zb_xmzb, "");
        zbList.add(beant1);
        zbList.add(beant2);
        zbList.add(beant3);
        //短视频
        ItemBeans dsp1 = new ItemBeans("mv", "抖音", R.mipmap.dy, "");
        ItemBeans dsp2 = new ItemBeans("mv", "快手", R.mipmap.ks, "");
        dspList.add(dsp1);
        dspList.add(dsp2);

        //新闻
        ItemBeans xw1 = new ItemBeans("news", "今日头条", R.mipmap.xw_jrtt, "");
        ItemBeans xw2 = new ItemBeans("news", "网易新闻", R.mipmap.xw_wy, "");
        ItemBeans xw3 = new ItemBeans("news", "腾讯新闻", R.mipmap.xw_tx, "");
        ItemBeans xw4 = new ItemBeans("news", "天天快报", R.mipmap.xw_ttkb, "");
        ItemBeans xw5 = new ItemBeans("news", "搜狐新闻", R.mipmap.xw_sh, "");
        ItemBeans xw6 = new ItemBeans("news", "凤凰新闻", R.mipmap.xw_fh, "");
        ItemBeans xw7 = new ItemBeans("news", "虎扑", R.mipmap.xw_hp, "");
        xwList.add(xw1);
        xwList.add(xw2);
        xwList.add(xw3);
        xwList.add(xw4);
        xwList.add(xw5);
        xwList.add(xw6);
        xwList.add(xw7);
        //旅游
        ItemBeans ly1 = new ItemBeans("tour", "携程", R.mipmap.ly_xc, "");
        ItemBeans ly3 = new ItemBeans("tour", "同程", R.mipmap.ly_tc, "");
        ItemBeans ly5 = new ItemBeans("tour", "铁友", R.mipmap.ly_tyhcp, "");
        ItemBeans ly2 = new ItemBeans("tour", "去哪儿", R.mipmap.ly_qne, "");
        ItemBeans ly4 = new ItemBeans("tour", "途牛旅游", R.mipmap.ly_tnly, "");
        lyList.add(ly1);
        lyList.add(ly3);
        lyList.add(ly5);
        lyList.add(ly2);
        lyList.add(ly4);
        //地图
        ItemBeans dt1 = new ItemBeans("map", "高德地图", R.mipmap.dt_gddt, "");
        ItemBeans dt2 = new ItemBeans("map", "百度地图", R.mipmap.dt_bddt, "");
        dtList.add(dt1);
        dtList.add(dt2);
        //工作
        ItemBeans job1 = new ItemBeans("job", "智联招聘", R.mipmap.job_zlzp, "");
        ItemBeans job2 = new ItemBeans("job", "前程无忧", R.mipmap.job_qcwy, "");
        ItemBeans job3 = new ItemBeans("job", "Boos直聘", R.mipmap.job_bosszp, "");
        jobList.add(job1);
        jobList.add(job2);
        jobList.add(job3);
        //外卖
        ItemBeans wm1 = new ItemBeans("wm", "美团外卖", R.mipmap.wm_mt, "");
        ItemBeans wm3 = new ItemBeans("wm", "大众点评", R.mipmap.wm_dzdp, "");
        ItemBeans wm4 = new ItemBeans("wm", "百度外卖", R.mipmap.wm_bd, "");
        ItemBeans wm2 = new ItemBeans("wm", "饿了么", R.mipmap.wm_elm, "");
        wmList.add(wm1);
        wmList.add(wm3);
        wmList.add(wm4);
        wmList.add(wm2);
        //团购
        ItemBeans tg1 = new ItemBeans("tg", "美团", R.mipmap.tg_mt, "");
        ItemBeans tg2 = new ItemBeans("tg", "大众点评", R.mipmap.tg_dzdp, "");
        ItemBeans tg3 = new ItemBeans("tg", "百度糯米", R.mipmap.tg_bdnm, "");
        tgList.add(tg1);
        tgList.add(tg2);
        tgList.add(tg3);
        //信息
        ItemBeans xx1 = new ItemBeans("xx", "58同城", R.mipmap.xx_58, "");
        ItemBeans xx2 = new ItemBeans("xx", "赶集网", R.mipmap.xx_gj, "");
        xxList.add(xx1);
        xxList.add(xx2);

    }
}

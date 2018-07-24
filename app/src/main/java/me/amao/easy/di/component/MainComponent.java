package me.amao.easy.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.amao.easy.di.module.MainModule;

import com.jess.arms.di.scope.ActivityScope;

import me.amao.easy.mvp.ui.activity.MainActivity;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
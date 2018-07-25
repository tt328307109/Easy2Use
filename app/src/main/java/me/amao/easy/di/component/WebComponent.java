package me.amao.easy.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.amao.easy.di.module.WebModule;

import com.jess.arms.di.scope.ActivityScope;

import me.amao.easy.mvp.ui.activity.WebActivity;

@ActivityScope
@Component(modules = WebModule.class, dependencies = AppComponent.class)
public interface WebComponent {
    void inject(WebActivity activity);
}
package me.amao.easy.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.amao.easy.di.module.TestModule;

import com.jess.arms.di.scope.ActivityScope;

import me.amao.easy.mvp.ui.activity.TestActivity;

@ActivityScope
@Component(modules = TestModule.class, dependencies = AppComponent.class)
public interface TestComponent {
    void inject(TestActivity activity);
}
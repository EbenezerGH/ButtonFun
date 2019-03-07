package jfyg.buttonfun.di

import jfyg.buttonfun.data.DataManager
import jfyg.buttonfun.data.FileHelper
import jfyg.buttonfun.data.SharedPrefsHelper
import jfyg.buttonfun.view.ButtonFunRVAdapter
import jfyg.buttonfun.viewmodel.ButtonFunViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {
    factory { ButtonFunRVAdapter() }
    viewModel { (height: Float, width: Float) -> ButtonFunViewModel(height, width) }
}

val localDataSourceModule = module {
    single { SharedPrefsHelper(get()) }
    single { FileHelper(get()) }
    single { DataManager(get(), get()) }
}
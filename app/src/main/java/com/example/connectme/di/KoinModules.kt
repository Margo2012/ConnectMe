package com.example.connectme.di

import androidx.room.Room
import com.example.connectme.data.repository.UserRepository
import com.example.connectme.db.AppDataBase
import com.example.connectme.network.ApiService
import com.example.connectme.ui.profile.ProfileViewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule: Module = module{
    single {
        Retrofit.Builder()
            .baseUrl("https://plannerok.ru/docs#/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {get<Retrofit>().create(ApiService::class.java)}
}

val databaseModule: Module = module {
    single {
        Room.databaseBuilder(get(), AppDataBase::class.java, "my_database")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDataBase>().userDao() }
}

val repositoryModule: Module = module{
    single { UserRepository(get(), get()) }
}

val viewModelModule: Module = module {
    viewModel { ProfileViewModel(get()) }
}
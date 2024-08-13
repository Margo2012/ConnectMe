package com.example.connectme.di

import androidx.room.Room
import com.example.connectme.data.repository.UserRepository
import com.example.connectme.db.AppDataBase
import com.example.connectme.network.ApiService
import com.example.connectme.network.TokenAuthenticator
import com.example.connectme.network.TokenManager
import com.example.connectme.ui.profile.ProfileViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule: Module = module {
    single { TokenManager(androidContext()) }

    single {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val token = get<TokenManager>().accessToken
                if (token != null) {
                    request.addHeader("Authorization", "Bearer $token")
                }
                chain.proceed(request.build())
            }
            .authenticator(TokenAuthenticator(get(), get()))
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://plannerok.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    single { get<Retrofit>().create(ApiService::class.java) }
}

val databaseModule: Module = module {
    single {
        Room.databaseBuilder(get(), AppDataBase::class.java, "my_database")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDataBase>().userDao() }
}

val repositoryModule: Module = module {
    single { UserRepository(get(), get(), get()) }
}

val viewModelModule: Module = module {
    viewModel { ProfileViewModel(get()) }
}
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}


android {
    namespace = "com.example.kotlin_test2"
    compileSdk = 34

    dataBinding {
        enable = true
    }


    defaultConfig {
        applicationId = "com.example.kotlin_test2"
        minSdk = 32
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.name)
            }
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.paging:paging-runtime-ktx:3.1.1") // For Kotlin use paging-runtime-ktx
    implementation ("androidx.room:room-paging:2.5.0-alpha01")
    // alternatively - without Android dependencies for testing
    testImplementation("androidx.paging:paging-common-ktx:3.1.1") // For Kotlin use paging-common-ktx


    implementation("com.gyf.immersionbar:immersionbar:3.0.0")
    implementation("com.gyf.immersionbar:immersionbar-ktx:3.0.0")

    implementation("com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.4")//BaseRecyclerViewAdapterHelper
    implementation("io.github.vincent-series:sharp-retrofit:1.9")
    //FlowCallAdapter
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
    implementation("io.reactivex.rxjava3:rxjava:3.1.6")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    //RXJAVA
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0") //非必要依赖，log依赖，如果需要打印OkHttpLog需要添加
    //添加Retrofit网络库和gsonConverter的依赖，注意一定要2.6.0版本以上
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
//添加Jetpack中架构组件的依赖，注意viewmodel要添加viewmodel-ktx的依赖
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
//lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
//添加Glide的依赖用于图片加载
    implementation("com.github.bumptech.glide:glide:4.15.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    implementation("androidx.navigation:navigation-fragment:2.7.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")

    // 导入 Room 依赖库
    implementation("androidx.room:room-runtime:2.5.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    // 导入注解处理器 ( Java )
    annotationProcessor("androidx.room:room-compiler:2.5.1")
    // 导入 Room 依赖库
    implementation("androidx.room:room-runtime:2.5.1")
    // 导入注解处理器 ( Kotlin )
    kapt("androidx.room:room-compiler:2.5.1")
    kapt("com.alibaba:arouter-compiler:1.5.2")

    implementation("androidx.room:room-ktx:2.5.1")
    implementation("androidx.recyclerview:recyclerview:1.3.0")
    implementation("com.alibaba:arouter-api:1.5.2")
    annotationProcessor("com.alibaba:arouter-compiler:1.5.2")

    implementation("androidx.work:work-runtime-ktx:2.8.1")
//arouter 路由跳转

    androidTestImplementation("androidx.test:rules:1.5.0")

    testImplementation("org.mockito:mockito-core:2.13.0")
// 如果需要 mock final 类或方法的话，还要引入 mockito-inline 依赖
    testImplementation("org.mockito:mockito-inline:2.13.0")
    implementation("junit:junit:4.13.2")

    implementation("androidx.test.ext:junit-ktx:1.1.5")
    implementation("MVVM:app:1.0.0")

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
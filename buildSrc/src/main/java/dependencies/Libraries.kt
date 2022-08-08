package dependencies

import dependencies.android.androidX
import dependencies.android.vmLifeCycle
import dependencies.kotlin.coroutine
import dependencies.retrofit_okhttp.gson
import dependencies.retrofit_okhttp.okHttp
import dependencies.retrofit_okhttp.retrofit
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.appLibraries() {
    recycleView()
    androidCore()
    gander()
    testUnit()
    androidX()
    vmLifeCycle()
    materialDesign()
    coroutine()
    gson()
    okHttp()
    retrofit()
    glide()
    dagger()
    androidPaging()
    youtube()

}
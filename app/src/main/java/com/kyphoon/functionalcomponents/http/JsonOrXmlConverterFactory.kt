package com.kyphoon.functionalcomponents.http

import com.kyphoon.functionalcomponents.annotation.ResponseFormat
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.lang.reflect.Type

/**
 *
 * @ProjectName: functionalcomponents
 * @Package: com.kyphoon.functionalcomponents.http
 * @ClassName: JsonOrXmlConverterFactory
 * @Description:适配gson和xml，string三种解析格式的解析器
 * @Author: kyphoon
 * @CreateDate: 2020-06-05 10:10
 * @UpdateUser:
 * @UpdateDate: 2020-06-05 10:10
 * @UpdateRemark:
 * @Version: 1.0
 */
class JsonOrXmlConverterFactory : Converter.Factory() {

    val xmlFactory : Converter.Factory = SimpleXmlConverterFactory.create()
    val gsonFactory : Converter.Factory = GsonConverterFactory.create()
    val stringFactory : Converter.Factory = StringConverterFactory.create()

    companion object{
        fun create() : JsonOrXmlConverterFactory{
            return JsonOrXmlConverterFactory()
        }
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        for(annotation in annotations){
            if(!(annotation is ResponseFormat)){
                continue
            }
            var value : String = (annotation as ResponseFormat).value
            if(value.equals("gson")){
                return gsonFactory.responseBodyConverter(type, annotations, retrofit)
            }else if(value.equals("xml")){
                return xmlFactory.responseBodyConverter(type, annotations, retrofit)
            }else if(value.equals("string")){
                return stringFactory.responseBodyConverter(type, annotations, retrofit)
            }
        }
        return return gsonFactory.responseBodyConverter(type, annotations, retrofit)
    }
}
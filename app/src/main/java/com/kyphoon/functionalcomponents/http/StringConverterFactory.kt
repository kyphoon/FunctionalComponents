package com.kyphoon.functionalcomponents.http

import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.nio.charset.Charset

/**
 *
 * @ProjectName: functionalcomponents
 * @Package: com.kyphoon.functionalcomponents.http
 * @ClassName: StringConverterFactory
 * @Description: string字符串解析
 * @Author: kyphoon
 * @CreateDate: 2020-07-12 16:17
 * @UpdateUser:
 * @UpdateDate: 2020-07-12 16:17
 * @UpdateRemark:
 * @Version: 1.0
 */
class StringConverterFactory : Converter.Factory(){

    companion object{
        fun create() : StringConverterFactory{
            return StringConverterFactory()
        }
    }
    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        return StringRequestBodyConverter()
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return StringResponseBodyConverter()
    }
}

class StringResponseBodyConverter : Converter<ResponseBody,String>{
    override fun convert(value: ResponseBody): String? {
        try {
            return value.string()
        } finally {
            value.close()
        }
    }
}

class StringRequestBodyConverter : Converter<String, RequestBody>{
    val MEDIA_TYPE : MediaType? = MediaType.parse("text/html; charset=UTF-8")
    val UTF_8 : Charset = Charset.forName("UTF-8")

    override fun convert(value: String): RequestBody? {
        return RequestBody.create(MEDIA_TYPE,value)

    }
}
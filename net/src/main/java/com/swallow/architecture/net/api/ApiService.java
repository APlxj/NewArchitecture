package com.swallow.architecture.net.api;

import com.swallow.architecture.net.base.BaseEntry;
import com.swallow.architecture.net.base.BaseUrl;
import com.swallow.architecture.orm.model.Item;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
public interface ApiService {

    /***********************************************************************************************
     *GET 示例
     ***********************************************************************************************/
    @Headers({BaseUrl.UTF_8})
    @GET(BaseUrl.MSG)
    Observable<BaseEntry<String>> getMsg(@Query("page") int page, @Query("str") String str);

    /***********************************************************************************************
     *POST 示例
     ***********************************************************************************************/
    @Headers({BaseUrl.URLENCODED})
    @POST(BaseUrl.MSG)
    Observable<BaseEntry<List<String>>> postMsg(@Body Item item);


    /***********************************************************************************************
     *文件 示例
     ***********************************************************************************************/
    //上传
    @Multipart
    @POST(BaseUrl.FILEPATH)
    Observable<Item> upFile(@Part MultipartBody.Part file);

    //下载
    @GET
    @Headers({"Accept-Encoding: identity"})
    Observable<ResponseBody> downFile(@Url String url);

}
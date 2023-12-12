package datas.remote.api

import io.ktor.client.request.get
import network.httpClient

/**
 * @desc
 *
 * @author weihl
 * @date 2023/12/12
 */

class ApiDownload {
   suspend fun fetch(url:String){
       httpClient.get(url){

       }
   }
}


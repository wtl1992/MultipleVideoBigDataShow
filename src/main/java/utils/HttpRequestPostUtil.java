package utils;

import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

public class HttpRequestPostUtil {

    public static String requestHttp(String url, String encoding, Map<String,String> params,String cookie){
        byte [] buffer = new byte[1024];
        int length = -1;
        StringBuffer content = new StringBuffer();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedInputStream is = null;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();

            //设置参数
            urlConnection.setDoOutput(true);     //需要输出
            urlConnection.setDoInput(true);      //需要输入
            urlConnection.setUseCaches(false);   //不允许缓存
            urlConnection.setRequestMethod("POST");      //设置POST方式连接

            //设置请求属性
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            urlConnection.setRequestProperty("Charset", encoding);
            if (cookie != null){
                urlConnection.setRequestProperty("Cookie",cookie);
            }

            //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
            urlConnection.connect();

            //建立输入流，向指向的URL传入参数
            if (params.keySet().size() != 0){
                StringBuffer param = new StringBuffer();
                for (String par : params.keySet()){
                    param.append(par +"="+ URLEncoder.encode(params.get(par),encoding) +"&");
                }
                DataOutputStream dos=new DataOutputStream(urlConnection.getOutputStream());

                dos.writeBytes(param.toString().substring(0,param.toString().length()-1));
                dos.flush();
                dos.close();
            }
            is = new BufferedInputStream(urlConnection.getInputStream());
            do{
                length = is.read(buffer);
                if (length !=-1){
                    //content.append(new String(buffer,0,length,encoding));
                    outputStream.write(buffer,0,length);
                }
            }while(length!=-1);
            content.append(new String(outputStream.toByteArray(),0,outputStream.toByteArray().length,encoding));
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return content.toString();
    }

    public static String requestHttpByJSONObject(String url, String encoding, JSONObject params,String cookie){
        byte [] buffer = new byte[1024];
        int length = -1;
        StringBuffer content = new StringBuffer();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedInputStream is = null;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();

            //设置参数
            urlConnection.setDoOutput(true);     //需要输出
            urlConnection.setDoInput(true);      //需要输入
            urlConnection.setUseCaches(false);   //不允许缓存
            urlConnection.setRequestMethod("POST");      //设置POST方式连接

            //设置请求属性
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            urlConnection.setRequestProperty("Charset", encoding);
            if (cookie != null){
                urlConnection.setRequestProperty("Cookie",cookie);
            }

            //建立输入流，向指向的URL传入参数
            if (params.keySet().size() != 0){
                DataOutputStream dos=new DataOutputStream(urlConnection.getOutputStream());
                dos.write(params.toString().getBytes(encoding));
                dos.flush();
                dos.close();
            }
            is = new BufferedInputStream(urlConnection.getInputStream());
            do{
                length = is.read(buffer);
                if (length !=-1){
                    //content.append(new String(buffer,0,length,encoding));
                    outputStream.write(buffer,0,length);
                }
            }while(length!=-1);
            content.append(new String(outputStream.toByteArray(),0,outputStream.toByteArray().length,encoding));
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return content.toString();
    }


    public static String upload(String remoteUrl,
                                byte [] bytes,
                                String fileName,
                                String encoding,
                                JSONObject params){
        byte [] buffer = new byte[1024];
        int length = -1;
        StringBuffer content = new StringBuffer();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedInputStream is = null;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(remoteUrl).openConnection();

            //设置参数
            urlConnection.setDoOutput(true);     //需要输出
            urlConnection.setDoInput(true);      //需要输入
            urlConnection.setUseCaches(false);   //不允许缓存
            urlConnection.setRequestMethod("POST");      //设置POST方式连接

            //设置请求属性
            //设置数据的边界
            String boundary = "------" + System.currentTimeMillis();
            urlConnection.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);
            urlConnection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            urlConnection.setRequestProperty("Charset", encoding);

            DataOutputStream dos=new DataOutputStream(urlConnection.getOutputStream());

            //建立输入流，向指向的URL传入参数
            if (params.keySet().size() != 0){
                StringBuffer param = new StringBuffer();
                param.append(params.toString());

                dos.writeBytes(param.toString().substring(0,param.toString().length()-1));
            }
            //准备头信息
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("--");
            stringBuilder.append(boundary);
            stringBuilder.append("\r\n");
            stringBuilder.append("Content-Disposition:form-data;name=\"media\";filename=\""+fileName+"\"\r\n");
            stringBuilder.append("Content-Type:application/octet-stream\r\n\r\n");
            //写入头信息
            dos.write(stringBuilder.toString().getBytes());

            //开始往remoteUrl写入文件
            dos.write(bytes);

            //写入尾部信息
            String footer = "\r\n--" + boundary + "--\r\n";
            dos.write(footer.getBytes());
            dos.flush();
            dos.close();

            is = new BufferedInputStream(urlConnection.getInputStream());
            do{
                length = is.read(buffer);
                if (length !=-1){
                    //content.append(new String(buffer,0,length,encoding));
                    outputStream.write(buffer,0,length);
                }
            }while(length!=-1);
            content.append(new String(outputStream.toByteArray(),0,outputStream.toByteArray().length,encoding));
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }


    public static byte [] exchangeMusicsUrl(String url){
        byte [] buffer = new byte[1024];
        int length = -1;
        StringBuffer content = new StringBuffer();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedInputStream is = null;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();

            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.3");
            urlConnection.setRequestProperty("cache","0");
            is = new BufferedInputStream(urlConnection.getInputStream());
            do{
                length = is.read(buffer);
                if (length !=-1){
                    //content.append(new String(buffer,0,length,encoding));
                    outputStream.write(buffer,0,length);
                }
            }while(length!=-1);

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return outputStream.toByteArray();
    }
}

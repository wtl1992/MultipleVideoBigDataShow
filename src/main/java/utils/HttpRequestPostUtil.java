package utils;

import net.sf.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpRequestPostUtil {

    /**
     *
     * @param url 请求的post的url地址
     * @param encoding 所使用的编码格式
     * @param params 所传入的参数设置
     * @param cookie
     * @param jsonStr 所传入的字节数组
     * @return
     */
    public static String requestHttp(String url, String encoding, String content_type, Map<String,String> params,String cookie,String jsonStr){
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
            urlConnection.setRequestProperty("Content-Type", content_type);
            urlConnection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            urlConnection.setRequestProperty("Charset", encoding);
            if (cookie != null){
                urlConnection.setRequestProperty("Cookie",cookie);
            }

            //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
            urlConnection.connect();

            //建立输入流，向指向的URL传入参数
            if (params != null && params.keySet().size() != 0){
                StringBuffer param = new StringBuffer();
                for (String par : params.keySet()){
                    param.append(par +"="+ URLEncoder.encode(params.get(par),encoding) +"&");
                }
                DataOutputStream dos=new DataOutputStream(urlConnection.getOutputStream());

                dos.writeBytes(param.toString().substring(0,param.toString().length()-1));
                dos.flush();
                dos.close();
            }
            else if (jsonStr != null && jsonStr.length() > 0){
                DataOutputStream dos=new DataOutputStream(urlConnection.getOutputStream());

                dos.write(jsonStr.getBytes(encoding));
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

    public static String requestHttpByJSONObject(String url, String encoding, JSONObject params, String cookie){
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

    /**
     *
     * @param url 上传的地址
     * @param params 所传入的参数设置
     * @param fileMap 多文件上传的文件列表
     * @param fileDatas 多文件上传的所对应的二进制数组
     * @param cookie 所需要的cookie
     * @return
     */
    public static String upload(String url,Map<String, String> params, Map<String, String> fileMap, List<byte[]> fileDatas,String cookie,String multipartFileName)
    {
        String ret = formUpload(url,params, fileMap,fileDatas,cookie,multipartFileName);

//        System.out.println(ret);

        return ret;
    }

    /**
     * 上传图片
     *
     * @param urlStr 请求文件上传的url地址
     * @param params 所要传递的request header的参数
     * @param fileMap 所要传的文件列表
     * @param fileDatas 所要传的文件的字节数组列表
     * @param cookie 所需要的cookie
     * @param multipartFileName 上传多个文件的唯一名称标识，即MultipartFile[]的名称
     * @return
     */
    public static String formUpload(String urlStr,Map<String, String> params,
                                    Map<String, String> fileMap,
                                    List<byte[]> fileDatas,
                                    String cookie,String multipartFileName) {
        String res = "";
        HttpURLConnection conn = null;
        String BOUNDARY = "---------------------------"+UUID.getUUID(); //boundary就是request头和上传文件内容的分隔符
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn
                    .setRequestProperty("User-Agent",
                            "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);
            if (cookie != null && cookie.length() > 0){
                conn.setRequestProperty("Cookie",cookie);
            }

            OutputStream out = new DataOutputStream(conn.getOutputStream());
            //建立输入流，向指向的URL传入参数
            if (params != null && params.keySet().size() != 0){
                StringBuffer param = new StringBuffer();
                for (String par : params.keySet()){
                    param.append(par +"="+ URLEncoder.encode(params.get(par),"utf-8") +"&");
                }
                DataOutputStream dos=new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(param.toString().substring(0,param.toString().length()-1));
                dos.flush();
                dos.close();
            }

            // file
            if (fileMap != null) {
                Iterator iter = fileMap.entrySet().iterator();
                int index = 0;
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    File file = new File(inputValue);
                    String filename = file.getName();
                    String contentType = new MimetypesFileTypeMap()
                            .getContentType(file);
                    if (filename.endsWith(".png")) {
                        contentType = "image/png";
                    }
                    if (contentType == null || contentType.equals("")) {
                        contentType = "application/octet-stream";
                    }

                    StringBuffer strBuf = new StringBuffer();
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append(
                            "\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\""
                            + multipartFileName + "\"; filename=\"" + filename
                            + "\"\r\n");
                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

                    out.write(strBuf.toString().getBytes());

                    out.write(fileDatas.get(index),0,fileDatas.get(index).length);
                    index ++;
                }
            }

            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();

            // 读取返回数据
            StringBuffer strBuf = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            res = strBuf.toString();
            reader.close();
            reader = null;
        } catch (Exception e) {
            System.out.println("发送POST请求出错。" + urlStr);
            e.printStackTrace();
        } finally {
//            if (conn != null) {
//                conn = null;
//            }
        }
        return res;
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


    /**
     * 上传多个文件
     * @param urlStr http请求路径
     * @param params 请求参数
     * @param files 上传的文件map
     * @return
     */
    public static InputStream uploadMultipartFile(String urlStr, Map<String, String> params,
                                                  Map<String, byte[]> files, String multipartFileName,String suffix) {
       final String BOUNDARY = "-------45962402127348";
       final String FILE_ENCTYPE = "multipart/form-data";
        InputStream is = null;

        try {
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setConnectTimeout(100000);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            con.setRequestProperty("Content-Type", FILE_ENCTYPE + "; boundary="
                    + BOUNDARY);

            StringBuilder sb = null;
            DataOutputStream dos = new DataOutputStream(con.getOutputStream());;
            if (params != null) {
                sb = new StringBuilder();
                for (String s : params.keySet()) {
                    sb.append("--");
                    sb.append(BOUNDARY);
                    sb.append("\r\n");
                    sb.append("Content-Disposition: form-data; name=\"");
                    sb.append(s);
                    sb.append("\"\r\n\r\n");
                    sb.append(params.get(s));
                    sb.append("\r\n");
                }

                dos.write(sb.toString().getBytes());
            }

            if (files != null) {
                for (String s : files.keySet()) {
                    sb = new StringBuilder();
                    sb.append("--");
                    sb.append(BOUNDARY);
                    sb.append("\r\n");
                    sb.append("Content-Disposition: form-data; name=\"");
                    sb.append(multipartFileName);
                    sb.append("\"; filename=\"");
                    sb.append(UUID.getUUID()+"."+suffix);
                    sb.append("\"\r\n");
                    sb.append("Content-Type: application/octet-stream");//这里注意！如果上传的不是图片，要在这里改文件格式，比如txt文件，这里应该是text/plain
                    sb.append("\r\n\r\n");
                    dos.write(sb.toString().getBytes());

                    System.out.println(sb.toString());

                    dos.write(files.get(s));
                    dos.write("\r\n".getBytes());
                }

                sb = new StringBuilder();
                sb.append("--");
                sb.append(BOUNDARY);
                sb.append("--\r\n");
                dos.write(sb.toString().getBytes());
            }
            dos.flush();

            if (con.getResponseCode() == 200)
                is = con.getInputStream();

            dos.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }
}

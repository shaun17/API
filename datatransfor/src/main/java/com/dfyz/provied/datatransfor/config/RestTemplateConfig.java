package com.dfyz.provied.datatransfor.config;


import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Configuration
public class RestTemplateConfig {
    @Autowired
    HttpPoolProperties httpPoolProperties;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory());
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        // 重新设置StringHttpMessageConverter字符集为UTF-8，解决中文乱码问题
        HttpMessageConverter<?> converterTarget = null;
        for (HttpMessageConverter<?> item : converterList) {
            // StringHttpMessageConverter 默认的编码机是ISO_8859_1，并移除
            if (StringHttpMessageConverter.class == item.getClass()) {
                converterTarget = item;
                break;
            }
        }
        if (null != converterTarget) {
            converterList.remove(converterTarget);
        }
        // 重新将StringHttpMessageConverter设为utf8编码，解决中文乱码问题
        converterList.add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        // 设置错误处理器
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    @Bean
    public HttpClient httpClient() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        try {
            // 设置信任ssl访问
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (arg0, arg1) -> true).build();

            httpClientBuilder.setSSLContext(sslContext);
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
                    hostnameVerifier);

            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", SSLConnectionSocketFactory.getSocketFactory()).build();
            // 使用Httpclient连接池的方式配置(推荐)，同时支持netty，okHttp以及其他http框架
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
            // 最大连接数
            connectionManager.setMaxTotal(httpPoolProperties.getMaxTotalConnect());
            // 路由并发数
            connectionManager.setDefaultMaxPerRoute(httpPoolProperties.getMaxConnectPerRoute());
            // 配置连接池
            httpClientBuilder.setConnectionManager(connectionManager);
            // 设置充实次数
            httpClientBuilder
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(httpPoolProperties.getRetryTimes(), true));
            // 设置默认请求头
            httpClientBuilder.setDefaultHeaders(getDefaultHeaders());
            // 设置长连接保持策略
            httpClientBuilder.setKeepAliveStrategy(connectionKeepAliveStrategy());

//	        RequestConfig requestConfig = RequestConfig.custom()
//	                .setSocketTimeout(httpPoolProperties.getSocketTimeout()) //服务器返回数据(response)的时间，超过抛出read timeout
//	                .setConnectTimeout(httpPoolProperties.getConnectTimeout()) //连接上服务器(握手成功)的时间，超出抛出connect timeout
//	                .setConnectionRequestTimeout(httpPoolProperties.getConnectionRequestTimeout())//从连接池中获取连接的超时时间，超时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
//	                .build();
            return httpClientBuilder.build();
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            e.printStackTrace();
        }
        return null;
//		return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).setConnectionManager(connectionManager)
//				.setDefaultHeaders(getDefaultHeaders()).build();
    }

    private List<Header> getDefaultHeaders() {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN"));
        headers.add(new BasicHeader("Connection", "Keep-Alive"));
        return headers;
    }

    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        return (response, context) -> {
            // Honor 'keep-alive' header
            HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
//	        log.info("HeaderElement:{}", JSON.toJSONString(he));
                String param = he.getName();
                String value = he.getValue();
                if (value != null && "timeout".equalsIgnoreCase(param)) {
                    try {
                        return Long.parseLong(value) * 1000;
                    } catch (NumberFormatException ignore) {
//	            log.error("解析长连接过期时间异常",ignore);
                    }
                }
            }
            HttpHost target = (HttpHost) context.getAttribute(HttpClientContext.HTTP_TARGET_HOST);
            // 如果请求目标地址,单独配置了长连接保持时间,使用该配置
            Optional<Map.Entry<String, Integer>> any = Optional.ofNullable(httpPoolProperties.getKeepAliveTargetHost())
                    .orElseGet(HashMap::new).entrySet().stream()
                    .filter(e -> e.getKey().equalsIgnoreCase(target.getHostName())).findAny();
            // 否则使用默认长连接保持时间
            return any.map(en -> en.getValue() * 1000L).orElse(httpPoolProperties.getKeepAliveTime() * 1000L);
        };
    }
}
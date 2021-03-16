package com.ali.wzq.config;

import com.ali.wzq.utils.RedisUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * redis 的基础配置。
 *
 * @Package com.ali.wzq.config
 * @Author wuzhiqing
 * @className ALI-DAP
 * @Version v1.0.0
 * @createData 2021/3/14  16:09
 */

@Configuration
@Slf4j
class RedisConfig {


    /**
     * 初始化redisTemplate 模板对象
     * @param redisConnectionFactory
     * @return
     */
//    @Bean
//    @SuppressWarnings("all")
//    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//
//        // key采用String的序列化方式
//        redisTemplate.setKeySerializer(stringRedisSerializer);
//        // hash的key也采用String的序列化方式
//        redisTemplate.setHashKeySerializer(stringRedisSerializer);
//        // value序列化方式采用jackson
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        // hash的value序列化方式采用jackson
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.afterPropertiesSet();

//        return redisTemplate;
//    }


    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.host}")
    private String hostName;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private Integer maxIdle;

    @Value("${spring.redis.jedis.pool.max-active}")
    private Integer maxTotal;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private Integer maxWaitMillis;

    @Value("${redis.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;

    @Value("${redis.numTestsPerEvictionRun}")
    private Integer numTestsPerEvictionRun;

    @Value("${redis.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${redis.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.redis.sentinel.nodes}")
    private String sentinelNodes;

    /**
     * JedisPoolConfig 连接池
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲数
        jedisPoolConfig.setMaxIdle(maxIdle);
//        // 连接池的最大数据库连接数
//        jedisPoolConfig.setMaxTotal(maxTotal);
//        // 最大建立连接等待时间
//        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
//        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
//        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
//        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
//        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
//        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
//        // 在空闲时检查有效性, 默认false
//        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        return jedisPoolConfig;
    }
    /**
     * 配置redis的哨兵
     * @return RedisSentinelConfiguration
     * @autor lpl
     * @date 2017年12月21日
     * @throws
     */
    @Bean
    public RedisSentinelConfiguration sentinelConfiguration(){
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        //配置matser的名称
        RedisNode redisNode = new RedisNode(hostName, port);
        redisNode.setName("mymaster");
        redisSentinelConfiguration.master(redisNode);
        //配置redis的哨兵sentinel
        String[] split = sentinelNodes.split(",");
        String[] sentinelNode = split[0].split(":");
        RedisNode senRedisNode = new RedisNode(sentinelNode[0],Integer.parseInt(sentinelNode[1]));
        Set<RedisNode> redisNodeSet = new HashSet<>();
        redisNodeSet.add(senRedisNode);
        redisSentinelConfiguration.setSentinels(redisNodeSet);
        return redisSentinelConfiguration;
    }
    /**
     * 配置工厂
     * @param jedisPoolConfig
     * @return
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig,RedisSentinelConfiguration sentinelConfig) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(sentinelConfig,jedisPoolConfig);
        return jedisConnectionFactory;
    }
    /**
     * 实例化 RedisTemplate 对象
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }
    /**
     * 设置数据存入 redis 的序列化方式,并开启事务
     *
     * @param redisTemplate
     * @param factory
     */
    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);
    }
    /**
     * 封装RedisTemplate
     * @Title: redisUtil
     * @return RedisUtil
     * @autor lpl
     * @date 2017年12月21日
     * @throws
     */
    @Bean(name = "RedisUtils")
    public RedisUtils redisUtil(RedisTemplate<String, Object> redisTemplate) {
        RedisUtils redisUtil = new RedisUtils();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }
}

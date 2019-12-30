package com.payno.boot.config.env;

/**
 * @author payno
 * @date 2019/12/27 16:02
 * @description
 */
public class EnvMain {
    /**
     * Spring3.1提供了新的属性管理API，而且功能非常强大且很完善，对于一些属性配置信息都应该使用新的API来管理
     *
     * 新的属性管理API
     *
     * PropertySource：属性源，key-value属性对抽象，比如用于配置数据
     *
     * PropertyResolver：属性解析器，用于解析相应key的value
     *
     * Environment：环境，本身是一个PropertyResolver，但是提供了Profile特性，即可以根据环境得到相应数据（即激活不同的Profile，可以得到不同的属性数据，比如用于多环境场景的配置（正式机、测试机、开发机DataSource配置））
     *
     * Profile：剖面，只有激活的剖面的组件/配置才会注册到Spring容器，类似于maven中profile
     */

    /**
     * SpringMvc Binder
     *
     * 1.@InitBinder
     * 2.@RequestBody && Format/Converter SPI
     * 3.WebBindingInitializer
     */
}

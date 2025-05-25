package com.sia.mp;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Types;
import java.util.Collections;

@SpringBootApplication
@MapperScan("com.sia.mp.mapper")
public class MpApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpApplication.class, args);

            FastAutoGenerator.create("jdbc:mysql://localhost:3306/school", "root", "123456")
                    .globalConfig(builder -> {
                        builder.author("baomidou") // 设置作者
                                .enableSwagger() // 开启 swagger 模式
                                .outputDir("mp.src.main.java"); // 指定输出目录
                    })
                    .dataSourceConfig(builder ->
                            builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                                int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                                if (typeCode == Types.SMALLINT) {
                                    // 自定义类型转换
                                    return DbColumnType.INTEGER;
                                }
                                return typeRegistry.getColumnType(metaInfo);
                            })
                    )
                    .packageConfig(builder ->
                            builder.parent("com.sia.mp") // 设置父包名
                                    .moduleName("mp") // 设置父包模块名
                                    .pathInfo(Collections.singletonMap(OutputFile.xml,"C:\\Users\\32156\\IdeaProjects\\springBoot3\\springBoot3\\mp\\src\\main\\java\\com\\sia\\mp\\mapper")) // 设置mapperXml生成路径
                    )
                    .strategyConfig(builder ->
                            builder.addInclude("users") // 设置需要生成的表名// 设置过滤表前缀
                    )
                    .templateEngine(new FreemarkerTemplateEngine()).execute(); // 使用Freemarker引擎模板，默认的是Velocity引擎模板.execute();

    }

}

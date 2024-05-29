package ru.geekbrains.seminar6_hw.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
public class IntegrationConfig {

    @Bean
    public MessageChannel textInputChanel(){
        return new DirectChannel();
    }


    @Bean
    public MessageChannel fileWriterChanel(){
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "textInputChanel",outputChannel = "fileWriterChanel")
    public GenericTransformer<String, String> myTransformer(){
        return text -> text.toUpperCase().trim();
    }

    @Bean
    @ServiceActivator(inputChannel = "fileWriterChanel")
    public FileWritingMessageHandler meMessageHandler(){
        FileWritingMessageHandler handler =
                new FileWritingMessageHandler(new File("C:\\Users\\funpa\\Desktop\\GB\\Spring\\seminar6_hw"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }
}

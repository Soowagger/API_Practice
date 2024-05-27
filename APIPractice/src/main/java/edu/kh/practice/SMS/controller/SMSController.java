package edu.kh.practice.SMS.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.kh.practice.SMS.model.service.SMSService;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Controller
@RequiredArgsConstructor
@PropertySource("classpath:/config.properties")
@RequestMapping("sms")
public class SMSController {
	
	@Value("${coolsms.api.key}")
	private String apiKey;
	
	@Value("${coolsms.api.secret}")
	private String apiSecret;
	
	@Value("${coolsms.api.phone}")
	private String senderNumber;
	
	private final DefaultMessageService messageService;

        
	public SMSController() {
		
		// 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
		this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
		
	}


    @PostMapping("sms/send")
    public SingleMessageSentResponse sendOne() {
    	
  
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom(senderNumber); // 발신번호 입력
        message.setTo("01032920409");
        message.setText("테스트 문자"); 

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return response;
    }

	
	
}

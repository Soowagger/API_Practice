package edu.kh.practice.SMS.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.practice.SMS.model.mapper.SMSMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class SMSServiceImpl {

	private final SMSMapper mapper;
	
}

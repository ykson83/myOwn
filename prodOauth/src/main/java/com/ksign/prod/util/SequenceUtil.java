package com.ksign.prod.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ksign.prod.entity.SequenceEntity;
import com.ksign.prod.mapper.impl.SequenceRepository;


@Component
public class SequenceUtil {
	@Autowired SequenceRepository seqRepo;
	
	public String getNextId(String seqName, String prefix, int length) {
		SequenceEntity seq = seqRepo.select(seqName);
		
		if(seq == null) {
			seq = new SequenceEntity();
			seq.setName(seqName);
			seq.setSeqValue(1);
			seqRepo.insert(seq);
		} else {
			seq.setSeqValue(seq.getSeqValue() + 1L);
			seqRepo.update(seq);
		}
		
		int zeroCount = length - (seq.getSeqValue() + "").length() - prefix.length();
		StringBuffer sb = new StringBuffer();
		sb.append(prefix.toUpperCase());
		
		for(int i = 0; i < zeroCount; i++) {
			sb.append(0);
		}
		
		sb.append(seq.getSeqValue());
		
		return sb.toString();
	}
}
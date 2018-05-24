package com.card.common.util;

import com.card.common.util.bean.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/11
 * Description：
 */
@Service
public class SequenceUtil {
	private Map<String, Sequence> sequenceMap;

	@Autowired
	private Sequence defaultSequence;

	public SequenceUtil() {
	}

	public void setDefaultSequence(Sequence defaultSequence) {
		this.defaultSequence = defaultSequence;
	}

	public void setSequenceMap(Map<String, Sequence> sequenceMap) {
		this.sequenceMap = sequenceMap;
	}

	public long get(String idName) {
		Sequence sequence = null;
		if (this.sequenceMap != null) {
			/**
			 * 从sequenceMap中获取 idName 对应的 下一个id值
			 */
			sequence = (Sequence)this.sequenceMap.get(idName);
		}

		if (sequence == null) {
			if (this.defaultSequence != null) {
				return this.defaultSequence.get(idName);
			} else {
				throw new RuntimeException("sequence " + idName + " undefined!");
			}
		} else {
			return sequence.get(idName);
		}
	}
}
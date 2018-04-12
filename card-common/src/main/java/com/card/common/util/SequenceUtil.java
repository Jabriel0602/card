package com.card.common.util;

import com.card.common.util.bean.Sequence;

import java.util.Map;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/11
 * Description：
 */

public class SequenceUtil {
	private Map<String, Sequence> sequenceMap;
	private Sequence defaultSequence;

	public SequenceUtil() {
	}

	public void setDefaultSequence(Sequence defaultSequence) {
		this.defaultSequence = defaultSequence;
	}

	public void setSequenceMap(Map<String, Sequence> sequenceMap) {
		this.sequenceMap = sequenceMap;
	}

	public long get(String name) {
		Sequence sequence = null;
		if (this.sequenceMap != null) {
			sequence = (Sequence)this.sequenceMap.get(name);
		}

		if (sequence == null) {
			if (this.defaultSequence != null) {
				return this.defaultSequence.get(name);
			} else {
				throw new RuntimeException("sequence " + name + " undefined!");
			}
		} else {
			return sequence.get(name);
		}
	}
}